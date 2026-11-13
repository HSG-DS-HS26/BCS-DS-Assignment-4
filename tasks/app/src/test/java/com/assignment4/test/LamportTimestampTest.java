package com.assignment4.test;

import com.assignment4.tasks.task1.LamportTimestamp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
class LamportTimestampTest {
    LamportTimestamp cl1 = new LamportTimestamp(0);
    int timestamp;

    @Test
    void tick() {
        cl1.tick();
        timestamp = cl1.getCurrentTimestamp();
        Assertions.assertEquals(1, timestamp);
    }

    @Test
    void updateClockWithLargerReceivedTimestamp() {
        LamportTimestamp clock = new LamportTimestamp(0);
        clock.updateClock(2);
        clock.tick();
        Assertions.assertEquals(3, clock.getCurrentTimestamp(),
                "A larger received timestamp must be incremented by one");
    }

    @Test
    void updateClockWithSmallerReceivedTimestamp() {
        LamportTimestamp clock = new LamportTimestamp(5);
        clock.updateClock(2);
        clock.tick();
        Assertions.assertEquals(6, clock.getCurrentTimestamp(),
                "A smaller received timestamp must still advance the local clock");
    }

    @Test
    void updateClockWithEqualReceivedTimestamp() {
        LamportTimestamp clock = new LamportTimestamp(2);
        clock.updateClock(2);
        clock.tick();
        Assertions.assertEquals(3, clock.getCurrentTimestamp(),
                "An equal received timestamp must still advance the local clock");
    }
}
