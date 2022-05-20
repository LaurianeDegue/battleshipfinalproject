

public class Ship {
    private int hp;
    public int length;
    public boolean orientationVertical = true;

    public Ship(int len, boolean orientation) {
        this.length = len;
        this.orientationVertical = orientation;
        hp = len;

        VBox ship = new VBox();
        for (int b=0;b > length; b++){
            Rectangle shipPart = new Rectangle();

            ship.getChildren().add(shipPart);

        }

    }

    public void shipHit (){
        hp--;
    }

    public boolean isAlive(){
        return hp > 0;
    }
}
