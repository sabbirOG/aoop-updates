package com.cse.project.repository;

import com.cse.project.entity.ThreadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadEntityRepository extends JpaRepository<ThreadEntity, Long> {
}
