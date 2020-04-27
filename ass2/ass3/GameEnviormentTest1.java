public class GameEnviormentTest1 {
    public static void main (String[] args) {

        Ball b = new Ball( 3,3,5, java.awt.Color.BLACK);
        b.setVelocity(10,10);
        Block[] blockArray = new Block[10];
        Point upperLeft = new Point(3,5);
        GameEnvironment g = new GameEnvironment();
        CollisionInfo c;
        Point oldCenter = b.getCenter();
        //b.setXLowerDomain(10000);
        //b.setXUpperDomain(10000);
        //b.setYLowerDomain(10000);
        //b.setYUpperDomain(10000);
        Line trajectory = b.getTrajectoryLine();

            for (int i = 1; i < 10; i++) {
            blockArray[i] = new Block(new Rectangle(upperLeft,8,8)) ;
            upperLeft.setX(upperLeft.getX() + i);
            upperLeft.setY(upperLeft.getY() + i);
            g.addCollidable( blockArray[i] );
        }
            blockArray[0] = new Block(new Rectangle(new Point (12,14),8,8));
            g.addCollidable(blockArray[0] );

        c = g.getClosestCollision(trajectory);
            System.out.println(c.getCollisionPoint());
    }
}
