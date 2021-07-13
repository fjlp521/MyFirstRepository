/**
 * 客户类信息展示
 */
public class CustomerView {
    private CustomerList customerList = new CustomerList(10);
    public CustomerView(){
        customerList.addCustomer(new Customer("王磊", '男', 24 ,"18839107003", "2249553911@qq.com"));
    }
    public static void main(String[] args) {
        new CustomerView().enterMainMenu();
    }
    //进入软件主界面
    public void enterMainMenu(){
        boolean flag = true;
        do {
            System.out.println("\n------------------客户信息管理软件-----------------\n");
            System.out.println("                   1 添 加 客 户");
            System.out.println("                   2 修 改 客 户");
            System.out.println("                   3 删 除 客 户");
            System.out.println("                   4 客 户 列 表");
            System.out.println("                   5 退 出 软 件\n");
            System.out.print("                   请选择(1-5)：");
            char selection = CMUtility.readMenuSelection();
            switch (selection){
                case '1':
                    addNewCustomer();
                    break;
                case '2':
                    modifyCustomer();
                    break;
                case '3':
                    deleteCustomer();
                    break;
                case '4':
                    listAllCustomer();
                    break;
                case '5':
                    System.out.print("确定退出软件（Y/N）：");
                    if(CMUtility.readConfirmSelection() == 'Y'){
                        flag = false;
                        System.out.println("软件已退出...");
                    }

            }
        }while(flag);
    }
    //增加客户
    public void addNewCustomer(){
        System.out.println("---------------------添加客户---------------------");
        System.out.print("名字：");
        String name = CMUtility.readString(10);
        System.out.print("性别：");
        char gender = CMUtility.readChar();
        System.out.print("年龄：");
        int age = CMUtility.readInt();
        System.out.print("电话：");
        String phone = CMUtility.readString(18);
        System.out.print("邮箱：");
        String email = CMUtility.readString(20);
        customerList.addCustomer(new Customer(name,gender,age,phone,email));
        System.out.println("---------------------添加完成---------------------");
    }
    //修改客户
    public void modifyCustomer(){
        System.out.println("---------------------修改客户---------------------");
        if(customerList.getTotal() == 0){
            System.out.println("暂无客户，修改失败。。。");
            return;
        }
        int index;
        Customer customer;
        System.out.print("输入要修改的客户的编号（1 ~ " + customerList.getTotal()+ "有效，-1退出）：");
        while(true){
            index = CMUtility.readInt();
            if(index == -1) return;
            else{
                customer = customerList.getCustomer(index - 1);
                if(customer == null){
                    System.out.print("输入的编号异常（1 ~ " + customerList.getTotal()+ "有效），请重新输入（-1退出）：");
                    continue;
                }else{
                    System.out.print("姓名（" + customer.getName() + "）：");
                    customer.setName(CMUtility.readString(10, customer.getName()));
                    System.out.print("性别（" + customer.getGender() + "）：");
                    customer.setGender(CMUtility.readChar(customer.getGender()));
                    System.out.print("年龄（" + customer.getAge() + "）：");
                    customer.setAge(CMUtility.readInt(customer.getAge()));
                    System.out.print("电话（" + customer.getPhone() + "）：");
                    customer.setPhone(CMUtility.readString(18,customer.getPhone()));
                    System.out.print("邮箱（" + customer.getEmail() + "）：");
                    customer.setEmail(CMUtility.readString(20,customer.getEmail()));
                    System.out.println("---------------------修改完成---------------------");
                    break;
                }
            }
        }
    }
    //删除客户
    public void deleteCustomer(){
        System.out.println("---------------------删除客户---------------------");
        if(customerList.getTotal() == 0){
            System.out.println("列表为空，无可删除项。。。");
            return;
        }
        int index;
        System.out.print("输入要修改的客户的编号（1 ~ " + customerList.getTotal() + "有效，-1退出）：");
        while (true){
            index = CMUtility.readInt();
            if(index == -1) return;
            else{
                if(customerList.getCustomer(index - 1) == null){
                    System.out.print("输入的编号异常（1 ~ " + customerList.getTotal() + "有效，请重新输入（-1退出）：");
                    continue;
                }else{
                    System.out.print("确认删除（Y/N）：");
                    if(CMUtility.readConfirmSelection() == 'Y'){
                        customerList.deleteCustomer(index - 1);
                        System.out.println("---------------------删除成功---------------------");
                    }
                    break;
                }
            }
        }
    }
    //显示客户列表
    public void listAllCustomer(){
        System.out.println("-----------------客户列表------------------");
        if(customerList.getTotal() == 0){
            System.out.println("暂无客户");
        }else{
            System.out.println("编号\t\t姓名\t\t性别\t\t年龄\t\t电话\t\t\t\t邮箱");
            Customer[] cust = customerList.getAllCustomers();
            for (int i = 0; i < cust.length; i++) {
                System.out.println(i + 1 + "\t\t" + cust[i].getDetails());
            }
        }
        System.out.println("----------------客户列表完成---------------------");
    }
}
