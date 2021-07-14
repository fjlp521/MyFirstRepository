package view;

import service.*;
import domain.*;


public class TeamView {
    private NameListService listSvc = new NameListService();//员工信息
    private TeamService teamSvc = new TeamService();//团队列表
    public void enterMainMenu(){
        boolean flag = true;
        char key = 0;
        while (flag){
            if(key != '1')
                listAllEmployees();
            System.out.print("1-团队列表 2-添加团队成员 3-删除团队成员 4-退出  （请选择1-4）：");
            key = TSUtility.readMenuSelection();
            switch (key){
                case '1' ://显示开发成员列表
                    listTeam();
                    break;
                case '2'://添加团队成员
                    addMember();
                    break;
                case '3'://删除团队成员
                    deleteMember();
                    break;
                case '4'://退出
                    System.out.print("确认是否退出（Y/N）：");
                    if(TSUtility.readConfirmSelection() == 'Y'){
                        flag = false;
                        System.out.println("软件已退出...");
                    }
                    break;
            }
        }
    }
    //显示所有的员工成员
    private void listAllEmployees(){
        System.out.println("\n--------------------------开发调度软件------------------------");
        Employee[] employees = listSvc.getAllEmployees();
        System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");
        for(Employee e : employees)
            System.out.println(e);
        System.out.println("---------------------------------------------------------------");
    }
    //显示开发团队成员列表
    private void listTeam(){
        System.out.println("--------------团队列表----------------\n");
        Programmer[] team = teamSvc.getTeam();
        if(team.length == 0)
            System.out.println("开发团队目前没有成员！");
        else
            System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票");
        for (Programmer p : team){
            System.out.println(p.getDetailsForTeam());
        }
        System.out.println("----------------------------------");
    }
    //添加团队成员
    private void addMember(){
        System.out.println("------------添加成员-------------");
        int id;
        while(true){
            if(teamSvc.isFull()){
                System.out.println("\n团队成员已满，无法再添加!");
                break;
            }
            System.out.print("请输入要添加员工的ID（-1退出）：");
            id = TSUtility.readInt();
            if(id < 0)
                break;
            try{
                Employee e = listSvc.getEmployee(id);
                teamSvc.addMember(e);
                System.out.println("添加成功");
            }catch(TeamException e){
                System.out.println("添加失败，原因：" + e.getMessage());
            }
        }
        TSUtility.readReturn();
    }
    //删除团队成员
    private void deleteMember(){
        System.out.println("------------删除成员-------------");
        if(teamSvc.isEmpty()){
            System.out.println("团队列表为空，删除失败");
            TSUtility.readReturn();
            return;
        }
        System.out.print("请输入要删除员工的TID（-1退出）：");
        int id = TSUtility.readInt();
        if(id < 0){
            System.out.println("已退出");
            TSUtility.readReturn();
            return;
        }

        System.out.print("确认是否删除（Y/N）：");
        if(TSUtility.readConfirmSelection() == 'N')
            return;
        try{
            teamSvc.removeMember(id);
            System.out.println("删除成功");
        }catch(TeamException e){
            System.out.println("删除失败，原因：" + e.getMessage());
        }
        TSUtility.readReturn();
    }
    public static void main(String[] args) {
        new TeamView().enterMainMenu();
    }
}
