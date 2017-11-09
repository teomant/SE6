package buildings.dwelling;

import buildings.Building;
import buildings.Floor;
import buildings.office.OfficeBuilding;

import java.util.Iterator;

public class DwellingIterator implements Iterator<Floor> {

    private Building building;
    private Floor current;
    private int currentPosition;

    public DwellingIterator(Building building)
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
