package com.example.Melendar.service;

import com.example.Melendar.domain.CalendarEvent;
import com.example.Melendar.domain.Group;
import com.example.Melendar.dto.CalendarEventDTO;
import com.example.Melendar.repository.CalendarEventRepository;
import com.example.Melendar.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarEventService {
    private final CalendarEventRepository calendarEventRepository;
    private final GroupRepository groupRepository;

    // 1. 특정 groupId 리스트에 매핑되는 모든 이벤트 불러오기
    public List<CalendarEventDTO> getEventsByGroupIds(List<Long> groupIds) {
        return calendarEventRepository.findAll().stream()
                .filter(event -> groupIds.contains(event.getGroup().getGroupId()))
                .map(CalendarEventDTO::of)
                .collect(Collectors.toList());
    }

    // 2. 새로운 이벤트 추가
    public CalendarEventDTO createEvent(Long groupId, CalendarEventDTO eventDTO) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found with id: " + groupId));

        CalendarEvent event = CalendarEvent.builder()
                .group(group)
                .date(eventDTO.getDate())
                .task(eventDTO.getTask())
                .build();

        CalendarEvent savedEvent = calendarEventRepository.save(event);
        return CalendarEventDTO.of(savedEvent);
    }

    // 3. 특정 eventId에 해당하는 이벤트 수정 (groupId는 변경 불가)
    public CalendarEventDTO updateEvent(Long eventId, CalendarEventDTO eventDTO) {
        CalendarEvent event = calendarEventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with id: " + eventId));

        event.setDate(eventDTO.getDate());
        event.setTask(eventDTO.getTask());
        calendarEventRepository.save(event);

        return CalendarEventDTO.of(event);
    }

    // 4. 특정 eventId에 해당하는 이벤트 삭제
    public void deleteEvent(Long eventId) {
        if (calendarEventRepository.existsById(eventId)) {
            calendarEventRepository.deleteById(eventId);
        } else {
            throw new IllegalArgumentException("Event not found with id: " + eventId);
        }
    }
}
