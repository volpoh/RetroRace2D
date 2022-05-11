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
     * For the Map, if it has
     * a seed or not.
     */
    private String hasSeed = "";

    /**
     * Map of the Lobby.
     */
    static Map map;

    /**
     * CRServer Constructor.
     *
     * Initializes the Server and creates the map.
     * with or without a seed
     */
    public CRServer(){
        this.server = new Server();

        if(hasSeed.isEmpty()){ map = new Map(); }
        else{ map = new Map(hasSeed); }
    }

    /**
     * Sets the seed of the map.
     *
     * @param seed seed of the map.
     * @author Marco Marrelli
     * @since 11/05/2022
     * @version 0.1.0
     */
    public void setSeed(String seed){
        hasSeed = seed;
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
}
