package com.example.habari.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.habari.R;

import java.security.Permission;
import java.util.Objects;

public class DialFragment extends Fragment implements View.OnClickListener {
    private View view;
    private TextView display;

    public DialFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dial, container, false);
        display = view.findViewById(R.id.dial_display);

        view.findViewById(R.id.dial_erase).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!display.getText().toString().equals("")) {
                    display.setText(null);
                }

                return true;
            }
        });
        dialPad(view);
        return view;
    }

    private void dialPad(View view) {
        view.findViewById(R.id.one).setOnClickListener(this);
        view.findViewById(R.id.two).setOnClickListener(this);
        view.findViewById(R.id.three).setOnClickListener(this);
        view.findViewById(R.id.four).setOnClickListener(this);
        view.findViewById(R.id.five).setOnClickListener(this);
        view.findViewById(R.id.six).setOnClickListener(this);
        view.findViewById(R.id.seven).setOnClickListener(this);
        view.findViewById(R.id.eight).setOnClickListener(this);
        view.findViewById(R.id.nine).setOnClickListener(this);
        view.findViewById(R.id.zero).setOnClickListener(this);
        view.findViewById(R.id.star).setOnClickListener(this);
        view.findViewById(R.id.dial_call).setOnClickListener(this);
        view.findViewById(R.id.hash).setOnClickListener(this);
        view.findViewById(R.id.dial_erase).setOnClickListener(this);
        view.findViewById(R.id.dial_options).setOnClickListener(this);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one:
                display.setText(display.getText() + "1");
                break;
            case R.id.two:
                display.setText(display.getText() + "2");
                break;
            case R.id.three:
                display.setText(display.getText() + "3");
                break;
            case R.id.four:
                display.setText(display.getText() + "4");
                break;
            case R.id.five:
                display.setText(display.getText() + "5");
                break;
            case R.id.six:
                display.setText(display.getText() + "6");
                break;
            case R.id.seven:
                display.setText(display.getText() + "7");
                break;
            case R.id.eight:
                display.setText(display.getText() + "8");
                break;
            case R.id.nine:
                display.setText(display.getText() + "9");
                break;
            case R.id.zero:
                display.setText(display.getText() + "0");
                break;
            case R.id.star:
                display.setText(display.getText() + "*");
                break;
            case R.id.hash:
                display.setText(display.getText() + "#");
                break;
            case R.id.dial_call:
                String number = display.getText().toString();
                runCall(number);
                break;
            case R.id.dial_erase:
                if (!display.getText().toString().equals("")) {
                    display.setText(display.getText().subSequence(0, display.getText().length() - 1));
                }

                break;
            case R.id.dial_options:

                break;
        }
    }

    public void runCall(String number) {
        if (number.trim().length() > 0) {
            //First Check if the App has permissions to Call Phone.
            if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                //...if Not, Request Permission from the User.
                ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[]{Manifest.permission.CALL_PHONE}, 2);
            } else {
                //Check if the value Entered is Ussd.
                if (number.startsWith("*") && number.endsWith("#")) {
                    number = number.substring(0, number.length() - 1); //We eliminate the last hash(#) in the value.
                    String ussdCode = number + Uri.encode("#"); //We encode our own hash(#) onto the value
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + ussdCode))); //We run the USSD code.
                } else {
                    //We call the Number
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + display.getText().toString())));
                }

            }
        } else {
            Toast.makeText(getContext(), "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }
}
