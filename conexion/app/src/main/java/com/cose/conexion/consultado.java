package com.cose.conexion;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import modelo.ContactoModel;
import conexion.conexdb;

public class consultado extends AppCompatActivity {
    //Declaramos variables
    EditText gettextid, gettextnom, gettextcorr, gettexttele, gettextedad;
    ListView lv;
    ArrayList<String> listase;
    ArrayAdapter adaptadors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultado);
        //asignamos valores
        gettextid = findViewById(R.id.txtgetid);
        gettextnom = findViewById(R.id.txtgetnom);
        gettextcorr = findViewById(R.id.txtgetcorr);
        gettextedad = findViewById(R.id.txtgetedass);
        gettexttele = findViewById(R.id.txtgettele);
        lv = findViewById(R.id.listarallregis);


        //Traigo mis intenciones de la interfaz correspondientes
        Bundle datoss = getIntent().getExtras();

        //asignamos valores obtenidos de la ventana principal a sus respectivas variables declaradas en este bloque
        //inicio bloque
        int inds = datoss.getInt("id");
        String nombre = datoss.getString("nombre").toString();
        String correos = datoss.getString("correo").toString();
        String telefono = datoss.getString("telefono").toString();
        int edad = datoss.getInt("edad");
        //fin bloque

        //pasamos los valores de las variables a las variables de las propiedades xml
        gettextid.setText("" + inds);
        gettextnom.setText(nombre);
        gettextcorr.setText(correos);
        gettexttele.setText(telefono);
        gettextedad.setText("" + edad);


    }


    public void Eliminares(View view) {
        conexdb conex = new conexdb(getApplicationContext());

        ContactoModel con = new ContactoModel();

        con.setCorreo(gettextcorr.getText().toString());

        conex.Eliminar(con);
        Toast.makeText(getApplicationContext(), "ELIMINADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();


    }
    public void consultarallregistger(View view){
        conexdb conex = new conexdb(getApplicationContext());
        listase = conex.llenar_lv();
        adaptadors = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listase);
        lv.setAdapter(adaptadors);

    }

}
