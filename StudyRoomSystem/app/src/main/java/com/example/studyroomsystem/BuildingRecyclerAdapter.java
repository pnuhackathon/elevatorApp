package com.example.studyroomsystem;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;


class BuildingCardData {
    public String text;
    public int img;

    public BuildingCardData(String text, int img) {
        this.text = text;
        this.img = img;
    }
}

public class BuildingRecyclerAdapter extends RecyclerView.Adapter<BuildingRecyclerAdapter.ViewHolder> {
    private ArrayList<BuildingCardData> mDataset;
    Calendar c = Calendar.getInstance();
    int cyear = c.get(Calendar.YEAR);
    int cmonth = c.get(Calendar.MONTH) + 1;
    final int cday = c.get(Calendar.DAY_OF_MONTH);

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageCard;
        public TextView textCard;



        public ViewHolder(View v) {
            super(v);
            imageCard = (ImageView) v.findViewById(R.id.imageCard);
            textCard = (TextView) v.findViewById(R.id.textCard);
        }
    }


    public boolean CanReserve(String dates, String dates2) {
        if (dates == null || dates.equals("")) return true;
        String date[] = new String[5];
        String date2[] = new String[5];
        date = dates.split(" ");
        date2 = dates2.split(" ");
        if(Integer.parseInt(date[2])<=cday && Integer.parseInt(date2[2])>=cday) {
            return false;
        }
        return true;
    }

    public BuildingRecyclerAdapter(ArrayList<BuildingCardData> dataset) {
        mDataset = dataset;
    }

    @Override
    public BuildingRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.imageCard.setImageResource(mDataset.get(position).img);
        final String text = mDataset.get(position).text;
        holder.textCard.setText(text);

        DatabaseReference myRef;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("building").child(text);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = 0;
                for(DataSnapshot item : dataSnapshot.getChildren()) {
                    String dates = item.child("noday").getValue(String.class);
                    String dates2 = item.child("noday2").getValue(String.class);
                    if(item.child("capacity").getValue(Integer.class) != item.child("current").getValue(Integer.class) && CanReserve(dates, dates2))
                        count++;
                }
                holder.textCard.setText(text + "                                       예약 가능한 강의실 " + String.valueOf(count));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        holder.imageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(), position + "Select", Toast.LENGTH_SHORT).show();

                // 인텐트 : 선택시 해당 학습공간 액티비티로 이동
                Context context = v.getContext();
                Intent in = new Intent(context, StudyRoomActivity.class);
                in.putExtra("BuildingPosition",position);

                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
