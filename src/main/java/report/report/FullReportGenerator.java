package report.report;

import exceptions.DateException;
import models.Student;

import java.time.LocalDateTime;
import java.util.List;


public class FullReportGenerator extends AbstractReportGenerator {

    public FullReportGenerator(LocalDateTime timeOfReportGeneration) {
        super(timeOfReportGeneration);
    }

    @Override
    public void generateReport(List<Student> studentList) throws DateException {
        for (Student student : studentList) {
            System.out.printf("%s (%s) " + "\nWorking time: %s \nStart date: %s \n" +
                            "Program duration: %s \nEnd date: %s \nInfo about progress: %s",
                    student.getName(),
                    student.getCurriculum().getDesc(),
                    student.getWorkingTime(),
                    student.getStartDateAsString(),
                    courseProgressInfo.getTotalDurationOfCourses(student),
                    courseProgressInfo.getEndDateAsString(student),
                    courseProgressInfo.getInfoProgressOfCourses(student, timeOfReportGeneration)
            );
        }
    }

}
