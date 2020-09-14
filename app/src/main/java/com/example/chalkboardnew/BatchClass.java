package com.example.chalkboardnew;

public class BatchClass {
    String batchName,numberOfDays,batchDays,batchTime,paymentPerStudent;

    public BatchClass() {
    }

    public BatchClass(String batchName, String numberOfDays, String batchDays, String batchTime, String paymentPerStudent) {
        this.batchName = batchName;
        this.numberOfDays = numberOfDays;
        this.batchDays = batchDays;
        this.batchTime = batchTime;
        this.paymentPerStudent = paymentPerStudent;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(String numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getBatchDays() {
        return batchDays;
    }

    public void setBatchDays(String batchDays) {
        this.batchDays = batchDays;
    }

    public String getBatchTime() {
        return batchTime;
    }

    public void setBatchTime(String batchTime) {
        this.batchTime = batchTime;
    }

    public String getPaymentPerStudent() {
        return paymentPerStudent;
    }

    public void setPaymentPerStudent(String paymentPerStudent) {
        this.paymentPerStudent = paymentPerStudent;
    }
}
