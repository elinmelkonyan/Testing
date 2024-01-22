package models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Course {

    private final String name;
    private final int durationInHours;

//    public String printFormattedCourseData() {
//        return String.format("COURSE: \"%s\" DURATION: %s (hrs)\n", name, durationInHours);
//    }
}
