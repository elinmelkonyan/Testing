package models;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
public class Student {

  private static final int DEFAULT_START_DAY=10;
  private static final int DEFAULT_END_DAY=18;

  private final String name;
  private final int workDayFrom;
  private final int workDayTo;

  private Curriculum curriculum;
  private LocalDate startDate;
  private List<Course> courses;
  public Student(String name, Curriculum curriculum, LocalDate startDate) {
    this.workDayFrom = DEFAULT_START_DAY;
    this.workDayTo = DEFAULT_END_DAY;
    this.name = name;
    this.curriculum = curriculum;
    this.startDate = startDate;
    this.courses = curriculum.getCurriculumCourses();
  }

  public String getStartDateAsString() {
    return startDate.format(DataConstants.DATE_FORMATTER.getFormatter());
  }

  public String getWorkingTime() {
    return String.format("From %s to %s", workDayFrom, workDayTo);
  }

}
