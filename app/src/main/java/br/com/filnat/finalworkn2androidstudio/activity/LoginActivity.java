package br.com.filnat.finalworkn2androidstudio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.com.filnat.finalworkn2androidstudio.R;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void cadastrar(View v){
        Intent i = new Intent(this,CadastroActivity.class);
        startActivity(i);
    }
}