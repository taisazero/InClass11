package com.example.zero.inclass11.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zero.inclass11.Contact;
import com.example.zero.inclass11.ContactAdapter;
import com.example.zero.inclass11.R;

public class ContactsFragment extends Fragment {

    public static final String TAG = "onActivityCreated";
    private OnFragmentInteractionListener mListener;
    private RecyclerView listContacts;
    private LinearLayoutManager linearLayoutManager;
    public ContactAdapter contactAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listContacts= getActivity().findViewById(R.id.listContacts);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        contactAdapter = new ContactAdapter(Contact.contacts, new ContactAdapter.OnItemClickListener() {
            @Override
            public void setOnClickDeleteListener(Contact contact, int position) {
                Log.d(TAG, "setOnClickDeleteListener: contacts is: " + Contact.contacts.toString());
                contactAdapter.contacts.remove(position);
                contactAdapter.notifyDataSetChanged();
            }

            @Override
            public void setOnClickEditListener(Contact contact, int position) {
                mListener.gotoNextFragment(FragmentAction.FROM_CONTACTS_GO_TO_EDIT_CONTACT);
            }
        });

        contactAdapter.notifyDataSetChanged();
        listContacts.setLayoutManager(linearLayoutManager);
        listContacts.setAdapter(contactAdapter);
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
