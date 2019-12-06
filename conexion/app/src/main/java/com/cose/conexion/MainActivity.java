package com.cose.conexion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import conexion.conexdb;
import modelo.ContactoModel;

public class MainActivity extends AppCompatActivity {
    //Declaramos variables tipo edit y boton
    EditText txtnoms,txtcorS,txtedads,txttels,txtconsult;
    Button consultbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //asignamos valor
        txtnoms = findViewById(R.id.txtnom);
        txtcorS = findViewById(R.id.txtcor);
        txtedads = findViewById(R.id.txtedad);
        txttels = findViewById(R.id.txttel);
        txtconsult = findViewById(R.id.txtconsult);

        consultbtn = findViewById(R.id.btnconsul);
    }

    //Funcion del boton registro
    public void registro(View view) {
        //Instanciamos conexdb que contiene los metodos de registro
        conexdb cad = new conexdb(getApplicationContext());
        //instanciamos modelo contiene setter y getter
        ContactoModel cm = new ContactoModel();
        try{
        //enviamos los valores asignados de los editext con los set obteniendolos como texto a string
        cm.setNombre(txtnoms.getText().toString());
        cm.setCorreo(txtcorS.getText().toString());
        cm.setEdad(Integer.parseInt(txtedads.getText().toString()));
        cm.setTelefono(txttels.getText().toString());


        //si registra el contacto con los valores enviados por setter con cm
        if(cad.RegisterContact(cm))
        {
            //mensaje emergente
            Toast.makeText(getApplicationContext(),"REGISTRADO CORRECTAMENTE",Toast.LENGTH_LONG).show();
            limpiarcampos();
            //si no registra arroje error
        }else {
            Toast.makeText(getApplicationContext(),"ERROR REGISTRAR",Toast.LENGTH_LONG).show();
            limpiarcampos();
        }
        }catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),"POR FAVOR LLENA TODOS LOS CAMPOS OBLIGATORIOS.",Toast.LENGTH_SHORT).show();
        }
    }

    //metodo privado de limpiarcampos
    private void limpiarcampos() {
        txtnoms.getText().clear();
        txtcorS.getText().clear();
        txtedads.getText().clear();
        txttels.getText().clear();
        txtnoms.requestFocus();

    }

    public void consultar(View view) {


            int id = 0;
            String nom;
            String corr;
            String tel;
            int edads;
            //instanciamos model setter and getter
            ContactoModel con = new ContactoModel();

            //enviamos por setter lo que contenga la variable consut como texto string
            con.setCorreo(txtconsult.getText().toString());

            //instanciamos conexbd contiene el metodo de consulta
            conexdb conex = new conexdb(getApplicationContext());

            //asignamos valor a con con los datos obtenidos
            con = conex.consultarPersona(con);

            try{


            //si con es diferente a null me almacene los datos de la consulta en variables
            if (con != null) {

                    //asignacion de los valores consultados a las variables
                    id = con.get_id();
                    nom = con.getNombre();
                    corr = con.getCorreo();
                    tel = con.getTelefono();
                    edads = con.getEdad();

                    //mensaje emergente
                    Toast.makeText(getApplicationContext(), "CONSULTADO...", Toast.LENGTH_SHORT).show();
                    //Pasamos de una ventana a otra con el metodo intent
                    Intent pasarview = new Intent(MainActivity.this, consultado.class);

                    //pasamos los valores correspondientes a la otra ventana
                    pasarview.putExtra("id", id);
                    pasarview.putExtra("nombre", nom);
                    pasarview.putExtra("correo", corr);
                    pasarview.putExtra("telefono", tel);
                    pasarview.putExtra("edad", edads);

                    //ejecutamos el paso de ventana
                    startActivity(pasarview);
                    //si es igual a null nos arroje mensaje que no existe

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "NO EXISTE USUARIO", Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception ex)
            {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();

            }


            }
        }


    //funcion consultar
    /*
    public void consultar(View view) {

        conexdb db = new conexdb(getApplicationContext());
        String buscar = txtcorS.getText().toString();
        String[] datos;
        datos=db.Consultar(buscar);
        txtnoms.setText(datos[0]);
        txttels.setText(datos[1]);
        txtedads.setText(datos[2]);
        Toast.makeText(getApplicationContext(),datos[4],Toast.LENGTH_SHORT).show();

        //asignamos variables
        int id = 0;
        String nom;
        String corr;
        String tel;
        int edads;
        //instanciamos model setter and getter
        ContactoModel con = new ContactoModel();

        //enviamos por setter lo que contenga la variable consut como texto string
        con.setCorreo(txtconsult.getText().toString());

        //instanciamos conexbd contiene el metodo de consulta
        conexdb conex = new conexdb(getApplicationContext());

        //asignamos valor a con con los datos obtenidos
        con = conex.consultarPersona(con);


        //si con es diferente a null me almacene los datos de la consulta en variables
        if(con!=null){
            try{
                //asignacion de los valores consultados a las variables
                id = con.get_id();
                nom = con.getNombre();
                corr = con.getCorreo();
                tel = con.getTelefono();
                edads = con.getEdad();

                //mensaje emergente
            Toast.makeText(getApplicationContext(),"CONSULTADO...",Toast.LENGTH_SHORT).show();
                //Pasamos de una ventana a otra con el metodo intent
            Intent pasarview = new Intent(MainActivity.this,consultado.class);

            //pasamos los valores correspondientes a la otra ventana
            pasarview.putExtra("id",id);
            pasarview.putExtra("nombre",nom);
            pasarview.putExtra("correo",corr);
            pasarview.putExtra("telefono",tel);
            pasarview.putExtra("edad",edads);

            //ejecutamos el paso de ventana
            startActivity(pasarview);
            }catch (Exception ex){

            }
            //si es igual a null nos arroje mensaje que no existe
        }else{
            Toast.makeText(getApplicationContext(),"NO EXISTE USUARIO",Toast.LENGTH_SHORT).show();

        }

    }
    */


