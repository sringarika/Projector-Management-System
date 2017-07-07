package org.sring.LeanTaaS.projector.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import org.sring.LeanTaaS.projector.model.Projector;
import org.sring.LeanTaaS.projector.service.ProjectorService;

@Path("/requestprojector")
public class ProjectorResource {

    ProjectorService service = new ProjectorService();
    
        
    public List<Projector> getAllProjector() {
        return service.getAllProjectors();
    }
    
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Projector getProjector(@PathParam("id") int id) {
        return service.getProjector(id);
    }
}
