package com.techdragons.web.education.ai.repository;

import com.techdragons.web.education.ai.AITest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AITestRepository extends JpaRepository<AITest, Long> {
}
