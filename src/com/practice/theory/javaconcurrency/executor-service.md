* Date : 12 Dec, 2018
* Time : 5:24 PM
* @author : Varun Vats (varunvats32@gmail.com)

# JAVA CONCURRENCY

## EXECUTOR SERVICE

## Feature 1

> It runs multiple tasks asynchronously.

> It splits the tasks, and the subtasks can run parallely on different cores of CPU, (Forking) and once the taks are done
all the results are joined together (Joining)

## Feature 2


> Each thread takes the task from the common queue, and then break the task into sub tasks and add those into it's own dequeue, 
and once one subtask is done, the thread pulls another subtask from the front. In case all the subtasks are done, it can also 
steal the tasks from some other thread's dequeue, where it will steal from the end of the dequeue.


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
