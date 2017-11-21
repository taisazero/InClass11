package com.example.zero.inclass11.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zero.inclass11.R;

/**
 * @author Josiah Laivins
 * @version 10/23/2017
 */

public class LoginFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public static boolean flagLogin = false;
    public static boolean flagSignUpNav = false;

    public static Button btnLogin;
    public static Button btnSignUpNav;

    public static TextView editEmail;
    public static TextView editPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnLogin = getView().findViewById(R.id.btnLogin);
        btnSignUpNav = getView().findViewById(R.id.btnSignUpNav);
        editEmail = getView().findViewById(R.id.editUserName);
        editPassword = getView().findViewById(R.id.editPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flagSignUpNav) {
                    flagLogin = true;
                    mListener.gotoNextFragment(FragmentAction.FROM_LOGIN_GO_TO_HOME);
                }
            }
        });

        btnSignUpNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flagLogin) {
                    flagSignUpNav = true;
                    mListener.gotoNextFragment(FragmentAction.FROM_LOGIN_GO_TO_SIGNUP);
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
