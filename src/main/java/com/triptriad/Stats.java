package com.triptriad;
public class Stats {

            private int top;
            private int bottom;
            private int left;
            private int right;

    public Stats()
    {
        this.top = 0;
        this.bottom = 0;
        this.left = 0;
        this.right = 0;
    }

    public Stats(int top, int bottom, int left, int right)
    {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public int getTop() { return this.top; }
    public void setTop(int top) { this.top = top; }

    public int getBottom() { return this.bottom; }
    public void setBottom(int bottom) { this.bottom = bottom; }

    public int getLeft() { return this.left; }
    public void setLeft(int left) { this.left = left; }

    public int getRight() { return this.right; }
    public void setRight(int right) { this.right = right; }

    @Override
public String toString() {
    return "Stats{" +
            "top=" + top +
            ", bottom=" + bottom +
            ", left=" + left +
            ", right=" + right +
            '}';
}
}
