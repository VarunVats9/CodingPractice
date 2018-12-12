* Date : 12 Dec, 2018
* Time : 3:24 PM
* @author : Varun Vats (varunvats32@gmail.com)

# JAVA CONCURRENCY

## FORK JOIN POOL

## Feature 1

> It deals with tasks which produce its own sub-tasks a.k.a ForkJoin 
![img](https://user-images.githubusercontent.com/2538815/49852690-d9159b00-fe0a-11e8-9ba4-33055affc42c.png)

> It splits the tasks, and the subtasks can run parallely on different cores of CPU, (Forking) and once the taks are done
all the results are joined together (Joining)

## Feature 2

> It has per thread queueing ![img](https://user-images.githubusercontent.com/2538815/49852862-54774c80-fe0b-11e8-84f6-e601157a19c9.png) and 
a concept of work stealing ![img](https://user-images.githubusercontent.com/2538815/49852863-550fe300-fe0b-11e8-9a24-3c2fcbf01806.png). 

> Each thread takes the task from the common queue, and then break the task into sub tasks and add those into it's own dequeue, 
and once one subtask is done, the thread pulls another subtask from the front. In case all the subtasks are done, it can also 
steal the tasks from some other thread's dequeue, where it will steal from the end of the dequeue.

> It all looks similar to Executor Service, but has above features, which makes it different. 
![img](https://user-images.githubusercontent.com/2538815/49852861-54774c80-fe0b-11e8-9e75-b6b79b8f6788.png)

## Ways to submit tasks
1. Same as Executor Service: execute(Runnable), submit(Runnable), submit(Callable)
2. Own Specific Operations: execute(ForkJoinTask), invoke(ForkJoinTask), submit(ForkJoinTask) 

## Ideal ForkJoin Tasks
1. Do not perform blocking I/O
2. Do not share variables across tasks
3. Avoid synchronization

## Use cases
1. Sorting
2. Matrix Multiplication
3. Tree Traversal
4. Best move finder in a game
