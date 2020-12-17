package car.impl;

import parking.ParkingLot;
import turndirection.TurnDirection;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 19:25 2020/12/16
 * @ Description：子类Car
 * @ Modified By：
 * @Version: $version$
 */
public class OneCar extends AbstractCar {

    public OneCar(TurnDirection turnDirection, ParkingLot parkingLot, int x, int y, int o) {
        super(turnDirection, parkingLot, x, y, o);
    }
}