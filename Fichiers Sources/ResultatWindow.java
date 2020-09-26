import javax.swing.JFrame;
import java.awt.GridLayout;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class ResultatWindow implements Phase
{

	private JFrame frame;

	private JLabel firstPlayer;

	private JLabel secondPlayer;

	private JLabel thirdPlayer;

	private JLabel fourthPlayer;

	private static int compteurEgalite = 0;
	private static int phaseAvantEgalite = 0;

	/**
	 * Create the application.
	 */
	public ResultatWindow(EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, Themes themes, int phase)
	{
		initialize(joueursSelectionnes, allQuestions, themes, phase);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, Themes themes, int phase)
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(5, 0, 0, 0));
		frame.setLocationRelativeTo(null);

		Collections.sort(joueursSelectionnes.getVectorJoueurs(), Collections.reverseOrder());
		
		// TEST EGALITE
		
		//joueursSelectionnes.getVectorJoueurs().get(0).setCompteur();
		//joueursSelectionnes.getVectorJoueurs().get(1).setCompteur();
		//joueursSelectionnes.getVectorJoueurs().get(2).setCompteur();
		//joueursSelectionnes.getVectorJoueurs().get(1).setCompteur();

		SelectionnerJoueurs(joueursSelectionnes, phase); //On récupère la liste des joueurs sélectionnés

		fillPlayer(joueursSelectionnes); //On remplit la frame avec la liste des joueurs sélectionnés

		frame.getContentPane().add(firstPlayer);


		frame.getContentPane().add(secondPlayer);


		frame.getContentPane().add(thirdPlayer);


		frame.getContentPane().add(fourthPlayer);

		JButton btnNewButton = new JButton("Continuer");
		//On relance la phase du jeu entamée
		btnNewButton.addActionListener(e -> PhasedeJeu(joueursSelectionnes, allQuestions, themes, phase));
		frame.getContentPane().add(btnNewButton);

		frame.setVisible(true);
	}

	private void fillPlayer(EnsJoueurs joueursSelectionnes)
	{
		//Inscription des pseudos des joueurs sélectionnés sur la frame
		firstPlayer = new JLabel("1 - "+joueursSelectionnes.getVectorJoueurs().get(0).getNom()+" : "+joueursSelectionnes.getVectorJoueurs().get(0).getScore()+" en "+joueursSelectionnes.getVectorJoueurs().get(0).getTimer().getTimerValue()+" ms "+" ("+joueursSelectionnes.getVectorJoueurs().get(0).getEtat()+")");
		firstPlayer.setHorizontalAlignment(SwingConstants.CENTER);

		secondPlayer = new JLabel("2 - "+joueursSelectionnes.getVectorJoueurs().get(1).getNom()+" : "+joueursSelectionnes.getVectorJoueurs().get(1).getScore()+" en "+joueursSelectionnes.getVectorJoueurs().get(1).getTimer().getTimerValue()+" ms "+" ("+joueursSelectionnes.getVectorJoueurs().get(1).getEtat()+")");
		secondPlayer.setHorizontalAlignment(SwingConstants.CENTER);

		thirdPlayer = new JLabel("3 - "+joueursSelectionnes.getVectorJoueurs().get(2).getNom()+" : "+joueursSelectionnes.getVectorJoueurs().get(2).getScore()+" en "+joueursSelectionnes.getVectorJoueurs().get(2).getTimer().getTimerValue()+" ms "+" ("+joueursSelectionnes.getVectorJoueurs().get(2).getEtat()+")");
		thirdPlayer.setHorizontalAlignment(SwingConstants.CENTER);

		fourthPlayer = new JLabel("4 - "+joueursSelectionnes.getVectorJoueurs().get(3).getNom()+" : "+joueursSelectionnes.getVectorJoueurs().get(3).getScore()+" en "+joueursSelectionnes.getVectorJoueurs().get(3).getTimer().getTimerValue()+" ms "+" ("+joueursSelectionnes.getVectorJoueurs().get(3).getEtat()+")");
		fourthPlayer.setHorizontalAlignment(SwingConstants.CENTER);
	}

	@Override
	public void SelectionnerJoueurs(EnsJoueurs joueursSelectionnes, int phase)
	{
		if(phase == 1) //4 joueurs sélectionnés
		{
			joueursSelectionnes.getVectorJoueurs().get(0).ChangerEtat("gagnant");
			joueursSelectionnes.getVectorJoueurs().get(1).ChangerEtat("gagnant");
			joueursSelectionnes.getVectorJoueurs().get(2).ChangerEtat("gagnant");
			joueursSelectionnes.getVectorJoueurs().get(3).ChangerEtat("elimine");
		}
		else if(phase == 2) //3 joueurs sélectionnés
		{
			joueursSelectionnes.getVectorJoueurs().get(0).ChangerEtat("gagnant");
			joueursSelectionnes.getVectorJoueurs().get(1).ChangerEtat("gagnant");
			joueursSelectionnes.getVectorJoueurs().get(2).ChangerEtat("elimine");
		}
		else if(phase == 3) //2 joueurs sélectionnés
		{
			joueursSelectionnes.getVectorJoueurs().get(0).ChangerEtat("super gagnant");
			joueursSelectionnes.getVectorJoueurs().get(1).ChangerEtat("elimine");
		}

	}

	@Override
	public void PhasedeJeu(EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, Themes themes, int phase)
	{
		phaseAvantEgalite += phase ;

		//Vérification d'une possible égalité entre joueurs
		EnsJoueurs ensEgalite = joueursSelectionnes.TrouverJoueurEgalite();
		ensEgalite.Afficher();
		if(ensEgalite.getVectorJoueurs().size() != 0 && compteurEgalite < 3)
		{
			JOptionPane.showMessageDialog(null, "Egalité entre"+ensEgalite.listeNom());
			frame.dispose();
			compteurEgalite++;
			int randomNb = (int) (Math.random() * ( 3 ));

			//On choisit aléatoirement le type de la question qui suivra, soit QCM, soit VF soit RC
			if(randomNb == 0)
			{
				new QCMWindow(0, ensEgalite, allQuestions, themes.selectTheme(), themes, 5); // On utilise la phase 5 pour representer une egalite
			}
			else if(randomNb == 1)
			{
				new VFWindow(0, ensEgalite, allQuestions, themes.selectTheme(), themes, 5);
			}
			else
			{
				new RCWindow(0, ensEgalite, allQuestions, themes.selectTheme(), themes, 5);
			}
		}
		else
		{
			phase = phaseAvantEgalite%5; // S'il y a eu une égalite, on doit retrouver la phase à laquelle on s'était arrêté
			phaseAvantEgalite = 0;
			compteurEgalite = 0;
			joueursSelectionnes.resetValeurEgalite(); 
			if(phase == 1)
			{
				frame.dispose();
				new ChoixThemeWindow(joueursSelectionnes, allQuestions, themes); //Retour à la phase ou le joeur récupère un thème au hasard
			}
			else if(phase == 2)
			{
				frame.dispose();
				int randomNb = (int) (Math.random() * ( 3 ));

				//Phase ou chaque joueur choisit 2 thèmes parmi 6, sélection au hasard du type de question
				if(randomNb == 0)
				{
					new QCMWindow(0, joueursSelectionnes, allQuestions, themes.selectTheme(), themes, 3);
				}
				else if(randomNb == 1)
				{
					new VFWindow(0, joueursSelectionnes, allQuestions, themes.selectTheme(), themes, 3);
				}
				else
				{
					new RCWindow(0, joueursSelectionnes, allQuestions, themes.selectTheme(), themes, 3);
				}
			}
			{
				frame.dispose();
			}
		}
	}

}