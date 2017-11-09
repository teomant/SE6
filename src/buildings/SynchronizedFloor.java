package buildings;

/**
 * Created by Teomant on 25.10.2017.
 */
public class SynchronizedFloor {

    private Floor floor;

    public SynchronizedFloor (Floor floor)
    {
        this.floor=floor;
    }

    public synchronized int getSpacesNumber()
    {
        return floor.getSpacesNumber();
    }

    public synchronized float getFullArea()
    {
        return floor.getFullArea();
    }

    public synchronized int getRoomNumber()
    {
        return floor.getRoomNumber();
    }

    public synchronized Space[] getSpaces()
    {
        return floor.getSpaces();
    }

    public synchronized Space getSpace(int number)
    {
        return floor.getSpace(number);
    }

    public synchronized void setSpace(int number, Space space)
    {
        floor.setSpace(number,space);
    }

    public synchronized void addSpace(int number, Space space)
    {
        floor.addSpace(number, space);
    }

    public synchronized void removeSpace(int number)
    {
        floor.removeSpace(number);
    }

    public synchronized Space getBestSpace()
    {
        return floor.getBestSpace();
    }

    public Object clone()
    {
        return floor.clone();
    }

    public boolean equals (Object object)
    {
        return floor.equals(object);
    }

    public int hashCode()
    {
        return floor.hashCode();
    }

    public String toString()
    {
        return floor.toString();
    }

}
