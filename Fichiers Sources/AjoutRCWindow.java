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
import static javax.swing.JOptionPane.showMessageDialog;

public class AjoutRCWindow 
{
    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;

    /**
     * Create the application.
     */
    public AjoutRCWindow(Themes objettheme, HashMap<String, ListeQuestions> questions, String diff, String theme) {
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
        JLabel lblNewLabel = new JLabel("Ecrivez la question et la réponse : ");
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
        JLabel lblRponse = new JLabel("Reponse:");
        lblRponse.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblRponse.setBounds(0, 65, 94, 59);
        panel.add(lblRponse);
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(86, 0, 464, 66);
        panel.add(textField);
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(86, 58, 464, 66);
        panel.add(textField_1);
        JButton btnNewButton_1 = new JButton("Valider");
        btnNewButton_1.setBounds(426, 256, 89, 23);
        btnNewButton_1.addActionListener(arg0 -> {
            if (textField.getText().isEmpty() || textField_1.getText().isEmpty())
            {
                showMessageDialog(null, "Tous les champs ne sont pas remplis");
            }
            else
            {
                int difficulte;
                String enonce = textField.getText();
                Question<RC> q0;
                ListeQuestions questionspartheme = questions.get(theme);
                int num_to_increment=questionspartheme.getNumberToIncrement();
                String r = textField_1.getText();
                if (diff.equals("Facile"))
                    difficulte = 1;
                else if (diff.equals("Moyen"))
                    difficulte = 2;
                else
                    difficulte = 3;
                q0 = new Question<>(num_to_increment, theme, new RC(enonce, r), difficulte);
                questionspartheme.AjouterQuestion(q0);
                questions.remove(theme);
                questions.put(theme, questionspartheme);
                try
                {
                    FileOutputStream fos = new FileOutputStream("HashMap_Questions.ser");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(questions);
                    oos.close();
                    fos.close();
                    System.out.println("Serialized HashMap contenant la nouvelle liste de question sauvegardée dans HashMap_Questions.ser");
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