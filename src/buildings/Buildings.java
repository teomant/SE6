package buildings;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.dwelling.hotel.HotelBuilding;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by Teomant on 09.10.2017.
 */
public class Buildings {

    private static BuildingFactory factory = new DwellingFactory();

    public static void setBuildingFactory(BuildingFactory factory1)
    {
        factory=factory1;
    }

    public static void outputBuilding (Building building, OutputStream out) throws IOException
    {
        DataOutputStream outputStream = new DataOutputStream(out);
        outputStream.writeInt(building.getFloorsNumber());
        for (int i=0;i<building.getFloorsNumber();i++)
        {
            Floor floor = building.getFloor(i);
            outputStream.writeInt(floor.getSpacesNumber());

            for (int j=0;j<floor.getSpacesNumber();j++)
            {
                Space space = floor.getSpace(j);
                outputStream.writeInt(space.getRoomNumber());
                outputStream.writeFloat(space.getArea());
            }
        }
    }

    public static Building inputBuilding (InputStream in) throws IOException
    {
        DataInputStream inputStream = new DataInputStream(in);

        int floorsNumber = inputStream.readInt();

        Floor[] floorsArray=new Floor[floorsNumber];
        for (int i=0;i<floorsNumber;i++)
        {

            int spacesNumber = inputStream.readInt();
            Space[] spacesArray=new Space[spacesNumber];
            for (int j=0;j<spacesNumber;j++)
            {
                int rooms=inputStream.readInt();
                float area=inputStream.readFloat();
                spacesArray[j]=factory.createSpace(rooms,(double)area);
            }

            floorsArray[i]=factory.createFloor(spacesArray);
        }

        return factory.createBuilding(floorsArray);


    }


    public static void writeBuilding (Building building, Writer out) throws IOException
    {
        String s="";
        s=s+building.getFloorsNumber()+" ";
        for (int i=0;i<building.getFloorsNumber();i++)
        {
            Floor floor = building.getFloor(i);
            s=s+floor.getSpacesNumber()+" ";
            for (int j=0;j<floor.getSpacesNumber();j++)
            {
                Space space = floor.getSpace(j);
                s=s+space.getRoomNumber()+" "+space.getArea()+" ";
            }
        }
        s=s;

            out.write(s);
            out.flush();

    }

    public static Building readBuilding (Reader in) throws IOException
    {
        StreamTokenizer token = new StreamTokenizer(in);

        token.nextToken();

        int floorsNumber=(int)token.nval;

        Floor[] floors = new Floor[floorsNumber];

        for (int i=0;i<floorsNumber;i++)
        {
            token.nextToken();
            int spacesNumber=(int)token.nval;
            Space[] flats = new Space[spacesNumber];
            for (int j=0;j<spacesNumber;j++)
            {
                token.nextToken();
                int rooms= (int)token.nval;
                token.nextToken();
                float area = (float)token.nval;
                flats[j]=factory.createSpace(rooms,(float)area);
            }
            floors[i]=factory.createFloor(flats);
        }

        return factory.createBuilding(floors);

    }

    public static void writeBuildingFormat (Building building1, Writer out)
    {
        Building building=building1;
        PrintWriter outPrint = new PrintWriter(out);
        int floorsNumber=building.getFloorsNumber();
        outPrint.printf("%d ", floorsNumber);

        for (int i=0;i<floorsNumber;i++)
        {
            Floor floor = building.getFloor(i);
            int spacesNumber=floor.getSpacesNumber();
            outPrint.printf("%d ", spacesNumber);
            for (int j=0;j<spacesNumber;j++)
            {
                Space space=floor.getSpace(j);
                outPrint.printf("%d ", space.getRoomNumber());
                outPrint.printf("%f ",space.getArea());
            }
        }
        outPrint.printf("\n");
        //outPrint.flush();
    }

    public static Building readBuilding (Scanner in) throws IOException
    {

        int floorsNumber=in.nextInt();

        Floor[] floors = new Floor[floorsNumber];

        for (int i=0;i<floorsNumber;i++)
        {

            int spacesNumber=in.nextInt();
            Space[] flats = new Space[spacesNumber];
            for (int j=0;j<spacesNumber;j++)
            {
                int rooms= in.nextInt();
                float area = in.nextFloat();
                flats[j]=factory.createSpace(rooms,(float)area);
            }
            floors[i]=factory.createFloor(flats);
        }

        return factory.createBuilding(floors);

    }


