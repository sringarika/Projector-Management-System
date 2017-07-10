# Projector-Management-System
Self project

Using Java with Jersey

  Assuming bookings cannot be extended to the next day. Bookings have to end on the same day at midnight.
  The webservice allows the following API requests: 
           POST: For path "http://localhost:8080/projector/request" the user can book projectors.
                   Time should be in this format-- "yyyy-MM-dd hh:mm:ss". The service suggests next slot 
                   availability to the user as well.
                   
           GET: For path "http://localhost:8080/projector/request" the user can see the list of all 
                   bookings for all the projectors.
                   
           DELETE: For path "http://localhost:8080/projector/request/{id}", the user can delete a slot for a given projector
                   by entering the projector ID and the Request object.
                   
           GET: For path "http://localhost:8080/projector/request/{id}", the user can see a list of available slots.
                    The service suggests slots starting from the current time of the system till Midnight for the current date.
 
