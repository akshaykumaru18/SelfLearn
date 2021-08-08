package com.selfLearn.SELF_LEARN.DataModels;

public class CourseVideo {
    String videoTitle;
    String videoImage;
    String videoDescription;
    String youtubeId;


    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public CourseVideo(String videoTitle, String videoImage, String videoDescription, String youtubeId) {
        this.videoTitle = videoTitle;
        this.videoImage = videoImage;
        this.videoDescription = videoDescription;
        this.youtubeId = youtubeId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoImage() {
        return videoImage;
    }

    public void setVideoImage(String videoImage) {
        this.videoImage = videoImage;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }
}
