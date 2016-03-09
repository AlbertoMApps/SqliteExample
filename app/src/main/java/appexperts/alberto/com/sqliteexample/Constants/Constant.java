package appexperts.alberto.com.sqliteexample.Constants;

import android.provider.BaseColumns;

/**
 * Created by alber on 03/03/2016.
 */
public interface Constant extends BaseColumns {

    //First table
    public static final String TABLE_NAME_ACCESS = "access";
    //columns in the db access
    public static final String COLUMNA_TIME = "tiempo";
    public static final String COLUMNA_TITLE = "titulo";

    //Second table
    public static final String TABLE_NAME_USER = "User";
    //columns in the db access
    public static final String COLUMNA_USERNAME = "userName";
    public static final String COLUMNA_USEREMAIL = "userEmail";
    public static final String TABLE_FK_ACCESS = "idUserAccess";

}
