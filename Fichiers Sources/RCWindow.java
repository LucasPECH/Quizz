import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.util.HashMap;

public class RCWindow {

	private JFrame frame;

	private JLabel playerLabel;

	private JLabel enonceLabel;

	private JLabel heureLabel;

	private JLabel minuteLabel;

	private JLabel secondeLabel;

	private JLabel millisecondeLabel;

	private Question<RC> questionRC;


	private Thread thread;

    /**
     * Create the application.
     */
	public RCWindow(int numeroTour, EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, String objetTheme, Themes themes, int phase) {
		initialize(numeroTour, joueursSelectionnes, allQuestions, objetTheme, themes, phase);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int numeroTour, EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, String objetTheme, Themes themes, int phase)
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(4, 0, 0, 0));
		frame.setLocationRelativeTo(null);

		FillWindow(numeroTour, joueursSelectionnes, allQuestions, objetTheme, phase);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));

		panel.add(playerLabel);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 4, 0, 0));

		heureLabel = new JLabel("00 :");
		heureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(heureLabel);

		minuteLabel = new JLabel("00 :");
		minuteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(minuteLabel);

		secondeLabel = new JLabel("00 :");
		secondeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(secondeLabel);

		millisecondeLabel = new JLabel("00");
		millisecondeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(millisecondeLabel);

		JButton btnNewButton_1 = new JButton("Score");
		btnNewButton_1.addActionListener(e -> new ScoreWindow(joueursSelectionnes));
		panel.add(btnNewButton_1);

		frame.getContentPane().add(enonceLabel);

		JTextPane textPane = new JTextPane();
		frame.getContentPane().add(textPane);

		JButton btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(e -> {
			if(phase == 1)
			{
				NextWindowP1(numeroTour,joueursSelectionnes, allQuestions, objetTheme, textPane.getText(), themes, phase);
			}
			else if(phase == 2)
			{
				NextWindowP2(numeroTour,joueursSelectionnes, allQuestions, objetTheme, textPane.getText(), themes, phase);
			}
			else if(phase == 3)
			{
				NextWindowP3(numeroTour,joueursSelectionnes, allQuestions, objetTheme, textPane.getText(), themes, phase);
			}
			else
			{
				NextWindowEgalite(numeroTour,joueursSelectionnes, allQuestions, objetTheme, textPane.getText(), themes, phase);
			}
		});
		frame.getContentPane().add(btnNewButton);

		if(phase == 3)
		{
			StartTimer(joueursSelectionnes.getVectorJoueurs().get(numeroTour%2).getTimer());
		}
		else if(phase == 5)
		{
			//StartTimer(joueursSelectionnes.getVectorJoueurs().get(numeroTour%joueursSelectionnes.getVectorJoueurs().size()).getTimer());
		}
		else
		{
			StartTimer(joueursSelectionnes.getVectorJoueurs().get(numeroTour).getTimer());
		}

		frame.setVisible(true);
	}
	
	/*
	 * Cette fonction permet de remplir correctement la fenêtre. Une question sur le bon thème, du bon type et de la bonne difficulté est choisie. Le tout est possible à partir des
	 * paramètres. 
	 * 
	 * @numeroTour numéro du tour dans la phase donnée, on utilise ce numéro avec un modulo pour savoir quel joueur doit répondre à la question sur cette fenetre
	 * @joueursSelectionnes Liste des 4 joueurs selectionnes pour la partie
	 * @allQuestions Liste de toutes les questions du jeu
	 * @objetTheme nom du theme sur lequel la question va devoir porter
	 * @phase nombre entier permettant de determiner la phase de jeu (cela permet par la même occasion de determiner la difficulté des question)
	 */

	@SuppressWarnings("unchecked")
	public void FillWindow(int numeroTour,EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, String objetTheme, int phase)
	{
		if(phase == 3)
		{
			playerLabel = new JLabel(objetTheme+" / "+joueursSelectionnes.getVectorJoueurs().get(numeroTour%2).getNom());
		}
		else if(phase == 5)
		{
			playerLabel = new JLabel(objetTheme+" / "+joueursSelectionnes.getVectorJoueurs().get(numeroTour%joueursSelectionnes.getVectorJoueurs().size()).getNom());
		}
		else
		{
			playerLabel = new JLabel(objetTheme+" / "+joueursSelectionnes.getVectorJoueurs().get(numeroTour).getNom());
		}
		
		playerLabel.setHorizontalAlignment(SwingConstants.CENTER);

		Question<?> question = allQuestions.get(objetTheme).getQuestion(phase%4);

		while(!question.getEnonce().getClass().getName().equals("RC"))
		{
			question =  allQuestions.get(objetTheme).getQuestion(phase%4);
		}

		questionRC = (Question<RC>) question;

		enonceLabel = new JLabel("Question : " + questionRC.getEnonce().getText());
		enonceLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}

	/*
	 * Cette fonction permet de lancer le chronomètre (grace à un thread) du joueur qui doit répondre à la question
	 * 
	 * @timer timer (chronometre) du joueur que nous devons faire augmenter.
	 */
	public void StartTimer(Joueur.Timer timer)
	{
		thread = new Thread(() -> {
			for(;;)
			{
				try
				{
					Thread.sleep(1);

					if(timer.millisecondes > 1000)
					{
						timer.millisecondes = 0;
						timer.secondes++;
					}
					if(timer.secondes > 60)
					{
						timer.millisecondes = 0;
						timer.secondes = 0;
						timer.minutes++;
					}
					if(timer.minutes > 60)
					{
						timer.millisecondes = 0;
						timer.secondes = 0;
						timer.minutes = 0;
						timer.heures++;
					}
					heureLabel.setText(timer.heures+" :");
					minuteLabel.setText(timer.minutes+" :");
					secondeLabel.setText(timer.secondes+" :");
					millisecondeLabel.setText(timer.millisecondes+"");
					timer.millisecondes++;
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		});
		thread.start();
	}

	/*
	 * Cette fonction permet d'arrêter le chronomètre (grace à un thread) du joueur qui a répondu à la question
	 * 
	 * @timer timer (chronometre) du joueur que nous devons arrêter.
	 */
	@SuppressWarnings("deprecation")
	public void StopThread(Joueur.Timer timer)
	{
		thread.stop();
	}

	/*
	 * Cette fonction permet de determiner quel fenêtre ouvrir après que le joueur ai répondu à sa question. En fonction du tour cela peut être une question pour un autre joueur ou alors l'affichage
	 * des résultats de la phase. C'est aussi cette fonction qui permet d'ajouter des points au joueur s'il a trouvé la bonne réponse.
	 * 
	 * Cette fonction ne s'occupe que de la phase 1
	 * 
	 * @numeroTour numéro du tour dans la phase donnée, on utilise ce numéro avec un modulo pour savoir quel joueur doit répondre à la question sur cette fenetre
	 * @joueursSelectionnes Liste des 4 joueurs selectionnes pour la partie
	 * @allQuestions Liste de toutes les questions du jeu
	 * @objetTheme nom du theme sur lequel la question va devoir porter
	 * @textValue réponse que le joueur a donné à la question posée.
	 * @themes objet avec tous les thèmes
	 * @phase nombre entier permettant de determiner la phase de jeu (cela permet par la même occasion de determiner la difficulté des question)
	 * 
	 * Tout ces parametres sont nécessaires car ils doivent être transmis à la fenêtre suivante
	 */
	public void NextWindowP1(int numeroTour,EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, String objetTheme, String textValue, Themes themes, int phase)
	{
		StopThread(joueursSelectionnes.getVectorJoueurs().get(numeroTour).getTimer());

		textValue = textValue.toLowerCase();
		if(textValue.equals(questionRC.getEnonce().getBonneReponse()))
		{
			joueursSelectionnes.getVectorJoueurs().get(numeroTour).MAJScore(2);
			JOptionPane.showMessageDialog(null, "Bien joue !");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Mauvaise reponse ! La bonne reponse etait : "+ questionRC.getEnonce().getBonneReponse());
		}
		if(numeroTour < 3)
		{
			frame.setVisible(false);
			frame.dispose();
			int randomNb = (int) (Math.random() * ( 2 ));

			if(randomNb == 0)
			{
				new QCMWindow(numeroTour+1, joueursSelectionnes, allQuestions, objetTheme, themes, phase);
			}
			else
			{
				new VFWindow(numeroTour+1, joueursSelectionnes, allQuestions, objetTheme, themes, phase);
			}

		}
		else
		{
			frame.setVisible(false);
			frame.dispose();
			new ResultatWindow(joueursSelectionnes, allQuestions, themes, phase);
		}
	}

	/*
	 * Cette fonction permet de determiner quel fenêtre ouvrir après que le joueur ai répondu à sa question. En fonction du tour cela peut être une question pour un autre joueur ou alors l'affichage
	 * des résultats de la phase. C'est aussi cette fonction qui permet d'ajouter des points au joueur s'il a trouvé la bonne réponse.
	 * 
	 * Cette fonction ne s'occupe que de la phase 2
	 * 
	 * @numeroTour numéro du tour dans la phase donnée, on utilise ce numéro avec un modulo pour savoir quel joueur doit répondre à la question sur cette fenetre
	 * @joueursSelectionnes Liste des 4 joueurs selectionnes pour la partie
	 * @allQuestions Liste de toutes les questions du jeu
	 * @textTheme nom du theme sur lequel la question va devoir porter
	 * @buttonValue réponse que le joueur a donné à la question posée.
	 * @themes objet avec tous les thèmes
	 * @phase nombre entier permettant de determiner la phase de jeu (cela permet par la même occasion de determiner la difficulté des question)
	 * 
	 * Tout ces parametres sont nécessaires car ils doivent être transmis à la fenêtre suivante
	 */
	public void NextWindowP2(int numeroTour,EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, String objetTheme, String textValue, Themes themes, int phase)
	{
		StopThread(joueursSelectionnes.getVectorJoueurs().get(numeroTour).getTimer());

		if(textValue.equals(questionRC.getEnonce().getBonneReponse()))
		{
			joueursSelectionnes.getVectorJoueurs().get(numeroTour).MAJScore(3);
			JOptionPane.showMessageDialog(null, "Bien joue !");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Mauvaise reponse ! La bonne reponse etait : "+ questionRC.getEnonce().getBonneReponse());
		}
		if(ChoixThemeWindow.allDisabled())
		{
			frame.setVisible(false);
			frame.dispose();
			new ResultatWindow(joueursSelectionnes, allQuestions, themes, phase);
		}
		else
		{
			frame.setVisible(false);
			frame.dispose();
			ChoixThemeWindow.getFrame().setVisible(true);
		}
	}
	
	/*
	 * Cette fonction permet de determiner quel fenêtre ouvrir après que le joueur ai répondu à sa question. En fonction du tour cela peut être une question pour un autre joueur ou alors l'affichage
	 * des résultats de la phase. C'est aussi cette fonction qui permet d'ajouter des points au joueur s'il a trouvé la bonne réponse.
	 * 
	 * Cette fonction ne s'occupe que de la phase 3
	 * 
	 * @numeroTour numéro du tour dans la phase donnée, on utilise ce numéro avec un modulo pour savoir quel joueur doit répondre à la question sur cette fenetre
	 * @joueursSelectionnes Liste des 4 joueurs selectionnes pour la partie
	 * @allQuestions Liste de toutes les questions du jeu
	 * @objetTheme nom du theme sur lequel la question va devoir porter
	 * @textValue réponse que le joueur a donné à la question posée.
	 * @themes objet avec tous les thèmes
	 * @phase nombre entier permettant de determiner la phase de jeu (cela permet par la même occasion de determiner la difficulté des question)
	 * 
	 * Tout ces parametres sont nécessaires car ils doivent être transmis à la fenêtre suivante
	 */
	public void NextWindowP3(int numeroTour,EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, String objetTheme, String textValue, Themes themes, int phase)
	{
		StopThread(joueursSelectionnes.getVectorJoueurs().get(numeroTour%2).getTimer());

		if(textValue.equals(questionRC.getEnonce().getBonneReponse()))
		{
			joueursSelectionnes.getVectorJoueurs().get(numeroTour%2).MAJScore(5);
			JOptionPane.showMessageDialog(null, "Bien joue!");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Mauvaise reponse ! La bonne reponse etait : "+ questionRC.getEnonce().getBonneReponse());
		}
		if(numeroTour < 5)
		{
			frame.setVisible(false);
			frame.dispose();
			int randomNb = (int) (Math.random() * ( 2 ));
			
			if(numeroTour%2 == 1)
			{
				objetTheme = themes.selectTheme();
			}

			if(randomNb == 0)
			{
				new QCMWindow(numeroTour+1, joueursSelectionnes, allQuestions, objetTheme, themes, phase);
			}
			else
			{
				new VFWindow(numeroTour+1, joueursSelectionnes, allQuestions, objetTheme, themes, phase);
			}

		}
		else
		{
			frame.setVisible(false);
			frame.dispose();
			new ResultatWindow(joueursSelectionnes, allQuestions, themes, phase);
		}
		
	}
	
	
	/*
	 * Cette fonction permet de determiner quel fenêtre ouvrir après que le joueur ai répondu à sa question. En fonction du tour cela peut être une question pour un autre joueur ou alors l'affichage
	 * des résultats de la phase. C'est aussi cette fonction qui permet d'ajouter des points au joueur s'il a trouvé la bonne réponse.
	 * 
	 * Cette fonction n'est utilisé que si le programme est entrain de gérer une égalité
	 * 
	 * @numeroTour numéro du tour dans la phase donnée, on utilise ce numéro avec un modulo pour savoir quel joueur doit répondre à la question sur cette fenetre
	 * @joueursSelectionnes Liste des 4 joueurs selectionnes pour la partie
	 * @allQuestions Liste de toutes les questions du jeu
	 * @objetTheme nom du theme sur lequel la question va devoir porter
	 * @textValue réponse que le joueur a donné à la question posée.
	 * @themes objet avec tous les thèmes
	 * @phase nombre entier permettant de determiner la phase de jeu (cela permet par la même occasion de determiner la difficulté des question)
	 * 
	 * Tout ces parametres sont nécessaires car ils doivent être transmis à la fenêtre suivante
	 */
	public void NextWindowEgalite(int numeroTour,EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, String objetTheme, String textValue, Themes themes, int phase)
	{
		//StopThread(joueursSelectionnes.getVectorJoueurs().get(numeroTour%joueursSelectionnes.getVectorJoueurs().size()).getTimer());

		if(textValue.equals(questionRC.getEnonce().getBonneReponse()))
		{
			joueursSelectionnes.getVectorJoueurs().get(numeroTour%joueursSelectionnes.getVectorJoueurs().size()).MAJScore(0.01);
			JOptionPane.showMessageDialog(null, "Bien joue!");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Mauvaise reponse ! La bonne reponse etait : "+ questionRC.getEnonce().getBonneReponse());
		}
		
		if(numeroTour < joueursSelectionnes.getVectorJoueurs().size()-1)
		{
			frame.setVisible(false);
			frame.dispose();
			int randomNb = (int) (Math.random() * ( 2 ));

			if(randomNb == 0)
			{
				new QCMWindow(numeroTour+1, joueursSelectionnes, allQuestions, objetTheme, themes, phase);
			}
			else
			{
				new VFWindow(numeroTour+1, joueursSelectionnes, allQuestions, objetTheme, themes, phase);
			}

		}
		else
		{
			frame.setVisible(false);
			frame.dispose();
			new ResultatWindow(PseudoWindow.getJoueurs(), allQuestions, themes, phase);
		}
		
	}


}