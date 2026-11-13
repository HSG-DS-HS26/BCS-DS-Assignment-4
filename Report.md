Assignment 4
------------

# Team Members

# GitHub link to your private repository

...

# Task 1

1. Record what you observe, for the server and each client, in your experiments for Task 1. Is that consistent with your expectations concerning Lamport clocks? Explain why.

> Ans:
> 

# Task 2

## Task 2.1: Timestamps based on Vector Clocks

1. Record what you observe, for the server and each client, in your experiments for Task 2.1. Is that consistent with your expectations concerning vector clocks (for causally ordered broadcast)? Explain why.

> Ans:



## Task 2.2: Causal Communication using Vector Clocks

1. Why did message D have to be buffered and can we now always guarantee that all clients
   display the same message order?

> Ans:

2. Note that the chat application uses UDP. What could be an issue with this design choice—and
   how would you fix it?

> Ans:


# Task 3

1. What is potential causality in Distributed Systems, and how can you model it? Why
   “potential causality” and not just “causality”?

> Ans:

2. If you look at your implementation of Task 2.1, can you think of one limitation of Vector Clocks? How would you overcome the limitation? To help you dive deeper into the
  topic, you can have a quick look at the following paper: https://vs.inf.ethz.ch/edu/HS2016/VS/exercises/A3/DVC_Landes.pdf.

> Ans:

3. Figure [`causal-broadcast`](images/causal-broadcast.png) shows an example of enforcing causal communication using Vector Clocks. You can find a detailed explanation of this example and the broadcast algorithm being used in the Distributed Systems book by van Steen and Tanenbaum (see Chapter 5.2.2, page 270). Would you achieve the same result if you used the same broadcast algorithm but replaced vector clocks with Lamport clocks? What would happen if you would increment the vector clocks using in causally-ordered broadcast for internal events as well? Provide one concrete example where this would create issues. Make sure to answer both sub-questions.

> Ans: