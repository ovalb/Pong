/**
 * Created by gval on 12/09/15.
 */
package game;
import java.awt.*;
import javax.swing.*;


public class StatusPanel extends JPanel {
    JLabel scoreLeft = new JLabel("0");
    JLabel scoreRight = new JLabel("0");

    StatusPanel() {
        setBackground(Color.BLACK);

        scoreLeft.setFont(new Font("Helvetica", Font.BOLD, 24));
        scoreRight.setFont(new Font("Helvetica", Font.BOLD, 24));

        scoreLeft.setForeground(Color.WHITE);
        scoreRight.setForeground(Color.WHITE);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setBackground(Color.BLACK);
        splitPane.setDividerSize(70);
        splitPane.setLeftComponent(scoreLeft);
        splitPane.setRightComponent(scoreRight);

        this.add(splitPane);
    }
}
