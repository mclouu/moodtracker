package com.romain.mathieu.moodtracker;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * Created by Romain on 03/02/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.HistoryViewHolder> {

    private List<MoodData> mdatas;


    MyAdapter(List<MoodData> datas) {
        this.mdatas = datas;
    }


    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new HistoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {

        // Date
        holder.textViewTime.setText(mdatas.get(position).time);

        // Couleur de la carte
        int colorId = mdatas.get(position).colorCard;
        int color = holder.cardView.getContext().getResources().getColor(colorId);
        holder.cardView.setCardBackgroundColor(color);

        // Largeur de la carte
        holder.cardView.getLayoutParams().width = (int) mdatas.get(position).sizeCard;
        holder.cardView.requestLayout();

        // disable l'icons message si aucun message
        if (mdatas.get(position).message.isEmpty()) {
            holder.messageIcons.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        if (mdatas == null) {
            return 0;
        } else {
            return this.mdatas.size();
        }


    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView textViewTime;
        ImageButton messageIcons;


        HistoryViewHolder(final View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);

            textViewTime = itemView.findViewById(R.id.textview_time);
            messageIcons = itemView.findViewById(R.id.message_icons);


            messageIcons.setClickable(true);


            messageIcons.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    Toast.makeText(itemView.getContext(), mdatas.get(position).message, Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}
