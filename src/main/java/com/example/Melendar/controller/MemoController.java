package com.example.Melendar.controller;

import com.example.Melendar.dto.MemoDTO;
import com.example.Melendar.service.MemoService;
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

    // 1. 메모 불러오기: 특정 user_id에 매핑되는 모든 memo 반환
    @GetMapping("/getByUser")
    public List<MemoDTO> getMemosByUserId(@RequestParam Long userId) {
        return memoService.getMemosByUserId(userId);
    }

    // 2. 메모 추가하기: 새로운 메모 생성
    @PostMapping("/create")
    public MemoDTO createMemo(@RequestParam Long userId, @RequestBody MemoDTO memoDTO) {
        return memoService.createMemo(userId, memoDTO);
    }

    // 3. 메모 수정하기: 특정 memo_id에 해당하는 메모 수정
    @PutMapping("/update/{memoId}")
    public MemoDTO updateMemo(@PathVariable Long memoId, @RequestBody MemoDTO memoDTO) {
        return memoService.updateMemo(memoId, memoDTO);
    }

    // 4. 메모 삭제하기: 특정 memo_id에 해당하는 메모 삭제
    @DeleteMapping("/delete/{memoId}")
    public void deleteMemo(@PathVariable Long memoId) {
        memoService.deleteMemo(memoId);
    }

}