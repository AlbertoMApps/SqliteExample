package appexperts.alberto.com.sqliteexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import appexperts.alberto.com.sqliteexample.Constants.Constant;

/**
 * Created by alber on 03/03/2016.
 */
public class AccessData extends SQLiteOpenHelper {

    private static final String DATABASENAME = "access.db";
    private static final int DATABASE_VERSION = 1;

    public AccessData(Context context) {
        super(context, DATABASENAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Constant.TABLE_NAME_ACCESS
                + "("+ Constant._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"
                + Constant.COLUMNA_TIME + " INTEGER,"
                + Constant.COLUMNA_TITLE + " TEXT NOT NULL);" );
        db.execSQL("CREATE TABLE " + Constant.TABLE_NAME_USER
                + "(" + Constant._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Constant.COLUMNA_USERNAME + " TEXT NOT NULL,"
                + Constant.COLUMNA_USEREMAIL + " TEXT NOT NULL,"
                + Constant.TABLE_FK_ACCESS + " INTEGER,"
                + "FOREIGN KEY(" + Constant.TABLE_FK_ACCESS + ") REFERENCES " + Constant.TABLE_NAME_ACCESS + "(" + Constant._ID + ") );");
        //FOREIGN KEY(trackartist) REFERENCES artist(artistid)
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constant.TABLE_NAME_ACCESS);
        db.execSQL("DROP TABLE IF EXISTS " + Constant.TABLE_NAME_USER);
        onCreate(db);
    }
}
