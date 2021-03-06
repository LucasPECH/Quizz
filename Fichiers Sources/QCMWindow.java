import javax.swing.JFrame;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPanel;

public class QCMWindow
{
	private JFrame frame;

	private JLabel playerLabel;

	private JLabel enonceLabel;

	private JButton buttonReponse;

	private JButton buttonReponse2;

	private JButton buttonReponse3;

	private Question<QCM> questionQCM;

	private JLabel heureLabel;
	private JLabel minuteLabel;
	private JLabel secondeLabel;
	private JLabel millisecondeLabel;

	private Thread thread;

    /**
     * Create the application.
     */
	public QCMWindow(int numeroTour, EnsJoueurs joueurSelectionnes, HashMap<String, ListeQuestions> allQuestions, String objetTheme, Themes themes, int phase)
	{
		initialize(numeroTour, joueurSelectionnes, allQuestions, objetTheme, themes, phase);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int numeroTour, EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, String objetTheme, Themes themes, int phase)
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 628, 358);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		frame.setLocationRelativeTo(null);

		FillWindow(numeroTour, joueursSelectionnes, allQuestions, objetTheme, phase);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));

		panel.add(playerLabel);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 4, 0, 0));

		heureLabel = new JLabel("00 : ");
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

		JButton scoreButton = new JButton("Score");
		scoreButton.addActionListener(arg0 -> new ScoreWindow(joueursSelectionnes));

		panel.add(scoreButton);

		buttonReponse.addActionListener(arg0 -> {
			if(phase == 1)
			{
				NextWindowP1(numeroTour, joueursSelectionnes, allQuestions, objetTheme, buttonReponse.getText(), themes, phase);
			}
			else if(phase == 2)
			{
				NextWindowP2(numeroTour, joueursSelectionnes, allQuestions, objetTheme, buttonReponse.getText(), themes, phase);
			}
			else if(phase == 3)
			{
				NextWindowP3(numeroTour, joueursSelectionnes, allQuestions, objetTheme, buttonReponse.getText(), themes, phase);
			}
			else
			{
				NextWindowEgalite(numeroTour, joueursSelectionnes, allQuestions, objetTheme, buttonReponse.getText(), themes, phase);
			}
		});

		buttonReponse2.addActionListener(arg0 -> {
			if(phase == 1)
			{
				NextWindowP1(numeroTour, joueursSelectionnes, allQuestions, objetTheme, buttonReponse2.getText(), themes, phase);
			}
			else if(phase == 2)
			{
				NextWindowP2(numeroTour, joueursSelectionnes, allQuestions, objetTheme, buttonReponse2.getText(), themes, phase);
			}
			else if(phase == 3)
			{
				NextWindowP3(numeroTour, joueursSelectionnes, allQuestions, objetTheme, buttonReponse2.getText(), themes, phase);
			}
			else
			{
				NextWindowEgalite(numeroTour, joueursSelectionnes, allQuestions, objetTheme, buttonReponse2.getText(), themes, phase);
			}
		});


		buttonReponse3.addActionListener(arg0 -> {
			if(phase == 1)
			{
				NextWindowP1(numeroTour, joueursSelectionnes, allQuestions, objetTheme, buttonReponse3.getText(), themes, phase);
			}
			else if(phase == 2)
			{
				NextWindowP2(numeroTour, joueursSelectionnes, allQuestions, objetTheme, buttonReponse3.getText(), themes, phase);
			}
			else if(phase == 3)
			{
				NextWindowP3(numeroTour, joueursSelectionnes, allQuestions, objetTheme, buttonReponse3.getText(), themes, phase);
			}
			else
			{
				NextWindowEgalite(numeroTour, joueursSelectionnes, allQuestions, objetTheme, buttonReponse3.getText(), themes, phase);
			}
		});

		frame.getContentPane().add(enonceLabel);
		frame.getContentPane().add(buttonReponse);
		frame.getContentPane().add(buttonReponse2);
		frame.getContentPane().add(buttonReponse3);

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
	 * Cette fonction permet de remplir correctement la fen�tre. Une question sur le bon th�me, du bon type et de la bonne difficult� est choisie. Le tout est possible � partir des
	 * param�tres. 
	 * 
	 * @numeroTour num�ro du tour dans la phase donn�e, on utilise ce num�ro avec un modulo pour savoir quel joueur doit r�pondre � la question sur cette fenetre
	 * @joueursSelectionnes Liste des 4 joueurs selectionnes pour la partie
	 * @allQuestions Liste de toutes les questions du jeu
	 * @objetTheme nom du theme sur lequel la question va devoir porter
	 * @phase nombre entier permettant de determiner la phase de jeu (cela permet par la m�me occasion de determiner la difficult� des question)
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

		while(!question.getEnonce().getClass().getName().equals("QCM"))
		{
			question =  allQuestions.get(objetTheme).getQuestion(phase%4);
		}

		questionQCM = (Question<QCM>) question;

		enonceLabel = new JLabel("Question : " + questionQCM.getEnonce().getText());
		enonceLabel.setHorizontalAlignment(SwingConstants.CENTER);

		buttonReponse = new JButton(questionQCM.getEnonce().getReponse());
		buttonReponse2 = new JButton(questionQCM.getEnonce().getReponse2());
		buttonReponse3 = new JButton(questionQCM.getEnonce().getReponse3());
	}
	
	/*
	 * Cette fonction permet de lancer le chronom�tre (grace � un thread) du joueur qui doit r�pondre � la question
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
	 * Cette fonction permet d'arr�ter le chronom�tre (grace � un thread) du joueur qui a r�pondu � la question
	 * 
	 * @timer timer (chronometre) du joueur que nous devons arr�ter.
	 */
	@SuppressWarnings("deprecation")
	public void StopThread(Joueur.Timer timer)
	{
		thread.stop();
	}

	/*
	 * Cette fonction permet de determiner quel fen�tre ouvrir apr�s que le joueur ai r�pondu � sa question. En fonction du tour cela peut �tre une question pour un autre joueur ou alors l'affichage
	 * des r�sultats de la phase. C'est aussi cette fonction qui permet d'ajouter des points au joueur s'il a trouv� la bonne r�ponse.
	 * 
	 * Cette fonction ne s'occupe que de la phase 1
	 * 
	 * @numeroTour num�ro du tour dans la phase donn�e, on utilise ce num�ro avec un modulo pour savoir quel joueur doit r�pondre � la question sur cette fenetre
	 * @joueursSelectionnes Liste des 4 joueurs selectionnes pour la partie
	 * @allQuestions Liste de toutes les questions du jeu
	 * @objetTheme nom du theme sur lequel la question va devoir porter
	 * @buttonValue r�ponse que le joueur a donn� � la question pos�e.
	 * @themes objet avec tous les th�mes
	 * @phase nombre entier permettant de determiner la phase de jeu (cela permet par la m�me occasion de determiner la difficult� des question)
	 * 
	 * Tout ces parametres sont n�cessaires car ils doivent �tre transmis � la fen�tre suivante
	 */
	public void NextWindowP1(int numeroTour,EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, String objetTheme, String buttonValue, Themes themes, int phase)
	{
		StopThread(joueursSelectionnes.getVectorJoueurs().get(numeroTour).getTimer());

		if(buttonValue.equals(questionQCM.getEnonce().getBonneReponse()))
		{
			joueursSelectionnes.getVectorJoueurs().get(numeroTour).MAJScore(2);
			JOptionPane.showMessageDialog(null, "Bien joue !");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Mauvaise reponse ! La bonne reponse etait : "+questionQCM.getEnonce().getBonneReponse());
		}

		if(numeroTour < 3)
		{
			frame.setVisible(false);
			frame.dispose();
			frame.removeAll();
			int randomNb = (int) (Math.random() * ( 2 ));

			if(randomNb == 0)
			{
				new VFWindow(numeroTour+1, joueursSelectionnes, allQuestions, objetTheme, themes, phase);
			}
			else
			{
				new RCWindow(numeroTour+1, joueursSelectionnes, allQuestions, objetTheme, themes, phase);
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
	 * Cette fonction permet de determiner quel fen�tre ouvrir apr�s que le joueur ai r�pondu � sa question. En fonction du tour cela peut �tre une question pour un autre joueur ou alors l'affichage
	 * des r�sultats de la phase. C'est aussi cette fonction qui permet d'ajouter des points au joueur s'il a trouv� la bonne r�ponse.
	 * 
	 * Cette fonction ne s'occupe que de la phase 2
	 * 
	 * @numeroTour num�ro du tour dans la phase donn�e, on utilise ce num�ro avec un modulo pour savoir quel joueur doit r�pondre � la question sur cette fenetre
	 * @joueursSelectionnes Liste des 4 joueurs selectionnes pour la partie
	 * @allQuestions Liste de toutes les questions du jeu
	 * @objetTheme nom du theme sur lequel la question va devoir porter
	 * @buttonValue r�ponse que le joueur a donn� � la question pos�e.
	 * @themes objet avec tous les th�mes
	 * @phase nombre entier permettant de determiner la phase de jeu (cela permet par la m�me occasion de determiner la difficult� des question)
	 * 
	 * Tout ces parametres sont n�cessaires car ils doivent �tre transmis � la fen�tre suivante
	 */
	public void NextWindowP2(int numeroTour,EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, String objetTheme, String buttonValue, Themes themes, int phase)
	{
		StopThread(joueursSelectionnes.getVectorJoueurs().get(numeroTour).getTimer());

		if(buttonValue.equals(questionQCM.getEnonce().getBonneReponse()))
		{
			joueursSelectionnes.getVectorJoueurs().get(numeroTour).MAJScore(3);
			JOptionPane.showMessageDialog(null, "Bien joue !");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Mauvaise reponse ! La bonne reponse etait : "+questionQCM.getEnonce().getBonneReponse());
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
	 * Cette fonction permet de determiner quel fen�tre ouvrir apr�s que le joueur ai r�pondu � sa question. En fonction du tour cela peut �tre une question pour un autre joueur ou alors l'affichage
	 * des r�sultats de la phase. C'est aussi cette fonction qui permet d'ajouter des points au joueur s'il a trouv� la bonne r�ponse.
	 * 
	 * Cette fonction ne s'occupe que de la phase 3
	 * 
	 * @numeroTour num�ro du tour dans la phase donn�e, on utilise ce num�ro avec un modulo pour savoir quel joueur doit r�pondre � la question sur cette fenetre
	 * @joueursSelectionnes Liste des 4 joueurs selectionnes pour la partie
	 * @allQuestions Liste de toutes les questions du jeu
	 * @objetTheme nom du theme sur lequel la question va devoir porter
	 * @buttonValue r�ponse que le joueur a donn� � la question pos�e.
	 * @themes objet avec tous les th�mes
	 * @phase nombre entier permettant de determiner la phase de jeu (cela permet par la m�me occasion de determiner la difficult� des question)
	 * 
	 * Tout ces parametres sont n�cessaires car ils doivent �tre transmis � la fen�tre suivante
	 */
	public void NextWindowP3(int numeroTour,EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, String objetTheme, String buttonValue, Themes themes, int phase)
	{
		StopThread(joueursSelectionnes.getVectorJoueurs().get(numeroTour%2).getTimer());

		if(buttonValue.equals(questionQCM.getEnonce().getBonneReponse()))
		{
			joueursSelectionnes.getVectorJoueurs().get(numeroTour%2).MAJScore(5);
			JOptionPane.showMessageDialog(null, "Bien joue !");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Mauvaise reponse ! La bonne reponse etait : "+questionQCM.getEnonce().getBonneReponse());
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
				new RCWindow(numeroTour+1, joueursSelectionnes, allQuestions, objetTheme, themes, phase);
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
	 * Cette fonction permet de determiner quel fen�tre ouvrir apr�s que le joueur ai r�pondu � sa question. En fonction du tour cela peut �tre une question pour un autre joueur ou alors l'affichage
	 * des r�sultats de la phase. C'est aussi cette fonction qui permet d'ajouter des points au joueur s'il a trouv� la bonne r�ponse.
	 * 
	 * Cette fonction n'est utilis� que si le programme est entrain de g�rer une �galit�
	 * 
	 * @numeroTour num�ro du tour dans la phase donn�e, on utilise ce num�ro avec un modulo pour savoir quel joueur doit r�pondre � la question sur cette fenetre
	 * @joueursSelectionnes Liste des 4 joueurs selectionnes pour la partie
	 * @allQuestions Liste de toutes les questions du jeu
	 * @objetTheme nom du theme sur lequel la question va devoir porter
	 * @buttonValue r�ponse que le joueur a donn� � la question pos�e.
	 * @themes objet avec tous les th�mes
	 * @phase nombre entier permettant de determiner la phase de jeu (cela permet par la m�me occasion de determiner la difficult� des question)
	 * 
	 * Tout ces parametres sont n�cessaires car ils doivent �tre transmis � la fen�tre suivante
	 */
	public void NextWindowEgalite(int numeroTour,EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, String objetTheme, String buttonValue, Themes themes, int phase)
	{
		//StopThread(joueursSelectionnes.getVectorJoueurs().get(numeroTour%joueursSelectionnes.getVectorJoueurs().size()).getTimer());

		if(buttonValue.equals(questionQCM.getEnonce().getBonneReponse()))
		{
			joueursSelectionnes.getVectorJoueurs().get(numeroTour%joueursSelectionnes.getVectorJoueurs().size()).MAJScore(0.01);
			JOptionPane.showMessageDialog(null, "Bien joue !");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Mauvaise reponse ! La bonne reponse etait : "+questionQCM.getEnonce().getBonneReponse());
		}
		
		if(numeroTour < joueursSelectionnes.getVectorJoueurs().size()-1)
		{
			frame.setVisible(false);
			frame.dispose();
			int randomNb = (int) (Math.random() * ( 2 ));

			if(randomNb == 0)
			{
				new RCWindow(numeroTour+1, joueursSelectionnes, allQuestions, objetTheme, themes, phase);
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
