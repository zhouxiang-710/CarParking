package parking;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 19:19 2020/12/16
 * @ Description：停车场
 * @ Modified By：
 * @Version: $version$
 */
public interface ParkingLot {
    /**
     * 检测坐标是否在停车场范围内
     * @param x
     * @param y
     * @return true-在，false-不在
     */
    public boolean check(int x, int y);
}
