package com.company.campaign.service;

import java.math.BigDecimal;
import java.util.Date;

public class Occupacy {
    private Date date;
    private BigDecimal occupacyPercent;

    public Date getDate(){
        return this.date;
    }
    public BigDecimal getOccupacyPercent(){
        return this.occupacyPercent;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public void  setOccupacyPercent(BigDecimal occupacyPercent){
        this.occupacyPercent = occupacyPercent;
    }

    @Override
    public String toString() {
        return "Occupacy{" +
                "date=" + date +
                ", occupacyPercent=" + occupacyPercent +
                '}';
    }
}
