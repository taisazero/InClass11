package com.example.zero.inclass11;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import com.example.zero.inclass11.fragments.FragmentAction;
import com.example.zero.inclass11.fragments.LoginFragment;
import com.example.zero.inclass11.fragments.OnFragmentInteractionListener;
import com.example.zero.inclass11.fragments.SignUpFragment;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener{











    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction()
                .replace(R.id.container, new LoginFragment(), "loginFragment")
                .addToBackStack(null)
                .commit();;

    }

    @Override
    public void gotoNextFragment(FragmentAction action) {

        if (action == FragmentAction.FROM_LOGIN_GO_TO_SIGNUP) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new SignUpFragment(), "signUpFragment")
                    .addToBackStack(null)
                    .commit();
        } else if (action == FragmentAction.FROM_SIGN_UP_GO_TO_LOGIN) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new LoginFragment(), "loginFragment")
                    .addToBackStack(null)
                    .commit();
        } else if (action == FragmentAction.FROM_CANCEL_GO_TO_LOGIN) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new LoginFragment(), "loginFragment")
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        }else {
            super.onBackPressed();
        }
    }


}
