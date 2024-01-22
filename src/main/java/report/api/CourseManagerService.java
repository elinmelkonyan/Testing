package report.api;

import exceptions.DateException;
import models.Student;

import java.time.LocalDateTime;

public interface CourseManagerService {
    int getTotalDurationOfCourses(Student student);

    LocalDateTime getEndOfCourses(Student student);
    String getInfoProgressOfCourses(Student student, LocalDateTime timeOfReportGeneration) throws DateException;
}
