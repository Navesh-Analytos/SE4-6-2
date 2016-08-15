package connections;

import entities.Client;
import entities.Employee;
import entities.Project;
import java.io.File;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;

public class LoadProjectFromCsv {
    
    public LoadProjectFromCsv(String filePath){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("InvoiceGenerationPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();    
        try {
            Scanner sc = new Scanner(new File(filePath));
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
            while (sc.hasNextLine()) {
                Project project;
                Scanner line = new Scanner(sc.nextLine());
                line.useDelimiter(",");  
                Employee emp;
                project = new Project(em.find(Client.class, line.nextInt()), line.nextInt(), 
                    line.next(), line.next(), line.next(), line.next(), 
                    emp = em.find(Employee.class, line.next()), emp.getName(),
                    line.next(), line.nextInt());
                line.close();
                em.persist(project);
            }
            sc.close();   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occured while Loading Project data from CSV file!");
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.getTransaction().commit();
            em.close();
            emf.close();
        }
    }
}