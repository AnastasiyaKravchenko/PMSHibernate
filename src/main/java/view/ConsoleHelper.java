package view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Mala on 3/30/2017.
 */
public class ConsoleHelper extends View{

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Logger LOGGER = LoggerFactory.getLogger(ConsoleHelper.class);
    private String input;

    private CompanyView companyView = new CompanyView();
    private CustomerView customerView = new CustomerView();
    private DeveloperView developerView = new DeveloperView();
    private ProjectView projectView = new ProjectView();
    private SkillView skillView = new SkillView();

    public void displayStartMenu() {
        int choice = 0;

        System.out.println(" Enter what data do you want to operate with: ");
        System.out.println("1-Company\n2-Customer\n3-Developer\n4-Skill\n5-Project");
        System.out.println("Enter number according to your choice");

        try {
            input = reader.readLine();
        } catch (IOException e) {
            LOGGER.error("IOException occurred:" + e.getMessage());
            displayStartMenu();
        }

        try {
            choice = Integer.valueOf(input);
        } catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException occurred: " + e.getMessage());
            System.out.println("An incorrect value. Please try again.");
            displayStartMenu();
        }

        if (choice == 1) {
            companyView.companyMenu();
        } else if (choice == 2) {
            customerView.customerMenu();
        } else if (choice == 3) {
            developerView.developerMenu();
        } else if (choice == 4) {
            skillView.skillMenu();
        } else if (choice == 5) {
            projectView.projectMenu();
        }else
            System.out.println("Incorrect value!Try again.");
        displayStartMenu();

    }

}

