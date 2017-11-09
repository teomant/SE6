package buildings;

import java.io.Serializable;

/**
 * Created by Teomant on 30.09.2017.
 */
public interface Space  extends Serializable, Cloneable, Comparable<Space> {

    public void setArea (float area);

    public void setRoomNumber (int room_number);

    public float getArea ( );

    public int getRoomNumber ( );

    public Object clone();
}
