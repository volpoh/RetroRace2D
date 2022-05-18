package net;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Server containing all Lobbies class.
 *
 * @author Marco Marrelli
 * @version 0.3.0
 * @see CRServer
 * @see CRPort
 * @since 11/05/2022
 */
public class MainServer {
    /**
     * Lobby Server List.
     */
    protected static CRServer[] LOBBY_LIST;

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
     */

    public MainServer() {
        MainServer.PORTS = initPorts();
        MainServer.LOBBY_LIST = initServers();
    }

    /**
     * Initialize PORTS.
     *
     * @return CRPort[]
     * @author Marco Marrelli
     * @since 11/05/2022
     */
    public static CRPort @NotNull [] initPorts() {
        List<CRPort> portsList = new LinkedList<>();

        for (int i = STARTING_PORT; i < STARTING_PORT + MAX_LOBBY; i++)
            portsList.add(new CRPort(i - STARTING_PORT, i, false));

        return portsList.toArray(new CRPort[0]);
    }

    /**
     * Initialize LOBBYLIST.
     *
     * @return CRServer[]
     * @author Marco Marrelli
     * @since 11/05/2022
     */
    public static CRServer @NotNull [] initServers() {
        List<CRServer> serversList = new LinkedList<>();

        for (int i = 0; i < MAX_LOBBY; i++)
            serversList.add(null);

        return serversList.toArray(new CRServer[0]);
    }

    /**
     * Returns ports list.
     *
     * @return CRPort[]
     * @author Marco Marrelli
     * @since 11/05/2022
     */
    public static CRPort @NotNull [] getPorts() {
        return PORTS;
    }

    /**
     * Returns free ports list.
     *
     * @return CRPort[]
     * @author Marco Marrelli
     * @since 11/05/2022
     */
    public static CRPort @NotNull [] getFreePorts() {
        List<CRPort> freePortsList = new LinkedList<>();

        for (CRPort port : PORTS)
            if (isPortFree(port.TCPPort))
                freePortsList.add(port);

        return freePortsList.toArray(new CRPort[0]);
    }

    /**
     * Returns if a port is free or not.
     *
     * @return CRPort[]
     * @author Marco Marrelli
     * @since 11/05/2022
     */
    public static boolean isPortFree(int port) {
        Server s = new Server();
        try {
            s.bind(port);
        } catch (IOException e) {
            return false;
        }

        s.close();
        return true;
    }

    public static void main(String[] args) throws IOException {
        new MainServer();

        CRClient[] clients = new CRClient[MAX_LOBBY];

        LOBBY_LIST[0] = new CRServer(PORTS[0]);
        clients[0] = new CRClient("localhost", PORTS[0]);
        clients[0].send("Miao");
    }
}
