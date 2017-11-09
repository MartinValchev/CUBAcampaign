package com.company.campaign.service;

import java.util.Date;
import java.util.List;

public class Occupacy {
    private Date date;
    private List<PositionImpressions> positionImpressionsList;


    public List<PositionImpressions> getPositionImpressionsList() {
        return positionImpressionsList;
    }

    public void setPositionImpressionsList(List<PositionImpressions> positionImpressionsList) {
        this.positionImpressionsList = positionImpressionsList;
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    @Override
    public String toString(){
        String positionsRecord= "";
        for(PositionImpressions positionInmpression:positionImpressionsList){
            positionsRecord += positionInmpression.toString() + "\n";
        }
        return "Date: " + date.toString() + " , " +positionsRecord;
    }

   }
