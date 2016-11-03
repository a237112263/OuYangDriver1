package com.keyi.ouyang1.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/16.
 */
public class DetailData1 implements Serializable{
    private static final long serialVersionUID = -564642316L;

    private boolean IsOK;
    private Object ErrMsg;


    private List<DataBean> Data;

    public boolean isIsOK() {
        return IsOK;
    }

    public void setIsOK(boolean IsOK) {
        this.IsOK = IsOK;
    }

    public Object getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(Object ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable{
        private static final long serialVersionUID = -4644234L;
        private String Arrival;
        private String CheckNo;
        private String Departure;
        private String Deliveryway;
        private double Freight;
        private String FreightType;
        private String LogisBatchNo;
        private String Distributor;
        private String Shipper;
        private String ReceiverName;
        private String ReceiverMobile;
        private double VolumeSum;
        private int OrderNum;
        private int OrderPackcount;
        private String CarNo;
        private double Carcost;
        private int ConsignmentNum;
        private String SendDate;
        private String DriverMobile;
        private String SendDateString;
        private int unloading;

        public String getArrival() {
            return Arrival;
        }

        public void setArrival(String Arrival) {
            this.Arrival = Arrival;
        }

        public String getCheckNo() {
            return CheckNo;
        }

        public void setCheckNo(String CheckNo) {
            this.CheckNo = CheckNo;
        }

        public String getDeparture() {
            return Departure;
        }

        public void setDeparture(String Departure) {
            this.Departure = Departure;
        }

        public String getDeliveryway() {
            return Deliveryway;
        }

        public void setDeliveryway(String Deliveryway) {
            this.Deliveryway = Deliveryway;
        }

        public double getFreight() {
            return Freight;
        }

        public void setFreight(double Freight) {
            this.Freight = Freight;
        }

        public String getFreightType() {
            return FreightType;
        }

        public void setFreightType(String FreightType) {
            this.FreightType = FreightType;
        }

        public String getLogisBatchNo() {
            return LogisBatchNo;
        }

        public void setLogisBatchNo(String LogisBatchNo) {
            this.LogisBatchNo = LogisBatchNo;
        }

        public String getDistributor() {
            return Distributor;
        }

        public void setDistributor(String Distributor) {
            this.Distributor = Distributor;
        }

        public String getShipper() {
            return Shipper;
        }

        public void setShipper(String Shipper) {
            this.Shipper = Shipper;
        }

        public String getReceiverName() {
            return ReceiverName;
        }

        public void setReceiverName(String ReceiverName) {
            this.ReceiverName = ReceiverName;
        }

        public String getReceiverMobile() {
            return ReceiverMobile;
        }

        public void setReceiverMobile(String ReceiverMobile) {
            this.ReceiverMobile = ReceiverMobile;
        }

        public double getVolumeSum() {
            return VolumeSum;
        }

        public void setVolumeSum(double VolumeSum) {
            this.VolumeSum = VolumeSum;
        }

        public int getOrderNum() {
            return OrderNum;
        }

        public void setOrderNum(int OrderNum) {
            this.OrderNum = OrderNum;
        }

        public int getOrderPackcount() {
            return OrderPackcount;
        }

        public void setOrderPackcount(int OrderPackcount) {
            this.OrderPackcount = OrderPackcount;
        }

        public String getCarNo() {
            return CarNo;
        }

        public void setCarNo(String CarNo) {
            this.CarNo = CarNo;
        }

        public double getCarcost() {
            return Carcost;
        }

        public void setCarcost(double Carcost) {
            this.Carcost = Carcost;
        }

        public int getConsignmentNum() {
            return ConsignmentNum;
        }

        public void setConsignmentNum(int ConsignmentNum) {
            this.ConsignmentNum = ConsignmentNum;
        }

        public String getSendDate() {
            return SendDate;
        }

        public void setSendDate(String SendDate) {
            this.SendDate = SendDate;
        }

        public String getDriverMobile() {
            return DriverMobile;
        }

        public void setDriverMobile(String DriverMobile) {
            this.DriverMobile = DriverMobile;
        }

        public String getSendDateString() {
            return SendDateString;
        }

        public void setSendDateString(String SendDateString) {
            this.SendDateString = SendDateString;
        }

        public int getUnloading() {
            return unloading;
        }

        public void setUnloading(int unloading) {
            this.unloading = unloading;
        }
    }
}
