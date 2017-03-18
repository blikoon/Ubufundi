/*
    * MinSdk : 10 (Because of glide
    * We use Glide so it takes care of the heavy lifting in loading images
    * Set up a recycler view as you would normaly do. Previous apps have enough examples on that
    * The work is done in onBindViewHolder where you give the link to the image to be downloaded and the place holder where it will be placed.
            public void onBindViewHolder(ImageViewHolder holder, int position) {
            Glide.with(MainActivity.this)
                    .load(strArray[position])
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .centerCrop()
                    .placeholder(R.drawable.ic_cloud_download_black_24dp)
                    .error(R.drawable.ic_warning_black_24dp)
                    .crossFade()
                    .into(holder.image);
        }
    *Remember to clear that data of the image when it gets out of the view
       public void onViewRecycled(ImageViewHolder holder) {
            super.onViewRecycled(holder);
            Glide.clear(holder.image);
        }

    *This brings in great optimizations for example in killing connections downloading images that are out of the view.

    * To be good citizens, we also provide local resources to show if there has been an error and if the image is in the process of being downloaded.
    *
    *
 */


package com.blikoon.app340;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MainActivity extends AppCompatActivity {

    //These are images links we prepare due to some other image sources being
    //unreachagle from time to time.
    String[] strArray = {
            "http://www.blikoon.com/wp-content/uploads/2017/03/gitignore.png",
            "http://www.blikoon.com/wp-content/uploads/2016/11/Git-Logo.png",
            "http://www.blikoon.com/wp-content/uploads/2016/10/wordpress.png",
            "http://www.blikoon.com/wp-content/uploads/2016/10/letsencryptnginx.png",
            "http://www.blikoon.com/wp-content/uploads/2016/10/wordpress-mysql.png",
            "http://www.blikoon.com/wp-content/uploads/2016/05/http_tute_telnet_terminal_processed.png",
            "http://www.blikoon.com/wp-content/uploads/2017/03/gitignore.png",
            "http://www.blikoon.com/wp-content/uploads/2016/11/Git-Logo.png",
            "http://www.blikoon.com/wp-content/uploads/2016/10/wordpress.png",
            "http://www.blikoon.com/wp-content/uploads/2016/10/letsencryptnginx.png",
            "http://www.blikoon.com/wp-content/uploads/2016/10/wordpress-mysql.png",
            "http://www.blikoon.com/wp-content/uploads/2016/05/http_tute_telnet_terminal_processed.png",
            "http://www.blikoon.com/wp-content/uploads/2017/03/gitignore.png",
            "http://www.blikoon.com/wp-content/uploads/2016/11/Git-Logo.png",
            "http://www.blikoon.com/wp-content/uploads/2016/10/wordpress.png",
            "http://www.blikoon.com/wp-content/uploads/2016/10/letsencryptnginx.png",
            "http://www.blikoon.com/wp-content/uploads/2016/10/wordpress-mysql.png",
            "http://www.blikoon.com/wp-content/uploads/2016/05/http_tute_telnet_terminal_processed.png",
            "http://www.blikoon.com/wp-content/uploads/2017/03/gitignore.png",
            "http://www.blikoon.com/wp-content/uploads/2016/11/Git-Logo.png",
            "http://www.blikoon.com/wp-content/uploads/2016/10/wordpress.png",
            "http://www.blikoon.com/wp-content/uploads/2016/10/letsencryptnginx.png",
            "http://www.blikoon.com/wp-content/uploads/2016/10/wordpress-mysql.png",
            "http://www.blikoon.com/wp-content/uploads/2016/05/http_tute_telnet_terminal_processed.png",
            "http://www.blikoon.com/wp-content/uploads/2017/03/gitignore.png",
            "http://www.blikoon.com/wp-content/uploads/2016/11/Git-Logo.png",
            "http://www.blikoon.com/wp-content/uploads/2016/10/wordpress.png",
            "http://www.blikoon.com/wp-content/uploads/2016/10/letsencryptnginx.png",
            "http://www.blikoon.com/wp-content/uploads/2016/10/wordpress-mysql.png",
            "http://www.blikoon.com/wp-content/uploads/2016/05/http_tute_telnet_terminal_processed.png",
            "http://www.blikoon.com/wp-content/uploads/2017/03/gitignore.png",
            "http://www.blikoon.com/wp-content/uploads/2016/11/Git-Logo.png",
            "http://www.blikoon.com/wp-content/uploads/2016/10/wordpress.png",
            "http://www.blikoon.com/wp-content/uploads/2016/10/letsencryptnginx.png",
            "http://www.blikoon.com/wp-content/uploads/2016/10/wordpress-mysql.png",
            "http://www.blikoon.com/wp-content/uploads/2016/05/http_tute_telnet_terminal_processed.png"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView
                = (RecyclerView) findViewById(R.id.image_grid);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new ImagesAdapter());
    }
    private class ImagesAdapter extends RecyclerView.Adapter<ImageViewHolder> {
        @Override
        public ImageViewHolder onCreateViewHolder(ViewGroup parent,
                                                  int viewType) {
            View view = LayoutInflater.from(MainActivity.this)
                    .inflate(R.layout.image_item, parent, false);
            return new ImageViewHolder(view);
        }
        @Override
        public void onBindViewHolder(ImageViewHolder holder, int position) {
            Glide.with(MainActivity.this)
                    .load(strArray[position])
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .centerCrop()
                    .placeholder(R.drawable.ic_cloud_download_black_24dp)
                    .error(R.drawable.ic_warning_black_24dp)
                    .crossFade()
                    .into(holder.image);
        }
        @Override
        public void onViewRecycled(ImageViewHolder holder) {
            super.onViewRecycled(holder);
            Glide.clear(holder.image);
        }
        @Override
        public int getItemCount() {
            return 36;
        }
    }
    private class ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        public ImageViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}