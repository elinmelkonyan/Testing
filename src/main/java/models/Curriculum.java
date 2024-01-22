package models;

import lombok.Data;

import java.util.List;

@Data
public class Curriculum {

    private List<Course> curriculumCourses;
    private String desc;

    public Curriculum(String desc, List<Course> curriculumCourses) {
        this.desc = desc;
        this.curriculumCourses = curriculumCourses;
    }
    private void addCourse(Course course) {
        curriculumCourses.add(course);
    }

}
