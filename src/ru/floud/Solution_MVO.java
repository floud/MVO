package ru.floud;

import java.io.IOException;


/**
 * Created by halturin_a on 05.09.2014.
 */
public class Solution_MVO
{
    public static void main(String[] args) throws IOException
    {
        //создаем плоскость и наносим на нее точки с координатами х и y
        Plane plane = new Plane();
        plane.loadPlane();
        plane.printMvo();

    }
}
