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
import com.google.firebase.auth.FirebaseUser;

import br.com.filnat.finalworkn2androidstudio.R;

public class CadastroActivity extends AppCompatActivity {

    private Button buttonCadastrar;
    private EditText editTextEmail, editTextSenha;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        initView();
        //setButtonCadastrar();
        auth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                usuario = auth.getCurrentUser();
                if(usuario != null){
                    cadastrar();
                    Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        };
        auth = FirebaseAuth.getInstance();
    }

    private void initView() {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSenha = findViewById(R.id.editTextSenha);
        buttonCadastrar = findViewById(R.id.buttonCadastrar);

    }

    /*public void setButtonCadastrar() {
        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();
                Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }*/

    private void cadastrar(){
        String email = editTextEmail.getText().toString();
        String senha = editTextSenha.getText().toString();

        if(email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Todos os campos são obrigatórios!", Toast.LENGTH_LONG).show();
        }else{
            auth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                                 if(task.isSuccessful()){
                                     usuario = auth.getCurrentUser();
                                 }else{
                                     task.getException().toString();
                                     Toast.makeText(CadastroActivity.this, "Não foi possível criar o usuário!", Toast.LENGTH_LONG).show();
                                 }
                        }
                    });
        }
    }

}