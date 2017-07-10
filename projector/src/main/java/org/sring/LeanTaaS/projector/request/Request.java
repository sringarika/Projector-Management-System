package org.sring.LeanTaaS.projector.request;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.sring.LeanTaaS.projector.model.Projector;
@XmlRootElement
public class Request implements Comparable {

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
    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
        Date time1 = df.parse(((Request)o).getStartTime());
        Date time2 = df.parse(this.startTime);
        return (int) (time2.getTime() - time1.getTime());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return 0;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Request)) {
            return false;
        }
        Request object = (Request) o;
        if (this.endTime.equals(object.endTime) && this.startTime.equals(object.startTime) && this.teamID == object.teamID) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
