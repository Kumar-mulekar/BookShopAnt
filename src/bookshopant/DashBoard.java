
package bookshopant;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class DashBoard extends JFrame implements ActionListener{
    
    public DashBoard(){
        guiDashBoard();
    }
    
    private void guiDashBoard(){
        setTitle("Canteen");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        
        //+++background image
        ImageIcon loadIco = new ImageIcon(ClassLoader.getSystemResource("pic/dash1.jpg"));
        Image resizeImg = loadIco.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon finalImg=new ImageIcon(resizeImg);
        JLabel lblBackground = new JLabel(finalImg);
        add(lblBackground);
        //+++++
        
        //+++++++++++Menu Bar
        JMenuBar menuBar=new JMenuBar();
            //++++ customer column
            JMenu Customer =new JMenu("Customer");
            JMenuItem custAdd =new JMenuItem("ADD Customer");
            JMenuItem custEdit =new JMenuItem("Edit Customer");
            JMenuItem custDelete=new JMenuItem("Delete Customer");
            Customer.setForeground(Color.BLACK);
            
            custAdd.addActionListener(this);
            custEdit.addActionListener(this);
            custDelete.addActionListener(this);
            //+++++++End customer column
            
            //+++++++Order Column
            JMenu Order =new JMenu("Order");
            JMenuItem newOrder=new JMenuItem("New Order");
            Order.setForeground(Color.BLACK);
            
            
            newOrder.addActionListener(this);
            //+++++++End Order Column
            
            //++++Food Column
            JMenu Food =new JMenu("Food");
            JMenuItem foodAdd=new JMenuItem("ADD Food");
            JMenuItem foodEdit=new JMenuItem("Edit Food");
            JMenuItem foodDelete=new JMenuItem("Delete Food");
            Food.setForeground(Color.BLACK);
                
            foodAdd.addActionListener(this);
            foodEdit.addActionListener(this);
            foodDelete.addActionListener(this);
            //++++End Food Column
            
            //++++Payment Column
            JMenu Payment=new JMenu("Payment");
            JMenuItem pay=new JMenuItem("Pay");
            Payment.setForeground(Color.BLACK);
            
            pay.addActionListener(this);
            //++++End Payment Column
            
            //++++Exit Column
            JMenu Exit=new JMenu("Exit");
            JMenuItem exit=new JMenuItem("Exit");
            Exit.setForeground(Color.BLACK);
            
            exit.addActionListener(this);
            //++++End Exit Column
            
            //---------------------------------------------
            
            
            //++++++Add Menu items to Menu
            Customer.add(custAdd);
            Customer.add(custEdit);
            Customer.add(custDelete);
            
            Order.add(newOrder);
            
            Food.add(foodAdd);
            Food.add(foodEdit);
            Food.add(foodDelete);
            
            Payment.add(pay);
            
            Exit.add(exit);
            //+++++++++++++
            
            //++++Add Menu to Menu Bar
            menuBar.add(Customer);
            menuBar.add(Order);
            menuBar.add(Food);
            menuBar.add(Payment);
            menuBar.add(Exit);
            //+++++
            
            setJMenuBar(menuBar);
            addWindowListener(new WindowAdapter(){
                public void WindowClosing(WindowEvent e){
                    System.exit(0);
                }
            });
        
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String button=e.getActionCommand();
        if(button.equals("Exit")){
            System.exit(0);
        }else if(button.equals("ADD Customer")){
            new AddCustomer(1).setVisible(true);
        }else if(button.equals("Edit Customer")){
            new AddCustomer(2).setVisible(true);
        }else if(button.equals("Delete Customer")){
            new AddCustomer(3).setVisible(true);
        }
      
    }
    
    public static void main(String args[]){
      new DashBoard().setVisible(true);
    }

    
}
