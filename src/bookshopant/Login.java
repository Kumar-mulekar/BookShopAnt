package bookshopant;

import java.sql.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    JPanel leftPanel, rightPanel;
    JTextField userId;
    JPasswordField pass;
    JLabel lblUserId, lblpass;
    JButton btnLogin;

    Login() {
        guiLogin();
    }

    private void guiLogin() {
        setTitle("LOG IN");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        leftPanel = new JPanel();
        rightPanel = new JPanel(new GridBagLayout());
        add(leftPanel);
        add(rightPanel);
        setLayout(new GridLayout());
        //+++++++++++left panel
        ImageIcon loginImage = new ImageIcon(ClassLoader.getSystemResource("pic/log1.png"));
        Image image = loginImage.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon loginImage1 = new ImageIcon(image);
        leftPanel.add(new JLabel(loginImage1));

        //++++++++++++++++right panel
        Dimension d = new Dimension(150, 30);
        lblUserId = new JLabel("USER ID");
        lblUserId.setPreferredSize(d);
        userId = new JTextField();
        userId.setPreferredSize(d);
        lblpass = new JLabel("PASSWORD");
        lblpass.setPreferredSize(d);
        pass = new JPasswordField();
        pass.setEchoChar('*');
        pass.setPreferredSize(d);

        btnLogin = new JButton("Log In");
        btnLogin.setPreferredSize(d);
        btnLogin.setBackground(Color.black);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.addActionListener(this);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        c.gridx = 0;
        c.gridy = 1;
        rightPanel.add(lblUserId, c);
        c.gridx = 1;
        c.gridy = 1;
        rightPanel.add(userId, c);

        c.gridx = 0;
        c.gridy = 2;
        rightPanel.add(lblpass, c);
        c.gridx = 1;
        c.gridy = 2;
        rightPanel.add(pass, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        rightPanel.add(btnLogin, c);

        rightPanel.setBackground(Color.white);
        leftPanel.setBackground(Color.white);
        setBackground(Color.white);
        setLocation(400, 200);
        setVisible(true);
    }

    public static void main(String args[]) {
        new Login().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            DbConnect dbConnect = new DbConnect();
            String strUserName = userId.getText();
            char[] charPass = pass.getPassword();
            String strPass = new String(charPass);
            
            String sqlQry = "select *from login where userName='" + strUserName + "' and pass='" + strPass + "'";
            try {
                ResultSet resultSetLogin = dbConnect.s.executeQuery(sqlQry);
                if (resultSetLogin.next()) {
                    new DashBoard().setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Invalid Details");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
