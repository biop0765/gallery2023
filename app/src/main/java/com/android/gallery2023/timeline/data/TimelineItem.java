package com.android.gallery2023.timeline.data;


import androidx.annotation.IntDef;

/**
 * Interface to define that this item is capable of being displayed on timeline
 */
public interface TimelineItem {

    int TYPE_HEADER = 101;
    int TYPE_MEDIA = 102;

    @IntDef({TYPE_HEADER, TYPE_MEDIA})
    @interface TimelineItemType {
    }

    @TimelineItemType
    int getTimelineType();
}
