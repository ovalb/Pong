/**
 * Created by gval on 21/01/16.
 */
package game;

import javax.swing.*;

public class SettingsMenu extends JPanel {
    private JRadioButton easy = new JRadioButton("EASY");
    private JRadioButton medium = new JRadioButton("MEDIUM");
    private JRadioButton hard = new JRadioButton("HARD");

    SettingsMenu() {
        add(easy);
        add(medium);
        add(hard);
    }
}
