package com.example.sibanez.miner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Button aceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.militar);

        aceptar = (Button) findViewById(R.id.btn1);
        aceptar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent aceptar = new Intent(MainActivity.this, Buscaminas.class);
                startActivity(aceptar);
            }
        });
    }
}
