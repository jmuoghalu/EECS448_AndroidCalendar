package eecs448_first_team.calender_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class CalendarEventDb extends SQLiteOpenHelper
{
    public class CalendarEvent
    {
        private Long ID;
        private String details;

        //These are here to allow the building of CalendarEvents from Cursors (database results) to be non-instantaneous
        public void setDetails(String newDetails) {details = newDetails;}
        public String getDetails() {return details;}

        public void setID(Long newId) {ID = newId;}
        public Long getID() {return ID;}
    }

	public static final int DATABASE_VER = 1; //to be incremented to prevent conflict issues
	private static final String DATABASE_NAME = "CalendarEventTable.db";
    private class CalendarEventTable implements BaseColumns
    {
        //IMPLIED Property _ID returns unique ID of object in database for direct reference
        static final String Table_Name = "Event";
        static final String Column_Details = "Details";
    }

    //when given to SQLite, this command creates the Table and defines its columns. TEXT is equivalent to String
    //and INTEGER is equivalent to Long (the class-number, not the lowercase number)
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + CalendarEventTable.Table_Name
            + " (" + CalendarEventTable._ID + " INTEGER PRIMARY KEY," + CalendarEventTable.Column_Details + " TEXT )";
    //when called, deletes the table outright, all data is lost
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + CalendarEventTable.Table_Name;

	public CalendarEventDb(Context context)
	{
		super(context,DATABASE_NAME,null,DATABASE_VER);
	}

    /**
	* Called automatically when a database is being accessed while database does not exist.
	* Creates a new empty database for user.
	* @param SQLiteDatabase the Phone-side database class to write using
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