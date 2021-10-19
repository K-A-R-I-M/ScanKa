package com.util.scanka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class LivreAdapter extends RecyclerView.Adapter<LivreAdapter.LivreViewHoldler> {

    private Context context;
    private ArrayList<Livre> listeLivre;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public class LivreViewHoldler extends RecyclerView.ViewHolder {
        public ImageView iv_livre;
        public TextView tv_titre_livre;

        public LivreViewHoldler(CardView itemView) {
            super(itemView);
            iv_livre = itemView.findViewById(R.id.iv_livre);
            tv_titre_livre = itemView.findViewById(R.id.tv_titre_livre);
            itemView.setOnClickListener(new View.OnClickListener() {
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

    public LivreAdapter(Context context, ArrayList<Livre> listeLivre) {
        this.context = context;
        this.listeLivre = listeLivre;
    }

    @NonNull
    @Override
    public LivreViewHoldler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(context).inflate(R.layout.livre_item, parent, false);
        LivreViewHoldler livreViewHoldler = new LivreViewHoldler(v);
        return livreViewHoldler;
    }

    @Override
    public void onBindViewHolder(@NonNull LivreViewHoldler holder, int position) {
        Livre currentlivre = listeLivre.get(position);
        holder.tv_titre_livre.setText(currentlivre.getTitle());
    }

    @Override
    public int getItemCount() {
        return listeLivre.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}
