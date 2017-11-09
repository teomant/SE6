package buildings.net.client;

import buildings.*;
import buildings.net.BuildingUnderArrestException;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Teomant on 03.11.2017.
 */
public class SerialClient {

    public static void main(String args[])     {
        Socket socket = null;
        ObjectOutputStream outToSocket = null;
        ObjectInputStream inFromSocket = null;
        FileInputStream inFromFile1 = null;
        FileInputStream inFromFile2 = null;
        FileOutputStream outToFile3 = null;



        System.out.println("Trying to connect to server...");

        try {
            socket = new Socket("127.0.0.1", 1);
            outToSocket = new ObjectOutputStream(socket.getOutputStream());
            inFromSocket = new ObjectInputStream(socket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: netcracker.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to:" +
                    "netcracker.");
            System.exit(1);
        }

        System.out.println("Connected!");

        System.out.println("Opening files");

        try {
            inFromFile1=new FileInputStream(args[0]);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File 1 not found!");
        }

        try {
            inFromFile2=new FileInputStream(args[1]);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File 2 not found!");
        }

        try {
            outToFile3=new FileOutputStream(args[2]);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File 3 not found!");
        }

        System.out.println("Files opened");

        System.out.println("Working with server");

        InputStreamReader reader1 = new InputStreamReader(inFromFile1);
        InputStreamReader reader2 = new InputStreamReader(inFromFile2);
        OutputStreamWriter writer3 = new OutputStreamWriter(outToFile3);

        StreamTokenizer token = new StreamTokenizer(reader2);

        try {
            token.nextToken();
            while (reader1.ready())
            {
                int type =(int)token.nval;
                token.nextToken();
                if (type == 0)
                {
                    Buildings.setBuildingFactory(new DwellingFactory());
                }
                if (type == 1)
                {
                    Buildings.setBuildingFactory(new OfficeFactory());
                }
                if (type == 2)
                {
                    Buildings.setBuildingFactory(new HotelFactory());
                }
                Building building = Buildings.readBuilding(reader1);
                outToSocket.writeObject(building);
                Object o = inFromSocket.readObject();
                if (o instanceof BuildingUnderArrestException)
                {
                    throw new BuildingUnderArrestException();
                }
                else
                {
                    float cost = (float) o;
                    writer3.write(cost+" ");
                }
            }
        } catch (IOException e)
        {
            System.out.println("Can`t read from file or send to server");
        } catch (ClassNotFoundException e)
        {

        }

        try {
            socket.close();
            outToSocket.close();
            inFromSocket.close();
            inFromFile1.close();
            inFromFile2.close();
            outToFile3.close();
        } catch (IOException e)
        {
            System.out.println("Error while closing");
        }

    }
}
