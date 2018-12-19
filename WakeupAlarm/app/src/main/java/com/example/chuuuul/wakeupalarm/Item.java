package com.example.chuuuul.wakeupalarm;

public class Item {

    String BUS_NODE_ID;     // 요청 노드 7자리
    //String BUS_STOP_ID;     // 요청 노드 5자리
    String EXTIME_SEC;         //남은시간 초
    String ROUTE_NO;        //버스번호
    String STATUS_POS;      //잔여 정거장

    public String getBUS_NODE_ID() {
        return BUS_NODE_ID;
    }

   // public String getBUS_STOP_ID() {
   //     return BUS_STOP_ID;
   // }

    public String getEXTIME_SEC() {
        return EXTIME_SEC;
    }

    public String getROUTE_NO() {
        return ROUTE_NO;
    }

    public String getSTATUS_POS() {
        return STATUS_POS;
    }


    public void setBUS_NODE_ID(String BUS_NODE_ID) {
        this.BUS_NODE_ID = BUS_NODE_ID;
    }

    //public void setBUS_STOP_ID(String BUS_STOP_ID) {
    //    this.BUS_STOP_ID = BUS_STOP_ID;
    //}

    public void setEXTIME_SEC(String EXTIME_SEC) {

        this.EXTIME_SEC = EXTIME_SEC;
        //this.EXTIME_SEC = Integer.parseInt(EXTIME_SEC);
    }

    public void setROUTE_NO(String ROUTE_NO) {
        this.ROUTE_NO = ROUTE_NO;
    }

    public void setSTATUS_POS(String STATUS_POS) {
        this.STATUS_POS = STATUS_POS;
    }

}

