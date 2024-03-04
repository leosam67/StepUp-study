package ru.leosam.game;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Unit unit = new Unit("Dwarf");
        unit.setHealth(100);
        unit.setResource(Resource.GOLD, 100);
        unit.setResource(Resource.FOOD, 100);
        unit.setResource(Resource.WATER, 100);
        unit.setResource(Resource.ENERGY, 100);
    }
}
