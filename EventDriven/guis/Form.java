package EventDriven.guis;

import EventDriven.colors;

import javax.swing.*;

public class Form extends JFrame {
    public Form(String title) {
        super(title);

        setSize(520, 680);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(null);

        setLocationRelativeTo(null);

        setResizable(false);

        getContentPane().setBackground(colors.PRIMARY_COLOR);
    }
}
