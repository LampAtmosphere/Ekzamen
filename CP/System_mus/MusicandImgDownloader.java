import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class MusicandImgDownloader {

    public static void downloadMusic(String musicUrl, String outputPath) throws IOException {
        downloadUsingNIO(musicUrl, outputPath);
    }

    public static void downloadImage(String imageUrl, String outputPath) throws IOException {
        downloadImageUsingNIO(imageUrl, outputPath);
    }

    public static void playMusic(String musicFilePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(musicFilePath);
            Bitstream bitstream = new Bitstream(fileInputStream);
            javazoom.jl.decoder.Header frame = bitstream.readFrame();
            AdvancedPlayer player = new AdvancedPlayer(fileInputStream);
            player.play();
        } catch (Exception e) {
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
        System.out.println("Скачал, щас как заиграю");
    }

    private static void downloadImageUsingNIO(String imageUrl, String file) throws IOException {
        System.out.println("Скачиваю изображение с сайта: " + imageUrl);
        URL url = new URL(imageUrl);
        try (InputStream in = url.openStream();
             ReadableByteChannel byteChannel = Channels.newChannel(in);
             FileOutputStream stream = new FileOutputStream(file)) {
            stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
        }
        System.out.println("Изображение скачано: " + file);
    }
}
