package com.example.calupe.s5_penaranda_llamada;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ListView lv_contactos;
    ContactoAdapter customAdapter;
    ImageButton btn_generar;
    EditText et_contacto;
    EditText et_telefono;
    Switch swt_sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        et_contacto=findViewById(R.id.et_contacto);
        et_telefono=findViewById(R.id.et_telefono);
        btn_generar=(ImageButton)findViewById(R.id.btn_generar);

        lv_contactos = findViewById(R.id.lv_contactos);
        customAdapter = new ContactoAdapter(this);
        lv_contactos.setAdapter(customAdapter);

        Contacto contacto1 = new Contacto("Valentina alegria","3182726766");
        Contacto contacto2 = new Contacto("Ana Isabel","3226422998");
        Contacto contacto3 = new Contacto("Jose Devera","3023428234");

        customAdapter.agregarContacto(contacto1);
        customAdapter.agregarContacto(contacto2);
        customAdapter.agregarContacto(contacto3);



        btn_generar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String nombre = et_contacto.getText().toString();
                String telefono = et_telefono.getText().toString();

                Contacto newContacto = new Contacto(nombre,telefono);
                customAdapter.agregarContacto(newContacto);
            }
        });
    }
}
