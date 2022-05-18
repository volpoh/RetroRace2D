package net;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

/**
 * tHE client for the CRServer.
 *
 * @author Marco Marrelli
 * @version 0.1.0
 * @see CRServer
 * @see MainServer
 * @see CRPort
 * @since 11/05/2022
 */
public class CRClient {
    /**
     * Client (Kryonet).
     */
    private final Client client;

    /**
     * Port of the Server to connect to.
     */
    public CRPort port;

    /**
     * TimeOut of Connection
     */
    final public int TIMEOUT = 5000;

    public CRClient(String host, CRPort port) throws IOException {
        this.client = new Client();
        this.start();
        this.addListenerToClient();
        this.connect(host, port);

        this.port = port;
    }

    /**
     * Connects the client to the port of the server.
     *
     * @param host IP of the server (e.g. localhost).
     * @param port port to connect the client to.
     * @author Marco Marrelli
     * @since 11/05/2022
     */
    public void connect(String host, CRPort port) throws IOException {
        this.client.connect(TIMEOUT, host, port.TCPPort);
        this.port = port;
    }

    /**
     * Starts the client.
     *
     * @author Marco Marrelli
     * @since 11/05/2022
     */
    public void start() {
        this.client.start();
    }

    /**
     * Closes the client.
     *
     * @author Marco Marrelli
     * @since 11/05/2022
     */
    public void close() {
        this.client.close();
    }

    /**
     * Send TCP Packet to Server (e.g. CRRequest).
     *
     * @param obj object to send (e.g. CRRequest).
     * @author Marco Marrelli
     * @since 13/05/2022
     */
    public void send(String obj) {
        this.client.sendTCP(obj);
    }

    /**
     * Add the listener to the current Client
     *
     * @author Marco Marrelli
     * @since 12/05/2022
     */
    public void addListenerToClient() {
        this.client.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof String) {
                }
            }
        });
    }
}
