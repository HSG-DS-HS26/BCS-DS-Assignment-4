# Assignment 4 - Logical Clocks

Course: Distributed Systems

**Deadline: November 26, 2026, at 23:59 CET.**

One partner creates the private template
repository and gives the other **Write** access. Record both students here.

| student name | GitHub handle |
|---|---|
| `TODO` | `TODO` |
| `TODO` | `TODO` |

>In this assignment, you will create a chat application that uses logical clocks for the causal ordering of messages. To this end, you will implement two of the most well-known algorithms in distributed systems for event ordering: Lamport’s logical clocks and vector clocks. Then, you will answer questions about logical clocks, perform the Viva, and record a video concerning your own scenario to develop.

**The assignment repository:**
**<https://github.com/HSG-DS-HS26/BCS-DS-Assignment-4>**

One group member should create a repo by clicking **Use this template** to create the group's private repository. Do not fork the repository or
clone it directly! The template features allow us to have your repository private instead of public. See [SETUP.md](SETUP.md) for a more detailed walkthrough.


| |                                                   |         |
|---|---------------------------------------------------|---------|
| **Before you start** | your own private copy of this repository          | **0**   |
| **Task 1** | A Chat Application using Lamport’s Logical Clocks | **2**   |
| **Task 2** | A Chat Application using Vector Clocks            | **4**   |
| **Task 3** | Causality and Logical Time Review                 | **1**   |
| **Task 4** | defend your submission                            | **1**   |
| **Task 5** | a recording                                       | **2**   |

---

## ★ Task 1: A Chat Application using Lamport’s Logical Clocks  (2 pt)

Lamport’s logical clock is a simple algorithm for obtaining a partial ordering of events in a distributed system. 
In this task, using the given project template, you will  implement a UDP-based chat application and you will use
Lamport’s logical clocks to set message timestamps.

To this end, you will use one server process and 4 client processes. Once you complete and build the project 
template, the server will simulate network delays by broadcasting the messages sent by clients with random delays. 
The delays are randomized for each receiver, meaning Client 1 can receive a message seconds after Client 2, for example. 
All messages should have timestamps attached to them. Because the timestamps are based on Lamport clocks, 
the chat clients can extract a partial ordering of messages.

Reminder on Lamport clocks:

- For every message sent or received by the client, increment its local clock by 1 (i.e., **tick**).

- When receiving a message, the client should update its clock to the maximum between the
 received clock value and its local value before **ticking** on receive (synchronization of the processes).

- We do not consider internal events in this application

**Example**: If the client’s current clock value is 2 and the timestamp of a received message has a
clock value of 6, the new clock value should be 6 + 1 = 7.

**Note**: [`Figure 1`](images/Lamport.PNG) is an example of how your implementation could look like. Your communication
between server and client may look slightly different due to the network delays. Focus on getting
the counters right.



### Build and run

You need to complete 3 classes:

- [LamportTimestamp](tasks/app/src/main/java/com/assignment4/tasks/task1/LamportTimestamp.java) is a wrapper class to capture the timestamp and the two essential operations (tick and update).

- [LamportClockedProcess](tasks/app/src/main/java/com/assignment4/tasks/task1/LamportClockedProcess.java) is a process bootstrapper that makes a connection to the ``UDPServer`` and listens for messages (using ``MessageListener``). It also checks if there is a string entered via the console, and if so, formulates a message and sends it to the ``UDPServer``.

- [MessageListener](tasks/app/src/main/java/com/assignment4/tasks/task1/MessageListener.java) implements a thread that listens for incoming messages and on receiving one updates the clock.

Use Java 21 and run these commands from `tasks/` (on Windows, use `.\gradlew.bat`
instead of `./gradlew`).

The following command is used to test your implementation of the Lamport timestamp:
```bash
./gradlew :app:test --tests com.assignment4.test.LamportTimestampTest
```

The following command is used to run the chat server:

```bash
./gradlew :app:run -PchooseMain="com.assignment4.common.UdpServer"  
```

Keep the server running. In a separate terminal in `tasks/` for each client, run:

```bash
./gradlew :app:run -PchooseMain=com.assignment4.tasks.task1.LamportClockedProcess
```

Use distinct client IDs from 1 to 4. 

### To do

