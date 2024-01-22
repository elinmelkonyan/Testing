import exceptions.DateException;
import models.Course;
import models.Curriculum;
import models.DataConstants;
import models.Student;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import report.api.CourseManagerService;
import report.impl.CourseManagerServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CourseManagerTest {
    private Student student;
    private CourseManagerService courseManagerService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeEach
    public void setUp() {
        courseManagerService = new CourseManagerServiceImpl();
        student = new Student("Ivan Ivanov", new Curriculum("Java Developer", List.of(
                new Course("Java", 16),
                new Course("Spring", 16),
                new Course("JDBC", 24))),
                LocalDate.of(2023, 1, 1)
        );

    }
    @Test
    void shouldReturnTotalDuration() {
    int expectedHours = 56;
    int result = courseManagerService.getTotalDurationOfCourses(student);
    assertEquals(result, expectedHours);
  }

  @Test
  void shouldReturnExpectedDate(){
    LocalDateTime endOfCourses = courseManagerService.getEndOfCourses(student);
    assertEquals("date of end training not correct", LocalDateTime.of(2023, 1, 11, 10, 0), endOfCourses);
  }


  @Test
  void shouldReturnNullPointerExceptionForNullStudent() {
    LocalDateTime endOfCourses = courseManagerService.getEndOfCourses(null);
    assertNull(endOfCourses, "End of courses should be null for a null student");
  }
    @Test
    public void getDateException() throws DateException {
        expectedException.expect(DateException.class);
        expectedException.expectMessage("The date can not be early than " + student.getStartDate().format(DataConstants.DATE_FORMATTER.getFormatter()));
        courseManagerService.getInfoProgressOfCourses(student, LocalDateTime.of(2020, 5, 1, 15, 0));
    }

    @Test
    public void getInfoAboutProgressWithStartDate() throws DateException {
        String infoProgressOfCourses = courseManagerService.getInfoProgressOfCourses(student, LocalDateTime.of(student.getStartDate(), LocalTime.of(10, 0)));
        assertTrue("info about courses progress not correct", infoProgressOfCourses.equalsIgnoreCase("You just started!"));
    }

    @Test
    public void should_return_training_progress_info_when_training_ended() throws DateException {
        String infoProgressOfCourses = courseManagerService.getInfoProgressOfCourses(student, LocalDateTime.of(2023, 1, 12, 10, 0));

        assertEquals("Training completed. 1 days 0 hours have passed since the end.\n"
                , infoProgressOfCourses);
    }
    @Test
    public void should_return_training_progress_info_when_training_inProgress() throws DateException {
        String infoProgressOfCourses = courseManagerService.getInfoProgressOfCourses(student, LocalDateTime.of(2023, 1, 8, 12, 0));
        assertEquals("Training is not finished. 3 days 2 hours are left until the end.\n"
                , infoProgressOfCourses);
    }
}
