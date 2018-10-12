package com.resort.springbootMolvenoLake;

import java.util.Random;

public class Mathf
{
    private static final Random random = new Random();

    public static float NormalizeValue(float value, float min, float max)
    {
        return (value - min) / (max - min);
    }
    public static double NormalizeValue(double value, double min, double max)
    {
        return (value - min) / (max - min);
    }

    public static int clampInt(int value, int min, int max)
    {
        value = value > max ? max : value;
        return value = value < min ? min : value;
    }

    public static double calculateDistance(int originX, int originY, int endX, int endY)
    {
        int vx = originX - endX;
        int vy = originY - endY;
        return Math.sqrt(vx*vx + vy*vy);
    }

    public static int getRandomInt(int min, int max)
    {
        int randomInt = random.nextInt(min + max);
        randomInt = randomInt + min > min ? randomInt - min : min;
        return Mathf.clampInt(randomInt, min, max);
    }

    public static boolean inRange(int value, int min, int max)
    {
        return value >= min && value <= max;
    }

    public static boolean intToBool(int nr)
    {
        return nr == 1;
    }

    public static long StringToCleanLong(String... input)
    {
        StringBuilder sB = new StringBuilder();
        for(int i = 0; i < input.length; i++)
        {
            String inputInc = input[i];
            for(int c = 0; c < inputInc.length(); c++)
            {
                char cha = inputInc.charAt(c);
                if( Character.isDigit(cha) )
                {
                    sB.append(cha);
                    System.out.println(cha);
                }
            }
        }
        return Long.parseLong(sB.toString());
    }
}
