package net;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;

import java.util.LinkedList;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

/**
 * Server containing all Lobbies class.
 *
 * @author Marco Marrelli
 * @since 11/05/2022
 * @version 0.3.0
 * @see CRServer
 * @see CRPort
 */
public class MainServer {
    /**
     * Lobby Server List.
     */
    protected static CRServer[] LOBBYLIST;

    /**
     * Port List.
     */
    protected static CRPort[] PORTS;

    /**
     * MAX Number of Lobbies.
     */
    protected static final int MAX_LOBBY = 5;

    /**
     * Starting Port (e.g. 50000).
     */
    protected static final int STARTING_PORT = 50000;

    /**
     * MainServer Constructor.
     * Initialize PORTS and LOBBYLIST.
     *
     * @version 0.1.0
     */

    public MainServer () {
        this.PORTS = initPorts ();
        this.LOBBYLIST = initServers();
    }

    /**
     * Initialize PORTS.
     *
     * @author Marco Marrelli
     * @since 11/05/2022
     * @version 0.2.0
     * @return CRPort[]
     */
    public static CRPort @NotNull [] initPorts () {
        List<CRPort> portsList = new LinkedList<> ();

        for (int i = STARTING_PORT; i < STARTING_PORT + MAX_LOBBY; i ++)
            portsList.add(new CRPort (i - STARTING_PORT, i,false));

        return portsList.toArray (new CRPort[0]);
    }

    /**
     * Initialize LOBBYLIST.
     *
     * @author Marco Marrelli
     * @since 11/05/2022
     * @version 0.4.0
     * @return CRServer[]
     */
    public static CRServer @NotNull [] initServers () {
        List<CRServer> serversList = new LinkedList<> ();

        for (int i = 0; i < MAX_LOBBY; i ++)
            serversList.add(null);

        return serversList.toArray (new CRServer[0]);
    }

    /**
     * Returns ports list.
     *
     * @author Marco Marrelli
     * @since 11/05/2022
     * @version 0.1.0
     * @return CRPort[]
     */
    public static CRPort @NotNull [] getPorts () {
        return PORTS;
    }

    /**
     * Returns free ports list.
     *
     * @author Marco Marrelli
     * @since 11/05/2022
     * @version 0.2.0
     * @return CRPort[]
     */
    public static CRPort @NotNull [] getFreePorts () {
        List<CRPort> freePortsList = new LinkedList<> ();

        for(CRPort port : PORTS)
            if(isPortFree(port.TCPPort)) freePortsList.add(port);

        return freePortsList.toArray (new CRPort[0]);
    }

    /**
     * Returns if a port is free or not.
     *
     * @author Marco Marrelli
     * @since 11/05/2022
     * @version 0.1.0
     * @return CRPort[]
     */
    public static boolean isPortFree (int port) {
        Server s = new Server();
        try { s.bind(port); }
        catch (IOException e) { return false; }

        s.close();
        return true;
    }

    public static void main (String[] args) throws IOException {
        new MainServer();

        CRClient[] clients = new CRClient[MAX_LOBBY];

        for (CRPort port : PORTS){
            LOBBYLIST[port.ID] = new CRServer(port);
            clients[port.ID] = new CRClient("localhost", port);
            clients[port.ID].send("Miao" + port.ID);
        }
    }
}
