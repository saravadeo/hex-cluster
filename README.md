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

## Add node in cluster 
(*Note- Default cluster existing node is "ax" )
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

## Get neighbours

`GET /cluster/node`

Request Params
	
	name: <<string | send name of node for which we need to fetch neighbours>>

Response:

	[
	   {
	      "x":0,
             "y":-1,
             "name":"az",
             "colEvenOrOrdd":0
	   },
 	   ...
	]

## Delete node from cluster

`DELETE /cluster/node`

Request Params
	
	name: <<string | send name of node which need to delete>>
	
Response:
	
	true
	
	or 
Exception: 
	
	status: 400
	{
		title: <<string>>,
		message: <<string | error message>>
	}
