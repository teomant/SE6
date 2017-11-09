package buildings.dwelling;

import buildings.*;


import java.util.Iterator;
import java.util.LinkedList;

public class Dwelling implements Building {

    private LinkedList<Floor> floors = new LinkedList<Floor>();

    public Dwelling (int floors, int[] flats)
    {
        for (int i=0;i<floors;i++)
        {
            this.floors.add(new DwellingFloor(flats[i]));
        }
    }



    public Dwelling (Floor[] floors)
    {
        for (int i=0;i<floors.length;i++)
        {
            this.floors.add(floors[i]);
        }
    }

    public Dwelling(){}

    public int getFloorsNumber()
    {
        return floors.size();
    }

    public int getSpacesNumber()
    {
        int number=0;

        for (int i=0;i<floors.size();i++)
        {
            number+=floors.get(i).getSpacesNumber();
        }

        return number;
    }

    public float getFullArea()
    {

        float full_area=0;
        for (int i=0;i<floors.size();i++)
        {
            full_area+=floors.get(i).getFullArea();
        }
        return full_area;
    }

    public int getRoomNumber()
    {
        int room_number=0;
        for (int i=0;i<floors.size();i++)
        {

            room_number+=floors.get(i).getRoomNumber();
        }
        return room_number;
    }

    public Floor[] getFloors ()
    {
        Floor[] floors = new Floor[this.floors.size()];
        for (int i=0;i<this.floors.size();i++)
        {
            floors[i]=this.floors.get(i);
        }
        return floors;
    }

    public Floor getFloor(int number)
    {
        if ((number>=0)&&(number<floors.size())) {
            return floors.get(number);
        }
        return  null;

    }

    public void setFloor(int number, Floor floor)
    {
        if ((number>=0)&&(number<floors.size()))
        {
            floors.set(number, floor);
        }
    }

    public Space getSpace(int number)
    {
        if ((number>=0)&&(number<this.getSpacesNumber()))
        {
            for (int i=0; i<floors.size();i++)
            {
                if (number>=floors.get(i).getSpacesNumber())
                {
                    number-=floors.get(i).getSpacesNumber();
                }
                else
                {
                    return floors.get(i).getSpace(number);
                }
            }
        }
        return null;
    }

    public void setSpace(int number, Space flat)
    {
        if ((number>=0)&&(number<this.getSpacesNumber())) {
            for (int i = 0; i < floors.size(); i++) {
                if (number >= floors.get(i).getSpacesNumber()) {
                    number -= floors.get(i).getSpacesNumber();
                } else {
                    floors.get(i).setSpace(number, flat);
                    break;
                }
            }
        }
    }

    public void addSpace(int number, Space flat)
    {
        for (int i=0; i<floors.size();i++)
        {
            if (number>floors.get(i).getSpacesNumber())
            {
                number-=floors.get(i).getSpacesNumber();
            }
            else
            {
                floors.get(i).addSpace(number,flat);
                break;
            }

        }
    }

    public void removeSpace(int number)
    {
        if ((number>=0)&&(number<this.getSpacesNumber())) {
            for (int i = 0; i < floors.size(); i++) {
                if (number >= floors.get(i).getSpacesNumber()) {
                    number -= floors.get(i).getSpacesNumber();
                } else {
                    floors.get(i).removeSpace(number);
                    break;
                }
                //System.out.println(number);
            }
        }
    }

    public Space getBestSpace()
    {

        Space BestFlat = this.getSpace(0);

        for (int i=1;i<this.getSpacesNumber();i++)
        {
            if (this.getSpace(i).getArea()>BestFlat.getArea())
            {
                BestFlat=this.getSpace(i);
            }
        }

        return BestFlat;
    }

    public Space[] getSpacesSorted()
    {

        Space[] flats = new Space[this.getSpacesNumber()];
        for (int i=0;i<this.getSpacesNumber();i++)
        {
            flats[i]=this.getSpace(i);
        }

        for(int i = 0; i < flats.length - 1; i++)
        {
            for(int j = i + 1; j < flats.length; j++)
            {
                if (flats[i].getArea() < flats[j].getArea())
                {
                    Space temp = flats[i];
                    flats[i] = flats[j];
                    flats[j] = temp;
                }
            }
        }

        return flats;
    }

    @Override
    public boolean equals (Object object)
    {
        if (!(object instanceof Dwelling))
            return false;
        Dwelling building = (Dwelling) object;
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
        String full = "Dwelling ("+this.getFloorsNumber()+", ";

        for (int i=0;i<floors.size();i++)
        {
            full+=floors.get(i).toString();
            if (i<floors.size()-1)
                full+=", ";
        }
        return full+")";
    }

    public Object clone(){
        Dwelling result=null;
        try {
            result = (Dwelling) super.clone();
            result.floors = (LinkedList<Floor>)floors.clone();
        } catch (CloneNotSupportedException ex) { }
        return result;
    }

    @Override
    public Iterator iterator() {
        return /*new DwellingIterator(this)*/ floors.iterator();
    }
}
