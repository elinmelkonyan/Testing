package models;

import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public enum DataConstants {
    DATE_FORMATTER(DateTimeFormatter.ofPattern("d MMMM yyyy - EEEE")),
    DATE_TIME_FORMATTER(DateTimeFormatter.ofPattern("d MMMM yyyy - EEEE (hh:mm)"));
    private final DateTimeFormatter formatter;

    DataConstants(DateTimeFormatter dateTimeFormatter) {
        this.formatter=dateTimeFormatter;
    }
}
