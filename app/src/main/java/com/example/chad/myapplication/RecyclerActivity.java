package com.example.chad.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData> myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new GridLayoutManager(this, 3);
        //mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //mLayoutManager = new LinearLayoutManager(getApplicationContext());

        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        myDataset = new ArrayList<>();
        mAdapter = new MyRecyclerAdapter(getApplicationContext(), myDataset);
        mRecyclerView.setAdapter(mAdapter);
        myDataset.add(new MyData("#1", R.drawable.a));
        myDataset.add(new MyData("#2", R.drawable.b));
        myDataset.add(new MyData("#3", R.drawable.c));
        myDataset.add(new MyData("#4", R.drawable.d));
        myDataset.add(new MyData("#5", R.drawable.e));

        myDataset.add(new MyData("#InsideOut", R.drawable.insideout));
        myDataset.add(new MyData("#Mini", R.drawable.mini));
        myDataset.add(new MyData("#ToyStory", R.drawable.toystory));
    }
}
