package com.company.campaign.service;

import com.company.campaign.entity.BannerPosition;

public class PositionImpressions {

    public PositionImpressions(Long impressions,BannerPosition bannerPosition){
        this.impressions = impressions;
        this.bannerPosition = bannerPosition;
    }
    public PositionImpressions(){
        this(new Long(0),null);
    }
    private Long impressions;

    public BannerPosition getBannerPosition() {
        return bannerPosition;
    }

    public void setBannerPosition(BannerPosition bannerPosition) {
        this.bannerPosition = bannerPosition;
    }

    private BannerPosition bannerPosition;

    public Long getImpressions() {
        return impressions;
    }

    public void setImpressions(Long impressions) {
        this.impressions = impressions;
    }

    @Override
    public String toString() {
        return "Banner Position Name: " +
                bannerPosition.getName() + ",  Banner Postion Impressions: "  + impressions ;
    }
}
