# SYSTEM TIME


> Numbers everyone should know.

> 1 sec = 1 * 1000 msec

> 1 msec = 1000 * 1000 nsec

> Disk Seek : Amount of time it takes a hard
drive's read/write head to find the physical location of a piece of data on the disk.

### TABLE


Operation | Time
--- | ---
`L1 Cache Reference` | **0.5 nsec**
`L2 Cache Reference` | **5 nsec**
`com.practice.cses.introductoryproblems.Main Memory Reference` | **100 nsec**
|
`Mutex Lock/Unlock` | **25 nsec**
`System Call Overhead` | **400 nsec**
`Context switch between processes` | **30 msec**
|
`Read 1MB sequentially from memory` | **0.25 msec**
`Read 1MB sequentially from disk` | **20 msec**
`Disk seek` | **10 msec**
|
`Send 2K bytes over 1 Gbps network` | **0.02 msec**
`Roundtrip within same datacenter` | **0.5 msec**
`Send packet CA -> Netherlands -> CA` | **150 msec**

