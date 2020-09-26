import javax.swing.JFrame;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class VFWindow
{

	private JFrame frame;

	private JLabel playerLabel;

	private JLabel enonceLabel;

	private JLabel heureLabel;

	private JLabel minuteLabel;

	private JLabel secondeLabel;

	private JLabel millisecondeLabel;

	private Question<VF> questionVF;

	private Thread thread;

	/**
	 * Create the application.
	 */
	public VFWindow(int numeroTour, EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, String objetTheme, Themes themes, int phase) {
		initialize(numeroTour, joueursSelectionnes, allQuestions, objetTheme, themes, phase);
		//On initialise la fenêtre/partie de jeu en fonction d'un numéro de tour, de joueurs restants, de la liste de questions, des thèmes restants et de la phase de jeu (1,2 ou 3)
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int numeroTour, EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, String objetTheme, Themes themes, int phase) {
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

		millisecondeLabel = new JLabel("00 ");
		millisecondeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(millisecondeLabel);

		JButton buttonScore = new JButton("Score");
		//Affichage de la fenêtre des scores
		buttonScore.addActionListener(e -> new ScoreWindow(joueursSelectionnes));
		panel.add(buttonScore);

		frame.getContentPane().add(enonceLabel);

		JButton btnNewButton = new JButton("Vrai"); //Bouton réponse "vrai"
		btnNewButton.addActionListener(arg0 -> {
			if(phase == 1)
			{
				NextWindowP1(numeroTour, joueursSelectionnes, allQuestions, objetTheme, btnNewButton.getText(), themes, phase);
			}
			else if(phase == 2)
			{
				NextWindowP2(numeroTour, joueursSelectionnes, allQuestions, objetTheme, btnNewButton.getText(), themes, phase);
			}
			else if(phase == 3)
			{
				NextWindowP3(numeroTour, joueursSelectionnes, allQuestions, objetTheme, btnNewButton.getText(), themes, phase);
			}
			else
			{
				NextWindowEgalite(numeroTour, joueursSelectionnes, allQuestions, objetTheme, btnNewButton.getText(), themes, phase);
			}
		});
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Faux"); //Bouton réponse faux
		btnNewButton_1.addActionListener(arg0 -> {
			if(phase == 1)
			{
				NextWindowP1(numeroTour, joueursSelectionnes, allQuestions, objetTheme, btnNewButton_1.getText(), themes, phase);
			}
			else if(phase == 2)
			{
				NextWindowP2(numeroTour, joueursSelectionnes, allQuestions, objetTheme, btnNewButton_1.getText(), themes, phase);
			}
			else if(phase == 3)
			{
				NextWindowP3(numeroTour, joueursSelectionnes, allQuestions, objetTheme, btnNewButton_1.getText(), themes, phase);
			}
			else
			{
				NextWindowEgalite(numeroTour, joueursSelectionnes, allQuestions, objetTheme, btnNewButton_1.getText(), themes, phase);
			}
		});
		frame.getContentPane().add(btnNewButton_1);

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

		while(!question.getEnonce().getClass().getName().equals("VF"))
		{
			question =  allQuestions.get(objetTheme).getQuestion(phase%4);
		}

		questionVF = (Question<VF>) question;

		enonceLabel = new JLabel("Question : " + questionVF.getEnonce().getText());
		enonceLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}

	/*
	 * Cette fonction permet de lancer le chronom�tre (grace � un thread) du joueur qui doit r�pondre � la question
	 * 
	 * @timer timer (chronometre) du joueur que nous devons faire augmenter.
	 */
	public void StartTimer(Joueur.Timer timer)
	{ //Timer de chaque joueur
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

		if( (buttonValue.equals("Vrai") && questionVF.getEnonce().getBonneReponse()) || (buttonValue.equals("Faux") && !questionVF.getEnonce().getBonneReponse()))
		{
			joueursSelectionnes.getVectorJoueurs().get(numeroTour).MAJScore(2);
			JOptionPane.showMessageDialog(null, "Bien joué !");
		}
		else
		{
			if(questionVF.getEnonce().getBonneReponse())
			{
				JOptionPane.showMessageDialog(null, "Mauvaise réponse ! La bonne réponse était : vrai");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Mauvaise réponse ! La bonne réponse était : faux");
			}
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

		if( (buttonValue.equals("Vrai") && questionVF.getEnonce().getBonneReponse()) || (buttonValue.equals("Faux") && !questionVF.getEnonce().getBonneReponse()))
		{
			joueursSelectionnes.getVectorJoueurs().get(numeroTour).MAJScore(3);
			JOptionPane.showMessageDialog(null, "Bien joue !");
		}
		else
		{
			if(questionVF.getEnonce().getBonneReponse())
			{
				JOptionPane.showMessageDialog(null, "Mauvaise reponse ! La bonne reponse etait : vrai");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Mauvaise reponse ! La bonne reponse etait : faux");
			}
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

		if( (buttonValue.equals("Vrai") && questionVF.getEnonce().getBonneReponse()) || (buttonValue.equals("Faux") && !questionVF.getEnonce().getBonneReponse()))
		{
			joueursSelectionnes.getVectorJoueurs().get(numeroTour%2).MAJScore(5);
			JOptionPane.showMessageDialog(null, "Bien joue !");
		}
		else
		{
			if(questionVF.getEnonce().getBonneReponse())
			{
				JOptionPane.showMessageDialog(null, "Mauvaise reponse ! La bonne reponse etait : vrai");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Mauvaise reponse ! La bonne reponse etait : faux");
			}
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

		if( (buttonValue.equals("Vrai") && questionVF.getEnonce().getBonneReponse()) || (buttonValue.equals("Faux") && !questionVF.getEnonce().getBonneReponse()))
		{
			joueursSelectionnes.getVectorJoueurs().get(numeroTour%joueursSelectionnes.getVectorJoueurs().size()).MAJScore(0.01);
			JOptionPane.showMessageDialog(null, "Bien joue !");
		}
		else
		{
			if(questionVF.getEnonce().getBonneReponse())
			{
				JOptionPane.showMessageDialog(null, "Mauvaise reponse ! La bonne reponse etait : vrai");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Mauvaise reponse ! La bonne reponse etait : faux");
			}
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
				new RCWindow(numeroTour+1, joueursSelectionnes, allQuestions, objetTheme, themes, phase);
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
