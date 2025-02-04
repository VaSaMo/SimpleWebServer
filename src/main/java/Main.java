import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);

        System.out.println("Server Listening...");
        Socket socket =  serverSocket.accept();
        System.out.println("Connection Acepted.");

        BufferedWriter bw = new BufferedWriter(
            new OutputStreamWriter(socket.getOutputStream())
        );

        bw.write("HTTP/1.1 200 OK\n");
        bw.write("Content-Type: text/html\n");
        bw.write("\r\n");
        bw.write("<html><head><body>Computación</body></head></html>");
        bw.flush();

    }
}
