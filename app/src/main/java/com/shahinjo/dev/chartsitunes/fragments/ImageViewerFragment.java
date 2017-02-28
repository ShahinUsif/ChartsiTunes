package com.shahinjo.dev.chartsitunes.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.shahinjo.dev.chartsitunes.R;
import com.shahinjo.dev.chartsitunes.activities.ImageViewerActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by shahinjo on 2/28/17.
 */

public class ImageViewerFragment extends Fragment {

    String imageURL;
    SubsamplingScaleImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_image_viewer, container, false);

        imageURL = getArguments().getString(ImageViewerActivity.KEY_IMAGE_URI);

        imageView = (SubsamplingScaleImageView) rootView.findViewById(R.id.imageView);
        imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
        imageView.setMinScale(5f);

        imageView.setMaxScale(100f);

        new DownloadImageTask(rootView.getContext()).execute();

        return rootView;
    }

    private class DownloadImageTask extends AsyncTask<Void, Void, Void> {

        private ProgressDialog dialog;
        private Context context;
        String filePath;

        public DownloadImageTask(Context context) {
            this.context = context;
            dialog = new ProgressDialog(context);
            dialog.setCancelable(false);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Progress start");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            filePath = context.getCacheDir() + URLUtil.guessFileName(imageURL, null, null);

           /* if(fileExistance(filePath)) {
                return null;
            }*/

            FileOutputStream out = null;

            try {

                URL myFileUrl = new URL(imageURL);
                HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();

                InputStream is = conn.getInputStream();
                Bitmap bm = BitmapFactory.decodeStream(is);

                out = new FileOutputStream(filePath);
                bm.compress(Bitmap.CompressFormat.PNG, 90, out);

            } catch (MalformedURLException e) {

            } catch (IOException e) {

            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            imageView.setImage(ImageSource.uri(filePath));
        }

        public boolean fileExistance(String fname) {
            File file = new File(fname);
            return file.exists();
        }

    }
}
