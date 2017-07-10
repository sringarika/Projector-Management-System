package org.sring.LeanTaaS.projector.resources;

import java.text.ParseException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;

import org.sring.LeanTaaS.projector.exception.ApplicationExceptionMapper;
import org.sring.LeanTaaS.projector.model.Projector;
import org.sring.LeanTaaS.projector.request.Request;
import org.sring.LeanTaaS.projector.request.TimeSlot;
import org.sring.LeanTaaS.projector.service.ProjectorService;

/**
 * Assuming bookings cannot be extended to the next day. Bookings have to end on the same day at midnight.
 * The webservice allows the following API requests: 
 *          POST: For path "http://localhost:8080/projector/request" the user can book projectors.
 *                  Time should be in this format-- "yyyy-MM-dd hh:mm:ss". The service suggests next slot 
 *                  availability to the user as well.
 *                  
 *          GET: For path "http://localhost:8080/projector/request" the user can see the list of all 
 *                  bookings for all the projectors.
 *                  
 *          DELETE: For path "http://localhost:8080/projector/request/{id}", the user can delete a slot for a given projector
 *                  by entering the projector ID and the Request object.
 *                  
 *          GET: For path "http://localhost:8080/projector/request/{id}", the user can see a list of available slots.
 *                  The service suggests slots starting from the current time of the system till Midnight for the current date.
 */
@Path("/request")
public class ProjectorResource extends ApplicationExceptionMapper {

    ProjectorService service = new ProjectorService();
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String requestProjector(Request slot) throws ParseException, WebApplicationException {
        
        int result = service.bookSlot(slot);
        if (result == -1) {
            return "This time slot isnt available in any projector. Next availability is at " + service.checkNextAvailability(slot);
        } else if (result == -2) {
            return "Please check your timings and book again.";
        }
        else {
            return ("Slot booked in Projector " + result);
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Request> getAllBookings() {
        return service.getAllBookings();
    }
    
    @Path("/{id}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String cancelRequest(Request slot, @PathParam("id") int id) {
        boolean result = service.cancelSlot(slot, id);
        if(result) {
            return "Booking slot cancelled";
        } else {
            return "Cannot process request. Slot doesnt exist.";
        }
    }
    
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TimeSlot> checkAvailabilityForProjector(@PathParam("id") int id) throws ParseException {
        return service.getAvailableSlots(id);
    }
}
