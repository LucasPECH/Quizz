import java.io.Serializable;

public class VF implements Serializable
{
    //une question de type VF est caractérisée par son texte et sa bonne réponse (vrai ou faux)
    private String texte;
    private final boolean bonneReponse;

    public VF(String texte, boolean bonneReponse)
    {
        this.texte = texte;
        this.bonneReponse = bonneReponse;
    }
    
    public String getText()
    {
    	return texte;
    }
    
    public boolean getBonneReponse()
    {
    	return bonneReponse;
    }

    @Override
    public String toString() {
        String bonneRep;
        if(!bonneReponse){
            bonneRep="Faux";
        }
        else{
            bonneRep="Vrai";
        }
        return " Vrai ou Faux :\n"+ texte + "\n1.Vrai\n2.Faux" + "\n" + "Bonne réponse: "+bonneRep + "\n";
    }
}

