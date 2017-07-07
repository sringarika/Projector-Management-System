package org.sring.LeanTaaS.projector.request;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Request {

    private int teamID;
    private String startTime;
    private String endTime;
    public Request() {
        
    }
    public Request(int teamID, String startTime, String endTime) {
        this.teamID = teamID;
        this.endTime = endTime;
        this.startTime = startTime;
    }
    /**
     * @return the startTime
     * @throws ParseException 
     */
    public Date getStartTime() throws ParseException {
        DateFormat df = new SimpleDateFormat("hh:mm:ss a");
        return df.parse(startTime);
    }
    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    /**
     * @return the teamID
     */
    public int getTeamID() {
        return teamID;
    }
    /**
     * @param teamID the teamID to set
     */
    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }
    /**
     * @return the endTime
     * @throws ParseException 
     */
    public Date getEndTime() throws ParseException {
        DateFormat df = new SimpleDateFormat("hh:mm:ss a");
        return df.parse(endTime);
    }
    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
