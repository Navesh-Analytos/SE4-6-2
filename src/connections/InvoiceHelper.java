package connections;

import entities.Client;
import entities.Project;
import entities.ProjectInvoiceDate;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class InvoiceHelper {
    public InvoiceHelper(){
        ConnectToDB cm = new ConnectToDB();
        EntityManager em = cm.getEntityManager();
        Query query = em.createQuery("Select p  from Project p where p.status = 'In Progress'");
        List<Project> projectList = query.getResultList();
        
        for (Project project : projectList) {
            Date startDate = new Date(new java.util.Date(project.getStartDate()).getTime());
            Date endDate = new Date(new java.util.Date(project.getEndDate()).getTime());
            
            query = em.createQuery("Select MIN(ch.date) from ClockedHours ch where ch.projectID='"+ 
                    project.getId()+ "'");
            List<Date> dateList = query.getResultList();
            Date firstHourDate = (dateList==null || dateList.isEmpty() || dateList.get(0)==null)? 
                        startDate: dateList.get(0);
              
            Calendar firstDayOfStartWeek = Calendar.getInstance();                         
            firstDayOfStartWeek.setTime(firstHourDate);
            
            firstDayOfStartWeek.set(Calendar.DAY_OF_WEEK, 1);
            Client client = project.getClient();
            Calendar firstInvoiceDate = (Calendar) firstDayOfStartWeek.clone();
            
            Date dateOfFirstInvoice= null; 
            if(!client.getInvoiceFrequency().equals("Monthly-Cal")){
                firstInvoiceDate.set(Calendar.DAY_OF_WEEK, 1);
            }

            if(client.getInvoiceFrequency().equals("Weekly")){
                firstInvoiceDate.add(Calendar.DAY_OF_WEEK, 7);
            }
            else if(client.getInvoiceFrequency().equals("BiWeekly")){
                firstInvoiceDate.add(Calendar.DAY_OF_WEEK, 14);
            }
            else if(client.getInvoiceFrequency().equals("Monthly")){
                firstInvoiceDate.add(Calendar.DAY_OF_WEEK, 28);
            }
            else if(client.getInvoiceFrequency().equals("Monthly-Cal")){
                firstInvoiceDate.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                firstInvoiceDate.add(Calendar.MONTH, 1);
                firstInvoiceDate.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);               
            }

            if(!client.getInvoiceFrequency().equals("Monthly-Cal")){
                firstInvoiceDate.add(Calendar.DAY_OF_WEEK, 3);
            }
            dateOfFirstInvoice = new Date(firstInvoiceDate.getTimeInMillis());    
            
            if(project.getId()==1013){
                Calendar cal = Calendar.getInstance();
                cal.set(2016, 6, 6);
                dateOfFirstInvoice = new Date(cal.getTimeInMillis());
            }  
                        
            project.setFirstInvoiceDate(dateOfFirstInvoice); 
            ProjectInvoiceDate pInvDate = new ProjectInvoiceDate(project, project.getId(), 
                    dateOfFirstInvoice, project.getClient(), project.getClient().getName());
            em.persist(pInvDate);
            
                        
            Calendar nextInvoiceDate = Calendar.getInstance();
            nextInvoiceDate.setTime(em.find(Project.class, project.getId()).getFirstInvoiceDate());
            Date dateOfNextInvoice= null;
            while(dateOfNextInvoice== null || dateOfNextInvoice.before(endDate)  ){   
                if(!client.getInvoiceFrequency().equals("Monthly-Cal")){
                    nextInvoiceDate.set(Calendar.DAY_OF_WEEK, 1);
                }
            
                if(client.getInvoiceFrequency().equals("Weekly")){
                    nextInvoiceDate.add(Calendar.DAY_OF_WEEK, 7);
                }
                else if(client.getInvoiceFrequency().equals("BiWeekly")){
                    nextInvoiceDate.add(Calendar.DAY_OF_WEEK, 14);
                }
                else if(client.getInvoiceFrequency().equals("Monthly")){
                    nextInvoiceDate.add(Calendar.DAY_OF_WEEK, 28);
                }
                else if(client.getInvoiceFrequency().equals("Monthly-Cal")){
                    nextInvoiceDate.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                    nextInvoiceDate.add(Calendar.MONTH, 1);
                    nextInvoiceDate.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);               
                }
                
                if(!client.getInvoiceFrequency().equals("Monthly-Cal")){
                    nextInvoiceDate.add(Calendar.DAY_OF_WEEK, 3);
                }
                dateOfNextInvoice = new Date(nextInvoiceDate.getTimeInMillis());  
                
                pInvDate = new ProjectInvoiceDate(project, project.getId(), dateOfNextInvoice,
                                project.getClient(), project.getClient().getName());
                em.persist(pInvDate);
                
            } 
            project.setNextInvoiceDate(dateOfNextInvoice);
        }
        
        cm.close();
    }   
}
