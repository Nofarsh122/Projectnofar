package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.graphics.Insets;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button btnlog;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;

    EditText etemail, etpass;
    Button btnSignIn;
    String email, pass;
    TextView tvError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("User");


        etemail = findViewById(R.id.etEmailLogin);
        etpass = findViewById(R.id.etPassLogin);
        tvError=findViewById(R.id.tvErrorLogIn);
        btnlog=(Button)findViewById(R.id.btnSignIn);
        btnlog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        email = etemail.getText().toString()+"";
        pass = etpass.getText().toString()+"";
        Log.d("TAG", "onClick:btnSignIn");

        boolean isValid=true;
        if (!email.contains("@")){
            etpass.setError("כתובת אימייל אינה תקינה");
            isValid = false;
        }
        if(pass.length()<6){
            etpass.setError("סיסמא צריכה להיות בעלת שש תווים לפחות");
            isValid = false;
        }
        if(pass.length()>20){
            etpass.setError("סיסמא צריכה להיות מקסימום בעלת 20 תווים");
            isValid = false;
        }

        if(isValid) {
            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                final String userUid = user.getUid();


                                Intent go = new Intent(getApplicationContext(), HomePage.class);
                                startActivity(go);
                            } else {

//                                // If sign in fails, display a message to the user.
                                Log.w("etroor sign in", "signInWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                tvError.setText("משתמש אינו קיים");
//

                            }
                        }
                    }
        }

    }