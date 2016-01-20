/**
 * Created by gval on 12/09/15.
 */
package game;
import java.awt.*;
import javax.swing.*;


public class StatusPanel extends JPanel {
    JLabel scoreLeft = new JLabel("0");
    JLabel scoreRight = new JLabel("0");
    JLabel separator = new JLabel(" - ");

    StatusPanel() {
        setBackground(Color.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        scoreLeft.setFont(new Font("Helvetica", Font.BOLD, 24));
        scoreRight.setFont(new Font("Helvetica", Font.BOLD, 24));
        separator.setFont(new Font("Helvetica", Font.BOLD, 24));

        scoreLeft.setForeground(Color.WHITE);
        scoreRight.setForeground(Color.WHITE);
        separator.setForeground(Color.WHITE);

        add(Box.createHorizontalGlue());
        add(scoreLeft);
        add(separator);
        add(scoreRight);
        add(Box.createHorizontalGlue());
    }
}
