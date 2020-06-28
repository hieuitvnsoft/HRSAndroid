package com.example.hrs;

import android.content.Context;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
public class ImageViewLoader {
    public static void load(Context context, ImageView imageView, String link) {
        // Config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
        ImageLoader.getInstance().init(config);
        ImageLoader imageLoader = ImageLoader.getInstance();

        // Thêm tùy chọn ảnh hiển thị
//        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.ic_eye) // resource or drawable
//                .showImageForEmptyUri(R.drawable.ic_menu_manage) // resource or drawable
//                .showImageOnFail(R.drawable.ic_grid).build(); // resource or drawable
        // Log.e("MInhVT", proc.getProImgLink());

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_loading) // resource or drawable
                .showImageForEmptyUri(R.drawable.ic_no_image) // resource or drawable
                .showImageOnFail(R.drawable.ic_error) // resource or drawable
                .resetViewBeforeLoading(false)  // default
                .delayBeforeLoading(0)
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .build();

        imageLoader.displayImage(link, imageView, options);
    }
}
