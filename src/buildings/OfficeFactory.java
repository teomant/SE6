package buildings;

import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;

/**
 * Created by Teomant on 18.10.2017.
 */
public class OfficeFactory implements BuildingFactory {
    public Space createSpace(double area)
    {
        return new Office((float)area);
    }
    public Space createSpace(int roomsCount, double area)
    {
        return new Office((float)area,roomsCount);
    }
    public Floor createFloor(int spacesCount)
    {
        return new OfficeFloor(spacesCount);
    }
    public Floor createFloor(Space[] spaces)
    {
        return new OfficeFloor(spaces);
    }
    public Building createBuilding(int floorsCount, int[] spacesCounts)
    {
        return new OfficeBuilding(floorsCount,spacesCounts);
    }
    public Building createBuilding(Floor[] floors)
    {
        return new OfficeBuilding(floors);
    }
}