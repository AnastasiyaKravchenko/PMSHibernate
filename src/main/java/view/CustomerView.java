package view;

import hibernate.HibernateCustomerDAO;
import model.Customer;

import java.io.IOException;

/**
 * Created by Mala on 3/30/2017.
 */
public class CustomerView extends View {

    HibernateCustomerDAO customerDAO = new HibernateCustomerDAO(sessionFactory);

    public void customerMenu() {
        int choice = 0;
        System.out.println("Select option. What would you like to do with customer?\n");
        System.out.println("Enter 1 for show all customers,\n2-INSERT,\n3-UPDATE,\n4-DELETE\n5-return to start menu");

        printExitMessage();
        try {
            input = reader.readLine();
        } catch (IOException e) {
            LOGGER.error("IOException occurred:" + e.getMessage());
            customerMenu();
        }

        quickExit(input);

        try {
            choice = Integer.valueOf(input);
        } catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException occurred: " + e.getMessage());
            System.out.println("An incorrect value. Please try again.");
            customerMenu();
        }

        if (choice == 1) {
//            showAllCustomer();
        } else if (choice == 2) {
            insertCustomer();
        } else if (choice == 3) {
            updateCustomer();
        } else if (choice == 4) {
            deleteCustomer();
        } else if (choice == 5) {
            new ConsoleHelper().displayStartMenu();
        } else customerMenu();
    }

//    private void showAllCustomer() {
//        customerDAO.getAll().forEach(System.out::println);
//        customerMenu();
//    }


    private void insertCustomer() {

        Customer customer=new Customer();
        System.out.println("Enter name for a new customer");
        getInput();
        customer.setName(input);
        customerDAO.save(customer);
        customerMenu();
    }


    private void updateCustomer() {
        int choice = 0;
        System.out.println("Enter id of the customer you want to update ");
        getInput();
        //проверка на правильность введения id
        try {
            Customer customer = customerDAO.getByID(Long.valueOf(input));
            if (customer != null) {
                System.out.println("Chosen customer name is: " + customer.getName());
                System.out.println("Enter what you want to update in the customer " + customer.getName());
                System.out.println("Enter 1 for name,\n 2- inn,\n 3-edrpou ");

                try {
                    input = reader.readLine();
                } catch (IOException e) {
                    LOGGER.error("IOException occurred:" + e.getMessage());
                    customerMenu();
                }

                try {
                    choice = Integer.valueOf(input);
                } catch (NumberFormatException e) {
                    LOGGER.error("NumberFormatException occurred:" + e.getMessage());
                    System.out.println("An incorrect value. Please try again.");
                    customerMenu();
                }

                if (choice == 1) {
                    System.out.println("Enter new name for the customer  ");
                    getInput();
                    customer.setName(input);
                } else if (choice == 2) {
                    System.out.println("Enter new inn for the customer  ");
                    getInput();
                    customer.setInn(Integer.parseInt(input));
                } else if (choice == 3) {
                    System.out.println("Enter new edrpou for the customer  ");
                    getInput();
                    customer.setEdrpou(Integer.parseInt(input));
                }
                customerDAO.update(customer);
            } else
                throw new NumberFormatException("No one item with such id ");

        } catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException occurred:" + e.getMessage());
            System.out.println("An incorrect ID value. Please try again.");
        }
        customerMenu();
    }


    private void deleteCustomer() {
        System.out.println("Enter id of the customer you want to delete ");
        getInput();
        try {
            Customer customer= customerDAO.getByID(Long.valueOf(input));
            if (customer != null){
                System.out.println("Chosen customer name is: " + customer.getName());
                customerDAO.remove(customer);
            }
            else
                throw new NumberFormatException("No one item with such id ");

        } catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException occurred:" + e.getMessage());
            System.out.println("An incorrect ID value. Please try again.");
        }
        customerMenu();
    }
}
