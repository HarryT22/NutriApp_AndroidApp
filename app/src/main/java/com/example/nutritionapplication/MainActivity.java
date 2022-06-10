package com.example.nutritionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.searchbar:
                Intent intent1 = new Intent(this,SearchbarActivity.class);
                startActivity(intent1);
                break;
            case R.id.deleteRecipe:
                Intent intent2 = new Intent(this,DeleteRecipeActivity.class);
                startActivity(intent2);
                break;
            case R.id.DeleteFoodFromRecipe:
                Intent intent3 = new Intent(this,deleteFoodFromRecipeActivity.class);
                startActivity(intent3);
                break;
            case R.id.AddRecipe:
                Intent intent4 = new Intent(this,AddRecipeActivity.class);
                startActivity(intent4);
                break;
            case R.id.AddFoodToRecipe:
                Intent intent5 = new Intent(this,addFoodToRecipeActivity.class);
                startActivity(intent5);
                break;
        }
        return true;
    }


}