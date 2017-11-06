package com.company.campaign.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.util.List;
import javax.persistence.OneToMany;

@NamePattern("%s|name")
@Table(name = "CAMPAIGN_CAMPAIGN")
@Entity(name = "campaign$Campaign")
public class Campaign extends StandardEntity {
    private static final long serialVersionUID = -2166544415207200912L;

    @Column(name = "NAME", nullable = false, length = 50)
    protected String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE", nullable = false)
    protected Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE", nullable = false)
    protected Date endDate;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "campaign")
    protected List<CampaignBannerPosition> campaignPosition;

    public void setCampaignPosition(List<CampaignBannerPosition> campaignPosition) {
        this.campaignPosition = campaignPosition;
    }

    public List<CampaignBannerPosition> getCampaignPosition() {
        return campaignPosition;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

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


}