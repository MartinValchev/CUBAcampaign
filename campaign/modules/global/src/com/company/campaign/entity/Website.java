package com.company.campaign.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.cuba.core.entity.StandardEntity;
import java.util.List;
import javax.persistence.OneToMany;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s %s|name,version")
@Table(name = "CAMPAIGN_WEBSITE")
@Entity(name = "campaign$Website")
public class Website extends StandardEntity {
    private static final long serialVersionUID = -5070245308848172332L;

    @Column(name = "NAME", nullable = false, length = 50)
    protected String name;

    @Column(name = "WEB_VERSION")
    protected Integer webVersion;

    @Column(name = "IMPRESSIONS_LIMIT", nullable = false)
    protected Long impressionsLimit;

    @OneToMany(mappedBy = "website")
    protected List<BannerPosition> bannerPosition;

    public void setImpressionsLimit(Long impressionsLimit) {
        this.impressionsLimit = impressionsLimit;
    }

    public Long getImpressionsLimit() {
        return impressionsLimit;
    }

    public void setBannerPosition(List<BannerPosition> bannerPosition) {
        this.bannerPosition = bannerPosition;
    }

    public List<BannerPosition> getBannerPosition() {
        return bannerPosition;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setWebVersion(WebVersion webVersion) {
        this.webVersion = webVersion == null ? null : webVersion.getId();
    }

    public WebVersion getWebVersion() {
        return webVersion == null ? null : WebVersion.fromId(webVersion);
    }


}