package test;

import entities.Employee;
import org.junit.Test;
import org.junit.Assert;

public class TestEmployee {
    
    @Test
    public void testEmployee(){
        Employee employee = new Employee();     
        String name = "Benton Skursky";
        employee.setName(name);
        String title = "Developer";
        employee.setTitle(title);
        int billRate =  75;
        employee.setBillRate(billRate);
        String role = "Quality Assurance Spec.";
        employee.setEmpRole(role);
                
        
        Assert.assertEquals(name, employee.getName());
        Assert.assertEquals(title, employee.getTitle());
        Assert.assertEquals(billRate, employee.getBillRate());
        Assert.assertEquals(role, employee.getEmpRole());
    }
}


