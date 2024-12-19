package org.example.control.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduleController {

    private static final Logger log = LoggerFactory.getLogger(ScheduleController.class);

    @FXML
    private Button entryButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button showDataButton;

    @FXML
    private Label employeeLabel;

    private String employee;

    @FXML
    public void initialize() {
    }

    public void setEmployee(String employee) {
        this.employee = employee;
        if (employee != null && !employee.isEmpty()) {
            employeeLabel.setText(employee);
            log.info("{}", employee);
        } else {
            log.info("NO EMPLOYEE");
        }
    }

}
