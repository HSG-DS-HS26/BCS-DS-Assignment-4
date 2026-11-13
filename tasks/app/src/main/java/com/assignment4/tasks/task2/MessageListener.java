package com.assignment4.tasks.task2;

import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

public class MessageListener implements Runnable {

  private final DatagramSocket clientSocket;
  private final VectorClock vcl;
  private final int id;
  private final byte[] receiveData = new byte[1024]; // Buffer for incoming data
  private final List<Message> buffer = new ArrayList<>(); // This buffer can be used for Task 2.2 to store messages that cannot yet be delivered.

  public MessageListener(DatagramSocket clientSocket, VectorClock vcl, int id) {
    this.clientSocket = clientSocket;
    this.vcl = vcl;
    this.id = id;
  }

  @Override
  public void run() {

  // TODO:
  /*
      Write your code here to continuously listen for incoming messages from the server
      You should first process the received message and then update the vector clock based on the received message (you can use .replaceAll("[\\[\\]]", "").split(",\\s*"); to split a received vector clock into its components)
      Then display the received message and its vector clock.

  */

    // TODO Task 2.2:
  /*
     Check whether a message is eligible for causal delivery. If it is not eligible, store it in the buffer.
     Otherwise, deliver it alongside all buffered messages that can be now delivered (if any).

  */


  }

// TODO:
/*
    This method should print out the message (e.g. Client 1: Hello World!: [1, 0, 0]) and update
    the vector clock without ticking on receive. Then it should display the updated vector clock.
    Example: Initial clock [0,0,0], updated clock after message from Client 1: [1, 0, 0]
*/
  private void processMessage(Message message) {

  }
}
