package car;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 19:30 2020/12/16
 * @ Description：车子接口
 * @ Modified By：
 * @Version: $version$
 */
public interface Car {

    /**
     * @Description 移动到某个位置
     * @param x
     * @param y
     * @return
     */
    boolean moveTo(int x, int y);

    /**
     * @Description 转向
     * @param d
     * @return
     */
    boolean turn(int d);

    /**
     * @Description 获取car的X坐标
     */
    int getPositionX();

    /**
     * @Description 获取car的Y坐标
     */
    int getPositionY();

    /**
     * @Description 获取当前car的方向
     */
    int getOrientation();

    /**
     * @Description 前进多少步
     * @param n
     */
    void moveForward(int n);
}

