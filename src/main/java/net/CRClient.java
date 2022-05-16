package net;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import com.esotericsoftware.kryo.Kryo;

import java.io.IOException;

/**
 * tHE client for the CRServer.
 *
 * @author Marco Marrelli
 * @version 0.1.0
 * @since 11/05/2022
 * @see CRServer
 * @see MainServer
 * @see CRPort
 */
public class CRClient {
    /**
     * Client (Kryonet).
     */
    private Client client;

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

        Kryo kryo = new Kryo();
        kryo.register(CRRequest.class);
        kryo.register(CRResponse.class);
    }

    /**
     * Connects the client to the port of the server.
     *
     * @param host IP of the server (e.g. localhost).
     * @param port port to connect the client to.
     * @author Marco Marrelli
     * @since 11/05/2022
     * @version 0.1.0
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
     * @version 0.1.0
     */
    public void start() { this.client.start(); }

    /**
     * Closes the client.
     *
     * @author Marco Marrelli
     * @since 11/05/2022
     * @version 0.1.0
     */
    public void close(){ this.client.close(); }

    /**
     * Send TCP Packet to Server (e.g. CRRequest).
     *
     * @author Marco Marrelli
     * @param obj object to send (e.g. CRRequest).
     * @since 13/05/2022
     * @version 0.1.0
     */
    public void send(Object obj){ this.client.sendTCP(obj); }

    /**
     * Add the listener to the current Client
     *
     * @author Marco Marrelli
     * @since 12/05/2022
     * @version 0.0.1
     */
    public void addListenerToClient(){
        this.client.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof CRResponse) {
                    CRResponse response = (CRResponse) object;
                }
            }
        });
    }
}
