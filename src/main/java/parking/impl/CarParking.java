package parking.impl;

import lombok.Getter;
import parking.ParkingLot;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 19:19 2020/12/16
 * @ Description：停车厂实现类
 * @ Modified By：
 * @Version: $version$
 */
@Getter
public class CarParking implements ParkingLot {
    private final int startX;
    private final int endX;
    private final int startY;
    private final int endY;

    public CarParking(int startX, int endX, int startY, int endY) {
        if (startX >= endX || startY >= endY)
            throw new RuntimeException("停车场范围不合法");

        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
    }

    @Override
    public boolean check(int x, int y) {
        if (x < startX || x > endX || y < startY || y > endY) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CarPark{" +
                "startX=" + startX +
                ", endX=" + endX +
                ", startY=" + startY +
                ", endY=" + endY +
                '}';
    }
}
