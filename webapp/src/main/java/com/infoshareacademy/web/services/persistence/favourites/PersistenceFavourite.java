package com.infoshareacademy.web.services.persistence.favourites;

import com.infoshareacademy.web.model.analyzer.criterias.WebInvestmentRevenueCriteria;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Default
public class PersistenceFavourite implements IFavouriteService {
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
