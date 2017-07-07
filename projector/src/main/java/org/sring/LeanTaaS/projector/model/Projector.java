package org.sring.LeanTaaS.projector.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Projector {

    private int id;
    private static List<Date> requestQueue = new ArrayList<Date>();
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
    public static List<Date> getRequestQueue() {
        return requestQueue;
    }
}
