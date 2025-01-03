package org.example.control.model;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "DNI", nullable = false, unique = true)
    private String dni;

    @Column(nullable = false)
    private String name;

    @Column
    private String surname;

    @Column(nullable = false, unique = true)
    private String affiliateNumber;

    @Column(nullable = false)
    private String schedule;

    @Column
    private String entryDate;

    @Column
    private String exitDate;

    @Column
    private String afternoonShift;

    @Column
    private String afterEntry;

    @Column
    private String afterExit;

    @Column
    private byte overtime;

    @Column
    private String note;

    public Employee() {
    }

    public Employee(String dni, String name, String surname, String affiliateNumber, String schedule, String afternoonShift) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.affiliateNumber = affiliateNumber;
        this.schedule = schedule;
        this.afternoonShift = afternoonShift;
    }

    public Employee(String dni, String name, String surname, String affiliateNumber, String schedule, String entryDate,
                    String exitDate, String afternoonShift, String afterEntry, String afterExit, byte overtime, String note) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.affiliateNumber = affiliateNumber;
        this.schedule = schedule;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.afternoonShift = afternoonShift;
        this.afterEntry = afterEntry;
        this.afterExit = afterExit;
        this.overtime = overtime;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAffiliateNumber() {
        return affiliateNumber;
    }

    public void setAffiliateNumber(String affiliateNumber) {
        this.affiliateNumber = affiliateNumber;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getExitDate() {
        return exitDate;
    }

    public void setExitDate(String exitDate) {
        this.exitDate = exitDate;
    }

    public String getAfternoonShift() {
        return afternoonShift;
    }

    public void setAfternoonShift(String afternoonShift) {
        this.afternoonShift = afternoonShift;
    }

    public String getAfterEntry() {
        return afterEntry;
    }

    public void setAfterEntry(String afterEntry) {
        this.afterEntry = afterEntry;
    }

    public String getAfterExit() {
        return afterExit;
    }

    public void setAfterExit(String afterExit) {
        this.afterExit = afterExit;
    }

    public byte getOvertime() {
        return overtime;
    }

    public void setOvertime(byte overtime) {
        this.overtime = overtime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", affiliateNumber='" + affiliateNumber + '\'' +
                ", schedule='" + schedule + '\'' +
                ", entryDate='" + entryDate + '\'' +
                ", exitDate='" + exitDate + '\'' +
                ", afternoon_shift='" + afternoonShift + '\'' +
                ", afterEntry='" + afterEntry + '\'' +
                ", afterExit='" + afterExit + '\'' +
                ", overtime=" + overtime +
                ", note='" + note + '\'' +
                '}';
    }
}
