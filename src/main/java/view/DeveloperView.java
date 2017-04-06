package view;

import hibernate.HibernateDeveloperDAO;
import model.Developer;

import java.io.IOException;

/**
 * Created by Mala on 3/30/2017.
 */
public class DeveloperView extends View {
    HibernateDeveloperDAO developerDAO = new HibernateDeveloperDAO(sessionFactory);

    public void developerMenu() {
        int choice = 0;
        System.out.println("Select option. What would you like to do with developer?\n");
        System.out.println("Enter 1 for show all companies,\n2-INSERT,\n3-UPDATE,\n4-DELETE\n5-return to start menu");

        printExitMessage();
        try {
            input = reader.readLine();
        } catch (IOException e) {
            LOGGER.error("IOException occurred:" + e.getMessage());
            deleteDeveloper();
        }

        quickExit(input);

        try {
            choice = Integer.valueOf(input);
        } catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException occurred: " + e.getMessage());
            System.out.println("An incorrect value. Please try again.");
            deleteDeveloper();
        }

        if (choice == 1) {
//            showAllDeveloper();
        } else if (choice == 2) {
            insertDeveloper();
        } else if (choice == 3) {
            updatDeveloper();
        } else if (choice == 4) {
            deleteDeveloper();
        } else if (choice == 5) {
            new ConsoleHelper().displayStartMenu();
        } else {
            System.out.println("Incorrect value! Try again.\n");
            developerMenu();
        }
    }

//    private void showAllDevelopers() {
//        developerDAO.getAll().forEach(System.out::println);
//        developerMenu();
//    }


    private void insertDeveloper() {
        Developer developer = new Developer();
        System.out.println("Enter name for a new developer");
        getInput();
        developer.setName(input);
        developerDAO.save(developer);
        developerMenu();
    }


    private void updatDeveloper() {
        int choice = 0;
        System.out.println("Enter id of the developer you want to update ");
        getInput();
        //проверка на правильность введения id
        try {
            Developer developer = developerDAO.getByID(Long.valueOf(input));
            if (developer != null) {
                System.out.println("Chosen developer name is: " + developer.getName());
                System.out.println("Enter what you want to update in the developer " + developer.getName());
                System.out.println("Enter 1 for name,\n 2- age,\n 3-country,\n 4-city,\n 5-salary ");

                try {
                    input = reader.readLine();
                } catch (IOException e) {
                    LOGGER.error("IOException occurred:" + e.getMessage());
                    developerMenu();
                }

                try {
                    choice = Integer.valueOf(input);
                } catch (NumberFormatException e) {
                    LOGGER.error("NumberFormatException occurred:" + e.getMessage());
                    System.out.println("An incorrect value. Please try again.");
                    developerMenu();
                }

                if (choice == 1) {
                    System.out.println("Enter new name for the developer  ");
                    getInput();
                    developer.setName(input);
                } else if (choice == 2) {
                    System.out.println("Enter new age for the developer  ");
                    developer.setAge(Integer.parseInt(input));
                } else if (choice == 3) {
                    System.out.println("Enter new country for the developer  ");
                    getInput();
                    developer.setCountry(input);
                } else if (choice == 4) {
                    System.out.println("Enter new city for the developer  ");
                    getInput();
                    developer.setCity(input);
                } else if (choice == 5) {
                    System.out.println("Enter new salary for the developer  ");
                    getInput();
                    developer.setSalary(Integer.parseInt(input));
                }

                developerDAO.update(developer);
            } else
                throw new NumberFormatException("No one item with such id ");

        } catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException occurred:" + e.getMessage());
            System.out.println("An incorrect ID value. Please try again.");
        }
        developerMenu();
    }


    private void deleteDeveloper() {
        System.out.println("Enter id of the developer you want to delete ");
        getInput();
        try {
            Developer developer = developerDAO.getByID(Long.valueOf(input));
            if (developer != null) {
                System.out.println("Chosen developer name is: " + developer.getName());
                developerDAO.remove(developer);
            } else
                throw new NumberFormatException("No one item with such id ");

        } catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException occurred:" + e.getMessage());
            System.out.println("An incorrect ID value. Please try again.");
        }
        developerMenu();
    }
}
