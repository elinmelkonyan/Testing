package report.report;

import exceptions.DateException;
import lombok.RequiredArgsConstructor;
import models.Student;
import report.impl.CourseManagerServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractReportGenerator {
   protected final CourseManagerServiceImpl courseProgressInfo = new CourseManagerServiceImpl();
   protected final LocalDateTime timeOfReportGeneration;

   public abstract void generateReport(List<Student> studentList) throws DateException;

}
