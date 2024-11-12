package server;

import exceptions.ChannelClosedException;
import exceptions.SocketCloseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static messages.ConnectionMessages.*;

public class ServerConnector implements AutoCloseable {
    private final Socket socket;
    private InputStream inStream;
    private OutputStream outStream;

    public ServerConnector(Socket socket) {
        this.socket = socket;
    }

    public String connect(String host, int port) {
        try {
            InetAddress address = InetAddress.getByName(host);
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
        byte[] requestBytes = request.getBytes(StandardCharsets.UTF_8);
        byte[] intValueBox = ByteBuffer.allocate(4).putInt(requestBytes.length).array();
        byte[] message = Arrays.copyOf(intValueBox, intValueBox.length + requestBytes.length);
        System.arraycopy(requestBytes, 0, message, 4, requestBytes.length);
        return message;
    }

    public String sendRequest(String request) throws IOException {
        try {
            outStream.write(getMessageForSend(request));
            outStream.flush();
            return readMessage();
        } catch (IOException e) {
            throw new ChannelClosedException(SERVER_CLOSED_THE_CONNECTION);
        }
    }

    public String getGreetMessage() throws IOException {
        return readMessage();
    }

    @Override
    public void close() throws Exception {
        try {
            socket.close();
        } catch (IOException e) {
            if (!socket.isClosed()) {
                throw new SocketCloseException(e.getMessage());
            }
        }
    }
}
