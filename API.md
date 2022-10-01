A temporary API documentation before hosting the regit version.

Overall structure, 
```
Directory_A
        - Record_1
        - Record_2
        - Directory_B
                - Record_3
                - Directory_C  
                - ...  
```

## Directory ##
- **Create a directory**
```
POST http://{HOST_URL}/directories
```

- **List all records, subdirectories, and directory metadata in a directory**
```
GET http://{HOST_URL}/api/directories/{directory_id}
```
Params:
```
only=meta | only=records | only=dir
```

- **Delete a directory**


## Record ##

- **Get a record with raw JSON payload and metadata**  
```
GET http://{HOST_URL}/api/records/{record_id}
```
Params:
```
only=meta
```

- **Delete a record**
```
DELETE http://{HOST_URL}/api/records/{record_id}
```