
package bookshopant;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Set;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class Order extends JFrame implements ActionListener{
    JPanel west,east,south;
    JLabel lblOrderCode,lblCustCode,lblCustName,lblCustNamePrint,lblMsg,lblOrderList;
    JTextField txtOrderCode,txtCustCode;
    JButton btnSearch,btnSubmit,btnAdd,btnRemove;
    JTable tblOrderList;
    public Order(){
        guiOrder();
    }
    private void guiOrder(){
        setTitle("New Order");
        setSize(1000,700);
        //+++++++declaration
        west=new JPanel();
        //west.setBackground(Color.red);
        east=new JPanel();
        //east.setBackground(Color.BLUE);
        south=new JPanel();
        //south.setBackground(Color.BLACK);
        Font fontForLbl=new Font("Serif",Font.BOLD,20);
        //west panel declaration
        lblOrderCode=new JLabel("Order Code");
        lblOrderCode.setFont(fontForLbl);
        txtOrderCode=new JTextField();
        txtOrderCode.setFont(fontForLbl);
        txtOrderCode.setEnabled(false);
        lblCustCode=new JLabel("Customer Code");
        lblCustCode.setFont(fontForLbl);
        txtCustCode=new JTextField();
        txtCustCode.setFont(fontForLbl);
        btnSearch=new JButton("SEARCH");
        btnSearch.setFont(fontForLbl);
        btnSearch.addActionListener(this);
        
        //+++++east panel declaration
        lblCustName=new JLabel("Customer Name");
        lblCustName.setFont(fontForLbl);
        lblCustNamePrint=new JLabel("lblCustNamePrint");
        lblCustNamePrint.setFont(fontForLbl);
        lblMsg=new JLabel("lblMsg");
        lblMsg.setFont(fontForLbl);
        //+++++end declaration
        
        //+++++south panel declaration
        lblOrderList=new JLabel("Order List");
        lblOrderList.setFont(fontForLbl);
        
        btnAdd=new JButton("ADD");
        btnAdd.setFont(fontForLbl);
        btnAdd.addActionListener(this);
        btnRemove=new JButton("REMOVE");
        btnRemove.setFont(fontForLbl);
        btnRemove.addActionListener(this);
        
        String column[]={"FOOD","QTY","AMOUNT"};
        DefaultTableModel tblModel=new DefaultTableModel(column,3);
        tblOrderList=new JTable(tblModel);
        JScrollPane tbl=new JScrollPane(tblOrderList);
        
        btnSubmit=new JButton("Confirm Order");
        btnSubmit.setFont(fontForLbl);
        btnSubmit.addActionListener(this);
        //++++end south panel declaration
        
        //++++++ panel arrangement on form
        GridBagLayout formLayout=new GridBagLayout();
        setLayout(formLayout);
        GridBagConstraints gbConst=new GridBagConstraints();
        gbConst.fill=GridBagConstraints.BOTH;
        gbConst.weightx=1;
        gbConst.weighty=1;
        gbConst.gridx=0;
        gbConst.gridy=0;
        add(west,gbConst);
        gbConst.gridx=1;
        add(east,gbConst);
        gbConst.weighty=2;
        gbConst.gridwidth=2;
        gbConst.gridx=0;
        gbConst.gridy=1;
        add(south,gbConst);
        //+++++++++ end 
        
        //+++++west panel
        GridBagLayout westLayout=new GridBagLayout();
        west.setLayout(westLayout);
        gbConst.fill=GridBagConstraints.HORIZONTAL;
        gbConst.insets=new Insets(10,10,10,10);
        gbConst.gridheight=1;
        gbConst.weighty=0;
        gbConst.weightx=1;
        gbConst.gridx=0;gbConst.gridy=0;
        gbConst.gridwidth=1;
        west.add(lblOrderCode,gbConst);
        gbConst.weightx=2;gbConst.gridx=1;
        gbConst.gridwidth=2;
        west.add(txtOrderCode,gbConst);
        gbConst.weightx=1;
        gbConst.gridx=0;gbConst.gridy=1;
        gbConst.gridwidth=1;
        west.add(lblCustCode,gbConst);
        gbConst.weightx=2;gbConst.gridx=1;
        gbConst.gridwidth=2;
        west.add(txtCustCode,gbConst);
        gbConst.weightx=1;gbConst.gridx=3;
        gbConst.gridwidth=1;
        west.add(btnSearch,gbConst);
        //+++++end west panel
        
        //+++++east panel layout
        GridBagLayout eastLayout=new GridBagLayout();
        east.setLayout(eastLayout);
        gbConst.fill=GridBagConstraints.HORIZONTAL;
        gbConst.insets=new Insets(10,10,10,10);
        gbConst.gridheight=1;
        gbConst.weighty=0;
        gbConst.weightx=1;
        gbConst.gridx=0;gbConst.gridy=0;
        gbConst.gridwidth=1;
        east.add(lblCustName,gbConst);
        gbConst.gridx=1;gbConst.gridwidth=2;
        east.add(lblCustNamePrint,gbConst);
        gbConst.gridx=0;gbConst.gridy=1;
        gbConst.gridwidth=3;
        east.add(lblMsg,gbConst);
        //+++++east panel
        
        //++++south panel
        GridBagLayout southLayout=new GridBagLayout();
        south.setLayout(southLayout);
        gbConst.fill=GridBagConstraints.BOTH;
        gbConst.insets=new Insets(10,10,10,10);
        gbConst.gridheight=1;
        gbConst.weighty=0;
        gbConst.weightx=1;
        gbConst.gridx=0;gbConst.gridy=0;
        gbConst.gridwidth=1;
        south.add(lblOrderList,gbConst);
        gbConst.gridx=1;
        south.add(btnAdd,gbConst);
        gbConst.gridx=2;
        south.add(btnRemove,gbConst);
        gbConst.gridwidth=3;
        gbConst.gridx=0;
        gbConst.weighty=10;
        gbConst.gridy=1;
        gbConst.gridheight=10;
        south.add(tblOrderList,gbConst);
        gbConst.weighty=0;
        gbConst.gridy=11;
        gbConst.gridheight=1;
        south.add(btnSubmit,gbConst);
        //++++end soth panel
        AutoCode();
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       String cmd=e.getActionCommand();
        if(cmd.equals("SUBMIT")){
            
        } else if (cmd.equals("SEARCH")) {
            search();
        }else if (cmd.equals("ADD")) {
            new AddFood();
        }else if (cmd.equals("REMOVE")) {
           
        }
    }
    public static void main(String args[]){
        new Order().setVisible(true);
    }
    //++++++++back end logic
    
    //+++auto code
    private void AutoCode(){
        try{
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
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //+++++search customer
    private void search() {
            try {
            String sql = "select *from customer where code = '" + Integer.parseInt(txtCustCode.getText()) + "'";
            DbConnect conn = new DbConnect();
            ResultSet rs = conn.s.executeQuery(sql);
            if (rs.next()) {
                lblCustNamePrint.setText(rs.getString("fname")+" "+rs.getString("mname")+" "+rs.getString("lname"));
                    
            } else {
                JOptionPane.showMessageDialog(this, "Not Found!!");
            }
            } catch (Exception e) {
            e.printStackTrace();
        }
        }
    
    
    
    
}
