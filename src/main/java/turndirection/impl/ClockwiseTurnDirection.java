package turndirection.impl;

import common.DirectionEnum;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 19:19 2020/12/16
 * @ Description：顺时针方向旋转类
 * @ Modified By：
 * @Version: $version$
 */
public class ClockwiseTurnDirection extends AbstractTurnDirection {

    @Override
    public int nextDirection(int direction) {
        DirectionEnum[] directionEnums = DirectionEnum.values();
        for (int i = 0; i < directionEnums.length; i++) {
            if (direction == directionEnums[i].getValue()) {
                if (i == directionEnums.length - 1) {
                    return directionEnums[0].getValue();
                }
                return directionEnums[i + 1].getValue();
            }
        }
        throw new RuntimeException("该方向不存在!");
    }
}