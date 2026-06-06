package com.cse.project.repository;

import com.cse.project.entity.ThreadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadEntityRepository extends JpaRepository<ThreadEntity, Long> {
    List<ThreadEntity> findByChannelId(Long channelId);
}
