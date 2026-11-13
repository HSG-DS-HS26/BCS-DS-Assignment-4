
package com.assignment4.tasks.task1;


public class LamportTimestamp {
    private int timestamp;
    public LamportTimestamp(int time){

        timestamp = time;
    }
    public synchronized void tick(){
        // TODO: update the timestamp by implementing the tick
    }
    public synchronized int getCurrentTimestamp(){
        return timestamp;
    }

    public synchronized void updateClock(int receivedTimestamp){
        // TODO: update the function so that the clock is properly updated when a message is received with the provided timestamp.

    }

}
