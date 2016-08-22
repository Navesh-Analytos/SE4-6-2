package forms;

import entities.Client;
import entities.Employee;
import entities.Invoice;
import entities.InvoiceLineItem;
import entities.Project;
import connections.SystemData;
import javax.swing.JFrame;

import connections.ConnectToDB;
import entities.ProjectPerson;
import entities.ClockedHours;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import org.apache.poi.openxml4j.opc.OPCPackage;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;


public class InvoiceReport extends javax.swing.JPanel {
    JFrame  panelHolder;
    SystemData systemData;
        
    DefaultComboBoxModel comboModel1;
    DefaultComboBoxModel comboModel2;
    DefaultTableModel tableModel;
    Object[][] rowData;
    List<Date> dateList;
    Date fromDate;
    Date toDate;
    List<Invoice> invoiceList;
        
    public InvoiceReport(JFrame  panelHolder, SystemData systemData) {
        this.panelHolder = panelHolder;
        this.systemData = systemData;  
        initComponents();
                        
        ConnectToDB cm = new ConnectToDB();
        EntityManager em = cm.getEntityManager();
        
        comboModel1 = new DefaultComboBoxModel();
        comboModel2 = new DefaultComboBoxModel();
        Query query = em.createQuery("Select Distinct inv.invoiceDate from Invoice inv order by inv.invoiceDate");
        dateList = query.getResultList();
        if(dateList.isEmpty()){ 
            jButton1.setEnabled(false);
            viewButton.setEnabled(false);
            saveButton.setEnabled(false);
            JOptionPane.showMessageDialog(null, "No Invoices there to show.");            
            return;           
        }
        for (Date date : dateList) {            
            comboModel1.addElement(new SimpleDateFormat("MM-dd-yyyy").format(date));
            comboModel2.addElement(new SimpleDateFormat("MM-dd-yyyy").format(date));
        }
        fromComboBox.setModel(comboModel1);
        toComboBox.setModel(comboModel2);        
        
        query = em.createQuery("Select Max(inv.invoiceDate) from Invoice inv");
        Date maxDate = (Date)query.getResultList().get(0);
        fromComboBox.setSelectedItem(new SimpleDateFormat("MM-dd-yyyy").format(dateList.get(0)));
        toComboBox.setSelectedItem(new SimpleDateFormat("MM-dd-yyyy").format(maxDate));
        
        fromDate = dateList.get(fromComboBox.getSelectedIndex());
        toDate = dateList.get(toComboBox.getSelectedIndex());                  
        cm.close();               
        refreshTable();        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        fromComboBox = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        toComboBox = new javax.swing.JComboBox<>();
        viewButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addGap(33, 33, 33))
        );

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Show  Invoice From");

        fromComboBox.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        fromComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jButton1.setText("Go");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("To");

        toComboBox.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        toComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));

        viewButton.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        viewButton.setText("View");
        viewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewButtonActionPerformed(evt);
            }
        });

        saveButton.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(toComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fromComboBox, 0, 101, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addComponent(jButton1)
                .addGap(81, 81, 81)
                .addComponent(viewButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saveButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fromComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jButton1)
                    .addComponent(viewButton)
                    .addComponent(saveButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void refreshTable(){
        ConnectToDB cm = new ConnectToDB();
        EntityManager em = cm.getEntityManager();
        
        Query query = em.createQuery("Select inv from Invoice inv "
                +" where inv.invoiceDate between '"+ fromDate+"' and '"+toDate+"'"
                + " ORDER BY INV.clientNumber, inv.invoiceDate");
        invoiceList = query.getResultList();
        if(invoiceList.isEmpty()){        
            jButton1.setEnabled(false);
            viewButton.setEnabled(false);
            saveButton.setEnabled(false);
            JOptionPane.showMessageDialog(null, "No Invoice in the seleted week to show.");
            return;              
        }
        
        Object[] columnNames = {"Client", "Project", "Invoice Number", "Invoice Date", "Invoice Amount"};
        Object[][] rowData = new Object[invoiceList.size()][5]; 
        int i =0; 
        for (Invoice invoice : invoiceList) {
            query = em.createQuery("Select itp.project from InvoiceToProject itp"
            +" where itp.invoiceID ="+ invoice.getId() +"");
            List<Project> projectList = query.getResultList();         
            String projectNames = "";
            for (Project project : projectList) {
                projectNames = projectNames+"<br>"+project.getName()+" ("+project.getId()+")";
            }
            projectNames = "<html>"+projectNames.substring(4)+"</html>";
            rowData[i][0] =  invoice.getClient().getName();
            rowData[i][1] =  projectNames;
            rowData[i][2] =  invoice.getId();
            rowData[i][3] =  new SimpleDateFormat("MM-dd-yyyy").format(invoice.getInvoiceStartDate())+ " To "
                            + new SimpleDateFormat("MM-dd-yyyy").format(invoice.getInvoiceEndDate());
            rowData[i][4] =  "$"+invoice.getTotalAmountDue();
            ++i;                    
        }          
        tableModel = new DefaultTableModel(rowData, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
         };
        
        jTable1 = new JTable(tableModel);        
        jScrollPane1.setViewportView(jTable1);
        jTable1.setRowHeight(25);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(50);  
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(120);  
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);  
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
    
    private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewButtonActionPerformed
        if(jTable1.getSelectedRow()==-1){
            JOptionPane.showMessageDialog(null, "Please select an Invoice.");
            return;            
        }
        Invoice selectedInvoice = invoiceList.get(jTable1.getSelectedRow());
        panelHolder.setTitle("View Invoice");
        panelHolder.getContentPane().removeAll();
        panelHolder.getContentPane().add(new ViewInvoice(panelHolder, systemData, selectedInvoice, true ));
        panelHolder.getContentPane().revalidate();
    }//GEN-LAST:event_viewButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if(jTable1.getSelectedRow()==-1){
            JOptionPane.showMessageDialog(null, "Please select the Invoice to save.");
            return;            
        }        
        Invoice invoice = invoiceList.get(jTable1.getSelectedRow());
        String filepath = null;
        try{
            XWPFDocument doc = new XWPFDocument(OPCPackage.open("data/template.docx"));
            XWPFTable lineItemTable = null;
            for (XWPFTable tbl : doc.getTables()) {
               for (XWPFTableRow row : tbl.getRows()) {
                  for (XWPFTableCell cell : row.getTableCells()) {
                     for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            String text = r.getText(0);
                            if (text != null){
                                if(text.contains("clientName")){                                
                                    text = text.replace("clientName", invoice.getClient().getName());
                                    r.setText(text, 0);
                                }
                                if(text.contains("addressLine")){                                
                                    text = text.replace("addressLine", 
                                            invoice.getClient().getAddressLine1()
                                            + (invoice.getClient().getAddressLine2().equals("")?"":
                                                    ", "+ invoice.getClient().getAddressLine2()));
                                    r.setText(text, 0);
                                }
                                if(text.contains("cityStateZip")){                                
                                    text = text.replace("cityStateZip", invoice.getClient().getCity()
                                            + ", "+invoice.getClient().getState()
                                            + " "+invoice.getClient().getZip());
                                    r.setText(text, 0);
                                }
                                if(text.contains("clientId")){                                
                                    text = text.replace("clientId", invoice.getClient().getNumber()+"");
                                    r.setText(text, 0);
                                }

                                ConnectToDB cm = new ConnectToDB();
                                EntityManager em = cm.getEntityManager();
                                Query query = em.createQuery("Select itp.project from InvoiceToProject itp"
                                    +" where itp.invoiceID ='"+ invoice.getId() +"'");
                                List<Project> projectList = query.getResultList();
                                String projectNames = "";
                                for (Project project : projectList) {
                                    projectNames = projectNames+", "+project.getName()+" ("+project.getId()+")";
                                }
                                projectNames = projectNames.substring(1);
                                em.close();
                                if(text.contains("projectName")){                                
                                    text = text.replace("projectName", projectNames);
                                    r.setText(text, 0);
                                }if(text.contains("invoiceNumber")){                                
                                    text = text.replace("invoiceNumber", invoice.getId()+"");
                                    r.setText(text, 0);
                                }if(text.contains("invoiceDate")){                                
                                    text = text.replace("invoiceDate", new SimpleDateFormat("MM-dd-yyyy").
                                            format(invoice.getInvoiceDate()));
                                    r.setText(text, 0);
                                }if(text.contains("billingTerm")){                                
                                    text = text.replace("billingTerm", invoice.getClient().getBillingTerm());
                                    r.setText(text, 0);
                                }if(text.contains("invoiceFrequency")){                                
                                    text = text.replace("invoiceFrequency", invoice.getClient().getInvoiceFrequency());
                                    r.setText(text, 0);
                                }if(text.contains("totalAmountDue")){                                
                                    text = text.replace("totalAmountDue", "$"+invoice.getTotalAmountDue());
                                    r.setText(text, 0);
                                }                         
                                
                                if(text.contains("Description")){ 
                                    lineItemTable = tbl;
                                }
                            }
                        }
                     }
                  }
               }
            }
            
            for (XWPFParagraph p : doc.getParagraphs()) {
                List<XWPFRun> runs = p.getRuns();
                if (runs != null) {
                    for (XWPFRun r : runs) {
                        String text = r.getText(0);
                        if (text != null && text.contains("amnt")) {
                            text = text.replace("amnt", "$"+invoice.getTotalAmountDue());
                            r.setText(text, 0);
                        }
                    }
                }
            }
            
            ConnectToDB cm = new ConnectToDB();
            EntityManager em = cm.getEntityManager();
            Query query = em.createQuery("Select line from InvoiceLineItem line"
                    +" where line.invoiceId ='"+ invoice.getId()+"'");
            List<InvoiceLineItem> lineItems = query.getResultList();        
        
            for (InvoiceLineItem lineItem : lineItems) {
                XWPFTableRow tableRow= lineItemTable.createRow();
                tableRow.getCell(0).setText(lineItem.getProject().getName());
                tableRow.getCell(1).setText(new SimpleDateFormat("MM-dd-yyyy").format(lineItem.getStartDate())+" To "
                        +new SimpleDateFormat("MM-dd-yyyy").format(lineItem.getEndDate()));
                tableRow.getCell(2).setText(lineItem.getDescription());
                tableRow.getCell(3).setText("$"+lineItem.getBillRate());
                tableRow.getCell(4).setText(lineItem.getHours()+"");
                tableRow.getCell(5).setText("$"+lineItem.getTotal());
            }          
            cm.close();
            
            filepath = "C:\\Users\\navesh\\Desktop\\Invoices\\";
            filepath = filepath+invoice.getId()+".docx";
            doc.write(new FileOutputStream(filepath));
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error in saving Report!");            
            e.printStackTrace();
            return;
        }
        JOptionPane.showMessageDialog(null, "Invoice was saved in the following path:\n"
                + filepath);
    }//GEN-LAST:event_saveButtonActionPerformed
       
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        fromDate = dateList.get(fromComboBox.getSelectedIndex());
        toDate = dateList.get(toComboBox.getSelectedIndex());    
        if(fromDate.after(toDate)){
            JOptionPane.showMessageDialog(null, "From Date should be before To Date");
            return;
        }
        refreshTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> fromComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox<String> toComboBox;
    private javax.swing.JButton viewButton;
    // End of variables declaration//GEN-END:variables
}
