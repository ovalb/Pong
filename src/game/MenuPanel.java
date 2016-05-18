/**
 * Created by gval on 20/01/16.
 */
package game;

import javax.swing.*;
import java.awt.*;


public class MenuPanel extends JPanel {

    final static String pauseIconLocation = "/Users/gval/Documents/Projects/Pong/icons/Settings.png";
    final static String soundIconLocation = "/Users/gval/Documents/Projects/Pong/icons/Sound.png";
    final static String muteIconLocation = "/Users/gval/Documents/Projects/Pong/icons/Mute.png";

    final public static ImageIcon pauseIcon = new ImageIcon(pauseIconLocation);
    final public static ImageIcon musicOnIcon = new ImageIcon(soundIconLocation);
    final public static ImageIcon musicOffIcon = new ImageIcon(muteIconLocation);

    private JLabel pause;
    private JLabel musicSwitch;

    MenuPanel() {
        setBackground(Color.BLACK);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        pause = new JLabel(pauseIcon);
        musicSwitch = new JLabel(musicOnIcon);
//        musicSwitch = new JLabel(musicOffIcon);

        add(Box.createRigidArea(new Dimension(10, 0)));
        add(pause, BorderLayout.WEST);
        add(Box.createHorizontalGlue());
        add(musicSwitch, BorderLayout.EAST);
        add(Box.createRigidArea(new Dimension(10, 0)));
    }

    JLabel getPause() {
        return pause;
    }

    JLabel getMusicSwitch() {
        return musicSwitch;
    }
}
