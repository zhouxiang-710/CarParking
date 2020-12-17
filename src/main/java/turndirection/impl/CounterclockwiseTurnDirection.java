package turndirection.impl;

import common.DirectionEnum;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 19:19 2020/12/16
 * @ Description：逆时针方向方向旋转类
 * @ Modified By：
 * @Version: $version$
 */
public class CounterclockwiseTurnDirection extends AbstractTurnDirection {

    @Override
    public int nextDirection(int direction) {
        DirectionEnum[] directionEnums = DirectionEnum.values();//根据定义,directionEnums内元素默认是顺时针的.
        for (int i = directionEnums.length - 1; i >= 0; i--) {
            if (direction == directionEnums[i].getValue()) {
                if (i == 0) {
                    return directionEnums[directionEnums.length - 1].getValue();
                }
                return directionEnums[i - 1].getValue();
            }
        }
        throw new RuntimeException("该方向不存在!");
    }
}
