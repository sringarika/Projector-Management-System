package org.sring.LeanTaaS.projector.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import org.sring.LeanTaaS.projector.model.Projector;
import org.sring.LeanTaaS.projector.request.Request;
import org.sring.LeanTaaS.projector.service.ProjectorService;

//assuming bookings cannot be extended to the next day. bookings have to end on the same day.

@Path("/request")
public class ProjectorResource {

    ProjectorService service = new ProjectorService();
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String bookProjector(Request slot) throws ParseException {
        
        int result = service.bookSlot(slot);
        if (result == -1) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date startTime = df.parse(slot.getStartTime());
            Date endTime = df.parse(slot.getEndTime());
            long minutes = endTime.getTime() - startTime.getTime();
            return ("This time slot isnt available in any projector. Next availability is at " 
                    + service.checkNextAvailability(slot) + " for " + 
                    TimeUnit.MILLISECONDS.toMinutes(minutes - 6000) + " minutes");
        } else if (result == -2) {
            return "Please check your timings and book again.";
        }
        else {
            return ("slot booked in Projector " + result);
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
    public String removeBookingSlot(Request slot, @PathParam("id") int id) {
        boolean result = service.cancelSlot(slot, id);
        if(result) {
            return "Booking slot cancelled";
        } else return "Cannot process request";
    }
    
    
}
