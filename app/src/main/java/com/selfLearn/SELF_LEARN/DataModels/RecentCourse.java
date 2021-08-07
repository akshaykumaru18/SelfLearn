package com.selfLearn.SELF_LEARN.DataModels;

import java.util.List;

public class RecentCourse {

    private String courseID;
    private String courceName;
    private List<String> videos;
    private String lastViewedCourse;
    private String imageURL;

    public RecentCourse(String courseID, String courceName, List<String> videos, String lastViewedCourse, String imageURL) {
        this.courseID = courseID;
        this.courceName = courceName;
        this.videos = videos;
        this.lastViewedCourse = lastViewedCourse;
        this.imageURL = imageURL;
    }
    public String getCourseID() {
        return courseID;
    }



    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourceName() {
        return courceName;
    }

    public void setCourceName(String courceName) {
        this.courceName = courceName;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

    public String getLastViewedCourse() {
        return lastViewedCourse;
    }

    public void setLastViewedCourse(String lastViewedCourse) {
        this.lastViewedCourse = lastViewedCourse;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
