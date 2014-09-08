package ru.floud;

import com.sun.deploy.util.ArrayUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by halturin_a on 05.09.2014.
 */
public class Plane
{
    public ArrayList<Point> points = new ArrayList<Point>();

    public void addPoint(Point point) {
        points.add(point);
    }

    public void printMvo() {

        ArrayList<Integer> list = new ArrayList<Integer>(); //массив с индексами точек-вершин в МВО
        ArrayList<Point> copy = new ArrayList<Point>();
        Collections.copy(copy, points);
        System.out.println(copy.size());


        // Ищем нижнюю точку (первая вершина МВО)
        Point left = null;
        double min = Double.MAX_VALUE;
        for (Point point : copy) {
            if (point.getX() < min) {
                min = point.getX();
                left = point;
                list.add(this.points.indexOf(left));
            }
        }
        //Найденную точку помещаем в начало массива-копии
        copy.set(copy.indexOf(left), copy.get(0));
        copy.set(0, left);

        //Продолжаем поиск вершин итерацией через точки
        Point current = left; //Текущая ершина, относительно которой будет производиться поиск других вершин.
        for (int i = 1; i < copy.size(); i++) {
            for (int j = 1; j < copy.size(); j++) {
                if (rotate(current, copy.get(i), copy.get(j)) < 0) {
                    current = copy.get(j);
                }

            }
            if (current == left) break;
            else {
                list.add(this.points.indexOf(current));

            }
        }

        System.out.println(list);







    }

    public static double rotate(Point a, Point b, Point c) {
        return ((b.getX() - a.getX())*(c.getY() - b.getY()) - (b.getY() - a.getY()*(c.getX() - c.getY())));
    }
}
