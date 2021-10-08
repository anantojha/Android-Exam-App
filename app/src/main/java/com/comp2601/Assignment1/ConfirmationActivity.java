package com.comp2601.Assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        TextView mConfirmationView = findViewById(R.id.confirmation_view);
        Button mCloseButton = findViewById(R.id.close_button);

        mConfirmationView.setText("Exam Finished! \n\nEmail with copy of submitted exam sent.\n\n\n");

        mCloseButton.setOnClickListener(v -> {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory( Intent.CATEGORY_HOME );
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        });
    }
}

