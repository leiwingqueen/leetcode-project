package com.liyongquan.recursion;

/**
 * 汉诺塔
 */
public class HanoTower {
    /**
     * 假设盘子的序号为1~n，第i+1大于第i个盘子
     * @param n
     */
    public void move(int n){
        move1(n,'A','C','B');
    }

    private void move1(int n,char from,char to,char middle){
        if (n==1) {
            System.out.println(String.format("move %s from %s to %s",1,from,to));
            return;
        }
        move1(n-1,from,middle,to);
        System.out.println(String.format("move %s from %s to %s",n,from,to));
        move1(n-1,middle,to,from);
    }

    public static void main(String[] args) {
        HanoTower hanoTower=new HanoTower();
        hanoTower.move(8);
    }
}
