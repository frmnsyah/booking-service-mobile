package com.appsaja.wagiman.bookingservices.fragment;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.appsaja.wagiman.bookingservices.App;
import com.appsaja.wagiman.bookingservices.R;
import com.appsaja.wagiman.bookingservices.RetrofitApi;
import com.appsaja.wagiman.bookingservices.data.response.BaseResponse;
import com.appsaja.wagiman.bookingservices.data.response.DataLookupResponse;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingFragment extends Fragment {

    View bookingView;
    EditText bookingDate;
    EditText bookingTime;
    DatePickerDialog datePicker;
    TimePickerDialog timePicker;
    RecyclerView rView;
    LayoutInflater inflat;
    ProgressDialog progress;

    public BookingFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflat = inflater;
        bookingView = inflater.inflate(R.layout.add_booking, container, false);
        progress = new ProgressDialog(getActivity());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(true);
        progress.show();
        rView = bookingView.findViewById(R.id.recycler);
        bookingDate = (EditText) bookingView.findViewById(R.id.bookind_date);
        bookingDate.setInputType(InputType.TYPE_NULL);
        bookingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datePicker = new DatePickerDialog(bookingView.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                bookingDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePicker.show();
            }
        });

        bookingTime = (EditText) bookingView.findViewById(R.id.bookind_time);
        bookingTime.setInputType(InputType.TYPE_NULL);
        bookingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hours = cldr.get(Calendar.HOUR);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                timePicker = new TimePickerDialog(bookingView.getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                bookingTime.setText(hourOfDay + ":" + minute);
                            }
                        }, hours, minutes, false);
                timePicker.show();
            }
        });

        Button btnBooking = bookingView.findViewById(R.id.btn_booking);
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBooking(bookingView);
            }
        });

        getCategories();
        getTypeMobil();
        getJenisMobil();
        getLokasiService();
        new MyProgressBar().execute((Void) null);
        return bookingView;
    }

    public void getTypeMobil() {
        RetrofitApi retrofitApi = App.getRetrofit().create(RetrofitApi.class);
        Call<DataLookupResponse> call = retrofitApi.getTypeMobil();
        call.enqueue(new Callback<DataLookupResponse>() {
            @Override
            public void onResponse(Call<DataLookupResponse> call, Response<DataLookupResponse> response) {
                if (!response.body().getSpinnerModalClassArrayList().isEmpty()) {
                    Log.d("JSON  LIST", response.body().getSpinnerModalClassArrayList().toString());
                    Spinner dropdown = bookingView.findViewById(R.id.booking_type_mobil);
                    ArrayList<DataLookupResponse.DataLookup> spinnerArrayList = response.body().getSpinnerModalClassArrayList();
                    ArrayList<String> itemList = new ArrayList<>();
                    for (int l = 0; l < spinnerArrayList.size(); l++) {
                        itemList.add(spinnerArrayList.get(l).getValues());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(bookingView.getContext(), android.R.layout.simple_spinner_dropdown_item, itemList);
                    dropdown.setAdapter(adapter);
                } else {
                    Toast.makeText(bookingView.getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DataLookupResponse> call, Throwable t) {
                Toast.makeText(bookingView.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getJenisMobil() {
        RetrofitApi retrofitApi = App.getRetrofit().create(RetrofitApi.class);
        Call<DataLookupResponse> call = retrofitApi.getJenisMobil();
        call.enqueue(new Callback<DataLookupResponse>() {
            @Override
            public void onResponse(Call<DataLookupResponse> call, Response<DataLookupResponse> response) {
                if (!response.body().getSpinnerModalClassArrayList().isEmpty()) {
                    Log.d("JSON  LIST", response.body().getSpinnerModalClassArrayList().toString());
                    Spinner dropdown = bookingView.findViewById(R.id.booking_jenis_mobil);
                    ArrayList<DataLookupResponse.DataLookup> spinnerArrayList = response.body().getSpinnerModalClassArrayList();
                    ArrayList<String> itemList = new ArrayList<>();
                    for (int l = 0; l < spinnerArrayList.size(); l++) {
                        itemList.add(spinnerArrayList.get(l).getValues());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(bookingView.getContext(), android.R.layout.simple_spinner_dropdown_item, itemList);
                    dropdown.setAdapter(adapter);
                } else {
                    Toast.makeText(bookingView.getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DataLookupResponse> call, Throwable t) {
                Toast.makeText(bookingView.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getLokasiService() {
        RetrofitApi retrofitApi = App.getRetrofit().create(RetrofitApi.class);
        Call<DataLookupResponse> call = retrofitApi.getLokasi();
        call.enqueue(new Callback<DataLookupResponse>() {
            @Override
            public void onResponse(Call<DataLookupResponse> call, Response<DataLookupResponse> response) {
                if (!response.body().getSpinnerModalClassArrayList().isEmpty()) {
                    Log.d("JSON  LIST", response.body().getSpinnerModalClassArrayList().toString());
                    Spinner dropdown = bookingView.findViewById(R.id.booking_lokasi_service);
                    ArrayList<DataLookupResponse.DataLookup> spinnerArrayList = response.body().getSpinnerModalClassArrayList();
                    ArrayList<String> itemList = new ArrayList<>();
                    for (int l = 0; l < spinnerArrayList.size(); l++) {
                        itemList.add(spinnerArrayList.get(l).getValues());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(bookingView.getContext(), android.R.layout.simple_spinner_dropdown_item, itemList);
                    dropdown.setAdapter(adapter);
                } else {
                    Toast.makeText(bookingView.getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DataLookupResponse> call, Throwable t) {
                Toast.makeText(bookingView.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getCategories() {
        RetrofitApi retrofitApi = App.getRetrofit().create(RetrofitApi.class);
        Call<DataLookupResponse> call = retrofitApi.getKategori();
        call.enqueue(new Callback<DataLookupResponse>() {
            @Override
            public void onResponse(Call<DataLookupResponse> call, Response<DataLookupResponse> response) {
                if (!response.body().getSpinnerModalClassArrayList().isEmpty()) {
                    Log.d("JSON  LIST", response.body().getSpinnerModalClassArrayList().toString());
                    Spinner dropdown = bookingView.findViewById(R.id.booking_category);
                    ArrayList<DataLookupResponse.DataLookup> spinnerArrayList = response.body().getSpinnerModalClassArrayList();
                    ArrayList<String> itemList = new ArrayList<>();
                    for (int l = 0; l < spinnerArrayList.size(); l++) {
                        itemList.add(spinnerArrayList.get(l).getValues());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(bookingView.getContext(), android.R.layout.simple_spinner_dropdown_item, itemList);
                    dropdown.setAdapter(adapter);
                } else {
                    Toast.makeText(bookingView.getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DataLookupResponse> call, Throwable t) {
                Toast.makeText(bookingView.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void sendBooking(View bookingView) {
        EditText etDate = bookingView.findViewById(R.id.bookind_date);
        EditText etTime = bookingView.findViewById(R.id.bookind_time);
        EditText etYear = bookingView.findViewById(R.id.booking_tahun_mobil);
        Spinner spCategory = bookingView.findViewById(R.id.booking_category);
        Spinner spType = bookingView.findViewById(R.id.booking_type_mobil);
        Spinner spJenis = bookingView.findViewById(R.id.booking_jenis_mobil);
        Spinner spLokasi = bookingView.findViewById(R.id.booking_lokasi_service);

        String dateVal = etDate.getText().toString();
        String timeVal = etTime.getText().toString();
        String yearVal = etYear.getText().toString();
        String categoryVal = spCategory.getSelectedItem().toString();
        String typeVal = spType.getSelectedItem().toString();
        String jenisVal = spJenis.getSelectedItem().toString();
        String lokasiVal = spLokasi.getSelectedItem().toString();
        SharedPreferences myPrefs = bookingView.getContext().getSharedPreferences("session", Context.MODE_PRIVATE);
        Integer custId = myPrefs.getInt("user_id", 0);
        RetrofitApi retrofitApi = App.getRetrofit().create(RetrofitApi.class);
        Call<BaseResponse> call = retrofitApi.postBooking(
                custId.toString(),
                dateVal,
                timeVal,
                categoryVal,
                typeVal,
                jenisVal,
                lokasiVal,
                yearVal
        );
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body().getSuccess()) {
                    Toast.makeText(inflat.getContext(), "Booking berhasil", Toast.LENGTH_LONG).show();
                    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("History Service");
                    Fragment fragment = new HistoryServiceFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
                } else {
                    Toast.makeText(inflat.getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(inflat.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
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
