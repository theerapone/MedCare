package com.example.pone.medcare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Search extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_search);

    }
    public void search_medicine(View click){
        Intent intent = new Intent(this, SearchMedicine.class);
        startActivity(intent);
    }

}
