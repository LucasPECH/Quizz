import javax.swing.JFrame;
import javax.swing.JButton;
import java.util.HashMap;

public class ModerationWindow {
	private JFrame frame;

	 /**
     * Create the application.
     */
	public ModerationWindow(Themes theme, HashMap<String,ListeQuestions> questions) {
		initialize(theme, questions);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Themes theme, HashMap<String, ListeQuestions> questions) {
		frame = new JFrame();
		frame.setBounds(100, 100, 553, 406);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		fillwindow(theme, questions);

	}
	public void fillwindow(Themes theme, HashMap<String, ListeQuestions> questions)
	{
		JButton btnNewButton = new JButton("AFFICHER THEMES");
		btnNewButton.setBounds(0, 72, 537, 73);
		btnNewButton.addActionListener(e -> {
			new AfficherThemeWindow(theme, questions);
			frame.dispose();
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnNewButton);
		JButton btnNewButton_1 = new JButton("AFFICHER QUESTION PAR THEME");
		btnNewButton_1.setBounds(0, 146, 537, 73);
		btnNewButton_1.addActionListener(e -> {
			new AfficherQuestionWindow(questions, theme);
			frame.setVisible(false);
		});
		frame.getContentPane().add(btnNewButton_1);
		JButton btnNewButton_2 = new JButton("AJOUTER QUESTION");
		btnNewButton_2.setBounds(0, 220, 537, 73);
		btnNewButton_2.addActionListener(e -> {
			new AjouterQuestionWindow(questions, theme);
			frame.dispose();
		});
		frame.getContentPane().add(btnNewButton_2);
		JButton btnNewButton_3 = new JButton("SUPPRIMER QUESTION");
		btnNewButton_3.setBounds(0, 294, 537, 73);
		btnNewButton_3.addActionListener(e -> {
			new SupprimerQuestionWindow(questions, theme);
			frame.dispose();
		});
		frame.getContentPane().add(btnNewButton_3);
		JButton btnNewButton_4 = new JButton("PRECEDENT");
		btnNewButton_4.setBounds(0, 0, 109, 37);
		btnNewButton_4.addActionListener(e -> {
			new MenuWindow();
			frame.dispose();
		});
		frame.getContentPane().add(btnNewButton_4);
		frame.setVisible(true);
	}
}