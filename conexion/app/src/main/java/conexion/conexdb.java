package conexion;

import android.app.Person;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.CountDownTimer;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import modelo.ContactoModel;

public class conexdb extends SQLiteOpenHelper {
/*
    private static final String NAME_TABLE = "CONTACTS";
    private static final String CAMP_ID = "_id";
    private static final String CAMP_NAME = "NOMBRE";
    private static final String CAMP_TELEFONO = "TELEFONO";
    private static final String CAMP_CORREO = "CORREO";
    private static final String CAMP_EDAD = "EDAD";
*/

    public conexdb(@Nullable Context context) {

        super(context, "practica.db", null, 1);
    }

    //funcion de crear
    @Override
    public void onCreate(SQLiteDatabase db) {

        //ejecutamos la cracion de la tabla que se encuentra en ContactoModel
        db.execSQL(ContactoModel.CREATE_TABLE);

    }

    //funcion update
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Actualizacion de las tablas
        db.execSQL("DROP TABLE IF EXISTS " + ContactoModel.NAME_TABLE);
        onCreate(db);
    }

    public boolean RegisterContact(ContactoModel c) {

        //instanciamos funciones de sqlitedatabase
        SQLiteDatabase base = getWritableDatabase();

        //instanciamos contenct values
        ContentValues v = new ContentValues();

        //Obtenemos los valores de los getter de los campos correspondientes a los valores de contenido
        v.put(ContactoModel.CAMP_NAME, c.getNombre());
        v.put(ContactoModel.CAMP_CORREO, c.getCorreo());
        v.put(ContactoModel.CAMP_TELEFONO, c.getTelefono());
        v.put(ContactoModel.CAMP_EDAD, c.getEdad());

        //retornamos el insert
        return base.insert(ContactoModel.NAME_TABLE, null, v) > 0;
    }


    public ContactoModel consultarPersona(ContactoModel cons){

        //Creamos un array que me contenga los campos correspondientes
        String[] columns = {ContactoModel.CAMP_ID,ContactoModel.CAMP_NAME, ContactoModel.CAMP_CORREO, ContactoModel.CAMP_EDAD,ContactoModel.CAMP_TELEFONO};
        //creamos variable que contenga el princiap consulta concatenandole = ? de lo que se consultara
        String selection = cons.CAMP_CORREO + " = ? ";
        //creamos un array que me contenga valor obtenido del getter del correo consulta
        String selectionArgs[] = {cons.getCorreo()};
        //null a valores de la consulta de sqllite
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;

        //instanciamos el sqldatabase
        SQLiteDatabase bd=getReadableDatabase();

        //creamos un cursor que es una coleccion de filas de cada dato consultado
        Cursor datos = bd.query(ContactoModel.NAME_TABLE,columns,selection,selectionArgs,groupBy,having,orderBy,limit);
       /* Cursor datos=bd.query(ContactoModel.NAME_TABLE,new String[]{ContactoModel.CAMP_ID,ContactoModel.CAMP_NAME,ContactoModel.CAMP_CORREO,ContactoModel.CAMP_EDAD,ContactoModel.CAMP_TELEFONO},
                ContactoModel.CAMP_CORREO + "=?", new String[]{String.valueOf(cons)},null,null,null
        ,null);*/

       //movetofirst pocisionamiento de las primera fila si tiene datos hara la respectiva consulta
        if(datos.moveToFirst()){

            //enviamos los setter obteniendolos como int/string a la ves obteniendo index columna
            cons.set_id(datos.getInt(datos.getColumnIndex(cons.CAMP_ID)));
            cons.setNombre(datos.getString(datos.getColumnIndex(cons.CAMP_NAME)));
            cons.setCorreo(datos.getString(datos.getColumnIndex(cons.CAMP_CORREO)));
            cons.setTelefono(datos.getString(datos.getColumnIndex(cons.CAMP_TELEFONO)));
            cons.setEdad(datos.getInt(datos.getColumnIndex(cons.CAMP_EDAD)));
            //retornamos cons que es el parametro
            return cons;
        }else{
            //en caso de que es sino nos retorne null
            return null;
        }
/*

        if(datos != null)
            datos.moveToFirst();

            ContactoModel contact = new ContactoModel(
                    datos.getInt(datos.getColumnIndex(ContactoModel.CAMP_ID)),
                    datos.getString(datos.getColumnIndex(ContactoModel.CAMP_NAME)),
                    datos.getString(datos.getColumnIndex(ContactoModel.CAMP_CORREO)),
                    datos.getString(datos.getColumnIndex(ContactoModel.CAMP_TELEFONO)),
                    datos.getString(datos.getColumnIndex(ContactoModel.CAMP_EDAD)));

                    datos.close();

                    return contact;
*/
    }
    public ContactoModel consultarpersonal(ContactoModel c){
        SQLiteDatabase base = getReadableDatabase();
        Cursor datos = base.rawQuery("SELECT * FROM "+ c.NAME_TABLE+" " +
                "WHERE "+c.CAMP_CORREO+"=?",new String[]{c.getCorreo()});
        if(datos.moveToFirst()){
            c.setNombre(datos.getString(datos.getColumnIndex(c.CAMP_NAME)));
            c.setCorreo(datos.getString(datos.getColumnIndex(c.CAMP_CORREO)));
            c.setTelefono(datos.getString(datos.getColumnIndex(c.CAMP_TELEFONO)));
            c.setEdad(datos.getInt(datos.getColumnIndex(c.CAMP_EDAD)));
            datos.close();
            return c;

        }else
            {
            return null;
        }
    }

    public ContactoModel Eliminar(ContactoModel elim)
    {
        SQLiteDatabase base = getReadableDatabase();
        String [] parametro ={elim.getCorreo()};
        base.delete(ContactoModel.NAME_TABLE,ContactoModel.CAMP_CORREO+"=?",parametro);
        base.close();
        return elim;
    }

    public ArrayList llenar_lv(){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM "+ ContactoModel.NAME_TABLE;
        Cursor registros = database.rawQuery(q,null);
        if(registros.moveToFirst())
        {
            do{
                lista.add(registros.getString(registros.getColumnIndex(ContactoModel.CAMP_ID)));
                lista.add(registros.getString(registros.getColumnIndex(ContactoModel.CAMP_NAME)));
                lista.add(registros.getString(registros.getColumnIndex(ContactoModel.CAMP_CORREO)));
                lista.add(registros.getString(registros.getColumnIndex(ContactoModel.CAMP_TELEFONO)));
                lista.add(registros.getString(registros.getColumnIndex(ContactoModel.CAMP_EDAD)));
            }while (registros.moveToNext());
        }

        return lista;
    }

}




