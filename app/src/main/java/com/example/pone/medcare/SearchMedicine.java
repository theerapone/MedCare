package com.example.pone.medcare;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SearchMedicine extends Activity {
    SimpleCursorAdapter simpleCursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.layout_search_medicine);


        MedicineTable medicineTable = new MedicineTable(this);
        Cursor cursor = medicineTable.allName();

        String[] columns = new String[]{"_id", "name"};
        int[] bind = new int[]{R.id.tvIDMedicine, R.id.tvNameMedicine};

        simpleCursorAdapter =   new SimpleCursorAdapter(this,
                                    R.layout.layout_list_name_medicine,
                                    cursor,
                                    columns,
                                    bind);

        ListView lv = (ListView)findViewById(R.id.lvMedicineName);
        lv.setAdapter(simpleCursorAdapter);
        lv.setOnItemClickListener(new ListClickHandler());
    }
    public class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
            // TODO Auto-generated method stub
            TextView listTextId = (TextView) view.findViewById(R.id.tvIDMedicine);
            TextView listTextName = (TextView) view.findViewById(R.id.tvNameMedicine);
            String text = listTextId.getText().toString() + ":" + listTextName.getText().toString();

            Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG).show();

            //Intent intent = new Intent(this, SecondActivity.class);
            //intent.putExtra("selected-item", listTextId.getText().toString());
            //startActivity(intent);
        }

    }

}
