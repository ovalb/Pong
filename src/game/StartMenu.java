/**
 * Created by gval on 21/01/16.
 */

package game;

import com.sun.codemodel.internal.JOp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartMenu extends JPanel {
    private final JLabel title = new JLabel(
            new ImageIcon("/Users/gval/Projects/Pong/icons/Pong_Title.png"));

    private JButton multiplay;
    private JButton singleplay;
    private JButton settings;

    final private Color DARK_GREEN = new Color(0, 160, 0);
    final private int FONT_SIZE = 40;

    StartMenu() {
        setBackground(Color.BLACK);
        setLayout(new FlowLayout(FlowLayout.CENTER, 120, 25));

        singleplay = new JButton("PLAY VS CPU");
        singleplay.setPreferredSize(new Dimension(300, 100));
        singleplay.setFont(new Font("Futura", Font.BOLD, 30));
        singleplay.setForeground(DARK_GREEN);

        multiplay = new JButton("PLAY VS FRIEND");
        multiplay.setPreferredSize(new Dimension(300, 100));
        multiplay.setFont(new Font("Futura", Font.BOLD, 30));
        multiplay.setForeground(DARK_GREEN);

        settings = new JButton("SETTINGS");
        settings.setPreferredSize(new Dimension(300, 100));
        settings.setFont(new Font("Futura", Font.BOLD, FONT_SIZE));
        settings.setForeground(DARK_GREEN);

        add(title);
        add(singleplay);
        add(multiplay);
        add(settings);
    }
    JButton getSingleplay() {
        return singleplay;
    }

    JButton getMultiplay() {
        return multiplay;
    }
    JButton getSettings() {
        return settings;
    }
}
