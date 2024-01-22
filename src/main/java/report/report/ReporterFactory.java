package report.report;

import exceptions.DateException;
import models.Student;

import java.time.LocalDateTime;
import java.util.List;

//not abstract and returns AbstractReportGeneretor
public class ReporterFactory {

    public static AbstractReportGenerator getReport(int parameter, LocalDateTime timeOfReportGeneration){
        if (parameter == 1) {
            return new FullReportGenerator(timeOfReportGeneration);
        } else {
            return new ShortReportGenerator(timeOfReportGeneration);
        }
    }

}
