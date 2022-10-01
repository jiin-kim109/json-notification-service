[![CI](https://github.com/jiin-kim109/mongo-JSON-directory/actions/workflows/github_ci.yml/badge.svg)](https://github.com/jiin-kim109/mongo-JSON-directory/actions/workflows/github_ci.yml)
[![Publish](https://github.com/jiin-kim109/mongo-JSON-directory/actions/workflows/publish.yml/badge.svg)](https://github.com/jiin-kim109/mongo-JSON-directory/actions/workflows/publish.yml)
![license](https://img.shields.io/badge/license-MIT-blue)
![java17](https://img.shields.io/badge/java-17-brightgreen)
![mongodb](https://img.shields.io/badge/MongoDB-v6.0-brightgreen) 

### Mongo JSON Directory
```mongo-JSON-dir``` provides a simple, easy-to-use REST interface to manage JSON data in the MongoDB. 

## Records ##
A Record is a basic unit of information which only contains a chunk of JSON data and metadata.

## Directories ##
A Directory is a unit of the user access. Each directory contains a set of Records or another directory as its child.
<details>
<summary><b>Privacy</b></summary>
All folders are private and require ACCESS_KEY or MASTER_KEY to grant access. <br/>
Parent folder’s ACCESS_KEY also grants access to its child folders.
</details>
<details>
<summary><b>User Access</b></summary>
ID or Path based accessor <br/>
Direct Access → Base_URL/f/<Folder_ID> or Base_URL/r/<Record_ID>   <br/>
Path Access → Parent_Folder/Child_Folder/…/Record_Name
</details>
<details>
<summary><b>Metadata</b></summary>
record id prefix      <br/>
hide/show folder or record metadata
</details>
<details>
<summary><b>Users</b></summary>
Admin and user accounts are used for browsing Admin View UI. <br/>
Each User is attached to a folder. That means each User is granted an ACCESS_KEY but multiple Folders in the same level cannot be granted.
</details>
<details>
<summary><b>Admin View UI</b></summary>
Logging in with different accounts show different root Dir start
</details>

## Features ##
**APIs**
- Create Folder
- Create Child Folder
- Get list of Records and Folder metadata (e.g. ID, ACCESS_KEY)
- Update Folder settings
- Delete Folder
- Create JSON Record within Folder
- Get JSON Record via ID/Path
- Update JSON Record via ID/Path
- Delete JSON Record via ID/Path
- Create an User (=attach ID/PW to a folder) (MASTER_KEY)
- Get list of existing Users (=Folders’ ID/PW) (MASTER_KEY)
- Delete an User (=detach ID/PW from a folder) (MASTER_KEY)


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
