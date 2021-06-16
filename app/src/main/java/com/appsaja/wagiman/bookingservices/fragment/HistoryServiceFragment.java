package com.appsaja.wagiman.bookingservices.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appsaja.wagiman.bookingservices.App;
import com.appsaja.wagiman.bookingservices.DetailServiceActivity;
import com.appsaja.wagiman.bookingservices.R;
import com.appsaja.wagiman.bookingservices.RetrofitApi;
import com.appsaja.wagiman.bookingservices.adapter.RecyclerViewAdapterBooking;
import com.appsaja.wagiman.bookingservices.adapter.SpaceItem;
import com.appsaja.wagiman.bookingservices.data.response.BookingListResponse;
import com.appsaja.wagiman.bookingservices.implement.ItemClickListener;
import com.appsaja.wagiman.bookingservices.model.Booking;
import com.appsaja.wagiman.bookingservices.model.Movie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryServiceFragment extends Fragment implements ItemClickListener {

    View bookingHistoryView;
    RecyclerViewAdapterBooking rAdapter;
    RecyclerView rView;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    List<Movie> movieList = new ArrayList<>();
    List<Booking> dataBookings = new ArrayList<>();
    ProgressDialog progress;

    public HistoryServiceFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bookingHistoryView = inflater.inflate(R.layout.main_layout, container, false);
        progress = new ProgressDialog(bookingHistoryView.getContext());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        rView = bookingHistoryView.findViewById(R.id.recycler);
        getBookingList();
        new MyProgressBar().execute((Void) null);
        return bookingHistoryView;
    }

    public void getBookingList() {
        rAdapter = new RecyclerViewAdapterBooking(dataBookings, getActivity().getApplicationContext());
        rAdapter.setClick(this);
        rView.setHasFixedSize(true);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        rView.setLayoutManager(staggeredGridLayoutManager);
        rView.setAdapter(rAdapter);

        SpaceItem spaceItem = new SpaceItem(1);
        rView.addItemDecoration(spaceItem);
        RetrofitApi retrofitApi = App.getRetrofit().create(RetrofitApi.class);
        SharedPreferences myPrefs = bookingHistoryView.getContext().getSharedPreferences("session", Context.MODE_PRIVATE);
        Integer custId = myPrefs.getInt("user_id", 0);
        Call<BookingListResponse> call = retrofitApi.getBookingList(custId.toString());
        call.enqueue(new Callback<BookingListResponse>() {
            @Override
            public void onResponse(Call<BookingListResponse> call, Response<BookingListResponse> response) {
                dataBookings.addAll(response.body().getDataBookingArrayList());
                rAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<BookingListResponse> call, Throwable t) {
                Log.e("ERROT API", t.toString());
            }
        });
    }

    @Override
    public void onClick(View v, int pos) {
        if (!dataBookings.get(pos).getStatus().equals("menunggu diproses")) {
            Intent intent = new Intent(getActivity(), DetailServiceActivity.class);
            intent.putExtra(App.TAG_ID_SERVICE, dataBookings.get(pos).getId());
            startActivity(intent);
        }
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
