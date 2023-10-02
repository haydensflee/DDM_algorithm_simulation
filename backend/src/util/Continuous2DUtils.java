package util;

import ec.util.MersenneTwisterFast;
import sim.field.continuous.Continuous2D;
import sim.util.Double2D;

public class Continuous2DUtils {
    public static Double2D randomPositionAtBorder(Continuous2D field, MersenneTwisterFast random) {
        int border = random.nextInt(4);
        switch (border) {
            // left border
            case 0:
                return new Double2D(0, field.height * random.nextDouble());
            // right border
            case 1:
                return new Double2D(field.width, field.height * random.nextDouble());
            // top border
            case 2:
                return new Double2D(field.width * random.nextDouble(), 0);
            // bottom border
            case 3:
                return new Double2D(field.width * random.nextDouble(), field.height);
            default:
                return null;
        }
    }
}
