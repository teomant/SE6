package buildings;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by Teomant on 30.09.2017.
 */
public interface Building extends Serializable, Cloneable, Iterable {

    public int getFloorsNumber();

    public int getSpacesNumber();

    public float getFullArea();

    public int getRoomNumber();

    public Floor[] getFloors();

    public Floor getFloor(int number);

    public void setFloor(int number, Floor floor);

    public Space getSpace(int number);

    public void setSpace(int number, Space office);

    public void addSpace(int number, Space office);

    public void removeSpace(int number);

    public Space getBestSpace();

    public Space[] getSpacesSorted();

    public Object clone();

    public Iterator iterator();
}
