import java.util.HashMap;

public interface Phase
{
    //méthodes demandées dans le sujet
    void SelectionnerJoueurs(EnsJoueurs joueursSelectionnes, int phase);
    void PhasedeJeu(EnsJoueurs joueursSelectionnes, HashMap<String, ListeQuestions> allQuestions, Themes themes, int phase);
}
