package modelo;

public class ContactoModel {

    public static final String NAME_TABLE = "CONTACTS";
    public static final String CAMP_ID = "_id";
    public static final String CAMP_NAME = "NOMBRE";
    public static final String CAMP_TELEFONO = "TELEFONO";
    public static final String CAMP_CORREO = "CORREO";
    public static final String CAMP_EDAD = "EDAD";




    private String nombre;
    private int _id;
    private String correo;
    private String telefono;
    private int edad;

    public static final String CREATE_TABLE = NAME_TABLE + "(" +
    CAMP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
    CAMP_NAME + " TEXT," +
    CAMP_TELEFONO + " TEXT," +
    CAMP_CORREO + " TEXT," +
    CAMP_EDAD + " INTEGER" +
            ")";



    public ContactoModel() {
    }

    public ContactoModel(String nombre, int _id, String correo, String telefono, int edad) {
        this.nombre = nombre;
        this._id = _id;
        this.correo = correo;
        this.telefono = telefono;
        this.edad = edad;
    }

    public ContactoModel(int anInt, String string, String string1, String string2, String string3) {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}


