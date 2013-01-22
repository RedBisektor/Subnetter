/**
 * Controller Class for the Subnetter App
 * User: Robin
 * Date: 1/21/13
 * Time: 12:07 PM
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;

public class Control extends JFrame{
    BackEnd model;
    subView view;
    JMenuBar menu;
    JMenu edit;
    JMenuItem copyClip;
    //Constructor Time
    public Control (String title)
    {
        //Setting up the Frame and all the good stuff
        super(title);
        setSize(200,370);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        makeMenu();
        model = new BackEnd();
        view = new subView(model);
        getContentPane().setLayout(null);
        getContentPane().add(view);
        //Submit button action listener
        view.getSubmit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCalculatePress();
            }
        });
        //copy button action listener
        copyClip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
                clip.setContents(new StringSelection(view.getOutput().getText()),null);
            }
        });

    }

    private void makeMenu ()
    {
        menu = new JMenuBar();
        this.setJMenuBar(menu);

        edit = new JMenu("Edit");
        menu.add(edit);

        copyClip = new JMenuItem("Copy output to the ClipBoard");
        edit.add(copyClip);
    }

    private void handleCalculatePress()
    {
        //Check to make sure the input is valid
        view.getOutput().setText("");
        if (checkMaskFirst() && checkMaskSecond() && checkMaskThird() && checkMaskFourth() && checkAddFirst() && checkAddSecond() && checkAddThird() && checkAddFourth())
        {
            int[] mask = new int[4];
            mask[0]=Integer.parseInt(view.getMaskFirst().getText());
            mask[1]=Integer.parseInt(view.getMaskSecond().getText());
            mask[2]=Integer.parseInt(view.getMaskThird().getText());
            mask[3]=Integer.parseInt(view.getMaskFourth().getText());
            model.Calculate(mask);
            view.update();
        }
        else
        {
            if (!checkMaskFirst()) {view.getOutput().append("First Octet of Mask isn't valid\n");}
            if (!checkMaskSecond()) {view.getOutput().append("Second Octet of Mask isn't valid\n");}
            if (!checkMaskThird()) {view.getOutput().append("Third Octet of Mask isn't valid\n");}
            if (!checkMaskFourth()) {view.getOutput().append("Fourth Octet of Mask isn't valid\n");}
            if (!checkAddFirst()) {view.getOutput().append("First Octet of Address isn't valid\n");}
            if (!checkAddSecond()) {view.getOutput().append("Second Octet of Address isn't valid\n");}
            if (!checkAddThird()) {view.getOutput().append("Third Octet of Address isn't valid\n");}
            if (!checkAddFourth()) {view.getOutput().append("Fourth Octet of Address isn't valid\n");}
        }

        //update the view
    }
    //Methods that check the Subnet mask for validity
    private boolean checkMaskFirst ()
    {   if (checkIfEntered(view.getMaskFirst()))   {return false;}
        if ((Integer.parseInt(view.getMaskFirst().getText()) > 0) && (Integer.parseInt(view.getMaskFirst().getText()) == 255))
        {
            return true;
        }
        return false;
    }

    private boolean checkMaskSecond()
    {
        if (checkIfEntered(view.getMaskSecond())) {return false;}
        if ((Integer.parseInt(view.getMaskSecond().getText()) >= 0) && (Integer.parseInt(view.getMaskSecond().getText()) <= 255))
        {
            return true;
        }
        return false;
    }

    private boolean checkMaskThird()
    {
        if (checkIfEntered(view.getMaskThird())) {return false;}
        if ((Integer.parseInt(view.getMaskThird().getText()) >= 0) && (Integer.parseInt(view.getMaskThird().getText()) <= 255))
        {
            return true;
        }
        return false;
    }

    private boolean checkMaskFourth()
    {
        if (checkIfEntered(view.getMaskFourth())) {return false;}
        if ((Integer.parseInt(view.getMaskFourth().getText()) >= 0) && (Integer.parseInt(view.getMaskFourth().getText()) < 255))
        {
            return true;
        }
        return false;
    }
    //Methods that check the IP Address for validity
    private boolean checkAddFirst()
    {
        if (checkIfEntered(view.getAddFirst())) {return false;}
        if ((Integer.parseInt(view.getAddFirst().getText()) > 0) && (Integer.parseInt(view.getAddFirst().getText()) <= 255))
        {
            return true;
        }
        return false;
    }

    private boolean checkAddSecond()
    {
        if (checkIfEntered(view.getAddSecond())) {return false;}
        if ((Integer.parseInt(view.getAddSecond().getText()) >= 0) && (Integer.parseInt(view.getMaskSecond().getText()) <= 255))
        {
            return true;
        }
        return false;
    }

    private boolean checkAddThird()
    {
        if (checkIfEntered(view.getAddThird())) {return false;}
        if ((Integer.parseInt(view.getAddThird().getText()) >= 0) && (Integer.parseInt(view.getMaskThird().getText()) <= 255))
        {
            return true;
        }
        return false;
    }

    private boolean checkAddFourth()
    {
        if (checkIfEntered(view.getAddFourth())) {return false;}
        if ((Integer.parseInt(view.getAddFourth().getText()) >= 0) && (Integer.parseInt(view.getMaskFourth().getText()) <= 255))
        {
            return true;
        }
        return false;
    }

    //Checks the text field passed to this function to see if any data has been entered
    private boolean checkIfEntered(JTextField e)
    {
        return e.getText().equals("");
    }

    public static void main (String[] args)
    {
        JFrame frame = new Control("Subnetter");
        frame.setVisible(true);
    }
}
