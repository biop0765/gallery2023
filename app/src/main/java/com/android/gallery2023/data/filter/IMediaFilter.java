package com.android.gallery2023.data.filter;

import com.android.gallery2023.data.Media;

/**
 * Created by dnld on 4/10/17.
 */

public interface IMediaFilter {
    boolean accept(Media media);
}
