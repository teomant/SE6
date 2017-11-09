package buildings;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by Teomant on 30.09.2017.
 */
public interface Floor extends Serializable, Cloneable, Iterable, Comparable<Floor> {

    public int getSpacesNumber();

    public float getFullArea();

    public int getRoomNumber();

    public Space[] getSpaces();

    public Space getSpace(int number);

    public void setSpace(int number, Space space);

    public void addSpace(int number, Space space);

    public void removeSpace(int number);

    public Space getBestSpace();

    public Object clone();

//    public Iterator iterator();

}
