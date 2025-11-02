package com.personal.project.to_do_list.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "active_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "task")
    private String toDo;

    @Column(name = "start_date")
    private LocalDateTime startDateTime;

    public Task(){}

    public Task(String toDo, LocalDateTime startDateTime) {
        this.toDo = toDo;
        this.startDateTime = startDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", toDo='" + toDo + '\'' +
                ", startDateTime=" + startDateTime +
                '}';
    }
}
