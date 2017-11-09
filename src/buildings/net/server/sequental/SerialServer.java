package buildings.net.server.sequental;

import buildings.Building;
import buildings.Buildings;
import buildings.dwelling.Dwelling;
import buildings.dwelling.hotel.HotelBuilding;
import buildings.net.BuildingUnderArrestException;
import buildings.office.OfficeBuilding;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Teomant on 03.11.2017.
 */
public class SerialServer {
    public static void main(String args[]) {

        int port = 1;
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        ObjectInputStream inFromClient = null;
        ObjectOutputStream outToClient = null;

        System.out.println("Server started");

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Waiting for client...");
            clientSocket = serverSocket.accept();
            System.out.println("Get client!");
            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();
            inFromClient = new ObjectInputStream(in);
            outToClient = new ObjectOutputStream(out);
        } catch (IOException e) {
            System.out.println(
                    "Could not listen on port: 1");
            System.exit(-1);
        }

        try {
            while (true)
            {

                float cost=0;
                Object o = inFromClient.readObject();

                System.out.println("Got building");

                if (o instanceof Dwelling)
                {
                    Dwelling dwelling = (Dwelling) o;
                    cost=dwelling.getFullArea()*1000;
                }
                if (o instanceof OfficeBuilding)
                {
                    OfficeBuilding dwelling = (OfficeBuilding) o;
                    cost=dwelling.getFullArea()*1500;
                }
                if (o instanceof HotelBuilding)
                {
                    HotelBuilding dwelling = (HotelBuilding) o;
                    cost=dwelling.getFullArea()*2000;
                }

                if (o instanceof Integer)
                {
                    System.out.println("Finishing");
                    break;
                }

                if (Math.random()>0.1)
                {
                    outToClient.writeFloat(cost);
                }
                if (Math.random()<=0.1)
                {
                    outToClient.writeObject(new BuildingUnderArrestException());
                    throw new BuildingUnderArrestException();
                }
                System.out.println("Cost sended");

            }
        } catch (IOException e) {
            System.out.println("communication error");
            System.exit(-1);
        } catch (ClassNotFoundException e) {

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
