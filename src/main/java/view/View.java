package view;

import hibernate.HibernateSessionFactory;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Mala on 3/30/2017.
 */
public class View {
    SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

     static Logger LOGGER = LoggerFactory.getLogger(View.class);

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    String input;

     void printExitMessage() {
        System.out.println("To close application enter 'quit'");
    }

     void splitLine() {
        System.out.println("\n--------------------------------------------\n");
    }

    void quickExit(String str) {
        if (str.equals("quit")) {
            HibernateSessionFactory.shutdown();
            System.exit(0);
        }
    }

    void getInput(){
        try {
            input=reader.readLine();
        }catch (IOException e){
            LOGGER.error("IOException occurred"+e);
        }
    }
}
