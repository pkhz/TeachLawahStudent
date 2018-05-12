package com.example.idea_pad.teachlawahstudent;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Idea-Pad on 8/5/2018.
 */

public class ListsAdapter extends RecyclerView.Adapter<ListsViewHolder>{

    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
    private String userId;
    DatabaseReference mFirebaseDatabase;

    Context c;
    ArrayList<ListsData> classlist;

    //SwipeRefreshLayout swiper;

    public ListsAdapter(Context c, ArrayList<ListsData> classlist) {
        this.c = c;
        this.classlist = classlist;
    }


    @Override
    public ListsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.model_lists,parent,false);
        return new ListsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListsViewHolder holder, final int position) {
        holder.nameTxt.setText(classlist.get(position).getName());
        holder.venueTxt.setText(classlist.get(position).getVenue());
        holder.contactTxt.setText(classlist.get(position).getContact());
        holder.timeTxt.setText(classlist.get(position).getTime());
        holder.dateTxt.setText(classlist.get(position).getDate());

        holder.btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add etails to MovieDetails object
                // m = new MovieDetails(e1.getText().toString(), e2.getText().toString(), e3.getText().toString());
                //push object to database to add in a new node
                String name = classlist.get(position).getName();
                String venue = classlist.get(position).getVenue();
                String contact = classlist.get(position).getContact();
                String time = classlist.get(position).getTime();
                String date = classlist.get(position).getDate();

                ListsData cd = new ListsData(name, venue, contact, time, date);

                userId = currentFirebaseUser.getUid();
                mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("user - students").child(userId);
                mFirebaseDatabase.child("bookings").push().setValue(cd);
                //Toast.makeText(getApplicationContext(), "Booked", Toast.LENGTH_SHORT).show();
                //finish();
            }
        });
        //holder.descTxt.setText(spacecrafts.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return classlist.size();}
}
