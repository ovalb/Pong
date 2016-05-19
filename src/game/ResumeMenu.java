package game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gval on 20/05/16.
 */
public class ResumeMenu extends JPanel {
    private final JLabel title = new JLabel(
                new ImageIcon("/Users/gval/Documents/Projects/Pong/icons/Pong_Title.png"));

        private JButton resume;
        private JButton settings;

        final private Color DARK_GREEN = new Color(0, 160, 0);
        final private int FONT_SIZE = 40;

        ResumeMenu() {
            setBackground(Color.BLACK);
            setLayout(new FlowLayout(FlowLayout.CENTER, 120, 30));

            resume = new JButton("RESUME");
            resume.setPreferredSize(new Dimension(300, 100));
            resume.setFont(new Font("Futura", Font.BOLD, FONT_SIZE));
            resume.setForeground(DARK_GREEN);

            settings = new JButton("SETTINGS");
            settings.setPreferredSize(new Dimension(300, 100));
            settings.setFont(new Font("Futura", Font.BOLD, FONT_SIZE));
            settings.setForeground(DARK_GREEN);

            add(title);
            add(resume);
            add(settings);
        }
        JButton getResume() {
            return resume;
        }

        JButton getSettings() {
            return settings;
        }
    }
