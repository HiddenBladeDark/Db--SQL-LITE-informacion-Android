import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import modelo.ContactoModel;

public class ModelDB extends SQLiteOpenHelper {

    private static final String NAME_TABLE ="CONTACTS" ;
    private static final String CAMP_ID ="_id" ;
    private static final String CAMP_NAME ="NOMBRE" ;
    private static final String CAMP_TELEFONO ="TELEFONO" ;
    private static final String CAMP_CORREO ="CORREO" ;
    private static final String CAMP_EDAD ="EDAD" ;


    public ModelDB(@Nullable Context context){

        super(context,"practica.db",null,1);
    }

        //funcion de crear
    @Override
    public void onCreate(SQLiteDatabase db) {
        //creacion tabla
        String sql="CREATE TABLE "+NAME_TABLE+"(" +
                CAMP_ID+"INTEGER PRIMARY KEY AUTOINCREMENT," +
                CAMP_NAME+"TEXT," +
                CAMP_TELEFONO+"TEXT," +
                CAMP_CORREO+"TEXT UNIQUE," +
                CAMP_EDAD+"INTEGER" +
                ")";
        db.execSQL(sql);

    }
        //funcion update
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+NAME_TABLE);
        onCreate(db);
    }

    public boolean RegisterContact(ContactoModel c){
        SQLiteDatabase base = getWritableDatabase();

        ContentValues v=new ContentValues();
        v.put(CAMP_NAME,c.getNombre());
        v.put(CAMP_CORREO,c.getCorreo());
        v.put(CAMP_TELEFONO,c.getTelefono());
        v.put(CAMP_EDAD,c.getEdad());

        return base.insert(NAME_TABLE,null,v)>0;
    }
}
