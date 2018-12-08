* Date : 8 Dec, 2018
* Time : 6:24 PM
* @author : Varun Vats (varunvats32@gmail.com)

# CAP Theorem

> It says if partition happens in node, that is if node gets down. Choose either between providing availability (but data will be inconsistent) or
choose between providing consistency (but data will not be available, server will say it is down).


## CA (Sacrifice Partition Tolerance)

> Partition can't happen, one case can be there is single database. No distributed architecture.

> You can't wish away partitions; network failure, software bugs, misconfiguration in firewall config, garbage collector not working properly, power failure.

> Hence, there is actually NO CA DATABASE. Yes single database has no partition, but it is of no value then. You CAN'T HAVE CA.


## CP (Sacrifice Availability)

> In CAP theorem the consistency which is talked about is said to be linear, which means at single point of time all the sync should happen simultaneously.

> Linearizability is extreme form of consistency, very costly to achieve; but it is possible to build systems without it. You can have great systems even without it.

> Since all systems (RDBMS) don't offer it. You actually DON'T NEED CP.


## AP (Sacrifice Consistency)

> "Every request received by non-failing node in the system, should result in a response."

> CAP availability says nothing about response time, moreover it talks only about non-failing node.

> If all nodes return empty response that is CAP-availability.

> High availability says system as a whole should be up almost all the time. (4 nines, mean 99.99%)

> So, we actually need high availability, You DON'T WANT AP.


## CONSISTENCY Vs AVAIALABILITY

> Trade offs between these two are real.

> One end of line is AP, and other end of line is CP, which are ideal scenarios. CAP theorem talks about these two points. But the spectrum in between these two lines are also useful.

> Most systems tend to be towards AP, or CP.

> Basically Eventual Consistency and High Availability is good enough.


## PACELC : CAP Theorem Extension

