package com.example.root.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MyTask extends AsyncTask<String,String,Bitmap>{//in generics String type of url passing, progress,Bitmap result as image we are getting

    private Context mContext;
    ProgressDialog mProgressDialog;
    private ImageView mImageView;


    public MyTask(Context mContext, ImageView mImageView) {
        this.mContext = mContext;
        this.mImageView = mImageView;
        mProgressDialog=new ProgressDialog(mContext);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
    }


    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap=null;
        try{
            Thread.sleep(2000);
            publishProgress("Connecting Server");
            URL url=new URL(params[0]);//it converts string into url
            URLConnection urlConnection=url.openConnection();//it will find the server
            urlConnection.connect();//it connects to the server

            Thread.sleep(2000);
            publishProgress("Downloading data");//this method will send the message to doInbackground which accepts string.

            InputStream inputStream=urlConnection.getInputStream();//to get the data or to read the data
            BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);//to collect all the data
            bitmap= BitmapFactory.decodeStream(bufferedInputStream);//converting data into an bitmap

            Thread.sleep(2000);
            publishProgress("Image downloaded");
            Thread.sleep(2000);


        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        mProgressDialog.setMessage(values[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        mImageView.setImageBitmap(bitmap);
        mProgressDialog.dismiss();
    }
}
