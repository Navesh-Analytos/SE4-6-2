package connections;

import entities.Employee;
import entities.Project;
import entities.ProjectPerson;
import java.io.File;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;

public class LoadProjectPersonFromCsv {
    
    public LoadProjectPersonFromCsv(String filePath){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("InvoiceGenerationPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();       
        try {
            Scanner sc = new Scanner(new File(filePath));
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
            while (sc.hasNextLine()) {
                ProjectPerson projectPerson;
                Scanner line = new Scanner(sc.nextLine());
                line.useDelimiter(",");  
                Project project = em.find(Project.class, line.nextInt());
                String empName = line.next();
                projectPerson = new ProjectPerson(project, project.getId(),  em.find(Employee.class, empName), 
                        empName, "Yes", project.getProjectManager().getName());
                line.close();
                em.persist(projectPerson);
            }
            sc.close();   
                        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occured while Loading ProjectPerson data from CSV file!");
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.getTransaction().commit();
            em.close();
            emf.close();
        }
    }
    
}