> ![PACELC](https://lh3.googleusercontent.com/WRlh1SIgRjq0WI-j6wQE3sp6dtjmk_RDpTOiIWhkDJPT4oyzLoeprlfAEtF6Gt4giW-M6fq48P0b5DD4fVX1ihCQLee7VPFjmc922aRV=s1416) :
"It states that in case of Partition(P), one has to choose between A and C, else (E) when there is no P, one has to choose between C and Latency (L) "

> It talks about system trade offs between consistency and availability when there is a partition, and system trade offs between latency (high latency is unavailability) and consistency when there is no partition.

> __PC/EC system Extreme Consistency__ - Always choose consistency, whether there is partition or no partition. __Rarely you want such cases.__

> __PA/EC system__ - Consistent when there is no partition, but loose consistency and choose availability when there is a partition.

> __PA/EL system Extreme Availability__ - Choose low latency and drop consistency, even there is no partition. And choose availability when there is partition and forego consistency.

> __PC/EL system__ - Choose Latency when there is no partition and forego consistency, but choose consistency and drop availability when there is a partition.

> __Lets see some real world databases, and see where they lie in this new matrix.__


## MYSQL

> ![MYSQL PACELC](https://lh5.googleusercontent.com/ixIhuTJsgA7qFQmsIPzywQ61AMuWfb2C3YdljSLes0LQzTyRO04WwNyp3JZCBhuNiSOCFHDcCDZHBMTxzLAMU1UREmSJ_CgLZUcgB2S-=s1458)

> __Master - Slave setup__ AP system, difficult to get consistency.

> In most cases, we use it as async replication, where it is a PA/EL system. Writes go only to master, and asynchronously they get replicated to slave. Without partition it is just choosing latency, rather than consistency.

> But when there is a partition, it still reads from slave and writes to master. It is actually choosing availability over consistency. It won't disallow writes, when there is a partition.

> You can set Mysql in semi-sync replication mode. In which master would update/replicate at least one slave synchronously, and if it couldn't connect to any slave, it falls back to async replication mode.
In this mode, initially when there was no partition it choose consistency, but once there is a partition it chooses availability.


## ZOOKEEPER

> ![ZOOKEEPER PACELC](https://lh6.googleusercontent.com/i6SbL3h9xDBsAEitn1Z9k9E8QDibEjP-qtwWM8KbsGm53a9hTE-l7PY3dzRsn9y7e2N-rFO-A1cFCQXK9vA6A_g4Hi-mD5PJ3DVt80z2=s1452)

> It is CP system.

> In this all writes go through one master, and reads can happen through any node.

> If you just set it at normal read mode, maybe it won't show the latest data that has been updated.

> It doesn't offer linearizable consistency, though it has sequential consistency that is updates happen in sequential order to the read nodes.

> But it has sync+read mode which makes it linearizabely consistent. In this mode, first it does a sync call and then read call.


## CASSANDRA

> ![CASSANDRA PACELC](https://lh5.googleusercontent.com/G0YldJT8B_jdE9thK5trriddVg6BWr8TrD9rRfCAjQy67uYgnovVq51tZ0jutu_4ggA8KtKGb72BTVDJqA04A-QapnDRdFfR43NZrL-8=s1428)

> In Cassandra if you want to make it in most available mode. You have to set three configs.
WRITE-CONSISTENCY = ANY
HINTED-HANDOFFS = ON
READ-CONSISTENCY = ONE
Here, first line says that any node can accept a write (And not just the replicas of the shard.),the second line helps in this, it makes sure it stores the write till the node where the data actually belongs to come up.
The node actually stores it locally and replicate later to that actual node, but it can happen that the data gets loose in case the actual node doesn't come up. Hence writes are CAP available, but it can loose consistency as mentioned above.
Third line tells that any one node having the data will respond to the read query. But keep in mind only the node which was supposed to have it can respond. In above hinted-handoff case, that node can't respond. Reads are highly available, but not CAP available.

> If you want to choose it in extreme consistent mode __PC/EC__. Then set below configs.
WRITE-CONSISTENCY = ALL
READ-CONSISTENCY = ALL
__Nobody uses Cassandra in this mode.__ Here, first line says, on every write each replica that should have that key (data) should be synched. And when you read all replicas must respond. This sacrifices availability like crazy. So effectively if any
replica node is down on which write was supposed to happen synchronously, hence that key update cannot happen anymore.
Hence, linearizable consistency is not possible in cssandra but you can achieve sequential consistency in it by not allowing any updates, just do append. That is add new columns but don't modify any cell.


## KAFKA

> ![KAFKA PACELC](https://lh3.googleusercontent.com/vIuMoV9Oe4IVGdksO-IrxYc8OtV1MmbLlFs5gDO9bMXRU1AiBtiYP0BuyeqnasYegLzLxbOwS0Phyy3ytvrN3WzI17GEm89rmIB9x-Ly=s1422)

> If you operate it in most available mode, set
REQUIRED.ACKS = 0
That is no acknowledgment would be given for any write. You can loose data as you care. But latency is very low here. There is no consistency requirements, and even if the entire cluster is down, it will keep on accepting writes.

> If you operate it in extreme consistent mode __PC/EC__, set
REQUIRED.ACKS = -1
MIN.ISR = <#Replicas>
That is all replicas should acknowledge the update, before the actual overall write is acknowledge. Second line is for number of replicas you need in Kafka, that would acknowledge.

> But if you set MIN.ISR = 1, that is only master needs to acknowledge. So get a system which PA/EC system
__Consistent when there is no partition, but loose consistency and choose availability when there is a partition.__


## PNUTS

> ![PNUTS PACELC](https://lh6.googleusercontent.com/St4o-VIfpEE2pzNQUCGz37CNT1-eF4TVJPmL5xqYDdYRqRjsKuMTQgexBgvfSGE6velSXeBsXa4RNBpoT-JNS_eKhvKh1PrZEeFzdJTk=s1426)

> In this datasource, only master accepts write and then it is replicated (asyn-replication) to all the slaves. Any master or slave can serve read request. But when there is a partition problem (master not reachable), it just stops writes  and chooses consistency.


## CONCLUSION

> "DataSource/Database are not these extremes, AP/CP. And nor are these extremes useful either. And these days the datasources let the developer choose the tradeoffs between consistency, availability and latency."

> Business decision to choose the tradeoffs between consistency, availability and latency.
Would you rather be down, or show wrong prices ?
Would you rather be slow, or show wrong prices ?
Example : Flipkart on flash sales go for consistency rather than availability (wrong number of products.)