import java.util.*;

class ComparateurDate implements Comparator<Event>
{
    @Override
    public int compare(Event e1, Event e2)
    {
        if (e1.getDate() < e2.getDate())
            return -1;
        else if (e1.getDate() == e2.getDate())
            return 0;
        else
            return 1;
    }
}
