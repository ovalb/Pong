/* My first try on a pong game in Java
 * started 11/09/2015
 */

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override public void run() {
              new PongFrame("Pong Game");
            }
        });
    }
}
