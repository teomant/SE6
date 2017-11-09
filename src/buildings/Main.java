package buildings;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.DwellingFloorIterator;
import buildings.dwelling.Flat;
import buildings.dwelling.hotel.HotelBuilding;
import buildings.dwelling.hotel.HotelFloor;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;
import buildings.threads.Cleaner;
import buildings.threads.Repairer;
import buildings.threads.SequentalCleaner;
import buildings.threads.SequentalRepairer;

import javax.swing.*;
import java.io.*;
import java.util.Iterator;

import static java.lang.Thread.MAX_PRIORITY;

/**
 * Created by Teomant on 29.09.2017.
 */

public class Main {
    public static void main (String[] args)  throws IOException, ClassNotFoundException, FileNotFoundException
    {

        /*HotelFloor floor0 = new HotelFloor(2);
        HotelFloor floor1 = new HotelFloor(3);
        HotelFloor floor2 = new HotelFloor(3);

        floor0.setStars(2);
        floor1.setStars(1);
        //floor0.addSpace(0,new Flat(500,4));
        //floor1.addSpace(0,new Flat(400,4));
        HotelBuilding b=new HotelBuilding(new HotelFloor[]{floor0,floor1});

        System.out.println(floor1.equals(floor2));

        Iterator i = b.iterator();

        DwellingFloor f = new DwellingFloor(new Space[]{new Flat(250,4),new Flat(450,2),new Flat(150,1)});
        Buildings.compareToSort(f);
        System.out.println(f);
        SpacesComparator sc = new SpacesComparator();

        System.out.println(sc.compare(new Flat(500, 2), new Flat(500,3)));*/

        //System.out.println(b);

        //Dwelling d1=new Dwelling(new DwellingFloor[] {new DwellingFloor(2), new DwellingFloor(5), new DwellingFloor(3)});

        //HotelFloor fl = new HotelFloor(5);

        //System.out.println(fl.getRoomNumber());

        //System.out.println(new Dwelling(new DwellingFloor[] {new DwellingFloor(2), new DwellingFloor(5), new DwellingFloor(3)}));

        /*OfficeBuilding b2=(OfficeBuilding)b.clone();

        System.out.println(b2);

        b.setSpace(1,new Office(700,1));

        System.out.println();
        System.out.println(b);
        System.out.println(b2);*/

        /*DataOutputStream out = new DataOutputStream(new FileOutputStream("out.bin"));

        Buildings.outputBuilding(b,out);
        out.close();

        DataInputStream in = new DataInputStream(new FileInputStream("out.bin"));
        OfficeBuilding b2=(OfficeBuilding) Buildings.inputBuilding(in);
        in.close();
        System.out.println(b2);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obj.dat"));
        oos.writeObject(new Office(999,5));
        oos.close();
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream("obj.dat"));
        Office o=(Office)ois.readObject();
        System.out.println(o);
        ois.close();

        //System.out.println(Buildings.readBuilding(new InputStreamReader(System.in)));
        System.out.println(Buildings.readBuilding(new Scanner(System.in)));
        Buildings.writeBuildingFormat(b,new PrintWriter(System.out));*/

        /*Office office = new Office();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("objects.dat"));
            out.writeObject(floor0);
            out.close();
        }
        catch (IOException e)
        {

        }
        OfficeFloor office1 = null;
        try {
        ObjectInputStream in =  new ObjectInputStream (new FileInputStream("objects.dat"));
        office1= (OfficeFloor) in.readObject();
        in.close();
        }
        catch (IOException e)
        {

        }
        catch (ClassNotFoundException e)
        {

        }
        System.out.println(office1);*/


        /*Floor f1 = new OfficeFloor(10);
        Repairer r = new Repairer(f1);
        Cleaner c = new Cleaner(f1);




//        r.setPriority(MAX_PRIORITY);
//        c.setPriority(1);
//
//        c.start();
//        r.start();

        Semafore semafore = new Semafore(1);

        SequentalRepairer SR = new SequentalRepairer(f1,semafore);
        SequentalCleaner SC = new SequentalCleaner(f1,semafore);

        Thread t1 = new Thread(SR);
        Thread t2 = new Thread(SC);

        t1.start();
        t2.start();*/


        //Dwelling dwelling = new Dwelling(3,new int[]{2,3,1});

        /*DataOutputStream out = new DataOutputStream(new FileOutputStream("out.txt"));

        OutputStreamWriter writer = new OutputStreamWriter(out);

        Buildings.writeBuilding(dwelling, writer);
        Buildings.writeBuilding(dwelling, writer);
        Buildings.writeBuilding(dwelling, writer);

        out.close();*/

//        FileInputStream in = new FileInputStream("out.txt");
//
//        InputStreamReader reader = new InputStreamReader(new FileInputStream("out.txt"));
//
//        while (reader.ready())
//        {
//            Dwelling dwelling1=(Dwelling)Buildings.readBuilding(reader);
//            System.out.println(dwelling1);
//        }

        /*Dwelling dwelling1=(Dwelling)Buildings.readBuilding(reader);
        Dwelling dwelling2=(Dwelling)Buildings.readBuilding(reader);
        Dwelling dwelling3=(Dwelling)Buildings.readBuilding(reader);

        System.out.println(dwelling1);
        System.out.println(dwelling2);
        System.out.println(dwelling3);*/

        //in.close();

        Dwelling dwelling = new Dwelling(20, new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20});

        DataOutputStream out = new DataOutputStream(new FileOutputStream("out1.txt"));

        OutputStreamWriter writer = new OutputStreamWriter(out);

        Buildings.writeBuilding(dwelling, writer);


        GUI app = new GUI();
        app.setVisible(true);

    }


}
