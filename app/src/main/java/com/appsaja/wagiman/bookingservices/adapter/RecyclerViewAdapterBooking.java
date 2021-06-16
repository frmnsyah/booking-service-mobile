package com.appsaja.wagiman.bookingservices.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appsaja.wagiman.bookingservices.R;
import com.appsaja.wagiman.bookingservices.implement.ItemClickListener;
import com.appsaja.wagiman.bookingservices.model.Booking;

import java.util.List;

public class RecyclerViewAdapterBooking extends RecyclerView.Adapter<RecyclerViewAdapterBooking.ViewHolder> {
    List<Booking> bookings;
    Context mContext;
    ItemClickListener itemClickListener;

    public RecyclerViewAdapterBooking(List<Booking> bookings, Context mContext) {
        this.bookings = bookings;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String jenisType = bookings.get(position).getJenisMobil() + " " + bookings.get(position).getTypeMobil();
        holder.category.setText(bookings.get(position).getKategori());
        holder.typeJenis.setText(jenisType);
        holder.tanggal.setText(bookings.get(position).getTanggal());
        holder.status.setText(bookings.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public void setClick(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView category;
        TextView typeJenis;
        TextView jenis;
        TextView tanggal;
        TextView status;

        public ViewHolder(View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.category);
            typeJenis = itemView.findViewById(R.id.type_jenis);
            tanggal = itemView.findViewById(R.id.tanggal);
            status = itemView.findViewById(R.id.status);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition());
        }
    }
}
