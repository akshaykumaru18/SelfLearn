package com.selfLearn.SELF_LEARN.DataModels;

import java.util.List;



public class Course {
    private String courseName;
    private String courseDescription;
    private String courseId;
    private float coursePrice;
    private CourseType courseType;
    private String courseImage;
    private Quiz courseQuiz;
    private List<CourseVideo> courseVideos;
    private List<CourseMessage> discussionMessages;
    private List<String> categories;





    public Course(String courseName, String courseId, String courseDescription, float coursePrice, CourseType courseType, String courseImage, Quiz courseQuiz, List<CourseVideo> courseVideos, List<CourseMessage> discussionMessages) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.courseDescription = courseDescription;
        this.coursePrice = coursePrice;
        this.courseType = courseType;
        this.courseImage = courseImage;
        this.courseQuiz = courseQuiz;
        this.courseVideos = courseVideos;
        this.discussionMessages = discussionMessages;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
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

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}

class Quiz {
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

