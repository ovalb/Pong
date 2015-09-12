/**
 * Created by gval on 12/09/15.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class StatusPanel extends JPanel {
    JLabel score1 = new JLabel("score1");
    JLabel score2 = new JLabel("score2");

    StatusPanel() {
        setLayout(new BorderLayout());
//        setBackground(Color.WHITE);
        add(score1, BorderLayout.WEST);
        add(score2, BorderLayout.EAST);
    }
}
