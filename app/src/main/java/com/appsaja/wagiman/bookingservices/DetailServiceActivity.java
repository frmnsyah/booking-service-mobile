package com.appsaja.wagiman.bookingservices;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.appsaja.wagiman.bookingservices.data.response.ServiceResponse;
import com.appsaja.wagiman.bookingservices.fragment.HistoryServiceFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailServiceActivity extends AppCompatActivity {

    TextView categoryService;
    TextView tanggal;
    TextView jenisKendaraan;
    TextView tipeKendaraan;
    TextView noPolisi;
    TextView lokasi;
    TextView totalBiaya;
    TextView catatan;
    ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        getSupportActionBar().setTitle("Detail Service");
        progress = new ProgressDialog(DetailServiceActivity.this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        binding();
        new DetailServiceActivity.MyProgressBar().execute((Void) null);
        getResponse();
    }

    private void getResponse(){
        RetrofitApi api = App.getRetrofit().create(RetrofitApi.class);
        Call<ServiceResponse> call = api.getServiceDetail(getIdService());
        call.enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                categoryService.setText(response.body().getData().getBooking().getCategory().getKategori());
                tanggal.setText(response.body().getData().getBooking().getTanggal());
                jenisKendaraan.setText(response.body().getData().getBooking().getJenisMobil());
                tipeKendaraan.setText(response.body().getData().getBooking().getTypeMobil());
                noPolisi.setText(response.body().getData().getNoPolisi() == null ? "" : response.body().getData().getNoPolisi());
                lokasi.setText(response.body().getData().getBooking().getLokasi());
                totalBiaya.setText(response.body().getData().getTotalBiaya() == null ? "" : response.body().getData().getTotalBiaya());
                catatan.setText(response.body().getData().getCatatan() == null ? "" : response.body().getData().getCatatan());
            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {
                Log.e("ERROR API", t.toString());
            }
        });

    }

    private void binding(){
        categoryService = findViewById(R.id.categoryService);
        tanggal = findViewById(R.id.tanggal);
        jenisKendaraan = findViewById(R.id.jenisKendaraan);
        tipeKendaraan = findViewById(R.id.tipeKendaraan);
        noPolisi = findViewById(R.id.noPolisi);
        lokasi = findViewById(R.id.lokasi);
        totalBiaya = findViewById(R.id.totalBiaya);
        catatan = findViewById(R.id.catatan);
    }

    private String getIdService(){
        Intent i = getIntent();
        int id = Integer.parseInt(i.getStringExtra(App.TAG_ID_SERVICE));
        return String.valueOf(id);
    }

    class MyProgressBar extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progress.dismiss();
        }
    }
}
