package com.example.idea_pad.teachlawahstudent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Idea-Pad on 8/5/2018.
 */

public class BookingAdapter extends RecyclerView.Adapter<BookingViewHolder> {

    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
    private String userId;
    DatabaseReference mFirebaseDatabase;

    Context c;
    ArrayList<BookingData> classlist;

    public BookingAdapter(Context c, ArrayList<BookingData> classlist) {
        this.c = c;
        this.classlist = classlist;
    }


    @Override
    public BookingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.model_booking, parent, false);
        return new BookingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BookingViewHolder holder, final int position) {
        holder.nameTxt.setText(classlist.get(position).getName());
        holder.venueTxt.setText(classlist.get(position).getVenue());
        holder.contactTxt.setText(classlist.get(position).getContact());
        holder.timeTxt.setText(classlist.get(position).getTime());
        holder.dateTxt.setText(classlist.get(position).getDate());
        //holder.descTxt.setText(spacecrafts.get(position).getDescription());

        holder.btnUnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //point databaseReference to Movies
                userId = currentFirebaseUser.getUid();
                mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("user - students").child(userId).child("bookings");
                //remove value of child movie.getKey()
                //mFirebaseDatabase.child(push().getKey()).setValue(null);
                //String key = holder.getRef(position).getKey();
                //String k;

                mFirebaseDatabase.setValue(null); //delete all, how to not delete all?
                //mFirebaseDatabase.removeValue();



                //holder.databaseReference = getRef(position);
                //remove item from list
                classlist.remove(position);
                //notofy datachanged to adapter
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, classlist.size());
                //Toast.makeText(mContext, "Item Deleted", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return classlist.size();
    }


}
