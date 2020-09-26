import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PseudoWindow 
{
	private JFrame frmQuizzfriend;
	
	private static EnsJoueurs joueursSelectionnes;
	
	public static EnsJoueurs getJoueurs()
	{
		return joueursSelectionnes;
	}
	
	public static EnsJoueurs choisirJoueurs()
	{
		EnsJoueurs ensJoueurs = new EnsJoueurs(); //on r�cup�re un vecteur de 20 joueurs
		EnsJoueurs joueursSelect = new EnsJoueurs(); //copie de ce vecteur
		ensJoueurs.Creer(); //on assigne � chaque veecteur, un jour de nom compris entre A et Z
		for(int i=1;i<5;i++){
			Joueur selection = ensJoueurs.SelectionnerJoueur(); //Chaque joueur est choisir al�atoirement
			ensJoueurs.removeJoueur(selection.getNumero()); //Le joueur choisi est supprim� pour ne pas �tre s�lectionn� deux fois
			joueursSelect.JoueursSelectionnes(selection);  //la copie du vecteur r�cup�re les joueurs s�lectionn�s
		}
		return joueursSelect;
	}
	
    /**
     * Create the application.
     */
	public PseudoWindow(HashMap<String, ListeQuestions> allQuestions, Themes objetTheme) {
		initialize(allQuestions, objetTheme);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(HashMap<String, ListeQuestions> allQuestions, Themes objetTheme) {

		frmQuizzfriend = new JFrame();
		frmQuizzfriend.setTitle("QUIZZ'FRIEND");
		frmQuizzfriend.setBounds(100, 100, 435, 386);
		frmQuizzfriend.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmQuizzfriend.getContentPane().setLayout(new GridLayout(6, 1, 0, 0));
		frmQuizzfriend.setLocationRelativeTo(null);
		joueursSelectionnes = choisirJoueurs(); //on r�cup�re les 4 joueurs s�lectionn�s
		
		JLabel lblNewLabel_1 = new JLabel("Les 4 joueurs selectionnes sont :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		frmQuizzfriend.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel(joueursSelectionnes.getVectorJoueurs().get(0).getNom());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frmQuizzfriend.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel(joueursSelectionnes.getVectorJoueurs().get(1).getNom());
		label.setHorizontalAlignment(SwingConstants.CENTER);
		frmQuizzfriend.getContentPane().add(label);
		
		JLabel label_1 = new JLabel(joueursSelectionnes.getVectorJoueurs().get(2).getNom());
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		frmQuizzfriend.getContentPane().add(label_1);

		JLabel label_2 = new JLabel(joueursSelectionnes.getVectorJoueurs().get(3).getNom());
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		frmQuizzfriend.getContentPane().add(label_2);
		
		JButton btnNewButton = new JButton("JOUER"); //Bouton pour jouer
		
		btnNewButton.addActionListener(arg0 -> {
			JOptionPane.showMessageDialog(null, "Si votre pseudo apparait en haut � gauche d'une question, c'est � vous de r�pondre ! Bonne chance !");
			new QCMWindow(0,joueursSelectionnes, allQuestions, objetTheme.selectTheme(), objetTheme, 1); //D�marrage de la phase 1
			frmQuizzfriend.setVisible(false);
		});
		frmQuizzfriend.getContentPane().add(btnNewButton);
		frmQuizzfriend.setVisible(true);
	}
}