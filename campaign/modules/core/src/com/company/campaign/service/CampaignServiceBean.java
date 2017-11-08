package com.company.campaign.service;




import com.company.campaign.entity.BannerPosition;
import com.company.campaign.entity.CampaignBannerPosition;
import com.haulmont.cuba.core.*;
import com.haulmont.cuba.core.global.DatatypeFormatter;
import org.springframework.stereotype.Service;


import javax.inject.Inject;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service(CampaignService.NAME)
public class CampaignServiceBean implements CampaignService {

    @Inject
    private DatatypeFormatter formatter;

    @Inject
    private Persistence persistence;


    @Override
    public List<Occupacy> generateWebSiteOccupacy(Date start, Date end,String website) {
        Calendar c = Calendar.getInstance();
        c.setTime(start);
        c.add(Calendar.DATE,1);
        List<Occupacy> occupacyList =null;
        Occupacy currentOccupacy= null;
        while(start.before(end)){
            int occupdacyDays =0;
            currentOccupacy = new Occupacy();
            List<CampaignBannerPosition> campaignBannerPositionList = getCampaignBannerPositionList( start,website);
            if(campaignBannerPositionList !=null && campaignBannerPositionList.size()>0){

            }else{
                occupacyList = new ArrayList<>();
               for(int i=0; i<occupdacyDays;i++){
                   Occupacy occupacy= new Occupacy();

                   occupacyList.add(new Occupacy());
               }
            }
        }
        return occupacyList;
    }
    private List<CampaignBannerPosition> getCampaignBannerPositionList(Date startDate, String website){
        List<CampaignBannerPosition> campaignBannersList =null;
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager entityManager = persistence.getEntityManager();
            TypedQuery<CampaignBannerPosition> newQuery = entityManager.
                    createQuery("select p from campaign$CampaignBannerPosition p where p.START_DATE >=:startDate AND p.bannerPosition.website=:website",CampaignBannerPosition.class);
            newQuery.setParameter("startDate", startDate);
            newQuery.setParameter("website", website);
            campaignBannersList = newQuery.getResultList();
            tx.commit();
        }
        return campaignBannersList;
    }
    private List<BannerPosition> getWebsiteBannerList(String websiteName){
        List<BannerPosition> websiteBannerList =null;
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager entityManager = persistence.getEntityManager();
            TypedQuery<BannerPosition> newQuery = entityManager.
                    createQuery("select p from campaign$BannerPosition p where p.website=:websiteN",BannerPosition.class);
            newQuery.setParameter("websiteN", websiteName);
            websiteBannerList = newQuery.getResultList();
            tx.commit();
        }
        return websiteBannerList;
    }
    // apart from business logic
    @Override
    public List<BannerPosition> getBannerPositions(){
        List<BannerPosition> websiteBannerList =null;
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager entityManager = persistence.getEntityManager();
            TypedQuery<BannerPosition> newQuery = entityManager.
                    createQuery("select p from campaign$BannerPosition p",BannerPosition.class);
            websiteBannerList = newQuery.getResultList();
            tx.commit();
        }
        return websiteBannerList;
    }

}