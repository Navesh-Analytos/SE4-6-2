package connections;

import java.sql.Date;
import java.util.Calendar;
import java.util.Scanner;
import entities.Employee;
import entities.Project;
import entities.ClockedHours;
import java.io.File;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;

public class LoadHoursFromCsv {
    
    public LoadHoursFromCsv(String filePath){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("InvoiceGenerationNewPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();       
         
        try {
            Scanner sc = new Scanner(new File(filePath));
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
            
            while (sc.hasNextLine()) {
                ClockedHours wh;
                Scanner line = new Scanner(sc.nextLine());
                line.useDelimiter(",");  
                
                Calendar cal = Calendar.getInstance();
                cal.set(2016, 5, 27);
                                
                int projectId = Integer.parseInt(line.next());
                String empName = line.next();
                while(line.hasNext()){
                    String s = line.next();
                    if(!s.equals("")){
                        Date date = new Date(cal.getTimeInMillis());
                        int hours = Integer.parseInt(s);
                        wh =  new ClockedHours(projectId+ empName +date, em.find(Employee.class, empName), 
                        empName, em.find(Project.class, projectId),
                        projectId, hours, date, false, false, null);
                        
                        em.persist(wh);
                    }
                    cal.add(Calendar.DAY_OF_WEEK, 1); 
                }
                
                line.close();
            }
            sc.close();  
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something went wrong while Loading Hours from CSV file!");
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.getTransaction().commit();
            em.close();
            emf.close();
        }
    }  
    
}