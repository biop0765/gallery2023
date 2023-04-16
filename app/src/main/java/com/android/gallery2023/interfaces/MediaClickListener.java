package com.android.gallery2023.interfaces;

import com.android.gallery2023.data.Album;
import com.android.gallery2023.data.Media;

import java.util.ArrayList;

public interface MediaClickListener {

    void onMediaClick(Album album, ArrayList<Media> media, int position);
}
