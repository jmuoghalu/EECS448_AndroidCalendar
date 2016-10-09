package eecs448_first_team.calender_app;

/**
 * Created by mbauer on 10/9/16.
 */

public class CalendarEvent {
    private Long ID;
    private String details;
    private Long start;
    private Long end;

    //These are here to allow the building of CalendarEvents from Cursors (database results) to be non-instantaneous
    public void setDetails(String newDetails) {details = newDetails;}
    public String getDetails() {return details;}

    public void setID(Long newId) {ID = newId;}
    public Long getID() {return ID;}

    public void setStartDate(Long newStart) {start = newStart;}
    public Long getStartDate() {return start;}
    public void setEndDate(Long newEnd) {end = newEnd;}
    public Long getEndDate() {return end;}
}
