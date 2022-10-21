package com.psl.banking.user.FeignClients;


public class ChequeProxy {
    private long chequeId;
    private String bankName;
    private  String ifscCode;
    private  String micrCode;
    private String  chequeNumber;
    private double amount;
    private String date;
    private  boolean isChequeValid;
    private  boolean isChequeEnhanced;

    public ChequeProxy() {
    }

    public long getChequeId() {
        return chequeId;
    }

    public void setChequeId(long chequeId) {
        this.chequeId = chequeId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getMicrCode() {
        return micrCode;
    }

    public void setMicrCode(String micrCode) {
        this.micrCode = micrCode;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isChequeValid() {
        return isChequeValid;
    }

    public void setChequeValid(boolean chequeValid) {
        isChequeValid = chequeValid;
    }

    public boolean isChequeEnhanced() {
        return isChequeEnhanced;
    }

    public void setChequeEnhanced(boolean chequeEnhanced) {
        isChequeEnhanced = chequeEnhanced;
    }

    public ChequeProxy(long chequeId, String bankName, String ifscCode, String micrCode, String chequeNumber, double amount, String date, boolean isChequeValid, boolean isChequeEnhanced) {
        this.chequeId = chequeId;
        this.bankName = bankName;
        this.ifscCode = ifscCode;
        this.micrCode = micrCode;
        this.chequeNumber = chequeNumber;
        this.amount = amount;
        this.date = date;
        this.isChequeValid = isChequeValid;
        this.isChequeEnhanced = isChequeEnhanced;
    }
}
