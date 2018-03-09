package edu.nju.cheess.cloudserver.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    /**
     * 日期时间格式
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 日期格式
     */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDateTime getLocalDateTime(String str) {
        return LocalDateTime.parse(str, DATE_TIME_FORMATTER);
    }

    public static LocalDate getLocalDate(String str) {
        return LocalDate.parse(str, DATE_FORMATTER);
    }

    public static String localDateTimeToString(LocalDateTime dateTime) {
        return DATE_TIME_FORMATTER.format(dateTime);
    }

}
