package com.example.zero.inclass11.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.zero.inclass11.Contact;
import com.example.zero.inclass11.R;
import com.example.zero.inclass11.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;


public class CreateContactFragment extends Fragment {

    private static final int CAMERA_CODE = 1 ;
    ImageButton imgBtnProfilePhoto;
    EditText etxtFirstName;
    EditText etxtLastName;
    EditText etxtPhone;
    EditText etxtEmail;
    Button btnSave;
    public static byte[] byteArray;
    public static Bitmap picture;
    private OnFragmentInteractionListener mListener;
    private DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Contacts");
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_contact, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imgBtnProfilePhoto=getView().findViewById(R.id.imgBtnProfilePhoto);
        etxtFirstName=getView().findViewById(R.id.etxtFirstName);
        etxtLastName=getView().findViewById(R.id.etxtLastName);
        etxtPhone=getView().findViewById(R.id.etxtPhone);
        etxtEmail=getView().findViewById(R.id.etxtEmail);
        btnSave=getView().findViewById(R.id.btnSave);
        imgBtnProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (i.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(i, CAMERA_CODE);
                }
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = mAuth.getCurrentUser();
                Contact u = new Contact(etxtFirstName.getText().toString()+" "+etxtLastName.getText().toString(),etxtEmail.getText().toString(),
                        etxtPhone.getText().toString(),byteArray,user.getUid());


                     }
          

            
        

        });
        Button btnCancel = getView().findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoNextFragment(FragmentAction.FROM_CANCEL_CONTACT_GO_TO_CONTACTS);
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
