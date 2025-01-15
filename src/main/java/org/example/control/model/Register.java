package org.example.control.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

//@Entity
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private LocalDateTime entry;

    @Column
    private LocalDateTime exit;

    @Column(nullable = false)
    private LocalDateTime creationTime;

    public Register() {
        this.creationTime = LocalDateTime.now();
    }

    public Register(LocalDateTime entry, LocalDateTime exit) {
        this.entry = entry;
        this.exit = exit;
        this.creationTime = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getEntry() {
        return entry;
    }

    public void setEntry(LocalDateTime entry) {
        this.entry = entry;
    }

    public LocalDateTime getExit() {
        return exit;
    }

    public void setExit(LocalDateTime exit) {
        this.exit = exit;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "Register{" +
                "id=" + id +
                ", entry=" + entry +
                ", exit=" + exit +
                ", creationTime=" + creationTime +
                '}';
    }
}
