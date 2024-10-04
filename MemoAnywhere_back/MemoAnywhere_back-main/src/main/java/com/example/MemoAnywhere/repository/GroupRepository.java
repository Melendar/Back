package com.example.MemoAnywhere.repository;

import com.example.MemoAnywhere.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByGroupIdIn(List<Long> groupIds);
}