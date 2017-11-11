public class SQLiteHelper extends SQLiteOpenHelper {

public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //database values
    private static final String DATABASE_NAME      = "demoApp.db";
    private static final int DATABASE_VERSION      = 3;
    public static final String COLUMN_ID           = "_id";

    //team table
    public static final String TABLE_TEAM       = "team";
    public static final String COLUMN_MASCOT    = "mascot";
    public static final String COLUMN_CITY      = "city";
    public static final String COLUMN_COACH     = "coach";
    public static final String COLUMN_STADIUM   = "stadium";

    private static final String DATABASE_CREATE_TEAM = "create table "
            + TABLE_TEAM + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " string, "
            + COLUMN_MASCOT + " string, "
            + COLUMN_COACH + " string, "
            + COLUMN_STADIUM + " string, "
            + COLUMN_CITY + " string);";

    private static final String DATABASE_ALTER_TEAM_1 = "ALTER TABLE "
        + TABLE_TEAM + " ADD COLUMN " + COLUMN_COACH + " string;";

    private static final String DATABASE_ALTER_TEAM_2 = "ALTER TABLE "
        + TABLE_TEAM + " ADD COLUMN " + COLUMN_STADIUM + " string;";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_TEAM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
             db.execSQL(DATABASE_ALTER_TEAM_1);
        }
        if (oldVersion < 3) {
             db.execSQL(DATABASE_ALTER_TEAM_2);
        }
    }
}
