/**
 * Evenements particuliers concernant la mise à jour des balles et des boids.
 * @author Lucas Mahieu
 * @author Hugues de Valon
 */
public class BBEvent extends Event {
    /**
     * Tableau représentant les groupes de balles.
     */
    private Balls[] balls;
    /**
     * Délai entre deux execution pour chaque groupe de balles.
     */
    private int[] delai;
    /**
     * Permet de savoir quel groupe execute ce code.
     */
    private int groupe;

    /**
     * Constructeur : Initialise les différents éléments de l'événement.
     * @param balls Tableau des groupes de balles
     * @param date Date d'execution de l'événement
     * @param manager Manager des événements
     * @param delai Délai entre deux execution de chaque groupe
     * @param groupe Permet de distinguer quel groupe effectue executer
     */
    public BBEvent(Balls[] balls, long date, EventManager manager, int[] delai, int groupe) {
        super(date, manager);
        this.balls = balls;
        this.delai = delai;
        this.groupe = groupe;
    }

    /**
     * Cette méthode est executée à chaque événement, groupe permet de
     * distinguer si on execute la méthode pour la première fois (initialisation des événements
     * pour tous les groupes : groupe = -1) ou si c'est uniquement 
     * pour rajouter un événement à un groupe particulier.
     */
    public void execute() {
        // L'utilisation de groupe permet d'éviter des ajours récursifs d'événemnts.
        if (this.groupe == -1)
        {
            // Cette partie est executée lors du premier appel, lorsque groupe vaut -1.
            for(int i = 0; i < this.balls.length; i++) {
                this.balls[i].actualiser();
                // L'événement crée, crée a son tour deux événement.
                super.getManager().addEvent(new BBEvent(this.balls, super.getDate() + this.delai[i], this.getManager(), this.delai, i));
            }
        }
        else
        {
            // Cas normal, l'événement ajoute des événements que pour son groupe.
            this.balls[this.groupe].actualiser();
            super.getManager().addEvent(new BBEvent(this.balls, super.getDate() + this.delai[this.groupe], this.getManager(), this.delai, this.groupe));
        }
    }
}
