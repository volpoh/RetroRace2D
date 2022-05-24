package net;

import com.esotericsoftware.kryonet.*;

import com.example.carrace12.ImplementazioneGrafica;
import map.Map;

import java.io.IOException;
import java.util.List;

/**
 * Car Race Lobby Server class.
 *
 * @author Marco Marrelli
 * @version 0.1.0
 * @see Server
 * @see CRPort
 * @since 11/05/2022
 */
public class CRServer {

    /**
     * Server (Kryonet).
     */
    final Server server;

    /**
     * Port of the Server.
     */
    public CRPort port;

    /**
     * Map of the Lobby game.
     */
    public Map map;

    /**
     * Map's seed.
     */
    private final String seed;

    /**
     * If the Server is running.
     */
    public boolean isUsed;

    /**
     * Client List
     */
    public List<CRClient> clientList;

    /**
     * CRServer Constructor(s).
     * <p>
     * Initializes the Server and creates the map.
     * with or without a seed
     */
    public CRServer(Map map, CRPort port) throws IOException {
        this.server = new Server();
        this.bind(port);
        this.start();

        this.map = map;
        this.seed = map.seed;

        this.addListenerToServer(map);
    }

    public CRServer(CRPort port) throws IOException {
        this.server = new Server();
        this.bind(port);
        this.start();

        this.map = new Map();
        this.seed = map.seed;
        this.isUsed = false;

        this.addListenerToServer(this.map);
    }

    /**
     * Binds the server to the port.
     *
     * @param port port to bind the server to.
     * @author Marco Marrelli
     * @since 11/05/2022
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
     */
    public void close() {
        this.server.close();
        this.isUsed = false;
    }

    /**
     * Getter of the isUsed status.
     *
     * @return boolean, if it's used or not.
     * @author Marco Marrelli
     * @since 12/05/2022
     */
    public boolean isServerUsed() {
        return this.isUsed;
    }

    /**
     * Add the listener to the current Server
     *
     * @throws java.lang.IllegalStateException if the server tries to launch more than one application
     * @author Marco Marrelli
     * @since 12/05/2022
     */
    public void addListenerToServer(Map map) {
        this.server.addListener(new Listener() {
            public void received(Connection connection, Object object){
                //if (object instanceof CRRequest) {
                    ImplementazioneGrafica GUI = new ImplementazioneGrafica();
                    GUI.main(new String[]{ seed });
                //}
            }
        });
    }
}
