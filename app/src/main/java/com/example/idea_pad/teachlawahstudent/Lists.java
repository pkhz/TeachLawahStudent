package com.example.idea_pad.teachlawahstudent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Lists extends Fragment {

    DatabaseReference mFirebaseDatabase;
    private String userId;

    //DatabaseReference mFirebaseDatabase2 = FirebaseDatabase.getInstance().getReference(userID).child("makeclass");
    //FirebaseHelper helper; get db
    ListsAdapter adapter;
    //private FirebaseRecyclerAdapter<YourClassListData, YourClassListViewHolder> adapter2;
    RecyclerView rv;
    //EditText nameEditTxt,propTxt,descTxt;
    TextView txtDetails;

    ArrayList<ListsData> classlist=new ArrayList<>();

    //SwipeRefreshLayout swiper;

    public Lists() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lists, container, false);

        //swiper= (SwipeRefreshLayout) v.findViewById(R.id.swiper);

        //INITIALIZE FB
        mFirebaseDatabase= FirebaseDatabase.getInstance().getReference();

        //ADAPTER
        adapter=new ListsAdapter(getActivity(),retrieve());

        //String name = inputName.getText().toString();
        //String venue = inputFeed.getText().toString();

        //createListClasses(name, venue);

        //INITIALIZE RV
        rv= (RecyclerView) v.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        rv.setAdapter(adapter);

        return v;
    }

    /*private void createListClasses(String name, String venue, String contact, String time, String date) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        //FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

        //if (TextUtils.isEmpty(userId)) {
        //userId = mFirebaseDatabase.push().getKey();
        //userId = currentFirebaseUser.getUid();
        //}

        ListsData cd = new ListsData(name, venue, contact, time, date);

        //change other node here
        mFirebaseDatabase.child("listclasses").push().setValue(cd);

        //whhen retrieve how to get key?/just take child()?

        //addUserChangeListener();
    }*/

    //recyclerview


    public ArrayList<ListsData> retrieve()
    {
        //FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //userId = currentFirebaseUser.getUid();
        //DatabaseReference mFirebaseDatabase2 = FirebaseDatabase.getInstance().getReference().child("listclasses");
        DatabaseReference mFirebaseDatabase2 = FirebaseDatabase.getInstance().getReference("listclasses");

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
        //classlist.clear();

        //add(child)
         /*for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            //for(DataSnapshot ds2 : ds.getChildren()) { //second for
                //String name = ds2.child("name").getValue(String.class);
                //Log.d("TAG", name);


                ListsData classlists=dataSnapshot.getValue(ListsData.class);

            //Log.e(TAG, "Name:" )


                classlist.add(classlists);
            //}
            //Toast.makeText()
            //String name = dataSnapshot.child("name").getValue(String.class);
            //txtDetails.setText(name);
            //Log.e(TAG, "Name: " + name);
        }*/
        String key = dataSnapshot.getKey();

        ListsData classlists=dataSnapshot.getValue(ListsData.class);

        //Log.e(TAG, "Name:" )


        classlist.add(classlists);

        String name = dataSnapshot.child("name").getValue(String.class);
        String venue = dataSnapshot.child("venue").getValue(String.class);
        String contact = dataSnapshot.child("contact").getValue(String.class);
        String time = dataSnapshot.child("time").getValue(String.class);
        String date = dataSnapshot.child("date").getValue(String.class);
        //YourClassListData classlists=dataSnapshot.getValue(YourClassListData.class);
        //txtDetails.setText(name);
        //ListsData cd = dataSnapshot.getValue(ListsData.class);
        //classlist.add(cd);


        Log.e("key"," Key: " + key);



        //createListClasses(name, venue, contact, time, date);



        //txtDetails.setText(cd);


    }



}
