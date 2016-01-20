/**
 * Created by gval on 20/01/16.
 */
package game;

import javax.swing.*;
import java.awt.*;


public class MenuPanel extends JPanel {
    private JLabel pause;
    private JLabel musicSwitch;

    private ImageIcon pauseIcon = new ImageIcon("/Users/gval/Documents/Projects/Pong/icons/Settings.png");
    private ImageIcon musicIconOn = new ImageIcon("/Users/gval/Documents/Projects/Pong/icons/Sound.png");
    private ImageIcon musicIconOff = new ImageIcon("/Users/gval/Documents/Projects/Pong/icons/Mute.png");

    MenuPanel() {
        setBackground(Color.BLACK);

        setLayout(new BorderLayout());

        System.out.println("icon height: " + musicIconOn.getIconHeight() + " width: " + musicIconOn.getIconWidth());
        System.out.println("icon height: " + musicIconOff.getIconHeight() + " width: " + musicIconOff.getIconWidth());


        pause = new JLabel(pauseIcon);
//        musicSwitch = new JLabel(musicIconOn);
        musicSwitch = new JLabel(musicIconOff);


        add(pause, BorderLayout.WEST);
        add(musicSwitch, BorderLayout.EAST);
    }
}
