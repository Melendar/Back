package com.example.MemoAnywhere.controller;

import com.example.MemoAnywhere.dto.MemoDTO;
import com.example.MemoAnywhere.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/memos")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    // 메모 추가
    @PostMapping("/create")
    public ResponseEntity<MemoDTO> createMemo(@RequestBody MemoDTO memoDTO) {
        MemoDTO createdMemo =
                memoService.createMemo(memoDTO.getTitle(),
                        memoDTO.getContent(),
                        memoDTO.getDate());
        return ResponseEntity.ok(createdMemo);
    }

    // 메모 업데이트
    @PutMapping("/update/{memoId}")
    public ResponseEntity<MemoDTO> updateMemo(@PathVariable Long memoId, @RequestBody MemoDTO memoDTO) {
        MemoDTO updatedMemo =
                memoService.updateMemo(memoId,
                    memoDTO.getTitle(),
                    memoDTO.getContent(),
                    memoDTO.getDate());
        return updatedMemo != null ? ResponseEntity.ok(updatedMemo) : ResponseEntity.notFound().build();
    }

    // 메모 가져오기 (ID로)
    @GetMapping("/{memoId}")
    public ResponseEntity<MemoDTO> getMemoById(@PathVariable Long memoId) {
        MemoDTO memo = memoService.getMemoById(memoId);
        return memo != null ? ResponseEntity.ok(memo) : ResponseEntity.notFound().build();
    }

    // 메모 삭제
    @DeleteMapping("delete/{memoId}")
    public ResponseEntity<Void> deleteMemoById(@PathVariable Long memoId) {
        boolean deleted = memoService.deleteMemoById(memoId);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // 메모 날짜별로 분류 (오늘, 어제, 지난 30일)
    @GetMapping("/date")
    public ResponseEntity<MemoDateResponse> getMemosByDate() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalDate last30Days = today.minusDays(30);

        List<MemoDTO> todayMemos = memoService.getAllMemos().stream()
                .filter(memo -> memo.getDate().equals(today))
                .collect(Collectors.toList());

        List<MemoDTO> yesterdayMemos = memoService.getAllMemos().stream()
                .filter(memo -> memo.getDate().equals(yesterday))
                .collect(Collectors.toList());

        List<MemoDTO> last30DaysMemos = memoService.getAllMemos().stream()
                .filter(memo -> !memo.getDate().isBefore(last30Days) && memo.getDate().isBefore(today))
                .collect(Collectors.toList());

        MemoDateResponse response = new MemoDateResponse(todayMemos, yesterdayMemos, last30DaysMemos);
        return ResponseEntity.ok(response);
        // todayMemos, yesterdayMemos, last30DaysMemos 각각의 dto 리스트를 반환
    }

    // 메모 검색 (제목이나 내용으로), 해당 하는 메모 DTO만 리스트에 담아 반환
    @GetMapping("/search")
    public ResponseEntity<List<MemoDTO>> searchMemos(@RequestParam String keyword) {
        List<MemoDTO> memos = memoService.getAllMemos().stream()
                .filter(memo -> memo.getTitle().contains(keyword) || memo.getContent().contains(keyword))
                .collect(Collectors.toList());
        return ResponseEntity.ok(memos);
    }

    // 날짜별 메모 응답
    private static class MemoDateResponse {
        private List<MemoDTO> todayMemos;
        private List<MemoDTO> yesterdayMemos;
        private List<MemoDTO> last30DaysMemos;

        public MemoDateResponse(List<MemoDTO> todayMemos, List<MemoDTO> yesterdayMemos, List<MemoDTO> last30DaysMemos) {
            this.todayMemos = todayMemos;
            this.yesterdayMemos = yesterdayMemos;
            this.last30DaysMemos = last30DaysMemos;
        }
        // Getters, Setters 생략 (필요시 추가)
    }
}
