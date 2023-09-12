package org.oop;

public class Diamond {
    private int size;

    public Diamond(int size) {
        this.size = size;
    }

    public void printDiamond() {
        int halfSize = size / 2;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < Math.abs(halfSize - i); j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < size - 2 * Math.abs(halfSize - i); j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /*public static void main(String[] args) {
        Diamond diamond = new Diamond(7);
        diamond.printDiamond();
    }*/

}
