// RecipesActivity.java
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Recipes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        // Initialize the back button
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the ResultActivity
                finish(); // This will close the RecipesActivity and return to the previous activity (ResultActivity)
            }
        });

        String recipe1 = "Recipe 1: Grilled Chicken Salad\n" +
                "Calories: 350\n" + "Protein: 30g\n" + "Carbs: 15g\n" + "Fat: 20g\n" +
                "Ingredients: Grilled chicken breast, mixed greens, cherry tomatoes, cucumber, balsamic vinaigrette\n";

        String recipe2 = "Recipe 2: Quinoa Bowl\n" +
                "Calories: 400\n" + "Protein: 25g\n" + "Carbs: 50g\n" + "Fat: 15g\n" +
                "Ingredients: Quinoa, black beans, corn, avocado, salsa, lime\n";

        String recipe3 = "Recipe 3: Veggie Stir-Fry\n" +
                "Calories: 300\n" + "Protein: 20g\n" + "Carbs: 40g\n" + "Fat: 10g\n" +
                "Ingredients: Tofu, broccoli, bell peppers, carrots, soy sauce, ginger\n";

        String recipe4 = "Recipe 4: Oatmeal with Berries\n" +
                "Calories: 250\n" + "Protein: 10g\n" + "Carbs: 40g\n" + "Fat: 5g\n" +
                "Ingredients: Oats, almond milk, berries, honey, chia seeds\n";

        String recipe5 = "Recipe 5: Turkey and Avocado Wrap\n" +
                "Calories: 320\n" + "Protein: 25g\n" + "Carbs: 30g\n" + "Fat: 15g\n" +
                "Ingredients: Whole wheat wrap, turkey slices, avocado, lettuce, tomato, mustard\n";

        ArrayList<String> recipes = new ArrayList<>();
        recipes.add(recipe1);
        recipes.add(recipe2);
        recipes.add(recipe3);
        recipes.add(recipe4);
        recipes.add(recipe5);

        TextView recipeTextView = findViewById(R.id.recipe_text);

        StringBuilder recipeText = new StringBuilder();
        for (String recipe : recipes) {
            recipeTextView.setText(recipeText.append(recipe).append("\n\n"));
        }

    }
}
