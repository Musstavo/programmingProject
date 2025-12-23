package EventDriven.guis;

import EventDriven.colors.CommonConstants;

import javax.swing.*;

public class Form extends JFrame {
    public Form(String title) {
        super(title);

        setSize(520, 680);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(null);

        setLocationRelativeTo(null); // null here means the center of the screen (thanks google)

        setResizable(false);

        getContentPane().setBackground(CommonConstants.primaryColor);
    }
}
