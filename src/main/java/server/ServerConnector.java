package server;

import client.ConsoleUI;

import java.io.*;
import java.net.*;

import static messages.ConnectionMessages.*;

public class ServerConnector {
    private final InetAddress address;
    private final int port;
    private final char[] chars;
    private Reader reader;
    private Writer writer;
    private final ConsoleUI console;

    public ServerConnector(InetAddress address, int port) {
        this.address = address;
        this.port = port;
        this.chars = new char[1024 * 1024];
        this.console = new ConsoleUI(this);
    }

    public void connectAndStart() {
        try (Socket socket = new Socket()) {
            console.showMessage(WAITING_CONNECTION);
            socket.connect(new InetSocketAddress(address, port), 10000);
            try (InputStream inStream = socket.getInputStream();
                 OutputStream outStream = socket.getOutputStream()) {
                try(InputStreamReader inReader = new InputStreamReader(inStream);
                    OutputStreamWriter outWriter = new OutputStreamWriter(outStream)) {
                    reader = inReader;
                    writer = outWriter;
                    console.showMessage(SUCCESSFUL_CONNECT + address.getHostAddress() + ":" + port);
                    int r = reader.read(chars);
                    console.showMessage(new String(chars, 0, r));
                    console.scanRequests();
                }
            }
        } catch (UnknownHostException e) {
            console.showMessage(HOST_HAS_NOT_DETERMINED);
        } catch (ConnectException e) {
            console.showMessage(e.getMessage());
        } catch (SocketTimeoutException e) {
            console.showMessage(CONNECTION_TIMEOUT);
        } catch (IOException e) {
            console.showMessage(e.getMessage());
            console.showMessage(UNEXPECTED_ERROR);
        }
    }

    public String sendRequest(String request) throws IOException {
        writer.write(request);
        writer.flush();
        int r = reader.read(chars);
        if (r == -1) {
            throw new ConnectException(CONNECTION_HAS_DIED);
        }
        return new String(chars, 0, r);
    }
}
