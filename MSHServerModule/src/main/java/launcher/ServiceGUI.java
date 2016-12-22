package launcher;


import network.ServerHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SilverNarcissus on 2016/12/10.
 */
public class ServiceGUI {
    private JPanel panel;
    private Box vBox;
    private Box box1;
    private Box box2;
//    private Box box3;
    private JLabel portLabel;
    private TextField portTextField;
    private JButton startButton;
    private JButton endButton;
    private TextField logTextField;

    void showGUI() {
        JFrame frame = new JFrame("Server");
        panel = new JPanel();
        frame.add(panel);
        //
        vBox = new Box(BoxLayout.Y_AXIS);
        box1 = new Box(BoxLayout.X_AXIS);
        box2 = new Box(BoxLayout.X_AXIS);
        vBox.add(box1);
        vBox.add(box2);
        panel.add(vBox);
        //
        portLabel = new JLabel("Port: ");
        portTextField = new TextField("1099");
        box1.add(portLabel);
        box1.add(portTextField);
        //
        startButton = new JButton("Start");
        endButton = new JButton("Stop");
        startButton.addActionListener(new StartListener());
        endButton.addActionListener(new EndListener());
        box2.add(startButton);
        box2.add(endButton);
        //
        logTextField = new TextField();
        vBox.add(logTextField);
        logTextField.setEditable(false);
        //
        frame.setVisible(true);
        frame.setBounds(200, 200, 400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class StartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int port = Integer.parseInt(portTextField.getText());

            if(ServerHelper.getServerHelper(port) != null) {
                portTextField.setEditable(false);
                System.out.println("Connect Success");
                logTextField.setText("Connect Success");
            }else{
                System.out.println("Connect Failed");
                logTextField.setText("Connect Failed");
            }

        }
    }

    class EndListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            portTextField.setEditable(true);

            ServerHelper.disableNetwork();
            System.out.println("Connect Disable");
            logTextField.setText("Connect Disable");
        }
    }
}
