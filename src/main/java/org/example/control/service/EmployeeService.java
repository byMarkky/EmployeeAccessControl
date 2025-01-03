package org.example.control.service;

import org.example.control.dao.EmployeeDao;
import org.example.control.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeService {

    private static final EmployeeDao dao = new EmployeeDao();
    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    public void createEmployee(Employee employee) {
        if (dao.getByDni(employee.getDni()) == null) {
            dao.create(employee);
            log.debug("THERE IS NO EMPLOYEE, CREATED ONE {}", employee.getDni());
        } else {
            dao.update(employee);
            log.debug("UPDATE THE EMPLOYEE {} DATA", employee.getDni());
        }
    }

    public boolean validate(Employee employee) {

        if (employee.getDni().length() != 9) return false;

        if (employee.getName().isEmpty() || employee.getName() == null) return false;

        if (employee.getSurname().isEmpty() || employee.getSurname() == null) return false;

        if (employee.getSchedule().isEmpty() || employee.getSchedule() == null) return false;

        if (employee.getAffiliateNumber().length() != 14) return false;

        return true;
    }

    public boolean haveEmployees() {
        return !dao.getAll().isEmpty();
    }

}
