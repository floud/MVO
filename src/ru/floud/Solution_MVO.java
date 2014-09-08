package ru.floud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by halturin_a on 05.09.2014.
 */
public class Solution_MVO
{
    public static void main(String[] args) throws IOException {
        //создаем плоскость и наносим на нее точки с координатами х и y
        Plane plane = new Plane();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ввод точек на плоскость. Укажите числа-координаты, для окончания ввода наберите \"exit\"");
        String a, b;
        double x, y;
        while (true) {
            System.out.println("Введите х:");
            a = reader.readLine();
            try {
                x = Double.parseDouble(a);
            }
            catch (Exception e) {
                if (a.equals("exit")) break;
                else {
                    System.out.println("Указано неверное значение, введите число или \"exit\" для окончания ввода");
                    continue;
                }
            }
            System.out.println("Введите y:");
            b = reader.readLine();
            try {
                y = Double.parseDouble(b);
            }
            catch (Exception e) {
                if (b.equals("exit")) break;
                else {
                    System.out.println("Указано неверное значение, введите число или \"exit\" для окончания ввода");
                    continue;
                }
            }
            plane.addPoint(new Point(x, y));

        }
        for (Point p : plane.points) {
            System.out.println(p);
        }
        plane.printMvo();


    }
}
