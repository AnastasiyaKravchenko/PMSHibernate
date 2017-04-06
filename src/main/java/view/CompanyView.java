package view;

import hibernate.HibernateCompanyDAO;
import model.Company;

import java.io.IOException;

/**
 * Created by Mala on 3/30/2017.
 */
public class CompanyView extends View {

    HibernateCompanyDAO companyDAO = new HibernateCompanyDAO(sessionFactory);

    public void companyMenu() {
        int choice = 0;
        System.out.println("Select option. What would you like to do with company?\n");
        System.out.println("Enter 1 for show all companies,\n2-INSERT,\n3-UPDATE,\n4-DELETE\n5-return to start menu");

        printExitMessage();
        try {
            input = reader.readLine();
        } catch (IOException e) {
            LOGGER.error("IOException occurred:" + e.getMessage());
            companyMenu();
        }

        quickExit(input);

        try {
            choice = Integer.valueOf(input);
        } catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException occurred: " + e.getMessage());
            System.out.println("An incorrect value. Please try again.");
            companyMenu();
        }

        if (choice == 1) {
//            showAllCompany();
        } else if (choice == 2) {
            insertCompany();
        } else if (choice == 3) {
            updateCompany();
        } else if (choice == 4) {
            deleteCompany();
        } else if (choice == 5) {
            new ConsoleHelper().displayStartMenu();
        } else {
            System.out.println("Incorrect value! Try again.\n");
            companyMenu();
        }

    }

//    private void showAllCompany() {
//        companyDAO.getAll().forEach(System.out::println);
//        companyMenu();
//    }

    private void insertCompany() {

        Company company = new Company();
        System.out.println("Enter name  for a new company");
        getInput();
        company.setName(input);
        companyDAO.save(company);
        companyMenu();
    }


    private void updateCompany() {
        int choice = 0;
        System.out.println("Enter id of the company you want to update ");
        getInput();
        //проверка на правильность введения id
        try {
            Company company = companyDAO.getByID(Long.valueOf(input));
            if (company != null) {
                System.out.println("Chosen company name is: " + company.getName());
                System.out.println("Enter what you want to update in the company " + company.getName());
                System.out.println("Enter 1 for name,\n 2- address,\n 3-country,\n 4-city");


                try {
                    input = reader.readLine();
                } catch (IOException e) {
                    LOGGER.error("IOException occurred:" + e.getMessage());
                    companyMenu();
                }

                try {
                    choice = Integer.valueOf(input);
                } catch (NumberFormatException e) {
                    LOGGER.error("NumberFormatException occurred:" + e.getMessage());
                    System.out.println("An incorrect value. Please try again.");
                    companyMenu();
                }

                if (choice == 1) {
                    System.out.println("Enter new name for the company  ");
                    getInput();
                    company.setName(input);
                } else if (choice == 2) {
                    System.out.println("Enter new address for the company  ");
                    getInput();
                    company.setAddress(input);
                } else if (choice == 3) {
                    System.out.println("Enter new country for the company  ");
                    getInput();
                    company.setCountry(input);
                } else if (choice == 4) {
                    System.out.println("Enter new city for the company  ");
                    getInput();
                    company.setCity(input);
                }
                companyDAO.update(company);
            } else
                throw new NumberFormatException("No one item with such id ");

        } catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException occurred:" + e.getMessage());
            System.out.println("An incorrect ID value. Please try again.");
        }
        companyMenu();
    }


    private void deleteCompany() {
        System.out.println("Enter id of the company you want to delete ");
        getInput();
        try {
            Company company = companyDAO.getByID(Long.valueOf(input));
            if (company != null) {
                System.out.println("Chosen company name is: " + company.getName());
                companyDAO.remove(company);
            } else
                throw new NumberFormatException("No one item with such id ");
        } catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException occurred:" + e.getMessage());
            System.out.println("An incorrect ID value. Please try again.");
        }
        companyMenu();
    }
}
