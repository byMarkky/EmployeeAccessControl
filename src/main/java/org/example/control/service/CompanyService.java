package org.example.control.service;

import org.example.control.dao.CompanyDao;
import org.example.control.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompanyService {

    private static final CompanyDao dao = new CompanyDao();
    private static final Logger log = LoggerFactory.getLogger(CompanyService.class);

    /**
     * Method to create the company
     * @param company Company object
     */
    public void createCompany(Company company) {
        if (dao.getByCif(company.getCif()) == null) {
            dao.create(company);
            log.debug("THERE IS NO COMPANY, CREATED ONE {}", company.getCif());
        } else {
            dao.update(company);
            log.debug("UPDATE THE COMPANY {} DATA", company.getCif());
        }
    }

    /**
     * Return the company with the current CIF/NIF exists
     * @return null if not exists, else, the company
     */
    public Company getFirst() {
        Company company;

        company = dao.getById(1);

        return company;
    }

    /**
     * Return the company with the current CIF/NIF exists
     * @param cif CIF or NIF of the company
     * @return null if not exists, else, the company
     */
    public Company getByCif(String cif) {
        Company company;

        company = dao.getByCif(cif);

        return company;
    }

    /**
     * Method to validate the company data before save it.
     * @param company Company object
     * @return True if the data is correct, false if not.
     */
    public boolean validate(Company company) {

        // Don't have to validate the work center or the company name

        String cif = company.getCif();
        String ccc = company.getcAccount();

        // If the length is not 9 OR the first character is not a letter
        // If the CCC have 3 sections of numbers OR the length is 11 plus the two /
        return (cif.length() == 9 && (cif.charAt(0) >= 'A' && cif.charAt(0) <= 'Z')) && (ccc.length() == 13);
    }

}
