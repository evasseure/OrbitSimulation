import javax.swing.*;

/**
 * Created by Erwan on 21/05/2015.
 */
public class Main {

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        MainPanel panel = new MainPanel();

        frame.setContentPane(panel);
        frame.setTitle("Orbit");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 720);
        frame.setVisible(true);

        Simulation simulation = new Simulation(panel);
        simulation.launchSim();
    }
}
