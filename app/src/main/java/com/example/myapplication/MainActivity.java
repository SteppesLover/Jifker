package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DataAdapter.OnItemClickListener {

    RecyclerView rView;
    ArrayList<DataModel> dataModelArrayList = new ArrayList<>();
    DataAdapter dataAdapter;

    public static final String API_KEY= "5I3gstjYyn4YGdVKnmccpY1GTLn9cOPe";
    public static final String BASE_URL= "https://api.giphy.com/v1/gifs/trending?api_key=5I3gstjYyn4YGdVKnmccpY1GTLn9cOPe";

    String url = BASE_URL+API_KEY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rView = findViewById(R.id.recyclerView);
        rView.setLayoutManager(new GridLayoutManager(this, 2));
        rView.addItemDecoration(new SpaceItemDecoration(10));
        rView.setHasFixedSize(true);

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>()){
            @Override
            public void onResponse(JSONObject response){
                JSONArray dataArray = response.getJSONArray("data");

                for (int i=0;i<dataArray.length();i++){
                    JSONObject obj = dataArray.getJSONObject(i);

                    JSONObject obj1 = obj.getJSONObject("images");
                    JSONObject obj2 = obj1.getJSONObject("downsized_medium");

                    String sourceUrl = obj2.getString("url");

                    dataModelArrayList.add(new DataModel(sourceUrl));
                    rView.setAdapter(dataAdapter);
                    dataAdapter.setOnItemClickListener.MainActivity.this::onItemClick;
                }
            } catch(JSONException e){
                e.printStackTrace();
            }
        }, new Respose.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error"+error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        }

        MySingeleton.getInstance(this).addToRequestQueue(objectRequest);
    }

    @Override
    public void onItemClick(int pos) {
        Intent fullView = new Intent(this, FullActivity.class);
        DataModel clickedItem = dataModelArrayList.get(pos);

        fullView.putExtra("imageUrl", clickedItem.getImageUrl());
        startActivity(fullView);
    }
}