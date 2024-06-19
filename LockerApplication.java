import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LockerApplication extends JFrame {
    private StringBuilder enteredPassword = new StringBuilder();
    private String savedPassword = "";
    private JTextField passwordField;
    private JLabel statusLabel;

    public LockerApplication() {

        setTitle("Lock Class");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel keypadPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(new KeypadButtonListener());
            keypadPanel.add(button);
        }
        add(keypadPanel, BorderLayout.NORTH);


        JPanel controlPanel = new JPanel(new FlowLayout()); // Adjusted to FlowLayout
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ClearButtonListener());
        controlPanel.add(clearButton);

        passwordField = new JTextField(10);
        passwordField.setEditable(false);
        controlPanel.add(passwordField);

        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new EnterButtonListener());
        controlPanel.add(enterButton);


        statusLabel = new JLabel("Enter Password", JLabel.CENTER);
        controlPanel.add(statusLabel);

        add(controlPanel, BorderLayout.CENTER);
    }

    private class KeypadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            enteredPassword.append(source.getText());
            passwordField.setText(enteredPassword.toString());
        }
    }

    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            enteredPassword.setLength(0);
            passwordField.setText("");
        }
    }

    private class EnterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (savedPassword.isEmpty()) {

                if (enteredPassword.length() >= 8) {
                    savedPassword = enteredPassword.toString();
                    statusLabel.setText("Password Set");
                } else {
                    statusLabel.setText("Password must be at least 8 characters");
                }
            } else {

                if (enteredPassword.toString().equals(savedPassword)) {
                    statusLabel.setText("Correct Password");
                } else {
                    statusLabel.setText("Incorrect Password");
                }
            }
            enteredPassword.setLength(0);
            passwordField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LockerApplication lockerApplication = new LockerApplication();
            lockerApplication.setVisible(true);
        });
    }
}
