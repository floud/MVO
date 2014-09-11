package ru.floud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * Created by halturin_a on 05.09.2014.
 */
public class Solution_MVO
{
    private static final String WELCOME_STRING = "Ввод точек на плоскость. Укажите координаты, для окончания ввода наберите \"exit\"";
    private static final String ERROR_STRING = "Указано неверное значение, введите число или \"exit\" для окончания ввода";
    private static final String ENTER_X = "Введите х:";
    private static final String ENTER_Y = "Введите y:";
    private static final String EXIT = "exit";
    private static ArrayList<Point> points = new ArrayList<Point>(); //массив точек на плоскости
    private static ArrayList<Integer> list = new ArrayList<Integer>(); //массив с индексами точек-вершин в МВО

    public static void main(String[] args) throws IOException
    {
        loadPoints();
        printMvo();
    }

    //Проверяет с какой стороны луча "ab" на ходится точка "с"
    //Если результат > 0 то левее, если < 0 - правее, если = 0 - на луче.
    public static double rotate(Point a, Point b, Point c)
    {
        return ((b.getX() - a.getX()) * (c.getY() - b.getY()) - (b.getY() - a.getY()) * (c.getX() - b.getX()));
    }

    //Измерение длины отрезка, состоящего из двух точек.
    public static double segmentLength(Point one, Point two)
    {
        return Math.sqrt(Math.pow((two.getX() - one.getX()), 2) + Math.pow((two.getY() - one.getY()), 2));
    }


    //Выводит на экран точки-вершины МВО
    public static void printMvo()
    {
        ArrayList<Point> copy = new ArrayList<Point>(points); //список-копия для манипуляций с расположением точек в массиве, не затрагивая оригинал.

        //Поиск самой левой нижней точки (первая вершина МВО).
        Point left = null;
        double minX = Double.MAX_VALUE;
        double minY = Double.MIN_VALUE;
        for (Point point : points)
        {
            if (point.getX() < minX || point.getX() == minX && point.getY() < minY)
            {
                minX = point.getX();
                minY = point.getY();
                left = point;
            }
        }
        list.add(points.indexOf(left));

        //Найденную точку и последнюю в списке меняем местами для выхода из цикла при попадании на этот элемент (цикл сделает круг по всем точкам)
        copy.set(copy.indexOf(left), copy.get(copy.size() - 1));
        copy.set(copy.size() - 1, left);

        //"Проход" по всем точкам в поисках точек-вершин
        Point current = left;
        for (int i = 0; i < copy.size(); i++)
        {
            Point b = copy.get(i);
            if (b == current) continue;
            for (int j = 0; j < copy.size(); j++)
            {
                Point c = copy.get(j);
                double angle = rotate(current, b, c);
                if (c == b) continue;
                if (angle > 0 || angle == 0 && (segmentLength(current, b) < segmentLength(current, c)))
                {
                    b = c;
                }
            }
            if (b == left) break;
            else
            {
                list.add(points.indexOf(b));
                current = b;
            }
        }
        for (int ints : list)
        {
            System.out.printf(ints + 1 + " ");
        }

    }

    //Загрузка точек на плоскость
    public static void loadPoints() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(WELCOME_STRING);
        String a, b;
        double x, y;
        while (true)
        {
            System.out.println(ENTER_X);
            a = reader.readLine();
            try
            {
                x = Double.parseDouble(a);
            }
            catch (Exception e)
            {
                if (a.equals(EXIT)) break;
                else
                {
                    System.out.println(ERROR_STRING);
                    continue;
                }
            }
            System.out.println(ENTER_Y);
            b = reader.readLine();
            try
            {
                y = Double.parseDouble(b);
            }
            catch (Exception e)
            {
                if (b.equals(EXIT)) break;
                else
                {
                    System.out.println(ERROR_STRING);
                    continue;
                }
            }
            addPoint(new Point(x, y));

        }
    }

    public static void addPoint(Point point)
    {
        points.add(point);
    }
}
