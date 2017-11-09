package buildings.threads;

import buildings.Floor;

/**
 * Created by Teomant on 25.10.2017.
 */
public class Cleaner extends Thread {

    private Floor floor;

    public Cleaner (Floor floor)
    {
        this.floor=floor;
    }

    public void run()
    {
        for (int i=0;i<floor.getSpacesNumber();i++)
        {
            System.out.println("Cleaning room number "+ i +" with total area +"+floor.getSpace(i).getArea()+" square meters");
        }
    }
}