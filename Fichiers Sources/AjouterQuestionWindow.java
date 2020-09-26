import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AjouterQuestionWindow {

    private JFrame frame;

    /**
     * Create the application.
     */
    public AjouterQuestionWindow(HashMap<String, ListeQuestions> questions, Themes them) {
        initialize(questions, them);
    }
    /**
     * Initialize the contents of the frame.
     */
    private void initialize(HashMap<String, ListeQuestions> questions, Themes them) {
        frame = new JFrame();
        frame.setBounds(100, 100, 595, 513);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(5, 0, 0, 0));
        frame.setLocationRelativeTo(null);
        fillwindow(questions, them);
    }
    public void fillwindow(HashMap<String, ListeQuestions> questions, Themes them)
    {
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        JButton btnNewButton = new JButton("PRECEDENT");
        btnNewButton.addActionListener(arg0 -> {
            new ModerationWindow(them, questions);
            frame.dispose();
        });
        btnNewButton.setBounds(0, 0, 125, 34);
        panel.add(btnNewButton);
        JLabel lblNewLabel = new JLabel("Choisissez un niveau de difficulte, un theme, et un type de question pour en ajouter une");
        lblNewLabel.setBounds(55, 11, 500, 87);
        panel.add(lblNewLabel);
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Facile", "Moyen", "Difficile"}));
        frame.getContentPane().add(comboBox);
        JComboBox<String> comboBox_1 = new JComboBox<>();
        comboBox_1.setModel(new DefaultComboBoxModel<>(new String[] {"Histoire", "Cinema", "Sport", "Musique", "Celebrites", "Monde", "Cuisine", "Animaux", "Series Televises", "Litterature"}));
        frame.getContentPane().add(comboBox_1);
        JComboBox<String> comboBox_2 = new JComboBox<>();
        comboBox_2.setModel(new DefaultComboBoxModel<>(new String[] {"QCM", "Vrai/Faux", "Reponse Courte"}));
        frame.getContentPane().add(comboBox_2);
        JButton btnNewButton_1 = new JButton("Valider");
        btnNewButton_1.addActionListener(arg0 -> {
            String diff = comboBox.getSelectedItem().toString();
            String theme = comboBox_1.getSelectedItem().toString();
            String typ_question = comboBox_2.getSelectedItem().toString();
            if (typ_question.equals("QCM"))
            {
                new AjoutQcmWindow(them, questions, diff, theme);
            }
            else if(typ_question.equals("Vrai/Faux"))
            {
                new AjoutVFWindow(them, questions, diff, theme);
            }
            else
            {
                new AjoutRCWindow(them, questions, diff, theme);
            }
            frame.dispose();
        });
        frame.getContentPane().add(btnNewButton_1);
        frame.setVisible(true);
    }
}
