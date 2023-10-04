import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

public class App extends JFrame {

    public App() {
        setUndecorated(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Créez un panneau personnalisé pour le fond d'écran
        JPanel backgroundPanel = new JPanel();

        // Utilisez un gestionnaire de disposition pour organiser les composants
        
        backgroundPanel.setLayout(new BorderLayout());

        /*
        JButton closeButton = new JButton("X");
        closeButton.setBackground(Color.RED);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Arial", Font.BOLD, 24));
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setBorder(new LineBorder(Color.RED, 2, true));
        closeButton.setPreferredSize(new Dimension(60, 60)); // Agrandir la taille du bouton
        

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        

        backgroundPanel.add(buttonPanel, BorderLayout.NORTH);

        */

        String[] imagePaths = {"image/movies7.png", "image/spotify.png", "image/youtube.png", "image/orange_tv.png"};

        JPanel imagePanel = new JPanel(new GridLayout(2, 2)){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Dessinez l'image de fond
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
        
        backgroundPanel.add(imagePanel, BorderLayout.CENTER);
        add(backgroundPanel);
        setVisible(true);
    }

    private void openLinkInBrowser(String url) {
        String[] cmd;
        cmd = new String[]{"C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe", "--start-fullscreen", url};
        //cmd = new String[]{"firefox", url,  "--start-fullscreen"};
        try {   
            Process process = new ProcessBuilder(cmd).start();
        } catch (IOException e) {
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
