package org.skyhigh.afishadevappgui.common.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public class ServiceUtils {
    public static int countNull(List<Object> nullableObjects) {
        return (int) nullableObjects.stream().filter(x -> {
            if (x == null) return true;
            return x instanceof String && ((String) x).isEmpty();
        }).count();
    }

    public static int countNotNull(List<Object> nullableObjects) {
        return (int) nullableObjects.stream().filter(x -> {
            if (x != null)
                return !(x instanceof String) || !((String) x).isEmpty();
            return false;
        }).count();
    }

    public static String getFormattedLocalDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
                .toFormatter();
        return localDateTime.format(dateTimeFormatter);
    }
}
