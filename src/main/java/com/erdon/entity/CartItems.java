package com.erdon.entity;

import java.math.BigDecimal;

public class CartItems {
    private Integer gId;
    private String gName;
    private Integer gNumber;
    private BigDecimal gPrice;
    private BigDecimal gTotalPrice;

    public CartItems() {
    }

    public CartItems(Integer gId, String gName, Integer gNumber, BigDecimal gPrice, BigDecimal gTotalPrice) {
        this.gId = gId;
        this.gName = gName;
        this.gNumber = gNumber;
        this.gPrice = gPrice;
        this.gTotalPrice = gTotalPrice;
    }


    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public Integer getgNumber() {
        return gNumber;
    }

    public void setgNumber(Integer gNumber) {
        this.gNumber = gNumber;
    }

    public BigDecimal getgPrice() {
        return gPrice;
    }

    public void setgPrice(BigDecimal gPrice) {
        this.gPrice = gPrice;
    }

    public BigDecimal getgTotalPrice() {
        return gTotalPrice;
    }

    public void setgTotalPrice(BigDecimal gTotalPrice) {
        this.gTotalPrice = gTotalPrice;
    }

    @Override
    public String toString() {
        return "CartItems{" +
                "gId=" + gId +
                ", gName='" + gName + '\'' +
                ", gNumber=" + gNumber +
                ", gPrice=" + gPrice +
                ", gTotalPrice=" + gTotalPrice +
                '}';
    }
}
