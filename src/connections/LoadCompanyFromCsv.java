package connections;

import entities.Company;
import java.io.File;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;

public class LoadCompanyFromCsv {
    
    public LoadCompanyFromCsv(String filePath){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("InvoiceGenerationNewPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();       
         
        try {
            Scanner sc = new Scanner(new File(filePath));
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
            while (sc.hasNextLine()) {
                Company comp;
                Scanner line = new Scanner(sc.nextLine());
                line.useDelimiter(",");                
                comp = new Company(line.next(), line.next(), line.next(), line.next(), line.next(), line.nextInt());
                line.close(); 
                em.persist(comp);
            }
            sc.close();   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occured in loading Company data from CSV file!");
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.getTransaction().commit();
            em.close();
            emf.close();
        }
    }
    
}