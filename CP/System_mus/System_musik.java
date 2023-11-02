import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class System_musik {

    private static final String IN_FILE_TXT = "C:\\Users\\datch\\OneDrive\\Документы\\GitHub\\Ekzamen\\CP\\System_mus\\inFile.txt";
    private static final String OUT_FILE_TXT = "C:\\Users\\datch\\OneDrive\\Документы\\GitHub\\Ekzamen\\CP\\System_mus\\outFile.txt";
    private static final String PATH_TO_MUSIC = "C:\\Users\\datch\\OneDrive\\Документы\\GitHub\\Ekzamen\\CP\\System_mus\\music\\";
    private static final String PATH_TO_IMG = "C:\\Users\\datch\\OneDrive\\Документы\\GitHub\\Ekzamen\\CP\\System_mus\\photos\\";
    private static final String FOR_IMG_FILE = "C:\\Users\\datch\\OneDrive\\Документы\\GitHub\\Ekzamen\\CP\\System_mus\\ForImgFile.txt";
    private static final String FOR_OUT_IMG = "C:\\Users\\datch\\OneDrive\\Документы\\GitHub\\Ekzamen\\CP\\System_mus\\ForOutImg.txt";
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
        try (BufferedReader inFile = new BufferedReader(new FileReader(FOR_IMG_FILE));
             BufferedWriter outFile = new BufferedWriter(new FileWriter(FOR_OUT_IMG))) {
                while ((Url = inFile.readLine()) != null) {
                Document doc = Jsoup.connect(Url).get();
                Elements imgEl = doc.select("a[href$=.jpg]");
                for (Element ImgElem : imgEl) {
                    String imgUrl = ImgElem.attr("href");
                    outFile.write(imgUrl + "\n");
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
                    MusicandImgDownloader.downloadMusic(music, musicFilePath);
                    count++;
                    MusicandImgDownloader.playMusic(musicFilePath); // Воспроизводим музыку
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    

        try (BufferedReader imgFile = new BufferedReader(new FileReader(FOR_OUT_IMG))) {
                    String img;
                    int count = 0;
                    try {
                        while ((img = imgFile.readLine()) != null) {
                            String imgfilepath = PATH_TO_IMG + String.valueOf(count) + ".jpg";
                            MusicandImgDownloader.downloadImage(img, imgfilepath);
                            count++;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }
}