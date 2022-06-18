package com.example.nutritionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nutritionapplication.adapter.RecipeListArrayAdapter;
import com.example.nutritionapplication.databinding.ActivityListNormalBinding;
import com.example.nutritionapplication.dto.FoodEntryTO;
import com.example.nutritionapplication.dto.MealTO;
import com.example.nutritionapplication.dto.RezepteTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
ListNormalActivity extends AppCompatActivity {
    RecipeListArrayAdapter adapter;
    ActivityListNormalBinding binding;
    ListView simpleListView;
    ArrayList<RezepteTO> results;
    NutritionAndroidApplication myApp;
    String rezeptName;
    int minK;
    int maxK;
    int minP;
    int maxP;
    boolean fructose;
    boolean lactose;
    boolean histamine;
    boolean vegan;
    boolean vegetarisch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityListNormalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.myApp = (NutritionAndroidApplication) getApplication();
        simpleListView = (ListView) findViewById(R.id.listViewNormal);
        this.results = new ArrayList<RezepteTO>();
        adapter = new RecipeListArrayAdapter(results, getApplicationContext());
        simpleListView.setAdapter(adapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle b = getIntent().getExtras();
        this.minK = b.getInt("minK");
        this.maxK = b.getInt("maxK");
        this.minP = b.getInt("minP");
        this.maxP = b.getInt("maxP");
        this.rezeptName = b.getString("rezeptName");
        this.fructose = b.getBoolean("fructose");
        this.lactose = b.getBoolean("lactose");
        this.histamine = b.getBoolean("histamine");
        this.vegan = b.getBoolean("vegan");
        this.vegetarisch = b.getBoolean("vegetarisch");
        simpleListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), DisplayRezeptActivity.class);
            RezepteTO recipe = results.get(position);
            Bundle b1 = new Bundle();
            b1.putInt("wt",recipe.getArbeitszeit());
            b1.putInt("ct",recipe.getKochzeit());
            b1.putInt("ot",recipe.getGesamtzeit());
            b1.putInt("portionen",recipe.getPortionen());
            b1.putString("menu",recipe.getMenueart());
            b1.putString("rezeptName",recipe.getName());
            b1.putString("image",recipe.getImage());
            b1.putBoolean("vegan",recipe.isVegan());
            b1.putBoolean("vegetarisch",recipe.isVegetarisch());
            b1.putBoolean("histamine",recipe.isHistamine());
            b1.putBoolean("fructose", recipe.isFructose());
            b1.putBoolean("lactose", recipe.isLactose());
            b1.putInt("calories",recipe.getKalorien());
            b1.putInt("proteine",recipe.getProteine());
            b1.putString("author",recipe.getAuthor());
            intent.putExtras(b1);
            startActivity(intent);
        });
        listItems();
    }
    public void listItems(){
        Call<List<RezepteTO>> call = this.myApp.getRezepteService().listNormal
                ("Bearer "+myApp.getJwt(),rezeptName,fructose,lactose,histamine,vegan,vegetarisch,minK,maxK,minP,maxP);
        call.enqueue(new Callback<List<RezepteTO>>() {
            @Override
            public void onResponse(Call<List<RezepteTO>> call, Response<List<RezepteTO>> response) {
                if(response.isSuccessful()){
                    ArrayList<RezepteTO> list = (ArrayList<RezepteTO>) response.body();
                    results.clear();
                    results.addAll(list);
                    adapter.notifyDataSetChanged();
                }else{
                    showToast("Communication error occured. " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<RezepteTO>> call, Throwable t) {
                showToast("Communication error occured. ");
            }
        });
    }
    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
