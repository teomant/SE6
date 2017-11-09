package buildings.threads;

import buildings.Floor;
import buildings.Semafore;

/**
 * Created by Teomant on 25.10.2017.
 */
public class SequentalCleaner implements Runnable{

    private Floor floor;
    private Semafore semafore;

    public SequentalCleaner(Floor floor, Semafore semafore)
    {
        this.floor=floor;
        this.semafore=semafore;
    }

    public void run()
    {

        for (int i=0;i<floor.getSpacesNumber();i++)
        {
            semafore.waitRepaired();
            semafore.acquire();
            System.out.println("Cleaning room number "+ i +" with total area "+floor.getSpace(i).getArea()+" square meters");
            semafore.setCleared();
            semafore.release();

        }


    }
}