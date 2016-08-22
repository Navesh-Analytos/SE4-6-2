package test;

import org.junit.Test;
import org.junit.Assert;
import entities.Project;

public class TestProject {
    
    Project project;
    
    @Test
    public void testProject(){
        project = new Project();
        String name = "Mobile App";      
        String status = "In Progress";                    
        String  managerName = "Jamal Vanausdal";
        String clientContact = "Mattie Poquette";
        int budget = 480000;
        
        project.setName(name);
        project.setClientContact(clientContact);
        project.setManagerName(managerName);
        project.setStatus(status);
        project.setBudget(budget);
        
     
        
        Assert.assertEquals(name, project.getName());
        Assert.assertEquals(managerName, project.getManagerName());
        Assert.assertEquals(clientContact, project.getClientContact());
        Assert.assertEquals(status, project.getStatus());
        Assert.assertEquals(budget, project.getBudget());
    }
    
}

