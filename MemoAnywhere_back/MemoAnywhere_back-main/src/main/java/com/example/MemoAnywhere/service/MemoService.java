package com.example.MemoAnywhere.service;

import com.example.MemoAnywhere.domain.Memo;
import com.example.MemoAnywhere.dto.MemoDTO;
import com.example.MemoAnywhere.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    public MemoDTO createMemo(String title, String content, LocalDate date) {    // 메모 생성
        Memo memo = Memo.builder()
                .title(title)
                .content(content)
                .date(date)
                .build();

        Memo savedMemo = memoRepository.save(memo);
        return MemoDTO.of(savedMemo);
    }

    public MemoDTO updateMemo(Long memoId, String title, String content, LocalDate date) {     // 메모 업데이트
        Optional<Memo> memo = memoRepository.findById(memoId);
        if (memo.isPresent()) {
            Memo updatedMemo = memo.get();
            updatedMemo.setTitle(title);
            updatedMemo.setContent(content);
            updatedMemo.setDate(date);
            Memo savedMemo = memoRepository.save(updatedMemo);
            return MemoDTO.of(savedMemo);
        } else {
            return null;
        }
    }

    public MemoDTO getMemoById(Long memoId) {   // 메모 정보 가져오기
        System.out.println("id: " + memoId);
        Optional<Memo> memo = memoRepository.findById(memoId);
        System.out.println("user: " + memo.get());
        return memo.isPresent() ? MemoDTO.of(memo.get()) : null;
    }

    public boolean deleteMemoById(Long memoId) {    // 메모 삭제하기
        Optional<Memo> memo = memoRepository.findById(memoId);
        if (memo.isPresent()) {
            memoRepository.deleteById(memoId);      // 삭제하는 역할
            return true;    // 해당 아이디의 메모가 존재해 삭제하게 되면 true 반환
        } else {
            return false; // 메모가 존재하지 않을 경우 false 반환
        }
    }

    public List<MemoDTO> getAllMemos() {   // 모든 메모 가져오기
        List<Memo> memos = memoRepository.findAll();  // DB에서 모든 메모를 가져옴
        return memos.stream()
                .map(MemoDTO::of)  // Memo 엔티티를 MemoDTO로 변환
                .collect(Collectors.toList());  // 리스트로 변환하여 반환
    }

}
