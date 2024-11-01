package com.example.Melendar.controller;

import com.example.Melendar.dto.CalendarEventDTO;
import com.example.Melendar.service.CalendarEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class CalendarEventController {

    private final CalendarEventService calendarEventService;

    // 1. 이벤트 불러오기: 특정 groupId 리스트에 매핑되는 모든 이벤트 반환
    @GetMapping("/getByGroups")
    public List<CalendarEventDTO> getEventsByGroupIds(@RequestParam List<Long> groupIds) {
        return calendarEventService.getEventsByGroupIds(groupIds);
    }

    // 2. 이벤트 추가하기: 새로운 이벤트 생성
    @PostMapping("/create")
    public CalendarEventDTO createEvent(@RequestParam Long groupId, @RequestBody CalendarEventDTO eventDTO) {
        return calendarEventService.createEvent(groupId, eventDTO);
    }

    // 3. 이벤트 수정하기: 특정 eventId에 해당하는 이벤트 수정
    @PutMapping("/update/{eventId}")
    public CalendarEventDTO updateEvent(@PathVariable Long eventId, @RequestBody CalendarEventDTO eventDTO) {
        return calendarEventService.updateEvent(eventId, eventDTO);
    }

    // 4. 이벤트 삭제하기: 특정 eventId에 해당하는 이벤트 삭제
    @DeleteMapping("/delete/{eventId}")
    public void deleteEvent(@PathVariable Long eventId) {
        calendarEventService.deleteEvent(eventId);
    }
}