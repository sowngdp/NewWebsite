package com.news.QLLTC;

import java.time.LocalDateTime;

public class Utils {
    public String toDateTimeString(LocalDateTime dateTime) {
        var datetimeFormat = "dd/MM/yyyy HH:mm";
        return dateTime.format(java.time.format.DateTimeFormatter.ofPattern(datetimeFormat));
    }


}
