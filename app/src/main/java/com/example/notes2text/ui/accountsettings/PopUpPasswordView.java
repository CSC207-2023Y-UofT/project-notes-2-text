package com.example.notes2text.ui.accountsettings;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes2text.adapters.AccountSettingsAdaptors.PopUpPasswordController;
import com.example.notes2text.R;

public class PopUpPasswordView extends AppCompatActivity {

    PopUpPasswordController controller = new PopUpPasswordController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_password_view);

        EditText input = (EditText) findViewById(R.id.editTextTextPassword);
        EditText input2 = (EditText) findViewById(R.id.editTextTextPassword2);

        Button submit = (Button) findViewById(R.id.Submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast;
                if(!(input.getText().toString().equals("")) && !(input2.getText().toString().equals(""))){
                    if (controller.buttonPressed(getApplicationContext(), input, input2)){
                        toast = Toast.makeText(PopUpPasswordView.this, "Password Change Successful", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else{
                        toast = Toast.makeText(PopUpPasswordView.this, "Password Change Failed", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

            }
        });

    }
}