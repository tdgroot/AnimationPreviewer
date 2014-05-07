package nl.tdegroot.software.Anim;

import javax.swing.*;

public class Boot {

    public static void main(String[] args) {
        PreviewWindow window = new PreviewWindow();

        JFrame frame = new JFrame();
        frame.add(window);
        frame.pack();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        window.start();

    }

}
