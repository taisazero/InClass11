package com.example.zero.inclass11.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.zero.inclass11.Contact;
import com.example.zero.inclass11.R;
import com.example.zero.inclass11.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;

/**
 * @author Josiah Laivins
 * @version 10/23/2017
 */

public class SignUpFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public static boolean flagCancel = false;
    public static boolean flagSignUp = false;
    private static final int CAMERA_CODE = 1 ;
    public static byte[] byteArray;
    public static Bitmap picture;
    public static Button btnCancel;
    public static Button btnSignUp;
    public static EditText editFirstName;
    public static EditText editLastName;
    public static EditText editEmail;
    public static EditText editPassword;
    public static EditText editPasswordRepeat;
    private FirebaseAuth mAuth;
    private ImageButton btnProfilePic;
    private DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Users");


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
        btnProfilePic=getView().findViewById(R.id.btnProfilePic);

        btnProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (i.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(i, CAMERA_CODE);
                }
            }
        });


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
                                        User u = new User(editFirstName.getText().toString(),editLastName.getText().toString(),editEmail.getText().toString()
                                        ,byteArray);
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        String userID=databaseReference.push().getKey();
                                        u.setId(user.getUid());

                                        databaseReference.child(user.getUid()).setValue(u);

                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("JOSIAH FARGMENT COOL", "createUserWithEmail:success");

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_CODE) {
                Bitmap image = (Bitmap) data.getExtras().get("data");//from Camera
                image = image.createScaledBitmap(image, 750, 750, false);
                btnProfilePic.setImageBitmap(image);
                //   ImageSaver.saveImage(userNameSignUp.getText().toString(),image,(MainActivity) (getActivity()));



                if (image != null) {
                    picture=image;
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.PNG, 10, stream);
                    byteArray = stream.toByteArray();



                }

            }
        }
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
