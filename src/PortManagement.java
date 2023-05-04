import Exceptions.*;


import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
public class PortManagement extends JFrame  {
    private Hub hub3=new Hub();
    private Hub hub2=new Hub();
    private Hub hub1=new Hub();
    private JTextArea taDescription;
    private JTextField tfSender;
    private JTextField fReceiver;
    private JButton buttonPile;
    private JButton buttonUnpile;
    private JTextField tfColumnNumber;
    private JCheckBox cbCustomInspection;
    private JButton showContainerDescriptionButton;
    private JTextArea taContDescription;
    private JButton buttonNumContainers;
    private JTextField tfNumber;
    private JPanel mainPanel;
    private JTextField tfWeight;
    private JTextField tfID;
    private JTextField tfPriority;
    private JButton showHub;
    private JTextArea taHub;
    private JRadioButton a1RadioButton;
    private JRadioButton a2RadioButton;
    private JRadioButton a3RadioButton;
    public JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField hubNumber;
    private JButton buttonExercise6Button;
    private JCheckBox hub1CheckBox;
    private JCheckBox hub2CheckBox;
    private JCheckBox hub3CheckBox;
    public boolean shown=false;
    JTextField textFieldForWindow;

    public PortManagement() {
        int priority=0;
        hub1.hubID=1;
        hub2.hubID=2;
        hub3.hubID=3;
        setContentPane(mainPanel);
        setTitle("Port Management Application");
        setSize(1600,1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        ButtonGroup group = new ButtonGroup();
        group.add(a1RadioButton);
        group.add(a2RadioButton);
        group.add(a3RadioButton);
        buttonPile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Option 1", "Option 2", "Option 3"};
                try {
                    int id = Integer.parseInt(tfID.getText());

                    int weight = Integer.parseInt(tfWeight.getText());
                    if (weight<0)
                        throw new NegativeWeightException("Enter a positive weight value");
                    String country = (String) comboBox1.getSelectedItem();
                    String content = taDescription.getText();
                    String sender = tfSender.getText();
                    String receiver = fReceiver.getText();
                    boolean inspected;
                    if (cbCustomInspection.isSelected()) {
                        inspected = true;
                    } else {
                        inspected = false;
                    }

                    Container container = new Container(id, weight, country, inspected, content, sender, receiver);
                    if(!content.matches("[a-zA-Z ]+")){
                        throw new InvalidDescriptionException("Only text is allowed in description");
                    }
                    if (a1RadioButton.isSelected())
                        container.setPriorityLevel(1);
                    if (a2RadioButton.isSelected())
                        container.setPriorityLevel(2);
                    if (a3RadioButton.isSelected())
                        container.setPriorityLevel(3);
                    if (!hub1.full())
                        hub1.stackContainer(container);
                    else if (!hub2.full())
                        hub2.stackContainer(container);
                    else
                        hub3.stackContainer(container);
                    if(shown)
                        taHub.setText("Hub1\n"+hub1.toString()+"\nHub2\n"+hub2.toString()+"\nHub3"+"\n"+hub3.toString());
                }
                catch (NumberFormatException nfr){
                    JOptionPane.showMessageDialog(null,"Enter only numbers in the ID and Weight fields");
                }
                catch (NegativeWeightException nwe){
                    JOptionPane.showMessageDialog(null,nwe.getMessage());
                }
                catch (InvalidDescriptionException ide){
                    JOptionPane.showMessageDialog(null,ide.getMessage());

                } catch (FullPriorityLevel fpl) {
                    //Exception thrown in the hub class(inside the jar file)
                    JOptionPane.showMessageDialog(null,fpl);
                } catch (NoPriorityLevel npl) {
                    //Exception thrown in the hub class(inside the jar file)
                    JOptionPane.showMessageDialog(null,npl);
                }

            }
        });

        showContainerDescriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(tfID.getText());
                int weight = Integer.parseInt(tfWeight.getText());
                String country = (String) comboBox1.getSelectedItem();
                String content = taDescription.getText();
                String sender = tfSender.getText();
                String receiver = fReceiver.getText();
                boolean inspected;
                if(cbCustomInspection.isSelected()){
                    inspected=true;
                }
                else {
                    inspected=false;
                }
                Container container=new Container(id,weight,country,inspected,content,sender,receiver);
                if(a1RadioButton.isSelected())
                    container.setPriorityLevel(1);
                if(a2RadioButton.isSelected())
                    container.setPriorityLevel(2);
                if(a3RadioButton.isSelected())
                    container.setPriorityLevel(3);
                taContDescription.setText(container.toString());
            }
        });
        buttonUnpile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int column=Integer.parseInt(tfColumnNumber.getText());
                    int hubNum=Integer.parseInt(hubNumber.getText());
                    if(hubNum==1)
                        hub1.removeContainer(column);
                    else if(hubNum==2)
                        hub2.removeContainer(column);
                    else if(hubNum==3)
                        hub3.removeContainer(column);
                    if(shown){
                        taHub.setText("Hub1\n"+hub1.toString()+"\nHub2\n"+hub2.toString()+"\nHub3"+"\n"+hub3.toString());
                    }
                } catch (NoContainersException ex) {
                    //Exception thrown in the hub class(inside the jar file)
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }

            }
        });
        buttonNumContainers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String countryToCheck= (String) comboBox2.getSelectedItem();
                int x=hub1.containersByCountry(countryToCheck)+hub2.containersByCountry(countryToCheck)+hub3.containersByCountry(countryToCheck);
                tfNumber.setText(String.valueOf(x));
            }
        });
        showHub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hubPlan="Hub1\n"+hub1.toString()+"\nHub2\n"+hub2.toString()+"\nHub3"+"\n"+hub3.toString();
                taHub.setText(hubPlan);
                shown=true;
            }
        });
        buttonExercise6Button.addActionListener(new ActionListener() {
            //We will perform the method in the hub indicated by the checkboxes
            @Override
            public void actionPerformed(ActionEvent e) {
                String toDisplay="";
                int weightToCheck= Integer.parseInt(tfWeight.getText());
                if(hub1CheckBox.isSelected()){
                    toDisplay=hub1.weightLessOrEqual(1,weightToCheck);
                }
                else if(hub2CheckBox.isSelected()){
                    toDisplay=hub2.weightLessOrEqual(2,weightToCheck);
                }
                else if(hub3CheckBox.isSelected()){
                    toDisplay=hub3.weightLessOrEqual(3,weightToCheck);
                }
                else{
                    toDisplay="Enter a valid hub number->Between 1 and 3";
                }

                JFrame window=new JFrame();
                window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                window.setLayout(null);
                window.setSize(500,500);
                window.setTitle("Containers with weight less or equal than the indicated one");
                window.setResizable(false);
                textFieldForWindow=new JTextField();
                textFieldForWindow.setBounds(0,0,500,500);
                window.add(textFieldForWindow);
                window.setVisible(true);
                textFieldForWindow.setText(toDisplay);
            }
        });
    }



    public static void main(String[] args) throws IOException {
        PortManagement myFrame=new PortManagement();
    }
}

