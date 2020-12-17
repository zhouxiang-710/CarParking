package common;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 19:24 2020/12/16
 * @ Description：东南西北枚举值
 * @ Modified By：
 * @Version: $version$
 */
public enum DirectionEnum {
    NORTH(0, "N"),
    EAST(1, "E"),
    SOUTH(2, "S"),
    WEST(3, "W");

    private int value;
    private String name;

    private DirectionEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }
    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
