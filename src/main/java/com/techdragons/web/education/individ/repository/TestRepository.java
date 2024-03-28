package com.techdragons.web.education.individ.repository;

import com.techdragons.web.education.individ.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}
