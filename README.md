# Logical Clocks





## Project Structure

```text
.
├── README.md                         # project overview and structure
├── TASKS.md                          # assignment instructions and task commands
├── SETUP.md                          # repository setup and submission workflow
├── Report.md                         # student experiments and written answers
├── viva.yml                          # viva learning objectives
├── images/                           # assignment examples
│   ├── Lamport.PNG                   # Task 1 example
│   ├── Vector.PNG                    # Task 2.1 example
│   ├── OOD.PNG                       # Task 2.2 expected history output
│   └── causal-broadcast.png          # Task 3 example
├── .gitignore                        # files excluded from version control
└── tasks/                            # student Gradle project
    ├── settings.gradle               # includes the app subproject
    ├── gradlew                       # macOS/Linux Gradle Wrapper
    ├── gradlew.bat                   # Windows Gradle Wrapper
    ├── gradle/wrapper/
    │   ├── gradle-wrapper.jar
    │   └── gradle-wrapper.properties
    └── app/
        ├── build.gradle              # dependencies, tests, and application entry point
        └── src/
            ├── main/java/com/assignment4/
            │   ├── common/
            │   │   ├── UdpServer.java               # supplied server
            │   │   └── MessageHandler.java          # supplied broadcasts and history
            │   └── tasks/
            │       ├── task1/
            │       │   ├── LamportTimestamp.java      # TODO: clock operations
            │       │   ├── LamportClockedProcess.java # TODO: join and send
            │       │   └── MessageListener.java       # TODO: receive and update clock
            │       └── task2/
            │           ├── VectorClock.java          # TODO: clock operations and delivery check
            │           ├── VectorClockedProcess.java # TODO: join and send
            │           ├── MessageListener.java      # TODO: receive, deliver, and buffer
            │           └── Message.java              # supplied message container
            └── test/java/com/assignment4/test/
                ├── LamportTimestampTest.java
                └── VectorClockTest.java
```

Follow [SETUP.md](SETUP.md) to set up your repository and [TASKS.md](TASKS.md)
for the instructions.

## AI Usage Disclaimer

LLMs were used in the preparation of this assignment template to support the development process, for example, for code scaffolding and layout. The learning objectives, structure, and content of the assignment, including all student-facing templates and materials, were designed by the TAs and the coordinating professor. Any LLM-generated code was reviewed and tested before release.

