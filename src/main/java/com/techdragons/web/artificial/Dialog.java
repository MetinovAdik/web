package com.techdragons.web.artificial;

import java.util.List;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Dialog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long student;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dialog")
    private List<Message> messages;

    // Конструктор по умолчанию требуется для JPA
    public Dialog() {}

    // Конструктор с параметрами для удобства создания экземпляра
    public Dialog(Long student, List<Message> messages) {
        this.student = student;
        this.messages = messages;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudent() {
        return student;
    }

    public void setStudent(Long student) {
        this.student = student;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addNewMessage(String message, Sender sender){
        Message newMessage = new Message();
        newMessage.setMessage(message);
        newMessage.setSender(sender);
        newMessage.setDialog(this); // Установка обратной связи с диалогом
        this.messages.add(newMessage);
    }
}
