package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private String llave = "sesion";
    private Button cerrarsesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        iniciarelementos();

        if (revisarsesion()){
            startActivity(new Intent(this, MainActivity.class));
        }else {
            String mensaje = "Iniciando Sesión";
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
        }

        cerrarsesion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                boolean valor = false;
                revisarsesion();
                guardarpreferencia(valor);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }


    public void guardarpreferencia(/*Context context,*/ boolean valor){

        /*SharedPreferences setting = context.getSharedPreferences(precionar, MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = setting.edit();*/
        editor.putBoolean(llave, valor);
        editor.commit();
        editor.clear();
        editor.apply();

    }

    public boolean revisarsesion(){
        return this.preferences.getBoolean(llave, false);
    }

    public void iniciarelementos(){

        preferences = this.getPreferences(0);
        editor = preferences.edit();

        cerrarsesion = findViewById(R.id.cerrarlogeo);
    }
}