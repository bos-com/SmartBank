import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class UI_PRO {

    private static JFrame roleFrame = null;
    private static JFrame customerFrame = null;
    private static JFrame adminFrame = null;

    public static void main(String[] args) {
        // demo users (optional)
        Bank.register("kosea", "000123");
        Bank.register("Mac", "pass1234");

        SwingUtilities.invokeLater(() -> showRoleSelection());
    }


 public static void showRoleSelection() {
    if (roleFrame != null) roleFrame.dispose();

    roleFrame = new   JFrame(  " SmartBankn java final exam — Role Selection");
    roleFrame.setSize(1000, 660);
    roleFrame.setLocationRelativeTo(null);
    roleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    roleFrame.setResizable(false);

    JPanel root = new JPanel(new BorderLayout());
    root.setBackground(new Color(245, 248, 250));

    JPanel card = new JPanel();
    card.setBackground(new Color(250, 250, 250));
    card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230)),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
    ));
    card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS)); // vertical layout
    root.add(card, BorderLayout.CENTER);


    // Title 
    JLabel title = new JLabel("WELCOME TO SMARTBANK", SwingConstants.CENTER);
    title.setFont(new Font("Segoe UI", Font.BOLD, 28));
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    card.add(title);
    card.add(Box.createRigidArea(new Dimension(0, 30)));

    // Logo 
    try {
        URL url = new URL("https://mir-s3-cdn-cf.behance.net/project_modules/hd/18c1b374920459.5c3e3376dc522.jpg");
        ImageIcon raw = new ImageIcon(url);
        Image scaled = raw.getImage().getScaledInstance(220, 120, Image.SCALE_SMOOTH);
        ImageIcon logo = new ImageIcon(scaled);

        JLabel logoLabel = new JLabel(logo);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(logoLabel);
        card.add(Box.createRigidArea(new Dimension(0, 20))); // spacing
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    //  Buttons panel
    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));
    buttonsPanel.setBackground(new Color(250, 250, 250));

    JButton customerBtn = new JButton("Customer");
    styleBigTile(customerBtn, new Color(240, 248, 255));
    customerBtn.setPreferredSize(new Dimension(300, 140));
    buttonsPanel.add(customerBtn);

    JButton adminBtn = new JButton("Admin");
    styleBigTile(adminBtn, new Color(255, 245, 240));
    adminBtn.setPreferredSize(new Dimension(300, 140));
    buttonsPanel.add(adminBtn);

    card.add(buttonsPanel);
    card.add(Box.createRigidArea(new Dimension(0, 30)));

    JLabel hint = new JLabel("<html><div style='text-align:center'LWANO ABDOUL KARIM -- 23/BIT/BU/R/0017 — Click role to continue<br>Admin default credentials: <b> Admin / password: 1234567890 </b></div></html>", SwingConstants.CENTER);
    hint.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    hint.setAlignmentX(Component.CENTER_ALIGNMENT);
    card.add(hint);

    // Button Actions 
    customerBtn.addActionListener(e -> {
        roleFrame.dispose();
        LoginScreen ls = new LoginScreen(false);
        ls.setVisible(true);
    });

    adminBtn.addActionListener(e -> {
        roleFrame.dispose();
        LoginScreen ls = new LoginScreen(true);
        ls.setVisible(true);
    });

    roleFrame.add(root);
    roleFrame.setVisible(true);
}


    private static void styleBigTile(JButton b, Color bg) {
        b.setFont(new Font("Segoe UI", Font.BOLD, 20));
        b.setBackground(bg);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
    }

    //  CUSTOMER DASHBOARD
    public static void showCustomerDashboard(User user) {
        if (customerFrame != null) customerFrame.dispose();

        customerFrame = new JFrame("SmartBank — " + user.username);
        customerFrame.setSize(1100, 740);
        customerFrame.setLocationRelativeTo(null);
        customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerFrame.setResizable(false);

        JPanel root = new JPanel(null);
        root.setBackground(new Color(235, 245, 250));

        // Sidebar (white card) with avatar
        JPanel side = new JPanel(null);
        side.setBackground(Color.WHITE);
        side.setBounds(20, 20, 320, 680);
        side.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
        root.add(side);

        // Avatar area
        AvatarLabel avatarLabel = new AvatarLabel(user, 140);
        avatarLabel.setBounds(90, 30, 140, 140);
        side.add(avatarLabel);

        JLabel name = new JLabel(user.username, SwingConstants.CENTER);
        name.setFont(new Font("Segoe UI", Font.BOLD, 20));
        name.setBounds(20, 190, 280, 28);
        side.add(name);

        JLabel bal = new JLabel("Balance: $" + String.format("%.2f", user.balance), SwingConstants.CENTER);
        bal.setFont(new Font("Segoe UI", Font.BOLD, 18));
        bal.setForeground(new Color(0, 120, 0));
        bal.setBounds(20, 230, 280, 26);
        side.add(bal);

        JLabel debt = new JLabel("Debt: $" + calcDebt(user), SwingConstants.CENTER);
        debt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        debt.setForeground(new Color(180, 0, 0));
        debt.setBounds(20, 260, 280, 22);
        side.add(debt);

        JLabel intt = new JLabel("Interest: $" + calcInterest(user), SwingConstants.CENTER);
        intt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        intt.setForeground(new Color(200, 110, 0));
        intt.setBounds(20, 290, 280, 22);
        side.add(intt);

        JButton upload = new JButton("Upload Picture");
        upload.setBounds(40, 330, 240, 40);
        upload.setBackground(new Color(0, 122, 255));
        upload.setForeground(Color.WHITE);
        side.add(upload);

        JButton remove = new JButton("Remove Picture");
        remove.setBounds(40, 380, 240, 40);
        remove.setBackground(new Color(220, 80, 80));
        remove.setForeground(Color.WHITE);
        side.add(remove);

        JButton logout = new JButton("Logout");
        logout.setBounds(40, 620, 240, 44);
        logout.setBackground(new Color(200, 200, 200));
        side.add(logout);

        // Main area (transactions)
        JPanel main = new JPanel(null);
        main.setBackground(Color.WHITE);
        main.setBounds(360, 20, 720, 680);
        main.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
        root.add(main);

        JLabel txh = new JLabel("Transactions & Notifications");
        txh.setFont(new Font("Segoe UI", Font.BOLD, 18));
        txh.setBounds(20, 14, 400, 30);
        main.add(txh);

        JTextArea txArea = new JTextArea();
        txArea.setEditable(false);
        txArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane sp = new JScrollPane(txArea);
        sp.setBounds(20, 50, 680, 520);
        main.add(sp);

        // initial transactions populate
        StringBuilder sb = new StringBuilder();
        for (String tx : user.transactions) sb.append(tx).append("\n");
        txArea.setText(sb.toString());

        // actions on sidebar below stats
        JButton depositBtn = new JButton("Deposit");
        depositBtn.setBounds(40, 430, 240, 44);
        depositBtn.setBackground(new Color(0, 150, 255));
        depositBtn.setForeground(Color.WHITE);
        side.add(depositBtn);

        JButton withdrawBtn = new JButton("Request Withdrawal");
        withdrawBtn.setBounds(40, 490, 240, 44);
        withdrawBtn.setBackground(new Color(255, 140, 0));
        withdrawBtn.setForeground(Color.WHITE);
        side.add(withdrawBtn);

        JButton loanBtn = new JButton("Request Loan");
        loanBtn.setBounds(40, 550, 240, 44);
        loanBtn.setBackground(new Color(0, 180, 120));
        loanBtn.setForeground(Color.WHITE);
        side.add(loanBtn);

        // upload action
        upload.addActionListener(ev -> {
            JFileChooser chooser = new JFileChooser();
            int res = chooser.showOpenDialog(customerFrame);
            if (res == JFileChooser.APPROVE_OPTION) {
                try {
                    File src = chooser.getSelectedFile();
                    // ensure folder exists
                    File folder = new File("profile_pictures");
                    if (!folder.exists()) folder.mkdirs();
                    File dest = new File(folder, user.username + ".png");
                    BufferedImage img = ImageIO.read(src);
                    // write as PNG scaled to 400x400 max
                    BufferedImage scaled = scaleToSquare(img, 400);
                    ImageIO.write(scaled, "png", dest);
                    user.avatarPath = dest.getPath();
                    avatarLabel.setAvatarFromPath(user.avatarPath);
                    JOptionPane.showMessageDialog(customerFrame, "Profile picture uploaded.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(customerFrame, "Failed to load image.");
                }
            }
        });

        // remove action
        remove.addActionListener(ev -> {
            try {
                if (user.avatarPath != null) {
                    File f = new File(user.avatarPath);
                    if (f.exists()) f.delete();
                    user.avatarPath = null;
                    avatarLabel.clearAvatar();
                    JOptionPane.showMessageDialog(customerFrame, "Profile picture removed.");
                } else {
                    JOptionPane.showMessageDialog(customerFrame, "No picture to remove.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(customerFrame, "Cannot remove picture.");
            }
        });

        // deposit
        depositBtn.addActionListener(e -> {
            String s = JOptionPane.showInputDialog(customerFrame, "Amount to deposit:");
            try {
                double amt = Double.parseDouble(s);
                if (amt <= 0) throw new Exception();
                SmartBank_BankingEngine.deposit(user, amt);
                user.transactions.add("Deposit: $" + String.format("%.2f", amt));
                txArea.append("Deposit: $" + String.format("%.2f", amt) + "\n");
                bal.setText("Balance: $" + String.format("%.2f", user.balance));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(customerFrame, "Invalid amount.");
            }
        });

        // withdrawal request
        withdrawBtn.addActionListener(e -> {
            String s = JOptionPane.showInputDialog(customerFrame, "Amount to withdraw:");
            try {
                double amt = Double.parseDouble(s);
                if (amt <= 0) throw new Exception();
                user.transactions.add("Withdrawal requested: $" + String.format("%.2f", amt));
                txArea.append("Withdrawal requested: $" + String.format("%.2f", amt) + "\n");
                JOptionPane.showMessageDialog(customerFrame, "Withdrawal request sent to admin.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(customerFrame, "Invalid amount.");
            }
        });

        // loan request
        loanBtn.addActionListener(e -> {
            String s = JOptionPane.showInputDialog(customerFrame, "Loan amount:");
            try {
                double amt = Double.parseDouble(s);
                if (amt <= 0) throw new Exception();
                SmartBank_BankingEngine.submitLoan(user, amt);
                user.transactions.add("Loan requested: $" + String.format("%.2f", amt));
                txArea.append("Loan requested: $" + String.format("%.2f", amt) + "\n");
                JOptionPane.showMessageDialog(customerFrame, "Loan request submitted. Admin will review.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(customerFrame, "Invalid amount.");
            }
        });

        logout.addActionListener(e -> {
            customerFrame.dispose();
            showRoleSelection();
        });

        customerFrame.add(root);
        customerFrame.setVisible(true);
    }

    //  ADMIN DASHBOARD (unchanged, calls AdminModule)
    public static void showAdminDashboard() {
        if (adminFrame != null) adminFrame.dispose();

        adminFrame = new JFrame("SmartBank — Admin Panel");
        adminFrame.setSize(1200, 900);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setResizable(false);

        JPanel root = new JPanel(null);
        root.setBackground(new Color(235, 245, 250));

        JPanel left = new JPanel(null);
        left.setBackground(Color.WHITE);
        left.setBounds(20, 20, 320, 820);
        left.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
        root.add(left);

        JLabel h = new JLabel("ADMIN", SwingConstants.CENTER);
        h.setFont(new Font("Segoe UI", Font.BOLD, 22));
        h.setBounds(10, 10, 300, 34);
        left.add(h);

        JButton viewUsers = new JButton("View All Users");
        viewUsers.setBounds(20, 70, 280, 46);
        left.add(viewUsers);

        JButton approveLoans = new JButton("Approve Loans");
        approveLoans.setBounds(20, 140, 280, 46);
        left.add(approveLoans);

        JButton approveWithdrawals = new JButton("Approve Withdrawals");
        approveWithdrawals.setBounds(20, 210, 280, 46);
        left.add(approveWithdrawals);

        JButton broadcast = new JButton("Broadcast Notification");
        broadcast.setBounds(20, 280, 280, 46);
        left.add(broadcast);

        JButton viewAssets = new JButton("View Bank Assets");
        viewAssets.setBounds(20, 350, 280, 46);
        left.add(viewAssets);

        JButton back = new JButton("Logout");
        back.setBounds(20, 760, 280, 46);
        left.add(back);

        JTextArea output = new JTextArea();
        output.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(output);
        scroll.setBounds(360, 20, 800, 820);
        root.add(scroll);

        viewUsers.addActionListener(e -> output.setText(AdminModule.usersReport()));
        approveLoans.addActionListener(e -> {
            AdminModule.approveAllLoans();
            output.append("All pending loans approved.\n");
        });
        approveWithdrawals.addActionListener(e -> output.append(AdminModule.processWithdrawals()));
        broadcast.addActionListener(e -> {
            String msg = JOptionPane.showInputDialog(adminFrame, "Message to all users:");
            if (msg != null && !msg.trim().isEmpty()) {
                AdminModule.broadcast(msg.trim());
                output.append("Broadcast sent: " + msg + "\n");
            }
        });
        viewAssets.addActionListener(e -> {
            output.append("Collected interest: $" + Bank.collectedInterest + "\n");
            output.append("Total bank assets: $" + Bank.totalBankAsset() + "\n");
        });
        back.addActionListener(e -> {
            adminFrame.dispose();
            showRoleSelection();
        });

        adminFrame.add(root);
        adminFrame.setVisible(true);
    }


    // Helper: circular cropping + scaling
    private static BufferedImage scaleToSquare(BufferedImage src, int size) {
        int w = src.getWidth();
        int h = src.getHeight();
        int max = Math.max(w, h);
        BufferedImage square = new BufferedImage(max, max, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = square.createGraphics();
        g.setColor(new Color(0,0,0,0));
        g.fillRect(0,0,max,max);
        g.drawImage(src, (max - w)/2, (max - h)/2, null);
        g.dispose();
        BufferedImage scaled = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = scaled.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(square, 0, 0, size, size, null);
        g2.dispose();
        return scaled;
    }

    private static BufferedImage makeCircular(BufferedImage src) {
        int size = Math.min(src.getWidth(), src.getHeight());
        BufferedImage mask = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = mask.createGraphics();
        g2.setClip(new Ellipse2D.Float(0,0,size,size));
        g2.drawImage(src, 0, 0, size, size, null);
        g2.dispose();
        return mask;
    }

    // AvatarLabel: shows circular avatar, hover scale, click preview
    static class AvatarLabel extends JComponent {
        private BufferedImage avatar = null;
        private int diameter;
        private User user;
        private double scale = 1.0;

        public AvatarLabel(User user, int diameter) {
            this.user = user;
            this.diameter = diameter;
            setToolTipText("Click to preview");
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            loadAvatar();
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    scale = 1.06; repaint();
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    scale = 1.0; repaint();
                }
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (avatar != null) {
                        // show modal with full-size preview
                        JDialog d = new JDialog(SwingUtilities.getWindowAncestor(AvatarLabel.this), "Profile picture", Dialog.ModalityType.APPLICATION_MODAL);
                        d.setSize(500, 500);
                        d.setLocationRelativeTo(AvatarLabel.this);
                        JPanel p = new JPanel(new BorderLayout());
                        JLabel img = new JLabel(new ImageIcon(avatar.getScaledInstance(480, 480, Image.SCALE_SMOOTH)));
                        p.add(img, BorderLayout.CENTER);
                        d.add(p);
                        d.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(AvatarLabel.this), "No profile picture. Use Upload Picture.");
                    }
                }
            });
            setPreferredSize(new Dimension(diameter, diameter));
        }

        public void setAvatarFromPath(String path) {
            try {
                File f = new File(path);
                if (f.exists()) {
                    BufferedImage src = ImageIO.read(f);
                    BufferedImage scaled = scaleToSquare(src, diameter);
                    avatar = makeCircular(scaled);
                    repaint();
                }
            } catch (Exception ex) { ex.printStackTrace(); }
        }

        public void clearAvatar() {
            avatar = null;
            repaint();
        }

        private void loadAvatar() {
            try {
                if (user.avatarPath != null) {
                    File f = new File(user.avatarPath);
                    if (f.exists()) {
                        BufferedImage src = ImageIO.read(f);
                        BufferedImage scaled = scaleToSquare(src, diameter);
                        avatar = makeCircular(scaled);
                    }
                }
            } catch (Exception ex) { ex.printStackTrace(); }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            int drawSize = (int) (diameter * scale);
            int x = (getWidth() - drawSize) / 2;
            int y = (getHeight() - drawSize) / 2;
            // shadow/glow
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (avatar != null) {
                g2.drawImage(avatar, x, y, drawSize, drawSize, null);
                // border
                g2.setStroke(new BasicStroke(3f));
                g2.setColor(new Color(255,255,255,160));
                g2.drawOval(x, y, drawSize, drawSize);
            } else {
                // placeholder circle
                g2.setColor(new Color(230,230,230));
                g2.fillOval(x, y, drawSize, drawSize);
                g2.setColor(new Color(200,200,200));
                g2.drawOval(x, y, drawSize, drawSize);
                // initials
                String initials = user.username.length() > 0 ? user.username.substring(0,1).toUpperCase() : "?";
                g2.setFont(new Font("Segoe UI", Font.BOLD, drawSize/2));
                FontMetrics fm = g2.getFontMetrics();
                int tx = x + (drawSize - fm.stringWidth(initials)) / 2;
                int ty = y + (drawSize - fm.getHeight()) / 2 + fm.getAscent();
                g2.setColor(new Color(120,120,120));
                g2.drawString(initials, tx, ty);
            }
            g2.dispose();
        }
    }

    // helper debt/interest calculators
    private static String calcDebt(User u) {
        double debt = 0;
        for (Loan l : u.loans) if (l.approved) debt += l.amount;
        return String.format("%.2f", debt);
    }

    private static String calcInterest(User u) {
        double i = 0;
        for (Loan l : u.loans) if (l.approved) i += l.interest;
        return String.format("%.2f", i);
    }
}
