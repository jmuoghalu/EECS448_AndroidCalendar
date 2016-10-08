package eecs448_first_team.calender_app;

/**
 * author: Hans Brown
 * date: 9-18-16
 * purpose: a SQLite Database interfacing page: Instantiate an object to get access
 * to the database for reading and writing values. Primary database access methods are
 * getCalendarDetails(long) and setCalendarDetails(long,String)
 * Many thanks to Android (https://developer.android.com/index.html) for its help
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.Calendar;

public class CalendarEventDb extends SQLiteOpenHelper
{
    private class CalendarEvent
    {
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

    private SQLiteDatabase rdb; //readable database (for fetching values)
    private SQLiteDatabase wdb; //writable database (for editing values)
    private static final int DATABASE_VER = 2; //arbitrary database version representation: to be incremented to prevent conflict issues
	private static final String DATABASE_NAME = "CalendarEventTable.db";
    private Calendar timeCalendar;
    private class CalendarEventTable implements BaseColumns
    {
        //IMPLIED Property _ID returns unique ID of object in database for direct reference
        static final String Table_Name = "Event";
        static final String Column_Details = "Details";
        static final String Column_Start_Date = "StartDate";
        static final String Column_End_Date = "EndDate";
    }

    //when given to SQLite, this command creates the Table and defines its columns. TEXT is equivalent to String
    //and INTEGER is equivalent to Long (the class-number, not the lowercase number)
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + CalendarEventTable.Table_Name
            + " (" + CalendarEventTable._ID + " INTEGER PRIMARY KEY,"
            + CalendarEventTable.Column_Details + " TEXT,"
            + CalendarEventTable.Column_Start_Date + " INTEGER,"
            + CalendarEventTable.Column_End_Date + " INTEGER )";
    //when called, deletes the table outright, all data is lost
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + CalendarEventTable.Table_Name;

    //2nd parameter in cursor creation method db.query()
    //specifies what Columns from table to return
    private static final String[] PARAMETERS_TABLE_RETURN_COLUMNS = {CalendarEventTable._ID,CalendarEventTable.Column_Start_Date,CalendarEventTable.Column_End_Date,CalendarEventTable.Column_Details};

    /**
     * Constructs this interface to the app's SQLite database.
     * @param context the context (any Activity is a context) used by base class constructor
     */
	public CalendarEventDb(Context context)
	{
		super(context,DATABASE_NAME,null,DATABASE_VER);
	}
    /**
     * Called automatically when a database is being accessed while database does not exist.
     * Takes a SQLiteDatabase to represent the Phone-side database class to write with.
     * Creates a new empty database for user.
     * @param db The SQLiteDatabase to be constructed. Has no meaningful purpose since there is only ever one database
     */
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(SQL_CREATE_ENTRIES);
	}

    /**precondition: database was changed, we are upgrading to a new database.
     * postcondition: new database created, all existing data lost.
     * here we could do specific logic to transfer data from
     * old database to new database.
     * @param db the phone database used to hold table
     * @param oldVersion the old version the database exists at
     * @param newVersion the new version to update the database to
     */
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);
	}

    /**
     * precondition: database already instantiated.
     * postcondition: if readable database was not accessible, it is now accessible.
     * After (re)building readable database access, queries CalendarEventTable to see if
     * it contains details for supplied day of the week
     * @param timeInMilliseconds the millisecond time representation of the given day. Fetchable by CalendarView.getDate() or through a Date object
     * @return String containing Details message. Empty string if database query failed or no matching row
     */
    public String getCalendarDetails(Long timeInMilliseconds)
    {
        if(rdb == null)
            rdb = this.getReadableDatabase();
        String[] searchArgs = {timeInMilliseconds.toString()}; //used to search (you have to use strings)
        try
        {
            CalendarEvent matchingEvent = getCalendarEvent(timeInMilliseconds);
            if(matchingEvent != null)
            {
                return matchingEvent.getDetails();
            }
            else //otw, does not exist in table, or table corrupted
                return "";
        }
        catch(Exception e) //query failed horribly for some reason
        {
            return ""; //either there's nothing there, or table is corrupted
        }

    }

    /**
     * precondition: database already instantiated.
     * postcondition: if readable database was not accessible, it is now accessible.
     * After (re)building readable database access, queries CalendarEventTable for a particular CalendarEvent
     * @param id The event identifier
     * @return a CalendarEvent object with a Day, an ID, and some Details, or Null if object does not exist or table corrupted
     */
    public CalendarEvent getCalendarEvent(Long id)
    {
        if(rdb == null)
            rdb = this.getReadableDatabase();
        String[] searchArgs = {id.toString()}; //used to search (you have to use strings)
        try
        {
            Cursor searchContainer = rdb.query(
                    CalendarEventTable.Table_Name, //query the CalendarEventTable
                    PARAMETERS_TABLE_RETURN_COLUMNS, //give me ID, Date, and Details columns that...
                    CalendarEventTable._ID + "=?", //... that looking at the date column...
                    searchArgs, //... it matches the date I gave
                    null, //don't group rows
                    null, //don't filter by row groups
                    null //don't sort
            );
            //get the very first object returned (yes its possible to have multiple identical dates, but we prevent that)
            if(searchContainer.getCount() >= 1)
            {
                searchContainer.moveToFirst();
                //as specified in the RETURN_COLUMNS, 0th column is Date, 1st column is Details
                CalendarEvent returnCalendarEvent = new CalendarEvent();
                returnCalendarEvent.setDetails(searchContainer.getString(2));
                returnCalendarEvent.setID(searchContainer.getLong(0));
                returnCalendarEvent.setStartDate(searchContainer.getLong(1));
                returnCalendarEvent.setEndDate(searchContainer.getLong(1));
                if(returnCalendarEvent != null)
                    return returnCalendarEvent;
                else //something wrong happened at this point, likely object or table corrupted
                    return null;
            }
            else
            {
                return null;
            }
        }
        catch(Exception e) //query failed horribly for some reason
        {
            return null; //either there's nothing there, or table is corrupted
        }

    }

    /**
     * After (re)building writeable database access, inserts a new row into CalendarEventTable
     * overwriting an existing row with the same Date and storing the provided Details.
     * @param startCal The start time as a calendar
     * @param detailsForDay the details to add for the given day, ie "I had to mine more gold, but my supply cap was very high."
     * @return True if Details were added successfully, False if Details adding failed (possibly table corrupted)
     */
    public boolean setCalendarDetails(Calendar startCal,String detailsForDay)
    {
        if(wdb == null)
            wdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Long timeInMilliseconds = startCal.getTimeInMillis();
        values.put(CalendarEventTable.Column_Start_Date, timeInMilliseconds);

        Calendar endCal = (Calendar) startCal.clone();
        endCal.add(Calendar.DAY_OF_YEAR, 1);

        values.put(CalendarEventTable.Column_End_Date,endCal.getTimeInMillis());
        values.put(CalendarEventTable.Column_Details,detailsForDay);

        CalendarEvent matchingEvent = getCalendarEvent(timeInMilliseconds);
        if(matchingEvent != null)
        {
            values.put(CalendarEventTable._ID,matchingEvent.getID());
        }
        values.put(CalendarEventTable.Column_Details,detailsForDay);
        try
        {
            //replace tries to replace a conflicting row in table. If none exists, inserts instead.
            //In this case, if we found a corresponding CalendarEvent already, we grabbed its ID so there would
            //have to be a conflict
            wdb.replace(CalendarEventTable.Table_Name,null,values);
            return true;
        }
        catch (Exception e) //something failed, probably database corrupted, can't add details
        {
            return false;
        }
    }

    /**
     * Edit an event
     * @param id The identifier for th event
     * @param detailsForDay the details to add for the given day, ie "I had to mine more gold, but my supply cap was very high."
     * @return True if Details were added successfully, False if Details adding failed (possibly table corrupted)
     */
    public boolean editEvent(Long id, String detailsForDay)
    {
        if(wdb == null)
            wdb = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(CalendarEventTable.Column_Details, detailsForDay);

        try
        {
            wdb.update(CalendarEventTable.Table_Name, values, CalendarEventTable._ID + "=" + id, null);
            return true;
        }
        catch (Exception e) //something failed, probably database corrupted, can't add details
        {
            return false;
        }
    }

    /**
     * Adds a new event
     * @param start The start time as a calendar
     * @param end The start time as a calendar
     * @param detailsForDay the details to add for the given day, ie "I had to mine more gold, but my supply cap was very high."
     * @return True if Details were added successfully, False if Details adding failed (possibly table corrupted)
     */
    public boolean addEvent(Long start, Long end, String detailsForDay)
    {
        if(wdb == null)
            wdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CalendarEventTable.Column_Start_Date, start);
        values.put(CalendarEventTable.Column_End_Date,end);
        values.put(CalendarEventTable.Column_Details,detailsForDay);

        try
        {
            wdb.insert(CalendarEventTable.Table_Name,null,values);
            return true;
        }
        catch (Exception e) //something failed, probably database corrupted, can't add details
        {
            return false;
        }
    }

    /**
     * precondition: user reverting to old database version.
     * postcondition: new database created, all existing data lost.
     * here we could implement a way to preserve new data when reverting to an old database version.
     * @param db the database physically on the phone associated with this application
     * @param oldVersion the previous database version integer
     * @param newVersion the new version integer to update the database to
     */
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		//or possible reversion implementation
		onUpgrade(db,oldVersion,newVersion);
	}


}