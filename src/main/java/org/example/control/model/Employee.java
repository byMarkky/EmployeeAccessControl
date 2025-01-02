package org.example.control.model;

public class Employee {

    private String dni;
    private String name;
    private String surname;
    private String affiliateNumber;
    private String schedule;
    private String entryDate;
    private String exitDate;
    private String afternoon_shift;
    private String afterEntry;
    private String afterExit;
    private byte overtime;
    private String note;

    public Employee() {
    }

    public Employee(String dni, String name, String surname, String affiliateNumber, String schedule, String entryDate,
                    String exitDate, String afternoon_shift, String afterEntry, String afterExit, byte overtime, String note) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.affiliateNumber = affiliateNumber;
        this.schedule = schedule;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.afternoon_shift = afternoon_shift;
        this.afterEntry = afterEntry;
        this.afterExit = afterExit;
        this.overtime = overtime;
        this.note = note;
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

    public String getAfternoon_shift() {
        return afternoon_shift;
    }

    public void setAfternoon_shift(String afternoon_shift) {
        this.afternoon_shift = afternoon_shift;
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
                ", afternoon_shift='" + afternoon_shift + '\'' +
                ", afterEntry='" + afterEntry + '\'' +
                ", afterExit='" + afterExit + '\'' +
                ", overtime=" + overtime +
                ", note='" + note + '\'' +
                '}';
    }
}
