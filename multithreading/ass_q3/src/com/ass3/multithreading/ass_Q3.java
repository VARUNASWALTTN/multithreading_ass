package com.ass3.multithreading;

class Table {
    void printTable(int n){
        synchronized(this){//synchronized block
            for(int i=1;i<=5;i++){
                System.out.println(n*i);
                try{
                    Thread.sleep(400);
                }catch(Exception e){System.out.println(e);}
            }
        }
    }
}

class MyThread1 extends Thread{
    Table t;
    MyThread1(Table t){
        this.t=t;
    }
    public void run(){
        System.out.println("Synchronisation Block 1");
        t.printTable(1);
    }

}
class MyThread2 extends Thread{
    Table t;
    MyThread2(Table t){
        this.t=t;
    }
    public void run(){
        System.out.println("Synchronisation Block 2");
        t.printTable(1);
    }
}

public class ass_Q3 {
    public static void main(String args[]) throws InterruptedException {
        Table obj = new Table();//only one object
        MyThread1 t1=new MyThread1(obj);
        MyThread2 t2=new MyThread2(obj);
        t1.start();
        t1.join();
        t2.start();
        t2.join();

        //final Table obj = new Table();//only one object

        Thread t3=new Thread(){
            public void run(){
                System.out.println("synchronisation method 1");
                obj.printTable(1);
            }
        };
        Thread t4=new Thread(){
            public void run(){
                System.out.println("synchronisation method 2");
                obj.printTable(1);
            }
        };

        t3.start();
        t3.join();
        t4.start();
        t4.join();
    }
}