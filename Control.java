/**
 * Controller Class for the Subnetter App
 * User: Robin
 * Date: 1/21/13
 * Time: 12:07 PM
 */

import javax.swing.*;
import java.awt.event.*;

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

        view.getSubmit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCalculatePress();
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
        if (checkMaskFirst() && checkMaskSecond() && checkMaskThird() && checkMaskFourth())
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
        }

        //update the view
    }

    private boolean checkMaskFirst ()
    {
        if ((Integer.parseInt(view.getMaskFirst().getText()) > 0) && (Integer.parseInt(view.getMaskFirst().getText()) == 255))
        {
            return true;
        }
        return false;
    }

    private boolean checkMaskSecond()
    {
        if ((Integer.parseInt(view.getMaskSecond().getText()) >= 0) && (Integer.parseInt(view.getMaskSecond().getText()) <= 255))
        {
            return true;
        }
        return false;
    }

    private boolean checkMaskThird()
    {
        if ((Integer.parseInt(view.getMaskThird().getText()) >= 0) && (Integer.parseInt(view.getMaskThird().getText()) <= 255))
        {
            return true;
        }
        return false;
    }

    private boolean checkMaskFourth()
    {
        if ((Integer.parseInt(view.getMaskFourth().getText()) >= 0) && (Integer.parseInt(view.getMaskFourth().getText()) < 255))
        {
            return true;
        }
        return false;
    }

    private boolean checkAddFirst()
    {
        return false;
    }

    private boolean checkAddSecond()
    {
        return false;
    }

    private boolean checkAddThird()
    {
        return false;
    }

    private boolean checkAddFourth()
    {
        return false;
    }

    public static void main (String[] args)
    {
        JFrame frame = new Control("Subnetter");
        frame.setVisible(true);
    }
}
