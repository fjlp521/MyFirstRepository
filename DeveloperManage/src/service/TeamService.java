package service;

import domain.Architect;
import domain.Designer;
import domain.Employee;
import domain.Programmer;

public class TeamService {
    private static int counter = 1; //用于自动生成团队成员的memberId
    private final int MAX_MEMBER = 5; //团队人数上限
    private Programmer[] team = new Programmer[MAX_MEMBER];
    private int total;

    //获取团队列表
    public Programmer[] getTeam(){
        Programmer[] team = new Programmer[total];
        for (int i = 0; i < total; i++) {
            team[i] = this.team[i];
        }
        return team;
    }
    //添加团队成员
    public void addMember(Employee e) throws TeamException{
        //成员已满
        if(total >= MAX_MEMBER)
            throw new TeamException("成员已满，无法添加");
        //非开发人员
        if(!(e instanceof Programmer))
            throw new TeamException("该成员不是开发人员，无法添加");
        Programmer p = (Programmer) e;
        //已在本团队中
        if(isExist(p))
            throw new TeamException("该员工已在本团队中");
        //为其他团队开发人员或休假
        switch (p.getStatus()){
            case BUSY:
                throw new TeamException("该员工已为其他团队成员");
            case VOCATION:
                throw new TeamException("该员工正在休假，无法添加");
        }

        int numOfArch = 0, numOfDsgn = 0, numOfPrg = 0;
        for (int i = 0; i < total; i++) {
            if(team[i] instanceof Architect) numOfArch++;
            else if(team[i] instanceof Designer) numOfDsgn++;
            else numOfPrg++;
        }
        if(p instanceof Architect){
            if(numOfArch >= 1) throw new TeamException("团队中至多只能有一名架构师");
        }else if(p instanceof Designer){
            if(numOfDsgn >= 2) throw new TeamException("团队中至多只能有两名设计师");
        }else{
            if(numOfPrg >= 3) throw new TeamException("团队中至多只能有三名程序员");
        }
        team[total++] = p;
        p.setStatus(Status.BUSY);
        p.setMemberId(counter++);
    }
    private boolean isExist(Programmer p){
        for (int i = 0; i < total; i++) {
            if(team[i].getId() == p.getId()) return true;
        }
        return false;
    }
    //删除指定memberId的程序员
    public void removeMember(int memberId) throws TeamException{
        int n;
        for (n = 0; n < total; n++){
            if(team[n].getMemberId() == memberId){
                team[n].setStatus(Status.FREE);
                break;
            }
        }
        if(n == total){
            throw new TeamException("找不到该成员，无法删除");
        }
        for(int j = n; j < total - 1; j++){
            team[j] = team[j + 1];
        }
        team[--total] = null;
    }
    //判断团队是否已满
    public boolean isFull(){
        if(total >= MAX_MEMBER)
            return true;
        return false;
    }
    //判断团队是否为空
    public boolean isEmpty(){
        return total == 0;
    }
}
