package forms;


import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import connections.SystemData;
import javax.swing.JFrame;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import connections.ConnectToDB;
import java.awt.Image;
import entities.ProjectPerson;
import entities.ClockedHours;
import entities.InvoiceToProject;
import java.awt.Component;
import java.awt.Font;
import entities.Client;
import entities.Invoice;
import entities.InvoiceLineItem;
import entities.Project;

public class GenerateInvoice extends javax.swing.JPanel {
	private javax.swing.JLabel dateLabel;
    private javax.swing.JButton generate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton mail;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton prevButton;
	
    JFrame  panelHolder;
    SystemData systemData;
    
    DefaultTableModel defaultTableModel;
    Object[][] rows;
    Calendar selectedInvoiceDateCal;
    ArrayList projectListForInvoiceGrouping;
    List<String> listOfClientName;
    List<ProjectPerson> listOfProjectPerson;
    List<Long> listOfClockedHours;
        
    public GenerateInvoice(JFrame  panelHolder, SystemData systemData) {
        this.panelHolder = panelHolder;
        this.systemData = systemData;  
        initComponents();
        try{
            prevButton.setIcon(new ImageIcon(ImageIO.read( new File("data/prev.jpg")).
                getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        }catch(IOException e){
            System.out.println("Previous Button Icon not Found!");
        }
        try{
            nextButton.setIcon(new ImageIcon(ImageIO.read( new File("data/next.jpg")).
                getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        }catch(IOException e){
            System.out.println("Previous Button Icon not Found!");
        }
                
        selectedInvoiceDateCal = Calendar.getInstance();
        selectedInvoiceDateCal.set(Calendar.DAY_OF_WEEK, 4);   

        refreshTable();       
        
    }
            
    private void initComponents() {

jPanel2 = new javax.swing.JPanel();
jPanel1 = new javax.swing.JPanel();
jScrollPane1 = new javax.swing.JScrollPane();
jTable1 = new javax.swing.JTable();
jLabel1 = new javax.swing.JLabel();
prevButton = new javax.swing.JButton();
dateLabel = new javax.swing.JLabel();
nextButton = new javax.swing.JButton();
generate = new javax.swing.JButton();
mail = new javax.swing.JButton();

jTable1.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
jScrollPane1.setViewportView(jTable1);

javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
jPanel1.setLayout(jPanel1Layout);
jPanel1Layout.setHorizontalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
  .addComponent(jScrollPane1)
  .addGap(0, 0, 0))
);
jPanel1Layout.setVerticalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
  .addContainerGap()
  .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
  .addContainerGap(38, Short.MAX_VALUE))
);

jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
jLabel1.setText("Invoices on");

prevButton.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
prevButton.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
  prevButtonActionPerformed(evt);
}
});

dateLabel.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
dateLabel.setText("07/18/2016");

nextButton.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
nextButton.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
  nextButtonActionPerformed(evt);
}
});

generate.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
generate.setText("Generate Invoice");
generate.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
  generateActionPerformed(evt);
}
});

mail.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
mail.setText("Mail Invoice");
mail.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
  mailActionPerformed(evt);
}
});

javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
jPanel2.setLayout(jPanel2Layout);
jPanel2Layout.setHorizontalGroup(
jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel2Layout.createSequentialGroup()
  .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
          .addGap(67, 67, 67)
          .addComponent(jLabel1)
          .addGap(18, 18, 18)
          .addComponent(prevButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
          .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGap(18, 18, 18)
          .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGap(60, 60, 60)
          .addComponent(generate, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(mail))
      .addGroup(jPanel2Layout.createSequentialGroup()
          .addContainerGap()
          .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
  .addContainerGap())
);
jPanel2Layout.setVerticalGroup(
jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel2Layout.createSequentialGroup()
  .addGap(14, 14, 14)
  .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(nextButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(prevButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(dateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
          .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(generate)
          .addComponent(mail)))
  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
  .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
  .addContainerGap())
);

javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
this.setLayout(layout);
layout.setHorizontalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
  .addContainerGap()
  .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
  .addContainerGap())
);
layout.setVerticalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
  .addContainerGap()
  .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
  .addContainerGap())
);
}// </editor-fold>                        

    private void refreshTable(){
        Calendar cal = (Calendar) selectedInvoiceDateCal.clone();
        Date invoiceDate = new Date( cal.getTimeInMillis());
        jLabel1.setText("Invoices on: ");
        dateLabel.setText(new SimpleDateFormat("MM-dd-yyyy").format(invoiceDate));
        
        ConnectToDB cm = new ConnectToDB();
        EntityManager em = cm.getEntityManager();
        Query query = em.createQuery("Select pInvDate.project from ProjectInvoiceDate pInvDate "
                + "where pInvDate.invoiceDate = '" +invoiceDate+"'");
        List<Project> projectList = query.getResultList();
        int i =0; 
        rows = new Object[projectList.size()][5]; 
        projectListForInvoiceGrouping = new ArrayList();
        
        query = em.createQuery("Select Distinct(pInvDate.clientName) from ProjectInvoiceDate pInvDate "
                + "where pInvDate.invoiceDate = '" +invoiceDate+"'");
        listOfClientName = query.getResultList();
                
        for (String clientName : listOfClientName) {
            query = em.createQuery("Select pInvDate.project from ProjectInvoiceDate pInvDate "
                + "where pInvDate.invoiceDate = '" +invoiceDate
                    +"' and pInvDate.clientName = '"+ clientName+ "'");
            projectList = query.getResultList();
            
            Client client = null;
            for(Project project: projectList){
                client = project.getClient();

                Date projectStartDate = new Date(new java.util.Date(project.getStartDate()).getTime());
                Date projectEndDate = new Date(new java.util.Date(project.getEndDate()).getTime());
                

                Calendar invoiceStart = (Calendar) cal.clone(); 
                Calendar invoiceEnd = (Calendar) cal.clone();  
                if(!client.getInvoiceFrequency().equals("Monthly-Cal")){
                    invoiceStart.set(Calendar.DAY_OF_WEEK, 2);
                    invoiceEnd.set(Calendar.DAY_OF_WEEK, 1);
                }
                if(client.getInvoiceFrequency().equals("Weekly")){
                    invoiceStart.add(Calendar.DAY_OF_WEEK, -7);
                }
                else if(client.getInvoiceFrequency().equals("BiWeekly")){
                    invoiceStart.add(Calendar.DAY_OF_WEEK, -14);
                }
                else if(client.getInvoiceFrequency().equals("Monthly")){
                    invoiceStart.add(Calendar.DAY_OF_WEEK, -28);
                }
                else if(client.getInvoiceFrequency().equals("Monthly-Cal")){
                    invoiceStart.add(Calendar.MONTH, -1);
                    invoiceStart.set(Calendar.DATE, 1);
                    invoiceEnd.set(Calendar.DATE, 1);                
                    invoiceEnd.add(Calendar.DAY_OF_MONTH, -1);             
                }            
                Date invoiceStartDate = new Date(invoiceStart.getTimeInMillis());
                Date invoiceEndDate = new Date(invoiceEnd.getTimeInMillis());
                if(invoiceStartDate.before(projectStartDate)){
                    invoiceStartDate = projectStartDate;
                }
                if(invoiceEndDate.after(projectEndDate)){
                    invoiceEndDate = projectEndDate;
                }
                

                query = em.createQuery("Select pp  from ProjectPerson pp "
                    + "where pp.projectID= '" + project.getId() +"' and pp.isActivated = 'Yes'");
                listOfProjectPerson = query.getResultList();
                listOfClockedHours = new ArrayList<>(listOfProjectPerson.size());


                boolean isApprovalPending = false;
                boolean isInvoiceGenerated = true;
                for (ProjectPerson projectPerson : listOfProjectPerson) {
                    List<Long> hoursList = null;
                    query = em.createQuery("Select Sum(wh.hoursWorked) from ClockedHours wh "
                    + "where wh.isApproved= true and wh.projectID= '" + project.getId() +"'"
                    + " and wh.date between '"+ invoiceStartDate+"' and '"+invoiceEndDate
                    + "' and wh.empName='"+projectPerson.getPersonName()+"'");
                    hoursList = query.getResultList();
                    long  approvedHours= (hoursList==null || hoursList.isEmpty() || hoursList.get(0)==null)? 
                            0: hoursList.get(0);

                    query = em.createQuery("Select Sum(wh.hoursWorked) from ClockedHours wh "
                    + "where wh.date between '"+ invoiceStartDate+"' and '"+invoiceEndDate
                    + "' and wh.empName='"+projectPerson.getPersonName()
                    +"' and wh.projectID= '" + project.getId() +"'");
                    hoursList = query.getResultList(); 
                    long totalHours=  (hoursList==null || hoursList.isEmpty() || hoursList.get(0)==null)? 
                            0: hoursList.get(0);

                    query = em.createQuery("Select Count(wh.hoursWorked) from ClockedHours wh "
                    + "where wh.date between '"+ invoiceStartDate+"' and '"+invoiceEndDate
                    + "' and wh.empName='"+projectPerson.getPersonName()
                    + "' and wh.projectID= '" + project.getId() +"'");
                    hoursList = query.getResultList(); 
                    long rowCountClockedHours=  (hoursList==null || hoursList.isEmpty() || hoursList.get(0)==null)? 
                            0: hoursList.get(0);

                    query = em.createQuery("Select count(wh.hoursWorked) from ClockedHours wh "
                    + "where wh.isInvoiced= true and wh.date between '"+ invoiceStartDate+"' and '"+invoiceEndDate
                    + "' and wh.empName='"+projectPerson.getPersonName()
                    + "' and wh.projectID= '" + project.getId() +"'");
                    hoursList = query.getResultList();
                    long rowCountInvoicedHours=  (hoursList==null || hoursList.isEmpty() || hoursList.get(0)==null)? 
                            0: hoursList.get(0);


                    if(totalHours> approvedHours || rowCountClockedHours==0){
                        isApprovalPending = true;
                    }
                    if(rowCountInvoicedHours==0){
                        isInvoiceGenerated = false;
                    }
                    listOfClockedHours.add(totalHours);                
                }
                    
                if(client.getInvoiceGrouping().equals("Project")){
                    rows[i][0] =  project.getName()+" ("+project.getId()+")";
                    rows[i][1] =  client.getName()+ " ("+client.getInvoiceFrequency()
                            + "/" + client.getInvoiceGrouping()+ ")";
                    int status = ( isApprovalPending ? 1: (isInvoiceGenerated? 3: 2));
                    rows[i][2] =  (isApprovalPending?"Hours Approval Pending":
                                        (isInvoiceGenerated?"Invoice Generated": "Hours Approved"));
                    rows[i][3] =  status==2;
                    rows[i][4] =  status==3;
                    ++i;  
                    ArrayList al = new ArrayList();
                    al.add(project);
                    projectListForInvoiceGrouping.add(al);
                }
                else{
                    rows[i][0] =  (rows[i][0]==null)?"<br>"+project.getName()+" ("+project.getId()+")"
                                        :rows[i][0] + "<br>"+project.getName()+" ("+project.getId()+")";
                    rows[i][1] =  client.getName()+ " ("+client.getInvoiceFrequency()
                            + "/" + client.getInvoiceGrouping()+ ")";
                    int status = ( isApprovalPending ? 1: (isInvoiceGenerated? 3: 2));
                    rows[i][2] =  (rows[i][2]==null)?"<br>"+(isApprovalPending?"Hours Approval Pending":
                                        (isInvoiceGenerated?"Invoice Generated": "Hours Approved"))
                                        :rows[i][2]+ "<br>"+(isApprovalPending?"Hours Approval Pending":
                                        (isInvoiceGenerated?"Invoice Generated": "Hours Approved"));
                    rows[i][3] =  (rows[i][3]==null)?status==2:((Boolean)rows[i][3] && status==2);
                    rows[i][4] =  status==3;
                    
                }
                        
            }
            if(!client.getInvoiceGrouping().equals("Project")){
                rows[i][0] =  "<html>"+rows[i][0].toString().substring(4)+"</html>";
                rows[i][2] =  "<html>"+rows[i][2].toString().substring(4)+"</html>";
                ++i;
                projectListForInvoiceGrouping.add(projectList);
            }
        }
            
        Object[] columnNames = {"Project", "Client", "Invoice Status", "Select to Generate", "Select to Mail"};        
        defaultTableModel = new DefaultTableModel(rows, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column==3)
                    return (Boolean)rows[row][3];
                if(column==4)
                    return (Boolean)rows[row][4];
                return false;
            }
         };
        
        jTable1 = new JTable(defaultTableModel){
            private static final long serialVersionUID = 1L;
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 3:
                        return Boolean.class;
                    case 4:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };        
        jScrollPane1.setViewportView(jTable1);
        jTable1.setRowHeight(25);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(110);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(110);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);  
        
        for (int row = 0; row < jTable1.getRowCount(); row++){
            int rowHeight = jTable1.getRowHeight();

            for (int column = 0; column < jTable1.getColumnCount(); column++){
                Component comp = jTable1.prepareRenderer(jTable1.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }

            jTable1.setRowHeight(row, rowHeight);
        }
                
        
        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 11));
        jTable1.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 11));
        cm.close();   
    }
      
    private void generateActionPerformed(java.awt.event.ActionEvent evt) {                                         
        ConnectToDB cm = new ConnectToDB();
        EntityManager em = cm.getEntityManager();
        Calendar cal = (Calendar) selectedInvoiceDateCal.clone();
        cal.set(Calendar.DAY_OF_WEEK, 4);
        Date invoiceDate = new Date(cal.getTimeInMillis());
        
        int i = 0;
        boolean isAtLeastOneSelected = false;
        for (Object object : projectListForInvoiceGrouping) {            
            if(!(boolean)defaultTableModel.getValueAt(i, 3)){         
                ++i;
                continue;
            }
            ++i;
            isAtLeastOneSelected =true;
            
            List<Project> projectList = (List)object;  
            Invoice invoice = new Invoice();
            double totalAmountDue = 0;
            for(Project project: projectList){ 
                Client client = project.getClient(); 

                Date projectStartDate = new Date(new java.util.Date(project.getStartDate()).getTime());
                Date projectEndDate = new Date(new java.util.Date(project.getEndDate()).getTime());

                Calendar invoiceStart = (Calendar) cal.clone(); 
                Calendar invoiceEnd = (Calendar) cal.clone();  
                if(!client.getInvoiceFrequency().equals("Monthly-Cal")){
                    invoiceStart.set(Calendar.DAY_OF_WEEK, 2);
                    invoiceEnd.set(Calendar.DAY_OF_WEEK, 1);
                }
                if(client.getInvoiceFrequency().equals("Weekly")){
                    invoiceStart.add(Calendar.DAY_OF_WEEK, -7);
                }
                else if(client.getInvoiceFrequency().equals("BiWeekly")){
                    invoiceStart.add(Calendar.DAY_OF_WEEK, -14);
                }
                else if(client.getInvoiceFrequency().equals("Monthly")){
                    invoiceStart.add(Calendar.DAY_OF_WEEK, -28);
                }
                else if(client.getInvoiceFrequency().equals("Monthly-Cal")){
                    invoiceStart.add(Calendar.MONTH, -1);
                    invoiceStart.set(Calendar.DATE, 1);
                    invoiceEnd.set(Calendar.DATE, 1);                
                    invoiceEnd.add(Calendar.DAY_OF_MONTH, -1);             
                }            
                Date invoiceStartDate = new Date(invoiceStart.getTimeInMillis());
                Date invoiceEndDate = new Date(invoiceEnd.getTimeInMillis());
                if(invoiceStartDate.before(projectStartDate)){
                    invoiceStartDate = projectStartDate;
                }
                if(invoiceEndDate.after(projectEndDate)){
                    invoiceEndDate = projectEndDate;
                }
                invoice.setInvoiceDate(invoiceDate);
                invoice.setInvoiceStartDate(invoiceStartDate);
                invoice.setInvoiceEndDate(invoiceEndDate);
                invoice.setClient(client);
                invoice.setClientNumber(client.getNumber());
                em.persist(invoice);
                InvoiceToProject itp = new InvoiceToProject(invoice.getId(), project.getId(), project);
                em.persist(itp);
                
                Calendar invoiceWeeklyEnd = (Calendar) invoiceStart.clone();
                invoiceWeeklyEnd.set(Calendar.DAY_OF_WEEK, 1);
                invoiceWeeklyEnd.add(Calendar.DAY_OF_WEEK, 7);
                Date invoiceWeeklyEndDate = new Date(invoiceWeeklyEnd.getTimeInMillis());
                if(invoiceWeeklyEndDate.after(invoiceEndDate)){
                    invoiceWeeklyEndDate = invoiceEndDate;
                }
                while(invoiceWeeklyEndDate.compareTo(invoiceEndDate)<=0){
                    Query query = em.createQuery("Select pp  from ProjectPerson pp "
                        + "where pp.projectID= '" + project.getId() +"' and pp.isActivated = 'Yes'");
                    listOfProjectPerson = query.getResultList();

                    for (ProjectPerson projectPerson : listOfProjectPerson) {
                        List<Long> hoursList = null;

                        query = em.createQuery("Select Sum(wh.hoursWorked) from ClockedHours wh "
                        + "where wh.date between '"+ invoiceStartDate+"' and '"+invoiceWeeklyEndDate
                        + "' and wh.empName='"+projectPerson.getPersonName()+"'"
                        + " and wh.projectID= '" + project.getId() +"'");
                        hoursList = query.getResultList(); 
                        long totalHours=  (hoursList==null || hoursList.isEmpty() || hoursList.get(0)==null)? 
                                0: hoursList.get(0);
                        
                        double weeklyAmount = (totalHours>40)? (40 * projectPerson.getProjectPersonName().getBillRate())
                                                            +(totalHours-40)* projectPerson.getProjectPersonName().getBillRate() *1.5
                                            : (totalHours * projectPerson.getProjectPersonName().getBillRate());
                        totalAmountDue = totalAmountDue + weeklyAmount;
                        if(weeklyAmount>0){
                            InvoiceLineItem invoiceLineItem = new InvoiceLineItem(invoice.getId(), invoiceStartDate,                            
                                    invoiceWeeklyEndDate, projectPerson.getPersonName(),
                                    projectPerson.getProjectPersonName().getBillRate(), totalHours, 
                                    project, project.getId(), weeklyAmount);
                            em.persist(invoiceLineItem);
                        }
                        

                        query = em.createQuery("Select wh from ClockedHours wh "
                        + "where wh.date between '"+ invoiceStartDate+"' and '"+invoiceWeeklyEndDate
                        + "' and wh.empName='"+projectPerson.getPersonName()+"'"
                        + " and wh.projectID= '" + project.getId() +"'");
                        List<ClockedHours> workedHoursList = query.getResultList();
                        for (ClockedHours workedHours : workedHoursList) {
                            workedHours.setIsInvoiced(true);   
                        }                              
                    }
                    
                    invoiceWeeklyEnd.add(Calendar.DAY_OF_WEEK, 1);
                    invoiceStartDate = new Date(invoiceWeeklyEnd.getTimeInMillis());
                    invoiceWeeklyEnd.add(Calendar.DAY_OF_WEEK, 6);
                    invoiceWeeklyEndDate = new Date(invoiceWeeklyEnd.getTimeInMillis());                    
                    if(invoiceStartDate.compareTo(invoiceEndDate)<=0 && invoiceWeeklyEndDate.after(invoiceEndDate)){
                        invoiceWeeklyEndDate = invoiceEndDate;
                    }
                }               
            }
            invoice.setTotalAmountDue(totalAmountDue);
            em.persist(invoice);
        }
        if(!isAtLeastOneSelected){            
            JOptionPane.showMessageDialog(null, "Plaese select a Project to Generate Invoice!");
            return;
        }
        cm.close();         
                                                  
        JOptionPane.showMessageDialog(null, "Invoice generated sucessfully.");        
        refreshTable();         
    }                                        

    private void mailActionPerformed(java.awt.event.ActionEvent evt) {                                     
        JOptionPane.showMessageDialog(null, "Mail Sent");
    }                                    

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        selectedInvoiceDateCal.add(Calendar.DAY_OF_WEEK, -7);
        refreshTable();
    }                                          

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        selectedInvoiceDateCal.add(Calendar.DAY_OF_WEEK, 7);
        refreshTable();
    }                                          


}
