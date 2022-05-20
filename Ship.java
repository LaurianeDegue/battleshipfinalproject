/*
This is my ship class, that consists of the making of the ship itself, and all of it's components, like the health, the length of the ship and if its horizontal or vertical.
 */

public class Ship {
    private int hp;
    public int length;
    public boolean orientationVertical = true;

    // This is my constructor, that will set the length and orientation of the ship, as we call the class. It will also set the the health as the length, as they are the same thing essentially.
    // I am also adding a rectangle for each part of the ship, therefore creating a ship with the length that was set beforehadn and adding said ship to a vbox
    public Ship(int len, boolean orientation) {
        this.length = len;
        this.orientationVertical = orientation;
        hp = len;

        VBox ship = new VBox();
        for (int b=0;b > length; b++){
            Rectangle shipPart = new Rectangle();

            ship.getChildren().add(shipPart);

        }

        getChildren.add(ship);

    }

    //In this method, we are decreasing the health of the ship, if this method is called. We will call this method, when we or the enemy selects a ship cell succesfully
    public void shipHit (){
        hp--;
    }

    //In this method, we are returning whether or not the ship is alive, so it will return whether that the hp is greather than 0
    public boolean isAlive(){
        return hp > 0;
    }
}
