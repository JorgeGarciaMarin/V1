import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
public class PortManagement extends JFrame {
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

    public PortManagement() {
        int priority=0;
        setContentPane(mainPanel);
        setTitle("Port Management System");
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

                    Container container = new Container(id,weight,country,inspected,content,sender,receiver);
                    if(a1RadioButton.isSelected())
                        container.setPriorityLevel(1);
                    if(a2RadioButton.isSelected())
                    container.setPriorityLevel(2);
                    if(a3RadioButton.isSelected())
                    container.setPriorityLevel(3);
                    if(!hub1.full())
                    hub1.stackContainer(container);
                    else if(!hub2.full())
                    hub2.stackContainer(container);
                    else
                    hub3.stackContainer(container);
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
                int column=Integer.parseInt(tfColumnNumber.getText());
                int hubNum=Integer.parseInt(hubNumber.getText());
                if(hubNum==1)
                hub1.removeContainer(column);
                if(hubNum==2)
                hub2.removeContainer(column);
                if(hubNum==3)
                hub3.removeContainer(column);
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
            }
        });
    }

    public static void main(String[] args) throws IOException {
        PortManagement myFrame=new PortManagement();
    }

}
//Git-Create Commit-Push
