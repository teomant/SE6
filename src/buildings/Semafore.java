package buildings;

/**
 * Created by Teomant on 25.10.2017.
 */
public class Semafore {

    private boolean repaired;
    private int count;



    public Semafore (int count)
    {
        repaired=false;
        this.count=count;
    }

    public void acquire()
    {

        while (count<1)
        {
            if (count<0)
                System.out.println("Error");
        }

        count--;


    }

    public void release()
    {
        count ++;

    }

    public void setRepaired()
    {
        repaired=true;

    }

    public void setCleared()
    {
        repaired=false;
    }

    public void waitRepaired()
    {
        while (!repaired)
        {
            if (count<0)
                System.out.println("Error");
        }
    }

    public void waitCleared()
    {
        while (repaired)
        {
            if (count<0)
                System.out.println("Error");
        }

    }

}
