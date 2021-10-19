package com.util.scanka;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.OutputStream;
import java.util.ArrayList;

public class DLChapitreAdapter extends RecyclerView.Adapter<DLChapitreAdapter.ViewHolderCustom> {

        private ArrayList<Chapitre> listeChapitre;
        private Context ctx;

        public class ViewHolderCustom extends RecyclerView.ViewHolder {
            public TextView tv_chapitre_dl;
            public ImageView iv_chapitre_dl;
            public ProgressBar pb_chapitre_dl;
            public View item;

            public ViewHolderCustom(CardView item) {
                super(item);
                tv_chapitre_dl = item.findViewById(R.id.tv_chapitre_dl);
                iv_chapitre_dl = item.findViewById(R.id.iv_chapitre_dl);
                pb_chapitre_dl = item.findViewById(R.id.pb_chapitre_dl);
                this.item = item;

            }

        }

        public DLChapitreAdapter(ArrayList<Chapitre> listeChapitre, Context ctx) {
            this.listeChapitre = listeChapitre;
            this.ctx = ctx;
        }

    @NonNull
    @Override
    public ViewHolderCustom onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(ctx).inflate(R.layout.dlchapitre_item, parent, false);
        ViewHolderCustom viewHolderCustom = new ViewHolderCustom(v);
        return viewHolderCustom;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCustom holder, int position) {

        Chapitre currentItem = (Chapitre) listeChapitre.get(position);
        holder.tv_chapitre_dl.setText(currentItem.getName());
        holder.iv_chapitre_dl.setImageResource(R.drawable.att_koi);
        holder.pb_chapitre_dl.setMax(currentItem.getNb_pages());

        if(currentItem.isDl_status() != true) {
            OutputStream outputStream = null;
            Integer nb_page_actu = null;
            for (int i = 1; i < currentItem.getNb_pages() + 1; i++) {

                BitmapDrawable bitmapDrawable = (BitmapDrawable) holder.iv_chapitre_dl.getDrawable();
                ImageDownloader imageDownloader = new ImageDownloader(bitmapDrawable, outputStream, i, currentItem.getName(), holder.pb_chapitre_dl, ctx);
                imageDownloader.execute("https://cdn.readkingdom.com/file/mangap/8/10" + Integer.parseInt(currentItem.getName()) * 1000 + "/" + i + ".jpg");

            }
            if(currentItem.getNb_pages_dl() == currentItem.getNb_pages()){
                currentItem.setDl_status(true);
            }
            DataBaseHelperChapitre dbhc = new DataBaseHelperChapitre(ctx);
            dbhc.modifyChapitre(currentItem);
        }else{
            holder.pb_chapitre_dl.setProgress(currentItem.getNb_pages());
            System.out.println("plein");
        }

    }

    @Override
        public long getItemId(int position) {
            return position;
        }

    @Override
    public int getItemCount() {
        return listeChapitre.size();
    }


}
