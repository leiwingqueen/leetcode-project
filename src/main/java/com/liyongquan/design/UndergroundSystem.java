package com.liyongquan.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UndergroundSystem {
    List<Oper> in=new ArrayList<>();
    List<Oper> out=new ArrayList<>();
    Map<Integer,Oper> map=new HashMap<>();
    Map<String,Cal> calMap=new HashMap<>();
    public UndergroundSystem() {
    }

    public void checkIn(int id, String stationName, int t) {
        Oper oper=new Oper();
        oper.id=id;
        oper.stationName=stationName;
        oper.t=t;
        map.put(id,oper);
    }

    public void checkOut(int id, String stationName, int t) {
        Oper in = map.get(id);
        String key=in.stationName+"_"+stationName;
        Cal cal=new Cal();
        cal.count=1;
        cal.time=t-in.t;
        if (calMap.containsKey(key)) {
            Cal cal1 = calMap.get(key);
            cal.time+=cal1.time;
            cal.count+=cal1.count;
        }
        calMap.put(key,cal);
    }

    public double getAverageTime(String startStation, String endStation) {
        Cal cal = calMap.get(startStation + "_" + endStation);
        if (cal==null) {
            return 0;
        }
        return (double) cal.time/cal.count;
    }

    class Cal{
        int time;
        int count;
    }

    class Oper{
        int id;
        String stationName;
        int t;
    }

    public static void main(String[] args) {
        int id=1;
        String startStation="haha";
        String endStation="haha2";
        int t1=0;
        int t2=100;
        UndergroundSystem obj = new UndergroundSystem();
        obj.checkIn(id,startStation,t1);
        obj.checkOut(id,endStation,t2);
        double param_3 = obj.getAverageTime(startStation,endStation);
    }

}
