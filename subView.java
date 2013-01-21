/**
 * View for the Subnetter application
 * User: Robin Stark
 * Date: 1/21/13
 * Time: 10:45 AM
 * This is the main GUI
 */

import javax.swing.*;

public class subView extends JPanel{
    //Ref to the back end
    BackEnd model;
    //Text Fields for input (one / octet)
    JLabel aLabel;
    //FIRST ELEMENT IN ARRAY EACH FOR ADDRESS
    //SECOND IS FOR MASK
    JTextField[] first, second, third, fourth;
    JButton submit;
    JTextArea output;

    //Constructor
    public subView (BackEnd m)
    {
        model = m;

        //Setting up the main components
        setLayout(null);
        setSize(200,370);

        //Setting up the Text Field arrays
        first = new JTextField[2];
        second = new JTextField[2];
        third = new JTextField[2];
        fourth = new JTextField[2];

        //Setting up the Text Fields
        aLabel = new JLabel ("Address");
        aLabel.setLocation(10,10);
        aLabel.setSize(100,20);
        add(aLabel);

        //Setting up the Address Text Fields
        first[0] = new JTextField("");
        first[0].setLocation(10,30);
        first[0].setSize(25,20);
        add(first[0]);

        aLabel = new JLabel(".");
        aLabel.setLocation(40,40);
        aLabel.setSize(10,10);
        add(aLabel);

        second[0] = new JTextField("");
        second[0].setLocation(50,30);
        second[0].setSize(25,20);
        add(second[0]);

        aLabel = new JLabel(".");
        aLabel.setLocation(80,40);
        aLabel.setSize(10,10);
        add(aLabel);

        third[0] = new JTextField("");
        third[0].setLocation(90,30);
        third[0].setSize(25,20);
        add(third[0]);

        aLabel = new JLabel(".");
        aLabel.setLocation(120,40);
        aLabel.setSize(10,10);
        add(aLabel);

        fourth[0] = new JTextField("");
        fourth[0].setLocation(130,30);
        fourth[0].setSize(25,20);
        add(fourth[0]);

        //Setting up the Mask Fields
        aLabel = new JLabel("Subnet Mask");
        aLabel.setLocation(10,60);
        aLabel.setSize(100,20);
        add(aLabel);

        first[1] = new JTextField("");
        first[1].setLocation(10,80);
        first[1].setSize(25,20);
        add(first[1]);

        aLabel = new JLabel(".");
        aLabel.setLocation(40,90);
        aLabel.setSize(10,10);
        add(aLabel);

        second[1] = new JTextField("");
        second[1].setLocation(50,80);
        second[1].setSize(25,20);
        add(second[1]);

        aLabel = new JLabel(".");
        aLabel.setLocation(80,90);
        aLabel.setSize(10,10);
        add(aLabel);

        third[1] = new JTextField("");
        third[1].setLocation(90,80);
        third[1].setSize(25,20);
        add(third[1]);

        aLabel = new JLabel(".");
        aLabel.setLocation(120,90);
        aLabel.setSize(10,10);
        add(aLabel);

        fourth[1] = new JTextField("");
        fourth[1].setLocation(130,80);
        fourth[1].setSize(25,20);
        add(fourth[1]);

        //Adding the submit button
        submit = new JButton("Calculate");
        submit.setLocation(30,110);
        submit.setSize(100,20);
        add(submit);

        //Adding the output area
        aLabel = new JLabel("IP Address Ranges");
        aLabel.setLocation(10,140);
        aLabel.setSize(150,20);
        add(aLabel);

        output = new JTextArea("");
        JScrollPane scroll = new JScrollPane(output,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setLocation(10,160);
        scroll.setSize(150,150);
        add(scroll);
    }

    //Getters
    public JTextField getMaskFirst()
    {
        return first[1];
    }
    public JTextField getMaskSecond()
    {
        return second[1];
    }
    public JTextField getMaskThird()
    {
        return third[1];
    }
    public JTextField getMaskFourth()
    {
        return fourth[1];
    }
    public JTextField getAddFirst()
    {
        return first[0];
    }
    public JTextField getAddSecond()
    {
        return second[0];
    }
    public JTextField getAddThird()
    {
        return third[0];
    }
    public JTextField getAddFourth()
    {
        return fourth[0];
    }
    public JButton getSubmit()
    {
        return submit;
    }
    public JTextArea getOutput()
    {
        return output;
    }

    public void update()
    {
        int[][] ranges;
        ranges = model.getmaskRanges();
        for (int i=0; i < ranges.length; i++)
        {
            output.append(ranges[i][0] + " - " + ranges[i][1] + '\n');
        }
    }
}
