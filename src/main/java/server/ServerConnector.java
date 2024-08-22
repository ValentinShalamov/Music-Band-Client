package server;

import exceptions.SocketCloseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static messages.ConnectionMessages.*;

public class ServerConnector implements AutoCloseable {
    private final InetAddress address;
    private final int port;
    private final Socket socket;
    private InputStream inStream;
    private OutputStream outStream;

    public ServerConnector(InetAddress address, int port) {
        this.address = address;
        this.port = port;
        this.socket = new Socket();
    }

    public String connect() {
        try {
            socket.connect(new InetSocketAddress(address, port), 10000);
            inStream = socket.getInputStream();
            outStream = socket.getOutputStream();
            return SUCCESSFUL_CONNECT + address.getHostAddress() + ":" + port;
        } catch (UnknownHostException e) {
            return HOST_HAS_NOT_DETERMINED;
        } catch (SocketTimeoutException e) {
            return CONNECTION_TIMEOUT;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    private String readMessage() throws IOException {
        byte[] bytes = inStream.readNBytes(4);
        int messageLength = ByteBuffer.wrap(bytes).getInt();
        bytes = new byte[messageLength];
        inStream.readNBytes(bytes, 0, bytes.length);
        return new String(bytes);
    }

    private byte[] getMessageForSend(String request) {
        byte[] intValueBox = ByteBuffer.allocate(4).putInt(request.length()).array();
        byte[] requestBytes = request.getBytes(StandardCharsets.UTF_8);

        byte[] message = new byte[intValueBox.length + requestBytes.length];
        for (int i = 0; i < message.length; i++) {
            if (i < 4) {
                message[i] = intValueBox[i];
            } else {
                message[i] = requestBytes[i - 4];
            }
        }
        return message;
    }

    public String sendRequest(String request) throws IOException {
        byte[] message = getMessageForSend(request);

        for (byte b : message) {
            outStream.write(b);
            // This code uses for a test
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//
//            }
        }
        outStream.flush();
        return readMessage();
    }

    public String getGreetMessage() throws IOException {
        return readMessage();
    }

    @Override
    public void close() throws Exception {
        try {
            socket.close();
        } finally {
            try {
                if (!socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                throw new SocketCloseException(e.getMessage());
            }
        }
    }
}
