package com.example.nutritionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.nutritionapplication.adapter.RecipeListArrayAdapter;
import com.example.nutritionapplication.databinding.ActivityListNormalBinding;
import com.example.nutritionapplication.dto.RezepteTO;
import java.util.List;

public class
ListNormalActivity extends AppCompatActivity {
    RecipeListArrayAdapter adapter;
    ActivityListNormalBinding binding;
    ListView simpleListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityListNormalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        simpleListView = (ListView) findViewById(R.id.simpleListView);
        Intent i = getIntent();
        List<RezepteTO> results = (List<RezepteTO>) i.getSerializableExtra("LIST");
        adapter = new RecipeListArrayAdapter(results, getApplicationContext());
        simpleListView.setAdapter(adapter);
        simpleListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), DisplayRezeptActivity.class);
            RezepteTO recipe = results.get(position);
            intent.putExtra("Rezept", recipe);
            startActivity(intent);
        });
    }
}