    public static void /*Floor*/ compareToSort(Floor floor)
    {
        Space[] flats = new Space[floor.getSpacesNumber()];
        for (int i=0;i<floor.getSpacesNumber();i++)
        {
            flats[i]=floor.getSpace(i);
        }

        for (int i=0;i<flats.length;i++)
        {
            for (int j=0;j<flats.length-i-1;j++)
            {
                if (flats[j].compareTo(flats[j+1])>0)
                {
                    Space buffer = flats[j+1];
                    flats[j+1] = flats[j];
                    flats[j]=buffer;
                }
            }
        }

        for (int i=0;i<floor.getSpacesNumber();i++)
        {
            floor.setSpace(i,flats[i]);
        }

    }

    public static void/*Building*/ compareToSort(Building building)
    {
        Floor[] floors = new Floor[building.getFloorsNumber()];
        for (int i=0;i<building.getFloorsNumber();i++)
        {
            floors[i]=building.getFloor(i);
        }

        for (int i=0;i<floors.length;i++)
        {
            for (int j=0;j<floors.length-i-1;j++)
            {
                if (floors[j].compareTo(floors[j+1])>0)
                {
                    Floor buffer = floors[j+1];
                    floors[j+1] = floors[j];
                    floors[j]=buffer;
                }
            }
        }

        for (int i=0;i<building.getFloorsNumber();i++)
        {
            building.setFloor(i,floors[i]);
        }

    }

    public static<T extends Comparable<T>> void compareToSort(T[] objects)
    {
        for (int i=0;i<objects.length;i++)
        {
            for (int j=0;j<objects.length-i-1;j++)
            {
                if (objects[j].compareTo(objects[j+1])>0)
                {
                    T buffer = objects[j+1];
                    objects[j+1] = objects[j];
                    objects[j]=buffer;
                }
            }
        }
    }


    public static void /*Floor*/ comparatorSort(Floor floor,SpacesComparator sc)
    {

        Space[] flats = new Space[floor.getSpacesNumber()];
        for (int i=0;i<floor.getSpacesNumber();i++)
        {
            flats[i]=floor.getSpace(i);
        }

        for (int i=0;i<flats.length;i++)
        {
            for (int j=0;j<flats.length-i-1;j++)
            {
                if (sc.compare(flats[j],flats[j+1])>0)
                {
                    Space buffer = flats[j+1];
                    flats[j+1] = flats[j];
                    flats[j]=buffer;
                }
            }
        }

        for (int i=0;i<floor.getSpacesNumber();i++)
        {
            floor.setSpace(i,flats[i]);
        }
        //return new DwellingFloor(flats);
    }

    public static void/*Building*/ comparatorSort(Building building,FloorsComparator fc)
    {
        Floor[] floors = new Floor[building.getFloorsNumber()];
        for (int i=0;i<building.getFloorsNumber();i++)
        {
            floors[i]=building.getFloor(i);
        }

        for (int i=0;i<floors.length;i++)
        {
            for (int j=0;j<floors.length-i-1;j++)
            {
                if (fc.compare(floors[j],floors[j+1])>0)
                {
                    Floor buffer = floors[j+1];
                    floors[j+1] = floors[j];
                    floors[j]=buffer;
                }
            }
        }

        for (int i=0;i<building.getFloorsNumber();i++)
        {
            building.setFloor(i,floors[i]);
        }
        //return new Dwelling(floors);
    }

    public static<T extends Comparator<T>> void comparatorSort(T[] objects, Comparator<T> comparator)
    {
        for (int i=0;i<objects.length;i++)
        {
            for (int j=0;j<objects.length-i-1;j++)
            {
                if (comparator.compare(objects[j],objects[j+1])>0)
                {
                    T buffer = objects[j+1];
                    objects[j+1] = objects[j];
                    objects[j]=buffer;
                }
            }
        }
    }

    public static Space createSpace(double area)
    {
        return factory.createSpace(area);
    }
    public static Space createSpace(int roomsCount, double area)
    {
        return factory.createSpace(roomsCount,area);
    }
    public static Floor createFloor(int spacesCount)
    {
        return factory.createFloor(spacesCount);
    }
    public static Floor createFloor(Space[] spaces)
    {
        return factory.createFloor(spaces);
    }
    public static Building createBuilding(int floorsCount, int[] spacesCounts)
    {
        return factory.createBuilding(floorsCount,spacesCounts);
    }
    public static Building createBuilding(Floor[] floors)
    {
        return factory.createBuilding(floors);
    }


}
