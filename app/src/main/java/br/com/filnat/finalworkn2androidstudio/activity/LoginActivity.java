package br.com.filnat.finalworkn2androidstudio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.filnat.finalworkn2androidstudio.R;

public class LoginActivity extends AppCompatActivity {

    private Button buttonCadastre;
    private Button buttonAcessar;
    private EditText editTextLogin, editTextSenhaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        setButtonCadastre();
        setButtonAcessar();
    }

    public void initView() {

        editTextLogin = findViewById(R.id.editTextLogin);
        editTextSenhaLogin = findViewById(R.id.editTextSenhaLogin);
        buttonCadastre = findViewById(R.id.buttonCadastre);
        buttonAcessar = findViewById(R.id.buttonAcessar);

    }

    public void setButtonCadastre() {

        buttonCadastre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);

            }
        });
    }

    public void setButtonAcessar() {

        buttonAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, FuncoesActivity.class);
                startActivity(intent);

            }
        });
    }
}
