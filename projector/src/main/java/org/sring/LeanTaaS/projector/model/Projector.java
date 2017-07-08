package org.sring.LeanTaaS.projector.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.sring.LeanTaaS.projector.request.Request;

public class Projector{

    private int id;
    private List<Request> requestQueue = new ArrayList<Request>();
    public Projector() {
        
    }
    
    public Projector(int id) {
        this.id = id;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    public List<Request> getRequestQueue() {
        return requestQueue;
    }
    public void addSlot(Request slot) {
        if (slot == null) {
            return;
        } else {
            requestQueue.add(slot);
        }
    }
    public void removeSlot(Request slot) {
        if (slot == null) {
            return;
        } else {
            requestQueue.remove(slot);
        }
    }
    public boolean checkAvailability(Request slot) throws ParseException {
        if (slot == null) {
            return false;
        } else {
            if (requestQueue.isEmpty()) {
                return true;
            } else {
                for (Request req: requestQueue) {
                    if (req.getStartTime().compareTo(slot.getStartTime()) >= 0 && req.getEndTime().compareTo(slot.getEndTime()) <= 0) {
                        return false;
                    } else if (req.getStartTime().compareTo(slot.getStartTime()) <= 0 && req.getEndTime().compareTo(slot.getEndTime()) >= 0) {
                        return false;
                    } else if (req.getStartTime().compareTo(slot.getStartTime()) >= 0 && req.getEndTime().compareTo(slot.getEndTime()) >= 0 && req.getStartTime().compareTo(slot.getEndTime()) <= 0) {
                        return false;
                    } else if (req.getStartTime().compareTo(slot.getStartTime()) <= 0 && req.getEndTime().compareTo(slot.getEndTime()) <= 0 && req.getEndTime().compareTo(slot.getEndTime()) >= 0) {
                        return false;
                    }
                }
                return true;
            }
        }
    }
    
}
