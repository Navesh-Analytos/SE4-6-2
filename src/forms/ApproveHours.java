package forms;

import entities.Employee;
import entities.Project;
import entities.ClockedHours;

import connections.SystemData;
import connections.ConnectToDB;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Calendar;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import javax.imageio.ImageIO;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.swing.ImageIcon;
import javax.swing.plaf.IconUIResource;


public class ApproveHours extends javax.swing.JPanel {
    int selectedProjectId;
    DefaultTableModel tableModel;
    List<Project> projectList;
    List<Date> dates;
    List<String> developersList;JFrame  panelHolder;
    SystemData systemData;
    DefaultComboBoxModel model;
    Calendar selectedWeekStartDate;
        
    public ApproveHours(JFrame  panelHolder, SystemData systemData) {
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
        selectedWeekStartDate = Calendar.getInstance();
        selectedWeekStartDate.set(Calendar.DAY_OF_WEEK, 2);
        
        ConnectToDB cm = new ConnectToDB();
        EntityManager em = cm.getEntityManager();
        Query query = em.createQuery("Select p from Project p "
                + "where p.managerName= '" +systemData.getCurrentUser().getEmployee().getName()+"' "
                + " and p.status = 'In Progress'");              
        projectList = query.getResultList();
        model = new DefaultComboBoxModel();
        for(Project project: projectList){
            model.addElement(project.getName()+" ("+project.getId()+")");
        }
        projectSelection.setModel(model);
        model.setSelectedItem(projectList.get(0).getName()+" ("+projectList.get(0).getId()+")");
        cm.close();
        
        refreshTable();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jCheckBox1 = new javax.swing.JCheckBox();
        approveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        approveLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        projectSelection = new javax.swing.JComboBox<>();
        dateLabel = new javax.swing.JLabel();
        prevButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setName(""); // NOI18N
        jTable1.setRowHeight(25);
        jScrollPane1.setViewportView(jTable1);

        jCheckBox1.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jCheckBox1.setText("Check All");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        approveButton.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        approveButton.setText("Approve");
        approveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jCheckBox1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE))
                .addGap(11, 11, 11))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(approveButton)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(approveButton)
                    .addComponent(cancelButton)))
        );

        approveLabel.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        approveLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        approveLabel.setText("Approve hours for  ");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Select Project:");

        projectSelection.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        projectSelection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        projectSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectSelectionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(projectSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(projectSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        dateLabel.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        dateLabel.setText("07/18/2016 to 07/22/2016");

        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });

        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(approveLabel)
                .addGap(18, 18, 18)
                .addComponent(prevButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nextButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(prevButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(approveLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void approveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approveButtonActionPerformed
        ConnectToDB cm = new ConnectToDB();
        EntityManager em = cm.getEntityManager();
        boolean flag = true;
        for (int i = 0; i < developersList.size(); i++) {
            if(tableModel.getValueAt(i, 9).equals(new Boolean(false))){
                continue;
            }
            flag = false;
            for (int j = 1; j <8 ; j++) {
                int hours = 0;
                try{
                    if(tableModel.getValueAt(i, j).toString()==null ||
                            tableModel.getValueAt(i, j).toString().equals("")){
                        hours =0;                    
                    }
                    else{                        
                        hours = Integer.parseInt(tableModel.getValueAt(i, j).toString());  
                    }                  
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Please enter Integer value for hours");
                    return;
                }

                ClockedHours ch = em.find(ClockedHours.class, selectedProjectId
                        +developersList.get(i)+dates.get(j-1));
                if(ch==null){
                    ch = new ClockedHours(selectedProjectId+developersList.get(i)+dates.get(j-1), 
                        em.find(Employee.class, developersList.get(i)), 
                        developersList.get(i), em.find(Project.class, selectedProjectId),
                        selectedProjectId, hours, dates.get(j-1), true, false, null);
                    em.persist(ch); 
                }
                if(ch!=null){      
                    ch.setHoursWorked(hours);
                    ch.setIsApproved(true);
                }
            }    
        }
        if(flag){
            JOptionPane.showMessageDialog(null, "Please select Hours to approve!");
            return;
        }
        cm.close();
        JOptionPane.showMessageDialog(null, "Hours Approved sucessfully");
        refreshTable();
    }//GEN-LAST:event_approveButtonActionPerformed
    
    private void refreshTable(){          
        Calendar calendar = (Calendar)selectedWeekStartDate.clone();
	calendar.set(Calendar.DAY_OF_WEEK, 2);
        Date weekStartDate = new Date(calendar.getTimeInMillis());
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndDate = new Date(calendar.getTimeInMillis());
        approveLabel.setText("Approve Hours for: ");
        dateLabel.setText(weekStartDate+" to "+weekEndDate);
        
        ConnectToDB cm = new ConnectToDB();
        EntityManager em = cm.getEntityManager();        
        selectedProjectId = projectList.get(model.getIndexOf(model.getSelectedItem())).getId();
                
        Object[] columnNames =  {"Developer", 
                                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday","Sunday", 
                                "Total", "Select"};
        dates = new ArrayList<>();
        for (int i = 7; i >0; ) {
            Date date = new Date(calendar.getTimeInMillis());
            dates.add(0, date);
            --i;
            calendar.add(Calendar.DAY_OF_WEEK, -1);           
        }
        
        Query query = em.createQuery("Select pp.personName  from ProjectPerson pp "
                + "where pp.projectID='"+ selectedProjectId +"' and pp.isActivated = 'Yes'");
        developersList = query.getResultList();
        
        Object[][] rowData = new Object[developersList.size()][10];        
        boolean[] isApproved = new boolean[developersList.size()];
        int i =0;
        List<ClockedHours> hoursList;
        for(String developer: developersList){
            Date startDate = new Date(weekStartDate.getTime());
            Date endDate = new Date(weekEndDate.getTime());
            query = em.createQuery("Select ch from ClockedHours ch where ch.projectID= '"+selectedProjectId +"' " 
                    + "and ch.empName='"+developer+"'"  
                    + " and ch.date between '"+startDate+"' and '"+ endDate+"' order by ch.date");
            hoursList = query.getResultList();
            isApproved[i] = (hoursList==null || hoursList.isEmpty()? false: hoursList.get(0).isIsApproved());
            rowData[i][0] = developer;
            if(!hoursList.isEmpty()){
                rowData[i][1] = hoursList.get(0).getHoursWorked();
                rowData[i][2] = hoursList.get(1).getHoursWorked();
                rowData[i][3] = hoursList.get(2).getHoursWorked();
                rowData[i][4] = hoursList.get(3).getHoursWorked();
                rowData[i][5] = hoursList.get(4).getHoursWorked();
                rowData[i][6] = hoursList.get(5).getHoursWorked();
                rowData[i][7] = hoursList.get(6).getHoursWorked();
                int total = hoursList.get(0).getHoursWorked()+  hoursList.get(1).getHoursWorked()
                                + hoursList.get(2).getHoursWorked()+ hoursList.get(3).getHoursWorked()
                                + hoursList.get(4).getHoursWorked()+ hoursList.get(5).getHoursWorked()
                                + hoursList.get(6).getHoursWorked();
                
                rowData[i][8] = total;
                rowData[i][9] = true;
                if(!isApproved[i] && total==0) rowData[i][9] = false;
            } else{
                rowData[i][1] = "0";
                rowData[i][2] = "0";
                rowData[i][3] = "0";
                rowData[i][4] = "0";
                rowData[i][5] = "0";
                rowData[i][6] = "0";
                rowData[i][7] = "0";
                rowData[i][8] = 0;
                rowData[i][9] = false;
            }    
            ++i;
        }
        
        tableModel = new DefaultTableModel(rowData, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column==8)
                    return false;
                return !isApproved[row];
            }
         };
        jTable1.setModel(tableModel);
        jTable1 = new JTable(tableModel){
            private static final long serialVersionUID = 1L;
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 9:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        }; 
        jScrollPane1.setViewportView(jTable1);
        jTable1.setRowHeight(25);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(90);
        jTable1.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTable1.getSelectedRow();
                if(!tableModel.isCellEditable(row, 0)){
                    JOptionPane.showMessageDialog(null, "Can't be edited! Already Approved!");
                }
            }
        });               
        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 11));
        jTable1.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 11));
        cm.close();
    }
        
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        panelHolder.setTitle("Home Page");
        panelHolder.getContentPane().removeAll();
        panelHolder.getContentPane().add(new HomePage(panelHolder, systemData, false));
        panelHolder.getContentPane().revalidate();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void projectSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectSelectionActionPerformed
        refreshTable();
    }//GEN-LAST:event_projectSelectionActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if(jCheckBox1.isSelected()){
            int i=0;
            for(String Dev: developersList){
                tableModel.setValueAt(true, i, 9);
                tableModel.fireTableDataChanged();
                ++i;
            }
            jCheckBox1.setText("Uncheck All");
        }else{
            int i=0;
            for(String Dev: developersList){
                if(tableModel.isCellEditable(i, 9)){
                    tableModel.setValueAt(false, i, 9);                
                }
                tableModel.fireTableDataChanged();
                ++i;
            }
            jCheckBox1.setText("Check All");
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        selectedWeekStartDate.add(Calendar.DAY_OF_WEEK, -7);
        refreshTable();
    }//GEN-LAST:event_prevButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        selectedWeekStartDate.add(Calendar.DAY_OF_WEEK, 7);
        refreshTable();
    }//GEN-LAST:event_nextButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton approveButton;
    private javax.swing.JLabel approveLabel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton prevButton;
    private javax.swing.JComboBox<String> projectSelection;
    // End of variables declaration//GEN-END:variables
}
