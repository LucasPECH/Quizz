import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import static javax.swing.JOptionPane.showMessageDialog;

public class AjoutVFWindow 
{
    private JFrame frame;
    private JTextField textField;
    
    /**
     * Create the application.
     */
    public AjoutVFWindow(Themes objettheme, HashMap<String, ListeQuestions> questions, String diff, String theme) {
        initialize(objettheme, questions, diff, theme);
    }

	/**
	 * Initialize the contents of the frame.
	 */
    private void initialize(Themes objettheme, HashMap<String, ListeQuestions> questions, String diff, String theme) {
        frame = new JFrame();
        frame.setBackground(Color.WHITE);
        frame.setBounds(100, 100, 584, 417);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);
        fillwindow(objettheme, questions, diff, theme);
    }
    public void fillwindow(Themes objettheme, HashMap<String, ListeQuestions> questions, String diff, String theme)
    {
        JLabel lblNewLabel = new JLabel("Ecrivez la question et indiquez si c'est vrai ou faux : ");
        lblNewLabel.setBounds(166, 11, 350, 14);
        frame.getContentPane().add(lblNewLabel);
        JButton btnNewButton = new JButton("PRECEDENT");
        btnNewButton.setBounds(0, 0, 111, 23);
        btnNewButton.addActionListener(e -> {
            new AjouterQuestionWindow(questions, objettheme);
            frame.dispose();
        });
        frame.getContentPane().add(btnNewButton);
        JPanel panel = new JPanel();
        panel.setBounds(0, 75, 550, 308);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        JLabel lblNewLabel_1 = new JLabel("Question: ");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblNewLabel_1.setBounds(0, 0, 82, 59);
        panel.add(lblNewLabel_1);
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(86, 0, 464, 66);
        panel.add(textField);
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Vrai", "Faux"}));
        comboBox.setBounds(327, 257, 46, 20);
        panel.add(comboBox);
        JLabel lblNewLabel_2 = new JLabel("Entrez si la réponse est vraie ou fausse:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(0, 262, 317, 14);
        panel.add(lblNewLabel_2);
        JButton btnNewButton_1 = new JButton("Valider");
        btnNewButton_1.setBounds(426, 256, 89, 23);
        btnNewButton_1.addActionListener(arg0 -> {
            if (textField.getText().isEmpty())
            {
                showMessageDialog(null, "Tous les champs ne sont pas remplis");
            }
            else
            {
                int difficulte;
                String enonce = textField.getText();
                Question<VF> q0;
                String bonnereponse = comboBox.getSelectedItem().toString();
                ListeQuestions questionspartheme = questions.get(theme);
                int num_to_increment=questionspartheme.getNumberToIncrement();
                if (diff.equals("Facile"))
                    difficulte = 1;
                else if (diff.equals("Moyen"))
                    difficulte = 2;
                else
                    difficulte = 3;
                if (bonnereponse.equals("Vrai"))
                {
                    q0 = new Question<>(num_to_increment,theme, new VF(enonce,true),difficulte);
                    questionspartheme.AjouterQuestion(q0);
                }
                else if (bonnereponse.equals("Faux"))
                {
                    q0 = new Question<>(num_to_increment,theme, new VF(enonce, false),difficulte);
                    questionspartheme.AjouterQuestion(q0);
                }
                questions.remove(theme);
                questions.put(theme, questionspartheme);
                try
                {
                    FileOutputStream fos = new FileOutputStream("HashMap_Questions.ser");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(questions);
                    oos.close();
                    fos.close();
                    System.out.println("Serialized HashMap contenant les questions sauvegardée dans HashMap_Questions.ser");
                    showMessageDialog(null, "Question ajoutée dans la liste de questions!");
                }catch(IOException ioe)
                {
                    ioe.printStackTrace();
                }
            }
        });
        panel.add(btnNewButton_1);

        frame.setVisible(true);
    }
}