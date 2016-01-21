/**
 * Created by gval on 21/01/16.
 */

package game;

import javax.swing.*;
import java.awt.*;

public class GameMenu extends JPanel {
    private final JLabel title = new JLabel(
            new ImageIcon("/Users/gval/Documents/Projects/Pong/icons/Pong_Title.png"));

    private JButton play;
    private JButton settings;

    final private Color DARK_GREEN = new Color(0, 160, 0);
    final private int FONT_SIZE = 40;

    GameMenu() {
        setBackground(Color.BLACK);
        setLayout(new FlowLayout(FlowLayout.CENTER, 120, 30));

        play = new JButton("PLAY");
        play.setPreferredSize(new Dimension(300, 100));
        play.setFont(new Font("Futura", Font.BOLD, FONT_SIZE));
        play.setForeground(DARK_GREEN);
//        play.setBackground(Color.DARK_GRAY);
//        play.setOpaque(true);
//        play.setBorderPainted(false);

        settings = new JButton("SETTINGS");
        settings.setPreferredSize(new Dimension(300, 100));
        settings.setFont(new Font("Futura", Font.BOLD, FONT_SIZE));
        settings.setForeground(DARK_GREEN);

        add(title);
        add(play);
        add(settings);
    }
}