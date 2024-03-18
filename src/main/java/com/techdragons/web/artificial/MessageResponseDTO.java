package com.techdragons.web.artificial;

public class MessageResponseDTO {
    private String reply; // Ответ системы

    // Конструкторы и геттеры/сеттеры
    public MessageResponseDTO(String reply) {
        this.reply = reply;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
