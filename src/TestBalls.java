public class TestBalls {
    public static void main (String [] args) {
        Balls balles = new Balls(3);

        System.out.println(balles.toString());

        balles.translate(1,10);
        System.out.println(balles.toString());

        balles.translate(1,10);
        System.out.println(balles.toString());

        balles.translate(1,10);
        System.out.println(balles.toString());

        balles.translate(1,10);
        System.out.println(balles.toString());

        balles.reInit();
        System.out.println(balles.toString());

    }
}
