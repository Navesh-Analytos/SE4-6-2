package forms;

import connections.SystemData;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;

public class HomePage extends javax.swing.JPanel {
    JFrame  panelHolder;
    SystemData systemData;
    DefaultListModel model;
    
    public HomePage(JFrame  panelHolder, SystemData systemData, boolean isFirstLogin) {
        this.panelHolder = panelHolder;
        this.systemData = systemData;        
        initComponents(); 
                
        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(new Font("Times New Roman", Font.BOLD, 12));
        panelHolder.setJMenuBar(menuBar);

        JMenu mnMaintain = new JMenu("Maintain");
        mnMaintain.setFont(new Font("Times New Roman", Font.BOLD, 12));
        menuBar.add(mnMaintain);
        if(systemData.getCurrentUser().getEmployeeType().equals("Developer") ||
            systemData.getCurrentUser().getEmployeeType().equals("Project Manager")   ){
            mnMaintain.setEnabled(false);
        }
        
        JMenuItem mntmCompany = new JMenuItem("Company");
        mntmCompany.setFont(new Font("Times New Roman", Font.BOLD, 12));
        mnMaintain.add(mntmCompany);  
        mntmCompany.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHolder.setTitle("Company Information");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new CompanyInformation(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();        
            }
        });
        
        JMenuItem mntmClient = new JMenuItem("Client");
        mntmClient.setFont(new Font("Times New Roman", Font.BOLD, 12));
        mnMaintain.add(mntmClient);
        mntmClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHolder.setTitle("Client Information");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new ClientInformation(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();        
            }
        });             
        
        JMenuItem mntmEmployee = new JMenuItem("Employee");
        mntmEmployee.setFont(new Font("Times New Roman", Font.BOLD, 12));
        mnMaintain.add(mntmEmployee);
        mntmEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHolder.setTitle("Employee Information");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new EmployeeInformation(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();        
            }
        }); 
                

        JMenu mnManage = new JMenu("Manage");        
        mnManage.setFont(new Font("Times New Roman", Font.BOLD, 12));
        menuBar.add(mnManage);
        if(!systemData.getCurrentUser().getEmployeeType().equals("Project Manager")   ){
            mnManage.setEnabled(false);
        }
        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Manage Project-Person");
        mntmNewMenuItem_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
        mnManage.add(mntmNewMenuItem_1);
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                panelHolder.setTitle("Manage Project-Person");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new ProjectPersonInformation(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();        
            }
        });
        
        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Approve Hours");        
        mntmNewMenuItem_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
        mnManage.add(mntmNewMenuItem_2);
        mntmNewMenuItem_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                panelHolder.setTitle("Approve Hours");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new ApproveHours(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();        
            }
        });
        JMenuItem mntmManageProject = new JMenuItem("Project");              
        mntmManageProject.setFont(new Font("Times New Roman", Font.BOLD, 12));
        mnManage.add(mntmManageProject);
        mntmManageProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHolder.setTitle("Project Information");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new ProjectInformation(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();        
            }
        });
              
        
        JMenu mnInvoice = new JMenu("Invoice");
        mnInvoice.setFont(new Font("Times New Roman", Font.BOLD, 12));
        menuBar.add(mnInvoice);
        if(systemData.getCurrentUser().getEmployeeType().equals("Developer") ||
            systemData.getCurrentUser().getEmployeeType().equals("Project Manager")   ){
            mnInvoice.setEnabled(false);
        }
        JMenuItem mntmGenerateInvoice = new JMenuItem("Generate / Mail Invoice");
        mntmGenerateInvoice.setFont(new Font("Times New Roman", Font.BOLD, 12));
        mnInvoice.add(mntmGenerateInvoice);
        mntmGenerateInvoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        JMenuItem mntmNewMenuItem_3 = new JMenuItem("View / Save as PDF");
        mntmNewMenuItem_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
        mnInvoice.add(mntmNewMenuItem_3);
        mntmNewMenuItem_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });                
        
        JMenu mnReport = new JMenu("Report");
        mnReport.setFont(new Font("Times New Roman", Font.BOLD, 12));
        menuBar.add(mnReport);
        JMenuItem mntmGenerateSchedule = new JMenuItem("Available Employees");
        mntmGenerateSchedule.setFont(new Font("Times New Roman", Font.BOLD, 12));
        mnReport.add(mntmGenerateSchedule);
        mntmGenerateSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                panelHolder.setTitle("Available Employees");