- [ ] Implement the chat application using Lamport Clocks by completing [`LamportTimestamp.java`](tasks/app/src/main/java/com/assignment4/tasks/task1/LamportTimestamp.java), [`LamportClockedProcess.java`](tasks/app/src/main/java/com/assignment4/tasks/task1/LamportClockedProcess.java), and [`MessageListener.java`](tasks/app/src/main/java/com/assignment4/tasks/task1/MessageListener.java).
- [ ] Test your Lamport Timestamp implementation (using LamportTimestampTest, see command above).
- [ ] Run your Lamport Timestamp server and clients. Send messages nearly simultaneously.
- [ ] Record what you observe, for the server and each client, in the [`Report.md`](Report.md). Is that consistent with your expectations concerning Lamport clocks? Explain why.
### Grading

The chat application using Lamport clocks works as expected. The [`Report.md`](Report.md) is properly completed for Task 1.

---


## ★ Task 2: A Chat Application using Vector Clocks (4 pt)

Your second task is to implement a chat application  using vector clocks. This time, we will use vector clocks to enforce the causal ordering of messages.

### Task 2.1: Timestamps based on Vector Clocks (2 pt)

For this task, your chat application will implement the same basic functionality as in *Task 1* —  but using vector 
clocks to track causal dependencies among messages. This time, the server will broadcast received messages without 
delay (but we will still simulate network delays in a different way in the following sub-task).
The client should implement the algorithm for causally ordered broadcast discussed in the lecture: the client will thus
tick when sending a message but **NOT** when receiving a message. 
The client will also maintain a time vector (i.e., its vector clock): an array that keeps track of the clocks of all
clients involved in the chat. You can find the vector clock implementation in [`VectorClock.java`](tasks/app/src/main/java/com/assignment4/tasks/task2/VectorClock.java). Timestamps based on vector clocks capture the causal
dependencies of a given message.

See an example in [`Figure 2`](images/Vector.PNG) of what you should observe.

Vector clock API convention: `setVectorClock` takes a zero-based array index (0–3); `tick`, `getCurrentTimestamp`, 
and `checkAcceptMessage` take a sender ID (0-3; corresponding respectively to the client identifiers 1-4). Join messages do not tick the sending clock. 
Receiving a message must not tick the vector clock.

You should complete these classes:

- [VectorClock](tasks/app/src/main/java/com/assignment4/tasks/task2/VectorClock.java) captures the clock vector and essential operations
- [VectorClockedProcess](tasks/app/src/main/java/com/assignment4/tasks/task2/VectorClockedProcess.java) Similar to the Lamport process implementation, this is the process bootstrapper
- [MessageListener](tasks/app/src/main/java/com/assignment4/tasks/task2/MessageListener.java) listens, receives, and processes messages


### Build and run

Run these commands from  the [`tasks/`](./tasks) folder, using Java 21, (on Windows, replace `./gradlew`
with `gradlew.bat`). Stop any Task 1 server and clients before testing this variant.

The following command is used to test your implementation of the vector clocks:
```bash
./gradlew :app:test --tests com.assignment4.test.VectorClockTest.tick --tests com.assignment4.test.VectorClockTest.updateClock
```

The following command is used to run the chat server:

```bash
./gradlew :app:run -PchooseMain="com.assignment4.common.UdpServer"  
```

Keep the server running. In a separate terminal in `tasks/` for each client, run:

```bash
./gradlew :app:run -PchooseMain=com.assignment4.tasks.task2.VectorClockedProcess
```

Use distinct client IDs from 1 to 4. The tests above cover Task 2.1 clock operations; the delivery-check tests apply to Task 2.2.


### To do

- [ ] Implement the chat application using vector clocks by completing [`VectorClock.java`](tasks/app/src/main/java/com/assignment4/tasks/task2/VectorClock.java), [`VectorClockedProcess.java`](tasks/app/src/main/java/com/assignment4/tasks/task2/VectorClockedProcess.java), and [`MessageListener.java`](tasks/app/src/main/java/com/assignment4/tasks/task2/MessageListener.java).
- [ ] Test your vector timestamp implementation (using VectorClockTest, see command above).
- [ ] Run your server and clients. Send messages nearly simultaneously.
- [ ] Record what you observe, for the server and each client, in the [`Report.md`](Report.md). Is that consistent with your expectations concerning vector clocks (for causally ordered broadcast)? Explain why.

