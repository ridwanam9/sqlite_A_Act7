package com.example.sqlite_a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sqlite_a.adapter.TemanAdapter;
import com.example.sqlite_a.database.DBController;
import com.example.sqlite_a.database.Teman;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TemanAdapter adapter;
    private ArrayList<Teman> temanArrayList;
    DBController controller = new DBController(this);
    String id,nmn,tlp;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.floatingBtn);
        BacaData();
        adapter = new TemanAdapter(temanArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TemanBaru.class);
                startActivity(intent);
            }
        });
    }

    public void BacaData(){

        ArrayList<HashMap<String,String>> dafterTeman = controller.getAllTeman();
        temanArrayList = new ArrayList<>();
        for (int i = 0; i<dafterTeman.size(); i++){
            Teman teman = new Teman();
            teman.setId(dafterTeman.get(i).get("id").toString());
            teman.setNama(dafterTeman.get(i).get("nama").toString());
            teman.setTelpon(dafterTeman.get(i).get("telpon").toString());

            temanArrayList.add(teman);
        }
    }

}