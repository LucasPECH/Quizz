public class Joueur implements Comparable<Joueur>
{
    private static int incr = 100;
    private int numero;
    private double score;
    private String nom, etat;
    private Timer timer;
    private boolean egalite;

    public Joueur(String nom)
    {
        numero = incr;
        Joueur.incr +=10;
        this.score = 0;
        Saisir(nom);
        this.etat = "en attente";
        this.timer = new Timer(); //Initialise le timer d'un joueur à 00:00:00:00
        this.egalite = false;
    }

    //méthodes demandées dans le sujet
    public void ChangerEtat(String etat){ this.etat = etat; }
    public void MAJScore(double score){ this.score += score; }
    public void Afficher(){System.out.println(this);}
    public void Saisir(String nom)
    {
        this.nom = nom;
    }


    //méthodes ajoutées pour les besoins du projet
    public int getScore() { return (int) score; }
    public int getNumero() { return numero; }
    public String getEtat() { return etat; }
    public String getNom() { return nom; }
    public boolean getEgalite(){return egalite;}
    public void SetEgalite(boolean tf){this.egalite = tf;}

    @Override
    public String toString() {
        return "\nJoueur numero : " + numero + "\nScore : " + score + "\nNom : " + nom;
    }
    
    public void resetEgalite()
    {
    	score = (int) score;
    }

    public Timer getTimer()
    {
        return timer;
    }

    class Timer
    {
        int millisecondes = 0;
        int secondes = 0;
        int minutes = 0;
        int heures = 0;

        public int getTimerValue()
        {
            //Retourne la valeur du timer en millisecondes
            return timer.heures * 3600000 + timer.minutes * 60000 + timer.secondes * 1000 + timer.millisecondes;
        }
        
        public void setMillisecondes(int value)
        {
        	millisecondes = value;
        }
        
        public void setSecondes(int value)
        {
        	secondes = value;
        }
    }
    public boolean isEqual(Joueur joueur)
    {
    	if(this.score == joueur.score && this.getTimer().getTimerValue() == joueur.getTimer().getTimerValue())
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }

    public int compareTo(Joueur joueur) //Comparer les joueurs grâce à leur score et leur timer
    {
        if(joueur.getEtat().equals("elimine"))
        {
            return 1;
        }
        else
        {
            if(this.score < joueur.score)
            {
                return -1;
            }
            else if(this.score == joueur.score)
            {
                return  joueur.getTimer().getTimerValue() - this.getTimer().getTimerValue();
                //Retourne 0 si les 2 joueurs ont le même score et le même temps
            }
            else
            {
                return 1;
            }
        }
    }
}

