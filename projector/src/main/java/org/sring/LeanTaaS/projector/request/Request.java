package org.sring.LeanTaaS.projector.request;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Request {

    private int teamID;
    private String startTime;
    private String endTime;
    public Request() {
        
    }
    public Request(int teamID, String startTime, String endTime) {
        super();
        this.teamID = teamID;
        this.endTime = endTime;
        this.startTime = startTime;
    }
    /**
     * @return the startTime
     * @throws ParseException 
     */
    public String getStartTime() throws ParseException {
        //SimpleDateFormat df = new SimpleDateFormat("MMddyyyy hh:mm:ss");
        return startTime;
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
    public String getEndTime() throws ParseException {
        //SimpleDateFormat df = new SimpleDateFormat("MMddyyyy hh:mm:ss");
        return endTime;
    }
    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
