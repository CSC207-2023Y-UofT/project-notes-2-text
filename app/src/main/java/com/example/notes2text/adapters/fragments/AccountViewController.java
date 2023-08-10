package com.example.notes2text.adapters.fragments;

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
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountViewController#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountViewController extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountViewController() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountViewController.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountViewController newInstance(String param1, String param2) {
        AccountViewController fragment = new AccountViewController();
        return fragment;
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

        Button UsernameView = (Button) view.findViewById(R.id.PopUpUsernameView);

        Button PasswordView = (Button) view.findViewById(R.id.PopUpPasswordView);


        Button EmailView = (Button) view.findViewById(R.id.PopUpEmailView);

        Button Logout = (Button) view.findViewById(R.id.logout);

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