package com.techdragons.web.artificial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DialogRepository extends JpaRepository<Dialog, Long> {
    // Здесь вы можете добавить методы для поиска диалогов по различным критериям
    // Например, поиск диалога по идентификатору студента:
    Optional<Dialog> findByStudent(Long studentId);
}