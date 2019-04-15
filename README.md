# DMSDemo Application

Application uses in memory H2 DB and populate sample records at the startup 


# Requirement
> JDK 1.8

# How to run

> java -jar dms-1.0.0.jar

OR

> mvn spring-boot:run

# Sample cURL commands

> To list all the contacts

curl -X GET -i http://localhost:8080/dmsdemo/api/contacts/list

> To list all the contacts by given group

curl -X GET -i http://localhost:8080/dmsdemo/api/contacts/group/1

> Create a new Contact 

curl -X PUT \
  http://localhost:8080/dmsdemo/api/contact/7 \
  -H 'Content-Type: application/json' \  
  -H 'cache-control: no-cache' \
  -d '{
	"firstName": "David",
	"lastName": "Norman",
	"address": "123 Palm Ave, Ajax",
	"homeTel": "6471234567",
	"group":  {
		"id" : 1
	}
}'


> To list by firstname

curl -X GET \
  http://localhost:8080/dmsdemo/api/contacts/firstname/David \
  -H 'Content-Type: application/json' \  
  -H 'cache-control: no-cache'

> To list by lastname

curl -X GET \
  http://localhost:8080/dmsdemo/api/contacts/lastname/Norman \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache'

> To list by home phone number

curl -X GET \
  http://localhost:8080/dmsdemo/api/contacts/hometel/6471234567 \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache'

> Delete a Contact

curl -X DELETE -i http://localhost:8080/dmsdemo/api/contact/2

