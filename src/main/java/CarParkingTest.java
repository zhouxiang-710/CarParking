import car.impl.OneCar;
import parking.ParkingLot;
import parking.impl.CarParking;
import turndirection.TurnDirection;
import turndirection.impl.ClockwiseTurnDirection;
import turndirection.impl.CounterclockwiseTurnDirection;

import java.util.Scanner;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 20:19 2020/12/16
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
public class CarParkingTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("车辆开始初始化:");
        System.out.println("按顺序指定长方形地图边界(startX, endX, startY, endY), 以逗号隔开:");
        String tmp = scanner.nextLine();
        String[] tmpMap = tmp.split(",");
        ParkingLot parkingLot = new CarParking(
        Integer.valueOf(tmpMap[0].trim()), Integer.valueOf(tmpMap[1].trim()), Integer.valueOf(tmpMap[2].trim()), Integer.valueOf(tmpMap[3].trim()));

        System.out.println("按顺序指定汽车初始坐标(x,y), 以逗号隔开:");
        String[] tmpCar = scanner.nextLine().split(",");
        int x = Integer.valueOf(tmpCar[0].trim());
        int y = Integer.valueOf(tmpCar[1].trim());

        System.out.println("指定汽车初始方向, 0->N, 1->E, 2->S, 3->W :");
        int o = Integer.valueOf(scanner.nextLine().trim());

        System.out.println("指定旋转方向: 0->顺时针, 1->逆时针");
        int dc = Integer.valueOf(scanner.nextLine().trim());
        TurnDirection turnDirection = dc==0? new ClockwiseTurnDirection(): new CounterclockwiseTurnDirection();

        OneCar oneCar = new OneCar(turnDirection, parkingLot, x, y, o);
        System.out.println("汽车初始化完毕: "+ oneCar.toString());

        boolean isEnd = false;
        while (!isEnd) {
            try{
                System.out.println();
                System.out.println("请输入汽车指令, 1->去往指定目的地, 2->转一次方向, 3->前进, 4->获取汽车当前信息, 5->退出 : ");
                int command = Integer.valueOf(scanner.nextLine().trim());
                switch (command) {
                    case 1:
                        System.out.println("  按顺序指定目的地坐标(x,y), 以逗号隔开:");
                        String[] tmpTarget = scanner.nextLine().split(",");
                        int tx = Integer.valueOf(tmpTarget[0].trim());
                        int ty = Integer.valueOf(tmpTarget[1].trim());
                        System.out.println("car行驶轨迹:");
                        oneCar.moveTo(tx, ty);
                        break;

                    case 2:
                        System.out.println("  请指定方向, 0->N, 1->E, 2->S, 3->W :");
                        int tO = Integer.valueOf(scanner.nextLine().trim());
                        if (!oneCar.turn(tO)) {
                            System.out.println("转向失败!");
                        }
                        break;

                    case 3:
                        System.out.println("请指定前进步数:");
                        int tSteps = Integer.valueOf(scanner.nextLine().trim());
                        oneCar.moveForward(tSteps);
                        break;

                    case 4:
                        System.out.println("    "+ oneCar.toString());
                        break;

                    case 5:
                        isEnd = true;
                        break;
                    default:
                        System.out.println("WARNING:  未支持指令!");
                }
            } catch (Exception e){
                System.out.println("ERROR--["+e.getMessage()+"] "+ oneCar.toString());
            }
        }
        System.out.println("End");
    }
}
