package test;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

class TestDriverClass{
    public static void main(String[] args) {
      Result result = JUnitCore.runClasses(
              TestProject.class, 
              TestEmployee.class, 
              TestClient.class);		
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }		
      System.out.println(result.wasSuccessful());
   }
    
}