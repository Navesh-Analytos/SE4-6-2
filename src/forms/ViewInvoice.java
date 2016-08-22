package forms;

import entities.Invoice;
import entities.InvoiceLineItem;
import connections.ConnectToDB;
import connections.SystemData;
import entities.Project;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewInvoice extends javax.swing.JPanel {
    JFrame  panelHolder;
    SystemData systemData;
    boolean fromInvoice;
    Invoice invoice;
    
    public ViewInvoice(JFrame  panelHolder, SystemData systemData, Invoice invoice, boolean  fromInvoice) {
        this.panelHolder = panelHolder;
        this.systemData = systemData; 
        this.invoice = invoice;
        this.fromInvoice = fromInvoice;
        initComponents();
        to.setText("<html>"+invoice.getClient().getName()
                + "<br>"+invoice.getClient().getAddressLine1()
                + (invoice.getClient().getAddressLine2().equals("")?"":"<br>"+ invoice.getClient().getAddressLine2())
                + "<br>"+invoice.getClient().getCity()
                + ", "+invoice.getClient().getState()
                + " "+invoice.getClient().getZip()
                + "</html>");
        clientID.setText(invoice.getClient().getNumber()+"");
        ConnectToDB cm = new ConnectToDB();
        EntityManager em = cm.getEntityManager();
        Query query = em.createQuery("Select itp.project from InvoiceToProject itp"
            +" where itp.invoiceID ='"+ invoice.getId() +"'");
        List<Project> projectList = query.getResultList();
        String projectNames = "";
        for (Project project : projectList) {
            projectNames = projectNames+"<br>"+project.getName()+" ("+project.getId()+")";
        }
        projectNames = "<html>"+projectNames.substring(4)+"</html>";
        projectName.setText(projectNames);      
        
        invoiceNumber.setText("Invoice Number:  "+invoice.getId());
        invoiceDate.setText("Invoice Date: "+ new SimpleDateFormat("MM-dd-yyyy").format(invoice.getInvoiceDate()));
        payingTerm.setText("Payment Terms: "+ invoice.getClient().getBillingTerm());
        invoiceFreq.setText("Billing Frequency:  "+ invoice.getClient().getInvoiceFrequency());
        totalAmountDue.setText("Total Amount Due: $"+invoice.getTotalAmountDue()+"");
        
        totalAmountDue1.setText("$"+invoice.getTotalAmountDue());
        remitPaymentTo.setText("<html>"+"Remit Payment To:"
                +"<br>"+"Eagle Consulting Services"
                + "<br>"+"2501 E Memorial Road"
                + "<br>"+"Edmond, Ok 73013"
                + "</html>");
        
        
        Object[] columnNames = {"Project","Date", "Description", "Rate", "Hours", "Amount"};
        query = em.createQuery("Select line from InvoiceLineItem line"
        +" where line.invoiceId ='"+ invoice.getId()+"'");
        List<InvoiceLineItem> lineItems = query.getResultList();
        
        Object[][] rowData = new Object[lineItems.size()][6]; 
        int i =0; 
        for (InvoiceLineItem lineItem : lineItems) {
            rowData[i][0] = lineItem.getProject().getName();
            rowData[i][1] =  new SimpleDateFormat("MM-dd-yyyy").format(lineItem.getStartDate())+" To "
                    +new SimpleDateFormat("MM-dd-yyyy").format(lineItem.getEndDate());
            rowData[i][2] =  lineItem.getDescription();
            rowData[i][3] =  "$"+lineItem.getBillRate();
            rowData[i][4] =  lineItem.getHours();
            rowData[i][5] =  "$"+lineItem.getTotal();
            ++i;                    
        }          
        DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
         };
        
        jTable1 = new JTable(tableModel);        
        jScrollPane1.setViewportView(jTable1);
        jTable1.setRowHeight(25);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(130);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);  
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(60);  
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);         
        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 11));
        jTable1.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 11));
        cm.close();
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        to = new javax.swing.JLabel();
        clientID = new javax.swing.JLabel();
        projectName = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        invoiceNumber = new javax.swing.JLabel();
        invoiceDate = new javax.swing.JLabel();
        payingTerm = new javax.swing.JLabel();
        invoiceFreq = new javax.swing.JLabel();
        totalAmountDue = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        remitPaymentTo = new javax.swing.JLabel();
        totalAmountDue1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        back = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Eagle Consulting Invoice");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel2.setText("To");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel3.setText("Client ID:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel4.setText("Project:");

        to.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        to.setText("Client");

        clientID.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        clientID.setText("clientID");

        projectName.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        projectName.setText("ProjectName");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(to, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clientID, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(projectName))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(to, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(clientID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(projectName))
                .addContainerGap())
        );

        invoiceNumber.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        invoiceNumber.setText("a");

        invoiceDate.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        invoiceDate.setText("b");

        payingTerm.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        payingTerm.setText("c");

        invoiceFreq.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        invoiceFreq.setText("d");

        totalAmountDue.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        totalAmountDue.setText("e");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(invoiceNumber)
                    .addComponent(invoiceDate)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(payingTerm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(invoiceFreq, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(totalAmountDue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(invoiceNumber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(invoiceDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(payingTerm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(invoiceFreq)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(totalAmountDue)
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel6.setText("Details");

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
        jScrollPane1.setViewportView(jTable1);

        remitPaymentTo.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        remitPaymentTo.setText("Remit Payment To:");
        remitPaymentTo.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        totalAmountDue1.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        totalAmountDue1.setText("totalAmountDue");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Total");

        back.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        back.setText("Go Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(remitPaymentTo, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(totalAmountDue1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(back)
                                .addGap(25, 25, 25))))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(remitPaymentTo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalAmountDue1)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(back)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        if(fromInvoice){
            panelHolder.setTitle("Invoice Report");
            panelHolder.getContentPane().removeAll();
            panelHolder.getContentPane().add(new InvoiceReport(panelHolder, systemData));
            panelHolder.getContentPane().revalidate();
        }
        else{
            panelHolder.setTitle("View/Save Invoice");
            panelHolder.getContentPane().removeAll();
            panelHolder.getContentPane().add(new SaveInvoice(panelHolder, systemData));
            panelHolder.getContentPane().revalidate();
        }
    }//GEN-LAST:event_backActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JLabel clientID;
    private javax.swing.JLabel invoiceDate;
    private javax.swing.JLabel invoiceFreq;
    private javax.swing.JLabel invoiceNumber;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel payingTerm;
    private javax.swing.JLabel projectName;
    private javax.swing.JLabel remitPaymentTo;
    private javax.swing.JLabel to;
    private javax.swing.JLabel totalAmountDue;
    private javax.swing.JLabel totalAmountDue1;
    // End of variables declaration//GEN-END:variables
}
