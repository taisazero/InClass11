package com.example.zero.inclass11.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zero.inclass11.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * @author Josiah Laivins
 * @version 10/23/2017
 */

public class SignUpFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public static boolean flagCancel = false;
    public static boolean flagSignUp = false;

    public static Button btnCancel;
    public static Button btnSignUp;
    public static EditText editFirstName;
    public static EditText editLastName;
    public static EditText editEmail;
    public static EditText editPassword;
    public static EditText editPasswordRepeat;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        editFirstName = getView().findViewById(R.id.editFirstName);
        editLastName = getView().findViewById(R.id.editLastName);
        editEmail = getView().findViewById(R.id.editUserNameSignUp);
        editPassword = getView().findViewById(R.id.editPasswordSignUp);
        editPasswordRepeat = getView().findViewById(R.id.editPasswordRepeat);

        btnCancel = getView().findViewById(R.id.btnCancel);
        btnSignUp = getView().findViewById(R.id.btnSignup);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flagSignUp) {
                    flagCancel = true;
                    mListener.gotoNextFragment(FragmentAction.FROM_CANCEL_GO_TO_LOGIN);
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flagCancel) {
                    flagSignUp = true;
                    mAuth.createUserWithEmailAndPassword(editEmail.getText().toString(), editPassword.getText().toString())
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("JOSIAH FARGMENT COOL", "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        updateUI(user);
                                        mListener.gotoNextFragment(FragmentAction.FROM_SIGN_UP_GO_TO_LOGIN);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("JOSIAH FRAGEMENT COOL", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(getActivity(), "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });



                }
            }
        });
        mAuth = FirebaseAuth.getInstance();




    }

    private void updateUI(FirebaseUser user) {
        //ToDo: Josiah add move to login fragment
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
