

public class Ship {
    private int hp;
    public int length;
    public boolean orientationVertical = true;

    public Ship(int len, boolean orientation) {
        this.length = len;
        this.orientationVertical = orientation;
        hp = len;

        VBox ship = new VBox();

    }

    public void shipHit (){
        health--;
    }

    public boolean isAlive(){
        return hp > 0;
    }
}
