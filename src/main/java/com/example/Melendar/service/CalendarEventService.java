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
    private final CalendarEventRepository eventRepository;
    private final GroupRepository groupRepository;

    public CalendarEventDTO createEvent(Long groupId, LocalDate date, String task, LocalTime start_time, LocalTime end_time, boolean notification, boolean repeat, boolean is_com) {
        // groupId를 기반으로 Group 객체 조회
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group ID"));

        // CalendarEvent 빌더에 Group 객체 설정
        CalendarEvent event = CalendarEvent.builder()
                .group(group)  // group_id 대신 group 사용
                .date(date)
                .task(task)
                .startTime(start_time)
                .endTime(end_time)
                .notification(notification)
                .isRepeated(repeat)
                .isCompleted(is_com)
                .build();

        CalendarEvent savedEvent = eventRepository.save(event);
        return CalendarEventDTO.of(savedEvent);
    }

    // 일정 업데이트 메서드
    public CalendarEventDTO updateEvent(Long eventId, Long groupId, LocalDate date, String task, LocalTime start_time, LocalTime end_time, boolean notification, boolean repeat, boolean is_com) {
        // eventId로 CalendarEvent 조회
        Optional<CalendarEvent> eventOpt = eventRepository.findById(eventId);
        if (eventOpt.isPresent()) {
            CalendarEvent updatedEvent = eventOpt.get();

            // groupId로 Group 객체 조회
            Group group = groupRepository.findById(groupId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid group ID"));

            // CalendarEvent의 group 및 기타 필드 설정
            updatedEvent.setGroup(group);  // setGroup() 사용
            updatedEvent.setDate(date);
            updatedEvent.setTask(task);
            updatedEvent.setStartTime(start_time);
            updatedEvent.setEndTime(end_time);
            updatedEvent.setNotification(notification);
            updatedEvent.setIsRepeated(repeat);
            updatedEvent.setIsCompleted(is_com);

            CalendarEvent savedEvent = eventRepository.save(updatedEvent);
            return CalendarEventDTO.of(savedEvent);
        } else {
            return null;  // eventId가 없을 경우 null 반환 (예외 처리 가능)
        }
    }


//    public CalendarEventDTO getEventById(Long eventId) {   // 일정 정보 가져오기 (하나만)
//        System.out.println("id: " + eventId);
//        Optional<CalendarEvent> event = eventRepository.findById(eventId);
//        System.out.println("user: " + event.get());
//        return event.isPresent() ? CalendarEventDTO.of(event.get()) : null;
//    }
    // 일정 하나만 독단적으로 반환할 일은 없을 것으로 예상

    public List<CalendarEventDTO> getEventsByGroupId(Long groupId) {    // 특정 그룹의 모든 일정 조회
        return eventRepository.findByGroup_GroupId(groupId).stream()
                .map(CalendarEventDTO::of)  // CalendarEvent -> CalendarEventDTO로 변환
                .collect(Collectors.toList());
    }

    public boolean deleteEventById(Long eventId) {    // 일정 삭제하기
        Optional<CalendarEvent> event = eventRepository.findById(eventId);
        if (event.isPresent()) {
            eventRepository.deleteById(eventId);
            return true;
        } else {
            return false;
        }
    }

    public List<CalendarEventDTO> getAllEvents() {  // 모든 일정 가져오기
        return eventRepository.findAll().stream()
                .map(CalendarEventDTO::of)  // CalendarEvent -> CalendarEventDTO로 변환
                .collect(Collectors.toList());
    }
}
