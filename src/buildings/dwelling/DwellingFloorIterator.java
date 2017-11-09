package buildings.dwelling;

import buildings.Floor;
import buildings.Space;
import buildings.office.OfficeFloor;

import java.util.Iterator;

public class DwellingFloorIterator implements Iterator<Space> {
    private Floor floor;
    private Space current;
    private int currentPosition;

    public DwellingFloorIterator(OfficeFloor floor)
    {
        this.floor=floor;
        this.currentPosition=0;
        current=floor.getSpace(currentPosition);
    }

    public boolean hasNext()
    {
        if (currentPosition<floor.getSpacesNumber())
            return true;
        return false;
    }

    public Space next()
    {
        if (this.hasNext())
        {
            currentPosition=currentPosition+1;
            current=floor.getSpace(currentPosition);
            return current;
        }
        return null;
    }
}