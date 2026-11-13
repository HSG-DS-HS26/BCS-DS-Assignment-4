package com.assignment4.common;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.assignment4.common.UdpServer.isInteger;

public class MessageHandler implements Runnable {
  private final static Logger LOGGER = Logger.getLogger(MessageHandler.class.getCanonicalName());
  private static final ExecutorService executor = Executors.newFixedThreadPool(3); // executor to broadcast concurrently

  // prepare response message for the client
  byte[] sendData = new byte[1024];
  private final DatagramSocket socket;
  private final DatagramPacket receivePacket;
  private final InetAddress clientIP;
  private final int clientPort;
  ArrayList<String> activities;

  public MessageHandler(DatagramSocket serverSocket, DatagramPacket receivePacket, InetAddress clientIP, int clientPort, ArrayList<String> activities) {
    this.socket = serverSocket;
    this.receivePacket = receivePacket;
    this.clientIP = clientIP;
    this.clientPort = clientPort;
    this.activities=activities;
  }

  @Override
  public void run() {

    //try {
    String responseMessage = null;
    // receive data from the client
    String messageFromClient = (new String(receivePacket.getData(), 0, receivePacket.getLength())).trim();

    if (!messageFromClient.isEmpty()) {
      // process the received message
      String[] receivedMessage = messageFromClient.split(":");
      String receivedMessageBody = receivedMessage[0];

      if (!receivedMessageBody.isEmpty()) {

        //socket.setBroadcast(true);
        // check if the user wants to see the history and send the message continuously
        if (receivedMessageBody.equalsIgnoreCase("history")) {
          LOGGER.log(Level.INFO, "Sending the chat history...");
          sendScrambledHistory();
        } else {
          LOGGER.log(Level.INFO, "Received message: " + messageFromClient);
          LOGGER.log(Level.INFO, "Broadcasting to connected clients: " + messageFromClient);

          sendData = messageFromClient.getBytes();

          for (InetSocketAddress client : UdpServer.subscribedClients) {
            // Skip the sender
            if (!client.getAddress().equals(clientIP) || client.getPort() != clientPort) {
              // Submit a new task to the executor for each client
              executor.submit(() -> {
                try {
                  if (isInteger(receivedMessage[1])) {
                    // Apply a random delay before sending the message if LT client is used
                    long randomDelay = (long) (Math.random() * 4000) + 1000;
                    TimeUnit.MILLISECONDS.sleep(randomDelay);
                  }

                  // Send the packet to the client
                  DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, client.getAddress(), client.getPort());
                  socket.send(sendPacket);
                } catch (IOException | InterruptedException e) {
                  e.printStackTrace();
                }
              });
            }
          }

          synchronized (activities) {
            activities.add(receivedMessageBody);
          }

        }
      }
    }
  }
  // Ensure the executor shuts down when the server stops (if needed)
  public static void shutdownExecutor() {
    executor.shutdown();
    try {
      if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
        executor.shutdownNow();
      }
    } catch (InterruptedException e) {
      executor.shutdownNow();
    }
  }

  //TODO:Move this to unit tests
  private void sendScrambledHistory() {
    // Define the scrambled chat history
    List<String> scrambledHistory = new ArrayList<>(Arrays.asList(
            "A:[1, 0, 0, 0]:1",
            "B:[1, 1, 0, 0]:2",
            "D:[2, 1, 1, 0]:1",
            "C:[1, 1, 1, 0]:3",
            "E:[2, 2, 1, 0]:2",
            "F:[3, 2, 1, 0]:1"
    ));

    // Send the scrambled chat history to the client
    for (String message : scrambledHistory) {
      try {
        sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIP, clientPort);
        socket.send(sendPacket);
        Thread.sleep(200);
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
    }

  }

}