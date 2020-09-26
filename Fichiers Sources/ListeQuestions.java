import java.io.Serializable;
import java.util.LinkedList;

public class ListeQuestions implements Serializable
{
	String theme;
	int lastIndex = -1;
	LinkedList<Question<?>> myList;

	public ListeQuestions(String theme)
	{
		this.theme = theme;
		myList = new LinkedList<>();
	}

	//méthodes demandées dans le sujet
	public void AfficherListe()
	{
		for(Object question : myList)
			System.out.println(question);
	}

	public void AjouterQuestion(Question<?> question){ myList.add(question); }

	public void SupprimerQuestion(int n){ myList.remove(n); }


	//méthodes ajoutées pour les besoins du projet
	public int getNumberToIncrement(){
		return myList.getLast().getNumero()+1;
	}

	public Question<?> getQuestion(int difficulte)
	{
		if(lastIndex == myList.size()-1)
		{
			lastIndex = 0;
		}
		else
		{
			lastIndex++;
		}
		if(myList.get(lastIndex).getDifficulte() == difficulte)
		{
			return myList.get(lastIndex);
		}
		else
		{
			return getQuestion(difficulte);
		}
	}

	public Question<?> getQuestionByNb(int numero)
	{
		if(lastIndex == myList.size()-1)
		{
			lastIndex = 0;
		}
		else
		{
			lastIndex++;
		}
		if(myList.get(lastIndex).getNumero() == numero)
		{
			return myList.get(lastIndex);
		}
		else
		{
			return getQuestionByNb(numero);
		}
	}

	public int supprimerQuestionByNb(int numero)
	{
		if(lastIndex == myList.size())
		{
			lastIndex = 0;
		}
		else
		{
			lastIndex++;
		}
		if(myList.size()==1){
			return 1;
		}
		else{
			if(lastIndex>=myList.size()){
				return 2;
			}
			if(myList.get(lastIndex).getNumero() == numero)
			{
				int cpt=0;
				int cpt1=0;
				int cpt_QCM=0;
				int cpt_VF=0;
				int cpt_RC=0;
				String type=myList.get(lastIndex).getEnonce().getClass().getName();
				int difficulty=myList.get(lastIndex).getDifficulte();
				for (Question<?> question : myList) {
					if (question.getDifficulte() == difficulty) {
						if (question.getEnonce().getClass().getName().equals("QCM")) {
							cpt_QCM++;
						}
						if (question.getEnonce().getClass().getName().equals("VF")) {
							cpt_VF++;
						}
						if (question.getEnonce().getClass().getName().equals("RC")) {
							cpt_RC++;
						}
					}
					if (question.getDifficulte() == difficulty) {
						cpt++;
					}
					if (question.getEnonce().getClass().getName().equals(type)) {
						cpt1++;
					}
				}
				System.out.println(cpt_QCM);
				if(type.equals("QCM") && cpt_QCM==1){
					return 5;
				}
				else if(type.equals("VF") && cpt_VF==1){
					return 5;
				}
				else if(type.equals("RC") && cpt_RC==1){
					return 5;
				}
				if(cpt==1){
					return 3;
				}
				if(cpt1==1){
					return 4;
				}
				else{
					myList.remove(lastIndex);
					return 0;
				}
			}
			else
			{
				return supprimerQuestionByNb(numero);
			}
		}

	}
}
