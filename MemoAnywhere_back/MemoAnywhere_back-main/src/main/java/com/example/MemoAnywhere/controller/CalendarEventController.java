package com.example.MemoAnywhere.controller;

import com.example.MemoAnywhere.dto.CalendarEventDTO;
import com.example.MemoAnywhere.service.CalendarEventService;
import com.example.MemoAnywhere.service.UserGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class CalendarEventController {

    private final CalendarEventService eventService;
    private final UserGroupService userGroupService;

    // 1. User가 속해있는 그룹의 ID 가져오기
    @GetMapping("/user/{userId}/groups")
    public List<Long> getUserGroupNames(@PathVariable Long userId) {
        return userGroupService.getGroupIdsByUserId(userId);
    }

    // 2. 특정 단어로 일정 검색하기
    @GetMapping("/search")
    public List<CalendarEventDTO> searchEvents(@RequestParam String keyword) {
        return eventService.getAllEvents().stream()
                .filter(event -> event.getTask().contains(keyword))
                .collect(Collectors.toList());
    }

    // 3. 일정 생성하기
    @PostMapping("/create")
    public CalendarEventDTO createEvent(@RequestBody CalendarEventDTO eventDTO) {
        return eventService.createEvent(
                eventDTO.getGroupId(),
                eventDTO.getDate(),
                eventDTO.getTask(),
                eventDTO.getStartTime(),
                eventDTO.getEndTime(),
                eventDTO.getNotification(),
                eventDTO.getRepeat(),
                eventDTO.getIsCompleted()
        );
    }

    // 4. 일정 업데이트
    @PutMapping("/update/{eventId}")
    public CalendarEventDTO updateEvent(@PathVariable Long eventId, @RequestBody CalendarEventDTO eventDTO) {
        return eventService.updateEvent(
                eventId,
                eventDTO.getGroupId(),
                eventDTO.getDate(),
                eventDTO.getTask(),
                eventDTO.getStartTime(),
                eventDTO.getEndTime(),
                eventDTO.getNotification(),
                eventDTO.getRepeat(),
                eventDTO.getIsCompleted()
        );
    }

    // 5. 일정 삭제하기
    @DeleteMapping("/delete/{eventId}")
    public boolean deleteEvent(@PathVariable Long eventId) {
        return eventService.deleteEventById(eventId);
    }

    // 6. 특정 그룹의 모든 일정 조회하기
    @GetMapping("/group/{groupId}")
    public List<CalendarEventDTO> getEventsByGroupId(@PathVariable Long groupId) {
        return eventService.getEventsByGroupId(groupId);
    }
}
