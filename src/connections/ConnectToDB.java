package connections;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;

public class ConnectToDB {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public ConnectToDB() {
        entityManagerFactory = javax.persistence.Persistence.createEntityManagerFactory("InvoiceGenerationNewPU");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }
    
    public void close(){
      entityManager.getTransaction( ).commit( );
      entityManager.close( );
      entityManagerFactory.close( );
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }   
}
