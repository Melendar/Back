package com.example.Melendar.repository;

import com.example.Melendar.domain.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    List<UserGroup> findByGroup_GroupId(Long groupId);
    List<UserGroup> findByUser_Id(Long userId);
}