package com.company.campaign.service;




import com.company.campaign.entity.BannerPosition;
import com.company.campaign.entity.CampaignBannerPosition;
import com.haulmont.cuba.core.*;
import com.haulmont.cuba.core.global.DatatypeFormatter;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Service;


import javax.inject.Inject;
import javax.persistence.TemporalType;
import java.util.*;

@Service(CampaignService.NAME)
public class CampaignServiceBean implements CampaignService {

    @Inject
    private Persistence persistence;

    @Inject
    private Metadata meta;
    // list of all banner positions found in campaingnBannerPosition
    private List<BannerPosition> bannerPositionsList = new ArrayList<>();
    /*
    Business Logic
    1.Take from BannerPosition table all records that have the same website name as the one requested
    2.Take form CampaingBannerPosition table all records that have start date between the requested start and end date
      and has property bannerPosition that has website name equal to the one requested.
    3. start iterate over the Date period from start to end. Foreach day generate Occupacy and add it to the
    occupacy List as follows:
     3.1 Foreach BannerPostion record check if there is corresponding item in CampaingBannerPosition list
     which has end date after or equal the new start date(incremented by One day)
     if we have items in CampaingBannerPosition list create new Occupacy with BannerPostion record and impressions
     calculated as result on CampaingBannerPosition list impressions.
     if we have no items create a new Occupacy object with the current BannerPostion record and 0 as impressions value.
     */

    @Override
    public List<Occupacy> generateWebSiteOccupacy(Date start, Date end,String website) {
        Calendar calendar = Calendar.getInstance();

        List<Occupacy> occupacyList = new ArrayList<>();
        Occupacy currentOccupacy= null;
        Long tempImpressions=null;
        List<BannerPosition> bannerPositions =getWebsiteBannerList(website);
        List<CampaignBannerPosition> campaignBannerPositionList = getCampaignBannerPositionList(start,end, website);
        List<PositionImpressions> positionImpressionsList = null;
        while(start.before(end)||start.equals(end)){
            currentOccupacy = new Occupacy();
            currentOccupacy.setDate(start);
            positionImpressionsList = new ArrayList<>();

            for(BannerPosition bannerPosition: bannerPositions){
                // filter campaign banner position list by banner position
                campaignBannerPositionList = filterByCampaignPosition(campaignBannerPositionList,bannerPosition);
                PositionImpressions currentPositionImpressions= new PositionImpressions();
                currentPositionImpressions.setBannerPosition(bannerPosition);
                currentPositionImpressions.setImpressions(new Long(0));
                tempImpressions = new Long(0);
                if(campaignBannerPositionList !=null && campaignBannerPositionList.size()>0){
                   for(CampaignBannerPosition item:campaignBannerPositionList){
                       // check if CampaignBannerPosition is still active on website banner position
                       if(item.getEndDate().after(start) ||item.getEndDate().equals(start)) {
                           tempImpressions += item.getDailyImpressions();
                       }
                   }

                }
                currentPositionImpressions.setImpressions(tempImpressions);
                positionImpressionsList.add(currentPositionImpressions);

            }
            currentOccupacy.setPositionImpressionsList(positionImpressionsList);
            occupacyList.add(currentOccupacy);
            // increment the day value
            calendar.setTime(start);
            calendar.add(Calendar.DATE,1);
            start = calendar.getTime();
        }
        return occupacyList;
    }
    private List<CampaignBannerPosition> getCampaignBannerPositionList(Date startDate,Date endDate, String website){
        List<CampaignBannerPosition> campaignBannersList =null;
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager entityManager = persistence.getEntityManager();
            Query newQuery = entityManager.
                    createQuery("select p from campaign$CampaignBannerPosition p where p.startDate BETWEEN :start AND :end AND p.bannerPosition.website.name=:websiteName",
                            CampaignBannerPosition.class);
            newQuery.setParameter("start", startDate, TemporalType.TIMESTAMP);
            newQuery.setParameter("end", endDate, TemporalType.TIMESTAMP);
           newQuery.setParameter("websiteName", website);
            campaignBannersList = newQuery.getResultList();
            for(CampaignBannerPosition position: campaignBannersList){
                BannerPosition bannerPos = position.getBannerPosition();
                bannerPositionsList.add(bannerPos);
        }
            tx.commit();
        }
        return campaignBannersList;
    }
    private List<BannerPosition> getWebsiteBannerList(String websiteName){
        List<BannerPosition> websiteBannerList =null;

        try (Transaction tx = persistence.createTransaction()) {
            EntityManager entityManager = persistence.getEntityManager();
            Query newQuery = entityManager.
                    createQuery("select p from campaign$BannerPosition p where p.website.name=:websiteN",BannerPosition.class);
            newQuery.setParameter("websiteN", websiteName);
            websiteBannerList = newQuery.getResultList();
            tx.commit();
        }
        return websiteBannerList;
    }
    public List<CampaignBannerPosition> filterByCampaignPosition(List<CampaignBannerPosition> rawPositionsList,BannerPosition bannerPosition){
        List<CampaignBannerPosition> filteredList = new ArrayList<>();
        int counter =0;
            for(CampaignBannerPosition item: rawPositionsList){
                BannerPosition current =bannerPositionsList.get(counter);
                counter++;
                if(current.equals(bannerPosition)){
                    filteredList.add(item);
                }
            }
        return filteredList;
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

    @Override
    public String sayHello(String wordParamA,String wordParamB) {
        return "Hello this is a test  message" + wordParamA +wordParamB;
    }


}