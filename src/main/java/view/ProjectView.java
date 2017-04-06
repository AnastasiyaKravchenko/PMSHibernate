package view;

import hibernate.HibernateProjectDAO;
import model.Project;

import java.io.IOException;

/**
 * Created by Mala on 3/30/2017.
 */
public class ProjectView extends View {

    HibernateProjectDAO projectDAO = new HibernateProjectDAO(sessionFactory);

    public void projectMenu() {
        int choice = 0;
        System.out.println("Select option. What would you like to do with company?\n");
        System.out.println("Enter 1 for show all companies,\n2-INSERT,\n3-UPDATE,\n4-DELETE\n5-return to start menu");

        printExitMessage();
        try {
            input = reader.readLine();
        } catch (IOException e) {
            LOGGER.error("IOException occurred:" + e.getMessage());
            projectMenu();
        }

        quickExit(input);

        try {
            choice = Integer.valueOf(input);
        } catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException occurred: " + e.getMessage());
            System.out.println("An incorrect value. Please try again.");
            projectMenu();
        }

        if (choice == 1) {
//            showAllProjects();
        } else if (choice == 2) {
            insertProject();
        } else if (choice == 3) {
            updateProject();
        } else if (choice == 4) {
            deleteProject();
        } else if (choice == 5) {
            new ConsoleHelper().displayStartMenu();
        } else {
            System.out.println("Incorrect value! Try again.\n");
            projectMenu();
        }
    }

//    private void showAllProjects() {
//        projectDAO.getAll().forEach(System.out::println);
//        projectMenu();
//    }


    private void insertProject() {
        Project project = new Project();
        System.out.println("Enter name for a new project");
        getInput();
        project.setName(input);
        projectDAO.save(project);
        projectMenu();
    }


    private void updateProject() {
        int choice = 0;
        System.out.println("Enter id of the project you want to update ");
        getInput();
        //проверка на правильность введения id
        try {
            Project project = projectDAO.getByID(Long.valueOf(input));
            if (project != null) {
                System.out.println("Chosen project name is: " + project.getName());
                System.out.println("Enter what you want to update in the project " + project.getName());
                System.out.println("Enter 1 for name,\n 2- description,\n 3-cost.\n ");

                try {
                    input = reader.readLine();
                } catch (IOException e) {
                    LOGGER.error("IOException occurred:" + e.getMessage());
                    projectMenu();
                }

                try {
                    choice = Integer.valueOf(input);
                } catch (NumberFormatException e) {
                    LOGGER.error("NumberFormatException occurred:" + e.getMessage());
                    System.out.println("An incorrect value. Please try again.");
                    projectMenu();
                }

                if (choice == 1) {
                    System.out.println("Enter new name for the project  ");
                    getInput();
                    project.setName(input);
                } else if (choice == 2) {
                    System.out.println("Enter new description for the project  ");
                    project.setDescription(input);
                } else if (choice == 3) {
                    System.out.println("Enter new cost for the project  ");
                    getInput();
                    project.setCost(Integer.parseInt(input));
                }
                projectDAO.update(project);
            } else
                throw new NumberFormatException("No one item with such id ");

        } catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException occurred:" + e.getMessage());
            System.out.println("An incorrect ID value. Please try again.");
        }
        projectMenu();
    }


    private void deleteProject() {
        System.out.println("Enter id of the project you want to delete ");
        getInput();
        try {
            Project project = projectDAO.getByID(Long.valueOf(input));
            if (project != null) {
                System.out.println("Chosen developer name is: " + project.getName());
                projectDAO.remove(project);
            } else
                throw new NumberFormatException("No one item with such id ");

        } catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException occurred:" + e.getMessage());
            System.out.println("An incorrect ID value. Please try again.");
        }
        projectMenu();
    }
}
