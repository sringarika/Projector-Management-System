package org.sring.LeanTaaS.projector.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
            return -2;
        }
        if(slot.getEndTime().compareTo(slot.getStartTime()) <=0 ) {
            return -2;
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
        return projectors.get(id).removeSlot(slot);
    }
    @SuppressWarnings("unchecked")
    public String checkNextAvailability(Request slot) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date startTime = df.parse(slot.getStartTime());
        Date endTime = df.parse(slot.getEndTime());
        long duration = TimeUnit.MILLISECONDS.toHours(endTime.getTime() - startTime.getTime());
        
        for(Projector proj : projectors.values()) {
            List<Request> list = proj.getRequestQueue();
            Collections.sort(list);
            for(int i=0; i<list.size()-1; i++) {
                Date endTime1 = df.parse(list.get(i).getEndTime());
                Date startTime2 = df.parse(list.get(i+1).getStartTime());
                long timeDifference = TimeUnit.MILLISECONDS.toHours(startTime2.getTime() - endTime1.getTime());
                if (timeDifference > duration) {
                    return list.get(i).getEndTime();
                }
            }
        }
        String one = projectors.get(1).getRequestQueue().get(projectors.get(1).getRequestQueue().size() - 1).getEndTime();
        String two = projectors.get(2).getRequestQueue().get(projectors.get(2).getRequestQueue().size() - 1).getEndTime();
        String three = projectors.get(3).getRequestQueue().get(projectors.get(3).getRequestQueue().size() - 1).getEndTime();
        String min;
        if (one.compareTo(two) > 0) {
            min = two;
        } else {
            min = one;
        }
        if (min.compareTo(three) > 0) {
            return three; 
        } else {
            return min;
        }
    }
}
