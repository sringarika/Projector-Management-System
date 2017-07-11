# Projector-Management-System
Self project

Using Java with Jersey framework.

The RESTful Web Service doesnt need any dependency. The project can be run on any IDE and run on the local server (Tomcat for Eclipse). The API calls can be checked using Postman.

Assumptions: Bookings cannot be extended to the next day. Bookings have to end on the same day at midnight.
The web service allows the following API requests: 
  
           POST: For path "http://localhost:8080/projector/request" the user can book projectors.
                   Time should be in this format-- "yyyy-MM-dd hh:mm:ss". The service suggests next slot 
                   availability to the user as well.
                   
           GET: For path "http://localhost:8080/projector/request" the user can see the list of all 
                   bookings for all the projectors.
                   
           DELETE: For path "http://localhost:8080/projector/request/{id}", the user can delete a slot for a given projector
                   by entering the projector ID and the Request object.
                   
           GET: For path "http://localhost:8080/projector/request/{id}", the user can see a list of available slots.
                    The service suggests slots starting from the current time of the system till Midnight for the current date.

An example of a valid request object- 
{
    "teamID" : 3,
    "startTime": "2017-08-12 10:00:00",
    "endTime" : "2017-08-12 11:00:00"
}
