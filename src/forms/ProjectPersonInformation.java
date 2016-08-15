package forms;

import entities.ProjectPerson;
import entities.Employee;
import entities.Project;
import javax.swing.JFrame;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import connections.SystemData;
import connections.ConnectToDB;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

public class ProjectPersonInformation extends javax.swing.JPanel {
    JFrame  panelHolder;
    SystemData systemData;
    DefaultComboBoxModel model;
    DefaultTableModel tableModel, tableModel1;
    int selectedProjectId;
    Employee selectedDeveloperToAdd;
    Employee selectedDeveloperToActivate;
    List<Project> projectList;
    List<ProjectPerson> projectPersonList;
    List<Employee> empList;
    
        
    public ProjectPersonInformation(JFrame  panelHolder, SystemData systemData) {
        this.panelHolder = panelHolder;
        this.systemData = systemData;  
        initComponents();
        model = new DefaultComboBoxModel();
        ConnectToDB cm = new ConnectToDB();
        EntityManager em = cm.getEntityManager();
        Query query = em.createQuery("Select p from Project p "
                + "where p.managerName= '" +systemData.getCurrentUser().getEmployee().getName()+"' "
                + " and p.status = 'In Progress'");              
        projectList = query.getResultList();
        for(Project project: projectList){
            model.addElement(project.getName()+" ("+project.getId()+")");
        }
        projectComboBox.setModel(model);
        model.setSelectedItem(projectList.get(0).getName()+" ("+projectList.get(0).getId()+")");
        cm.close();   
        populateTable();
    }
    
    public void populateTable(){
        ConnectToDB cm = new ConnectToDB();
        EntityManager em = cm.getEntityManager();
        selectedProjectId = projectList.get(model.getIndexOf(model.getSelectedItem())).getId();
        Query query = em.createQuery("Select pp  from ProjectPerson pp "
                + "where pp.projectID= '" + selectedProjectId +"' and pp.isActivated = 'Yes' "
                + "and pp.personName != '" + systemData.getCurrentUser().getEmployee().getName() + "' ");
        projectPersonList = query.getResultList();        
        
        Object[] columnNames = {"Developer", "Title", "Billing Rate"};
        Object[][] rowData = new Object[projectPersonList.size()][3];
        int i =0;       
        for(ProjectPerson projectPerson: projectPersonList){            
            Employee emp = em.find(Employee.class, projectPerson.getProjectPersonName().getName());
            rowData[i][0] = (Object) emp.getName();
            rowData[i][1] = (Object) emp.getTitle();
            rowData[i][2] = (Object) emp.getBillRate();
            ++i;
        };
        jLabel1.setText("Developers working in: "+ projectComboBox.getSelectedItem());
        tableModel = new DefaultTableModel(rowData, columnNames);
        jTable1.setModel(tableModel);
        jTable1.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 11));
        
        query = em.createQuery("Select emp from Employee emp where emp.EmpRole= 'Developer'"); 
        empList = query.getResultList();  
        for (ProjectPerson projectPerson : projectPersonList) {
            empList.remove(projectPerson.getProjectPersonName());
        }
        Object[] columnNames1 = {"Developer", "Title", "Billing Rate"};
        Object[][] rowData1 = new Object[empList.size()][3];
        
        
        i =0;       
        for(Employee emp: empList){            
            rowData1[i][0] = (Object) emp.getName();
            rowData1[i][1] = (Object) emp.getTitle();
            rowData1[i][2] = (Object) emp.getBillRate();
            ++i;
        };
        jLabel3.setText("Add developers to: "+ projectComboBox.getSelectedItem());
        tableModel1 = new DefaultTableModel(rowData1, columnNames1);
        jTable2.setModel(tableModel1);
        jTable2.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 11));
        
        cm.close();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        removeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        projectComboBox = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        removeButton.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        removeButton.setText("Remove");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel1.setText("Developers working");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeButton)
                .addGap(6, 6, 6))
        );

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel2.setText("Select Project");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 116, 0, 0);
        jPanel3.add(jLabel2, gridBagConstraints);

        projectComboBox.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        projectComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        projectComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 129;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 6, 100);
        jPanel3.add(projectComboBox, gridBagConstraints);

        jTable2.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel3.setText("Available developers");

        addButton.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addButton)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        if(jTable1.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Please select a Developer to Remove from the Project!");
            return;
        } 
        ProjectPerson selectedProjectPerson = projectPersonList.get(jTable1.getSelectedRow());        
        ConnectToDB cm = new ConnectToDB();
        EntityManager em = cm.getEntityManager();
        ProjectPerson pp = em.find(ProjectPerson.class, selectedProjectPerson.getId());
        pp.setIsActivated("No");  
        cm.close(); 
        populateTable();
    }//GEN-LAST:event_removeButtonActionPerformed

    private void projectComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectComboBoxActionPerformed
        populateTable();
    }//GEN-LAST:event_projectComboBoxActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        if(jTable2.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Please select a Developer to Add to the Project!");
            return;
        } 
        Employee selectedEmployee = empList.get(jTable2.getSelectedRow());                          
        ConnectToDB cm = new ConnectToDB();
        EntityManager em = cm.getEntityManager();
        Query query = em.createQuery("Select pp  from ProjectPerson pp "
                + "where pp.projectID= '" + selectedProjectId +"' and pp.personName = '"
                + selectedEmployee.getName()+"'");
        List<ProjectPerson> ppList = query.getResultList(); 
        ProjectPerson pp;
        if(ppList!=null && !ppList.isEmpty()){
            pp = ppList.get(0);
            pp.setIsActivated("Yes");
        }
        else{
            pp = new ProjectPerson(em.find(Project.class, selectedProjectId), selectedProjectId, selectedEmployee,
                    selectedEmployee.getName(), "Yes", systemData.getCurrentUser().getEmployee().getName());
            em.persist(pp);
        }       
        cm.close();
        populateTable();
    }//GEN-LAST:event_addButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JComboBox<String> projectComboBox;
    private javax.swing.JButton removeButton;
    // End of variables declaration//GEN-END:variables
}
