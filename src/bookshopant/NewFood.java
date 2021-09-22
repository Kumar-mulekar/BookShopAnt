
package bookshopant;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

public class NewFood extends JFrame implements ActionListener{
    GridBagConstraints gbConst;
    JLabel lblHead,lblFoodName,lblPrice,lblType,lblFoodCode;
    JTextField txtFoodName,txtPrice,txtFoodCode;
    JComboBox cmbType;
    JButton btnSubmit,btnSearch;
    public NewFood(int form){
        guiNewFood();
        if(form==2){
            editFood();
        }else if(form==3){
            deleteFood();
        }else{
            gbConst.insets = new Insets(0, 20, 100, 20);
            gbConst.weightx = 1;
            gbConst.gridwidth = 2;
            gbConst.anchor = GridBagConstraints.BASELINE_TRAILING;
            gbConst.gridx = 0;
            gbConst.gridy = 5;
            add(btnSubmit, gbConst);
            txtFoodCode.setEditable(false);
            AutoCode();
            
        }
    }
    
    private void editFood(){
        setTitle("Edit Food");
        lblHead.setText("Edit Food");
        btnSubmit.setText("UPDATE");
        //for search nd edit button arangement
        commonEditDelete();
    }
    
    private void deleteFood(){
        setTitle("Delete Food");
        lblHead.setText("Delete Food");
        btnSubmit.setText("DELETE");
        //for search nd edit button arangement
        commonEditDelete();
    }
    
    private void commonEditDelete(){
        gbConst.insets = new Insets(0, 20, 50, 20);
        gbConst.weightx = 1;
        gbConst.gridwidth = 1;
        gbConst.anchor = GridBagConstraints.BASELINE_TRAILING;
        gbConst.gridx = 1;
        gbConst.gridy = 5;
        add(btnSubmit, gbConst);
        gbConst.gridx = 0;
        add(btnSearch, gbConst);
        
    }
    private void guiNewFood(){
        //++++++Component declaration
        Font font=new Font("Serif",Font.BOLD,50);
        
        lblHead =new JLabel("Add Food",SwingConstants.CENTER);
        lblHead.setFont(font);
        
        Font fontForLbl=new Font("Serif",Font.BOLD,20);
        
        lblFoodCode=new JLabel("Food Code");
        lblFoodCode.setFont(fontForLbl);
        txtFoodCode=new JTextField();
        txtFoodCode.setFont(fontForLbl);
        
        lblType=new JLabel("Select Type");
        lblType.setFont(fontForLbl);
        String list[]={"Food","Beverages"};
        cmbType=new JComboBox(list);
        cmbType.setBackground(Color.WHITE);
        cmbType.setFont(fontForLbl);
        
        lblFoodName=new JLabel("Food Name");
        lblFoodName.setFont(fontForLbl);
        txtFoodName=new JTextField();
        txtFoodName.setFont(fontForLbl);
        
        lblPrice=new JLabel("Price");
        lblPrice.setFont(fontForLbl);
        txtPrice=new JTextField();
        txtPrice.setFont(fontForLbl);
        
        btnSubmit = new JButton("SUBMIT");
        btnSubmit.setFont(fontForLbl);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBackground(Color.decode("#ffc36b"));
        btnSubmit.addActionListener(this);

        //search
        btnSearch = new JButton("SEARCH");
        btnSearch.setFont(fontForLbl);
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setBackground(Color.decode("#ffc36b"));
        btnSearch.addActionListener(this);
        
        //++++end declaration
        
        setTitle("Add FOOd");
        setSize(600,500);
        this.getContentPane().setBackground(Color.WHITE);
        GridBagLayout gridBagLayout=new GridBagLayout();
        setLayout(gridBagLayout);
        gbConst=new GridBagConstraints();
        
        //+++gridBag layout
        gbConst.fill=GridBagConstraints.HORIZONTAL;
        gbConst.insets = new Insets(10, 10, 10, 10);
        gbConst.gridx=0;
        gbConst.gridy=0;
        gbConst.gridwidth = 2;
        add(lblHead,gbConst);
        
        gbConst.gridwidth = 1;
        gbConst.weightx=1;
        gbConst.gridy=1;
        add(lblFoodCode,gbConst);
        gbConst.gridx=1;
        add(txtFoodCode,gbConst);
        
        gbConst.gridy=2;
        add(cmbType,gbConst);
        gbConst.gridx=0;
        add(lblType,gbConst);
        
        gbConst.gridy=3;
        add(lblFoodName,gbConst);
        gbConst.gridx=1;
        add(txtFoodName,gbConst);
        
        gbConst.gridy=4;
        add(txtPrice,gbConst);
        gbConst.gridx=0;
        add(lblPrice,gbConst);
        //++++end grid bag layout
        
        setVisible(true);
        
    }
    
    private void AutoCode(){
        try{
        DbConnect con = new DbConnect();
        String sql = "select code from food";
        ResultSet rs = con.s.executeQuery(sql);
        if (!rs.next()) {
            txtFoodCode.setText("1");
        } else {
            rs.last();
            int no = rs.getInt("code") + 1;
            txtFoodCode.setText(Integer.toString(no));
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
     
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd=e.getActionCommand();
        if(cmd.equals("SUBMIT")){
            add();
        } else if (cmd.equals("SEARCH")) {
            search();
        }else if(cmd.equals("UPDATE")){
            
            edit();
        }else if(cmd.equals("DELETE")){
            
            delete();
        }
       
    }
    
    public static void main(String args[]){
        new NewFood(1).setVisible(true);
    }
    
    //+++++backend logic
        //++++add
          private void add(){
            try{
            int code = Integer.parseInt(txtFoodCode.getText());
            String ftype = (String) cmbType.getSelectedItem();
            String fname = txtFoodName.getText();
            int fprice = Integer.parseInt(txtPrice.getText());

            String sql = "insert into food values('" + code + "','" + ftype + "','" + fname + "','" + fprice + "')";
            DbConnect con = new DbConnect();
            con.s.executeUpdate(sql);
            clearText();
            JOptionPane.showMessageDialog(this, "Done!!");
            AutoCode();  
            }catch(Exception e){
                e.printStackTrace();
            }  
          }
          
          private void edit(){
              try{
            String sql="update food set type='"
                        +cmbType.getSelectedItem()+
                        "',foodname='"
                        +txtFoodName.getText()+
                        "',foodprice='"
                        +Integer.parseInt(txtPrice.getText())+
                        "' where code='"
                        +Integer.parseInt(txtFoodCode.getText())+"'";
                        
            
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
            String sql="Delete from food where code ='"+Integer.parseInt(txtFoodCode.getText())+"'";
            conn.s.executeUpdate(sql);
            JOptionPane.showMessageDialog(this,"Deleted!!");
        }catch(Exception e){
            e.printStackTrace();
        }
          }
          
          private void search() {
            try {
            String sql = "select *from food where code = '" + Integer.parseInt(txtFoodCode.getText()) + "'";
            DbConnect conn = new DbConnect();
            ResultSet rs = conn.s.executeQuery(sql);
            if (rs.next()) {
                cmbType.setSelectedItem(rs.getString("type"));
                txtFoodName.setText(rs.getString("foodname"));
                txtPrice.setText(rs.getString("foodprice"));
                
            } else {
                JOptionPane.showMessageDialog(this, "Not Found!!");
            }
            } catch (Exception e) {
            e.printStackTrace();
        }
        }
          private void clearText(){
            cmbType.setSelectedItem(0);
            txtFoodName.setText("");
            txtPrice.setText(""); 
          }
        //+++++end add
    //++++++++

    
    
}
