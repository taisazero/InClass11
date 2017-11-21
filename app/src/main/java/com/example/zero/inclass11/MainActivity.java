package com.example.zero.inclass11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zero.inclass11.fragments.FragmentAction;
import com.example.zero.inclass11.fragments.LoginFragment;
import com.example.zero.inclass11.fragments.OnFragmentInteractionListener;
import com.example.zero.inclass11.fragments.SignUpFragment;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction()
                .replace(R.id.container, new LoginFragment(), "loginFragment")
                .addToBackStack(null)
                .commit();
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
