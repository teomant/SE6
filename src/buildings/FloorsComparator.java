package buildings;

import java.util.Comparator;

/**
 * Created by Teomant on 18.10.2017.
 */
public class FloorsComparator implements Comparator<Floor> {

    public int compare(Floor s0, Floor s1)
    {
        if (s0.getFullArea()<s1.getFullArea()) return 1;
        if (s0.getFullArea()>s1.getFullArea()) return -1;
        return 0;
    }
}
