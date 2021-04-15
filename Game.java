//Javid Husseynly, hussej3.

import java.util.ArrayList;
import java.util.Random;

public class Game {
    public Tile[][] tiles = new Tile[4][4];

    public Game() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tiles[i][j] = new Tile();
            }
        }
        this.initGame();
    }

    public boolean lost() {
        Game temp = new Game();
        for (int row=0; row<4; row++) {
            for (int col=0; col<4; col++) {
                temp.tiles[row][col].setValue(tiles[row][col].getValue());
            }
        }
        temp.swipe_D();
        temp.swipe_R();
        temp.swipe_L();
        temp.swipe_U();
        return (temp.tiles == this.tiles);
    }

    public boolean won() {
        boolean won = false;
        for (Tile[] row: tiles) {
            for (Tile tile: row) {
                if (tile.getValue() == 2048) {
                    won = true;
                }
            }
        }
        return won;
    }
    public void printBoard() {
        String boardOutput = "";
        for (int row = 0; row<4; row++) {
            for (int col = 0; col<4; col++) {
                boardOutput += tiles[row][col].getValue() + " ";
            }
            boardOutput += "\n";
        }
        System.out.println(boardOutput);
    }

    public void addRandom() {
        boolean done = true;
        while (done) {
            Random rand = new Random();
            int val = rand.nextInt(10);
            int row = rand.nextInt(4);
            int col = rand.nextInt(4);
            if (tiles[row][col].getValue() == 0) {
                done = false;
                if (val == 4) {
                    tiles[row][col].setValue(4);
                } else {
                    tiles[row][col].setValue(2);
                }
            }
        }
    }
    public void initGame() {
        Random rand = new Random();
        int val1 = rand.nextInt(10);
        int val2 = rand.nextInt(10);
        int col1 = rand.nextInt(4);
        int row1 = rand.nextInt(4);
        int col2 = rand.nextInt(4);
        int row2 = rand.nextInt(4);
        while (col1 == col2 && row1 == row2) {
            col2 = rand.nextInt(4);
            row2 = rand.nextInt(4);
        }

        if (val1 == 4) {
            tiles[row1][col1].setValue(4);
        } else {
            tiles[row1][col1].setValue(2);
        }

        if (val2 == 4) {
            tiles[row2][col2].setValue(4);
        } else {
            tiles[row2][col2].setValue(2);
        }

    }

    public static ArrayList<Integer> squish_L_U (ArrayList<Integer> ints) {
        ints.removeIf(n -> (n == 0));
        for (int i = ints.size(); i<4; i++) {
            ints.add(0);
        }
        return ints;
    }

    public static ArrayList<Integer> squish_R_D (ArrayList<Integer> ints) {
        ints.removeIf(n -> (n == 0));
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (int i = 0; i<4-ints.size(); i++) {
            temp.add(0);
        }
        temp.addAll(ints);
        return temp;
    }

    public void swipe_L() {
        for (Tile[] row: tiles ) {
            ArrayList<Integer> ints = new ArrayList<Integer>();
            for (Tile tile: row) {
                ints.add(tile.getValue());
            }

            ints = squish_L_U(ints);
            for (int i = 0; i < 4; i++) {
                row[i].setValue(ints.get(i));
            }

            for (int i = 0; i < 3; i++) {
                if (row[i].same(row[i+1])) {
                    row[i].combine(row[i + 1]);

                    ArrayList<Integer> ints_new = new ArrayList<Integer>();

                    for (Tile tile : row) {
                        ints_new.add(tile.getValue());
                    }
                    ints_new = squish_L_U(ints_new);
                    for (int x = 0; x < 4; x++) {
                        row[x].setValue(ints_new.get(x));
                    }
                }
            }

        }
    }


    public void swipe_R() {
        for (Tile[] row: tiles ) {
            ArrayList<Integer> ints = new ArrayList<Integer>();
            for (Tile tile: row) {
                ints.add(tile.getValue());
            }

            ints = squish_R_D(ints);
            for (int i = 0; i < 4; i++) {
                row[i].setValue(ints.get(i));
            }

            for (int i = 3; i > 0; i--) {
                if (row[i].same(row[i-1])) {
                    row[i].combine(row[i-1]);

                    ArrayList<Integer> ints_new = new ArrayList<Integer>();

                    for (Tile tile : row) {
                        ints_new.add(tile.getValue());
                    }
                    ints_new = squish_R_D(ints_new);
                    for (int x = 0; x < 4; x++) {
                        row[x].setValue(ints_new.get(x));
                    }
                }
            }

        }
    }

    public void swipe_U() {
        for (int col = 0; col < 4; col++ ) {
            ArrayList<Integer> ints = new ArrayList<Integer>();
            for (int i=0; i<4; i++) {
                ints.add(tiles[i][col].getValue());
            }

            ints = squish_L_U(ints);
            for (int z=0; z<4; z++) {
                tiles[z][col].setValue(ints.get(z));
            }

            for (int i=0; i<3; i++) {
                if (tiles[i][col].same(tiles[i+1][col])) {
                    tiles[i][col].combine(tiles[i+1][col]);

                    ArrayList<Integer> ints_new = new ArrayList<Integer>();

                    for (int x=0; x<4; x++) {
                        ints_new.add(tiles[x][col].getValue());
                    }
                    ints_new = squish_L_U(ints_new);
                    for (int y=0; y < 4; y++) {
                        tiles[y][col].setValue(ints_new.get(y));
                    }
                }
            }

        }
    }

    public void swipe_D() {
        for (int col = 0; col < 4; col++ ) {
            ArrayList<Integer> ints = new ArrayList<Integer>();
            for (int i=0; i<4; i++) {
                ints.add(tiles[i][col].getValue());
            }

            ints = squish_R_D(ints);
            for (int z=0; z<4; z++) {
                tiles[z][col].setValue(ints.get(z));
            }

            for (int i=3; i>0; i--) {
                if (tiles[i][col].same(tiles[i-1][col])) {
                    tiles[i][col].combine(tiles[i-1][col]);

                    ArrayList<Integer> ints_new = new ArrayList<Integer>();

                    for (int x=0; x<4; x++) {
                        ints_new.add(tiles[x][col].getValue());
                    }
                    ints_new = squish_R_D(ints_new);
                    for (int y=0; y < 4; y++) {
                        tiles[y][col].setValue(ints_new.get(y));
                    }
                }
            }

        }
    }


}
