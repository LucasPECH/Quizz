import java.util.ArrayList;
import java.util.Arrays;

public class Themes
{
    private ArrayList<String> listThemes;
    private boolean indicateur;
    private static int current = -1;
    String[] themes = {"Histoire", "Cinema", "Sport", "Musique", "Celebrites", "Monde", "Cuisine", "Animaux", "Series Televisees", "Litterature"};
    int[] checkoccurence = new int[10];

    // Méthodes demandées dans le sujet

    public void InitTheme()
    {
        listThemes = new ArrayList<>(Arrays.asList(themes));
    }

    public void ModifierTheme(int index, String themes)
    {
        this.themes[index] = themes; //On remplace un thème par un autre
        InitTheme(); //On réactualise la liste de thèmes
    }

    public String[] selectSixThemes()
    {
        String[] themeArray = new String[6];

        for(int i = 0 ; i < 6 ; i++)
        {
            themeArray[i] = selectTheme(); //Chacune des cases du tableau se voit affecter un thème
        }

        return themeArray;
    }

    public String selectTheme(){

        boolean isfull = false;
        for (int i=0; i<checkoccurence.length; i++)
        {
            if (i == 0)
            {
                if (checkoccurence[i] == 0)
                    isfull = true;
            }
            else
            {
                if (checkoccurence[i] == 0) //occurence vaut -1 si le thème a été choisi
                    isfull = false;
            }
        }
        if (isfull)
            setto0();

        boolean check = false;
        int theme = 0;
        while(!check)
        {
            theme = (int) (Math.random() * ( 10 ));
            if(theme == checkoccurence[theme])
                check = false;
            else
            {

                check = true;
            }


        }
        checkoccurence[theme] = theme;
        indicateur = true;

        return listThemes.get(theme);
    }

    public void Afficher()
    {
        System.out.println("Indicateur : " + current);
        System.out.println("Liste des thèmes");
        for (String listTheme : listThemes)
        {
            System.out.println(listTheme);
        }
    }


    // Méthodes ajoutées pour les besoins du programme

    public Themes(){
        checkoccurence[0] = -1;
        InitTheme();
        indicateur = false;
        Themes.current++;
    }

    public void setto0()
    {
        checkoccurence[0] = -1;
        for (int i=1; i<checkoccurence.length; i++)
        {
            checkoccurence[i] = 0;
        }
    }

    public void removeTheme(String theme){
        for(int i=0;i<listThemes.size();i++){
            listThemes.remove(theme);
        }
    }
}