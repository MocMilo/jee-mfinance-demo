package com.infoshare.web.services.persistence.favourites;
import com.infoshare.web.model.analyzer.criterias.WebInvestmentRevenueCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Default
public class PersistenceFavourite implements IFavouriteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceFavourite.class);
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<WebInvestmentRevenueCriteria> getAllFavouriteRevenueCriteria(long UserId) {

        List<WebInvestmentRevenueCriteria> list = em
                .createQuery("select m from WebInvestmentRevenueCriteria m left join fetch m.user t where t.id=:Id AND m.isFavourite=true", WebInvestmentRevenueCriteria.class)
                .setParameter("Id", UserId)
                .getResultList();
        return list;
    }

    @Override
    public List<WebInvestmentRevenueCriteria> getAllRevenueCriteria() {
        List<WebInvestmentRevenueCriteria> list = em
                .createQuery("select m from WebInvestmentRevenueCriteria m left join fetch m.user", WebInvestmentRevenueCriteria.class)
                .getResultList();
        return list;
    }

}
