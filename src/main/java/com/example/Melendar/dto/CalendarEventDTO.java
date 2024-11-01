package com.example.Melendar.dto;

import com.example.Melendar.domain.CalendarEvent;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CalendarEventDTO {
    private Long eventId;
    private Long groupId;
    private LocalDate date;
    private String task;

    public static CalendarEventDTO of(CalendarEvent event) {
        CalendarEventDTO calendareventDTO = new CalendarEventDTO();
        calendareventDTO.setEventId(event.getEventId());
        calendareventDTO.setGroupId(event.getGroup().getGroupId());
        calendareventDTO.setDate(event.getDate());
        calendareventDTO.setTask(event.getTask());
        return calendareventDTO;
    }
}
