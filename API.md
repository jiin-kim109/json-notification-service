A temporary API documentation before hosting the regit version.

The base url of all APIs are very similar with the path in a file system. For example, in the following structure,  
```
Directory_A
        - Record_1
        - Record_2
        - Directory_B
                - Record_3
                - Directory_C  
                - ...  
```
The base url of accessing the contents of Directory B will be
```
http://{HOST_URL}/Directory_A/Directory_B/
```

## Directory ##
- **Create a directory**
```
POST http://{HOST_URL}/.../{PARENT_DIRECTORY}
```

- **List all records, subdirectories, and directory metadata in a directory**
```
GET http://{HOST_URL}/.../{DIRECTORY_NAME}
```
Params:
```
only=meta
```

- **Delete a directory**


## Record ##
- **List all records in a directory (exclude subdirectories)**
```
GET http://{HOST_URL}/.../{DIRECTORY_NAME}/records
```

- **Get a record with raw JSON payload and metadata**  
```
GET http://{HOST_URL}/.../{DIRECTORY_NAME}/records/{RECORD_ID}
```

- **Delete a record**