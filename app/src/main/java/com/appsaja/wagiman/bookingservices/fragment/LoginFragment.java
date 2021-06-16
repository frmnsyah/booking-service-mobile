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
import android.widget.Toast;

import com.appsaja.wagiman.bookingservices.App;
import com.appsaja.wagiman.bookingservices.HomeActivity;
import com.appsaja.wagiman.bookingservices.R;
import com.appsaja.wagiman.bookingservices.RetrofitApi;
import com.appsaja.wagiman.bookingservices.data.response.BaseResponse;
import com.appsaja.wagiman.bookingservices.data.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    View loginView;
    Button btnLogin;
    LayoutInflater inflat;
    ProgressDialog progress;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflat = inflater;
        loginView = inflater.inflate(R.layout.fragment_login, container, false);
        btnLogin = loginView.findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = new ProgressDialog(v.getContext());
                progress.setTitle("Loading");
                progress.setMessage("Wait while loading...");
                progress.setCancelable(false);
                progress.show();
                sendLogin(loginView);
            }
        });
        return loginView;
    }

    public void sendLogin(View registerView) {
        EditText et_email = registerView.findViewById(R.id.et_email);
        String email = et_email.getText().toString();
        EditText et_password = registerView.findViewById(R.id.et_password);
        String password = et_password.getText().toString();
        RetrofitApi retrofitApi = App.getRetrofit().create(RetrofitApi.class);
        Call<LoginResponse> call = retrofitApi.postLogin(
                email,
                password
        );
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progress.dismiss();
                if (response.body().getCustomerId() != 0) {
                    SharedPreferences settings = inflat.getContext().getSharedPreferences("session", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putInt("user_id", response.body().getCustomerId());
                    editor.putString("nama", response.body().getNama());
                    editor.putString("alamat", response.body().getAlamat());
                    editor.putString("no_hp", response.body().getNoHp());
                    editor.apply();
                    Intent secondActivityIntent = new Intent(inflat.getContext(), HomeActivity.class);
                    startActivity(secondActivityIntent);
                } else {
                    Toast.makeText(inflat.getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(inflat.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}