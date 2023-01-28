# readers-writers

This is an implementation of well known concurrency problem called readers writers problem. 

### Program logic

Readers and Writers objects connected to the same resource should share the same ReadingRoom object.

#### ReadingRoom
Class ReadingRoom is a wrapper of the shared resource and allows Readers/Writers to read/write the resource synchronously. Only one Writer at the moment can write to the resource and Readers are not allowed to read the resource while Writer is writing. Up to 5 readers can read the resource at one time if and only if Writers are not writing to the resource in that time. Semaphore that manages the access to the reasource is fair. That means if writer is on the front of the queue of the waiting threads, then Readers that are waiting won't be let in to read the resource. This implementetion is starvation-free.

#### Writer

Class Writer extends Thread. Its sole purpose is to try to write to the resource in infinite loop.

#### Reader

Class Reader extends Thread. Its sole purpose is to try to read the resource in infinite loop. Writing/reading threads can acquire resource for time that varies between 1 and 3 seconds. 

### Console logs

Program logs to the console following information:
* Which reader wants to enter the reading room.
* Readers in queue to the reading room.
* Which reader entered the reading room.
* Readers in the reading room.
* What reader read.
* Which reader left the reading room.
* Which writer wants to enter the reading room.
* Writers in queue.
* Which writer entered the reading room.
* Which writer has written to the resource.
* Which writer left the reading room.
  
### Setup

To run this program run from the parent directory comand mvn package and after that java -jar main/target/main-1.0-SNAPSHOT.jar 

