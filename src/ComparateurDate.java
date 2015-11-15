import java.util.*;

/**
 * Cette classe fournit la relation de comparaison utilise pour notre liste
 * d'événements.
 *
 * @author Lucas Mahieu
 * @author Hugues de Valon
 */
class ComparateurDate implements Comparator<Event>
{
    /**
     * Implémente la relation de comparaison entre deux événements.
     * @param e1 Premier événement
     * @param e2 Deuxième événement
     * @return Renvoit -1 si le premier événement s'execute avant, 1 après, 0 en même temps
     */
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
