package buildings.office;

import buildings.*;

import java.util.Iterator;

/**
 * Created by Teomant on 30.09.2017.
 */
public class OfficeBuilding implements Building {

    private OfficeFloorNode first;

    private OfficeFloorNode getNode(int number) {
        OfficeFloorNode buffer = first;
        for (int i = 0; i < number; i++) {
            buffer = buffer.getNext();
        }
        return buffer;
    }

    private void addNode(int number, OfficeFloorNode office) {
        OfficeFloorNode buffer = first;
        OfficeFloorNode buffer1;
        if (number == 0) {
            buffer1 = buffer;
            while (!buffer1.getNext().equals(first)) {
                buffer1 = buffer1.getNext();
            }

            buffer1.setNext(office);
            first = office;
            first.setPrev(buffer1);
            first.setNext(buffer);
            buffer.setPrev(first);


        } else {
            for (int i = 0; i < number - 1; i++) {
                buffer = buffer.getNext();
            }
            buffer1 = buffer.getNext();
            buffer.setNext(office);
            office.setPrev(buffer);
            office.setNext(buffer1);
            buffer1.setPrev(office);
        }
    }

    private void removeNode(int number) {
        OfficeFloorNode buffer = first;
        OfficeFloorNode buffer1;
        if (number == 0) {
            buffer1 = buffer;
            while (!buffer1.getNext().equals(first)) {
                buffer1 = buffer1.getNext();
            }
            first = buffer.getNext();
            buffer1.setNext(first);
            first.setPrev(buffer1);
        } else {
            for (int i = 0; i < number - 1; i++) {
                buffer = buffer.getNext();
            }
            buffer.setNext(buffer.getNext().getNext());
            buffer = buffer.getNext();
            buffer.setPrev(buffer.getPrev().getPrev());
        }
    }

    public OfficeBuilding(int number, int[] spaces) {
        first = new OfficeFloorNode(new OfficeFloor(spaces[0]));
        OfficeFloorNode buffer = first;
        OfficeFloorNode buffer1 = first;
        for (int i = 1; i < number; i++) {
            buffer.setNext(new OfficeFloorNode(new OfficeFloor(spaces[i])));
            buffer = buffer.getNext();
            buffer.setPrev(buffer1);
            buffer1 = buffer;
        }

        buffer = first;
        while (buffer.hasNext()) {
            buffer = buffer.getNext();
        }
        buffer.setNext(first);
        first.setPrev(buffer);
    }

    public OfficeBuilding(Floor[] floors) {
        first = new OfficeFloorNode(floors[0]);
        OfficeFloorNode buffer = first;
        OfficeFloorNode buffer1;
        for (int i = 1; i < floors.length; i++) {
            buffer1 = new OfficeFloorNode(floors[i]);
            buffer.setNext(buffer1);
            buffer1.setPrev(buffer);
            buffer = buffer.getNext();
        }
        buffer = first;
        while (buffer.hasNext()) {
            buffer = buffer.getNext();
        }
        buffer.setNext(first);
        first.setPrev(buffer);
    }

    public int getFloorsNumber() {

        int count = 1;
        OfficeFloorNode buffer = first;
        while (!buffer.getNext().equals(first)) {
            count++;
            buffer = buffer.getNext();
        }

        return count;
    }

    public int getSpacesNumber() {
        int count = 0;
        OfficeFloorNode buffer = first;
        while (!buffer.getNext().equals(first)) {
            count += buffer.getFloor().getSpacesNumber();
            buffer = buffer.getNext();
        }
        count += buffer.getFloor().getSpacesNumber();

        return count;
    }

    public float getFullArea() {
        float area = 0;
        OfficeFloorNode buffer = first;
        while (!buffer.getNext().equals(first)) {
            area += buffer.getFloor().getFullArea();
            buffer = buffer.getNext();
        }
        area += buffer.getFloor().getFullArea();

        return area;
    }

    public int getRoomNumber() {
        int roomNumber = 0;
        OfficeFloorNode buffer = first;
        while (!buffer.getNext().equals(first)) {
            roomNumber += buffer.getFloor().getRoomNumber();
            buffer = buffer.getNext();
        }
        roomNumber += buffer.getFloor().getRoomNumber();

        return roomNumber;
    }

    public Floor[] getFloors() {
        Floor[] offices = new Floor[this.getFloorsNumber()];
        OfficeFloorNode buffer = first;
        int i = 1;
        offices[0] = first.getFloor();
        while (!buffer.getNext().equals(first)) {

            buffer = buffer.getNext();
            offices[i] = buffer.getFloor();
            i++;
        }

        return offices;
    }

    public Floor getFloor(int number) {
        if (number>=this.getFloorsNumber())
        {
            throw new FloorIndexOutOfBoundsException();
        }
        return getNode(number).getFloor();
    }

