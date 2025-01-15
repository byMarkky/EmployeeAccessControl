package org.example.control.model;

import jakarta.persistence.*;

import java.time.LocalTime;

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
    private LocalTime entryDate;

    @Column
    private LocalTime exitDate;

    @Column
    private String afternoonShift;

    @Column
    private LocalTime afterEntry;

    @Column
    private LocalTime afterExit;

    @Column
    private byte overtime;

    @Column
    private String note;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Employee() {
    }

    public Employee(String dni, String name, String surname, String affiliateNumber, String schedule, String afternoonShift) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.affiliateNumber = affiliateNumber;
        setSchedule(schedule);
        setAfternoonShift(afternoonShift);
    }

    public Employee(String dni, String name, String surname, String affiliateNumber, String schedule,
                    String afternoonShift, byte overtime, String note) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.affiliateNumber = affiliateNumber;
        this.schedule = schedule;

        String[] split = this.schedule.split("-");
        this.entryDate = LocalTime.parse(split[0].trim());
        this.exitDate = LocalTime.parse(split[1].trim());
        setAfternoonShift(afternoonShift);

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
        String[] splited = schedule.split("-");
        String[] entry = splited[0].split(":");
        String[] exit = splited[1].split(":");
        this.entryDate = LocalTime.of(Integer.parseInt(entry[0]), Integer.parseInt(entry[1]), 0);
        this.exitDate = LocalTime.of(Integer.parseInt(exit[0]), Integer.parseInt(exit[1]), 0);
    }

    public LocalTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalTime entryDate) {
        this.entryDate = entryDate;
    }

    public LocalTime getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalTime exitDate) {
        this.exitDate = exitDate;
    }

    public String getAfternoonShift() {
        return afternoonShift;
    }

    public void setAfternoonShift(String afternoonShift) {
        this.afternoonShift = afternoonShift;
        String[] splited = this.afternoonShift.split("-");
        String[] entry = splited[0].split(":");
        String[] exit = splited[1].split(":");
        this.afterEntry = LocalTime.of(Integer.parseInt(entry[0]), Integer.parseInt(entry[1]), 0);
        this.afterExit = LocalTime.of(Integer.parseInt(exit[0]), Integer.parseInt(exit[1]), 0);
    }

    public LocalTime getAfterEntry() {
        return afterEntry;
    }

    public void setAfterEntry(LocalTime afterEntry) {
        this.afterEntry = afterEntry;
    }

    public LocalTime getAfterExit() {
        return afterExit;
    }

    public void setAfterExit(LocalTime afterExit) {
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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
