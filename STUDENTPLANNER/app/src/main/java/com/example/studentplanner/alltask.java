package com.example.studentplanner;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class alltask extends AppCompatActivity {
    ArrayList<String> tasks = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alltask);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
            setContentView(R.layout.activity_alltasks);

            ListView lv = findViewById(R.id.listTasks);
            adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, tasks);
            lv.setAdapter(adapter);

            FirebaseFirestore.getInstance()
                    .collection("StudentPlannerDB")
                    .get()
                    .addOnSuccessListener(q -> {
                        for(DocumentSnapshot d : q)
                            tasks.add(d.getString("titre"));
                        adapter.notifyDataSetChanged();
        });
    }
}