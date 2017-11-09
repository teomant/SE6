package buildings.net.server.sequental;

import buildings.Building;
import buildings.Buildings;
import buildings.net.BuildingUnderArrestException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Teomant on 03.11.2017.
 */
public class BinaryServer {

    public static void main(String args[]) {

        int port = 1;
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        DataInputStream inFromClient = null;
        DataOutputStream outToClient = null;

        System.out.println("Server started");

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Waiting for client...");
            clientSocket = serverSocket.accept();
            System.out.println("Get client!");
            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();
            inFromClient = new DataInputStream(in);
            outToClient = new DataOutputStream(out);
        } catch (IOException e) {
            System.out.println(
                    "Could not listen on port: 1");
            System.exit(-1);
        }

        try {
            while (true)
            {
                int type = inFromClient.readInt();
                System.out.println("Got type");
                Building building = Buildings.inputBuilding(inFromClient);
                System.out.println("Got building");
                float cost = building.getFullArea()*(1000+500*type);
                if (Math.random()>0.1)
                {
                    outToClient.writeBoolean(true);
                    outToClient.writeFloat(cost);
                }
                if (Math.random()<=0.1)
                {
                    outToClient.writeBoolean(false);
                    throw new BuildingUnderArrestException();
                }
                System.out.println("Cost sended");
                if (type == 999){
                    System.out.println("Finishing...");
                    break;
                }

            }
        } catch (IOException e) {
            System.out.println("communication error");
            System.exit(-1);
        }

        try {
            serverSocket.close();
            clientSocket.close();
            inFromClient.close();
            outToClient.close();
        } catch (IOException e) {
            System.out.println("communication error");
            System.exit(-1);
        }

    }
}
