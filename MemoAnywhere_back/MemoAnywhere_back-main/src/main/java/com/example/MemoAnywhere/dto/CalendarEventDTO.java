package com.example.MemoAnywhere.dto;

import com.example.MemoAnywhere.domain.CalendarEvent;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CalendarEventDTO {
    private Long eventId;
    private Long groupId;
    private LocalDate date;
    private String task;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean notification;
    private Boolean repeat;
    private Boolean isCompleted;

    public static CalendarEventDTO of(CalendarEvent event) {
        CalendarEventDTO calendareventDTO = new CalendarEventDTO();
        calendareventDTO.setEventId(event.getEventId());
        calendareventDTO.setGroupId(event.getGroup_id());
        calendareventDTO.setDate(event.getDate());
        calendareventDTO.setTask(event.getTask());
        calendareventDTO.setStartTime(event.getStartTime());
        calendareventDTO.setEndTime(event.getEndTime());
        calendareventDTO.setNotification(event.getNotification());
        calendareventDTO.setRepeat(event.getRepeat());
        calendareventDTO.setIsCompleted(event.getIsCompleted());
        return calendareventDTO;
    }
}
