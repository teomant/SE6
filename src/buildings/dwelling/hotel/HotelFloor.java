package buildings.dwelling.hotel;

import buildings.*;
import buildings.dwelling.*;

import java.util.Iterator;
import java.util.LinkedList;

public class HotelFloor extends DwellingFloor {

    private int stars;

    private static final int DEFAULT_STARS_NUMBER = 1;

    public HotelFloor (int number)
    {
        super(number);
        stars=DEFAULT_STARS_NUMBER;
    }

    public HotelFloor (Space[] flats)
    {
        super(flats);
        stars=DEFAULT_STARS_NUMBER;
    }

    public void setStars (int stars)
    {
        this.stars=stars;
    }

    public int getStars ()
    {
        return stars;
    }

    @Override
    public String toString() {
        String full="HotelFloor ("+this.getSpacesNumber()+", "+this.getStars()+", ";
        for (int i=0;i<this.getSpacesNumber();i++)
        {
            full+=this.getSpace(i).toString();
            if (i<this.getSpacesNumber()-1)
                full+=", ";
        }
        return full+")";
    }

    @Override
    public boolean equals (Object object)
    {
        if (!(object instanceof HotelFloor))
            return false;
        HotelFloor floor = (HotelFloor) object;
        if (!(floor.getSpacesNumber()==this.getSpacesNumber()))
            return false;
        for (int i=0;i<floor.getSpacesNumber();i++)
        {
            if (!(floor.getSpace(i).equals(this.getSpace(i))))
                return false;
        }
        if (this.getStars()!=floor.getStars())
        {
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

        hash^=this.getStars();
        return hash;
    }



}
