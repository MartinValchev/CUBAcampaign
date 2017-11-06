package com.company.campaign.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.cuba.core.entity.StandardEntity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name = "CAMPAIGN_CAMPAIGN_BANNER_POSITION")
@Entity(name = "campaign$CampaignBannerPosition")
public class CampaignBannerPosition extends StandardEntity {
    private static final long serialVersionUID = -8042862710806412528L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CAMPAIGN_ID")
    protected Campaign campaign;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BANNER_POSITION_ID")
    protected BannerPosition bannerPosition;

    @Column(name = "IMPRESSIONS", nullable = false)
    protected Long impressions;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE", nullable = false)
    protected Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE", nullable = false)
    protected Date endDate;

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }


    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setBannerPosition(BannerPosition bannerPosition) {
        this.bannerPosition = bannerPosition;
    }

    public BannerPosition getBannerPosition() {
        return bannerPosition;
    }

    public void setImpressions(Long impressions) {
        this.impressions = impressions;
    }

    public Long getImpressions() {
        return impressions;
    }


}