# hex-cluster
Hex cluster backend service, to handle COVID-19 hex. Currently all hex are in memory.

## Technology

    Java
    Spring-boot
    Maven
    
## Build the application

    mvn clean package

## Run the appplication

    cd target & java -jar hex-cluster-0.0.1-SNAPSHOT.jar


# REST API

The REST API to the Hex-cluster application is described below.

## Add Node

`POST /cluster/node`

Request Body:
  
        {
           name : <<string>>,
	         existingNodeName: <<string>>,
	         border: <<number>>
        }

Response : 
         
         {
            x: <<coordinate x | number>>,
            y: <<coordinate y | number>>
            name: <<string>>
            colEvenOrOrdd: <<node on even or col row>>
         }
