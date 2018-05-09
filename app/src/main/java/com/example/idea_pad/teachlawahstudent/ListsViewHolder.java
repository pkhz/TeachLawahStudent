package com.example.idea_pad.teachlawahstudent;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Idea-Pad on 8/5/2018.
 */

public class ListsViewHolder extends RecyclerView.ViewHolder{

    Button btnBook;
    TextView nameTxt,venueTxt,contactTxt, timeTxt, dateTxt;

    public ListsViewHolder(View itemView) {
        super(itemView);

        nameTxt=(TextView) itemView.findViewById(R.id.nameTxt);
        venueTxt=(TextView) itemView.findViewById(R.id.venueTxt);
        contactTxt=(TextView) itemView.findViewById(R.id.contactTxt);
        timeTxt=(TextView) itemView.findViewById(R.id.timeTxt);
        dateTxt=(TextView) itemView.findViewById(R.id.dateTxt);
        btnBook=(Button) itemView.findViewById(R.id.btnBook);

        //descTxt=(TextView) itemView.findViewById(R.id.descTxt);
    }
}
