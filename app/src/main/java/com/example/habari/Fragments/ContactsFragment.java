package com.example.habari.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habari.Adapters.ContactsRecyclerAdapter;
import com.example.habari.Models.Contacts;
import com.example.habari.R;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {
    private RecyclerView contactsRecycler;
    private List<Contacts> contactsList;
    private ContactsRecyclerAdapter recyclerAdapter;
    public static final int REQUEST_CODE = 2;

    public ContactsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        setUpRecyclerView(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contactsList = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE);
        }

        Cursor cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            contactsList.add(new Contacts(name, phoneNumber));
        }
    }

    private void setUpRecyclerView(View view) {
        contactsRecycler = view.findViewById(R.id.recyclerview_contacts_frag);
        recyclerAdapter = new ContactsRecyclerAdapter(getContext(), contactsList);
        contactsRecycler.setHasFixedSize(true);
        contactsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        contactsRecycler.setAdapter(recyclerAdapter);
    }

}
