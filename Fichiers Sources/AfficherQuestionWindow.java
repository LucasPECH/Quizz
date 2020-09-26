import javax.swing.*;
import java.util.HashMap;

public class AfficherQuestionWindow {
	private JFrame frame;

	/**
     * Create the application.
     */
	public AfficherQuestionWindow(HashMap<String, ListeQuestions> questions, Themes them) {
		initialize(questions, them);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(HashMap<String, ListeQuestions> questions, Themes them) {
		frame = new JFrame();
		frame.setBounds(100, 100, 566, 474);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		fillwindow(questions, them);
	}
	public void fillwindow(HashMap<String, ListeQuestions> questions, Themes them)
	{
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		JButton btnNewButton = new JButton("PRECEDENT");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 0, SpringLayout.WEST, frame.getContentPane());
		btnNewButton.addActionListener(e -> {
			new ModerationWindow(them, questions);
			frame.dispose();
		});
		frame.getContentPane().add(btnNewButton);
		JLabel lblNewLabel = new JLabel("Choisissez un theme et valider pour afficher les questions : ");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 59, SpringLayout.EAST, btnNewButton);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, 0, SpringLayout.SOUTH, btnNewButton);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		JTextArea textArea = new JTextArea(20,20);
		JScrollPane scroll = new JScrollPane(textArea);
		springLayout.putConstraint(SpringLayout.NORTH, scroll, -318, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scroll, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scroll, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scroll, 540, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(scroll);
		textArea.setEditable(false);


		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Histoire", "Cinema", "Sport", "Musique", "Celebrites", "Monde", "Cuisine", "Animaux", "Series Televises", "Litterature"}));
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 0, SpringLayout.WEST, scroll);
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox, -35, SpringLayout.NORTH, scroll);
		frame.getContentPane().add(comboBox);
		JButton btnNewButton_1 = new JButton("Valider");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_1, -1, SpringLayout.NORTH, comboBox);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1, 0, SpringLayout.WEST, lblNewLabel);
		btnNewButton_1.addActionListener(e -> {
			textArea.setText(null);
			String theme = comboBox.getSelectedItem().toString();
			textArea.append(theme + "\n");
			ListeQuestions questionsdanstheme = questions.get(theme);
			for(Object question : questionsdanstheme.myList)
				textArea.append(question.toString());
		});
		frame.getContentPane().add(btnNewButton_1);
		frame.setVisible(true);
	}
}