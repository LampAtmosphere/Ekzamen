import javax.swing.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;

public class MusicPlayerApp {

    private JTextField urlTextField;
    private JButton playButton;
    private JTextField photoUrlTextField;
    private JButton downloadPhotoButton;
    private JLabel photoLabel;

    public MusicPlayerApp() {
        JFrame frame = new JFrame("Музыкальный плеер");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);

        urlTextField = new JTextField(20);
        playButton = new JButton("Воспроизвести");
        photoUrlTextField = new JTextField(20);
        downloadPhotoButton = new JButton("Скачать фото");

        JPanel panel = new JPanel();
        photoLabel = new JLabel();
        panel.add(new JLabel("URL музыки:"));
        panel.add(urlTextField);
        panel.add(playButton);
        panel.add(new JLabel("URL фото:"));
        panel.add(photoUrlTextField);
        panel.add(downloadPhotoButton);
        panel.add(photoLabel);

        frame.add(panel);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = urlTextField.getText();
                if (!url.isEmpty()) {
                    downloadAndPlayFile(url, "music\\Song");
                }
            }
        });
        downloadPhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String photoUrl = photoUrlTextField.getText();
                if (!photoUrl.isEmpty()) {
                    downloadAndSaveImage(photoUrl, "photos\\Photo");
                }
            }
        });

        frame.setVisible(true);
    }

    private void downloadAndPlayFile(String url, String baseFileName) {
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            Elements fileElements = doc.select("a[href$=.mp3]");

            for (Element fileElement : fileElements) {
                String fileUrl = fileElement.attr("href");
                String fileName = baseFileName + new Random().nextInt(100) + ".mp3";
                downloadFile(fileUrl, fileName);
                playMusic(fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downloadAndSaveImage(String url, String baseFileName) {
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            Elements fileElementsimg = doc.select("a[href$=.jpg]");
            for (Element fileElement : fileElementsimg) {
            String fileUrl = fileElement.attr("href");
            String fileName = baseFileName + new Random().nextInt(100) + ".jpg";
            downloadImage(fileUrl, fileName);
            displayImage(fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downloadFile(String fileUrl, String fileName) throws IOException {
        System.out.println("Скачиваю файл с URL: " + fileUrl);
        URL url = new URL(fileUrl);
        try (ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
             FileOutputStream outputStream = new FileOutputStream(fileName)) {
            outputStream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
        }
        System.out.println("Файл скачан: " + fileName);
    }

    private static void playMusic(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            AdvancedPlayer player = new AdvancedPlayer(fileInputStream);
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void downloadImage(String imageUrl, String fileName) throws IOException {
        System.out.println("Скачиваю изображение с URL: " + imageUrl);
        URL url = new URL(imageUrl);
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
        }
        System.out.println("Изображение скачано: " + fileName);
    }

    private void displayImage(String fileName) {
        ImageIcon imageIcon = new ImageIcon(fileName);
        photoLabel.setIcon(imageIcon);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MusicPlayerApp();
            }
        });
    }
}
