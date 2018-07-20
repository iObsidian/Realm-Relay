package oryx2D;

import oryx2D.util.hint.HintTextField;
import oryx2D.util.hint.PasswordHintTextField;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
	
		ArrayList<Image> icons = new ArrayList<Image>();
		icons.add(Toolkit.getDefaultToolkit().getImage(LoaderUI.class.getResource("/oryx2D/icons/icon128.png")));
		icons.add(Toolkit.getDefaultToolkit().getImage(LoaderUI.class.getResource("/oryx2D/icons/icon16.png")));
		icons.add(Toolkit.getDefaultToolkit().getImage(LoaderUI.class.getResource("/oryx2D/icons/icon32.png")));
		frame.setIconImages(icons);
		frame.setResizable(false);
		frame.setBackground(Color.black);
		frame.setTitle("Oryx2D Launcher");
		frame.setLocationRelativeTo(null);

		//frame.setResizable(false);
		frame.setBounds(100, 100, 800, 550);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(LoaderUI.class.getResource("/oryx2D/loader/oryx2D.png")));
		panel.add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		panel_1.add(panel_2);
		

		emailField = new HintTextField("Email");
		panel_2.add(emailField);
		emailField.setColumns(20);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.BLACK);
		panel_1.add(panel_3);
		
		passwordField = new PasswordHintTextField("Password");
		panel_3.add(passwordField);
		passwordField.setColumns(20);
		
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
		btnJoin.addActionListener(e -> {
			Game.main(null);
			frame.dispose();
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
