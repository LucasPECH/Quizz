import java.util.Random;
import java.util.Vector;

public class EnsJoueurs {
    private final Vector<Joueur> vectorJoueurs;

    public EnsJoueurs(){
        vectorJoueurs = new Vector<>(20);
    }
    
    @SuppressWarnings("unchecked")
	public EnsJoueurs(EnsJoueurs ens)
    {
        vectorJoueurs = (Vector<Joueur>) ens.getVectorJoueurs().clone();
    }

    //méthodes demandées dans le sujet
    public void Afficher(){
        for(Joueur joueur : vectorJoueurs)
            joueur.Afficher();
    }

    public void Creer(){
        vectorJoueurs.addElement(new Joueur("A"));
        vectorJoueurs.addElement(new Joueur("B"));
        vectorJoueurs.addElement(new Joueur("C"));
        vectorJoueurs.addElement(new Joueur("D"));
        vectorJoueurs.addElement(new Joueur("E"));
        vectorJoueurs.addElement(new Joueur("F"));
        vectorJoueurs.addElement(new Joueur("G"));
        vectorJoueurs.addElement(new Joueur("H"));
        vectorJoueurs.addElement(new Joueur("I"));
        vectorJoueurs.addElement(new Joueur("J"));
        vectorJoueurs.addElement(new Joueur("Q"));
        vectorJoueurs.addElement(new Joueur("R"));
        vectorJoueurs.addElement(new Joueur("S"));
        vectorJoueurs.addElement(new Joueur("T"));
        vectorJoueurs.addElement(new Joueur("U"));
        vectorJoueurs.addElement(new Joueur("V"));
        vectorJoueurs.addElement(new Joueur("W"));
        vectorJoueurs.addElement(new Joueur("X"));
        vectorJoueurs.addElement(new Joueur("Y"));
        vectorJoueurs.addElement(new Joueur("Z"));
    }

    public Joueur SelectionnerJoueur(){
        Random random = new Random();
        return vectorJoueurs.get(random.nextInt(vectorJoueurs.size()));
        //Sélectionne et retourne un joueur choisi de manière aléatoire dans le vecteur de joueurs
    }

    //méthodes ajoutées pour les besoins du programme

    public Vector<Joueur> getVectorJoueurs() { return vectorJoueurs; }

    public void removeJoueur(int num){ //Supprime un joueur en fonction de son numéro
        for(int i=0;i<vectorJoueurs.size();i++){
            if(vectorJoueurs.get(i).getNumero() == num){
                vectorJoueurs.remove(i);
            }
        }
    }

    public EnsJoueurs TrouverJoueurEgalite()
    {
        boolean egalite = false;
        EnsJoueurs ensembleegalitejoueur = new EnsJoueurs(); //Vecteur de 20 joueurs
        for (int i=0; i<vectorJoueurs.size(); i++)
        {
            for (int j=i+1; j<vectorJoueurs.size(); j++)
            {
              
                if (vectorJoueurs.get(i).compareTo(vectorJoueurs.get(j)) == 0) //Si les deux joueurs ont le même score et le même temps
                {
                    if (!vectorJoueurs.get(j).getEgalite())
                    {
                        ensembleegalitejoueur.vectorJoueurs.add(vectorJoueurs.get(j));
                        vectorJoueurs.get(j).SetEgalite(true);
                    }
                    egalite = true;
                }
            }
            if (egalite)
            {
                if (!vectorJoueurs.get(i).getEgalite())
                {
                    ensembleegalitejoueur.vectorJoueurs.add(vectorJoueurs.get(i));
                    vectorJoueurs.get(i).SetEgalite(true);
                }
            }
            egalite = false;
        }
        for (Joueur vectorJoueur : vectorJoueurs) {
            vectorJoueur.SetEgalite(false);
        }
        return ensembleegalitejoueur;
    }

    public void JoueursSelectionnes(Joueur joueur){
        vectorJoueurs.addElement(joueur);
    }
    
    public void resetValeurEgalite()
    {
        for (Joueur vectorJoueur : vectorJoueurs) {
            vectorJoueur.resetEgalite();
        }
    }
    
    public String listeNom()
    {
    	StringBuilder str = new StringBuilder();
    	
    	for(Joueur joueur : vectorJoueurs)
       	{
    		str.append(" ").append(joueur.getNom()).append(" et");
        }
    	str = new StringBuilder(str.substring(0, str.lastIndexOf("et")));
    	return str.toString();
    }
}