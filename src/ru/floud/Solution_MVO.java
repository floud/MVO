package ru.floud;

import java.util.Map;
import java.util.Scanner;

/**
 * Created by halturin_a on 05.09.2014.
 */
public class Solution_MVO
{
    public static void main(String[] args)
    {
        Plane plane = new Plane();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("Ввод точек на плоскость");
            double x, y;
            System.out.print("Введите х: ");
            x = Double.parseDouble(scanner.next());
            System.out.printf("Введите y: ");
            y = scanner.nextDouble();
            plane.addPoint(new Point(x, y));
        }




    }
}
