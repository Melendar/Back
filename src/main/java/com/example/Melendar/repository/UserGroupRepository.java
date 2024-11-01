package com.example.Melendar.repository;

import com.example.Melendar.domain.Group;
import com.example.Melendar.domain.User;
import com.example.Melendar.domain.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    // 1. 특정 사용자와 그룹 간의 관계가 존재하는지 확인 (초대 중복 확인용)
    boolean existsByUserAndGroup(User user, Group group);

    // 2. 특정 user_id와 group_id를 통해 UserGroup 관계 찾기 (그룹 나가기 기능용)
    Optional<UserGroup> findByUserUserIdAndGroupGroupId(Long userId, Long groupId);

    // 3. 특정 group_id에 속한 UserGroup 관계의 수를 반환 (사용자 수 확인용)
    long countByGroupGroupId(Long groupId);

    // 4. 특정 group에 속한 모든 사용자 조회
    List<UserGroup> findByGroupGroupId(Long groupId);

}