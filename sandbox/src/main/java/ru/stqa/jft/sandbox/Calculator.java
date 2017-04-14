package ru.stqa.jft.sandbox;

public class Calculator {

    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(5, 5);

        System.out.println("Расстояние между точками с координатами ("+ p1.x + ", " + p1.y + ") и (" + p2.x + ", " + p2.y + ") = " + distance(p1,p2));
        System.out.println();
        System.out.println("Альтернативная реализация через метод класса");
        System.out.println("Расстояние от точки с координатами ("+ p1.x + ", " + p1.y + ") до точки с координатами (" + p2.x + ", " + p2.y + ") = " + p1.distance(p2));
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }
}
