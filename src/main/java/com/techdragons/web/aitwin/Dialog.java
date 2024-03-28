package com.techdragons.web.aitwin;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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


    public void addNewMessage(String message, Sender sender){
        Message newMessage = new Message();
        newMessage.setMessage(message);
        newMessage.setSender(sender);
        newMessage.setDialog(this); // Установка обратной связи с диалогом
        this.messages.add(newMessage);
    }
}
