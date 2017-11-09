package buildings;

/**
 * Created by Teomant on 09.10.2017.
 */
public class PlacementExchanger {

    public static boolean exchangeSpacePossibility (Space s1, Space s2)
    {
        if (s1.getArea()==s2.getArea()&&s1.getRoomNumber()==s2.getRoomNumber())
        {
            return true;
        }
        return false;
    }

    public static boolean exchangeFloorPossibility (Floor f1, Floor f2)
    {
        if (f1.getFullArea()==f2.getFullArea()&&f1.getSpacesNumber()==f2.getSpacesNumber())
        {
            return true;
        }
        return false;
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InexchangeableSpacesException
    {
        Space buffer;
        if (index1>floor1.getSpacesNumber()||index2>floor2.getSpacesNumber())
        {
            throw new SpaceIndexOutOfBoundsException();
        }
        if (exchangeSpacePossibility(floor1.getSpace(index1),floor2.getSpace(index2)))
        {
            buffer=floor1.getSpace(index1);
            floor1.setSpace(index1,floor2.getSpace(index2));
            floor2.setSpace(index2,buffer);
        }
        else {
            /*try{
                throw new InexchangeableSpacesException();
            }
            catch (InexchangeableSpacesException e)
            {
                System.out.println("oooops!");
            }*/
            throw new InexchangeableSpacesException();
        }
    }

    public static void exchangeBuildingFloors (Building building1, int index1, Building building2, int index2) throws InexchangeableFloorsException
    {
        Floor buffer;
        if (index1>building1.getFloorsNumber()||index2>building2.getFloorsNumber())
        {
            throw new FloorIndexOutOfBoundsException();
        }
        if (exchangeFloorPossibility(building1.getFloor(index1),building2.getFloor(index2)))
        {
            buffer=building1.getFloor(index1);
            building1.setFloor(index1,building2.getFloor(index2));
            building2.setFloor(index2,buffer);
        }
        else {
            /*try{
                throw new InexchangeableFloorsException();
            }
            catch (InexchangeableFloorsException e)
            {
                System.out.println("oooops!");
            }*/
            throw new InexchangeableFloorsException();
        }


    }

}
