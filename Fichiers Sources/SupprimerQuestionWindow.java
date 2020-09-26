import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import static javax.swing.JOptionPane.showMessageDialog;


public class SupprimerQuestionWindow {


    private JFrame frame;
    private JTextField textField;

    /**
     * Create the application.
     */
    public SupprimerQuestionWindow(HashMap<String, ListeQuestions> questions, Themes them) {
        initialize(questions, them);
    }



    /**
     * Initialize the contents of the frame.
     */
    private void initialize(HashMap<String, ListeQuestions> questions, Themes them) {
        frame = new JFrame();
        frame.setBounds(100, 100, 566, 474);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        fillwindow(questions, them);
    }



    public void fillwindow(HashMap<String, ListeQuestions> questions, Themes them)
    {
        SpringLayout springLayout = new SpringLayout();
        frame.getContentPane().setLayout(springLayout);
        JButton btnNewButton = new JButton("PRECEDENT"); //Bouton retour en arrière
        springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 0, SpringLayout.NORTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 0, SpringLayout.WEST, frame.getContentPane());
        btnNewButton.addActionListener(e -> {
            new ModerationWindow(them, questions); //Retour à la fenêtre de modération du jeu
            frame.dispose();
        });
        frame.getContentPane().add(btnNewButton);



        JLabel lblNewLabel = new JLabel("Choisissez un theme et valider pour afficher les questions : ");
        springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 4, SpringLayout.NORTH, btnNewButton);
        springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 68, SpringLayout.EAST, btnNewButton);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(lblNewLabel);



        JTextArea textArea = new JTextArea(20,20);
        JScrollPane scroll = new JScrollPane(textArea);
        springLayout.putConstraint(SpringLayout.WEST, scroll, 10, SpringLayout.WEST, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, scroll, -62, SpringLayout.SOUTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, scroll, 540, SpringLayout.WEST, frame.getContentPane());
        frame.getContentPane().add(scroll);
        textArea.setEditable(false);


        JComboBox<String> comboBox = new JComboBox<>();
        springLayout.putConstraint(SpringLayout.NORTH, scroll, 16, SpringLayout.SOUTH, comboBox);
        springLayout.putConstraint(SpringLayout.NORTH, comboBox, 6, SpringLayout.SOUTH, btnNewButton);
        springLayout.putConstraint(SpringLayout.WEST, comboBox, 10, SpringLayout.WEST, btnNewButton);
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Histoire", "Cinema", "Sport", "Musique", "Celebrites", "Monde", "Cuisine", "Animaux", "Series Televises", "Litterature"}));
        frame.getContentPane().add(comboBox);



        JButton btnNewButton_1 = new JButton("Valider");
        springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_1, -1, SpringLayout.NORTH, comboBox);
        springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1, 24, SpringLayout.EAST, comboBox);
        btnNewButton_1.addActionListener(e -> {
            textArea.setText(null);
            String theme = comboBox.getSelectedItem().toString();
            textArea.append(theme + "\n");
            ListeQuestions questionsdanstheme = questions.get(theme);
            for(Object question : questionsdanstheme.myList)
                textArea.append(question.toString());
        });
        frame.getContentPane().add(btnNewButton_1);

        textField = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, textField, 19, SpringLayout.SOUTH, scroll);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JButton btnNewButton_2 = new JButton("Supprimer");
        springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_2, -1, SpringLayout.NORTH, textField);
        springLayout.putConstraint(SpringLayout.WEST, btnNewButton_2, 54, SpringLayout.EAST, textField);
        btnNewButton_2.addActionListener(e -> {
            textArea.setText(null);
            String theme = comboBox.getSelectedItem().toString();
            String numero_string = textField.getText();
            int numero=Integer.parseInt(numero_string);
            ListeQuestions questionsdanstheme = questions.get(theme);
            int error = questionsdanstheme.supprimerQuestionByNb(numero);
            if(error==1){
                showMessageDialog(null, "Vous ne pouvez pas supprimer la dernière question!");
            }
            else if(error==2){
                showMessageDialog(null, "Veuillez rentrer un numéro valide!");
            }
            else if(error==3){
                showMessageDialog(null, "Vous ne pouvez pas supprimer la dernière question avec cette difficulté");
            }
            else if(error==4){
                showMessageDialog(null, "Vous ne pouvez pas supprimer la dernière question de ce type");
            }
            else if(error==5){
                showMessageDialog(null, "Il vous faut une question de chaque type pour chaque difficulté");
            }
            else{
                questions.remove(theme);
                questions.put(theme, questionsdanstheme);
                try
                {
                    FileOutputStream fos = new FileOutputStream("HashMap_Questions.ser");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(questions);
                    oos.close();
                    fos.close();
                    System.out.println("Serialized HashMap contenant la nouvelle liste de question sauvegardée dans HashMap_Questions.ser");
                    textField.setText("");
                    showMessageDialog(null, "Question supprimée");
                }catch(IOException ioe)
                {
                    ioe.printStackTrace();
                }
            }

        });
        frame.getContentPane().add(btnNewButton_2);

        JLabel lblNewLabel_1 = new JLabel("Entrez le numero de la question a supprimer:");
        springLayout.putConstraint(SpringLayout.WEST, textField, 17, SpringLayout.EAST, lblNewLabel_1);
        springLayout.putConstraint(SpringLayout.EAST, textField, 58, SpringLayout.EAST, lblNewLabel_1);
        springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 24, SpringLayout.SOUTH, scroll);
        springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 41, SpringLayout.WEST, frame.getContentPane());
        frame.getContentPane().add(lblNewLabel_1);
        frame.setVisible(true);
    }
}