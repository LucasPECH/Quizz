import java.io.Serializable;

public class Question<T> implements Serializable
{
    private static int incr = 1;
    private final int numero, difficulte;
    private final String theme;
    private T enonce;

    // Méthodes demandées dans le sujet
    public Question(String theme, T enonce, int difficulte)
    {
        this.numero = incr;
        Question.incr++;
        this.theme = theme;

        this.difficulte = difficulte;

        Saisir(enonce);
    }

    public void Saisir(T enonce){ this.enonce = enonce; }

    public void Afficher() { System.out.println(this); }


    // Méthodes ajoutées pour les besoins du programme
    public Question(int numero, String theme, T enonce, int difficulte)
    {
        this.numero = numero;
        this.theme = theme;

        this.difficulte = difficulte;

        Saisir(enonce);
    }

    @Override
    public String toString() {
        String diff;
        if(difficulte == 1)
            diff = "Facile";
        else if (difficulte == 2)
        {
            diff = "Moyen";
        }
        else
        {
            diff = "Difficile";
        }
        return "Question n°"+numero+"\nDifficulte : " + diff + "\n" +"Enonce /" + enonce + "\n";
    }


    public T getEnonce()
    {
        return enonce;
    }

    public int getNumero()
    {
        return numero;
    }

    public int getDifficulte()
    {
        return difficulte;
    }
}