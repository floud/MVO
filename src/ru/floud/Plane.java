package ru.floud;


import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by halturin_a on 05.09.2014.
 */
public class Plane {
    private static final String WELCOME_STRING = "Ввод точек на плоскость. Укажите координаты, для окончания ввода наберите \"exit\"";
    private static final String ERROR_STRING = "Указано неверное значение, введите число или \"exit\" для окончания ввода";
    private ArrayList<Point> points = new ArrayList<Point>();
    private ArrayList<Integer> list = new ArrayList<Integer>(); //массив с индексами точек-вершин в МВО

    public static double rotate(Point a, Point b, Point c) {
        return ((b.getX() - a.getX()) * (c.getY() - b.getY()) - (b.getY() - a.getY()) * (c.getX() - b.getX()));
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void printMvo() {


        ArrayList<Point> copy = new ArrayList<Point>(points);

        // Поиск самой левой точки (первая вершина МВО).
        Point left = null;
        double min = Double.MAX_VALUE;
        for (Point point : points)
        {
            if (point.getX() < min) {
                min = point.getX();
                left = point;
                list.add(points.indexOf(left));
            }
        }
        //Найденную точку и последнюю меняем местами для выхода из цикла при попадании на этот элемент (цикл сделает круг по точкам)
        copy.set(copy.indexOf(left), copy.get(copy.size() - 1));
        copy.set(copy.size() - 1, left);

        Point current = left;
        for (int i = 0; i < copy.size(); i++) {
            Point b = copy.get(i);
            if (list.contains(points.indexOf(b))) continue;
            for (int j = 0; j < copy.size(); j++) {
                Point c = copy.get(j);
                //if (c == current || c == b) continue;
                if (rotate(current, b, c) < 0) {
                    b = c;
                }
            }
            if (b == left) break;
            else {
                list.add(this.points.indexOf(b));
                current = b;
                //copy.remove(b);
            }
        }
        for (int ints : list)
        {
            System.out.printf(ints + 1 + " ");
        }
    }

    //Загрузка точек на плоскость
    public void loadPlane() throws IOException {
        this.points.add(new Point(2, 3));
        this.points.add(new Point(4, 4));
        this.points.add(new Point(4, 7));
        this.points.add(new Point(6, 5));
        this.points.add(new Point(7, 2));

        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(WELCOME_STRING);
        String a, b;
        double x, y;
        while (true) {
            System.out.println("Введите х:");
            a = reader.readLine();
            try {
                x = Double.parseDouble(a);
            } catch (Exception e) {
                if (a.equals("exit")) break;
                else {
                    System.out.println(ERROR_STRING);
                    continue;
                }
            }
            System.out.println("Введите y:");
            b = reader.readLine();
            try {
                y = Double.parseDouble(b);
            } catch (Exception e) {
                if (b.equals("exit")) break;
                else {
                    System.out.println(ERROR_STRING);
                    continue;
                }
            }
            this.addPoint(new Point(x, y));*/

        //}
    }
}
