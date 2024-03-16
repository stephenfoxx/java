package com.hjss.controllers;

import com.hjss.enums.*;
import com.hjss.models.*;

import java.util.List;
import java.util.Random;

public class LessonController {
    private List<Lesson> lessons;
    private List<Coach> coaches;

    public LessonController(List<Lesson> lessons, List<Coach> coaches) {
        this.lessons = lessons;
        this.coaches = coaches;
    }

    public void createLessons() {
        // Lesson Time for Monday, Wednesday and Friday.
        Time[] weekdayTime = {Time.TIME_4PM_TO_5PM, Time.TIME_5PM_TO_6PM, Time.TIME_6PM_TO_7PM};

        // Lesson Time for Saturday.
        Time[] weekendTime = {Time.TIME_2PM_TO_3PM, Time.TIME_3PM_TO_4PM};

        // Grades array
        Grade[] grades = Grade.values();

        // Assign grade to lesson using this index which will be incrementally decreased.
        // Opted to decrease the count so that grade 5 will have 3 occurrences while 1-4 will all have 2.
        int gradeCount = 4;

        // Random class to generate random index to select a coach from coach list for a lesson.
        Random random = new Random();

        // Loop Algorithm 4 times, so it is repeated 4 times.
        // Results in 44 Lessons. 11 a week for a duration of 4 weeks.
        for (int i = 1; i <= 4; i++) {

            // Loop through each day
            for (Day day : Day.values()) {

                // If the current day in loop is saturday, the weekendTime array is used to assign Time to the Lesson
                if (day == Day.SATURDAY) {
                    for (Time time : weekendTime) {

                        // Generate random number within the limit of the coach list size
                        int coachIndex = random.nextInt(coaches.size()); // Randomly select a coach index

                        // Create and Add Lesson to Lesson's list.
                        lessons.add(new Lesson(lessons.size() + 1, grades[gradeCount], day, time, coaches.get(coachIndex)));

                        // Decrease the grade count
                        gradeCount--;

                        // Grade count might have fallen lower than zero, so reset.
                        if (gradeCount < 0) {
                            gradeCount = 4;
                        }
                    }
                } else {
                    // Else, we use the weekday Time
                    for (Time time : weekdayTime) {

                        // Generate random number within the limit of the coach list size
                        int coachIndex = random.nextInt(coaches.size()); // Randomly select a coach index

                        // Create and Add Lesson to Lesson's list.
                        lessons.add(new Lesson(lessons.size() + 1, grades[gradeCount], day, time, coaches.get(coachIndex)));

                        // Decrease the grade count
                        gradeCount--;

                        // Grade count might have fallen lower than zero, so reset.
                        if (gradeCount < 0) {
                            gradeCount = 4;
                        }
                    }
                }
            }
        }
    }
}
