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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class AddCustomer extends JFrame implements ActionListener,KeyListener {

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
        switch (form) {
            case 2:
                editCustomer();
                break;
            case 3:
                deleteCustomer();
                break;
            default:
                gbConst.insets = new Insets(0, 20, 100, 20);
                gbConst.weightx = 1;
                gbConst.gridwidth = 2;
                gbConst.anchor = GridBagConstraints.BASELINE_TRAILING;
                gbConst.gridx = 0;
                gbConst.gridy = 9;
                leftPanel.add(btnSubmit, gbConst);
                txtSearch.setEditable(false);
                txtSearch.setEnabled(false);
                try {
                    //Auto code generation
                    AutoCode();
                } catch (SQLException ex) {
                    Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
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
        txtSearch.addKeyListener(this);
        //First Name
        lblFirstName = new JLabel("First Name");
        txtFirstName = new JTextField();
        lblFirstName.setFont(dataFont);
        txtFirstName.setFont(dataFont);
        txtFirstName.addKeyListener(this);
        //middle Name
        lblMiddleName = new JLabel("Middle Name");
        txtMiddleName = new JTextField();
        lblMiddleName.setFont(dataFont);
        txtMiddleName.setFont(dataFont);
        txtMiddleName.addKeyListener(this);
        //Last NAme
        lblLastName = new JLabel("Last Name");
        txtLastName = new JTextField();
        lblLastName.setFont(dataFont);
        txtLastName.setFont(dataFont);
        txtLastName.addKeyListener(this);
        //Address
        lblAddress = new JLabel("Address");
        txtAddress = new JTextField();
        lblAddress.setFont(dataFont);
        txtAddress.setFont(dataFont);
        txtAddress.addKeyListener(this);
        //Mobile no
        lblContactNo = new JLabel("Contact No");
        txtContactNo = new JTextField();
        lblContactNo.setFont(dataFont);
        txtContactNo.setFont(dataFont);
        txtContactNo.addKeyListener(this);
        //E-mail
        lblEmail = new JLabel("Email");
        txtEmail = new JTextField();
        lblEmail.setFont(dataFont);
        txtEmail.setFont(dataFont);
        txtEmail.addKeyListener(this);
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
        switch (cmd) {
            case "SUBMIT":
                add();
                break;
            case "SEARCH":
                search();
                break;
            case "UPDATE":
                edit();
                break;
            case "DELETE":
                delete();
                break;
            default:
                break;
        }

    }

    public static void main(String args[]) {
        new AddCustomer(1).setVisible(true);
    }

    //back end logic
    //to add customer 
    private void add() {
        try {
            if (checkNull()&&contactCheck()) {
                int code = Integer.parseInt(txtSearch.getText());
                String fname = txtFirstName.getText();
                String mname = txtMiddleName.getText();
                String lname = txtLastName.getText();
                String addr = txtAddress.getText();
                String cno = txtContactNo.getText();
                String email = txtEmail.getText();
                java.util.Date date = new java.util.Date();
                java.sql.Date sdate = new java.sql.Date(date.getTime());
                int due = 0;

                String sql = "insert into customer values('" + code + "','" + fname + "','" + mname + "','" + lname + "','" + addr + "','" + cno + "','" + email + "',now(),'" + due + "')";
                DbConnect con = new DbConnect();
                con.s.executeUpdate(sql);
                clearText();
                JOptionPane.showMessageDialog(this, "Done!!");
                AutoCode();
            } else {
                JOptionPane.showMessageDialog(this, "Fill all details");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void edit() {
        try {
            if (checkNull()&&contactCheck()) {
                String sql = "update customer set fname='"
                        + txtFirstName.getText()
                        + "',mname='"
                        + txtMiddleName.getText()
                        + "',lname='"
                        + txtLastName.getText()
                        + "',addr='"
                        + txtAddress.getText()
                        + "',contact='"
                        + txtContactNo.getText()
                        + "',email='"
                        + txtEmail.getText()
                        + "' where code='"
                        + Integer.parseInt(txtSearch.getText()) + "'";

                DbConnect conn = new DbConnect();
                conn.s.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, "Updated!!");
            } else {
                JOptionPane.showMessageDialog(this, "Fill all details");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void delete() {
        try {
            DbConnect conn = new DbConnect();
            String sql = "Delete from customer where code ='" + Integer.parseInt(txtSearch.getText()) + "'";
            conn.s.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Deleted!!");
        } catch (Exception e) {
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
            if(!txtSearch.getText().isBlank()){
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
            }else{
                JOptionPane.showMessageDialog(this,"Enter Code ");
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

    public static java.sql.Date addDays(java.util.Date date, int days) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);
        c.add(java.util.Calendar.DATE, days);
        System.out.println(c.getTimeInMillis());
        return new java.sql.Date(c.getTimeInMillis());
    }

    //+++++++++++++++
    //@@@@@@@@@@@@@@@@@@@@@@Exception handeling
    boolean checkNull() {
        return !(txtFirstName.getText().equals("") || txtMiddleName.getText().equals("") || txtLastName.getText().equals("") || txtSearch.getText().equals("") || txtContactNo.getText().equals("") || txtAddress.getText().equals("") || txtEmail.getText().equals(""));

    }
    
    boolean contactCheck(){
        return txtContactNo.getText().length() == 10;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Object source = e.getSource();
        
        char c=e.getKeyChar();
        if(source==txtSearch){
            
            
            if(Character.isDigit(c)||Character.isWhitespace(c)||Character.isISOControl(c)){
              txtSearch.setEditable(true);
            }else{
              txtSearch.setEditable(false);
            }
        }else if(source==txtFirstName){
            
            if(Character.isLetter(c)||Character.isWhitespace(c)||Character.isISOControl(c)){
              txtFirstName.setEditable(true);
            }else{
              txtFirstName.setEditable(false);
            }
        }else if(source==txtMiddleName){
            
            if(Character.isLetter(c)||Character.isWhitespace(c)||Character.isISOControl(c)){
              txtMiddleName.setEditable(true);
            }else{
              txtMiddleName.setEditable(false);
            }
        }else if(source==txtLastName){
            
            if(Character.isLetter(c)||Character.isWhitespace(c)||Character.isISOControl(c)){
              txtLastName.setEditable(true);
            }else{
              txtLastName.setEditable(false);
            }
        }else if(source==txtContactNo){
            
            if(Character.isDigit(c)||Character.isWhitespace(c)||Character.isISOControl(c)){
              txtContactNo.setEditable(true);
            }else{
              txtContactNo.setEditable(false);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
