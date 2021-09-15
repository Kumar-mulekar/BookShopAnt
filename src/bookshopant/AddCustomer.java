package bookshopant;

import java.sql.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class AddCustomer extends JFrame implements ActionListener {

    private JPanel leftPanel, rightPanel;
    private JLabel lblCustCode, lblCustCodeAuto, lblAddCustomer;
    private JLabel lblFirstName, lblMiddleName, lblLastName;
    private JTextField txtFirstName, txtMiddleName, txtLastName, txtSearch;
    private JLabel lblContactNo, lblAddress, lblEmail;
    private JTextField txtContactNo, txtAddress, txtEmail;
    private JButton btnSubmit, btnSearch;
    private JLabel lblImg;
    private GridBagConstraints gbConst;

    public AddCustomer(int form) {
        guiAddCustomer();
        if (form == 2) {
            editCustomer();
        } else if (form == 3) {
            deleteCustomer();
        } else {
            gbConst.insets = new Insets(0, 20, 100, 20);
            gbConst.weightx = 1;
            gbConst.gridwidth = 2;
            gbConst.anchor = GridBagConstraints.BASELINE_TRAILING;
            gbConst.gridx = 0;
            gbConst.gridy = 9;
            leftPanel.add(btnSubmit, gbConst);
            txtSearch.setEditable(false);

            try {
                //Auto code generation
                AutoCode();
            } catch (SQLException ex) {
                Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    void editCustomer() {
        setTitle("Edit Customer");
        lblAddCustomer.setText("Edit Customer");
        btnSubmit.setText("UPDATE");
        //for search nd edit button arangement
        commonEditDelete();

    }

    void deleteCustomer() {
        setTitle("Delete Customer");
        lblAddCustomer.setText("Delete Customer");
        btnSubmit.setText("DELETE");
        //for search nd edit button arangement
        commonEditDelete();
    }

    void commonEditDelete() {
        gbConst.insets = new Insets(0, 20, 100, 20);
        gbConst.weightx = 1;
        gbConst.gridwidth = 1;
        gbConst.anchor = GridBagConstraints.BASELINE_TRAILING;
        gbConst.gridx = 1;
        gbConst.gridy = 9;
        leftPanel.add(btnSubmit, gbConst);
        gbConst.gridx = 0;
        leftPanel.add(btnSearch, gbConst);
    }

    private void guiAddCustomer() {
        setSize(1000, 600);
        setTitle("Add Customer");
        setLayout(new GridLayout());

        leftPanel = new JPanel();
        rightPanel = new JPanel();

        //+++++left Panel
        //add customer
        lblAddCustomer = new JLabel("Add Customer", SwingConstants.CENTER);
        //lblAddCustomer.setOpaque(true);
        //lblAddCustomer.setBackground(Color.cyan);
        Font headFont = new Font("Serif", Font.BOLD, 50);
        lblAddCustomer.setFont(headFont);

        Font dataFont = new Font("Serif", Font.BOLD, 20);

        //Customer code
        lblCustCode = new JLabel("Customer Code ");
        lblCustCodeAuto = new JLabel("CT0001");
        txtSearch = new JTextField();
        lblCustCode.setFont(dataFont);
        txtSearch.setFont(dataFont);
        //First Name
        lblFirstName = new JLabel("First Name");
        txtFirstName = new JTextField();
        lblFirstName.setFont(dataFont);
        txtFirstName.setFont(dataFont);

        //middle Name
        lblMiddleName = new JLabel("Middle Name");
        txtMiddleName = new JTextField();
        lblMiddleName.setFont(dataFont);
        txtMiddleName.setFont(dataFont);

        //Last NAme
        lblLastName = new JLabel("Last Name");
        txtLastName = new JTextField();
        lblLastName.setFont(dataFont);
        txtLastName.setFont(dataFont);

        //Address
        lblAddress = new JLabel("Address");
        txtAddress = new JTextField();
        lblAddress.setFont(dataFont);
        txtAddress.setFont(dataFont);

        //Mobile no
        lblContactNo = new JLabel("Contact No");
        txtContactNo = new JTextField();
        lblContactNo.setFont(dataFont);
        txtContactNo.setFont(dataFont);

        //E-mail
        lblEmail = new JLabel("Email");
        txtEmail = new JTextField();
        lblEmail.setFont(dataFont);
        txtEmail.setFont(dataFont);

        //Submit
        btnSubmit = new JButton("SUBMIT");
        btnSubmit.setFont(dataFont);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBackground(Color.decode("#ffc36b"));
        btnSubmit.addActionListener(this);

        //search
        btnSearch = new JButton("SEARCH");
        btnSearch.setFont(dataFont);
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setBackground(Color.decode("#ffc36b"));
        btnSearch.addActionListener(this);

        //image
        lblImg = new JLabel();

        //++++++end left panel
        //++++++++Grid bag Layout
        GridBagLayout gridBagLayout = new GridBagLayout();
        leftPanel.setLayout(gridBagLayout);

        gbConst = new GridBagConstraints();
        gbConst.fill = GridBagConstraints.HORIZONTAL;
        gbConst.insets = new Insets(0, 20, 0, 20);
        //add customer
        gbConst.gridx = 0;
        gbConst.gridy = 0;
        gbConst.gridheight = 1;
        gbConst.gridwidth = 2;
        gbConst.anchor = GridBagConstraints.CENTER;
        gbConst.weighty = 1;

        leftPanel.add(lblAddCustomer, gbConst);
        gbConst.weighty = 0.1;
        //cust code

        //first name
        gbConst.gridy = 1;
        gbConst.weightx = 1;
        gbConst.gridwidth = 1;
        leftPanel.add(lblCustCode, gbConst);
        gbConst.gridx = 1;
        leftPanel.add(txtSearch, gbConst);

        //middle name
        gbConst.gridx = 0;
        gbConst.gridy = 2;
        leftPanel.add(lblFirstName, gbConst);
        gbConst.gridx = 1;
        leftPanel.add(txtFirstName, gbConst);
        //last name
        gbConst.gridx = 0;
        gbConst.gridy = 3;
        leftPanel.add(lblMiddleName, gbConst);
        gbConst.gridx = 1;
        leftPanel.add(txtMiddleName, gbConst);

        gbConst.gridx = 0;
        gbConst.gridy = 4;
        gbConst.gridheight = 2;
        leftPanel.add(lblLastName, gbConst);
        gbConst.gridx = 1;
        leftPanel.add(txtLastName, gbConst);

        gbConst.gridx = 0;
        gbConst.gridy = 6;
        gbConst.gridheight = 1;
        leftPanel.add(lblAddress, gbConst);
        gbConst.gridx = 1;
        leftPanel.add(txtAddress, gbConst);

        gbConst.gridx = 0;
        gbConst.gridy = 7;
        leftPanel.add(lblContactNo, gbConst);
        gbConst.gridx = 1;
        leftPanel.add(txtContactNo, gbConst);

        gbConst.gridx = 0;
        gbConst.gridy = 8;
        leftPanel.add(lblEmail, gbConst);
        gbConst.gridx = 1;
        leftPanel.add(txtEmail, gbConst);

        //++++end grid bag layout
        leftPanel.setBackground(Color.decode("#cccc99"));
        add(leftPanel);
        add(rightPanel);
        setVisible(true);
        //++++right Panel
        rightPanel.setBackground(Color.decode("#cccc99"));

        ImageIcon loadImg = new ImageIcon(ClassLoader.getSystemResource("pic/hum.png"));
        Image resizeImg = loadImg.getImage().getScaledInstance(rightPanel.getWidth(), rightPanel.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon finalImg = new ImageIcon(resizeImg);
        lblImg.setIcon(finalImg);
        rightPanel.add(lblImg);

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                //System.out.println(rightPanel.getWidth());
                Image resizeImg = loadImg.getImage().getScaledInstance(rightPanel.getWidth(), rightPanel.getHeight(), Image.SCALE_DEFAULT);
                ImageIcon finalImg = new ImageIcon(resizeImg);
                lblImg.setIcon(finalImg);
            }
        });

        //+++end right panel
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("SUBMIT")) {
            add();
        } else if (cmd.equals("SEARCH")) {
            search();
        }else if(cmd.equals("UPDATE")){
            
            edit();
        }else if(cmd.equals("DELETE")){
            
            delete();
        }

    }

    public static void main(String args[]) {
        new AddCustomer(1).setVisible(true);
    }

    //back end logic
    //to add customer 
    private void add() {
        try {
            int code = Integer.parseInt(txtSearch.getText());
            String fname = txtFirstName.getText();
            String mname = txtMiddleName.getText();
            String lname = txtLastName.getText();
            String addr = txtAddress.getText();
            String cno = txtContactNo.getText();
            String email = txtEmail.getText();

            String sql = "insert into customer values('" + code + "','" + fname + "','" + mname + "','" + lname + "','" + addr + "','" + cno + "','" + email + "')";
            DbConnect con = new DbConnect();
            con.s.executeUpdate(sql);
            clearText();
            JOptionPane.showMessageDialog(this, "Done!!");
            AutoCode();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    private void edit(){
        try{
            String sql="update customer set fname='"
                        +txtFirstName.getText()+
                        "',mname='"
                        +txtMiddleName.getText()+
                        "',lname='"
                        +txtLastName.getText()+
                        "',addr='"
                        +txtAddress.getText()+
                        "',contact='"
                        +txtContactNo.getText()+
                        "',email='"
                        +txtEmail.getText()+
                        "' where code='"
                        +Integer.parseInt(txtSearch.getText())+"'";
            
            DbConnect conn=new DbConnect();
            conn.s.executeUpdate(sql);
            JOptionPane.showMessageDialog(this,"Updated!!");
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    private void delete(){
        try{
            DbConnect conn=new DbConnect();
            String sql="Delete from customer where code ='"+Integer.parseInt(txtSearch.getText())+"'";
            conn.s.executeUpdate(sql);
            JOptionPane.showMessageDialog(this,"Deleted!!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void AutoCode() throws SQLException {
        DbConnect con = new DbConnect();
        String sql = "select code from customer";
        ResultSet rs = con.s.executeQuery(sql);
        if (!rs.next()) {
            txtSearch.setText("1");
        } else {
            rs.last();
            int no = rs.getInt("code") + 1;
            txtSearch.setText(Integer.toString(no));
        }
    }

    private void search() {
        try {
            String sql = "select *from customer where code = '" + Integer.parseInt(txtSearch.getText()) + "'";
            DbConnect conn = new DbConnect();
            ResultSet rs = conn.s.executeQuery(sql);
            if (rs.next()) {

                txtFirstName.setText(rs.getString("fname"));
                txtMiddleName.setText(rs.getString("mname"));
                txtLastName.setText(rs.getString("lname"));
                txtAddress.setText(rs.getString("addr"));
                txtContactNo.setText(rs.getString("contact"));
                txtEmail.setText(rs.getString("email"));
            } else {
                JOptionPane.showMessageDialog(this, "Not Found!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearText() {
        txtSearch.setText("");
        txtFirstName.setText("");
        txtMiddleName.setText("");
        txtLastName.setText("");
        txtAddress.setText("");
        txtContactNo.setText("");
        txtEmail.setText("");
    }

    //+++++++++++++++
}
