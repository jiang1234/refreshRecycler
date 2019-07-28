package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RefreshRecyclerView refreshRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshRecyclerView = findViewById(R.id.recycler_view);
        RefreshRecyclerAdapter adapter = new RefreshRecyclerAdapter(initDate());
        refreshRecyclerView.setAdapter(adapter);
        refreshRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        refreshRecyclerView.addRefreshHeader(new HeaderView(this));
    }

    public ArrayList<Integer> initDate() {
        ArrayList<Integer> datalist = new ArrayList<>();
        datalist.add(1);
        datalist.add(2);
        datalist.add(3);
        datalist.add(4);
        datalist.add(5);
        datalist.add(6);
        datalist.add(7);
        datalist.add(7);datalist.add(7);
        datalist.add(7);
        datalist.add(7);
        datalist.add(7);
        datalist.add(7);
        datalist.add(7);
        datalist.add(7);
        datalist.add(7);
        datalist.add(7);
        datalist.add(7);
        datalist.add(7);
        datalist.add(7);


        return datalist;
    }
}
