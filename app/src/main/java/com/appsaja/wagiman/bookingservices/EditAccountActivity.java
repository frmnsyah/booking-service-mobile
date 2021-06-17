package com.appsaja.wagiman.bookingservices;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appsaja.wagiman.bookingservices.data.response.BaseResponse;
import com.appsaja.wagiman.bookingservices.fragment.AccountFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAccountActivity extends AppCompatActivity {

    EditText name;
    EditText telephone;
    EditText address;
    Button btnSimpan;
    ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_account);
        getSupportActionBar().setTitle("Edit Account");
        binding();
        btnSimpan = findViewById(R.id.btn_simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAccount(v);
            }
        });
    }

    private void updateAccount(View v){
        SharedPreferences settings = getApplicationContext().getSharedPreferences("session", 0);
        int userId = settings.getInt("user_id", 0);
        String nama = name.getText().toString();
        String noHp = telephone.getText().toString();
        String alamat = address.getText().toString();

        RetrofitApi retrofitApi = App.getRetrofit().create(RetrofitApi.class);
        Call<BaseResponse> call = retrofitApi.postUpdateAccount(
                userId,
                nama,
                noHp,
                alamat
        );
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body().getSuccess()) {
                    Toast.makeText(getApplicationContext(), "Edit Account Berhasil", Toast.LENGTH_LONG).show();
                    Fragment fragment = new AccountFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
                } else {
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void binding(){
        String nama = getIntent().getStringExtra("NAMA");
        String noHp = getIntent().getStringExtra("NO_HP");
        String alamat = getIntent().getStringExtra("ALAMAT");
        name = findViewById(R.id.nama);
        telephone = findViewById(R.id.no_hp);
        address = findViewById(R.id.alamat);
        name.setText(nama);
        telephone.setText(noHp);
        address.setText(alamat);
    }
}
