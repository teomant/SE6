package buildings.dwelling;

import buildings.*;

public class Flat implements Space {
	private float area;
	private int room_number;
	private static final int DEFAULT_ROOM_NUMBER = 2;
	private static final float DEFAULT_AREA = 50;

	public Flat() {
		this.area = DEFAULT_AREA;
		this.room_number = DEFAULT_ROOM_NUMBER;
	}

	public Flat(float area) {
		this.area = area;
		this.room_number = DEFAULT_ROOM_NUMBER;
	}

	public Flat(float area, int room_number) {
		this.area = Math.abs(area);
		this.room_number = room_number;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public void setRoomNumber(int room_number) {
		this.room_number = room_number;
	}

	public float getArea() {
		return area;
	}

	public int getRoomNumber() {
		return room_number;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Flat))
			return false;
		Flat e = (Flat) object;
		if (e.getArea() == this.getArea() && e.getRoomNumber() == this.getRoomNumber())
			return true;
		return false;
	}

	@Override
	public int hashCode()
	{
		return (Float.floatToIntBits(this.getArea())^this.getRoomNumber());
	}

	@Override
	public String toString() {
		return ("Flat ("+room_number+", "+area+")");
	}

	@Override
	public Object clone(){
		Flat result=null;
		try {
			result = (Flat) super.clone();
		} catch (CloneNotSupportedException ex) { }
		return result;
	}

	@Override
	public int compareTo(Space s)
	{
		if (this.getArea()>s.getArea())
			return 1;
		if (this.getArea()==s.getArea())
			return 1;
		return -1;
	}

}