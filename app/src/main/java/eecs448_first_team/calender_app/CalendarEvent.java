package eecs448_first_team.calender_app;

/**
 * Created by Matthew Bauer on 10/9/16.
 *
 * This holds information on an event returned from a database.
 *
 * Updated by "Team One" for Project 2.
 */

public class CalendarEvent {
    private Long ID;
    private String details;
    private Long start;
    private Long end;

    //These are here to allow the building of CalendarEvents from Cursors (database results) to be non-instantaneous

    /**
     * Set the details of the event.
     * @param newDetails A string object of text associated with an event.
     */
    public void setDetails(String newDetails) {details = newDetails;}

    /**
     * Get the details of the event.
     * @return Details associated with an event
     */
    public String getDetails() {return details;}

    // The identifier used by the database in storing events.
    // Each event has a unique ID.

    /**
     * Set the identifier of the event.
     * @param newId The new identifier to use for the event.
     */
    public void setID(Long newId) {ID = newId;}

    /**
     * Get the identifier of the event
     * @return A number that uniquely identifies this event.
     */
    public Long getID() {return ID;}

    // getters/setters used to specify info on events

    /**
     * Set the start date of the event as a "Long".
     * @param newStart The start date to use for the event.
     */
    public void setStartDate(Long newStart) {start = newStart;}

    /**
     * Get the start date for the event.
     * @return The start date of the event as a long.
     */
    public Long getStartDate() {return start;}

    /**
     * Set the end date for the event.
     * @param newEnd The end date to use for the event.
     */
    public void setEndDate(Long newEnd) {end = newEnd;}

    /**
     * Get the end date for the event
     * @return The end date for the event.
     */
    public Long getEndDate() {return end;}
}
