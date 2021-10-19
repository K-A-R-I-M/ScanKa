package com.util.scanka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChapitreAdapter extends RecyclerView.Adapter<ChapitreAdapter.ChapitreViewHolder> {

    private Context context;
    private ArrayList<Chapitre> listeChapitre;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public class ChapitreViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_livre_chapitre;
        public TextView tv_livre_chapitre;

        public ChapitreViewHolder(@NonNull CardView view){
            super(view);
            iv_livre_chapitre = view.findViewById(R.id.iv_livre_chapitre);
            tv_livre_chapitre = view.findViewById(R.id.tv_livre_chapitre);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!=null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public ChapitreAdapter(Context context, ArrayList<Chapitre> listeChapitre) {
        this.context = context;
        this.listeChapitre = listeChapitre;
    }

    @NonNull
    @Override
    public ChapitreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(context).inflate(R.layout.chapitre_item, parent, false);
        ChapitreViewHolder chapitreViewHolder = new ChapitreViewHolder(cardView);
        return chapitreViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChapitreViewHolder holder, int position) {
        Chapitre current_chapitre = listeChapitre.get(position);
        holder.iv_livre_chapitre.setImageResource(R.drawable.att_koi);
        holder.tv_livre_chapitre.setText(current_chapitre.getName());
    }

    @Override
    public int getItemCount() {
        return listeChapitre.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }


}
