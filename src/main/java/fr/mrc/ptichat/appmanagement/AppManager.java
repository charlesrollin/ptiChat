package main.java.fr.mrc.ptichat.appmanagement;

import main.java.fr.mrc.ptichat.connection.Client;
import main.java.fr.mrc.ptichat.connection.ClientServer;
import main.java.fr.mrc.ptichat.exceptions.NoServerException;

public class AppManager {

    private ConnectionManager connectionManager;
    private ChatManager chatManager;


    public AppManager() {
        this.connectionManager = new ConnectionManager(this);
        this.chatManager = new ChatManager(this);
    }

    public void init() {
        this.connectionManager.createUI();
        this.connectionManager.open();
    }

    public void initChat(String peerIp) {
        this.connectionManager.dispose();
        this.chatManager.create();
        this.chatManager.open(peerIp);
    }

    public void initConnection(String hostIp, int hostPort, int serverPort) {
        try {
            Client client = new Client();
            client.initiateClientSocket(hostIp, hostPort, this.chatManager);
            System.out.println("\n###### Client's Initialized");
            this.initChat(hostIp);
        } catch (NoServerException e) {
            ClientServer clientServer = new ClientServer();
            clientServer.initiateClientServerSocket(serverPort, this.chatManager);
            System.out.println("\n###### Server initialized ######");
            this.initChat(null);
        } catch (Exception e) {
            this.connectionManager.handleConnectionError("C TOUT KC LOL");
        }
    }

}
