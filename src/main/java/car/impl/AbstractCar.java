package car.impl;

import car.Car;
import common.DirectionEnum;
import lombok.Data;
import parking.ParkingLot;
import turndirection.TurnDirection;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 19:25 2020/12/16
 * @ Description：车子抽类
 * @ Modified By：
 * @Version: $version$
 */
@Data
public abstract class AbstractCar implements Car {

    private TurnDirection turnDirection;

    private ParkingLot parkingLot;

    private int x;

    private int y;
    /**
     * 当前方向Orientation
     */
    private int o;

    public AbstractCar(TurnDirection turnDirection, ParkingLot parkingLot, int x, int y, int o) {
        this.initCar(turnDirection, parkingLot, x, y, o);
    }

    @Override
    public int getPositionX() {
        return x;
    }

    @Override
    public int getPositionY() {
        return y;
    }

    @Override
    public int getOrientation() {
        return o;
    }

    /**
     *移动到哪个位置
     */
    @Override
    public boolean moveTo(int x1, int y1) {
        //入参合法性检测
        if (!parkingLot.check(x1, y1)) {
            throw new RuntimeException("车辆目的地坐标不在范围内!");
        }

        //原地
        if (x1 == this.x && y1 == this.y) {
            throw new RuntimeException("车辆目的地坐标起点位置");
        }

        //计算出目的地在car的哪个方向并转向那个方向
        int firstDirection = turnDirection.calculateTargetDirection(this, x1, y1);
        while (this.o != firstDirection) {//判断车向是否指向目的地方向. 若否,则一直移动并转至同向
            this.turnToNext();

            //查看是否是恰好贴近边界或指向边界, 若是,则循环转向直到找出不面向边界的方向
            while (turnDirection.checkOverBoundary(parkingLot, this)) {
                this.turnToNext();
            }

            if (this.o == firstDirection) {
                break;
            }

            this.moveForward(1);//移动一格
        }

        //令car移动到和目的地格子同列/同行
        int tmpFirst = 0, tmpSecond = 0;
        boolean nOrS = this.o == DirectionEnum.NORTH.getValue() || this.o == DirectionEnum.SOUTH.getValue();
        if (nOrS) {//如果当前方向是N或S, 则移动到同一行:
            tmpFirst = y1 - this.y;
            tmpSecond = x1 - this.x;
        } else {//如果当前方向是W或E, 则移动到同一列:
            tmpFirst = x1 - this.x;
            tmpSecond = y1 - this.y;
        }
        this.moveForward(tmpFirst > 0 ? tmpFirst : -tmpFirst);

        //car目前已经和目的地同列/同行位置, 接下来转一个方向直走即可抵达
        if (!(x1 == this.x && y1 == this.y)) {//如果此时已经抵达位置(即(x1 == x && y1 == y)), 则不再转
            this.turnToNext();
            //移动到目的地
            this.moveForward(tmpSecond > 0 ? tmpSecond : -tmpSecond);
        }

        return true;
    }

    /**
     * 令车转向下一个方向.
     */
    public void turnToNext() {
        this.o = turnDirection.nextDirection(this.o);
        System.out.println("-->转向:" + this.toString());
    }

    /**
     * 将车转向指定方向,可以通过子类覆盖此方法
     */
    @Override
    public boolean turn(int direction) {
        int tmp = turnDirection.nextDirection(this.o);
        if (tmp == direction) {
            this.o = turnDirection.nextDirection(this.o);
            System.out.println("-->转向:" + this.toString());
            return true;
        }

        return false;
    }

    /**
     * 指定向前走n步, 会先判断是否出界
     * 逐步向前移动, 同时n--, 遇见边界后如果n依然大于0,那么提前抛异常退出
     */
    @Override
    public void moveForward(int n) {
        if (n < 0) {
            throw new RuntimeException("入参: " + n + " 不合法");
        }

        while (n > 0) {
            if (turnDirection.checkOverBoundary(parkingLot, this)) {
                throw new RuntimeException("moveForward(" + n + ")越界!");
            }

            if (this.o == DirectionEnum.NORTH.getValue()) {
                this.y++;
            } else if (this.o == DirectionEnum.EAST.getValue()) {
                this.x++;
            } else if (this.o == DirectionEnum.SOUTH.getValue()) {
                this.y--;
            } else {
                this.x--;
            }
            System.out.println("-->前进一步:" + this.toString());
            n--;
        }
    }

    /**
     * 初始化
     */
    private boolean initCar(TurnDirection turnDirection, ParkingLot parkingLot, int x, int y, int o) {
        //合法检测
        if (!parkingLot.check(x, y)) {
            throw new RuntimeException("初始坐标(x,y)不合法!");
        }

        boolean flag = false;//校验方向是否合理
        for (DirectionEnum d : DirectionEnum.values()) {
            if (o == d.getValue()) {
                flag = true;
            }
        }
        if (!flag){
            throw new RuntimeException("初始方向:" + o + " 不合法!");
        }
        this.turnDirection = turnDirection;
        this.parkingLot = parkingLot;
        this.x = x;
        this.y = y;
        this.o = o;

        return true;
    }


    @Override
    public String toString() {
        return "当前车的状态:Car{" +
                "  x方向=" + x +
                "  y方向=" + y +
                "  旋转方向值0=" + o +
                "  parkingLot=" + parkingLot.toString()
                + " } ";
    }
}
