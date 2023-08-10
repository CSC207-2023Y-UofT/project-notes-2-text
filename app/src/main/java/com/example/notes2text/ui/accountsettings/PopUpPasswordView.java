/**
 * View class for the changing password.
 */
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

    //Controller for changing user's password.
    PopUpPasswordController controller = new PopUpPasswordController();

    /**
     * Creates the pop-up password view
     *
     * @param savedInstanceState    Instance of Bundle used to create the view.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_password_view);

        //View elements
        EditText input = (EditText) findViewById(R.id.editTextTextPassword);
        EditText input2 = (EditText) findViewById(R.id.editTextTextPassword2);

        Button submit = (Button) findViewById(R.id.Submit);

        //Set click listener for the Confirm button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast;
                //Checks if user entered something or not for both EditViews.
                if(!(input.getText().toString().equals("")) && !(input2.getText().toString().equals(""))){
                    //If password change was successful a pop up message will mention it.
                    if (controller.buttonPressed(getApplicationContext(), input, input2)){
                        toast = Toast.makeText(PopUpPasswordView.this, "Password Change Successful", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    //If password change was unsuccessful a pop up message will mention it.
                    else{
                        toast = Toast.makeText(PopUpPasswordView.this, "Password Change Failed", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

            }
        });

    }
}