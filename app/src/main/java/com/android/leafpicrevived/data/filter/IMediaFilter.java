package com.android.leafpicrevived.data.filter;

import com.android.leafpicrevived.data.Media;

/**
 * Created by dnld on 4/10/17.
 */

public interface IMediaFilter {
    boolean accept(Media media);
}
