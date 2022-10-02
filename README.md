[![CI](https://github.com/jiin-kim109/mongo-JSON-directory/actions/workflows/github_ci.yml/badge.svg)](https://github.com/jiin-kim109/mongo-JSON-directory/actions/workflows/github_ci.yml)
[![Publish](https://github.com/jiin-kim109/mongo-JSON-directory/actions/workflows/publish.yml/badge.svg)](https://github.com/jiin-kim109/mongo-JSON-directory/actions/workflows/publish.yml)
![license](https://img.shields.io/badge/License-MIT-blue)
![java17](https://img.shields.io/badge/Java-17-brightgreen)
![mongodb](https://img.shields.io/badge/MongoDB-v6.0-brightgreen) 

### Mongo JSON Directory
```mongo-JSON-dir``` sits on the top of MongoDB, provides a REST API to securely and effectively manage JSON data. 

<img src="https://i.postimg.cc/tJDKLbYr/json.png" />

**Access Control**
Each record in a directory has ACCESS_KEY (=JWT token) to grant access for viewing.

Each directory has either MASTER_KEY and ACCESS_KEY for granting access. Directories with MASTER_KEY requires users to authenticate via ID/PW to grant the access token. The MASTER_KEY allows users to create/view/modify directory settings and its belongings. This is useful when a user wants to grant permissions to other users only for a subset of a directory.

The ACCESS_KEYs can be set to a short live and be automatically renewed for security purposes. The MASTER_KEYs are permanent so beware of a security risk. 

ROOT_KEY is only for a systemic use and can be set via environment variables. Do not use ROOT_KEY for general use cases.

There are two types of users.

Users with ID/PW for a MASTER_KEY is considered as a “Owner” of a directory. If a sub-directory is set to have a MASTER_KEY, then users with the credentials are considered as “Sub-owners”

Users with a ACCESS_KEY are considered as a “Visitor” and only allowed to perform restricted actions on records/directories.

**API**
- Record API
- Directory API


## Features ##
**APIs**
- Create Directory
- Create Sub-directory
- Get list of Records and directories
- Get metadata for a reocrd
- Get metadata for a directory
- Update Directory settings
- Create a record with JSON payload
- Delete a directory
- Delete a record

**APIs**
- Create Directory
- Create Sub-directory
- Get list of Records and directories
- Get metadata for a reocrd
- Get metadata for a directory
- Update Directory settings
- Create a record with JSON payload
- Delete a directory
- Delete a record

## Installation
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
API documentation is here.  
  
```json-data-store``` provides the following objects:
###Record
###Collection
###Views

##Local Development Setup
Prerequisites
* Maven
* JDK 11
* IntelliJ IDE
* Lombok
