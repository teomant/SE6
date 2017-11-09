package buildings.office;

import buildings.Building;
import buildings.Floor;

import java.util.Iterator;

public class OfficeBuildingIterator implements Iterator {

    private Building building;
    private Floor current;
    private int currentPosition;

    public OfficeBuildingIterator(Building building)
    {
        this.building=building;
        this.current=building.getFloor(0);
        currentPosition=0;
    }

    public boolean hasNext()
    {
        if (currentPosition<building.getFloorsNumber())
            return true;
        return false;
    }

    public Floor next()
    {
        if (this.hasNext())
        {
            currentPosition=currentPosition+1;
            current=building.getFloor(currentPosition);
            return current;
        }
        return null;
    }
}
