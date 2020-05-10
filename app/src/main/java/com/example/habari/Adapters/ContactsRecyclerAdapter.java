package com.example.habari.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habari.Models.Contacts;
import com.example.habari.R;

import java.util.List;

public class ContactsRecyclerAdapter extends RecyclerView.Adapter<ContactsRecyclerAdapter.ContactsViewHolder> {
    private Context mContext;
    private List<Contacts> contactsList;

    public ContactsRecyclerAdapter(Context mContext, List<Contacts> contactsList) {
        this.mContext = mContext;
        this.contactsList = contactsList;
    }

    @NonNull
    @Override

    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_contacts, parent, false);
        ContactsViewHolder viewHolder = new ContactsViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        Contacts list = contactsList.get(position);
        holder.name.setText(list.getName());
        holder.phoneNumber.setText(list.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public static class ContactsViewHolder extends RecyclerView.ViewHolder {
        public TextView name, phoneNumber;

        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.contact_name);
            phoneNumber = itemView.findViewById(R.id.contact_phone);
        }
    }
}
