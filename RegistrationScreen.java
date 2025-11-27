import javax.swing.*;
import java.awt.*;

public class RegistrationScreen extends JFrame {

    public RegistrationScreen() {
        setTitle("SmartBank — Register");
        setSize(800, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel p = new JPanel(null);
        p.setBackground(new Color(245, 248, 250));

        // Neomorphic card
        JPanel card = new JPanel(null);
        card.setBackground(Color.WHITE);
        card.setBounds(60, 30, 680, 440);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230,230,230)),
                BorderFactory.createEmptyBorder(20,20,20,20)
        ));
        p.add(card);

        JLabel header = new JLabel("Create Account");
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

        JButton regBtn = new JButton("Register");
        regBtn.setBounds(150, 200, 200, 44);
        regBtn.setBackground(new Color(0, 122, 255));
        regBtn.setForeground(Color.WHITE);
        regBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        card.add(regBtn);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(370, 200, 200, 44);
        cancelBtn.setBackground(new Color(200, 200, 200));
        cancelBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        card.add(cancelBtn);

        regBtn.addActionListener(e -> {
            String u = userF.getText().trim();
            String key = new String(passF.getPassword());
            if (u.isEmpty() || key.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }
            if (!SmartBank_Utils.isValidUsername(u)) {
                JOptionPane.showMessageDialog(this, "Username must be 3-20 alphanumeric characters.");
                return;
            }
            if (!SmartBank_Utils.isValidPassword(key)) {
                JOptionPane.showMessageDialog(this, "Password must be at least 6 characters.");
                return;
            }
            boolean ok = Bank.register(u, key);
            if (!ok) {
                JOptionPane.showMessageDialog(this, "Username already exists.");
            } else {
                JOptionPane.showMessageDialog(this, "Account created. You can now log in.");
                dispose();
                // open login screen
                LoginScreen ls = new LoginScreen(false);
                ls.setVisible(true);
            }
        });

        cancelBtn.addActionListener(e -> {
            dispose();
            // go back to the UI main (role selection)
            UI_PRO.showRoleSelection();
        });

        add(p);
    }
}
