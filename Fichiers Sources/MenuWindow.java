import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.io.*;
import java.util.HashMap;

public class MenuWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				MenuWindow window = new MenuWindow();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuWindow(){
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Themes objetTheme = new Themes();
		
		frame = new JFrame();
		
		frame.setBounds(100, 100, 532, 367);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(4, 1, 0, 0));
		frame.setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("QUIZ'FRIEND");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("JOUER");
		btnNewButton.addActionListener(e -> {
			new PseudoWindow(initQuestionSerialize(), objetTheme);
			frame.setVisible(false);
		});
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("MODERATION");
		btnNewButton_1.addActionListener(e -> {
			new ModerationWindow(objetTheme, initQuestionDeserialize());
			frame.dispose();
		});
		frame.getContentPane().add(btnNewButton_1);
		JButton btnNewButton_2 = new JButton("QUITTER");
		btnNewButton_2.addActionListener(e -> {
			frame.dispose();
			System.exit(0);
		});
		frame.getContentPane().add(btnNewButton_2);
		frame.setVisible(true);
	}

	private HashMap<String, ListeQuestions> initQuestionSerialize(){
		//MUSIQUE
		//1ere difficulté
		Question<QCM> q0 = new Question<>("Musique", new QCM("Comment s'appelle le premier album de Michael Jackson?", "Dangerous-1991", "Bad-1987", "Got to be there-1972", "Got to be there-1972"), 1);
		Question<VF> q1 = new Question<>("Musique", new VF("L'octobasse peut avoir une taille de 4 metres:", true), 1);
		Question<RC> q2 = new Question<>("Musique", new RC("Quel est le style de musique venant des communautes afro-americaines a la fin du XIXe siecle?", "jazz"), 1);
		Question<QCM> question = new Question<>("Musique", new QCM("Laquelle de ces chansons appartient a 2Pac?", "Juicy", "Can't C Me", "Without Me", "Can't C Me"), 1);
		Question<VF> question1 = new Question<>("Musique", new VF("Un accord est compose d'au moins 2 notes ", false), 1);
		Question<RC> question2 = new Question<>("Musique", new RC("Remplir les paroles manquantes : Je me presente je m'appelle Henry, je ... ma vie", "voudrais bien reussir"), 1);

		//2e difficultÃ©
		Question<QCM> q0_1 = new Question<>("Musique", new QCM("De quel handicap Ludwig van Beethoven fut-il atteint au cours de sa vie ?", "Demence", "Cecite", "Surdite", "Surdite"), 2);
		Question<VF> q1_1 = new Question<>("Musique", new VF("En 1919 a ete cree un instrument permettant de jouer sans les mains:", true), 2);
		Question<RC> q2_1 = new Question<>("Musique", new RC("Quel est le nom du degre IV?", "sous-dominante"), 2);
		Question<QCM> question3 = new Question<>("Musique", new QCM("Comment s'appelle l'instrument qui a servi a l'enregistrement de la bande annonce de Breaking Bad?", "Cithare", "Handpan", "Cigar Box", "Cigar Box"), 2);
		Question<VF> question4 = new Question<>("Musique", new VF("Bono est l'artiste la plus deteste au monde", true), 2);
		Question<RC> question5 = new Question<>("Musique", new RC("Sur quel album de Nirvana peut-on voir un bebe tout nu dans une piscine ?", "nevermind"), 2);

		//3e difficultÃ©
		Question<QCM> q0_2 = new Question<>("Musique", new QCM("Comment la malediction de la 9e symphonie est-elle cense se traduire pour les malheureux compositeurs qu'elle touche ?", "Leur 9e symphonie sera vouee a l'echec", "Leur 9e symphonie restera inachevee", "Leur 9e symphonie sera leur derniere", "Leur 9e symphonie sera leur derniere"), 3);
		Question<VF> q1_2 = new Question<>("Musique", new VF("Les premieres traces de musiques remontent a  plus de 150 000 ans:", false), 3);
		Question<RC> q2_2 = new Question<>("Musique", new RC("De quelle majeure la gamme de sol mineur est-elle la relative?", "sib majeur"), 3);
		Question<QCM> question6 = new Question<>("Musique", new QCM("Dans combien de films la musique Alright de Supergrass est-elle diffusee ?", "2", "5", "9", "9"), 3);
		Question<VF> question7 = new Question<>("Musique", new VF("Le bluegrass est un type de musique derive de la country", true), 3);
		Question<RC> question8 = new Question<>("Musique", new RC("J'ai ete exclu du plus grand groupe de metal pour ensuite en creer un tout aussi connu, qui suis-je ?", "dave mustaine"), 3);

		ListeQuestions myListMusique = new ListeQuestions("Musique");
		myListMusique.AjouterQuestion(q0);
		myListMusique.AjouterQuestion(q1);
		myListMusique.AjouterQuestion(q2);
		myListMusique.AjouterQuestion(question);
		myListMusique.AjouterQuestion(question1);
		myListMusique.AjouterQuestion(question2);
		myListMusique.AjouterQuestion(q0_1);
		myListMusique.AjouterQuestion(q1_1);
		myListMusique.AjouterQuestion(q2_1);
		myListMusique.AjouterQuestion(question3);
		myListMusique.AjouterQuestion(question4);
		myListMusique.AjouterQuestion(question5);
		myListMusique.AjouterQuestion(q0_2);
		myListMusique.AjouterQuestion(q1_2);
		myListMusique.AjouterQuestion(q2_2);
		myListMusique.AjouterQuestion(question6);
		myListMusique.AjouterQuestion(question7);
		myListMusique.AjouterQuestion(question8);

		//HISTOIRE
		//1ere difficulte
		Question<QCM> q3 = new Question<>("Histoire", new QCM("En quelle annee a eu lieu la revolution francaise?", "1792", "1830", "1789", "1789" ),1);
		Question<VF> q4 = new Question<>("Histoire", new VF("Napoleon faisait vraiment moins d'1m60", false),1);
		Question<RC> q5 = new Question<>("Histoire", new RC("Qui a decouvert l'amerique?", "christophe colomb"), 1);
		Question<QCM> ques0  = new Question<>("Histoire", new QCM("J'ai ete empereur au XIXe siecle. Qui suis-je ?", "Napoleon Ier", "Jules Cesar", "Charlemagne", "Napoleon Ier"), 1);
		Question<VF> ques1 = new Question<>("Histoire", new VF("J'ai construit la tour Eiffel (1887-1889). Je m'appelle Leon Eiffel", false), 1);
		Question<RC> ques2 = new Question<>("Histoire", new RC("On m'a prise pour une sorciere, et j'ai ete brulee vive pour cela le 30 mai 1431. Qui suis-je ?", "jeanne d'arc"), 1);

		//2e difficulte
		Question<QCM> q3_1 = new Question<>("Histoire", new QCM("En quelle annee, Mao a-t-il lance sa revolution culturelle?", "1814", "1920", "1966", "1966"), 2);
		Question<VF> q4_1 = new Question<>("Histoire", new VF("Jeanne d'Arc etait bergere:", false), 2);
		Question<RC> q5_1 = new Question<>("Histoire", new RC("Qui fut le premier souverain de Russie a porter le titre de tsar?", "ivan iv"), 2);
		Question<QCM> ques3  = new Question<>("Histoire", new QCM("On me surnommait : Le Roi Soleil. Qui suis-je ?", "Hamlet", "Louis XIV", "Clovis Ier", "Louis XIV"), 2);
		Question<VF> ques4 = new Question<>("Histoire", new VF("J'ai vecu a Camelot, et ma femme s'appelait Guenievre. Je suis Robin des Bois", false),2);
		Question<RC> ques5 = new Question<>("Histoire", new RC("Nous avons joue un role primordial dans l'histoire du cinema, il y a plus d'un siecle deja. Qui sommes-nous ?", "les freres lumiere"),2);

		//3e difficulte
		Question<QCM> q3_2 = new Question<>("Histoire", new QCM("Qui etait contre leur general des finances sous Louis XIV?", "Colbert", "Sully", "Le maitre", "Colbert" ),3);
		Question<VF> q4_2 = new Question<>("Histoire", new VF("Napoleon III s'est fait couronner empereur des Francais en 1804:", false),3);
		Question<RC> q5_2 = new Question<>("Histoire", new RC("A quelle tribu d'Indiens appartenait Geronimo?", "apache"),3);
		Question<QCM> ques6  = new Question<>("Histoire", new QCM("J'ai fait exterminer des generations de juifs pendant la Seconde Guerre mondiale. Qui suis-je ?", "Hitler", "LCharles de Gaulle", "Woodrow Wilson", "Hitler" ),3);
		Question<VF> ques7 = new Question<>("Histoire", new VF("Ce jour-la, des avions se sont ecrases sur les deux tours jumelles. C'etait le 11 mai 2001", false),3);
		Question<RC> ques8 = new Question<>("Histoire", new RC("Quel fruit a inspire a Isaac Newton sa plus celebre theorie ?", "une pomme"),3);

		ListeQuestions myListHistoire = new ListeQuestions("Histoire");
		myListHistoire.AjouterQuestion(q3);
		myListHistoire.AjouterQuestion(q4);
		myListHistoire.AjouterQuestion(q5);
		myListHistoire.AjouterQuestion(q3_1);
		myListHistoire.AjouterQuestion(q4_1);
		myListHistoire.AjouterQuestion(q5_1);
		myListHistoire.AjouterQuestion(q3_2);
		myListHistoire.AjouterQuestion(q4_2);
		myListHistoire.AjouterQuestion(q5_2);
		myListHistoire.AjouterQuestion(ques0);
		myListHistoire.AjouterQuestion(ques1);
		myListHistoire.AjouterQuestion(ques2);
		myListHistoire.AjouterQuestion(ques3);
		myListHistoire.AjouterQuestion(ques4);
		myListHistoire.AjouterQuestion(ques5);
		myListHistoire.AjouterQuestion(ques6);
		myListHistoire.AjouterQuestion(ques7);
		myListHistoire.AjouterQuestion(ques8);


		//SPORT
		//1e difficulte
		Question<QCM> q6 = new Question<>("Sport", new QCM("En quelle annee l'AS Monaco est sorti en demi-finale contre la Juventus en LDC?", "2017", "2004", "2010", "2017" ),1);
		Question<VF> q7 = new Question<>("Sport", new VF("Usain Bolt est toujours champion du monde du 100m:", true),1);
		Question<RC> q8 = new Question<>("Sport", new RC("Qui etait l'entraineur des bleus lors du mondial 2006?", "raymond domenech"),1);
		Question<QCM> ques9 = new Question<>("Sport", new QCM("Qui ne joue PAS au football ?", "Paolo Maldini", "Jean-Pierre Papin", "Kenny Roberts", "Kenny Roberts" ),1);
		Question<VF> ques10 = new Question<>("Sport", new VF("Jackson Richardson joue au basket-ball", false),1);
		Question<RC> ques11 = new Question<>("Sport", new RC("Combien de joueurs sont sur un demi terrain de volley ?", "6"),1);

		//2e difficulte
		Question<QCM> q6_1 = new Question<>("Sport", new QCM("Dans quel sport s'est illustre Nigel Mansel?", "Au tennis", "Au hockey", "En Formule 1", "En Formule 1" ),2);
		Question<VF> q7_1 = new Question<>("Sport", new VF("En competition, le velo de piste est equipe de 3 freins:", false),2);
		Question<RC> q8_1 = new Question<>("Sport", new RC("Combien d'etoiles l'equipe bresilienne arbore sur son maillot?", "5"),2);
		Question<QCM> ques12 = new Question<>("Sport", new QCM("Qui ne pratique PAS le judo ?", "David Douillet", "Philippe Boisse", "Marie-Claire Restoux", "Philippe Boisse" ),2);
		Question<VF> ques13= new Question<>("Sport", new VF("Ian Woosnam joue au tennis ", false),2);
		Question<RC> ques14 = new Question<>("Sport", new RC("Combien constitue une equipe de handball ?", "7"),2);

		//3e difficulte
		Question<QCM> q6_2 = new Question<>("Sport", new QCM("A quelle hauteur se trouve le filet de volley pour une competition masculine?", "1,80m", "2,24m", "2,43m", "2,43m" ),3);
		Question<VF> q7_2 = new Question<>("Sport", new VF("L'equipe feminine de volley-ball du Japon a ete sacre championne olympique aux JO de Londres en 2012:", false),3);
		Question<RC> q8_2 = new Question<>("Sport", new RC("Combien de fois, Alain Prost a-t-il ete champion du monde de Formule 1 ?", "4"),3);
		Question<QCM> ques15 = new Question<>("Sport", new QCM("Qui ne pratique PAS le motocyclisme ?", "Valentino Rossi", "Joe Montana", "Barry Sheene", "Joe Montana" ),3);
		Question<VF> ques16 = new Question<>("Sport", new VF("Mark Spitz ne pratique PAS le ski ?", true),3);
		Question<RC> ques17 = new Question<>("Sport", new RC("Combien un parcours de golf  normal a-t-il de trous ?", "18"),3);

		ListeQuestions myListSport = new ListeQuestions("Sport");
		myListSport.AjouterQuestion(q6);
		myListSport.AjouterQuestion(q7);
		myListSport.AjouterQuestion(q8);
		myListSport.AjouterQuestion(q6_1);
		myListSport.AjouterQuestion(q7_1);
		myListSport.AjouterQuestion(q8_1);
		myListSport.AjouterQuestion(q6_2);
		myListSport.AjouterQuestion(q7_2);
		myListSport.AjouterQuestion(q8_2);
		myListSport.AjouterQuestion(ques9);
		myListSport.AjouterQuestion(ques10);
		myListSport.AjouterQuestion(ques11);
		myListSport.AjouterQuestion(ques12);
		myListSport.AjouterQuestion(ques13);
		myListSport.AjouterQuestion(ques14);
		myListSport.AjouterQuestion(ques15);
		myListSport.AjouterQuestion(ques16);
		myListSport.AjouterQuestion(ques17);


		//CINEMA
		//1e difficultÃ©
		Question<QCM> q9 = new Question<>("Cinema", new QCM("Qui est le realisateur du film Interstellar (2014) ?", "Christopher Nolan", "Quentin Tarantino", "Steven Spielberg", "Christopher Nolan"), 1);
		Question<VF> q10 = new Question<>("Cinema", new VF("Tom Hanks a joue dans Forrest Gump(1994):", true), 1);
		Question<RC> q11 = new Question<>("Cinema", new RC("Quel acteur est repute pour pouvoir facilement perdre ou gagner du poids pour ces roles?", "christian Bale"), 1);
		Question<QCM> question9 = new Question<>("Cinema", new QCM("Quel est l'acteur incarnant Paul Edgecombe dans la ligne Verte ?", "Tom Hanks", "Leonardo DiCaprio", "Brad Pitt", "Tom Hanks"), 1);
		Question<VF> question10 = new Question<>("Cinema", new VF("Jack meurt a la fin de Titanic", true), 1);
		Question<RC> question11 = new Question<>("Cinema", new RC("En quelle annee est sorti Pulp Fiction?", "1994"), 1);

		//2e difficultÃ©
		Question<QCM> q9_1 = new Question<>("Cinema", new QCM("Quelle actrice incarne Mary Jane Watson dans la trilogie Spider-Man?", "Kirsten Dunst", "Cate Blanchett", "Diane Kruger", "Kirsten Dunst"), 2);
		Question<VF> q10_1 = new Question<>("Cinema", new VF("James Cameron a commence a travailler sur Titanic avant Avatar:", false), 2);
		Question<RC> q11_1 = new Question<>("Cinema", new RC("Quel acteur joue un reparateur de drones dans le film « Oblivion »?", "tom cruise"), 2);
		Question<QCM> question12 = new Question<>("Cinema", new QCM("Quel film relate la vie d'un jeune balayeur au MIT genie des mathematiques ?", "Jason Bourne", "Will Hunting", "Ocean's Eleven", "Will Hunting"), 2);
		Question<VF> question13 = new Question<>("Cinema", new VF("Hitchock a realise Orange Mecanique", false), 2);
		Question<RC> question14 = new Question<>("Cinema", new RC("Quel teur en serie a fait l'objet d'un film Netflix?", "ted bundy"), 2);

		//3e difficultÃ©
		Question<QCM> q9_2 = new Question<>("Cinema", new QCM("Qui a realise le film « Les Aventures de Rabbi Jacob»?", "Eric Rohmer", "Claude Miller", "Gerard Oury", "Gerard Oury"), 3);
		Question<VF> q10_2 = new Question<>("Cinema", new VF("Le film 'Moulin Rouge' s'inspire du livre d'Alexandre Dumas 'La Dame au Camelias':", true), 3);
		Question<RC> q11_2 = new Question<>("Cinema", new RC("Qui a realise le film « Raging Bull »?", "martin Scorsese"), 3);
		Question<QCM> question15 = new Question<>("Cinema", new QCM("Dans quel film n'a pas joue Louis de Funes ?", "La Grande Vadrouille", "Les Aventures de Rabbi Jacob", "La Souple a l'oignon", "La soupe a l'oignon"), 3);
		Question<VF> question16 = new Question<>("Cinema", new VF("Les pop-corns vendus au cinema sont au minimum de 3€ l'unite", true), 3);
		Question<RC> question17 = new Question<>("Cinema", new RC("Quel groupe de 3 freres a joue dans Camp Rock?", "jonas brothers"), 3);


		ListeQuestions myListCinema = new ListeQuestions("Cinema");
		myListCinema.AjouterQuestion(q9);
		myListCinema.AjouterQuestion(q10);
		myListCinema.AjouterQuestion(q11);
		myListCinema.AjouterQuestion(question9);
		myListCinema.AjouterQuestion(question10);
		myListCinema.AjouterQuestion(question11);
		myListCinema.AjouterQuestion(q9_1);
		myListCinema.AjouterQuestion(q10_1);
		myListCinema.AjouterQuestion(q11_1);
		myListCinema.AjouterQuestion(question12);
		myListCinema.AjouterQuestion(question13);
		myListCinema.AjouterQuestion(question14);
		myListCinema.AjouterQuestion(q9_2);
		myListCinema.AjouterQuestion(q10_2);
		myListCinema.AjouterQuestion(q11_2);
		myListCinema.AjouterQuestion(question15);
		myListCinema.AjouterQuestion(question16);
		myListCinema.AjouterQuestion(question17);

		//MONDE
		//1e difficulte
		Question<QCM> q12 = new Question<>("Monde", new QCM("Quelle est la taille de la tour Eiffel?", "130m", "260m", "300m", "300m" ),1);
		Question<VF> q13 = new Question<>("Monde", new VF("La Taj-Mahal n'est pas un palais mais un mausole:", true),1);
		Question<RC> q14 = new Question<>("Monde", new RC("Quelle est la capitale de l'Indonesie?", "jakarta"),1);
		Question<QCM> ques18 = new Question<>("Monde", new QCM("L'Afrique du Sud a pour capitale(s)...", "Le Cap, Bloemfontein et Pretoria", "Le Cap", "Aucune", "Le Cap, Bloemfontein et Pretoria" ),1);
		Question<VF> ques19 = new Question<>("Monde", new VF("La baie d'Hudson se situe aux Etats Unis", false),1);
		Question<RC> ques20= new Question<>("Monde", new RC("Je suis l'Ile Gulf. Ou suis-je ?", "au canada"),1);

		//2e difficulte
		Question<QCM> q12_1 = new Question<>("Monde", new QCM("A quelle region est rattache le departement de la Lozere?", "Le Languedoc-Roussillon", "Le Centre", "L'ÃŽle de France", "Le Languedoc-Roussillon" ),2);
		Question<VF> q13_1 = new Question<>("Monde", new VF("La langue maternelle la plus parlee dans le monde est le mandarin:", true),2);
		Question<RC> q14_1 = new Question<>("Monde", new RC("Le ... est la langue officielle de neuf pays.", "portugais"),2);
		Question<QCM> ques21 = new Question<>("Monde", new QCM("Quelle est la capitale du Qatar ?", "Mascate", "Doha", "Manama", "Doha" ),2);
		Question<VF> ques22 = new Question<>("Monde", new VF("Myanmar se situe sur le continent d'Afrique.", false),2);
		Question<RC> ques23 = new Question<>("Monde", new RC("Bangkok est la capitale de la...", "thaïlande"),2);

		//3e difficulte
		Question<QCM> q12_2 = new Question<>("Monde", new QCM("Dans quelle ville italienne se trouve la tour Pirelli?", "Florence", "Turin", "Milan", "Milan" ),3);
		Question<VF> q13_2 = new Question<>("Monde", new VF("Madagascar est un pays insulaire situe a l'est de l'Afrique:", true),3);
		Question<RC> q14_2 = new Question<>("Monde", new RC("La muraille de Chine protegeait la Chine de la ... ?", "mongolie"),3);
		Question<QCM> ques24 = new Question<>("Monde", new QCM("Quelle est la capitale du Tadjikistan ?", "Astana", "Bichkek", "Douchanbe", "Douchanbe" ),3);
		Question<VF> ques25 = new Question<>("Monde", new VF("Edmonton se situe au Canada", true),3);
		Question<RC> ques26 = new Question<>("Monde", new RC("Quelle est la capitale du Paraguay ?", "asuncion"),3);


		ListeQuestions myListMonde = new ListeQuestions("Monde");
		myListMonde.AjouterQuestion(q12);
		myListMonde.AjouterQuestion(q13);
		myListMonde.AjouterQuestion(q14);
		myListMonde.AjouterQuestion(q12_1);
		myListMonde.AjouterQuestion(q13_1);
		myListMonde.AjouterQuestion(q14_1);
		myListMonde.AjouterQuestion(q12_2);
		myListMonde.AjouterQuestion(q13_2);
		myListMonde.AjouterQuestion(q14_2);
		myListMonde.AjouterQuestion(ques18);
		myListMonde.AjouterQuestion(ques19);
		myListMonde.AjouterQuestion(ques20);
		myListMonde.AjouterQuestion(ques21);
		myListMonde.AjouterQuestion(ques22);
		myListMonde.AjouterQuestion(ques23);
		myListMonde.AjouterQuestion(ques24);
		myListMonde.AjouterQuestion(ques25);
		myListMonde.AjouterQuestion(ques26);


		//CELEBRITE
		//1e difficulté
		Question<QCM> q15 = new Question<>("Celebrites", new QCM("Qui ne fait pas partie de la famille Kardashian?", "Kylie Jenner", "KhloÃ© Kardashian", "Steven Kardashian", "Steven Kardashian"), 1);
		Question<VF> q16 = new Question<>("Celebrites", new VF("Brad Pitt et Angelina Jolie se sont rencontres sur un tournage:", true), 1);
		Question<RC> q17 = new Question<>("Celebrites", new RC("Quel est le prenom de Lady Gaga?", "stefani"), 1);
		Question<QCM> question18 = new Question<>("Celebrites", new QCM("Quel acteur a gagne un Golden Globe pour le role de Walter White dans la serie Breaking Bad ?", "Bryan Cranston", "Bryan Adams", "Bryan May", "Bryan Cranston"), 1);
		Question<VF> question19 = new Question<>("Celebrites", new VF("Ellen Degeneres est une cousine eloignee de Kate Middleton", true), 1);
		Question<RC> question20 = new Question<>("Celebrites", new RC("Quel actrice a refuse le role de Rose dans Titanic ?", "gwyneth paltrow"), 1);

		//2e difficulté
		Question<QCM> q15_1 = new Question<>("Celebrites", new QCM("Comment s'appelle la mere de Kendall et de Kylie Jenner?", "Caitlyn", "Bruce", "Christian", "Caitlyn"), 2);
		Question<VF> q16_1 = new Question<>("Celebrites", new VF("Amel Bent a ete revelee par l'emission 'Danse avec les Stars':", false), 2);
		Question<RC> q17_1 = new Question<>("Celebrites", new RC("Martina Stoessel joue dans la serie:", "violetta"), 2);
		Question<QCM> question21 = new Question<>("Celebrites", new QCM("Quel acteur aurait du incarner le role de Neo dans Matrix ?", "Will Smith", "Brad Pitt", "Chris Hemsworth", "Will Smith"), 2);
		Question<VF> question22 = new Question<>("Celebrites", new VF("Le vrai prenom de Chuck Norris est Carlos", true), 2);
		Question<RC> question23 = new Question<>("Celebrites", new RC("Quel celebre acteur de mission impossible a renonce a une carriere de pretre ?", "tom cruise"), 2);

		//3e difficulté
		Question<QCM> q15_2 = new Question<>("Celebrites", new QCM("Combien Will Smith a-t-il d'enfants?", "4", "2", "1", "2"), 3);
		Question<VF> q16_2 = new Question<>("Celebrites", new VF("Dans l'emission Camp Rock, Demi Lovato chante avec les One Direction:", true), 3);
		Question<RC> q17_2 = new Question<>("Celebrites", new RC("Quel rappeur americain montre sa performance pendant son concert sur une plate-forme au-dessus des fans?", "kanye west"), 3);
		Question<QCM> question24 = new Question<>("Celebrites", new QCM("Qui etait l'acteur prefere du rappeur Tupac ?", "Al Pacino", "Jim Carrey", "Robert de Niro", "Jim Carrey"), 3);
		Question<VF> question25 = new Question<>("Celebrites", new VF("Les acteurs principaux de The Big Bang Theory gagnent 1 million de dollars par episode", true), 3);
		Question<RC> question26 = new Question<>("Celebrites", new RC("Quelle actrice de Gossip Girl est nee en prison ?", "leighton meester"), 3);

		ListeQuestions myListCelebrite = new ListeQuestions("Celebrites");
		myListCelebrite.AjouterQuestion(q15);
		myListCelebrite.AjouterQuestion(q16);
		myListCelebrite.AjouterQuestion(q17);
		myListCelebrite.AjouterQuestion(question18);
		myListCelebrite.AjouterQuestion(question19);
		myListCelebrite.AjouterQuestion(question20);
		myListCelebrite.AjouterQuestion(q15_1);
		myListCelebrite.AjouterQuestion(q16_1);
		myListCelebrite.AjouterQuestion(q17_1);
		myListCelebrite.AjouterQuestion(question21);
		myListCelebrite.AjouterQuestion(question22);
		myListCelebrite.AjouterQuestion(question23);
		myListCelebrite.AjouterQuestion(q15_2);
		myListCelebrite.AjouterQuestion(q16_2);
		myListCelebrite.AjouterQuestion(q17_2);
		myListCelebrite.AjouterQuestion(question24);
		myListCelebrite.AjouterQuestion(question25);
		myListCelebrite.AjouterQuestion(question26);


		//CUISINE
		//1e difficulte
		Question<QCM> q18 = new Question<>("Cuisine", new QCM("Que signifie l'action de monder une tomate?", "l'eplucher", "la manger", "la faire cuire", "l'eplucher" ),1);
		Question<VF> q19 = new Question<>("Cuisine", new VF("Les pommes sont originaires d'Asie:", true),1);
		Question<RC> q20 = new Question<>("Cuisine", new RC("Comment s'appelle l'arbre qui donne des fraises?", "fraisier"),1);
		Question<QCM> question27 = new Question<>("Cuisine", new QCM("De quel pays la poutine est-elle la specialite", "Etats-Unis", "Canada", "Russie", "Canada"), 1);
		Question<VF> question28 = new Question<>("Cuisine", new VF("Les canneles sont faits avec de la tequila", false), 1);
		Question<RC> question29 = new Question<>("Cuisine", new RC("Quel condiment permet aux aliments de gonfler a la cuisson ?", "levure"), 1);

		//2e difficulte
		Question<QCM> q18_1 = new Question<>("Cuisine", new QCM("Quels sont les prenoms de 2 freres McDonald qui ont cree la celebre chaine du meme nom?", "Richard et Maurice", "Michael et Tom", "Anthony et Bradley", "Richard et Maurice" ),2);
		Question<VF> q19_1 = new Question<>("Cuisine", new VF("Le Baeckeoffe est une specialite de Picardie:", false),2);
		Question<RC> q20_1 = new Question<>("Cuisine", new RC("La harissa est une puree de ...", "piments"),2);
		Question<QCM> question30 = new Question<>("Cuisine", new QCM("La recette originale de pates carbonara est a base de", "Mayonnaise", "Creme fraiche", "Oeufs", "Oeufs"), 2);
		Question<VF> question31 = new Question<>("Cuisine", new VF("Un restaurant peut posseder 4 etoiles", false), 2);
		Question<RC> question32 = new Question<>("Cuisine", new RC("Quel est l'ingredient principal du Nougat ?", "miel"), 2);

		//3e difficulte
		Question<QCM> q18_2 = new Question<>("Cuisine", new QCM("A quel chef le film 'Ratatouille' rend-il hommage?", "Bernard Loiseau", "Jamie Oliver", "Michel Guerard", "Bernard Loiseau" ),3);
		Question<VF> q19_2 = new Question<>("Cuisine", new VF("Dans la sauce bearnaise on trouve de l'estragon:", true),3);
		Question<RC> q20_2 = new Question<>("Cuisine", new RC("Le tofu est fait de lait de ...", "soja"),3);
		Question<QCM> question33 = new Question<>("Cuisine", new QCM("De quel pays le hamburger est-il originaire ?", "Etats-Unis", "France", "Allemagne", "Allemagne"), 3);
		Question<VF> question34 = new Question<>("Cuisine", new VF("La recette des macarons necessite des amandes", true), 3);
		Question<RC> question35 = new Question<>("Cuisine", new RC("A quelle periode de l'anne peut on voir pousser des fraises", "printemps"), 3);

		ListeQuestions myListCuisine = new ListeQuestions("Cuisine");
		myListCuisine.AjouterQuestion(q18);
		myListCuisine.AjouterQuestion(q19);
		myListCuisine.AjouterQuestion(q20);
		myListCuisine.AjouterQuestion(question27);
		myListCuisine.AjouterQuestion(question28);
		myListCuisine.AjouterQuestion(question29);
		myListCuisine.AjouterQuestion(q18_1);
		myListCuisine.AjouterQuestion(q19_1);
		myListCuisine.AjouterQuestion(q20_1);
		myListCuisine.AjouterQuestion(question30);
		myListCuisine.AjouterQuestion(question31);
		myListCuisine.AjouterQuestion(question32);
		myListCuisine.AjouterQuestion(q18_2);
		myListCuisine.AjouterQuestion(q19_2);
		myListCuisine.AjouterQuestion(q20_2);
		myListCuisine.AjouterQuestion(question33);
		myListCuisine.AjouterQuestion(question34);
		myListCuisine.AjouterQuestion(question35);

		//ANIMAUX
		//1e difficultÃ©
		Question<QCM> q21 = new Question<>("Animaux", new QCM("Quel mammifere marin est un cousin de l'elephant?", "la baleine", "l'elephant de mer", "le lamantin", "le lamantin" ),1);
		Question<VF> q22 = new Question<>("Animaux", new VF("Le papillon monarque peut voler sur 4000km:", true),1);
		Question<RC> q23 = new Question<>("Animaux", new RC("Quel animal vit dans un clapier?", "lapin"),1);
		Question<QCM> question45 = new Question<>("Animaux", new QCM("Quel genre d'animal un entomologiste etudie-t-il ?", "les insectes", "les serpents", "les poissons", "les insectes" ),1);
		Question<VF> question46 = new Question<>("Animaux", new VF("La basenji est un chien qui n'aboie pas", true),1);
		Question<RC> question47 = new Question<>("Animaux", new RC("Dans quelle region de France peut-on trouver des ours bruns", "pyrenees"),1);
		//2e difficultÃ©
		Question<QCM> q21_1 = new Question<>("Animaux", new QCM("Quel animal vit dans une frayere?", "Le renard", "Le koala", "Le saumon", "Le saumon" ),2);
		Question<VF> q22_1 = new Question<>("Animaux", new VF("Un campagnol est une race de chien:", false),2);
		Question<RC> q23_1 = new Question<>("Animaux", new RC("Ou vit le diable de Tasmanie?", "australie"),2);
		Question<QCM> question48 = new Question<>("Animaux", new QCM("Quel es le seul felin qui ne peut rentrer ses griffes ?", "le chat", "le guepard", "le puma", "le guepard" ),2);
		Question<VF> question49 = new Question<>("Animaux", new VF("On trouve des dromadaires en liberte en Australie", true),2);
		Question<RC> question50 = new Question<>("Animaux", new RC("Quel animal vit dans un clapier?", "lapin"),2);
		//3e difficultÃ©
		Question<QCM> q21_2 = new Question<>("Animaux", new QCM("Ã€ quelle famille appartient le scorpion?", "Les gasteropodes", "Les arachnides", "Les myriapodes", "Les arachnides" ),3);
		Question<VF> q22_2 = new Question<>("Animaux", new VF("Un chinchilla est un rongeur:", true),3);
		Question<RC> q23_2 = new Question<>("Animaux", new RC("Le gnou appartient a la famille des ...", "bovides"),3);
		Question<QCM> question51 = new Question<>("Animaux", new QCM("Quel animal peut marcher une heure apres sa naissance ?", "le poussin", "le poulain", "le chat", "le poulain" ),3);
		Question<VF> question52 = new Question<>("Animaux", new VF("L'hippopotamme a la plus grande bouche des animaux terrestres'", true),3);
		Question<RC> question53 = new Question<>("Animaux", new RC("A quelle vitesse peut courir une girafe ? (en km/h)", "55"),3);


		ListeQuestions myListAnimaux = new ListeQuestions("Animaux");
		myListAnimaux.AjouterQuestion(q21);
		myListAnimaux.AjouterQuestion(q22);
		myListAnimaux.AjouterQuestion(q23);
		myListAnimaux.AjouterQuestion(question45);
		myListAnimaux.AjouterQuestion(question46);
		myListAnimaux.AjouterQuestion(question47);
		myListAnimaux.AjouterQuestion(q21_1);
		myListAnimaux.AjouterQuestion(q22_1);
		myListAnimaux.AjouterQuestion(q23_1);
		myListAnimaux.AjouterQuestion(question48);
		myListAnimaux.AjouterQuestion(question49);
		myListAnimaux.AjouterQuestion(question50);
		myListAnimaux.AjouterQuestion(q21_2);
		myListAnimaux.AjouterQuestion(q22_2);
		myListAnimaux.AjouterQuestion(q23_2);
		myListAnimaux.AjouterQuestion(question51);
		myListAnimaux.AjouterQuestion(question52);
		myListAnimaux.AjouterQuestion(question53);


		//SERIES TELEVISES
		//1e difficultÃ©
		Question<QCM> q24 = new Question<>("Series Televisees", new QCM("Quelle est la serie ayant connu un franc succes et pourtant un des pires denouements?", "Game of Thrones", "The Mentalist", "Mr. Robot", "Game of Thrones" ),1);
		Question<VF> q25 = new Question<>("Series Televisees", new VF("La serie Chernobyl est elle consideree comme une serie historique?", true),1);
		Question<RC> q26 = new Question<>("Series Televisees", new RC("Quelle serie raconte l'histoire d'un homme voulant faire evader son rere condamne a mort?", "prison break"),1);
		Question<QCM> question54 = new Question<>("Series Televisees", new QCM("Dans quel serie le personnage de Negan s'illustre-t-il ?", "Game of Thrones", "Sons of Anarchy", "The Walking Dead", "The Walking Dead" ),1);
		Question<VF> question55 = new Question<>("Series Televisees", new VF("La serie Grey's Anatomy compte 16 saisons a son actif ", true),1);
		Question<RC> question56 = new Question<>("Series Televisees", new RC("Quelle serie raconte l'histoire d'un auteur de romans policiers aidant la police a resoudre des crimes", "castle"),1);

		//2e difficultÃ©
		Question<QCM> q24_1 = new Question<>("Series Televisees", new QCM("Dans la serie a succes 'Colombo', quel est le prenom de notre cher detective ?", "Frank", "James", "Joe", "Frank" ),2);
		Question<VF> q25_1 = new Question<>("Series Televisees", new VF("La serie 'Smallville' raconte les jeunes annees de Spider-Man.", false),2);
		Question<RC> q26_1 = new Question<>("Series Televisees", new RC("Dans quelle serie americaine, les personnages principaux sont-elles des sorcieres ?", "charmed"),2);
		Question<QCM> question57 = new Question<>("Series Televisees", new QCM( "Quel acteur incarne Derek Morgan dans la serie Esprits Criminels ?", "Shailene Woodley", "Sheemar Moore", "Shia Leboeuf", "Sheemar Moore"),2);
		Question<VF> question58 = new Question<>("Series Televisees", new VF("Dans la serie Orange Is The New Black, Piper est enfermee dans la prison de Fox River", false),2);
		Question<RC> question59 = new Question<>("Series Televisees", new RC("Quel est le titre du generique des experts manhattan", "baba o'riley"),2);

		//3e difficultÃ©
		Question<QCM> q24_2 = new Question<>("Series Televisees", new QCM("Dans quel quartier de New York se deroule la sitcom Friends ? ", "Manhattan", "Brooklyn", "Williamsburg", "Manhattan"), 3);
		Question<VF> q25_2 = new Question<>("Series Televisees", new VF("La serie 'Dallas' compte 14 saisons:", true),3);
		Question<RC> q26_2 = new Question<>("Series Televisees", new RC("En quelle annee la serie 'Les Mysteres de l'Ouest' a-t-elle ete diffusee pour la premiere fois ?", "1965"),3);
		Question<QCM> question60 = new Question<>("Series Televisees", new QCM("Kaamelott est une serie ", "belge", "roumaine", "francaise", "francaise"),3);
		Question<VF> question61 = new Question<>("Series Televisees", new VF("Hugh Laurie incarne Dr House dans la serie du meme nom", true),3);
		Question<RC> question62 = new Question<>("Series Televisees", new RC("Qui est le createur de la serie House of Cards ?", "beau willimon"),3);


		ListeQuestions myListSeries = new ListeQuestions("Series Televisees");
		myListSeries.AjouterQuestion(q24);
		myListSeries.AjouterQuestion(q25);
		myListSeries.AjouterQuestion(q26);
		myListSeries.AjouterQuestion(question54);
		myListSeries.AjouterQuestion(question55);
		myListSeries.AjouterQuestion(question56);
		myListSeries.AjouterQuestion(q24_1);
		myListSeries.AjouterQuestion(q25_1);
		myListSeries.AjouterQuestion(q26_1);
		myListSeries.AjouterQuestion(question57);
		myListSeries.AjouterQuestion(question58);
		myListSeries.AjouterQuestion(question59);
		myListSeries.AjouterQuestion(q24_2);
		myListSeries.AjouterQuestion(q25_2);
		myListSeries.AjouterQuestion(q26_2);
		myListSeries.AjouterQuestion(question60);
		myListSeries.AjouterQuestion(question61);
		myListSeries.AjouterQuestion(question62);

		//LITTERATURE
		//1e difficultÃ©
		Question<QCM> q27 = new Question<>("Litterature", new QCM("Quel ecrivain s'est suicide?", "Charles Baudelaire", "Victor Hugo", "Virginia Woolf", "Virginia Woolf"), 1);
		Question<VF> q28 = new Question<>("Litterature", new VF("L'histoire de 'Madame Bovary' de Gustave Flaubert est inspiree de faits reels:", true), 1);
		Question<RC> q29 = new Question<>("Litterature", new RC("Quel ecrivain a ecrit 'Le Horla'?", "guy de maupassant"), 1);
		Question<QCM> question36 = new Question<>("Litterature", new QCM("Quel celebre ancien PDG a raconte son ascension sociale dans son roman L'art de la victoire ?", "Phil Knight", "Elon Musk", "Jeff Bezos", "Phil Knight"), 1);
		Question<VF> question37 = new Question<>("Litterature", new VF("Thomas Moore a ecrit Utopie", true), 1);
		Question<RC> question38 = new Question<>("Litterature", new RC("Qui a ecrit Le Petit prince ?", "antoine de saint-exupery"), 1);

		//2e difficultÃ
		Question<QCM> q27_1 = new Question<>("Litterature", new QCM("A quel auteur doit-on le roman Le Compte de Monte-Christo?", "Boris Pasternak", "Victor Hugo", "Alexandre Dumas", "Alexandre Dumas" ),2);
		Question<VF> q28_1 = new Question<>("Litterature", new VF("Honore de Balzac a ecrit Les illusions perdues", false),2);
		Question<RC> q29_1 = new Question<>("Litterature", new RC("Qui est l'auteur de l'art subtile de s'en foutre ?", "mark manson"),2);
		Question<QCM> question39 = new Question<>("Litterature", new QCM("Quel roman n'est pas de Stefan Zweig?", "La confusion des sentiments", "Conscience contre violence", "Claude Gueux", "Claude Gueux"), 2);
		Question<VF> question40 = new Question<>("Litterature", new VF("Lettres de mon moulin est un roman de Florent Pagny", false), 2);
		Question<RC> question41 = new Question<>("Litterature", new RC("Je suis un roman epistolaire entre deux amis", "les lettres persannes"), 2);

		//3e difficultÃ©
		Question<QCM> q27_2 = new Question<>("Litterature", new QCM("Quel auteur de polar a publie «Le Grand nulle part»?", "Raymond Chandler", "James Ellroy", "Michael Connelly", "James Ellroy"), 3);
		Question<VF> q28_2 = new Question<>("Litterature", new VF("Amelie Nothomb a ecrit la metaphysique des tubes", true), 3);
		Question<RC> q29_2 = new Question<>("Litterature", new RC("Qui est l'auteur de l'ouvreage Sapiens : une breve histoire de l'humanite ? ", "yuval noah harari"), 3);
		Question<QCM> question42 = new Question<>("Litterature", new QCM("Dans son ouvrage, l'Oeuvre, lequel de ses amis Zola decrit-il comme un peintre rate ?", "Monet", "Cezanne", "Manet", "Cezanne"),3);
		Question<VF> question43 = new Question<>("Litterature", new VF("Victor Hugo est mort avant Emile Zola", true),3);
		Question<RC> question44 = new Question<>("Litterature", new RC("Qui est le double malefique du Dr Jekyll ?", "m. hyde"),3);

		ListeQuestions myListLitterature = new ListeQuestions("Litterature");
		myListLitterature.AjouterQuestion(q27);
		myListLitterature.AjouterQuestion(q28);
		myListLitterature.AjouterQuestion(q29);
		myListLitterature.AjouterQuestion(question36);
		myListLitterature.AjouterQuestion(question37);
		myListLitterature.AjouterQuestion(question38);
		myListLitterature.AjouterQuestion(q27_1);
		myListLitterature.AjouterQuestion(q28_1);
		myListLitterature.AjouterQuestion(q29_1);
		myListLitterature.AjouterQuestion(question39);
		myListLitterature.AjouterQuestion(question40);
		myListLitterature.AjouterQuestion(question41);
		myListLitterature.AjouterQuestion(q27_2);
		myListLitterature.AjouterQuestion(q28_2);
		myListLitterature.AjouterQuestion(q29_2);
		myListLitterature.AjouterQuestion(question42);
		myListLitterature.AjouterQuestion(question43);
		myListLitterature.AjouterQuestion(question44);

		HashMap<String, ListeQuestions> allQuestions= new HashMap<>();

		allQuestions.put("Musique", myListMusique);
		allQuestions.put("Histoire", myListHistoire);
		allQuestions.put("Sport", myListSport);
		allQuestions.put("Cinema", myListCinema);
		allQuestions.put("Monde", myListMonde);
		allQuestions.put("Celebrites", myListCelebrite);
		allQuestions.put("Cuisine", myListCuisine);
		allQuestions.put("Animaux", myListAnimaux);
		allQuestions.put("Series Televisees", myListSeries);
		allQuestions.put("Litterature", myListLitterature);




		try
		{
			FileOutputStream fos =
					new FileOutputStream("HashMap_Questions.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(allQuestions);
			oos.close();
			fos.close();
			System.out.println("Serialized HashMap contenant les questions sauvegardees dans HashMap_Questions.ser");
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
		}

		return allQuestions;


	}

	@SuppressWarnings("unchecked")
	private HashMap<String, ListeQuestions> initQuestionDeserialize(){
		HashMap<String, ListeQuestions> allQuestions= new HashMap<>();

		try
		{
			FileInputStream fis = new FileInputStream("HashMap_Questions.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			allQuestions = (HashMap<String, ListeQuestions>) ois.readObject();
			ois.close();
			fis.close();
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
		}catch(ClassNotFoundException c)
		{
			System.out.println("Class not found");
			c.printStackTrace();
		}
		System.out.println("Deserialized HashMap contenant les questions");

		return allQuestions;
	}

}