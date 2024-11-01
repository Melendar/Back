package com.example.Melendar.service;

import com.example.Melendar.domain.Memo;
import com.example.Melendar.domain.User;
import com.example.Melendar.dto.MemoDTO;
import com.example.Melendar.repository.MemoRepository;
import com.example.Melendar.repository.UserRepository;
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
    private final UserRepository userRepository;

    // 1. 특정 user_id에 매핑되는 모든 memo를 불러오기
    public List<MemoDTO> getMemosByUserId(Long userId) {
        return memoRepository.findByUserUserId(userId).stream()
                .map(MemoDTO::of)
                .collect(Collectors.toList());
    }

    // 2. 새로운 메모 추가
    public MemoDTO createMemo(Long userId, MemoDTO memoDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        Memo memo = Memo.builder()
                .user(user)
                .title(memoDTO.getTitle())
                .content(memoDTO.getContent())
                .date(memoDTO.getDate())
                .build();

        Memo savedMemo = memoRepository.save(memo);
        return MemoDTO.of(savedMemo);
    }

    // 3. 특정 memo_id에 해당하는 메모 수정 (user_id와 memo_id는 변경 불가)
    public MemoDTO updateMemo(Long memoId, MemoDTO memoDTO) {
        Optional<Memo> memoOptional = memoRepository.findById(memoId);
        if (memoOptional.isPresent()) {
            Memo memo = memoOptional.get();
            memo.setTitle(memoDTO.getTitle());
            memo.setContent(memoDTO.getContent());
            memo.setDate(memoDTO.getDate());
            memoRepository.save(memo);
            return MemoDTO.of(memo);
        } else {
            throw new IllegalArgumentException("Memo not found with id: " + memoId);
        }
    }

    // 4. 특정 memo_id에 해당하는 메모 삭제
    public void deleteMemo(Long memoId) {
        if (memoRepository.existsById(memoId)) {
            memoRepository.deleteById(memoId);
        } else {
            throw new IllegalArgumentException("Memo not found with id: " + memoId);
        }
    }

}
