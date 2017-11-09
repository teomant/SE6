package buildings;

import java.util.Comparator;

/**
 * Created by Teomant on 18.10.2017.
 */
public class SpacesComparator implements Comparator<Space> {

    public int compare(Space s0, Space s1)
    {
        if (s0.getRoomNumber()<s1.getRoomNumber()) return 1;
        if (s0.getRoomNumber()>s1.getRoomNumber()) return -1;
        return 0;
    }
}
