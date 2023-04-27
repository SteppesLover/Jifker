package com.example.myapplication;

import android.content.Context;

public class MySingelton {
    private static MySingelton instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private MySingelton(Context context){
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static MySingelton getInstance(Context context){
        if(instance == null){
            instance = new MySingelton(context);
        }
    }

    private RequestQueue getRequestQueue(){
        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }

        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);

    }
}
