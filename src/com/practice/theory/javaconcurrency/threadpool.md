* Date : 12 Dec, 2018
* Time : 5:24 PM
* @author : Varun Vats (varunvats32@gmail.com)

# JAVA CONCURRENCY

## THREADPOOL

> There are different types of threadpool which java executor service provides based on the type of task. 
FixedThreadPool, this has been discussed in executor service file. Here, you just provide the number of threads.

### CACHED THREADPOOL

> Unlike fixed threadpool, which has blocking queue, this has a special queue, called synchronous queue. This queue
has size limit of one. That is only one task can be queued anytime.

> Once the task gets queued, it searches for the thread which is idle, in case it couldn't find one, it creates one 
new thread. Since this can cause lot of threads to get created, it also kills those threads which are idle for more than
60 seconds. ![img](https://user-images.githubusercontent.com/2538815/49870160-9965a800-fe38-11e8-9d6c-0ecf1234f2ca.png)

> There is no parameter, in the constuctor. 
![img](https://user-images.githubusercontent.com/2538815/49870220-c023de80-fe38-11e8-8b32-55ebc31d0993.png)

### SCHEDULED THREADPOOL

> This is for certain tasks, which you want to trigger after certain delay. A special kind of queue is used called delay 
queue. In this queue, tasks are arranged based on the order of their turns. 
![img](https://user-images.githubusercontent.com/2538815/49925922-4e9f6b00-fee0-11e8-87d8-59a98c217e8e.png)

> Methods : schedule(tasks run after specified time), scheduleAtFixedRate(tasks run at every specified rate), 
scheduleWithFixedDelay(tasks run after every specified delay)

### SINGLE THREADED EXECUTOR

> It is used when all the tasks need to be run, sequentially.

> There is only one thread in the pool, it is just like FixedThreadPool. Here, if this single thread gets killed, 
maybe because of some exception, then like in any other threadpool, a new thread is created.
![img](https://user-images.githubusercontent.com/2538815/49926399-79d68a00-fee1-11e8-904d-5960d6115ceb.png)
