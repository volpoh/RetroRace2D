package net;

import com.esotericsoftware.kryonet.*;
import com.esotericsoftware.kryo.Kryo;

import com.example.carrace12.ImplementazioneGrafica;
import map.Map;

import java.io.IOException;

/**
 * Car Race Lobby Server class.
 *
 * @author Marco Marrelli
 * @since 11/05/2022
 * @version 0.1.0
 * @see Server
 * @see CRPort
 */
public class CRServer {

    /**
     * Server (Kryonet).
     */
    private Server server;

    /**
     * Port of the Server.
     */
    public CRPort port;

    /**
     * Map of the Lobby game.
     */
    private Map map;

    /**
     * Map's seed.
     */
    private String seed;

    public boolean isUsed;

    /**
     * CRServer Constructor(s).
     *
     * Initializes the Server and creates the map.
     * with or without a seed
     */
    public CRServer(Map map, CRPort port) throws IOException {
        this.server = new Server();
        this.bind(port);
        this.start();
        //this.addListenerToServer();

        this.map = map;
        this.seed = map.seed;
    }

    public CRServer(CRPort port) throws IOException {
        this.server = new Server();
        this.start();
        this.addListenerToServer();

        this.map = new Map();
        this.seed = map.seed;
        this.isUsed = false;

        this.bind(port);
    }

    /**
     * Binds the server to the port.
     *
     * @param port port to bind the server to.
     * @author Marco Marrelli
     * @since 11/05/2022
     * @version 0.2.0
     */
    public void bind(CRPort port) throws IOException {
        this.server.bind(port.TCPPort);
        this.port = port;
    }

    /**
     * Starts the server.
     *
     * @author Marco Marrelli
     * @since 11/05/2022
     * @version 0.2.0
     */
    public void start() {
        this.server.start();
        this.isUsed = true;
    }

    /**
     * Closes the server.
     *
     * @author Marco Marrelli
     * @since 11/05/2022
     * @version 0.2.0
     */
    public void close(){
        this.server.close();
        this.isUsed = false;
    }

    /**
     * Getter of the isUsed status.
     *
     * @author Marco Marrelli
     * @since 12/05/2022
     * @version 0.1.0
     * @return boolean, if it's used or not.
     */
    public boolean isServerUsed(){ return this.isUsed; }

    /**
     * Add the listener to the current Server
     *
     * @author Marco Marrelli
     * @since 12/05/2022
     * @version 0.0.1
     */
    public void addListenerToServer(){
        this.server.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                /* IT WORKS, BUT ONLY FOR ONE CLIENT
                ImplementazioneGrafica GUI = new ImplementazioneGrafica();
                GUI.init(new Map());
                GUI.main(null);
                */

                System.out.println("Received: " + object.toString());
            }
        });
    }
}