### Grading

The chat application using vector clocks works as expected.  The [`Report.md`](Report.md) is properly completed for Task 2.1.

---

### Task 2.2: Causal Communication using Vector Clocks (2 pt)

The chat application implemented so far based on vector clocks does not account for network delays, 
which could cause an out-of-order delivery of messages. 

In this task, you will expand your implementation to detect and buffer messages that violate causality based on their 
timestamps.

After you have completed your implementation for this task, test it by connecting a single client with the assigned ID 4
and sending the message "history" to the server. This requests a predefined,
out-of-order conversation between clients 1-3 that should then be put in order
by your implementation. Use a fresh server and a fresh client for this test.
The join and "history" request must not tick the client’s clock. All-zero vector
timestamps identify join notifications, which should be accepted without
advancing the receiving clock. You can see the expected output in [`Figure 3`](images/OOD.PNG).

**Reflection questions**: Please include answers to the following questions with your submission:

1) Why did message D have to be buffered and can we now always guarantee that all clients display the same message order?

2) Note that the chat application uses UDP. What could be an issue with this design choice—and how would you fix it?


### Implement and run

Implement the method *checkAcceptMessage* in [`VectorClock.java`](tasks/app/src/main/java/com/assignment4/tasks/task2/VectorClock.java) for detecting if a message may be accepted as the next in order, and extend your code in [`MessageListener.java`](tasks/app/src/main/java/com/assignment4/tasks/task2/MessageListener.java) to only display a message if it is accepted or otherwise store it in a buffer until it can be delivered. After each delivery, recheck buffered messages until no further message is eligible. Complete `processMessage` to display a delivered message and merge its clock without ticking.

After implementing causal delivery, run the full test suite from `tasks/`
using Java 21 (on Windows, replace `./gradlew` with `.\gradlew.bat`):

```bash
./gradlew :app:test
```


Start a fresh server in `tasks/`:

```bash
./gradlew :app:run -PchooseMain=com.assignment4.common.UdpServer
```

Keep the server running. In separate terminals, start one vector client per terminal (in the `tasks/` folder) using the following command:

```bash
./gradlew :app:run -PchooseMain=com.assignment4.tasks.task2.VectorClockedProcess --console=plain
```

Enter `4` at the ID prompt, then type `history` at the message prompt. Verify
that buffered messages are eventually displayed in causal order.

### Reflection Questions

1. Why did message D have to be buffered and can we now always guarantee that all clients
   display the same message order?

2. Note that the chat application uses UDP. What could be an issue with this design choice—and
   how would you fix it?


### To do

- [ ] Implement the method *checkAcceptMessage* (in [`VectorClock.java`](tasks/app/src/main/java/com/assignment4/tasks/task2/VectorClock.java)) for detecting if a message may be accepted as the next in order.
- [ ] Extend your code in [`MessageListener.java`](tasks/app/src/main/java/com/assignment4/tasks/task2/MessageListener.java) to only display a message if it is accepted or otherwise store it in a buffer until it can be delivered. After each delivery, recheck buffered messages until no further message is eligible. Complete `processMessage` (in [`MessageListener.java`](tasks/app/src/main/java/com/assignment4/tasks/task2/MessageListener.java)) to display a delivered message and merge its clock without ticking.
- [ ] Test the implementation using the predefined "history" conversation and verify that messages are delivered in causal order.
- [ ] Answer the reflection questions in [`Report.md`](Report.md).

### Grading

The code in [`VectorClock.java`](tasks/app/src/main/java/com/assignment4/tasks/task2/VectorClock.java) and [`MessageListener.java`](tasks/app/src/main/java/com/assignment4/tasks/task2/MessageListener.java) is completed to implement the required features. The questions in [`Report.md`](Report.md) are answered.

---

## ★ Task 3: Causality and Logical Time Review (1 pt)

In this task, you will answer theoretical questions about logical clocks and their relation to causality in [`Report.md`](Report.md).

The questions are:

1. (0.25pt) What is potential causality in Distributed Systems, and how can you model it? Why “potential causality” and not just “causality”?

2. (0.25pt) If you look at your implementation of Task 2.1, can you think of one limitation of Vector Clocks? How would you overcome the limitation? To help you dive deeper into the topic, you can read the following paper: https://vs.inf.ethz.ch/edu/HS2016/VS/exercises/A3/DVC_Landes.pdf.

