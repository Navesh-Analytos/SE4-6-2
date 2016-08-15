package connections;

import entities.Client;
import java.io.File;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;

public class LoadClientFromCsv {
    
    public LoadClientFromCsv(String filePath){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("InvoiceGenerationPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); 
        try {
            Scanner sc = new Scanner(new File(filePath));            
            if (sc.hasNextLine()) {
                sc.nextLine();
            }            
            while (sc.hasNextLine()) {
                Client client;
                Scanner line = new Scanner(sc.nextLine());
                line.useDelimiter(",");                
                client = new Client(line.nextInt(), line.next(), line.next(), line.next(),
                        line.next(), line.next(), line.nextInt(), line.next(), line.next(),
                        line.next(), line.next(), line.next());
                line.close();
                em.persist(client);
            }
            sc.close();  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occured while Loading Client data from CSV file!");
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.getTransaction().commit();
            em.close();
            emf.close();
        }
    }
    
}