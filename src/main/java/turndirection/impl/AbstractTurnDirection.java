package turndirection.impl;

import car.Car;
import common.DirectionEnum;
import parking.ParkingLot;
import turndirection.TurnDirection;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 19:19 2020/12/16
 * @ Description：方向控制旋转抽类
 * @ Modified By：
 * @Version: $version$
 */
public abstract class AbstractTurnDirection implements TurnDirection {

    @Override
    public int calculateTargetDirection(Car car, int x1, int y1) {
        int x = car.getPositionX();
        int y = car.getPositionY();

        int firstDirection = 0;
        if (x1 > x) {
            if (y1 > y) {
                firstDirection = DirectionEnum.NORTH.getValue();
            } else {
                firstDirection = DirectionEnum.EAST.getValue();
            }
        } else if (x1 < x) {
            if (y1 >= y) {
                firstDirection = DirectionEnum.WEST.getValue();
            } else {
                firstDirection = DirectionEnum.SOUTH.getValue();
            }
        } else {
            if (y1 > y) {
                firstDirection = DirectionEnum.NORTH.getValue();
            } else {
                firstDirection = DirectionEnum.SOUTH.getValue();
            }
        }

        return firstDirection;
    }

    @Override
    public boolean checkOverBoundary(ParkingLot parkingLot, Car car) {
        int tmpX = car.getPositionX(), tmpY = car.getPositionY();

        if (car.getOrientation() == DirectionEnum.NORTH.getValue()) {
            tmpY ++;
        } else if (car.getOrientation() == DirectionEnum.EAST.getValue()) {
            tmpX ++;
        } else if (car.getOrientation() == DirectionEnum.SOUTH.getValue()) {
            tmpY --;
        } else {
            tmpX --;
        }
        return !parkingLot.check(tmpX, tmpY);
    }
}


