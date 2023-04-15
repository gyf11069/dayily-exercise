package com.threadtest.www;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-07-28 10:23
 */
public class AccountThread extends Thread {
    //线程共享的账户
    private Account act;

    //通过构造方法把账户对象传递过来
    public AccountThread(Account act){
        this.act = act;
    }

    /**
     * 取款时调用的方法
     */
    public void run(){
      //取款的金额
        double money = 5000;

        act.widthBalance(money);

        System.out.println(Thread.currentThread().getName()+"对账户"
                +act.getActno()+"取款"+money+"，账户余额："+act.getBalance());
    }
}
