package bookshopant;

import java.awt.Color;
import java.util.Date;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class Order extends JFrame implements ActionListener {
    
    JPanel west, east, south, panelSelect, panelButton;
    JLabel lblOrderCode, lblCustCode, lblCustName, lblCustNamePrint, lblMsg, lblOrderList;
    JTextField txtOrderCode, txtCustCode;
    JButton btnSearch, btnSubmit, btnAdd, btnRemove;
    JTable tblOrderList, tblSelect;
    JComboBox cmbType;
    String headSelectTbl[] = {"Food Name", "Price"};
    String headOrderTbl[] = {"Food Name", "QTY", "AMT"};
    String y[][] = new String[40][8];
    DefaultTableModel orderTblModel;
    int due;
    
    public Order() {
        guiOrder();
    }
    
    private void guiOrder() {
        setTitle("New Order");
        setSize(1000, 800);
        //+++++++declaration
        west = new JPanel();
        west.setBackground(Color.decode("#cccc99"));
        east = new JPanel();
        east.setBackground(Color.decode("#cccc99"));
        south = new JPanel();
        south.setBackground(Color.decode("#cccc99"));
        panelSelect = new JPanel();
        panelSelect.setBackground(Color.decode("#cccc99"));
        panelButton = new JPanel();
        panelButton.setBackground(Color.decode("#cccc99"));
        Font fontForLbl = new Font("Serif", Font.BOLD, 20);
        //west panel declaration
        lblOrderCode = new JLabel("Order Code");
        lblOrderCode.setFont(fontForLbl);
        txtOrderCode = new JTextField();
        txtOrderCode.setFont(fontForLbl);
        txtOrderCode.setEnabled(false);
        lblCustCode = new JLabel("Customer Code");
        lblCustCode.setFont(fontForLbl);
        txtCustCode = new JTextField();
        txtCustCode.setFont(fontForLbl);
        txtCustCode.addKeyListener(new KeyListener() {
            
            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c) || Character.isWhitespace(c) || Character.isISOControl(c)) {
                    txtCustCode.setEditable(true);
                } else {
                    txtCustCode.setEditable(false);
                }
            }
            
            @Override
            public void keyTyped(KeyEvent e) {
                //To change body of generated methods, choose Tools | Templates.
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                //To change body of generated methods, choose Tools | Templates.
            }
        });
        btnSearch = new JButton("SEARCH");
        btnSearch.setFont(fontForLbl);
        btnSearch.addActionListener(this);

        //+++++east panel declaration
        lblCustName = new JLabel("Customer Name");
        lblCustName.setFont(fontForLbl);
        lblCustNamePrint = new JLabel("");
        lblCustNamePrint.setFont(fontForLbl);
        lblMsg = new JLabel("");
        lblMsg.setFont(fontForLbl);
        //+++++end declaration

        //+++++south panel declaration
        lblOrderList = new JLabel("Order List");
        lblOrderList.setFont(fontForLbl);
        
        tblOrderList = new JTable() {
            @Override
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        orderTblModel = (DefaultTableModel) tblOrderList.getModel();
        orderTblModel.addColumn("Code");
        orderTblModel.addColumn("Name");
        orderTblModel.addColumn("QTY");
        orderTblModel.addColumn("AMT");
        tblOrderList.setFont(new Font("Times New Roman", Font.ITALIC, 18));

        //tblOrderList.setEnabled(false);
        JScrollPane sp2 = new JScrollPane(tblOrderList);
        
        btnSubmit = new JButton("Confirm Order");
        btnSubmit.setFont(fontForLbl);
        btnSubmit.addActionListener(this);
        //++++end south panel declaration

        //+++++++panelSelect declaration
        String list[] = {"Food", "Beverages"};
        cmbType = new JComboBox(list);
        //+++++++showing food item on left tabel
        cmbType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        DbConnect conn = new DbConnect();
                        ResultSet rs = conn.s.executeQuery("Select code as 'Code',foodname as 'Food',foodprice as 'Price' from food where type ='" + cmbType.getSelectedItem() + "'");
                        tblSelect.setModel(DbUtils.resultSetToTableModel(rs));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    
                }
            }
        });
        tblSelect = new JTable() {
            @Override
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        tblSelect.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        //tblSelect.setEnabled(false);
        JScrollPane sp1 = new JScrollPane(tblSelect);
        //++++++panel select declaration

        //+++++++panelButton declaration
        btnAdd = new JButton("ADD");
        btnAdd.setFont(fontForLbl);
        btnAdd.addActionListener(this);
        btnRemove = new JButton("REMOVE");
        btnRemove.setFont(fontForLbl);
        btnRemove.addActionListener(this);
        //++++++++end panel Button

        //++++++ panel arrangement on form
        GridBagLayout formLayout = new GridBagLayout();
        setLayout(formLayout);
        GridBagConstraints gbConst = new GridBagConstraints();
        gbConst.fill = GridBagConstraints.BOTH;
        //gbConst.insets=new Insets(10,10,10,10);

        gbConst.weightx = 3;
        gbConst.weighty = 1;
        gbConst.gridx = 0;
        gbConst.gridy = 0;
        gbConst.gridwidth = 3;
        gbConst.gridheight = 1;
        add(west, gbConst);
        gbConst.gridx = 3;
        add(east, gbConst);
        
        gbConst.gridheight = 2;
        gbConst.gridwidth = 2;
        gbConst.weighty = 2;
        gbConst.weightx = 2;
        gbConst.gridx = 0;
        gbConst.gridy = 1;
        add(panelSelect, gbConst);
        
        gbConst.gridwidth = 1;
        gbConst.weightx = 1;
        gbConst.gridx = 2;
        add(panelButton, gbConst);
        
        gbConst.gridwidth = 3;
        gbConst.weightx = 3;
        gbConst.gridx = 3;
        add(south, gbConst);
        //+++++++++ end 

        //+++++west panel
        GridBagLayout westLayout = new GridBagLayout();
        west.setLayout(westLayout);
        gbConst.fill = GridBagConstraints.HORIZONTAL;
        gbConst.insets = new Insets(10, 10, 10, 10);
        gbConst.gridheight = 1;
        gbConst.weighty = 0;
        gbConst.weightx = 1;
        gbConst.gridx = 0;
        gbConst.gridy = 0;
        gbConst.gridwidth = 1;
        west.add(lblOrderCode, gbConst);
        gbConst.weightx = 2;
        gbConst.gridx = 1;
        gbConst.gridwidth = 2;
        west.add(txtOrderCode, gbConst);
        gbConst.weightx = 1;
        gbConst.gridx = 0;
        gbConst.gridy = 1;
        gbConst.gridwidth = 1;
        west.add(lblCustCode, gbConst);
        gbConst.weightx = 2;
        gbConst.gridx = 1;
        gbConst.gridwidth = 2;
        west.add(txtCustCode, gbConst);
        gbConst.weightx = 1;
        gbConst.gridx = 3;
        gbConst.gridwidth = 1;
        west.add(btnSearch, gbConst);
        //+++++end west panel

        //+++++east panel layout
        GridBagLayout eastLayout = new GridBagLayout();
        east.setLayout(eastLayout);
        gbConst.fill = GridBagConstraints.HORIZONTAL;
        gbConst.insets = new Insets(10, 10, 10, 10);
        gbConst.gridheight = 1;
        gbConst.weighty = 0;
        gbConst.weightx = 1;
        gbConst.gridx = 0;
        gbConst.gridy = 0;
        gbConst.gridwidth = 1;
        east.add(lblCustName, gbConst);
        gbConst.gridx = 1;
        gbConst.gridwidth = 2;
        east.add(lblCustNamePrint, gbConst);
        gbConst.gridx = 0;
        gbConst.gridy = 1;
        gbConst.gridwidth = 3;
        east.add(lblMsg, gbConst);
        //+++++east panel

        //++++south panel
        GridBagLayout southLayout = new GridBagLayout();
        south.setLayout(southLayout);
        gbConst.fill = GridBagConstraints.BOTH;
        gbConst.insets = new Insets(10, 10, 10, 10);
        gbConst.gridheight = 1;
        gbConst.weighty = 0;
        gbConst.weightx = 1;
        gbConst.gridx = 0;
        gbConst.gridy = 0;
        gbConst.gridwidth = 1;
        south.add(lblOrderList, gbConst);
        gbConst.gridx = 1;
        south.add(btnAdd, gbConst);
        gbConst.gridx = 2;
        south.add(btnRemove, gbConst);
        gbConst.gridwidth = 3;
        gbConst.gridx = 0;
        gbConst.weighty = 10;
        gbConst.gridy = 1;
        gbConst.gridheight = 10;
        south.add(sp2, gbConst);
        gbConst.weighty = 0;
        gbConst.gridy = 11;
        gbConst.gridheight = 1;
        south.add(btnSubmit, gbConst);
        //++++end soth panel

        //++++++panel Select
        GridBagLayout selectLayout = new GridBagLayout();
        panelSelect.setLayout(selectLayout);
        gbConst.fill = GridBagConstraints.BOTH;
        gbConst.insets = new Insets(10, 10, 10, 10);
        gbConst.gridheight = 1;
        gbConst.weighty = 1;
        gbConst.weightx = 1;
        gbConst.gridx = 0;
        gbConst.gridy = 0;
        gbConst.gridwidth = 1;
        panelSelect.add(cmbType, gbConst);
        gbConst.gridy = 1;
        gbConst.gridheight = 10;
        gbConst.weighty = 10;
        panelSelect.add(sp1, gbConst);

        //++++++end panel select
        //++++++++++panel button
        GridBagLayout buttonLayout = new GridBagLayout();
        panelButton.setLayout(buttonLayout);
        gbConst.fill = GridBagConstraints.HORIZONTAL;
        gbConst.insets = new Insets(10, 10, 10, 10);
        gbConst.weighty = 1;
        gbConst.weightx = 1;
        gbConst.gridheight = 1;
        gbConst.gridwidth = 1;
        gbConst.gridx = 0;
        gbConst.gridy = 0;
        panelButton.add(btnAdd, gbConst);
        gbConst.gridy = 1;
        panelButton.add(btnRemove, gbConst);
        //+++++++++end panel button
        //+++++++++component color
        btnSearch.setBackground(Color.decode("#ffc36b"));
        btnSubmit.setBackground(Color.decode("#ffc36b"));
        btnAdd.setBackground(Color.decode("#ffc36b"));
        btnRemove.setBackground(Color.decode("#ffc36b"));
        //++++++++++++++++++++++
        AutoCode();
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("SUBMIT")) {
            
        } else if (cmd.equals("SEARCH")) {
            search();
        } else if (cmd.equals("ADD")) {
            add();
        } else if (cmd.equals("REMOVE")) {
            remove();
        } else if (cmd.equals("Confirm Order")) {
            confirmOrder();
        }
    }
    
    public static void main(String args[]) {
        new Order().setVisible(true);
    }
    //++++++++back end logic

    //+++auto code
    private void AutoCode() {
        try {
            DbConnect con = new DbConnect();
            String sql = "select code from foodOrder";
            ResultSet rs = con.s.executeQuery(sql);
            if (!rs.next()) {
                txtOrderCode.setText("1");
            } else {
                rs.last();
                int no = rs.getInt("code") + 1;
                txtOrderCode.setText(Integer.toString(no));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //+++++search customer
    private void search() {
        try {
            if (!txtCustCode.getText().isBlank()) {
                String sql = "select *from customer where code = '" + Integer.parseInt(txtCustCode.getText()) + "'";
                DbConnect conn = new DbConnect();
                ResultSet rs = conn.s.executeQuery(sql);
                if (rs.next()) {
                    lblCustNamePrint.setText(rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("lname"));
                    due = rs.getInt("due");
                    java.sql.Date date = rs.getDate("paydate");
                    java.sql.Date now = new java.sql.Date(new java.util.Date().getTime());
                    date = addDays(date, 30);
                    System.out.println(date + " " + now);
                    if (now.after(date)) {
                        lblMsg.setForeground(Color.red);
                    } else {
                        lblMsg.setForeground(Color.white);
                    }
                    
                    lblMsg.setText("Last payment Date : " + rs.getString("paydate") + " Due : " + due);
                } else {
                    JOptionPane.showMessageDialog(this, "Not Found!!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Enter Code");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //++++add data to order table
    private void add() {
        
        int row = tblSelect.getSelectedRow();
        
        //@@@@@@@@@@@@@@null check
        if(row==-1){
            JOptionPane.showMessageDialog(this,"Please select food");
            return;
        }
        String foodcode = tblSelect.getModel().getValueAt(row, 0).toString();
        String foodVal = tblSelect.getModel().getValueAt(row, 1).toString();
        int priceVal = (int) tblSelect.getModel().getValueAt(row, 2);
        
        String qty = JOptionPane.showInputDialog(this, "Enter QTY");
        //@@@null check
        if(qty==null) 
            return;
        if(!isNumber(qty)){
            JOptionPane.showMessageDialog(this,"Only number allowed");
            return;
        }
        
        int amt = Integer.parseInt(qty) * priceVal;
        System.out.println(foodVal + " " + priceVal + " " + qty + " " + amt);
        orderTblModel = (DefaultTableModel) tblOrderList.getModel();
        List<String> list = new ArrayList<String>();
        list.add(foodcode);
        list.add(foodVal);
        list.add(qty);
        list.add(Integer.toString(amt));
        orderTblModel.addRow(list.toArray());
        //tblOrderList.setModel(model);
    }

    //++++++remove row from order list
    private void remove() {
        int row=tblOrderList.getSelectedRow();
        //System.out.println(row);
        if(row==-1){
            JOptionPane.showMessageDialog(this,"Please select food");
            return;
        }
        orderTblModel.removeRow(row);
    }

    //+++++++confirm order 
    private void confirmOrder() {
        try {
            if(txtCustCode.getText().isBlank()){
                JOptionPane.showMessageDialog(this,"Please select customer");
            }
            else if (tblOrderList.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Empty Order List");
            } else {
                DbConnect db = new DbConnect();
                int orderCode, custCode, amt, foodCode, qty;
                amt = 0;
                orderCode = Integer.parseInt(txtOrderCode.getText());
                custCode = Integer.parseInt(txtCustCode.getText());
                java.util.Date date = new java.util.Date();
                java.sql.Date sdate = new java.sql.Date(date.getTime());
                System.out.print(date.getTime());
                
                orderTblModel = (DefaultTableModel) tblOrderList.getModel();
                for (int i = 0; i < tblOrderList.getRowCount(); i++) {
                    foodCode = Integer.parseInt(orderTblModel.getValueAt(i, 0).toString());
                    qty = Integer.parseInt(orderTblModel.getValueAt(i, 2).toString());
                    amt += Integer.parseInt(orderTblModel.getValueAt(i, 3).toString());
                    
                    db.s.executeUpdate("insert into foodorderlist values('" + orderCode + "','" + foodCode + "','" + qty + "')");
                    
                }
                db.s.executeUpdate("insert into foodorder values('" + orderCode + "','" + custCode + "','" + sdate + "','" + amt + "')");
                due += amt;
                db.s.executeUpdate("Update customer set due= '" + due + "' where code= '" + txtCustCode.getText() + "'");
                JOptionPane.showMessageDialog(this, "Done");
                AutoCode();
                txtCustCode.setText("");
                lblCustNamePrint.setText("");
                lblMsg.setText("");
                orderTblModel.setRowCount(0);
                DefaultTableModel selectTblModel = (DefaultTableModel) tblSelect.getModel();
                selectTblModel.setRowCount(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static java.sql.Date addDays(Date date, int days) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);
        c.add(java.util.Calendar.DATE, days);
        System.out.println(c.getTimeInMillis());
        return new java.sql.Date(c.getTimeInMillis());
    }
    static boolean isNumber(String s){
        for(int i=0;i<s.length();i++){
            if(Character.isDigit(s.charAt(i))==false)
                return false;
            
         
        }
        return true;
    }
}
