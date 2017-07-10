package org.sring.LeanTaaS.projector.request;

public class TimeSlot {

     private String startTime;
     private String endTime;
     
     public TimeSlot() {
         
     }
     public TimeSlot(String startTime, String endTime) {
         this.startTime = startTime;
         this.endTime = endTime;
     }
    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }
    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }
    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
