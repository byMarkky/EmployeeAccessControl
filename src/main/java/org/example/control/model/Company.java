package org.example.control.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CIF", unique = true, nullable = false)
    private String cif;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String cAccount;    // Código de cuenta de cotización

    @Column
    private String workCenter;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private List<Employee> employeeList;

    public Company() {
    }

    public Company(String cif, String name, String cAccount, String workCenter) {
        this.cif = cif;
        this.name = name;
        this.cAccount = cAccount;
        this.workCenter = workCenter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcAccount() {
        return cAccount;
    }

    public void setcAccount(String cAccount) {
        this.cAccount = cAccount;
    }

    public String getWorkCenter() {
        return workCenter;
    }

    public void setWorkCenter(String workCenter) {
        this.workCenter = workCenter;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Company{" +
                "cif='" + cif + '\'' +
                ", name='" + name + '\'' +
                ", cAccount='" + cAccount + '\'' +
                ", workCenter='" + workCenter + '\'' +
                '}';
    }
}
