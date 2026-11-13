package com.assignment4.tasks.task1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

// This Class handles the continuous listening for incoming messages from the server
public class MessageListener implements Runnable {

  private final DatagramSocket clientSocket;
  private final LamportTimestamp lc;
  byte[] receiveData = new byte[1024];

  public MessageListener(DatagramSocket clientSocket, LamportTimestamp lc) {
    this.clientSocket = clientSocket;
    this.lc = lc;
  }

  @Override
  public void run() {
    // TODO:
    // Write your code here to continuously listen for incoming messages from the server and display them.
    // Make use of the Datagram sockets and functions in Java https://docs.oracle.com/javase/8/docs/api/java/net/DatagramSocket.html


    // TODO:
    // Update the clock based on the timestamp received from the server.

  }
}