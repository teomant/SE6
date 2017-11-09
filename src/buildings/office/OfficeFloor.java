package buildings.office;

import buildings.*;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by Teomant on 29.09.2017.
 */
public class OfficeFloor implements Floor {

    private OfficeNode first;

    /*public OfficeFloor()
    {
        this(1);
    }*/

    public OfficeFloor(int number) {
        first = new OfficeNode(new Office());
        OfficeNode buffer = first;
        for (int i = 1; i < number; i++)
        {
            buffer.setNext(new OfficeNode(new Office()));
            buffer = buffer.getNext();
        }

        buffer = first;
        while (buffer.hasNext()) {
            buffer = buffer.getNext();
        }
        buffer.setNext(first);
    }

    public OfficeFloor(Space[] array) {
        first = new OfficeNode(array[0]);
        OfficeNode buffer = first;
        for (int i = 1; i < array.length; i++) {
            buffer.setNext(new OfficeNode(array[i]));
            buffer = buffer.getNext();

        }
        buffer = first;
        while (buffer.hasNext()) {
            buffer = buffer.getNext();
        }
        buffer.setNext(first);
    }

    private OfficeNode getNode(int number) {
        OfficeNode buffer = first;
        for (int i = 0; i < number; i++) {
            buffer = buffer.getNext();
        }
        return buffer;
    }

    private void addNode(int number, OfficeNode office) {
        OfficeNode buffer = first;
        OfficeNode buffer1;
        if (number == 0) {
            buffer1=buffer;
            while (!buffer1.getNext().equals(first))
            {
                buffer1=buffer1.getNext();
            }

            buffer1.setNext(office);
            first=office;
            first.setNext(buffer);

        } else {
            for (int i = 0; i < number - 1; i++) {
                buffer = buffer.getNext();
            }
            buffer1 = buffer.getNext();
            buffer.setNext(office);
            office.setNext(buffer1);
        }
    }

    private void removeNode(int number) {
        OfficeNode buffer = first;
        OfficeNode buffer1;
        if (number == 0) {
            buffer1=buffer;
            while (!buffer1.getNext().equals(first))
            {
                buffer1=buffer1.getNext();
            }
            first = buffer.getNext();
            buffer1.setNext(first);
        } else {
            for (int i = 0; i < number - 1; i++) {
                buffer = buffer.getNext();
            }
            buffer.setNext(buffer.getNext().getNext());
        }
    }

    public int getSpacesNumber() {
        int count = 1;
        OfficeNode buffer = first;
        while (!buffer.getNext().equals(first)) {
            count++;
            buffer = buffer.getNext();
        }

        return count;
    }

    public float getFullArea()
    {
        float fullArea=0;
        OfficeNode buffer=first;
        fullArea+=buffer.getOffice().getArea();
        while (!buffer.getNext().equals(first)) {
            buffer = buffer.getNext();
            fullArea+=buffer.getOffice().getArea();
        }

        return fullArea;
    }

    public int getRoomNumber()
    {
        int room_number=0;
        OfficeNode buffer=first;
        room_number+=buffer.getOffice().getRoomNumber();
        while (!buffer.getNext().equals(first)) {
            buffer = buffer.getNext();
            room_number+=buffer.getOffice().getRoomNumber();
        }

        return room_number;
    }

    public Space[] getSpaces()
    {
        Space[] offices = new Space[this.getSpacesNumber()];
        OfficeNode buffer=first;
        int i=1;
        offices[0]=first.getOffice();
        while (!buffer.getNext().equals(first)) {

            buffer=buffer.getNext();
            offices[i]=buffer.getOffice();
            i++;
        }

        return offices;
    }

    public Space getSpace(int number)
    {
        if (number>this.getSpacesNumber())
        {
            throw new SpaceIndexOutOfBoundsException();
        }
        return getNode(number).getOffice();
    }

    public void setSpace(int number, Space office)
    {
        if (number>=this.getSpacesNumber())
        {
            throw new SpaceIndexOutOfBoundsException();
        }
        OfficeNode buffer=first;
        for (int i=0;i<number;i++)
        {
            buffer=buffer.getNext();
        }
        buffer.setOffice(office);
    }

    public void addSpace(int number, Space office)
    {
        if (number>this.getSpacesNumber())
        {
            throw new SpaceIndexOutOfBoundsException();
        }
        addNode(number,new OfficeNode(office));
    }

    public void removeSpace(int number)
    {
        if (number>=this.getSpacesNumber())
        {
            throw new SpaceIndexOutOfBoundsException();
        }
        removeNode(number);
    }

    public Space getBestSpace()
    {
        float maxSpacce=0;
        OfficeNode buffer=first;
        OfficeNode bestSpace=first;

        while (!buffer.getNext().equals(first)) {
            buffer=buffer.getNext();
            if (buffer.getOffice().getArea()>bestSpace.getOffice().getArea())
            {
                bestSpace=buffer;
            }
        }
        return bestSpace.getOffice();
    }

    @Override
    public boolean equals (Object object)
    {
        if (!(object instanceof OfficeFloor))
            return false;
        OfficeFloor floor = (OfficeFloor) object;
        if (!(floor.getSpacesNumber()==this.getSpacesNumber()))
            return false;
        for (int i=0;i<floor.getSpacesNumber();i++)
        {
            if (!(floor.getSpace(i).equals(this.getSpace(i))))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash =this.getSpacesNumber();

        for (int i=0;i<this.getSpacesNumber();i++)
        {
            hash^=this.getSpace(i).hashCode();
        }

        return hash;
    }

    @Override
    public String toString() {
        String full="OfficeFloor ("+this.getSpacesNumber()+", ";
        OfficeNode buffer=first;

        while (!buffer.getNext().equals(first))
        {
            full+=buffer.getOffice().toString()+", ";
            buffer=buffer.getNext();
        }
        full+=buffer.getOffice().toString()+")";
        return full;
    }

    @Override
    public Object clone(){
        OfficeFloor result=null;
        try {
            result = (OfficeFloor) super.clone();
            result.first=(OfficeNode) first.clone();
            OfficeNode buffer = first.getNext();
            OfficeNode buffer1=result.first;
            while (!(buffer==first))
            {
                buffer1.setNext((OfficeNode)buffer.clone());
                buffer1=buffer1.getNext();
                buffer=buffer.getNext();
            }
            buffer1.setNext(result.first);

        } catch (CloneNotSupportedException ex) { }
        return result;
    }

    public Iterator iterator()
    {
        return new OfficeFloorIterator(this);
    }

    @Override
    public int compareTo(Floor s)
    {
        if (this.getSpacesNumber()>((OfficeFloor)s).getSpacesNumber())
            return 1;
        if (this.getSpacesNumber()==((OfficeFloor)s).getSpacesNumber())
            return 0;
        return -1;
    }

}
