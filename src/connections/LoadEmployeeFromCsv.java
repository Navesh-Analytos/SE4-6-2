package connections;

import entities.Employee;
import entities.LoginCredentials;
import java.io.File;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;

public class LoadEmployeeFromCsv {
    
    public LoadEmployeeFromCsv(String filePath){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("InvoiceGenerationPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();       
        
        Employee emp;
        LoginCredentials loginCred;
        emp = new Employee("Accountant", "Accountant", 0, "Accountant");
        loginCred = new LoginCredentials("accountant", "qwerty", emp.getEmpRole(), emp);
        em.persist(emp);
        em.persist(loginCred);
        
        try {
            Scanner sc = new Scanner(new File(filePath));
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
            while (sc.hasNextLine()) {
                Scanner line = new Scanner(sc.nextLine());
                line.useDelimiter(",");                
                emp = new Employee(line.next(), line.next(), line.nextInt(), line.next());
                line.close();
                String[] empName = emp.getName().split(" ");
                loginCred = new LoginCredentials(
                        empName[1].toLowerCase(),
                        "qwerty", emp.getEmpRole(), emp);
                em.persist(emp);
                em.persist(loginCred);
            }
            sc.close();   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occured while Loading Employee data from CSV file!");
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.getTransaction().commit();
            em.close();
            emf.close();
        }
    }
    
}