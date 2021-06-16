package com.appsaja.wagiman.bookingservices.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
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
import com.appsaja.wagiman.bookingservices.R;
import com.appsaja.wagiman.bookingservices.RetrofitApi;
import com.appsaja.wagiman.bookingservices.data.response.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {

    View registerView;
    Button btnRegister;
    LayoutInflater inflat;
    ProgressDialog progress;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflat = inflater;
        registerView = inflater.inflate(R.layout.fragment_register, container, false);
        btnRegister = registerView.findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = new ProgressDialog(v.getContext());
                progress.setTitle("Loading");
                progress.setMessage("Wait while loading...");
                progress.setCancelable(false);
                progress.show();
                sendRegistration(registerView);
            }
        });
        return registerView;
    }

    public void sendRegistration(View registerView){
        EditText et_email = registerView.findViewById(R.id.et_email);
        String email = et_email.getText().toString();
        EditText et_name = registerView.findViewById(R.id.et_name);
        String name = et_name.getText().toString();
        EditText et_password = registerView.findViewById(R.id.et_password);
        String password = et_password.getText().toString();
        EditText et_address = registerView.findViewById(R.id.et_address);
        String address = et_address.getText().toString();
        EditText et_no_hp = registerView.findViewById(R.id.et_no_hp);
        String no_hp = et_no_hp.getText().toString();
        RetrofitApi retrofitApi = App.getRetrofit().create(RetrofitApi.class);
        Call<BaseResponse> call = retrofitApi.postRegistration(
                name,
                email,
                address,
                no_hp,
                password
        );
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                progress.dismiss();
                Toast.makeText(inflat.getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(inflat.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}