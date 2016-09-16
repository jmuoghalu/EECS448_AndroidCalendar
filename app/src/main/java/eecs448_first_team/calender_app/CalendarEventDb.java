package eecs448_first_team.calender_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.Calendar;

public class CalendarEventDb extends SQLiteOpenHelper
{
    public class CalendarEvent
    {
        private Long ID;
        private String details;
        private Long daySinceAugustFirst_2016; //how we order events in table

        //These are here to allow the building of CalendarEvents from Cursors (database results) to be non-instantaneous
        public void setDetails(String newDetails) {details = newDetails;}
        public String getDetails() {return details;}

        public void setID(Long newId) {ID = newId;}
        public Long getID() {return ID;}

        public Long getDaySinceAugustFirst_2016() {return daySinceAugustFirst_2016;}
        public void setDaySinceAugustFirst_2016(Long newValue) {daySinceAugustFirst_2016 = newValue;}
    }

    private static SQLiteDatabase rdb; //readable database (for fetching values)
    private static SQLiteDatabase wdb; //writable database (for editing values)
	public static final int DATABASE_VER = 1; //to be incremented to prevent conflict issues
	private static final String DATABASE_NAME = "CalendarEventTable.db";
    private Calendar timeCalendar;
    private class CalendarEventTable implements BaseColumns
    {
        //IMPLIED Property _ID returns unique ID of object in database for direct reference
        static final String Table_Name = "Event";
        static final String Column_Details = "Details";
        static final String Column_Date = "Date";
    }

    //when given to SQLite, this command creates the Table and defines its columns. TEXT is equivalent to String
    //and INTEGER is equivalent to Long (the class-number, not the lowercase number)
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + CalendarEventTable.Table_Name
            + " (" + CalendarEventTable._ID + " INTEGER PRIMARY KEY," + CalendarEventTable.Column_Details + " TEXT," + CalendarEventTable.Column_Date + " INTEGER )";
    //when called, deletes the table outright, all data is lost
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + CalendarEventTable.Table_Name;

    //2nd parameter in cursor creation method db.query()
    //specifies what Columns from table to return
    private static final String[] PARAMETERS_TABLE_RETURN_COLUMNS = {CalendarEventTable._ID,CalendarEventTable.Column_Date,CalendarEventTable.Column_Details};
    //3rd parameter in cursor creation method db.query()
    //specifies what Columns from table to search using
    //IE searches by date (you will supply a date
    private static final String[] PARAMETERS_TABLE_SEARCH_COLUMNS_DATE = {CalendarEventTable.Column_Date};

	public CalendarEventDb(Context context)
	{
		super(context,DATABASE_NAME,null,DATABASE_VER);
	}

    /**
	* Called automatically when a database is being accessed while database does not exist.
     * Takes a SQLiteDatabase to represent the Phone-side database class to write using
	* Creates a new empty database for user.
	*/
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(SQL_CREATE_ENTRIES);
	}

    /**precondition: database was changed, we are upgrading to a new database
     * postcondition: new database created, all existing data lost
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
     * After (re)building readable database access, queries CalendarEventTable for a particular CalendarEvent
     * @param timeInMilliseconds the millisecond time representation of the given day. Fetchable by CalendarView.getDate() or through a Date object
     * @return a CalendarEvent object with a Day, an ID, and some Details, or Null if object does not exist or table corrupted
     */
    public CalendarEvent getCalendarEvent(Long timeInMilliseconds)
    {
        if(rdb == null)
            rdb = this.getReadableDatabase();
        String[] searchArgs = {timeInMilliseconds.toString()}; //used to search (you have to use strings)
        try
        {
            Cursor searchContainer = rdb.query(
                    CalendarEventTable.Table_Name, //query the CalendarEventTable
                    PARAMETERS_TABLE_RETURN_COLUMNS, //give me ID, Date, and Details columns that...
                    CalendarEventTable.Column_Date + "=?", //... that looking at the date column...
                    searchArgs, //... it matches the date I gave
                    null, //don't group rows
                    null, //don't filter by row groups
                    CalendarEventTable.Column_Date //sort by date first (assume ascending order)
            );
            //get the very first object returned (yes its possible to have multiple identical dates, but we prevent that)
            searchContainer.moveToFirst();
            //as specified in the RETURN_COLUMNS, 0th column is Date, 1st column is Details
            CalendarEvent returnCalendarEvent = new CalendarEvent();
            returnCalendarEvent.setDetails(searchContainer.getString(2));
            returnCalendarEvent.setID(searchContainer.getLong(0));
            returnCalendarEvent.setDaySinceAugustFirst_2016(searchContainer.getLong(1));
            if(returnCalendarEvent != null)
                return returnCalendarEvent;
            else //something wrong happened at this point, likely object or table corrupted
                return null;
        }
        catch(Exception e) //query failed horribly for some reason
        {
            return null; //either there's nothing there, or table is corrupted
        }

    }

    /**
     * After (re)building writeable database access, inserts a new row into CalendarEventTable
     * overwriting an existing row with the same Date and storing the provided Details
     * @param timeInMilliseconds the millisecond time representation of the given day. Fetchable by CalendarView.getDate() or through a Date object
     * @param detailsForDay the details to add for the given day, ie "I had to mine more gold, but my supply cap was very high."
     * @return True if Details were added successfully, False if Details adding failed (possibly table corrupted)
     */
    public boolean setCalendarDetails(Long timeInMilliseconds,String detailsForDay)
    {
        if(wdb == null)
            wdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CalendarEventTable.Column_Date,timeInMilliseconds);
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
     * precondition: user reverting to old database version
     * postcondition: new database created, all existing data lost
     * here we could implement a way to preserve new data when reverting to an old database version
     * @param db the database physically on the phone associated with this application
     * @param oldVersion the previous database version
     * @param newVersion the new version to update the database to
     */
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		//or possible reversion implementation
		onUpgrade(db,oldVersion,newVersion);
	}


}