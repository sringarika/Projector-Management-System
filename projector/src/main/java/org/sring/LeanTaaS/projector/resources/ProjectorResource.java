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
import org.sring.LeanTaaS.projector.model.Projector;
import org.sring.LeanTaaS.projector.request.Request;
import org.sring.LeanTaaS.projector.service.ProjectorService;

@Path("/request")
public class ProjectorResource {

    ProjectorService service = new ProjectorService();
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String bookProjector(Request slot) throws ParseException {
        int result = service.bookSlot(slot);
        if (result == -1) {
            return "Slot isnt available in any projector";
        } else {
            return ("slot booked in Projector " + result);
        }
        //return slot;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Request> getAllBookings() {
        return service.getAllBookings();
    }
    
}
