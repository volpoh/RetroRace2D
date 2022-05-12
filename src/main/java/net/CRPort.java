package net;

/**
 * Port class for MainServer and Lobby Servers.
 *
 * @author Marco Marrelli
 * @since 11/05/2022
 * @version 0.1.0
 * @see MainServer
 * @see CRServer
 * @see CRClient
 */
public class CRPort {
    /**
     * Port ID (used is MainServer).
     */
    int ID;

    /**
     * TCP Port value.
     */
    int TCPPort;

    /**
     * If a port is used or not.
     */
    boolean isUsed;

    public CRPort (int ID, int TCPPort, boolean isUsed){
        this.ID = ID;
        this.TCPPort = TCPPort;
        this.isUsed = isUsed;
    }

    /**
     * Port ID Getter.
     *
     * @author Marco Marrelli
     * @since 11/05/2022
     * @version 0.1.0
     * @return int, port ID.
     */
    public int getID () { return ID; }

    /**
     * TCP Port Getter.
     *
     * @author Marco Marrelli
     * @since 11/05/2022
     * @version 0.1.0
     * @return int, TCP Port.
     */
    public int getTCPPort () { return TCPPort; }

    /**
     * Port Used Status Getter.
     *
     * @author Marco Marrelli
     * @since 11/05/2022
     * @version 0.1.0
     * @return boolean, if port is used or not.
     */
    public boolean isUsed () { return isUsed; }

    /**
     * TCP Port Setter.
     *
     * @author Marco Marrelli
     * @since 11/05/2022
     * @version 0.1.0
     */
    public void setTCPPort (int TCPPort) { this.TCPPort = TCPPort; }

    /**
     * TCP Used Status Setter.
     *
     * @author Marco Marrelli
     * @since 11/05/2022
     * @version 0.1.0
     */
    public void setUsed (boolean used) { this.isUsed = used; }

    @Override
    public String toString () {
        return "RacePortObject{" +
                "ID=" + ID +
                ", TCPPort=" + TCPPort +
                ", isUsed=" + isUsed +
                '}';
    }
}