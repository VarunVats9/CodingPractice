# Count Min Sketch

> This algo is used for counting the frequency in a stream.

* https://www.youtube.com/watch?v=ibxXO-b14j4
* Other options could have been, hash table, and sampling.
* In hashtable, there is an issue of hash collisions, and the space require would also be more.
* In sampling at regular interval, the answer won't be accurate. As it is asymptotic.
* In this approach we use hash functions, the more the better.
* To compensate for the hash collisions, we take the minimum of all the hash values.
* Space require is also limited.