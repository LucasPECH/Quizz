import java.io.Serializable;

public class QCM implements Serializable
{
    //Un QCM est caractérisé par son texte, ses 3 possibles réponses dont une bonne réponse
    private final String texte, reponse, reponse2, reponse3, bonneReponse;

    public QCM(String texte, String reponse, String reponse2, String reponse3, String bonneReponse)
    {
        this.texte = texte;
        this.reponse = reponse;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.bonneReponse = bonneReponse;
    }

    public String getText()
    {
        return texte;
    }
    public String getReponse()
    {
        return reponse;
    }
    public String getReponse2()
    {
        return reponse2;
    }
    public String getReponse3()
    {
        return reponse3;
    }
    public String getBonneReponse()
    {
        return bonneReponse;
    }
    public String toString() {
        return " QCM :\n"+texte + "\n" + "1. " + reponse + "\n2. " + reponse2 + "\n3. " + reponse3 + "\n" + "Bonne réponse: "+bonneReponse+ "\n";
    }
}