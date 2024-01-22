package report.report;

import exceptions.DateException;
import models.Student;

import java.time.LocalDateTime;
import java.util.List;

import static java.lang.Math.abs;

public class ShortReportGenerator extends AbstractReportGenerator {

    public ShortReportGenerator(LocalDateTime timeOfReportGeneration) {
        super(timeOfReportGeneration);
    }

    @Override
    public void generateReport(List<Student> studentList) throws DateException {
        for (Student student : studentList) {
            System.out.printf("%s (%s) - %s",
                    student.getName(),
                    student.getCurriculum().getDesc(),
                    courseProgressInfo.getInfoProgressOfCourses(student, timeOfReportGeneration));
//            System.out.print(courseProgressInfo.getInfoProgressOfCourses(student, timeOfReportGeneration));
        }
    }

}
