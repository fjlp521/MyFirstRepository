/**
 * 多线程相关操作，以及经典生产者-消费者问题
 */
public class ThreadTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer p1 = new Producer(clerk);
        Consumer c1 = new Consumer(clerk);
        Consumer c2 = new Consumer(clerk);
        p1.setName("生产者1");
        c1.setName("消费者1");
        c2.setName("消费者2");
        p1.start();
        c1.start();
        c2.start();

    }
}
//店员（被共享的数据，单独拎出来做成一个类，并将对共享数据的操作内嵌之中形成方法）
class Clerk{
    private int productCount = 0;

    public synchronized void produceProduct() {
        if(productCount < 20){
            productCount++;
            System.out.println(Thread.currentThread().getName() + "生产第" + productCount +"个产品");
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void resumeProduct() {
        if(productCount > 0){
            System.out.println(Thread.currentThread().getName() + "消费第" + productCount + "个产品");
            productCount--;
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
//生产者
class Producer extends Thread{
    private Clerk clerk;
    Producer(Clerk clerk){
        this.clerk = clerk;
    }
    @Override
    public void run() {
        System.out.println(getName() + "开始生产产品。。。");
        while(true){
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceProduct();
        }
    }
}
//消费者
class Consumer extends Thread{
    private Clerk clerk;
    Consumer(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName() + "开始消费产品。。。");
        while (true){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.resumeProduct();
        }
    }
}