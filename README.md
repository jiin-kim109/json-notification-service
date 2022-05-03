#json-data-storage
***  
A light-weighted JSON store service over HTTP API. With ```json-data-store```, You can read & write, or deduplicate a great chunk of JSON documents via an easy-to-use HTTP API. 

Key features on JSON:
* Read
* Write
* Deduplicate
* Cache Views

##Running the Project
***
Prerequisite
* Docker
* Docker compose
```
git clone https://github.com/jiin-kim109/json-data-store.git
docker-compose up
```
If you only want to build an image and run it in your custom enviroment,
```
docker build -t {package_name} .
```

##Documentation
***
API documentation is here.  
  
```json-data-store``` provides the following objects:
###Record
###Collection
###Views

##Local Development Setup
***
Prerequisites
* Maven
* JDK 11
* IntelliJ IDE
* Lombok