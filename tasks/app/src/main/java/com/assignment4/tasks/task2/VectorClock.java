package com.assignment4.tasks.task2;

import java.util.Arrays;

public class VectorClock {

  private final int[] timestamps;

  public VectorClock(int numOfClients) {
    timestamps = new int[numOfClients];
    Arrays.fill(timestamps, 0);
  }


  public synchronized void setVectorClock(int processId, int time) {
    // TODO: Set the vector clock value for the processId
  }


  public synchronized void tick(int processId) {
    // TODO: Increment the vector clock value for the processId
  }

  //Note: the processId is between 0 and 3 for client identifiers between 1 and 4.
  public synchronized int getCurrentTimestamp(int processId) {
    return timestamps[processId];
  }

  public synchronized void updateClock(VectorClock other) {
    // TODO: Update the vector clock based on the values of another vector clock
  }

  public synchronized String showClock() {
    return Arrays.toString(timestamps);
  }

  // TODO:
  // For Task 2.2
  // Check if a message can be delivered or has to be buffered
  public synchronized boolean checkAcceptMessage(int senderId, VectorClock senderClock) {
    //TODO: update
    return false;

  }
}
