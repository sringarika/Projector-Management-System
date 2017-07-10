package org.sring.LeanTaaS.projector.service;

import java.text.DateFormat;
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
import org.sring.LeanTaaS.projector.request.TimeSlot;

public class ProjectorService {
    private Map<Integer, Projector> projectors = Database.getProjectors();
    public ProjectorService() {
        
    }
    /**
     * Method to return a lsit of all projectors.
     * @return List<Projectors>
     */
    public List<Projector> getAllProjectors() {
        return new ArrayList<Projector>(projectors.values());
    }
    /**
     * Method to return a list of all bookings for all projectors.
     */
    public List<Request> getAllBookings() {
        List<Request> list = new ArrayList<Request>();
        for (Projector proj: projectors.values()) {
            for (Request req: proj.getRequestQueue()) {
                list.add(req);
            }
        }
        return list;
    }
    /**
     * Method to return a list of all available slots for a given projector.
     */
    public List<TimeSlot> getAvailableSlots(int id) throws ParseException {
        List<Request> list = getProjectorBookings(id);
        List<TimeSlot> finalList = new ArrayList<TimeSlot>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        if (list.get(1).getStartTime().compareTo(currentDate) != 0) {
            TimeSlot first = new TimeSlot("Available slot starting from " + currentDate, list.get(1).getStartTime());
            finalList.add(first);
        }
        for (int i = 0; i < list.size() - 1; i++) {
            if(list.get(i).getEndTime().compareTo(list.get(i+1).getStartTime()) < 0) {
                TimeSlot node = new TimeSlot(list.get(i).getEndTime(), list.get(i+1).getStartTime());
                finalList.add(node);
            }
        }
        TimeSlot last = new TimeSlot(list.get(list.size()-1).getEndTime(), "Till 23:59:00");
        finalList.add(last);
        return finalList;
    }
    /**
     * Method to return all booked slots for a given projector sorted in ascending order.
     */
    @SuppressWarnings("unchecked")
    public List<Request> getProjectorBookings(int id) {
        List<Request> list = new ArrayList<Request>();
        for (Request req: projectors.get(id).getRequestQueue()) {
            list.add(req);
        }
        Collections.sort(list);
        return list;
    }
    /**
     * Method to book slot.
     */
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
    /**
     * Method to cancel a slot in a given projector.
     */
    public boolean cancelSlot(Request slot, int id) {
        return projectors.get(id).removeSlot(slot);
    }
    /**
     * Method to check for next available slot in all projectors.
     */
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
