package com.example.data_fetch;

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

public class MainActivity extends AppCompatActivity {

    private EditText name1,name2;
    private Button submit ,register;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name1=findViewById(R.id.name1);
        name2=findViewById(R.id.name2);
        submit=findViewById(R.id.submit);
        register=  findViewById(R.id.register);
        mauth=FirebaseAuth.getInstance();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_id = name1.getText().toString().trim();
                String password_id = name2.getText().toString().trim();

                mauth.signInWithEmailAndPassword(email_id,password_id)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                    startActivity(new Intent(MainActivity.this, list.class));

                                    }
                                    else
                                    {
                                        Toast.makeText(MainActivity.this, "login error", Toast.LENGTH_SHORT).show();
                                    }
                            }
                        });

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, register.class));

            }
        });






    }
}