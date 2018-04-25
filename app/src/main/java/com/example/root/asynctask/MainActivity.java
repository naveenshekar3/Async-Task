package com.example.root.asynctask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView=(ImageView)findViewById(R.id.imageView);

    }

    public void download(View view)
    {
        MyTask myTask=new MyTask(MainActivity.this,mImageView);
        myTask.execute("https://free-images.com/sm/0e72/siberian_tiger_7.jpg");
    }
}
