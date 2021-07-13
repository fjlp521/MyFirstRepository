/**
 * 客户列表类增删改查相关操作
 */
public class CustomerList {
    private Customer[] customers;
    private int total;
    public CustomerList (int totalCustomer){
        customers = new Customer[totalCustomer];
    }

    //增
    public void addCustomer(Customer customer){
        if(total == customers.length){
            Customer[] temp = customers;
            customers = new Customer[2*total];
            for (int i = 0; i < temp.length; i++) {
                customers[i] = temp[i];
            }
        }
        customers[total++] = customer;
    }

    //删
    public boolean deleteCustomer(int index){
        if(index < 0 || index >= total)
            return false;
        for (int i = index; i < total - 1; i++) {
            customers[i] = customers[i+1];
        }
        customers[--total] = null;
        return true;
    }

    //改
    public boolean replaceCustomer(int index, Customer cust){
         if(index < 0 || index >= total)
             return false;
         customers[index] = cust;
         return true;
    }

    //查
    public Customer getCustomer(int index){
        if(index < 0 || index >= total)
            return null;
        return customers[index];
    }
    //获取客户列表
    public Customer[] getAllCustomers() {
        Customer[] cust = new Customer[total];
        for (int i = 0; i < total; i++) {
            cust[i] = customers[i];
        }
        return cust;
    }

    public int getTotal() {
        return total;
    }
}
