import java.io.Serializable;

public class RC implements Serializable
{
    //Une question de type réponse courte est caractérisée par son texte et sa réponse (chaine de caractères)
    private final String texte, bonneReponse;

    public RC(String texte, String bonneReponse)
    {
        this.texte = texte;
        this.bonneReponse = bonneReponse;
    }

    public String getBonneReponse() { return bonneReponse; }
    public String getText() { return texte; }

    @Override
    public String toString() {
        return " Réponse Courte :\n"+ texte + "\n" + "Bonne réponse: "+bonneReponse+ "\n";
    }
}