3. (0.5pt) Figure [`causal-broadcast`](images/causal-broadcast.png) shows an example of enforcing causal communication using Vector Clocks. You can find a detailed explanation of this example and the broadcast algorithm being used in the Distributed Systems book by van Steen and Tanenbaum (see Chapter 5.2.2, page 270). Would you achieve the same result if you used the same broadcast algorithm but replaced vector clocks with Lamport clocks? What would happen if you would increment the vector clocks using in causally-ordered broadcast for internal events as well? Provide one concrete example where this would create issues. Make sure to answer both sub-questions in your [`Report.md`](Report.md).


### To do

- [ ] Answer the questions in [`Report.md`](Report.md).

### Grading

The questions in [`Report.md`](Report.md) have all been answered correctly and properly justified.

---

## ★ Task 4: defend your submission (1pt, and required)

> **EXERCISE**: the viva itself, against [`viva.yml`](viva.yml). Required. Your submission is not complete without it.

When all tasks from 1 to 3 are completed, **commit and push**.

Open [the viva site](https://wiser-sp4.interactions.ics.unisg.ch) and sign in with GitHub. Select your repository and the commit you pushed, then answer the viva's questions about your code.

When the viva ends, **it commits its record to your repository and pushes the commit**. The commit is on GitHub but not yet in your Codespace or on your laptop. **Pull** before making more changes, or your next push will be rejected.

If you withhold GitHub access from the automated AI viva, complete the viva directly with the TA on Teams. For this you need to inform the TA beforehand.

### To do

- [ ] Tasks 1-3 are committed and pushed
- [ ] Open [the viva site](https://wiser-sp4.interactions.ics.unisg.ch), select your repository and commit, and answer its questions
- [ ] **Pull** and check that `.viva/` is in your repository. In VS Code, choose **Source Control** -> **Sync Changes**, or run `git pull` in a terminal
- [ ] **Commit** and **push** these new files.
- [ ] **Alternatively**, attend the agreed direct Teams viva if you use an archive submission or withhold GitHub access from the automated AI viva.

### Grading

The actual content of the viva is not yet graded, but you need to have the viva logs in the repository to get the point here.

## ★ Task 5: a recording (2 pt)

You will prepare a recording presenting and demonstrating a scenario with 4 different processes, at least one event per process, and at least 5 messages sent from one process to another. An event can be an internal event, the sending of a message, or the reception of a message.

You will need to compute the Lamport and vector clocks for each event. Use the standard convention for vector clocks; not the one used for Task 2.

The scenario should satisfy certain conditions:

- Using only information provided from Lamport timestamps, indicate an ordered pair of distinct events (a,b) such that you can establish that $a \not\rightarrow b$. That is, you need to identify a pair of events that could break a causal dependency. Explain your reasoning. 

- The scenario should define events such that it can provide a clear example for why Lamport clocks are not strong logical clocks.

- The scenario need to define 2 events (a and b) in different processes P1 and P2, such that $a \rightarrow b$. There should be no message directly sent from P1 to P2. Explain why $a \rightarrow b$. using vector clocks and explain why this causality cannot be proven with Lamport clocks.

### To do 

- [ ] Define the scenario with the required conditions
- [ ] Compute the Lamport and vector clocks for each event in the scenario.
- [ ] Prepare the slides for your presentation as a file slides.pdf
- [ ] Record a video (presentation.mp4) where you explain the scenario, the Lamport and vector clocks for each event. Using these clocks, explain why the conditions are met.
- [ ] Choose one Task 5 media option: commit both slides.pdf and presentation.mp4 to your GitHub project, or upload both files to Canvas alongside the [`Report.md`](Report.md) file.

### Grading

The slides (file slides.pdf) and the video (file presentation.mp4) are provided alongside your submission. The slides present a scenario satisfying the required conditions and the video justifies it. The Lamport and vector clocks for each event are properly computed.

## AI agents

You are allowed to use AI agents to implement the **Java code** from your instructions.

Design the **point-carrying work yourself**: Design the algorithms to implement, experiments being performed and their results recorded. Answer the questions in [`Report.md`](Report.md), and design of the scenario in Task 5.
