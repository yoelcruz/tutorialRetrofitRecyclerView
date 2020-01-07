package com.vogella.android.tutorialretrofitrecyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btRetrofit;
    Button btRetrofitOrdenado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //metodo que abre dos activities (retrofit activity y retrofitOrdenadoActivity)
        setUpView();
    }

    private void setUpView(){
        btRetrofit = findViewById(R.id.btRetrofit);
        btRetrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RetrofitActivity.class));
            }
        });

        btRetrofitOrdenado = findViewById(R.id.btRetrofitOrdenado);
        btRetrofitOrdenado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RetrofitOrdenadoActivity.class));
            }
        });
    }
}
