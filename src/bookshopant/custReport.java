
package bookshopant;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import net.proteanit.sql.DbUtils;
public class custReport extends JFrame implements ActionListener{
    JLabel lblHead;
    JTable tblData;
    JPanel pnlMain;
    String sql;
    DefaultTableModel dataTblModel;
    public custReport(int i,String Head){
        guiCustReport();
        lblHead.setText(Head);
        switch (i) {
            case 1:
                sql="select code as 'Code',fname as 'First Name',mname as 'Middle Name',lname as 'Last Name',addr as 'Address',contact as 'Contact',email as 'E-mail' from customer";
                break;
            case 2:
                sql="select code as 'Code',type as 'Type',foodname as 'Name',foodprice as 'Price' from food";
                break;
            case 3:
                sql="select code as 'Code',fname as 'First Name',lname as 'Last Name',paydate as 'Last pay date',due as 'Due' from customer where now()>=adddate(customer.paydate,interval 30 day);";
            default:
                break;
        }
        display(sql);
        
    }
    
    private void guiCustReport(){
        setSize(500,500);
        pnlMain=new JPanel();
        Font fontForLbl = new Font("Serif", Font.BOLD, 20);
        lblHead = new JLabel("Customer Details");
        lblHead.setFont(fontForLbl);

        tblData = new JTable();
        dataTblModel=(DefaultTableModel) tblData.getModel();
        dataTblModel.addColumn("Code");
        dataTblModel.addColumn("Name");
        dataTblModel.addColumn("QTY");
        dataTblModel.addColumn("AMT");
        tblData.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        JScrollPane sp2 = new JScrollPane(tblData);
        
        GridBagLayout gbLayout=new GridBagLayout();
        pnlMain.setLayout(gbLayout);
        GridBagConstraints gbConst=new GridBagConstraints();
        gbConst.insets=new Insets(10,10,10,10);
        gbConst.fill=GridBagConstraints.BOTH;
        gbConst.weightx=1;
        gbConst.weighty=1;
        gbConst.gridheight=1;
        gbConst.gridx=0;gbConst.gridy=0;
        pnlMain.add(lblHead,gbConst);
        gbConst.gridy=1;
        gbConst.weighty=10;
        gbConst.gridheight=10;
        pnlMain.add(sp2,gbConst);
        add(pnlMain);
    }
    
    public static void main(String args[]){
        new custReport(1,"Customer Details").setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
    }
    
    ///+++++++++++++back end
    void display(String qr){
        try{
            DbConnect c=new DbConnect();
            ResultSet rs=c.s.executeQuery(qr);
            tblData.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
