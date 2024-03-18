package com.techdragons.web.artificial;

public class MessageRequestDTO {
    private Long studentId; // Идентификатор студента (пользователя)
    private String message; // Сообщение от пользователя

    // Геттеры и сеттеры
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
