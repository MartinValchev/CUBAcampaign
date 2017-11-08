package com.company.campaign.service;


import com.company.campaign.entity.BannerPosition;

import java.util.Date;
import java.util.List;

public interface CampaignService {
    String NAME = "campaign_CampaignService";

    public List<Occupacy> generateWebSiteOccupacy(Date start, Date end,String website);

    public List<BannerPosition> getBannerPositions();
}