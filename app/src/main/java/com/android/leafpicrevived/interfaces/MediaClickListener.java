package com.android.leafpicrevived.interfaces;

import com.android.leafpicrevived.data.Album;
import com.android.leafpicrevived.data.Media;

import java.util.ArrayList;

public interface MediaClickListener {

    void onMediaClick(Album album, ArrayList<Media> media, int position);
}
