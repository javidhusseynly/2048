//Javid Husseynly, hussej3.

public class Tile {
    int value;

    public Tile() {
        this.value = 0;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void combine(Tile tile) {
        this.setValue(value * 2);
        tile.setValue(0);
    }

    public boolean same(Tile tile) {
        return this.getValue() == tile.getValue();
    }

}
