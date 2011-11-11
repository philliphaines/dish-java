package dish;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DishTest {
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidLife() throws Exception {
        boolean[][] life = {{true, true}, {true}};
        new Dish(life);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForNoLife() throws Exception {
        boolean[][] noLife = {};
        new Dish(noLife).evolve();
    }

    @Test
    public void testEvolveBlock() throws Exception {
        boolean[][] block = {
            { false, false, false, false },
            { false, true, true, false },
            { false, true, true, false },
            { false, false, false, false }
        };

        assertTrue(new Dish(block).equals(new Dish(block).evolve()));
        System.out.print(new Dish(block).evolve());
    }

    @Test
    public void testEvolveBlinker() throws Exception {
        boolean[][] blinker = {
            { false, false, false, false, false },
            { false, false, false, false, false },
            { false, true, true, true, false },
            { false, false, false, false, false },
            { false, false, false, false, false }
        };

        boolean[][] blinkerVertical = {
            { false, false, false, false, false },
            { false, false, true,  false, false },
            { false, false, true,  false, false },
            { false, false, true,  false, false },
            { false, false, false, false, false }
        };

        assertTrue(new Dish(blinker).equals(new Dish(blinker).evolve().evolve()));
        assertTrue(new Dish(blinkerVertical).equals(new Dish(blinker).evolve()));
        System.out.print(new Dish(blinker).evolve().toString());
    }

    @Test
    public void testEvolveToad() throws Exception {
        boolean[][] toad = {
            {false, false, false, false, false, false},
            {false, false, false, false, false, false},
            {false, false, true,  true,  true,  false},
            {false, true,  true,  true,  false, false},
            {false, false, false, false, false, false},
            {false, false, false, false, false, false}
        };

        assertTrue(new Dish(toad).equals(new Dish(toad).evolve().evolve()));
    }

    @Test
    public void testEvolveToadLoads() throws Exception {
        boolean[][] toad = {
            {false, false, false, false, false, false},
            {false, false, false, false, false, false},
            {false, false, true,  true,  true,  false},
            {false, true,  true,  true,  false, false},
            {false, false, false, false, false, false},
            {false, false, false, false, false, false}
        };

        Dish toadDish = new Dish(toad);
        int iterations = 10000;
        while (iterations > 0) {
            iterations--;
            toadDish = toadDish.evolve();
        }

        assertTrue(toadDish.equals(new Dish(toad)));
        System.out.print(toadDish);
    }

    @Test
    public void testEvolveGlider() throws Exception {
        boolean[][] glider = {
            {false, false, true,  false, false, false, false, false, false, false, false, false},
            {true,  false, true,  false, false, false, false, false, false, false, false, false},
            {false, true,  true,  false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false},
        };

        Dish gliderDish = new Dish(glider);

        int iterations = 30;
        while (iterations > 0) {
            iterations--;
            gliderDish = gliderDish.evolve();
        }

        String glides30Times =
                "000000000000\n" +
                "000000000000\n" +
                "000000000000\n" +
                "000000000000\n" +
                "000000000000\n" +
                "000000000000\n" +
                "000000000000\n" +
                "000000000100\n" +
                "000000000010\n" +
                "000000001110\n" +
                "000000000000";

        assertTrue(glides30Times.equals(gliderDish.toString().trim()));
        System.out.print(gliderDish);
    }
}
