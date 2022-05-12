package net;

import com.esotericsoftware.kryonet.*;
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

    /**
     * CRServer Constructor(s).
     *
     * Initializes the Server and creates the map.
     * with or without a seed
     */
    public CRServer(Map map){
        this.server = new Server();
        this.map = map;
        this.seed = map.seed;
    }

    public CRServer(){
        this.server = new Server();
        this.map = new Map();
        this.seed = map.seed;
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
     * @version 0.1.0
     */
    public void start() { this.server.start(); }

    /**
     * Closes the server.
     *
     * @author Marco Marrelli
     * @since 11/05/2022
     * @version 0.1.0
     */
    public void close(){ this.server.close(); }

    public void listen(){
        this.server.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof String) {

                }
            }
        });
    }
}
