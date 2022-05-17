package net;

/**
 * Port class for MainServer and Lobby Servers.
 *
 * @author Marco Marrelli
 * @version 0.1.0
 * @see MainServer
 * @see CRServer
 * @see CRClient
 * @since 11/05/2022
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

    public CRPort(int ID, int TCPPort, boolean isUsed) {
        this.ID = ID;
        this.TCPPort = TCPPort;
        this.isUsed = isUsed;
    }

    /**
     * Port ID Getter.
     *
     * @return int, port ID.
     * @author Marco Marrelli
     * @since 11/05/2022
     */
    public int getID() {
        return ID;
    }

    /**
     * TCP Port Getter.
     *
     * @return int, TCP Port.
     * @author Marco Marrelli
     * @since 11/05/2022
     */
    public int getTCPPort() {
        return TCPPort;
    }

    /**
     * Port Used Status Getter.
     *
     * @return boolean, if port is used or not.
     * @author Marco Marrelli
     * @since 11/05/2022
     */
    public boolean isUsed() {
        return isUsed;
    }

    /**
     * TCP Port Setter.
     *
     * @author Marco Marrelli
     * @since 11/05/2022
     */
    public void setTCPPort(int TCPPort) {
        this.TCPPort = TCPPort;
    }

    /**
     * TCP Used Status Setter.
     *
     * @author Marco Marrelli
     * @since 11/05/2022
     */
    public void setUsed(boolean used) {
        this.isUsed = used;
    }

    @Override
    public String toString() {
        return "RacePortObject{" +
                "ID=" + ID +
                ", TCPPort=" + TCPPort +
                ", isUsed=" + isUsed +
                '}';
    }
}