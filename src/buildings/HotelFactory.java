package buildings;

import buildings.dwelling.Flat;
import buildings.dwelling.hotel.HotelBuilding;
import buildings.dwelling.hotel.HotelFloor;

/**
 * Created by Teomant on 18.10.2017.
 */
public class HotelFactory implements BuildingFactory {
    public Space createSpace(double area)
    {
        return new Flat((float)area);
    }
    public Space createSpace(int roomsCount, double area)
    {
        return new Flat((float)area,roomsCount);
    }
    public Floor createFloor(int spacesCount)
    {
        return new HotelFloor(spacesCount);
    }
    public Floor createFloor(Space[] spaces)
    {
        return new HotelFloor(spaces);
    }
    public Building createBuilding(int floorsCount, int[] spacesCounts)
    {
        return new HotelBuilding(floorsCount,spacesCounts);
    }
    public Building createBuilding(Floor[] floors)
    {
        return new HotelBuilding(floors);
    }
}