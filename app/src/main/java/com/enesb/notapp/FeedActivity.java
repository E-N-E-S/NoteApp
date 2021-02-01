package com.enesb.notapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class FeedActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    Button signoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        firebaseAuth = FirebaseAuth.getInstance();
        signoutButton = findViewById(R.id.signoutButton);
    }

    public void signoutClicked (View view) {
        try {
            firebaseAuth.signOut();

            Intent intentToOpening = new Intent(FeedActivity.this, OpeningActivity.class);
            startActivity(intentToOpening);
            finish();
        } catch (Exception e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
    }
}