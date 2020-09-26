import javax.swing.JFrame;
import java.awt.GridLayout;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class ChoixThemeWindow
{

	private static JFrame frame;

	private static JLabel lblNewLabel;

	private static JButton buttonTheme1;

	private static JButton buttonTheme2;

	private static JButton buttonTheme3;

	private static JButton buttonTheme4;

	private static JButton buttonTheme5;

	private static JButton buttonTheme6;

	private static int numeroTour = -1;

	public static JFrame getFrame()
	{
		return frame;
	}

	public static void increaseNumTour()
	{
		numeroTour++;
	}

	public static int getNumTour()
	{
		return numeroTour;
	}

	public static boolean allDisabled()
	{
		return !buttonTheme1.isEnabled() && !buttonTheme2.isEnabled() && !buttonTheme3.isEnabled() && !buttonTheme4.isEnabled() && !buttonTheme5.isEnabled() && !buttonTheme6.isEnabled();
	}

	public static void changePlayer(EnsJoueurs joueursSelectionnes)
	{
		lblNewLabel.setText("Au tour de "+joueursSelectionnes.getVectorJoueurs().get((getNumTour()+1)%3).getNom()+" de choisir un theme :");
	}
	/**
	 * Create the application.
	 */
	public ChoixThemeWindow(EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, Themes themes) {
		initialize(joueursSelectionnes, allQuestions, themes);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, Themes themes)
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(7, 0, 0, 0));
		frame.setLocationRelativeTo(null);
		
		lblNewLabel = new JLabel("Au tour de "+joueursSelectionnes.getVectorJoueurs().get(getNumTour()+1).getNom()+" de choisir un theme :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);

		fillWindow(joueursSelectionnes, allQuestions, themes);

		frame.getContentPane().add(buttonTheme1);

		frame.getContentPane().add(buttonTheme2);

		frame.getContentPane().add(buttonTheme3);

		frame.getContentPane().add(buttonTheme4);

		frame.getContentPane().add(buttonTheme5);

		frame.getContentPane().add(buttonTheme6);
		frame.setVisible(true);
	}

	public void fillWindow(EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, Themes themes)
	{
		String[] selectedTheme = themes.selectSixThemes();

		buttonTheme1 = new JButton(selectedTheme[0]);
		buttonTheme2 = new JButton(selectedTheme[1]);
		buttonTheme3 = new JButton(selectedTheme[2]);
		buttonTheme4 = new JButton(selectedTheme[3]);
		buttonTheme5 = new JButton(selectedTheme[4]);
		buttonTheme6 = new JButton(selectedTheme[5]);

		buttonTheme1.addActionListener(e -> {
			buttonTheme1.setEnabled(false);
			NextWindow(joueursSelectionnes, allQuestions,buttonTheme1.getText() , themes);
		});

		buttonTheme2.addActionListener(e -> {
			buttonTheme2.setEnabled(false);
			NextWindow(joueursSelectionnes, allQuestions,buttonTheme2.getText() , themes);
		});

		buttonTheme3.addActionListener(e -> {
			buttonTheme3.setEnabled(false);
			NextWindow(joueursSelectionnes, allQuestions,buttonTheme3.getText() , themes);
		});

		buttonTheme4.addActionListener(e -> {
			buttonTheme4.setEnabled(false);
			NextWindow(joueursSelectionnes, allQuestions,buttonTheme4.getText() , themes);
		});

		buttonTheme5.addActionListener(e -> {
			buttonTheme5.setEnabled(false);
			NextWindow(joueursSelectionnes, allQuestions,buttonTheme5.getText() , themes);
		});

		buttonTheme6.addActionListener(e -> {
			buttonTheme6.setEnabled(false);
			NextWindow(joueursSelectionnes, allQuestions,buttonTheme6.getText() , themes);
		});
	}

	public void NextWindow(EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, String buttonValue, Themes themes)
	{
		int randomNb = (int) (Math.random() * ( 3 ));

		increaseNumTour();
		changePlayer(joueursSelectionnes);

		if(randomNb == 0)
		{
			frame.setVisible(false);
			new VFWindow((getNumTour())%3, joueursSelectionnes, allQuestions, buttonValue, themes, 2);
		}
		else if(randomNb == 1)
		{
			frame.setVisible(false);
			new RCWindow((getNumTour())%3, joueursSelectionnes, allQuestions, buttonValue, themes, 2);
		}
		else
		{
			frame.setVisible(false);
			new QCMWindow((getNumTour())%3, joueursSelectionnes, allQuestions,buttonValue, themes, 2);
		}
	}

}
