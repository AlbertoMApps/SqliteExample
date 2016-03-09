package appexperts.alberto.com.sqliteexample;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import appexperts.alberto.com.sqliteexample.Constants.Constant;
import appexperts.alberto.com.sqliteexample.Model.TimeAccess;

//public class MainActivity extends Activity {
public class MainActivity extends Activity{

    private ListView listView;
    private AccessData accessData;
    private Cursor access;
    private static String [] FROM = {Constant._ID, Constant.COLUMNA_TIME, Constant.COLUMNA_TITLE};
    private static String [] FROM_USER = {Constant._ID, Constant.COLUMNA_USERNAME, Constant.COLUMNA_USEREMAIL};
   // private static int [] TO = {R.id.rowid, R.id.time, R.id.title };
    private static String ORDER_BY = Constant.COLUMNA_TIME + " DESC";
    //Data atributes... not sure why..
    private TimeAccess timeAccess;
    private final ArrayList<TimeAccess> timeAccess_list = new ArrayList<TimeAccess>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView =(ListView) findViewById(R.id.list);
        accessData = new AccessData(this);
        try {
            //dataAccess
            addAccess ( "Acceso1" );//Add as many access as you want to enter data to it...
            Cursor cursor = getCursorAccess();
            int accessID = getCursorAccessId(cursor);
            ArrayList<TimeAccess> listAccess = getAllAccess(cursor);
            showAllAccess(listAccess);
            //dataUser
            addUser("alb","alb@gmail.com",accessID);
            Cursor cursorUser = getCursorUser();
            getAllUsers(cursorUser);
        } finally {
            accessData.close();
        }

    }

    private void addAccess(String s) {
        SQLiteDatabase db = accessData.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.COLUMNA_TIME, System.currentTimeMillis());
        values.put(Constant.COLUMNA_TITLE, s);
        db.insert(Constant.TABLE_NAME_ACCESS, null, values);
    }

    private void addUser(String userName, String userEmail, int fk_access) {
        SQLiteDatabase db = accessData.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.COLUMNA_USERNAME, userName);
        values.put(Constant.COLUMNA_USEREMAIL, userEmail);
        values.put(Constant.TABLE_FK_ACCESS, fk_access);
        db.insert(Constant.TABLE_NAME_USER, null, values);
    }

    public Cursor getCursorAccess() {
        SQLiteDatabase db = accessData.getReadableDatabase();
        Cursor cursor = db.query(Constant.TABLE_NAME_ACCESS, FROM, Constant._ID, null, null, null, null, null);
        // return contact
        return cursor;
    }
    public int getCursorAccessId(Cursor cursor) {
        SQLiteDatabase db = accessData.getReadableDatabase();
        cursor = db.query(Constant.TABLE_NAME_ACCESS, FROM , null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToLast();
        }
        // return contact
        return cursor.getInt(0);
    }

    public Cursor getCursorUser() {
        SQLiteDatabase db = accessData.getReadableDatabase();
        Cursor cursor = db.query(Constant.TABLE_NAME_USER, FROM_USER, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        // return contact
        return cursor;
    }

    private ArrayList<TimeAccess> getAllAccess(Cursor cursor){
//        StringBuilder builder = new StringBuilder("Data saved: \n");
//        while( cursor.moveToNext() ){
//            long id = cursor.getLong(0);
//            long time = cursor.getLong(1);
//            String title = cursor.getString(2);
//            builder.append(id).append(" : ");
//            builder.append(id).append(" : ");
//            builder.append(id).append(" \n ");
//        }
//        TextView textView = (TextView) findViewById(R.id.text);
//        textView.setText(builder);
        //Linking data
        //startManagingCursor(cursor);
        if (cursor != null) {
            cursor.moveToFirst();

            do {
                // Toast.makeText(getApplicationContext(), cursor.getLong(0) + " : " + cursor.getLong(1) + " : " + cursor.getString(2), Toast.LENGTH_SHORT).show();
                timeAccess = new TimeAccess();
                timeAccess.setId((int) cursor.getLong(0));
                timeAccess.setTime((int) cursor.getLong(1));
                timeAccess.setTitle(cursor.getString(2));
                this.timeAccess_list.add(timeAccess);
            } while (cursor.moveToNext());
        }


//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.item, cursor, FROM, TO);
//        listView.setAdapter(adapter);
        cursor.close();
    return timeAccess_list;
    }

    private void getAllUsers(Cursor cursor) {

        if (cursor != null) {
            cursor.moveToFirst();
            do {
                Toast.makeText(getApplicationContext(), " Users : " + cursor.getInt(0) + " : " + cursor.getString(1) + " : " + cursor.getString(2), Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
            cursor.close();
        }
    }

    private void showAllAccess( ArrayList<TimeAccess> allTableAccesses){
      // ArrayList<TimeAccess> allTableAccesses =  getAllAccess(cursor);
        int i = 0;
        while (i<allTableAccesses.size()) {
            Toast.makeText(getApplicationContext(), allTableAccesses.get(i).getTitle() + " : " + allTableAccesses.get(i).getTime(), Toast.LENGTH_SHORT).show();
            i++;
        }

    }
}
