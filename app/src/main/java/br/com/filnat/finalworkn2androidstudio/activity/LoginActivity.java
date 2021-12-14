package br.com.filnat.finalworkn2androidstudio.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.filnat.finalworkn2androidstudio.R;

public class LoginActivity extends AppCompatActivity {

    private Button buttonCadastre;
    private Button buttonAcessar;
    private EditText editTextLogin, editTextSenhaLogin;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        setButtonCadastre();
        setButtonAcessar();
        auth = FirebaseAuth.getInstance();

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
                Logar();
            }
        });
    }

    private void Logar() {
        String email = editTextLogin.getText().toString();
        String senha = editTextSenhaLogin.getText().toString();
            if(email.isEmpty() || senha.isEmpty()){
                Toast.makeText(this, "Todos os campos devem ser preenchidos!", Toast.LENGTH_LONG).show();
            } else {
                auth.createUserWithEmailAndPassword(email, senha)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "Não possível criar o usuário!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }

    }

}