    public void setFloor(int number, Floor floor) {
        if (number>=this.getFloorsNumber())
        {
            throw new FloorIndexOutOfBoundsException();
        }

        OfficeFloorNode buffer = first;
        for (int i = 0; i < number; i++) {
            buffer = buffer.getNext();
        }
        buffer.setFloor(floor);
    }

    public Space getSpace(int number)
    {
        if (number>=this.getSpacesNumber())
        {
            throw new SpaceIndexOutOfBoundsException();
        }
        Space space=null;
        OfficeFloorNode buffer=first;
        for (int i=0;i<this.getFloorsNumber();i++)
        {
            if (buffer.getFloor().getSpacesNumber() <= number)
            {
                number-=buffer.getFloor().getSpacesNumber();
                buffer=buffer.getNext();
            }
            else
            {
                return buffer.getFloor().getSpace(number);
            }
        }

        return space;
    }

    public void setSpace(int number, Space office)
    {
        if (number>=this.getSpacesNumber())
        {
            throw new SpaceIndexOutOfBoundsException();
        }
        OfficeFloorNode buffer=first;
        for (int i=0;i<this.getFloorsNumber();i++)
        {
            if (buffer.getFloor().getSpacesNumber() < number)
            {
                number-=buffer.getFloor().getSpacesNumber();
                buffer=buffer.getNext();
            }
            else
            {
                buffer.getFloor().setSpace(number,office);
                break;
            }
        }
    }

    public void addSpace(int number, Space office)
    {
        if (number>this.getSpacesNumber())
        {
            throw new SpaceIndexOutOfBoundsException();
        }
        OfficeFloorNode buffer=first;
        for (int i=0;i<this.getFloorsNumber();i++)
        {
            if (buffer.getFloor().getSpacesNumber() < number)
            {
                number-=buffer.getFloor().getSpacesNumber();
                buffer=buffer.getNext();
            }
            else
            {
                buffer.getFloor().addSpace(number,office);
                break;
            }
        }
    }

    public void removeSpace(int number)
    {
        if (number>=this.getSpacesNumber())
        {
            throw new SpaceIndexOutOfBoundsException();
        }
        OfficeFloorNode buffer=first;
        for (int i=0;i<this.getFloorsNumber();i++)
        {
            if (buffer.getFloor().getSpacesNumber() <= number)
            {
                number-=buffer.getFloor().getSpacesNumber();
                buffer=buffer.getNext();
            }
            else
            {
                buffer.getFloor().removeSpace(number);
                break;
            }
        }
    }

    public Space getBestSpace()
    {
        float maxSpacce=0;
        OfficeFloorNode buffer=first;
        Space bestSpace=first.getFloor().getBestSpace();

        while (!buffer.getNext().equals(first)) {
            buffer=buffer.getNext();
            if (buffer.getFloor().getBestSpace().getArea()>bestSpace.getArea())
            {
                bestSpace=buffer.getFloor().getBestSpace();
            }
        }
        return bestSpace;
    }

    public Space[] getSpacesSorted()
    {
        Space[] offices = new Space[this.getSpacesNumber()];
        for (int i=0;i<this.getSpacesNumber();i++)
        {
            offices[i]=this.getSpace(i);
        }

        for(int i = 0; i < offices.length - 1; i++)
        {
            for(int j = i + 1; j < offices.length; j++)
            {
                if (offices[i].getArea() < offices[j].getArea())
                {
                    Space temp = offices[i];
                    offices[i] = offices[j];
                    offices[j] = temp;
                }
            }
        }

        return offices;
    }

    @Override
    public boolean equals (Object object)
    {
        if (!(object instanceof OfficeBuilding))
            return false;
        OfficeBuilding building = (OfficeBuilding) object;
        if (!(building.getFloorsNumber()==this.getFloorsNumber()))
            return false;
        for (int i=0;i<building.getFloorsNumber();i++)
        {
            if (!(building.getFloor(i).equals(this.getFloor(i))))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash =this.getFloorsNumber();

        for (int i=0;i<this.getFloorsNumber();i++)
        {
            hash^=this.getFloor(i).hashCode();
        }

        return hash;
    }



    @Override
    public String toString() {
        String full = "OfficeBuilding ("+this.getFloorsNumber()+", ";
        OfficeFloorNode buffer = first;

        while (!buffer.getNext().equals(first)) {
            full += buffer.getFloor().toString()+", ";
            buffer = buffer.getNext();
        }
        full += buffer.getFloor().toString()+")";
        return full;
    }

    @Override
    public Object clone(){      //check clone
        OfficeBuilding result=null;
        try {
            result = (OfficeBuilding) super.clone();
            result.first=(OfficeFloorNode)first.clone();
        } catch (CloneNotSupportedException ex) { }
        return result;
    }

    public Iterator iterator()
    {
        return new OfficeBuildingIterator(this);
    }
}


