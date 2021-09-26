
package bookshopant;

import static bookshopant.Order.addDays;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class payment extends JFrame implements ActionListener {
    JLabel lblPaymentCode,lblCustCode,lblCustName,lblMsg,lblRemark;
    JTextField txtPaymentCode,txtCustCode,txtRemark;
    JButton btnSearch,btnPay;
    JPanel pnlMain;
    int due;
    public payment(){
        guiPayment();
    }
    
    void guiPayment(){
        setSize(500, 400);
        setTitle("Payment");
        Font fontForLbl = new Font("Serif", Font.BOLD, 20);
        //+++++++++declaration
        pnlMain=new JPanel();
        lblPaymentCode=new JLabel("Payment Code");
        lblPaymentCode.setFont(fontForLbl);
        lblCustCode=new JLabel("Customer Code");
        lblCustCode.setFont(fontForLbl);
        lblCustName=new JLabel("");
        lblCustName.setFont(fontForLbl);
        lblMsg=new JLabel("");
        lblMsg.setFont(fontForLbl);
        lblRemark=new JLabel("Remark");
        lblRemark.setFont(fontForLbl);
        
        txtPaymentCode=new JTextField();
        txtPaymentCode.setFont(fontForLbl);
        txtPaymentCode.setEnabled(false);
        txtCustCode=new JTextField();
        txtCustCode.setFont(fontForLbl);
        txtRemark=new JTextField();
        txtRemark.setFont(fontForLbl);
        
        btnSearch =new JButton("SEARCH");
        btnSearch.setFont(fontForLbl);
        btnSearch.addActionListener(this);
        btnPay =new JButton("PAY");
        btnPay.setFont(fontForLbl);
        btnPay.addActionListener(this);
        //++++end declaration
        
        //++++pnlMain aarangement
        pnlMain.add(lblPaymentCode);
        pnlMain.add(txtPaymentCode);
        pnlMain.add(lblCustCode);
        pnlMain.add(txtCustCode);
        pnlMain.add(lblCustName);
        pnlMain.add(lblMsg);
        pnlMain.add(lblRemark);
        pnlMain.add(txtRemark);
        pnlMain.add(btnSearch);
        pnlMain.add(btnPay);
        pnlMain.setLayout(new GridLayout(5,2,20,20));
        pnlMain.setBorder(new EmptyBorder(50,10,10,10));
        //+++end pnl Main
        add(pnlMain);
        
        AutoCode();
        
        
    }
    
    
    public static void main(String args[]){
        new payment().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       String cmd = e.getActionCommand();
        if (cmd.equals("PAY")) {
            pay();
        } else if (cmd.equals("SEARCH")) {
            search();
        }
    }
    
    ///////++++++++++++++back end login
    //+++++++++auto num
    private void AutoCode()  {
        try{
        DbConnect con = new DbConnect();
        String sql = "select code from payment";
        ResultSet rs = con.s.executeQuery(sql);
        if (!rs.next()) {
            txtPaymentCode.setText("1");
        } else {
            rs.last();
            int no = rs.getInt("code") + 1;
            txtPaymentCode.setText(Integer.toString(no));
        }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    //+++++search customer
    private void search() {
        try {
            String sql = "select *from customer where code = '" + Integer.parseInt(txtCustCode.getText()) + "'";
            DbConnect conn = new DbConnect();
            ResultSet rs = conn.s.executeQuery(sql);
            if (rs.next()) {
                lblCustName.setText(rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("lname"));
                due=rs.getInt("due");
                java.sql.Date date = rs.getDate("paydate");
                java.sql.Date now=new java.sql.Date(new java.util.Date().getTime());
                date=addDays(date,30);
                System.out.println(date +" "+now);
                if(now.after(date)){
                    lblMsg.setForeground(Color.red);
                }else{
                     lblMsg.setForeground(Color.white);
                }
                    
                
                
                lblMsg.setText(" Due : "+due);
            } else {
                JOptionPane.showMessageDialog(this, "Not Found!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //+++++++++++pay
    private void pay(){
        try{
            DbConnect c=new DbConnect();
            java.sql.Date now=new java.sql.Date(new java.util.Date().getTime());
            c.s.executeUpdate("insert into payment values('"+txtPaymentCode.getText()+"','"+txtCustCode.getText()+"','"+txtRemark.getText()+"','"+now+"','"+due+"')");
            c.s.executeUpdate("update customer set paydate ='"+now+"',due='"+0+"' where code ='"+txtCustCode.getText()+"'");
            JOptionPane.showMessageDialog(this,"Payment Successfull");
            clear();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void clear(){
        txtCustCode.setText("");
        lblCustName.setText("");
        lblMsg.setText("");
        txtRemark.setText("");
        AutoCode();
    }
}
