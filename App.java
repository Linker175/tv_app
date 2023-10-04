import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent; 

public class App extends JFrame {

    public App() {
        //JPanel backgroundPanel = new JPanel();
        setUndecorated(true); 

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        gd.setFullScreenWindow(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPanel = new JPanel(new BorderLayout());

        String[] imagePaths = {"image/movies7.png", "image/spotify.png", "image/youtube.png", "image/orange_tv.png"};
        
        JPanel imagePanel = new JPanel(new GridLayout(2, 2)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("image/fond.jpg").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        for (int i = 0; i < 4; i++) {
            JLabel imageLabel = new JLabel(new ImageIcon(imagePaths[i]));
            final String url;
            if (i == 0) {
                url = "https://movies7.to/home";
            } else if (i == 1) {
                url = "https://open.spotify.com/";
            } else if (i == 2) {
                url = "https://www.youtube.com/";
                } else {
                    url = "https://chaines-tv.orange.fr/?filtres=all";
                }

                imageLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        openLinkInBrowser(url);
                    }
                });

                imagePanel.add(imageLabel);
        }

        contentPanel.add(imagePanel, BorderLayout.CENTER);
        add(contentPanel);
        setVisible(true);
    }

    private void openLinkInBrowser(String url) {
        try {
            Process process = new ProcessBuilder("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe", url).start();
            Thread.sleep(300);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_F11);
            robot.keyRelease(KeyEvent.VK_F11);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new App();
            }
        });
    }
}
