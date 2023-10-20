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
import java.util.Random;

public class MusicPlayerApp {

    private JTextField urlTextField;
    private JButton playButton;

    public MusicPlayerApp() {
        JFrame frame = new JFrame("Музыкальный плеер");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);

        urlTextField = new JTextField(20);
        playButton = new JButton("Воспроизвести");

        JPanel panel = new JPanel();
        panel.add(new JLabel("URL музыки:"));
        panel.add(urlTextField);
        panel.add(playButton);

        frame.add(panel);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = urlTextField.getText();
                if (!url.isEmpty()) {
                    downloadAndPlayMusic(url);
                }
            }
        });

        frame.setVisible(true);
    }

    private void downloadAndPlayMusic(String url) {
        Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
        Elements audioElements = doc.select("a[href$=.mp3]");

        for (Element audioElement : audioElements) {
            String musicUrl = audioElement.attr("href");
            String name = "music\\Song"+Integer.toString(new Random().nextInt(100))+".mp3";
            try {
                downloadUsingNIO(musicUrl, name);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            playMusic(name);
        }
            
        
        // Ваш код для скачивания и воспроизведения музыки здесь
        // Пример: вызов функций downloadUsingNIO и playMusic с переданным URL
        // Не забудьте обработать исключения
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MusicPlayerApp();
            }
        });
    }
    private static void downloadUsingNIO(String strUrl, String file) throws IOException {
        System.out.println("Скачиваю с сайта: " + strUrl);
        URL url = new URL(strUrl);
        try (InputStream in = url.openStream();
             ReadableByteChannel byteChannel = Channels.newChannel(in);
             FileOutputStream stream = new FileOutputStream(file)) {
            stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
        }
        System.out.println("Скачал, щас как заиграю");
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
}
