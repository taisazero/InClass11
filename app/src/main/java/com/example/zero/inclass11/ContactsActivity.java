package com.example.zero.inclass11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zero.inclass11.fragments.ContactsFragment;
import com.example.zero.inclass11.fragments.FragmentAction;
import com.example.zero.inclass11.fragments.OnFragmentInteractionListener;

public class ContactsActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        getFragmentManager().beginTransaction()
                .replace(R.id.container, new ContactsFragment(), "contactsFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoNextFragment(FragmentAction action) {
        if (action == FragmentAction.FROM_CONTACTS_GO_TO_CREATE_CONTACT) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new ContactsFragment(), "contactsFragment")
                    .addToBackStack(null)
                    .commit();
        } else if (action == FragmentAction.FROM_CONTACTS_GO_TO_EDIT_OWN_CONTACT) {

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
