package com.enesb.notapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    ImageView backButton;
    private FirebaseAuth firebaseAuth;
    EditText emailText, passwordText, passwordText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        firebaseAuth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.emailRegisterText);
        passwordText = findViewById(R.id.passwordLoginText);
        passwordText2 = findViewById(R.id.passwordRegisterText2);

        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void registerClicked (View view) {
        
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        String password2 = passwordText2.getText().toString();
        
        if (password.equals(password2) && !email.matches("")){
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    // Giris basarili olursa intent
                    Toast.makeText(RegisterActivity.this, "User Created!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, FeedActivity.class);
                    startActivity(intent);
                    finish();
                    
                    
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(this, "Check your password or email!", Toast.LENGTH_LONG).show();
        }
    }
}