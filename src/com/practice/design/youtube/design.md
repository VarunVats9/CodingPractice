# Youtube Architecture

###### CAPACITY ESTIMATION
* User Estimation
* Storage Estimation
* Cache Estimation
* Bandwidth Estimation

###### DATABASE DESIGN
* User
* Video
* VideoComment

###### API DESIGN
* UploadVideo(apiKey, title, description,
categoryID, language)
* DeleteVideo (apiKey, videoID)
* SearchVideo (apiKey, query, videoCountToReturn, pageNumber)

###### HIGH-LEVEL DESIGN PROCESS
* – Client – Web Server – Application Server – Database – Video Storage – Encode – Queue

###### OPTIMIZATION OF THE SYSTEM

###### SHARDING PROCESS

###### REPLICATION PROCESS

###### LOAD BALANCING

###### CACHING MECHANISM
* Memcache (for user and video database)
* CDN (for static media)

###### DESIGN CONSIDERATION


