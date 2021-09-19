package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText password;
    private Switch guardalogeo;
    private String precionar = "Mis preferencias";
    private Button logearse;
    private String llave = "sesion";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = (EditText) findViewById(R.id.usuario);
        password = (EditText)findViewById(R.id.contraseña);
        guardalogeo = (Switch)findViewById(R.id.guardarlogin);

        iniciarelementos();

        if (revisarsesion()){
            startActivity(new Intent(this, HomeActivity.class));
        }else {
            String mensaje = "Iniciando Sesión";
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
        }

        logearse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarpreferencia(guardalogeo.isChecked());
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    public void iniciar(View view){

        String nombre = usuario.getText().toString();
        String pas = password.getText().toString();

        if(nombre.length() == 0){

           Toast.makeText(this,"Debes de ingresar un usuario", Toast.LENGTH_LONG).show();

        }if(pas.length() == 0){

            Toast.makeText(this, "Debes de ingresar una contraseña", Toast.LENGTH_LONG).show();

        }if(nombre.length() != 0 && pas.length() != 0){

            Toast.makeText(this, "Iniciando Sesión...!", Toast.LENGTH_LONG).show();
            Intent siguiente = new Intent(this, HomeActivity.class);
            startActivity(siguiente);
        }
    }

    public void guardarlogin(View view){

        boolean estado = getValuePreference(getApplicationContext());
        guardalogeo.setChecked(estado);

        guardalogeo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                guardarpreferencia(isChecked);

            }

        });
        if(view.getId() == R.id.guardarlogin){

            if (guardalogeo.isChecked()){
                Toast.makeText(this, "Activado", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, "desactivado", Toast.LENGTH_LONG).show();
            }
        }
    }

    /* Ejemplo para guardar el estado del switch

    public void saveValuePreference(Context context, boolean valor) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putBoolean("estado_switch", valor);
        editor.commit();
    }*/

    public void guardarpreferencia(/*Context context,*/ boolean valor){

        /*SharedPreferences setting = context.getSharedPreferences(precionar, MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = setting.edit();*/
        editor.putBoolean(llave, valor);
        editor.commit();
        editor.apply();

    }

    public boolean getValuePreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(precionar, MODE_PRIVATE);
        boolean b = false;
        return  preferences.getBoolean("estado: ", b);
    }

    public boolean revisarsesion(){
        return this.preferences.getBoolean(llave, false);
    }

    public void iniciarelementos(){

        preferences = this.getPreferences(0);
        editor = preferences.edit();

        logearse = findViewById(R.id.logearse);
        guardalogeo = (Switch)findViewById(R.id.guardarlogin);
    }
}