//                panelHolder.getContentPane().removeAll();
//		panelHolder.getContentPane().add(new AvailableEmployees(panelHolder, systemData));
//		panelHolder.getContentPane().revalidate();        
            }
        });
        JMenuItem mntmNewMenuItem = new JMenuItem("Worked Hours");
        mntmNewMenuItem.setFont(new Font("Times New Roman", Font.BOLD, 12));
        mnReport.add(mntmNewMenuItem);
        mntmNewMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                panelHolder.setTitle("Hours Clocked");
//                panelHolder.getContentPane().removeAll();
//		panelHolder.getContentPane().add(new HoursClocked(panelHolder, systemData));
//		panelHolder.getContentPane().revalidate();        
            }
        });
        JMenuItem mntmProjectReport= new JMenuItem("Project Report");
        mntmProjectReport.setFont(new Font("Times New Roman", Font.BOLD, 12));
        mnReport.add(mntmProjectReport);
        mntmProjectReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                panelHolder.setTitle("Project Report");
//                panelHolder.getContentPane().removeAll();
//		panelHolder.getContentPane().add(new ProjectReport(panelHolder, systemData));
//		panelHolder.getContentPane().revalidate();        
            }
        });
                
                
        JMenu mnCurUser = new JMenu(systemData.getCurrentUser().getUserName());
        mnCurUser.setFont(new Font("Times New Roman", Font.BOLD, 12));
        menuBar.add(mnCurUser);
        
        if(!systemData.getCurrentUser().getEmployeeType().equals("Accountant")){
            JMenuItem mntmClockHours = new JMenuItem("Clock Hours");            
            mntmClockHours.setFont(new Font("Times New Roman", Font.BOLD, 12));
            mnCurUser.add(mntmClockHours);
            mntmClockHours.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panelHolder.setTitle("Clock Hours");
                    panelHolder.getContentPane().removeAll();
                    panelHolder.getContentPane().add(new ClockHours(panelHolder, systemData));
                    panelHolder.getContentPane().revalidate();      
                }
            }); 
        }        
        
        JMenuItem mntmChangePassword = new JMenuItem("Change Password");  
        mntmChangePassword.setFont(new Font("Times New Roman", Font.BOLD, 12));
        mnCurUser.add(mntmChangePassword);
        mntmChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHolder.setTitle("Change Password");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new ChangePassword(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();      
            }
        });   
        
        JMenuItem mntmLogout = new JMenuItem("Logout");        
        mntmLogout.setFont(new Font("Times New Roman", Font.BOLD, 12));
        mnCurUser.add(mntmLogout);
        mntmLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHolder.setTitle("Login Page");
                panelHolder.setJMenuBar(null);
                panelHolder.getContentPane().removeAll();                                
		panelHolder.getContentPane().add(new LoginPage(panelHolder));
		panelHolder.getContentPane().revalidate();      
            }
        });     
        
        
                
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jPanel2 = new javax.swing.JPanel();
        welcomeMessage = new javax.swing.JLabel();
        welcomeMessage1 = new javax.swing.JLabel();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("File");
        jMenuBar3.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar3.add(jMenu6);

        welcomeMessage.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        welcomeMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeMessage.setText("Welcome to:");

        welcomeMessage1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        welcomeMessage1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeMessage1.setText("Invoice Generation System");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(welcomeMessage1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                    .addComponent(welcomeMessage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(welcomeMessage)
                .addGap(18, 18, 18)
                .addComponent(welcomeMessage1)
                .addGap(104, 104, 104))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel welcomeMessage;
    private javax.swing.JLabel welcomeMessage1;
    // End of variables declaration//GEN-END:variables
}
