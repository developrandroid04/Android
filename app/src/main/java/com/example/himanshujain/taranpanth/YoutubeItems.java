package com.example.himanshujain.taranpanth;

/**
 * Created by himanshu on 18/04/17.
 */

public class YoutubeItems {

    String videoId;
    String imageUrl;
    String channelID;
    String description;
    String title;
    String publishingDate;

    public YoutubeItems(String videoId, String imageUrl, String channelID, String description, String title, String publishingDate) {
        this.videoId = videoId;
        this.imageUrl = imageUrl;
        this.channelID = channelID;
        this.description = description;
        this.title = title;

        this.publishingDate=publishingDate;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getChannelID() {
        return channelID;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(String publishingDate) {
        this.publishingDate = publishingDate;
    }
}
