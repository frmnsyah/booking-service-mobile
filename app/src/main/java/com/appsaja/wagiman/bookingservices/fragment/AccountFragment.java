package com.appsaja.wagiman.bookingservices.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appsaja.wagiman.bookingservices.App;
import com.appsaja.wagiman.bookingservices.EditAccountActivity;
import com.appsaja.wagiman.bookingservices.HomeActivity;
import com.appsaja.wagiman.bookingservices.MainActivity;
import com.appsaja.wagiman.bookingservices.R;
import com.appsaja.wagiman.bookingservices.RetrofitApi;
import com.appsaja.wagiman.bookingservices.data.response.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {

    View accountView;
    Button btnLogout, btnEdit;
    LayoutInflater inflat;
    ProgressDialog progress;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflat = inflater;
        accountView = inflater.inflate(R.layout.account, container, false);
        btnLogout = accountView.findViewById(R.id.btn_logout);
        btnEdit = accountView.findViewById(R.id.btn_edit);
        SharedPreferences settings = inflat.getContext().getSharedPreferences("session", 0);
        String nama = settings.getString("nama", "");
        String noHp = settings.getString("alamat", "");
        String alamat = settings.getString("no_hp", "");
        TextView name = accountView.findViewById(R.id.txt_nama);
        TextView telephone = accountView.findViewById(R.id.txt_no_hp);
        TextView address = accountView.findViewById(R.id.txt_alamat);
        name.setText(nama);
        telephone.setText(noHp);
        address.setText(alamat);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = new ProgressDialog(v.getContext());
                progress.setTitle("Loading");
                progress.setMessage("Wait while loading...");
                progress.setCancelable(false);
                progress.show();
                doLogout();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = new ProgressDialog(v.getContext());
                progress.setTitle("Loading");
                progress.setMessage("Wait while loading...");
                progress.setCancelable(false);
                progress.show();
                editAccount();
            }
        });
        return accountView;
    }

    public void editAccount(){
        Intent secondActivityIntent = new Intent(inflat.getContext(), EditAccountActivity.class);
        SharedPreferences settings = inflat.getContext().getSharedPreferences("session", 0);
        String nama = settings.getString("nama", "");
        String noHp = settings.getString("alamat", "");
        String alamat = settings.getString("no_hp", "");
        secondActivityIntent.putExtra("NAMA", nama);
        secondActivityIntent.putExtra("NO_HP", noHp);
        secondActivityIntent.putExtra("ALAMAT", alamat);
        startActivity(secondActivityIntent);
    }

    public void doLogout(){
        SharedPreferences preferences = inflat.getContext().getSharedPreferences("session", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        Intent secondActivityIntent = new Intent(inflat.getContext(), MainActivity.class);
        startActivity(secondActivityIntent);
    }
}