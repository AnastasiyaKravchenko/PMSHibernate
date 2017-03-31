package main.java.view;

import main.java.hibernate.HibernateSkillDAO;
import main.java.model.Skill;

import java.io.IOException;

/**
 * Created by Mala on 3/30/2017.
 */
public class SkillView extends View {
    HibernateSkillDAO skillDAO = new HibernateSkillDAO(sessionFactory);

    void skillMenu() {
        int choice = 0;
        System.out.println("Select option. What would you like to do with company?\n");
        System.out.println("Enter 1 for show all companies,\n2-INSERT,\n3-UPDATE,\n4-DELETE\n5-return to start menu");

        printExitMessage();
        try {
            input = reader.readLine();
        } catch (IOException e) {
            LOGGER.error("IOException occurred:" + e.getMessage());
            skillMenu();
        }

        quickExit(input);

        try {
            choice = Integer.valueOf(input);
        } catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException occurred: " + e.getMessage());
            System.out.println("An incorrect value. Please try again.");
            skillMenu();
        }

        if (choice == 1) {
//            showAllSkills();
        } else if (choice == 2) {
            insertSkill();
        } else if (choice == 3) {
            updateSkill();
        } else if (choice == 4) {
            deleteSkill();
        } else if (choice == 5) {
            new ConsoleHelper().displayStartMenu();
        } else {
            System.out.println("Incorrect value! Try again.\n");
            skillMenu();
        }
    }

//    void showAllSkills() {
//        skillDAO.getAll().forEach(System.out::println);
//        skillMenu();
//    }

    void insertSkill() {
        Skill skill = new Skill();
        System.out.println("Enter description for a new skill ");
        getInput();
        skill.setDescription(input);
        skillDAO.save(skill);
        skillMenu();
    }

    void updateSkill() {
        int choice = 0;
        System.out.println("Enter name of skill you want to update ");
        getInput();
        try {
            Skill skill = skillDAO.getByID(Long.valueOf(input));
            if (skill != null) {
                System.out.println("Chosen skill name is: " + skill.getDescription());
                System.out.println("Enter new description for the skill ");
                getInput();
                skill.setDescription(input);
                skillDAO.update(skill);
            }else
                throw new NumberFormatException("No one item with such id ");
        }catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException occurred:" + e.getMessage());
            System.out.println("An incorrect ID value. Please try again.");
        }
        skillMenu();
    }

    void deleteSkill() {

        System.out.println("Enter name of the skill you want to delete");
        getInput();
        try {
            Skill skill=skillDAO.getByID(Long.valueOf(input));
            if (skill!=null){
                System.out.println("Chosen developer name is: " + skill.getDescription());
                skillDAO.remove(skill);
            }else
                throw new NumberFormatException("No one item with such id ");
        }catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException occurred:" + e.getMessage());
            System.out.println("An incorrect ID value. Please try again.");
        }
        skillMenu();
    }
}


