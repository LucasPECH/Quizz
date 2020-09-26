import javax.swing.JFrame;
import java.awt.GridLayout;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;

public class AfficherThemeWindow {

	private JFrame frame;

    /**
     * Create the application.
     */
	public AfficherThemeWindow(Themes theme, HashMap<String, ListeQuestions> questions) {
		initialize(theme, questions);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Themes objettheme, HashMap<String, ListeQuestions> questions) {
		frame = new JFrame();
		frame.setBounds(100, 100, 558, 506);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(11, 1, 0, 0));
		frame.setLocationRelativeTo(null);
		fillwindow(objettheme, questions);
	}

	void fillwindow(Themes objettheme, HashMap<String, ListeQuestions> questions)
	{
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		JButton btnNewButton = new JButton("PRECEDENT");
		btnNewButton.setBounds(0, 0, 112, 31);
		btnNewButton.addActionListener(e -> {
			new ModerationWindow(objettheme, questions);
			frame.dispose();
		});
		panel.add(btnNewButton);
		JLabel lblNewLabel_1 = new JLabel("Voici les 10 themes du jeu : ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(166, 17, 228, 14);
		panel.add(lblNewLabel_1);
		JLabel label = new JLabel(objettheme.selectTheme());
		label.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label);
		JLabel label_1 = new JLabel(objettheme.selectTheme());
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label_1);
		JLabel label_2 = new JLabel(objettheme.selectTheme());
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label_2);
		JLabel label_3 = new JLabel(objettheme.selectTheme());
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label_3);
		JLabel label_4 = new JLabel(objettheme.selectTheme());
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label_4);
		JLabel label_5 = new JLabel(objettheme.selectTheme());
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label_5);
		JLabel label_6 = new JLabel(objettheme.selectTheme());
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label_6);
		JLabel label_7 = new JLabel(objettheme.selectTheme());
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label_7);
		JLabel lblNewLabel = new JLabel(objettheme.selectTheme());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		JLabel label_8 = new JLabel(objettheme.selectTheme());
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label_8);
		frame.setVisible(true);
		objettheme.setto0();
	}
}