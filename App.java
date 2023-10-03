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
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
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

        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        String[] imagePaths = {"image/movies7.png", "image/spotify.png", "image/youtube.png", "image/orange_tv.png"};

        JPanel imagePanel = new JPanel(new GridLayout(2, 2));

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
        buttonPanel.setBackground(Color.GRAY);
        imagePanel.setBackground(Color.GRAY);
        mainPanel.add(imagePanel, BorderLayout.CENTER);

        add(mainPanel);

        setVisible(true);
    }

    private void openLinkInBrowser(String url) {
        String[] cmd;
    
        // Remplacez "C:\\chemin\\vers\\msedge.exe" par le chemin complet vers l'exécutable de Microsoft Edge
        cmd = new String[]{"firefox", "--start-fullscreen", url};
    
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
