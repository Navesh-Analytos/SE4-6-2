package forms;

import connections.SystemData;
import javax.swing.JFrame;

import connections.ConnectToDB;
import entities.Client;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class ClientInformation extends javax.swing.JPanel {
    JFrame  panelHolder;
    SystemData systemData;
      
    DefaultTableModel tableModel;
    List<Client> clientList;
    
    public ClientInformation(JFrame  panelHolder, SystemData systemData) {
        this.panelHolder = panelHolder;
        this.systemData = systemData;  
        initComponents();
        ConnectToDB cm = new ConnectToDB();
        EntityManager em = cm.getEntityManager();
        Query query = em.createQuery("Select c  from Client c");
        clientList = query.getResultList();
        
        Object[] columnNames = {"Name", "Address Line 1", "Address Line 2", "City",
            "State", "Zip", "Email", "Contact", "Invoice Freq", "Billing Terms", "Invoice Grouping"};
        Object[][] rowData = new Object[clientList.size()][11];
        int i =0;
        for(Client client: clientList){
            rowData[i][0] = (Object) client.getName();
            rowData[i][1] = (Object) client.getAddressLine1();
            rowData[i][2] = (Object) client.getAddressLine2();
            rowData[i][3] = (Object) client.getCity();
            rowData[i][4] = (Object) client.getState();
            rowData[i][5] = (Object) client.getZip();
            rowData[i][6] = (Object) client.getEmail();
            rowData[i][7] = (Object) client.getContact();
            rowData[i][8] = (Object) client.getInvoiceFrequency();
            rowData[i][9] = (Object) client.getBillingTerm();
            rowData[i][10] = (Object) client.getInvoiceGrouping();
            ++i;
        }
        
        tableModel = new DefaultTableModel(rowData, columnNames);
        jTable1.setModel(tableModel);
        jTable1.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));
        
        cm.close();        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        jPanel1.setLayout(new java.awt.GridBagLayout());

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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 311;
        gridBagConstraints.ipady = 213;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 11, 10);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        addButton.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        editButton.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        backButton.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(backButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton)
                    .addComponent(addButton)
                    .addComponent(backButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addGap(40, 40, 40))
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
    }// </editor-fold>//GEN-END:initComponents

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        if(jTable1.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Please select a Client to edit!");
            return;
        }
        Client selectedClient = clientList.get(jTable1.getSelectedRow());
        
        panelHolder.setTitle("Edit Client");
        panelHolder.getContentPane().removeAll();
        panelHolder.getContentPane().add(new ClientAddEdit(panelHolder, systemData, true, selectedClient));
        panelHolder.getContentPane().revalidate();
        
    }//GEN-LAST:event_editButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        panelHolder.setTitle("Add new Client");
        panelHolder.getContentPane().removeAll();
        panelHolder.getContentPane().add(new ClientAddEdit(panelHolder, systemData, false, null));
        panelHolder.getContentPane().revalidate();  
    }//GEN-LAST:event_addButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        panelHolder.setTitle("Home Page");
        panelHolder.getContentPane().removeAll();
        panelHolder.getContentPane().add(new HomePage(panelHolder, systemData, true));
        panelHolder.getContentPane().revalidate();
    }//GEN-LAST:event_backButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton editButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}