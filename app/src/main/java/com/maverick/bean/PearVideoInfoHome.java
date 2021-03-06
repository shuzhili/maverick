package com.maverick.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by limingfei on 2017/10/26.
 */
public class PearVideoInfoHome implements Serializable {

    private String resultCode;
    private String resultMsg;
    private String reqId;
    private String systemTime;
    private List<PearVideoInfoHomeArea> areaList;
    private List<PearVideoInfoHomeData> dataList;


    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(String systemTime) {
        this.systemTime = systemTime;
    }

    public List<PearVideoInfoHomeArea> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<PearVideoInfoHomeArea> areaList) {
        this.areaList = areaList;
    }

    public List<PearVideoInfoHomeData> getDataList() {
        return dataList;
    }

    public void setDataList(List<PearVideoInfoHomeData> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "PearVideoInfoHome{" +
                "resultCode='" + resultCode + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                ", reqId='" + reqId + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", areaList=" + areaList +
                ", dataList=" + dataList +
                '}';
    }
}
