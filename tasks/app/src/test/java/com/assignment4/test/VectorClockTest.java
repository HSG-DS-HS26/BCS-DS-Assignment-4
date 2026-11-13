package com.assignment4.test;

import com.assignment4.tasks.task2.VectorClock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class VectorClockTest {
    VectorClock cl1 = new VectorClock(4);

    @Test
    void tick() {
        cl1.tick(0);
        cl1.tick(3);
        cl1.tick(3);
        Assertions.assertEquals(1, cl1.getCurrentTimestamp(0));
        Assertions.assertEquals(2, cl1.getCurrentTimestamp(3));
        Assertions.assertEquals("[1, 0, 0, 2]", cl1.showClock());
    }

    @Test
    void updateClock() {
        VectorClock cl2=new VectorClock(4);
        cl2.setVectorClock(2,1);
        cl1.updateClock(cl2);
        String now = cl1.showClock();
        Assertions.assertEquals("[0, 0, 1, 0]", now);
    }

    @Test
    void checkTickOnSendNoBuffering() {
        VectorClock senderClock = new VectorClock(4);
        senderClock.setVectorClock(0,1);
        senderClock.setVectorClock(1,2);

        VectorClock receiverClock = new VectorClock(4);
        receiverClock.setVectorClock(0,1);
        receiverClock.setVectorClock(1,1);

        Assertions.assertTrue(receiverClock.checkAcceptMessage(1, senderClock));
    }

    @Test
    void checkBuffering() {
        VectorClock senderClock = new VectorClock(4);
        senderClock.setVectorClock(0,1);
        senderClock.setVectorClock(1,2);

        VectorClock receiverClock = new VectorClock(4);
        receiverClock.setVectorClock(1,1);

        Assertions.assertFalse(receiverClock.checkAcceptMessage(1, senderClock));
    }

    @Test
    void acceptsFirstAndLastSenderUsingZeroBasedIndices() {
        VectorClock firstSender = new VectorClock(4);
        firstSender.setVectorClock(0, 1);
        VectorClock lastSender = new VectorClock(4);
        lastSender.setVectorClock(3, 1);

        Assertions.assertTrue(cl1.checkAcceptMessage(0, firstSender));
        Assertions.assertTrue(cl1.checkAcceptMessage(3, lastSender));
        cl1.updateClock(lastSender);
        Assertions.assertFalse(cl1.checkAcceptMessage(3, lastSender));
    }


}
