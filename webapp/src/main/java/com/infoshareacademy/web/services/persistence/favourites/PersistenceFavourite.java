package com.infoshareacademy.web.services.persistence.favourites;

import com.infoshareacademy.web.model.analyzer.criterias.WebInvestmentRevenueCriteria;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Default
public class PersistenceFavourite implements FavouriteService {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<WebInvestmentRevenueCriteria> getAllRevenueCriteria() {
        List<WebInvestmentRevenueCriteria> list = em
                .createQuery("select m from WebInvestmentRevenueCriteria m left join fetch m.user", WebInvestmentRevenueCriteria.class)
                .getResultList();
        return list;
    }
}
