package connections;

public class LoadAllDataFromCsv {    
    public static void main(String args[]){        
        new LoadCompanyFromCsv("data/company_data.csv");
        new LoadClientFromCsv("data/client_data.csv");
        new LoadEmployeeFromCsv("data/people_data.csv");
        new LoadProjectFromCsv("data/project_data.csv");  
        new LoadProjectPersonFromCsv("data/project_person.csv");   
    }    
}
