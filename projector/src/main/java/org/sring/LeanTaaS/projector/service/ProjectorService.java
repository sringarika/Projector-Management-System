package org.sring.LeanTaaS.projector.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.sring.LeanTaaS.projector.database.Database;
import org.sring.LeanTaaS.projector.model.Projector;
import org.sring.LeanTaaS.projector.model.Team;
import org.sring.LeanTaaS.projector.request.Request;

public class ProjectorService {
    private Map<Integer, Projector> projectors = Database.getProjectors();
    private Map<Integer, Team> teams = Database.getTeams();
    public ProjectorService() {
        
    }

    public List<Projector> getAllProjectors() {
        return new ArrayList<Projector>(projectors.values());
    }
    public List<Request> getAllBookings() {
        List<Request> list = new ArrayList<Request>();
        for (Projector proj: projectors.values()) {
            for (Request req: proj.getRequestQueue()) {
                list.add(req);
            }
        }
        return list;
    }
    public List<Request> getProjectorBookings(int id) {
        List<Request> list = new ArrayList<Request>();
        for (Request req: projectors.get(id).getRequestQueue()) {
            list.add(req);
        }
    return list;
    }
    public int bookSlot(Request slot) throws ParseException {
        if (slot == null) {
            return -1;
        }
        for(Projector proj: projectors.values()) {
            if(proj.checkAvailability(slot)) {
                proj.addSlot(slot);
                return proj.getId();
            }
        }
        return -1;
    }
    public boolean cancelSlot(Request slot, int id) {
        if (slot == null) {
            return false;
        }
        projectors.get(id).removeSlot(slot);
        return true;
    }
//    public Request checkNextAvailability() {
//        List<Request> allBookings = new ArrayList<Request>();
//        allBookings = getAllBookings();
//        Collections.sort(allBookings);
//    }
}
