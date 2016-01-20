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
    private ImageIcon musicOnIcon = new ImageIcon("/Users/gval/Documents/Projects/Pong/icons/Sound.png");
    private ImageIcon musicOffIcon = new ImageIcon("/Users/gval/Documents/Projects/Pong/icons/Mute.png");

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
}
