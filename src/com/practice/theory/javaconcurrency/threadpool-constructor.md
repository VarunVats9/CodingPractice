* Date : 13 Dec, 2018
* Time : 2:27 PM
* @author : Varun Vats (varunvats32@gmail.com)

# JAVA CONCURRENCY

## THREADPOOL CONSTRUCTORS

> All threadpools internally calls a constructor called, ThreadPoolExecutor which has many argumnets, based on which
different threadpools are created. 
![img](https://user-images.githubusercontent.com/2538815/49926785-637cfe00-fee2-11e8-8c8a-8a46f6d55719.png)

> All arguments. ![img](https://user-images.githubusercontent.com/2538815/49926823-7e4f7280-fee2-11e8-927e-1a9acdad12fe.png)

> ThreadPool size never goes below core pool size (unless allowCoreThreadTimeOut is set to true), and when keepAliveTime is 0, 
it means not applicable.
![img](https://user-images.githubusercontent.com/2538815/49926894-aa6af380-fee2-11e8-8fe5-a43db7d1bce1.png)

### DIFFERENT ARGUMENTS

> Arguments, for every threadpool. 
![img](https://user-images.githubusercontent.com/2538815/49926997-f3bb4300-fee2-11e8-85f6-34042d738eb3.png)

### DIFFERENT QUEUES

> Queue used in threadpools.
![img](https://user-images.githubusercontent.com/2538815/49927512-11d57300-fee4-11e8-883c-8ebc30678491.png)

### REJECTION HANDLER POLICIES

> When you use, array blocking queue, there is a certain size with it. 
![img](https://user-images.githubusercontent.com/2538815/49928186-a12f5600-fee5-11e8-84aa-c3b0e42ec2fe.png)
If tasks have reached that value, it will try to
create new threads, but what if all the threads have already reached the maximum size. Then the threadpool falls back to 
Rejection Handler Policies, these are certain ways to handle rejections.
![img](https://user-images.githubusercontent.com/2538815/49928234-c1f7ab80-fee5-11e8-821c-e05454069d21.png)

> Examples
![img](https://user-images.githubusercontent.com/2538815/49928440-2450ac00-fee6-11e8-8fd7-0507072b91d2.png)
![img](https://user-images.githubusercontent.com/2538815/49928442-24e94280-fee6-11e8-9662-986e7dc37e01.png)

### LIFECYCLE METHODS

> Method : shutdown(will initiate shutdown, and will not accept any more tasks, but complete all the currently running
tasks and the ones currently in blocking queue), shutdownNow(will initate shutdown, and complete the running tasks, 
remaining ones from blocking queues are returned).
![img](https://user-images.githubusercontent.com/2538815/49928892-2b2bee80-fee7-11e8-9f09-c4daeedc147f.png)
