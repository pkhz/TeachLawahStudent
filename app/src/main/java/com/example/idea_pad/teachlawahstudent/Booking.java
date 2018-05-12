package com.example.idea_pad.teachlawahstudent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * A simple {@link Fragment} subclass.
 */
public class Booking extends Fragment {

    DatabaseReference mFirebaseDatabase;
    private String userId;

    //DatabaseReference mFirebaseDatabase2 = FirebaseDatabase.getInstance().getReference(userID).child("makeclass");
    //FirebaseHelper helper; get db
    BookingAdapter adapter;
    //private FirebaseRecyclerAdapter<YourClassListData, YourClassListViewHolder> adapter2;
    RecyclerView rv;
    //EditText nameEditTxt,propTxt,descTxt;
    TextView txtDetails;

    ArrayList<BookingData> classlist=new ArrayList<>();
    List<BookingData> list = new ArrayList<>();


    public Booking() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_booking, container, false);



        //INITIALIZE FB
        mFirebaseDatabase= FirebaseDatabase.getInstance().getReference();

        //ADAPTER
        try {
            adapter=new BookingAdapter(getActivity(),retrieve()); }
        catch(Exception e){
            Toast.makeText(getActivity(), "Sign in first!",
                    Toast.LENGTH_LONG).show();
        }

        //String name = inputName.getText().toString();
        //String venue = inputFeed.getText().toString();

        //createListClasses(name, venue);

        //INITIALIZE RV
        rv= (RecyclerView) v.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        rv.setAdapter(adapter);

        return v;
    }


    public ArrayList<BookingData> retrieve()
    {
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = currentFirebaseUser.getUid();
        DatabaseReference mFirebaseDatabase2 = FirebaseDatabase.getInstance().getReference().child("user - students").child(userId).child("bookings");

        mFirebaseDatabase2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return classlist;
    }

    private void fetchData(DataSnapshot dataSnapshot)
    {


        /* for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            BookingData list = dataSnapshot.getValue(BookingData.class);
            classlist.add(list);



            //BookingData classlists=dataSnapshot.getValue(BookingData.class);


            //classlist.add(classlists);
            //Toast.makeText()
            //String name = dataSnapshot.child("name").getValue(String.class);
            //txtDetails.setText(name);
            //Log.e(TAG, "Name: " + name);
        }*/

        BookingData list = dataSnapshot.getValue(BookingData.class);
        classlist.add(list);

        String name = dataSnapshot.child("name").getValue(String.class);
        String venue = dataSnapshot.child("venue").getValue(String.class);
        String contact = dataSnapshot.child("contact").getValue(String.class);
        String time = dataSnapshot.child("time").getValue(String.class);
        String date = dataSnapshot.child("date").getValue(String.class);
        //YourClassListData classlists=dataSnapshot.getValue(YourClassListData.class);
        //txtDetails.setText(name);
        //ListsData cd = dataSnapshot.getValue(ListsData.class);
        //classlist.add(cd);


        //Log.e(TAG, "Name: " + classlist.toString());



        //createListClasses(name, venue, contact, time, date);



        //txtDetails.setText(cd);


    }



}
