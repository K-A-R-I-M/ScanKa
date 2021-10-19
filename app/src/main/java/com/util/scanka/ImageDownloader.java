package com.util.scanka;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.Settings;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class ImageDownloader extends AsyncTask<String, Void, Integer> {

    private BitmapDrawable bitmapDrawable;
    private OutputStream outputStream;
    private int nb_current_pages;
    private String titre;
    private ProgressBar pb;
    private Context context;

    public ImageDownloader(BitmapDrawable bitmapDrawable, OutputStream outputStream, int nb_current_pages, String titre, ProgressBar pb, Context context ) {
        this.bitmapDrawable = bitmapDrawable;
        this.outputStream = outputStream;
        this.nb_current_pages = nb_current_pages;
        this.titre = titre;
        this.pb = pb;
        this.context = context;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        String url = strings[0];
        Bitmap bitmap = null;
        try{
            InputStream inputStream = new URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }


        if(bitmap == null){
            bitmap = bitmapDrawable.getBitmap();
        }


        File filepath = Environment.getExternalStorageDirectory();
        File dir = new File(filepath.getAbsolutePath()+"/ScanKa/");
            dir.mkdirs();
        String fin_fich = "_" + nb_current_pages + ".png";
        if( nb_current_pages < 10) {
            fin_fich = "_0" + nb_current_pages + ".png";
        }
        File file = new File(dir, titre+fin_fich);
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            pb.setProgress(pb.getProgress()+1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", context.getPackageName(), null);
            intent.setData(uri);
            context.startActivity(intent);
        }
    return  nb_current_pages;
    }

}
