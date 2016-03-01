/**
 * Created by gval on 21/01/16.
 */
package game;

import javax.swing.*;
import java.awt.*;

public class SettingsMenu extends JPanel {

    final private int RADIO_SIZE = 30;
    final private ButtonGroup group = new ButtonGroup();

    private JLabel message = new JLabel("Set difficulty level");
    private JRadioButton easy = new JRadioButton("EASY", false);
    private JRadioButton medium = new JRadioButton("MEDIUM", true);
    private JRadioButton hard = new JRadioButton("HARD", false);

    final private JButton back = new JButton("BACK");

    SettingsMenu() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 48));
        setBackground(Color.BLACK);

        message.setFont(new Font("Futura", Font.PLAIN, 50));
        easy.setFont(new Font("Futura", Font.PLAIN, RADIO_SIZE));
        medium.setFont(new Font("Futura", Font.PLAIN, RADIO_SIZE));
        hard.setFont(new Font("Futura", Font.PLAIN, RADIO_SIZE));
        back.setFont(new Font("Futura", Font.BOLD, 40));

        message.setForeground(Color.BLUE);
        easy.setForeground(Color.WHITE);
        medium.setForeground(Color.WHITE);
        hard.setForeground(Color.WHITE);
        back.setForeground(Color.BLUE);

        group.add(easy);
        group.add(medium);
        group.add(hard);

        add(message);
        add(easy);
        add(medium);
        add(hard);
        add(back);
    }

    JButton getBack() {
        return back;
    }
}
