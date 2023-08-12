/**
 * View class for the changing email.
 */
package com.example.notes2text.ui.accountsettings;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.notes2text.adapters.AccountSettingsAdaptors.PopUpEmailController;
import com.example.notes2text.adapters.AccountSettingsAdaptors.PopUpEmailPresenter;
import com.example.notes2text.R;

/**
 * View class for the changing email.
 */
public class PopUpEmailView extends AppCompatActivity {


    //Controller for changing user's email.
    PopUpEmailController controller = new PopUpEmailController();

    //Presenter for changing user's email.
    PopUpEmailPresenter presenter = new PopUpEmailPresenter();

    /**
     * Creates the pop-up email view
     *
     * @param savedInstanceState    Instance of Bundle used to create the view.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_email_view);

        //View elements
        TextView text = (TextView) findViewById(R.id.CurrentEmail);
        EditText input = (EditText) findViewById(R.id.editTextEmail);

        //Shows the user's email
        presenter.showEmail(text);

        Button submit = (Button) findViewById(R.id.Submit);

        //Set click listener for the Confirm button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast;
                //Checks if user entered something or not
                if(!(input.getText().toString().equals(""))){
                    //If email change was successful a pop up message will mention it.
                    if(controller.buttonPressed(getApplicationContext(), input, text)){
                        toast = Toast.makeText(PopUpEmailView.this, "Email Address Change Successful", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    //If email change was unsuccessful a pop up message will mention it.
                    else{
                        toast = Toast.makeText(PopUpEmailView.this, "Email Address Already Being Used", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }
            }
        });
    }
}