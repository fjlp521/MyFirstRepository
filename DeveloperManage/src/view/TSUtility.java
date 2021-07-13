package view;
import java.util.*;
public class TSUtility {
    private static Scanner scanner = new Scanner(System.in);
    public static char readMenuSelection(){
        char c;
        while(true){
            String str = readKeyBoard(1, false);
            c = str.charAt(0);
            if(c != '1' && c != '2' && c != '3' && c != '4'){
                System.out.print("选择错误，请重新输入：");
            }else break;
        }
        return c;
    }
    public static void readReturn(){
        System.out.print("按回车键继续...");
        readKeyBoard(100, true);
    }
    public static int readInt(){
        int n;
        while(true){
            String str = readKeyBoard(2, false);
            try{
                n = Integer.parseInt(str);
                break;
            }catch (NumberFormatException e){
                System.out.print("数字输入错误，请重新输入：");
            }
        }
        return n;
    }
    public static char readConfirmSelection(){
        char c;
        while(true){
            String str = readKeyBoard(1, false).toUpperCase();
            c = str.charAt(0);
            if(c == 'Y' || c == 'N')
                break;
            else{
                System.out.print("选择错误，请重新输入：");
            }
        }
        return c;
    }
    private static String readKeyBoard(int limit, boolean blankReturn){
        String line = "";
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            if(line.length() == 0){
                if(blankReturn) return line;
                else continue;
            }
            if(line.length() < 1 || line.length() > limit){
                System.out.print("输入长度错误，请重新输入（不大于" + limit + "）：");
                continue;
            }
            break;
        }
        return line;
    }
}
