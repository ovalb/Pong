/**
 * Created by gval on 12/09/15.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class StatusPanel extends JPanel {
    JLabel scoreLeft = new JLabel("0");
    JLabel scoreRight = new JLabel("0");

    StatusPanel() {
        setLayout(new BorderLayout());
//        setBackground(Color.WHITE);
        add(scoreLeft, BorderLayout.WEST);
        add(scoreRight, BorderLayout.EAST);
    }
}
