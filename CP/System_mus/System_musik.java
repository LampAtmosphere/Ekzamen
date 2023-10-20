import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.FileChannel;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.player.advanced.AdvancedPlayer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class System_musik {

    private static final String IN_FILE_TXT = "C:\\Users\\datch\\OneDrive\\Документы\\GitHub\\Ekzamen\\CP\\System_mus\\inFile.txt";
    private static final String OUT_FILE_TXT = "C:\\Users\\datch\\OneDrive\\Документы\\GitHub\\Ekzamen\\CP\\System_mus\\outFile.txt";
    private static final String PATH_TO_MUSIC = "C:\\Users\\datch\\OneDrive\\Документы\\GitHub\\Ekzamen\\CP\\System_mus\\music\\";

    public static void main(String[] args) {
        String Url;
        try (BufferedReader inFile = new BufferedReader(new FileReader(IN_FILE_TXT));
             BufferedWriter outFile = new BufferedWriter(new FileWriter(OUT_FILE_TXT))) {
            while ((Url = inFile.readLine()) != null) {
                Document doc = Jsoup.connect(Url).get();
                Elements audioElements = doc.select("a[href$=.mp3]");

                for (Element audioElement : audioElements) {
                    String musicUrl = audioElement.attr("href");
                    outFile.write(musicUrl + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader musicFile = new BufferedReader(new FileReader(OUT_FILE_TXT))) {
            String music;
            int count = 0;
            try {
                while ((music = musicFile.readLine()) != null) {
                    String musicFilePath = PATH_TO_MUSIC + String.valueOf(count) + ".mp3";
                    downloadUsingNIO(music, musicFilePath);
                    count++;
                    playMusic(musicFilePath); // Воспроизводим музыку
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downloadUsingNIO(String strUrl, String file) throws IOException {
        System.out.println("Скачиваю с сайта: " + strUrl);
        URL url = new URL(strUrl);
        try (InputStream in = url.openStream();
             ReadableByteChannel byteChannel = Channels.newChannel(in);
             FileOutputStream stream = new FileOutputStream(file)) {
            stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
        }
    }

    private static void playMusic(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Bitstream bitstream = new Bitstream(fileInputStream);
            javazoom.jl.decoder.Header frame = bitstream.readFrame();
            AdvancedPlayer player = new AdvancedPlayer(fileInputStream);
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
