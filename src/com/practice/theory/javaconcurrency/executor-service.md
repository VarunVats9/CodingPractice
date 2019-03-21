* Date : 12 Dec, 2018
* Time : 5:24 PM
* @author : Varun Vats (varunvats32@gmail.com)

# JAVA CONCURRENCY

## EXECUTOR SERVICE

## BASICS

> It runs multiple tasks asynchronously. ![img](https://user-images.githubusercontent.com/2538815/49866276-505c2680-fe2d-11e8-8d8c-be2ec2ab1c3d.png)
But here, creating so one thread for each task, is an expensive work. 

> So, rather we would want to have fixed number of threads
which can pull tasks one by one. 
![img](https://user-images.githubusercontent.com/2538815/49866530-0aec2900-fe2e-11e8-8efc-59dfa4120a96.png)

## IDEAL POOL SIZE 

### CASE 1 : CPU INTENSIVE

> CPU intensive operations can be calculating hash, or encryption.

> It depends on the number of CPUs your machine has, in case you got 4 CPUs but the thread pool you configure is 100,
then your OS would do time splitting for each thread, that is t0 will run for sometime then t1 ... t99. Hence, ideally
keep your thread pool size equal to the number of CPUs. ![img](https://user-images.githubusercontent.com/2538815/49866774-b6957900-fe2e-11e8-881b-93b68e63c2c2.png)

### CASE 2 : IO INTENSIVE

> IO intensive operations can be network (HTTP calls), database calls.

> In this case since most of the time threads would be in waiting state, it is better to have more threads in the thread
pool.![img](https://user-images.githubusercontent.com/2538815/49867067-62d75f80-fe2f-11e8-8121-69e9139a6fd4.png)

## Summary

![img](https://user-images.githubusercontent.com/2538815/49867216-c497c980-fe2f-11e8-9a51-57e12a4461b1.png)

