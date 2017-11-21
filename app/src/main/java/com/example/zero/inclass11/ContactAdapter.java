package com.example.zero.inclass11;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    public static final String TAG = "ContactAdapter:onBindViewHolder";
    private ArrayList<Contact> contacts;
    public OnItemClickListener listener;

    public ContactAdapter(ArrayList<Contact> contacts, OnItemClickListener listener) {
        this.contacts = contacts;
        this.listener = listener;
    }

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_contact, parent, false);

        return new ViewHolder(constraintLayout);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder holder, int position) {

        final Contact contact = this.contacts.get(position);

        holder.bind(contact, position, listener);
        holder.txtName.setText(contact.getName());
        holder.txtEmail.setText(contact.getEmail());
        holder.txtPhone.setText(contact.getPhone());



    }


    @Override
    public int getItemCount() {
        return this.contacts.size();
    }

    public interface OnItemClickListener {
        void setOnLongClickListener(Contact contact, int position);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    @SuppressWarnings("WeakerAccess")
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ConstraintLayout constraintLayout;
        public TextView txtName;
        public TextView txtEmail;
        public TextView txtPhone;
        public TextView txtDept;
        public ImageView imgAvatar;


        public ViewHolder(ConstraintLayout constraintLayout) {
            super(constraintLayout);
            this.constraintLayout = constraintLayout;/*
            txtName = constraintLayout.findViewById(R.id.txt);
            txtEmail = constraintLayout.findViewById(R.id.txtItemEmail);
            txtPhone = constraintLayout.findViewById(R.id.txtItemPhone);
            txtDept = constraintLayout.findViewById(R.id.txtItemDept);
            imgAvatar = constraintLayout.findViewById(R.id.imgItemAvatar);*/
        }

        public void bind(final Contact contact, final int position,
                         final OnItemClickListener onItemClickListener) {
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.setOnLongClickListener(contact, position);
                }
            });
        }
    }

}