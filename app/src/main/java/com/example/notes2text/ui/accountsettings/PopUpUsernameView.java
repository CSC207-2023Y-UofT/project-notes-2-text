/**
 * View class for the changing username.
 */
package com.example.notes2text.ui.accountsettings;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes2text.adapters.accountsettingsadaptors.PopUpUsernameController;
import com.example.notes2text.adapters.accountsettingsadaptors.PopUpUsernamePresenter;
import com.example.notes2text.R;

/**
 * View class for the changing username.
 */
public class PopUpUsernameView extends AppCompatActivity {

    //Controller for changing user's username.
    PopUpUsernameController controller;

    //Presenter for changing user's username.
    PopUpUsernamePresenter presenter;

    /**
     * Creates the pop-up username view
     *
     * @param savedInstanceState    Instance of Bundle used to create the view.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_username_view);

        //View elements
        TextView text = (TextView) findViewById(R.id.CurrentUsername);
        EditText input = (EditText) findViewById(R.id.editTextTextPersonName);

        //Initializing the controllers and presenters
        controller = new PopUpUsernameController(getApplicationContext());
        presenter = new PopUpUsernamePresenter(getApplicationContext());

        //Shows the user's username.
        presenter.showUsername(text);

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
                    //If username change was successful a pop up message will mention it.
                    if (controller.buttonPressed(input, text)){
                        toast = Toast.makeText(PopUpUsernameView.this, "Username Change Successful", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    //If username change was unsuccessful a pop up message will mention it.
                    else{
                        toast = Toast.makeText(PopUpUsernameView.this, "Username Change Was Unsuccessful", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }
            }
        });

    }
}