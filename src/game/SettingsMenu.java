/**
 * Created by gval on 21/01/16.
 */
package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsMenu extends JPanel {

    final private int RADIO_SIZE = 30;
    final private ButtonGroup group = new ButtonGroup();
    final private String msgStr = "Change are going to take place immediately";

    private JLabel message = new JLabel("Set difficulty level");
    private JLabel submsg = new JLabel(msgStr);

    private JRadioButton easy = new JRadioButton("EASY", false);
    private JRadioButton medium = new JRadioButton("MEDIUM", true);
    private JRadioButton hard = new JRadioButton("HARD", false);

    final private JButton back = new JButton("BACK");

    SettingsMenu() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 40));
        setBackground(Color.BLACK);

        message.setFont(new Font("Futura", Font.PLAIN, 50));
        submsg.setFont(new Font("Futura", Font.PLAIN, 20));
        easy.setFont(new Font("Futura", Font.PLAIN, RADIO_SIZE));
        medium.setFont(new Font("Futura", Font.PLAIN, RADIO_SIZE));
        hard.setFont(new Font("Futura", Font.PLAIN, RADIO_SIZE));
        back.setFont(new Font("Futura", Font.BOLD, 40));

        message.setForeground(Color.BLUE);
        submsg.setForeground(Color.CYAN);
        easy.setForeground(Color.WHITE);
        medium.setForeground(Color.WHITE);
        hard.setForeground(Color.WHITE);
        back.setForeground(Color.BLUE);

        group.add(easy);
        group.add(medium);
        group.add(hard);

        add(message);
        add(submsg);

        add(easy);
        add(medium);
        add(hard);

        add(back);
    }

    JButton getBack() {
        return back;
    }

    Difficulty getSelectedDifficulty() {
        if (easy.isSelected())
            return Difficulty.EASY;
        if (medium.isSelected())
            return Difficulty.MEDIUM;
        else
            return Difficulty.HARD;
    }
}
