package game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gval on 20/01/16.
 */
public class MenuPanel extends JPanel {
    private JButton pause;
    private JButton musicSwitch;

    private Icon pauseIcon;
    private Icon musicIconOn, musicIconOff;

    MenuPanel() {
        setBackground(Color.BLACK);

        setLayout(new BorderLayout(0, 100));

        pause = new JButton("pause");
        musicSwitch = new JButton("music");

//        pause.setEnabled(false);
//        musicSwitch.setEnabled(false);

        add(pause, BorderLayout.WEST);
        add(musicSwitch, BorderLayout.EAST);
    }
}
