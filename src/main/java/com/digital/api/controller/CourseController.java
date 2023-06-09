package com.digital.api.controller;

import com.digital.api.ApiRequest;
import com.digital.entities_pojo.Courses;

import static com.digital.api.BaseEndPoints.*;

public class CourseController extends ApiRequest {
    public CourseController(String url, String apiKey) {
        super(url, apiKey);
    }

    public Courses[] receiveCourses() {
        return super.get(generateEndPoints(API, V1, COURSES)).as(Courses[].class);

    }

    public static void main(String[] args) {
        CourseController courseController = new CourseController("https://burulai.talentlms.com", "5pgR9B2XNtyprXnbAuXFgR17mQ4EHs");
        courseController.receiveCourses();
    }
}
