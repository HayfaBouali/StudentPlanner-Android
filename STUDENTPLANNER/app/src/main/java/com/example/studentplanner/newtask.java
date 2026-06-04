package com.example.studentplanner;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kotlinx.coroutines.scheduling.Task;

public class newtask extends AppCompatActivity {
    EditText t, d, m, da;
    Spinner p;
    FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_newtask);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
                }
            setContentView(R.layout.activity_newtask);

            t = findViewById(R.id.edtTitre);
            d = findViewById(R.id.edtDesc);
            m = findViewById(R.id.edtMatiere);
            da = findViewById(R.id.edtDate);
            p = findViewById(R.id.spPriorite);

            db = FirebaseFirestore.getInstance();

            findViewById(R.id.btnAdd).setOnClickListener(v -> addTask());
        void addTask() {
            Task task = new Task(
                    t.getText().toString(),
                    d.getText().toString(),
                    m.getText().toString(),
                    da.getText().toString(),
                    p.getSelectedItem().toString()
            );

            db.collection("StudentPlannerDB")
                    .add(task)
                    .addOnSuccessListener(a ->
                            Toast.makeText(this,"Tâche ajoutée",Toast.LENGTH_SHORT).show());
        }
    }


});

