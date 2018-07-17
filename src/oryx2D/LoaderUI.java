package oryx2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoaderUI {

	private JFrame frame;
	private JTextField emailField;
	private JTextField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoaderUI window = new LoaderUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoaderUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.black);
		frame.setTitle("Oryx2D Launcher");
		//frame.setResizable(false);
		frame.setBounds(100, 100, 800, 450);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(LoaderUI.class.getResource("/oryx2D/loader/oryx2D.png")));
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		panel_1.add(panel_2);
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setForeground(Color.WHITE);
		panel_2.add(lblEmail);
		
		emailField = new JTextField();
		panel_2.add(emailField);
		emailField.setColumns(20);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.BLACK);
		panel_1.add(panel_3);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setForeground(Color.WHITE);
		panel_3.add(lblPassword);
		
		passwordField = new JPasswordField(10);
		panel_3.add(passwordField);
		passwordField.setColumns(18);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.BLACK);
		panel_1.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.BLACK);
		panel_1.add(panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.BLACK);
		panel_1.add(panel_6);
		
		JButton btnJoin = new JButton("        Connect        ");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Bas les couilles frero'");
			}
		});
		btnJoin.setForeground(Color.BLACK);
		btnJoin.setBackground(Color.WHITE);
		panel_6.add(btnJoin);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.BLACK);
		panel_1.add(panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.BLACK);
		panel_1.add(panel_8);
	}

}
