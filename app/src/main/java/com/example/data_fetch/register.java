package com.example.data_fetch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText username,password;
    private Button reg;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        reg = findViewById(R.id.reg);
        mAuth=FirebaseAuth.getInstance();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameuser =username.getText().toString().trim();
                String passuser = password.getText().toString().trim();
                Log.d("register",nameuser);
                Log.d("register",passuser);

                mAuth.createUserWithEmailAndPassword(nameuser,passuser)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete( Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                User user = new User(nameuser,passuser);

                                Log.d("register",user.name.toString());
                                    Log.d("register",user.pass.toString());

                                    FirebaseDatabase.getInstance().getReference("User-Data")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete( Task<Void> task) {
                                            if(task.isSuccessful())
                                            {
                                                Log.d("register","user created");
                                                Toast.makeText(register.this, "Created ", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(register.this , MainActivity.class));
                                            }
                                            else {
                                                Log.d("register","user  not created");
                                                Toast.makeText(register.this, "Failed ", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                                else
                                {
                                    Log.d("register","user  not created");
                                    Toast.makeText(register.this, "Failed ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });



    }
}