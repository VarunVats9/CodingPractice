* Date : 5 Dec, 2018
* Time : 3:24 PM
* @author : Varun Vats (varunvats32@gmail.com)

# NO-SQL

## FETAURES

> Relational Database table:
![Sql](https://user-images.githubusercontent.com/2538815/49687007-e7219e00-fb22-11e8-95a0-86b68b2eccf3.png)

> Foremost, It scales horizontally and it has distributed architecture.

> It has no single point of failure, as data is replicated, and it can be distributed geographically.

> Each data (cell) has a key, and a value. So, any row, can have n keys and n corresponding values.

> In these, each row can have different keys, and different number of keys as well.

> Some data which can be a good fit, in NoSQL : Addresses, Personalization (what news section you like), Documents, Friends & Followers, Notes

> Think of non-transactional tasks like loading catalog data on display page or user's preferences.

> ![NoSql](https://user-images.githubusercontent.com/2538815/49687008-e7ba3480-fb22-11e8-8255-e9d47a29ddd1.png)

## CAP THEOREM

> (C)onsistency : That data is in consistent state in all the nodes of the cluster.

> (A)vailability : We can get to that data rather quickly.

> (P)artition Tolerance : All the data need not be at once place.

> SQL/Relational databases we can have CP, we know sql's are fast, but when we talk about web scale applications, we need storage and retrieval
at a more faster speed, maybe in milliseconds, or lesser. In that sense, availability is compromised.

> NoSQL databases we can have AP, here it is more about eventual consistency.

> Let's clarify with two examples, (1) Suppose you are managing an e-commerce website, and some stock has depleted, so you update the stock table in US,
but not in India. And in th meantime, somebody from India order something. Here, order gets processed even though there is no stock. Hence, in this scenario
maintaining consistency was important.
(2) On the same website, some new product catalog is rolled out. In this case, product info has to be shown quickly, so first in US and then India. Trickling
geographically, getting consistent eventually. __It is okay to sacrifice consistency for speed, in some cases__


## COMPROMISES

> Eventual Consistency : Mentioned above.

> Write Buffering : The data to be saved, is not saved on the disc immediately, but rather saved in buffer, and committed in a batch, to save the time.
There are ways to work around the case, wherein if system goes down, all the system states remain intact.

> Index : ONLY PRIMARY KEYS can be INDEXED, unlike the relational database.

> Queries : It must be written as a program (procedural program, and usually map reduce code), although few NoSQL have their own query language.

> Tools : Less tools for NoSql. Since decades, relational databases have been there, it has better tools, support available.


## TYPES OF NO-SQL

> Key-Value Stores : Schema free, every row can have any different keys. And it is the basis for other three (check below).
Example : Redis, DynamoDB, SimpleDB. ![table](https://user-images.githubusercontent.com/2538815/49687010-e7ba3480-fb22-11e8-87a5-d65ef26516f5.png)

> Document Stores : So, instead of rows we have documents, which are nothing but javascript objects (JSON). Everything is in a document form, and including
other documents. Also schema free.
Example : MongoDB, CouchDB. ![table](https://user-images.githubusercontent.com/2538815/49687011-e852cb00-fb22-11e8-9792-2160aa0f47c4.png)

> Wide Column Stores : Not schema free, but semi-schematic. You don't need to mention the schema, but just the group of columns, and not the actual
columns within those families. So, columns can vary from row to row, but the domain needs to be mentioned prior.
Example : Cassandra, ApacheHBase. ![table](https://user-images.githubusercontent.com/2538815/49687009-e7ba3480-fb22-11e8-8e1f-f67dcb5d0b09.png)

> Graph Databases : Relationship focused. Can be used in social graphs.
Example : Neo4j ![table](https://user-images.githubusercontent.com/2538815/49687006-e7219e00-fb22-11e8-8a46-39fb71f333bb.png)


## DOs DONTs

> Performance : Certain types of queries on NoSQL can be unacceptably slow. Suppose you have business application loads, and would need joins and other complex
queries, in that case relational DBs are far more robust and reliable. In case you have simple information to store and fetch and concurrently BUT you have to
do enormous amount of such things (really heavy TRAFFIC) then NoSQL is the best.

> Appropriateness : Data such as configuration data, environment data, settings, Log files and events data. All these can work with NoSQL better. Anything that
is transactional especially financial, should be done with SQL. Like Stock trades, accounting debit, credit transactions.


## SUMMARY

> Line of Business -> __Relational__

> Large, Public(consumer facing) site -> __NoSQL__

> Complex data structures -> __Relational__

> Big Data -> __NoSQL__

> Transactional -> __Relational__

> Content Management -> __NoSQL__

> Enterprise -> __Relational__

> Consumer Web -> __NoSQL__
