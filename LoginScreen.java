import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {

    public LoginScreen(boolean isAdmin) {
        setTitle(isAdmin ? "SmartBank — Admin Login" : "SmartBank — Customer Login");
        setSize(800, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel p = new JPanel(null);
        p.setBackground(new Color(245, 248, 250));

        JPanel card = new JPanel(null);
        card.setBackground(Color.WHITE);
        card.setBounds(60, 30, 680, 440);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230,230,230)),
                BorderFactory.createEmptyBorder(20,20,20,20)
        ));
        p.add(card);

        JLabel header = new JLabel(isAdmin ? "Admin Login" : "Customer Login");
        header.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.setBounds(20, 10, 400, 40);
        card.add(header);

        JLabel uL = new JLabel("Username:");
        uL.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        uL.setBounds(20, 70, 120, 30);
        card.add(uL);

        JTextField userF = new JTextField();
        userF.setBounds(150, 70, 480, 34);
        card.add(userF);

        JLabel pL = new JLabel("Password:");
        pL.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        pL.setBounds(20, 120, 120, 30);
        card.add(pL);

        JPasswordField passF = new JPasswordField();
        passF.setBounds(150, 120, 480, 34);
        card.add(passF);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(150, 200, 200, 44);
        loginBtn.setBackground(new Color(0, 122, 255));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        card.add(loginBtn);

        JButton regLink = new JButton("Register (New Customer)");
        regLink.setBounds(370, 200, 260, 44);
        regLink.setBackground(new Color(220,220,220));
        regLink.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        card.add(regLink);

        JButton back = new JButton("Back");
        back.setBounds(20, 380, 120, 34);
        back.setBackground(new Color(200,200,200));
        card.add(back);

        loginBtn.addActionListener(e -> {
            String u = userF.getText().trim();
            String key = new String(passF.getPassword());
            if (isAdmin) {
                if (u.equals("admin") && key.equals("1234567890")) {
                    dispose();
                    UI_PRO.showAdminDashboard();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid admin credentials.");
                }
            } else {
                User user = Bank.getUser(u);
                if (user == null || !user.password.equals(key)) {
                    JOptionPane.showMessageDialog(this, "Invalid username or password.");
                } else {
                    dispose();
                    UI_PRO.showCustomerDashboard(user);
                }
            }
        });

        regLink.addActionListener(e -> {
            dispose();
            RegistrationScreen rs = new RegistrationScreen();
            rs.setVisible(true);
        });

        back.addActionListener(e -> {
            dispose();
            UI_PRO.showRoleSelection();
        });

        add(p);
    }
}
