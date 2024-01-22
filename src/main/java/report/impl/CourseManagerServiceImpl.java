package report.impl;

import exceptions.DateException;
import models.Course;
import models.DataConstants;
import models.Student;
import report.api.CourseManagerService;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;

import static java.lang.Math.abs;

public class CourseManagerServiceImpl implements CourseManagerService {
    public String getInfoProgressOfCourses(Student student, LocalDateTime timeOfReportGeneration) throws DateException {
        if (student.getStartDate().isAfter(ChronoLocalDate.from(timeOfReportGeneration)))
            throw new DateException("The date can not be early than " + student.getStartDate().format(DataConstants.DATE_FORMATTER.getFormatter()));
        if (student.getStartDate().isEqual(ChronoLocalDate.from(timeOfReportGeneration)))
            return "You just started!";
        if (getEndOfCourses(student).isAfter(timeOfReportGeneration)) {
            return String.format("Training is not finished. %s days %s hours are left until the end.\n",
                    getDiffBetweenDays(student, timeOfReportGeneration), getDiffBetweenHours(student, timeOfReportGeneration));
        } else
            return String.format("Training completed. %s days %s hours have passed since the end.\n",
                    getDiffBetweenDays(student, timeOfReportGeneration), getDiffBetweenHours(student, timeOfReportGeneration));
    }

    private int getDiffBetweenDays(Student student, LocalDateTime timeOfReportGeneration) {
        return abs(getEndOfCourses(student).getDayOfYear() - timeOfReportGeneration.getDayOfYear());
    }

    private int getDiffBetweenHours(Student student, LocalDateTime timeOfReportGeneration) {
        return abs(getEndOfCourses(student).getHour() - timeOfReportGeneration.getHour());
    }
    @Override
    public int getTotalDurationOfCourses(Student student) {
        int total = 0;
        for (Course s: student.getCourses()) {
            total += s.getDurationInHours();
        }
        return total;
    }

    @Override
    public LocalDateTime getEndOfCourses(Student student) {
        int totalDuration = getTotalDurationOfCourses(student);
        LocalDateTime startTime = student.getStartDate().atTime(student.getWorkDayFrom(), 0, 0);

        LocalDateTime courseEndDateTime = getEndDateTime(student, totalDuration, startTime);
        return courseEndDateTime;
    }
    public String getEndDateAsString(Student student) {
        return getEndOfCourses(student).format(DataConstants.DATE_TIME_FORMATTER.getFormatter());
      }
    private LocalDateTime getEndDateTime(Student student, int totalDuration, LocalDateTime startTime) {
        int remainingDuration = totalDuration;
        LocalDateTime currentDateTime = startTime;

        while (remainingDuration > 0) {
            if (isWorkingDay(currentDateTime.getDayOfWeek())) {
                int workHours = Math.min(remainingDuration, student.getWorkDayTo() - student.getWorkDayFrom());
                currentDateTime = currentDateTime.plusDays(1);
                remainingDuration -= workHours;
            } else {
                do {
                    currentDateTime = currentDateTime.plusDays(1);
                } while (!isWorkingDay(currentDateTime.getDayOfWeek()));
            }
        }
        return currentDateTime;
    }

    private boolean isWorkingDay(DayOfWeek dayOfWeek) {
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
    }

}
