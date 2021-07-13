
public class FamilyAccount {
    public static void main(String[] args) {
        int balance = 10000;
        String details = "收支\t\t\t账户金额\t\t\t收支金额\t\t\t说    明\n";
        boolean flag = true;
        while (flag)
        {
            System.out.println("\n-----------------家庭收支记账软件-----------------\n");
            System.out.println("                   1 收支明细");
            System.out.println("                   2 登记收入");
            System.out.println("                   3 登记支出");
            System.out.println("                   4 退出系统\n");
            System.out.print("                   请选择(1-4)：");
            char select = Utility.readMenuSelection();
            System.out.println();
            switch (select) {
                case '1':
                    System.out.println("-----------------当前收支明细记录-----------------");
                    System.out.println(details);
                    System.out.println("--------------------------------------------------");
                    break;
                case '2':
                    System.out.print("请输入本次收入金额：");
                    int amount1 = Utility.readNumber();
                    System.out.print("请输入本次收入说明：");
                    String desc1 = Utility.readString();
                    balance += amount1;
                    details += "收入\t\t\t" + balance + "\t\t\t" + amount1 + "\t\t\t" + desc1 + "\n";
                    System.out.println("---------------------登记完成---------------------");
                    break;
                case '3':
                    int amount2;
                    System.out.print("请输入本次支出金额：");
                    do {
                        amount2 = Utility.readNumber();
                        if(amount2 > balance){
                            System.out.print("余额不足，请重新输入：");
                        }else break;
                    }while (true);
                    System.out.print("请输入本次支出说明：");
                    String desc2 = Utility.readString();
                    balance -= amount2;
                    details += "收入\t\t\t" + balance + "\t\t\t" + amount2 + "\t\t\t" + desc2 + "\n";
                    System.out.println("---------------------登记完成---------------------");
                    break;
                case '4':
                    System.out.print("确认是否退出（Y/N）：");
                    if (Utility.readConfirmSelection() == 'Y') {
                        System.out.println("感谢使用，再见！");
                        flag = false;
                    } else {
                        break;
                    }
            }
        }
    }
}
