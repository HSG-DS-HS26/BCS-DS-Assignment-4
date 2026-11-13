package com.assignment4.tasks.task2;

// This class can help you to encapsulate a message, VectorClock and senderID together
public class Message {

  private VectorClock timestamp;
  private String message;
  private int senderID;

  public Message(String message, VectorClock timestamp, int senderID) {
    this.message = message;
    this.timestamp = timestamp;
    this.senderID = senderID;
  }

  public String getMessage() {
    return message;
  }

  public VectorClock getClock() {
    return timestamp;
  }

  public int getSenderID() {
    return senderID;
  }
}
