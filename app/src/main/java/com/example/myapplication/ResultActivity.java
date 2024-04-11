package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private CircularProgressView circularProgressView;
    private TextView calorieTextView, proteinTextView, fatTextView, carbohydratesTextView;
    private Button backButton, recipesButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Initialize views
        circularProgressView = findViewById(R.id.circular_progress_view);
        calorieTextView = findViewById(R.id.calorie_text);
        proteinTextView = findViewById(R.id.protein_text);
        fatTextView = findViewById(R.id.fat_text);
        carbohydratesTextView = findViewById(R.id.carbohydrates_text);
        recipesButton = findViewById(R.id.recipes_button);
        backButton = findViewById(R.id.back_button);


        // Get the calorie intake value from the Intent
        Intent intent = getIntent();
        double calorieIntake = intent.getDoubleExtra("calorieIntake", 0.0);

        // Set calorie intake text
        calorieTextView.setText(String.format("%.2f", calorieIntake));

        // Calculate and set macronutrient values
        double protein = calculateProtein(calorieIntake);
        proteinTextView.setText("Protein: " + String.format("%.2f", protein) + "g");

        double fat = calculateFat(calorieIntake);
        fatTextView.setText("Fat: " + String.format("%.2f", fat) + "g");

        double carbohydrates = calculateCarbohydrates(calorieIntake);
        carbohydratesTextView.setText("Carbohydrates: " + String.format("%.2f", carbohydrates) + "g");

        // Animate the circular progress view
        animateProgress(calorieIntake);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the main page
                onBackPressed();
            }
        });

        recipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the recipes page
                Intent intent = new Intent(ResultActivity.this, Recipes.class);
                startActivity(intent);
            }
        });
    }

    private double calculateProtein(double calorieIntake) {
        // Perform your protein calculation here
        // Example: return 0.25 * calorieIntake;
        return 0.25 * calorieIntake / 4;
    }

    private double calculateFat(double calorieIntake) {
        // Perform your fat calculation here
        // Example: return 0.20 * calorieIntake;
        return 0.20 * calorieIntake / 9;
    }

    private double calculateCarbohydrates(double calorieIntake) {
        // Perform your carbohydrates calculation here
        // Example: return 0.55 * calorieIntake;
        return 0.55 * calorieIntake / 4;
    }

    // This method animates the circular progress view
    private void animateProgress(double calorieIntake) {
        int progress = (int) (calorieIntake * 100 / 2500);
        circularProgressView.setProgressWithAnimation(progress);
    }
}
