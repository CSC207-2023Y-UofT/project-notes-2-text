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

import com.example.notes2text.adapters.accountsettingsadaptors.PopUpEmailController;
import com.example.notes2text.adapters.accountsettingsadaptors.PopUpEmailPresenter;
import com.example.notes2text.R;

/**
 * View class for the changing email.
 */
public class PopUpEmailView extends AppCompatActivity {


    //Controller for changing user's email.
    PopUpEmailController controller;

    //Presenter for changing user's email.
    PopUpEmailPresenter presenter;

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

        //Initializing the controllers and presenters
        controller = new PopUpEmailController(getApplicationContext());
        presenter = new PopUpEmailPresenter(getApplicationContext());

        //Shows the user's email
        presenter.showEmail(text);

        Button submit = (Button) findViewById(R.id.Submit);
        Button back = (Button) findViewById(R.id.Back);

        //Set click listener for the buttons
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast;
                //Checks if user entered something or not
                if(!(input.getText().toString().equals(""))){
                    //If email change was successful a pop up message will mention it.
                    if(controller.buttonPressed(input, text)){
                        toast = Toast.makeText(PopUpEmailView.this, "Email Address Change Successful", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    //If email change was unsuccessful a pop up message will mention it.
                    else{
                        toast = Toast.makeText(PopUpEmailView.this, "Email Address Change Was Unsuccessful", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }
            }
        });
    }
}