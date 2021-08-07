package com.selfLearn.SELF_LEARN.DataModels;

import java.util.List;

;
public class Course {
    private String courseName;
    private String courseDescrption;
    private String courseId;
    private float coursePrice;
    private CourseType courseType;
    private String courseImage;
    private Quiz courseQuiz;
    private List<CourseVideo> courseVideos;
    private List<CourseMessage> discussionMessages;

    public Course(String courseName, String courseId,String courseDescrption, float coursePrice, CourseType courseType, String courseImage, Quiz courseQuiz, List<CourseVideo> courseVideos, List<CourseMessage> discussionMessages) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.courseDescrption = courseDescrption;
        this.coursePrice = coursePrice;
        this.courseType = courseType;
        this.courseImage = courseImage;
        this.courseQuiz = courseQuiz;
        this.courseVideos = courseVideos;
        this.discussionMessages = discussionMessages;
    }

    public String getCourseDescrption() {
        return courseDescrption;
    }

    public void setCourseDescrption(String courseDescrption) {
        this.courseDescrption = courseDescrption;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public float getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(float coursePrice) {
        this.coursePrice = coursePrice;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public String getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(String courseImage) {
        this.courseImage = courseImage;
    }

    public Quiz getCourseQuiz() {
        return courseQuiz;
    }

    public void setCourseQuiz(Quiz courseQuiz) {
        this.courseQuiz = courseQuiz;
    }

    public List<CourseVideo> getCourseVideos() {
        return courseVideos;
    }

    public void setCourseVideos(List<CourseVideo> courseVideos) {
        this.courseVideos = courseVideos;
    }

    public List<CourseMessage> getDiscussionMessages() {
        return discussionMessages;
    }

    public void setDiscussionMessages(List<CourseMessage> discussionMessages) {
        this.discussionMessages = discussionMessages;
    }
}

class  Quiz {
    List<Question> questionList;
    private int minPassMarks;
    public Quiz(List<Question> questionList, int minPassMarks) {
        this.questionList = questionList;
        this.minPassMarks = minPassMarks;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public int getMinPassMarks() {
        return minPassMarks;
    }

    public void setMinPassMarks(int minPassMarks) {
        this.minPassMarks = minPassMarks;
    }


}

class Question {
    String question;
    List<?> choices;
    int correctChoice;

    public Question(String question, List<?> choices, int correctChoice) {
        this.question = question;
        this.choices = choices;
        this.correctChoice = correctChoice;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<?> getChoices() {
        return choices;
    }

    public void setChoices(List<?> choices) {
        this.choices = choices;
    }

    public int getCorrectChoice() {
        return correctChoice;
    }

    public void setCorrectChoice(int correctChoice) {
        this.correctChoice = correctChoice;
    }
}

class  CourseVideo {
    String videoTitle;
    String videoImage;
    String VideoDescription;
    public CourseVideo(String videoTitle, String videoImage, String videoDescription) {
        this.videoTitle = videoTitle;
        this.videoImage = videoImage;
        VideoDescription = videoDescription;
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
        return VideoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        VideoDescription = videoDescription;
    }
}

class  CourseMessage {
    String messageId;
    String message;
    String messageTime;
    String sentBy;
    String messageType;

    public CourseMessage(String messageId, String message, String messageTime, String sentBy, String messageType) {
        this.messageId = messageId;
        this.message = message;
        this.messageTime = messageTime;
        this.sentBy = sentBy;
        this.messageType = messageType;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}