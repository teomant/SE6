package buildings.dwelling;

import buildings.*;

import java.util.Iterator;
import java.util.LinkedList;

public class DwellingFloor implements Floor
{
	private LinkedList<Space> flats = new LinkedList<Space>();

	public DwellingFloor (int number)
	{
		for (int i=0;i<number;i++)
		{
			flats.add(new Flat());
		}
	}

	public DwellingFloor (Space[] flats)
	{
		for (int i=0;i<flats.length;i++)
		{
			this.flats.add(flats[i]);
		}
	}

	public DwellingFloor(){}
	
	public int getSpacesNumber()
	{
		return flats.size();
	}
	
	public float getFullArea()
	{

		float full_area=0;
		for (int i=0;i<flats.size();i++)
		{
			full_area+=flats.get(i).getArea();
		}
		return full_area;
	}
	
	public int getRoomNumber()
	{
		int room_number=0;
		for (int i=0;i<flats.size();i++)
		{

			room_number+=flats.get(i).getRoomNumber();
		}
		return room_number;
	}
	
	public Space[] getSpaces()
	{
		Space[] flats = new Space[this.flats.size()];
		for (int i=0;i<this.flats.size();i++)
		{
			flats[i]=this.flats.get(i);
		}
		return flats;
	}
	
	public Space getSpace (int number)
	{
		if ((number>=0)&&(number<flats.size())) {
			return flats.get(number);
		}

		return null;
	}
	
	public void setSpace (int number,Space flat)
	{
		if ((number>=0)&&(number<flats.size()))
		{
			flats.set(number, flat);
		}
	}
	
	public void addSpace (int number, Space flat)
	{
		if ((number>=0)&&(number<=flats.size()))
		{
			flats.add(number, flat);
		}
	}
	
	public void removeSpace (int number)
	{
		if ((number>=0)&&(number<flats.size())) {
			flats.remove(number);
		}
	}
	
	public Space getBestSpace()
	{

		int number=0;
		float max_area=flats.get(0).getArea();
		for (int i=1;i<flats.size();i++)
		{

			if (max_area<flats.get(i).getArea())
			{
				max_area=flats.get(i).getArea();
				number=i;
			}
		}
		return flats.get(number);
	}

	@Override
	public boolean equals (Object object)
	{
		if (!(object instanceof DwellingFloor))
			return false;
		DwellingFloor floor = (DwellingFloor) object;
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
		String full="DwellingFloor ("+this.getSpacesNumber()+", ";
		for (int i=0;i<flats.size();i++)
		{
			full+=flats.get(i).toString();
			if (i<flats.size()-1)
				full+=", ";
		}
		return full+")";
	}

	public Object clone(){
		DwellingFloor result=null;
		try {
			result = (DwellingFloor) super.clone();
			result.flats = (LinkedList<Space>)flats.clone();
		} catch (CloneNotSupportedException ex) { }
		return result;
	}

	public Iterator<Space> iterator()
	{
		return /*new DwellingFloorIterator(this)*/ flats.iterator();
	}

	@Override
	public int compareTo(Floor s)
	{
		if (this.getSpacesNumber()>s.getSpacesNumber())
			return 1;
		if (this.getSpacesNumber()==s.getSpacesNumber())
			return 0;
		return -1;
	}
}

