package buildings.dwelling.hotel;

import buildings.Floor;
import buildings.Space;
import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;

public class HotelBuilding extends Dwelling {

    private int stars=0;


    public HotelBuilding (int floors, int[] flats)
    {
        super(floors, flats);
        for (int i=0;i<this.getFloorsNumber();i++)
        {
            if (this.getFloor(i) instanceof HotelFloor)
                if (((HotelFloor) this.getFloor(i)).getStars()>stars)
                {
                    stars=((HotelFloor) this.getFloor(i)).getStars();
                }
        }
    }

    public HotelBuilding (Floor[] floors)
    {
        super(floors);
        for (int i=0;i<this.getFloorsNumber();i++)
        {
            if (this.getFloor(i) instanceof HotelFloor)
                if (((HotelFloor) this.getFloor(i)).getStars()>stars)
                {
                    stars=((HotelFloor) this.getFloor(i)).getStars();
                }
        }
    }

    public int getStars ()
    {
        for (int i=0;i<this.getFloorsNumber();i++)
        {
            if (this.getFloor(i) instanceof HotelFloor)
                if (((HotelFloor) this.getFloor(i)).getStars()>stars)
                {
                    stars=((HotelFloor) this.getFloor(i)).getStars();
                }
        }
        return stars;
    }

    @Override
    public Space getBestSpace()
    {
        int number=0;
        float coeff;

        switch (((HotelFloor)this.getFloor(0)).getStars()){
            case 1:
                coeff = (float)0.25;
                break;
            case 2:
                coeff = (float)0.5;
                break;
            case 3:
                coeff = (float)1;
                break;
            case 4:
                coeff = (float)1.25;
                break;
            case 5:
                coeff = (float)1.5;
                break;
            default:
                coeff = (float)0;
                break;
        }

        float max_area=this.getFloor(0).getBestSpace().getArea()*coeff;

        for (int i=1;i<this.getFloorsNumber();i++)
        {

            switch (((HotelFloor)this.getFloor(i)).getStars()){
                case 1:
                    coeff = (float)0.25;
                    break;
                case 2:
                    coeff = (float)0.5;
                    break;
                case 3:
                    coeff = (float)1;
                    break;
                case 4:
                    coeff = (float)1.25;
                    break;
                case 5:
                    coeff = (float)1.5;
                    break;
                default:
                    coeff = (float)0;
                    break;
            }

            if (max_area<this.getFloor(i).getBestSpace().getArea()*coeff)
            {
                max_area=this.getFloor(i).getBestSpace().getArea()*coeff;
                number=i;
            }
        }
        return this.getFloor(number).getBestSpace();
    }

    @Override
    public String toString() {
        String full = "HotelBuilding ("+this.getFloorsNumber()+", "+this.getStars()+", ";

        for (int i=0;i<this.getFloorsNumber();i++)
        {
            full+=this.getFloor(i).toString();
            if (i<this.getFloorsNumber()-1)
                full+=", ";
        }
        return full+")";
    }

    @Override
    public boolean equals (Object object)
    {
        if (!(object instanceof HotelBuilding))
            return false;
        HotelBuilding building = (HotelBuilding) object;
        if (!(building.getFloorsNumber()==this.getFloorsNumber()))
            return false;
        for (int i=0;i<building.getFloorsNumber();i++)
        {
            if (!(building.getFloor(i).equals(this.getFloor(i))))
                return false;
        }
        if (this.getStars()!=building.getStars())
        {
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

}
