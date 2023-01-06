/**
 * Classe représentant les coordonnées
 */
public class Coordinate {
    private int currentX;
    private int currentY;
    private static final int XMIN = 0;
    private static final int YMIN = 0;
    private static final int XMAX = 8;
    private static final int YMAX = 8;

    public Coordinate (int x, int y) {
        this.currentX = x;
        this.currentY = y;
    }
    public int getX() {
        return this.currentX;
    }
    public int getY() {
        return this.currentY;
    }
    public static int getXMIN() {
        return XMIN;
    }
    public static int getYMIN() {
        return YMIN;
    }
    public static int getXMAX() {
        return XMAX;
    }
    public static int getYMAX() {
        return YMAX;
    }
    public int incrementX() {
        if (this.currentX == XMAX) {
            return this.currentX;
        }
        return ++this.currentX;
    }
    public int incrementY() {
        if (this.currentY == YMAX) {
            return this.currentY;
        }
        return ++this.currentY;
    }
    public int decrementX() {
        if (this.currentX == XMIN) {
            return this.currentX;
        }
        return --this.currentX;
    }
    public int decrementY() {
        if (this.currentY == YMIN) {
            return this.currentY;
        }
        return --this.currentY;
    }

    public boolean equals(Coordinate other) {
        if (other == null) {
            return false;
        }
        return this.currentX == other.getX() && this.currentY == other.getY();
    }

    public boolean isAtNorthPole() {
        return this.currentY == YMIN;
    }
    public boolean isAtSouthPole() {
        return this.currentY == YMAX;
    }

    public void wrapXMax() {
        this.currentX = XMIN;
    }
    public void wrapXMin() {
        this.currentX = XMAX;
    }
    public void wrapYMax() {
        this.currentY = YMIN;
    }
    public void wrapYMin() {
        this.currentY = YMAX;
    }
}
