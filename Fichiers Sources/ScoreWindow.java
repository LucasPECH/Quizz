import javax.swing.JFrame;
import java.awt.GridLayout;
import java.util.Collections;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ScoreWindow {

	private JLabel firstPlayer, secondPlayer, thirdPlayer, fourthPlayer;

	/**
	 * Create the application.
	 */
	public ScoreWindow(EnsJoueurs joueursSelectionnes) {
		initialize(joueursSelectionnes);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(EnsJoueurs joueursSelectionnes) {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(new GridLayout(4, 0, 0, 0));
		frame.setLocationRelativeTo(null);

		EnsJoueurs copieJoueurs = new EnsJoueurs(joueursSelectionnes);
		Collections.sort(copieJoueurs.getVectorJoueurs(), Collections.reverseOrder());

		fillPlayer(copieJoueurs);

		frame.getContentPane().add(firstPlayer);

		frame.getContentPane().add(secondPlayer);

		frame.getContentPane().add(thirdPlayer);

		frame.getContentPane().add(fourthPlayer);

		frame.setVisible(true);
	}

	private void fillPlayer(EnsJoueurs joueursSelectionnes)
	{
		/*On vérifie que chaque joueur sélectionné n'a pas déjà été éliminé, si ce n'est pas le cas
		on affiche son nom*/
		if(joueursSelectionnes.getVectorJoueurs().get(0).getEtat().equals("elimine"))
		{
			firstPlayer = new JLabel(" ");
		}
		else
		{
			firstPlayer = new JLabel("1 - "+joueursSelectionnes.getVectorJoueurs().get(0).getNom()+" : "+joueursSelectionnes.getVectorJoueurs().get(0).getScore()+" en "+joueursSelectionnes.getVectorJoueurs().get(0).getTimer().getTimerValue()+" ms ");
			firstPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		}

		if(joueursSelectionnes.getVectorJoueurs().get(1).getEtat().equals("elimine"))
		{
			secondPlayer = new JLabel(" ");
		}
		else
		{
			secondPlayer = new JLabel("2 - "+joueursSelectionnes.getVectorJoueurs().get(1).getNom()+" : "+joueursSelectionnes.getVectorJoueurs().get(1).getScore()+" en "+joueursSelectionnes.getVectorJoueurs().get(1).getTimer().getTimerValue()+" ms ");
			secondPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		}

		if(joueursSelectionnes.getVectorJoueurs().get(2).getEtat().equals("elimine"))
		{
			thirdPlayer = new JLabel(" ");
		}
		else
		{
			thirdPlayer = new JLabel("3 - "+joueursSelectionnes.getVectorJoueurs().get(2).getNom()+" : "+joueursSelectionnes.getVectorJoueurs().get(2).getScore()+" en "+joueursSelectionnes.getVectorJoueurs().get(2).getTimer().getTimerValue()+" ms ");
			thirdPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		}

		if(joueursSelectionnes.getVectorJoueurs().get(3).getEtat().equals("elimine"))
		{
			fourthPlayer = new JLabel(" ");
		}
		else
		{
			fourthPlayer = new JLabel("4 - "+joueursSelectionnes.getVectorJoueurs().get(3).getNom()+" : "+joueursSelectionnes.getVectorJoueurs().get(3).getScore()+" en "+joueursSelectionnes.getVectorJoueurs().get(3).getTimer().getTimerValue()+" ms ");
			fourthPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		}
	}

}