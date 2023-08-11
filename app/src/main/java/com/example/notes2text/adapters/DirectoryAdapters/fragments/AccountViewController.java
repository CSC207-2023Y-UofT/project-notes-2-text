package com.example.notes2text.adapters.DirectoryAdapters.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.notes2text.R;
import com.example.notes2text.UserLogin.CurrentUser;
import com.example.notes2text.ui.accountsettings.PopUpEmailView;
import com.example.notes2text.ui.accountsettings.PopUpPasswordView;
import com.example.notes2text.ui.accountsettings.PopUpUsernameView;
import com.example.notes2text.UserLogin.LoginView;

/**
 * Controller class for the AccountView fragment.
 */
public class AccountViewController extends Fragment {

    public AccountViewController() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.settings_activity, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //View elements
        Button UsernameView = (Button) view.findViewById(R.id.PopUpUsernameView);

        Button PasswordView = (Button) view.findViewById(R.id.PopUpPasswordView);


        Button EmailView = (Button) view.findViewById(R.id.PopUpEmailView);

        Button Logout = (Button) view.findViewById(R.id.logout);

        //Set click listeners for the buttons to direct to pages that changes account info.
        UsernameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getApplicationContext(), PopUpUsernameView.class));
            }
        });

        PasswordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getApplicationContext(), PopUpPasswordView.class));
            }
        });

        EmailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getApplicationContext(), PopUpEmailView.class));
            }
        });

        //Set click listener for logout button. Clears any activities before it so that when
        //the android back button is pressed after logging out, the user can't go to the
        //AccountView page.
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentUser.setUser(null);
                CurrentUser.clearCurrent(getActivity().getApplicationContext());
                Intent intent = new Intent(getActivity().getApplicationContext(), LoginView.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

}