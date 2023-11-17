package Chat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class LAchat {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Как вас зовут?: ");
        String nick = scanner.nextLine();

        System.out.print("\"Сервер - 'S'\" или \"Клиент - 'C'\"?: ");
        String role = scanner.nextLine();
        if (role.equalsIgnoreCase("S")) {
            Servik(nick);
        } else if (role.equalsIgnoreCase("C")) {
            System.out.print("Введите IP сервера: ");
            String serverIP = scanner.nextLine();
            Clientik(nick, serverIP);
        } else {
            System.out.println("Ошибка");
        }
        scanner.close();
    }

    private static void Servik(String nick) {
        try {
            ServerSocket serverSocket = new ServerSocket(8070);
            System.out.println("Сервер запущен, ожидание подключения...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Подключено с адресом: " + clientSocket.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            out.println("Соединено с " + nick + "!");
            Thread.sleep(200);

            new Thread(() -> {
                try {
                    while (true) {
                        String clientmess = in.readLine();
                        if (clientmess == null) {
                            break;
                        }
                        System.out.println(clientmess);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            try {
                while (true) {
                    System.out.print(nick + ": ");
                    String serverMessage = consoleInput.readLine();
                    out.println(nick + ": " + serverMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void Clientik(String nick, String serverIP) {
        try {
            Socket socket = new Socket(serverIP, 8070);
            System.out.println("Подключение к серверу");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Chat with " + nick + "!");
            Thread.sleep(200);

            new Thread(() -> {
                try {
                    while (true) {
                        String serverMessage = in.readLine();
                        if (serverMessage == null) {
                            break;
                        }
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            try {
                while (true) {
                    System.out.print(nick + ": ");
                    String clientmess = consoleInput.readLine();
                    out.println(nick + ": " + clientmess);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}