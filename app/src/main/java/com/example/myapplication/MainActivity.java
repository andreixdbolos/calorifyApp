package com.example.myapplication; // Replace with your package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText currentWeight, heightFeet, heightInch, age;
    private RadioGroup radioGroup;
    private Spinner activityLevelSpinner;
    private String selectedActivityLevel;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        currentWeight = findViewById(R.id.current_weight);
        heightFeet = findViewById(R.id.height_feet);
        heightInch = findViewById(R.id.height_inch);
        age = findViewById(R.id.age);
        radioGroup = findViewById(R.id.radioGroup);
        activityLevelSpinner = findViewById(R.id.activity_level);
        calculateButton = findViewById(R.id.calculate);
        calculateButton.setOnClickListener(this::calculateCalories);

        // Set up spinner for activity level
        setupActivityLevelSpinner();

        // Handle spinner selection changes
        activityLevelSpinner.setOnItemSelectedListener(this);
    }

    private void setupActivityLevelSpinner() {
        // Create an adapter with activity level options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activity_levels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityLevelSpinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedActivityLevel = (String) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private double calculateBMR(int weight, int feet, int inch, int age, String selectedActivityLevel) {
        // Calculate BMR using the Mifflin-St Jeor equation
        double bmr = 0;
        int height = (feet * 12) + inch;
        double v = 10 * weight + 6.25 * height - 5 * age;
        if (Objects.equals(selectedActivityLevel, "Sedentary")) {
            bmr = v + 5;
        } else if (Objects.equals(selectedActivityLevel, "Light")) {
            bmr = v + 5 + 200;
        } else if (Objects.equals(selectedActivityLevel, "Moderate")) {
            bmr = v + 5 + 400;
        } else if (Objects.equals(selectedActivityLevel, "Active")) {
            bmr = v + 5 + 600;
        }
        return bmr;
    }

    private double calculateTDEE(double bmr, String activityLevel) {
        // Calculate TDEE based on activity level
        double tdee = 0;
        if (Objects.equals(activityLevel, "Sedentary")) {
            tdee = bmr * 1.2;
        } else if (Objects.equals(activityLevel, "Light")) {
            tdee = bmr * 1.375;
        } else if (Objects.equals(activityLevel, "Moderate")) {
            tdee = bmr * 1.55;
        } else if (Objects.equals(activityLevel, "Active")) {
            tdee = bmr * 1.725;
        }
        return tdee;
    }

    public void calculateCalories(View view) {
        String weightStr = currentWeight.getText().toString();
        String heightFeetStr = heightFeet.getText().toString();
        String heightInchStr = heightInch.getText().toString();
        String ageStr = age.getText().toString();
        String selectedActivityLevel = this.selectedActivityLevel;

        // Check for empty input and handle appropriately
        if (weightStr.isEmpty() || heightFeetStr.isEmpty() || heightInchStr.isEmpty() || ageStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int weight = Integer.parseInt(weightStr);
            int feet = Integer.parseInt(heightFeetStr);
            int inch = Integer.parseInt(heightInchStr);
            int userAge = Integer.parseInt(ageStr);
            // Calculate BMR
            double bmr = calculateBMR(weight, feet, inch, userAge, selectedActivityLevel);

            // Calculate TDEE
            double calorieIntake = calculateTDEE(bmr, selectedActivityLevel);

            // Display the result on a new page (assuming ResultActivity is implemented)
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("calorieIntake", calorieIntake);
            startActivity(intent);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }
}