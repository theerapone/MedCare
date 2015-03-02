package com.example.pone.medcare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class Profile extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_profile);

    }
    public void saveChange(View view){
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
        EditText etName = (EditText)findViewById(R.id.editTextFname);
        User user = new User();
        user.setFname(etName.getText().toString());

        RadioButton radioButton = (RadioButton)findViewById(R.id.radioButtonMale);
        boolean d = radioButton.isChecked();
        int gender = 0;
        if(d) {
            gender =1;
        }
        EditText etHight = (EditText)findViewById(R.id.editTextLName);
        Double hight = Double.parseDouble(etHight.getText().toString());





    }
}
