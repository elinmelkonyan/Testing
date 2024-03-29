import exceptions.DateException;
import models.Course;
import models.Curriculum;
import models.Student;
import report.report.AbstractReportGenerator;
import report.report.ReporterFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class TrainingCenter {
    public static List<Student> students = List.of(new Student("Ivan Ivanov", new Curriculum("Java Developer", List.of(
            new Course("Java", 16),
            new Course("Spring", 16),
            new Course("JDBC", 24))),
            LocalDate.of(2023, 1, 1)),

            new Student("Ivan Sidorov", new Curriculum("AQA",  List.of(
                    new Course("Test design", 10),
                    new Course("Page Object", 16),
                    new Course("Selenium", 16))),
                    LocalDate.of(2023, 1, 1)));

    public static void main(String[] args) throws DateException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input date (yyyy-mm-dd)");
        String date = scanner.nextLine();
        System.out.println("Input time (hh:mm)");
        String time = scanner.nextLine();
        System.out.println("Input parameter for type of report (1 - full report, no parameter/0 - short)");
        int parameter = scanner.nextInt();
        scanner.close();
        LocalDateTime dateTime = LocalDateTime.parse(date + "T" + time);


        AbstractReportGenerator generator = ReporterFactory.getReport(parameter, dateTime);
        generator.generateReport(students);
        //use strategy design pattern
    }
}
