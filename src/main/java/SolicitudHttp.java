import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

final class SolicitudHttp implements Runnable {
    final static String CRLF = "\r\n";
    Socket socket;

    public SolicitudHttp(Socket socket) throws Exception {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            procesarSolicitud();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void procesarSolicitud() throws Exception {
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()));

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        String lineaDeLaSolicitudHttp = in.readLine();

        System.out.println("Solicitud: " + lineaDeLaSolicitudHttp);

        // recoge y muestra las líneas de header.
        String linea = "";

        while ((linea = in.readLine()) != null && !linea.isEmpty()) {
            System.out.println(linea);
        }

        out.write("HTTP/1.1 200 OK" + CRLF);
        out.write("Content-Type: text/html" + CRLF);
        out.write(CRLF);
        out.write("<html><head><body>OLA</body></head></html>");
        // Cierra los streams y el socket.
        out.close();
        in.close();
        socket.close();

    }

}
