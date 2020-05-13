package com.example.habari.Adapters;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habari.Fragments.DialFragment;
import com.example.habari.Models.Contacts;
import com.example.habari.R;

import java.util.List;
import java.util.Objects;

public class ContactsRecyclerAdapter extends RecyclerView.Adapter<ContactsRecyclerAdapter.ContactsViewHolder> {
    private Context mContext;
    private List<Contacts> contactsList;
    private Dialog contactsDialog;

    public ContactsRecyclerAdapter(Context mContext, List<Contacts> contactsList) {
        this.mContext = mContext;
        this.contactsList = contactsList;
    }

    @NonNull
    @Override

    public ContactsViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_contacts, parent, false);
        final ContactsViewHolder viewHolder = new ContactsViewHolder(v);

        contactsDialog = new Dialog(mContext);
        contactsDialog.setContentView(R.layout.dialog_contacts);

        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView dialog_name = contactsDialog.findViewById(R.id.dialog_contact_name);
                final TextView dialog_phone = contactsDialog.findViewById(R.id.dialog_contact_phone);
                Button dialog_call = contactsDialog.findViewById(R.id.dialog_call);

                dialog_name.setText(contactsList.get(viewHolder.getLayoutPosition()).getName());
                dialog_phone.setText(contactsList.get(viewHolder.getLayoutPosition()).getPhoneNumber());

                dialog_call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //First Check if the App has permissions to Call Phone.
                        if (ContextCompat.checkSelfPermission(Objects.requireNonNull(mContext), Manifest.permission.CALL_PHONE)
                                != PackageManager.PERMISSION_GRANTED) {
                            //...if Not, Request Permission from the User.
                            ActivityCompat.requestPermissions((Activity) Objects.requireNonNull(mContext), new String[]{Manifest.permission.CALL_PHONE}, 2);
                        } else {
                            mContext.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + dialog_phone.getText().toString())));
                        }

                    }
                });
                contactsDialog.show();
            }
        });
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

    static class ContactsViewHolder extends RecyclerView.ViewHolder {
        TextView name, phoneNumber;
        LinearLayout layout;

        ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.contact_items_layout);
            name = itemView.findViewById(R.id.contact_name);
            phoneNumber = itemView.findViewById(R.id.contact_phone);
        }
    }
}
