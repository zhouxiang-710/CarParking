package turndirection;

import car.Car;
import parking.ParkingLot;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 19:19 2020/12/16
 * @ Description：方向控制
 * @ Modified By：
 * @Version: $version$
 */
public interface TurnDirection {
    /**
     * 根据当前方向currentO判断并返回允许转动的方向
     * @param currentO
     * @return
     */
    int nextDirection(int currentO);

    /**
     * 根据当前car位置判断如果前进是否越界, true为将会越界, false不会
     */
    boolean checkOverBoundary(ParkingLot parkingLot, Car car);

    /**
     * 根据car的当前状态,计算目的地(x1,y1)在car的哪个方向
     */
    int calculateTargetDirection(Car car, int x1, int y1);
}

