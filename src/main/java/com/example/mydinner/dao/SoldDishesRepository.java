package com.example.mydinner.dao;

import com.example.mydinner.entity.Result;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class SoldDishesRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Result> findAll(Timestamp from, Timestamp to) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Result> theQuery = currentSession.createQuery("SELECT new com.example.mydinner.entity.Result(SUM(od.quantity), d.cuisine) FROM OrderDetail od INNER JOIN od.order o INNER JOIN od.dish d WHERE o.orderedAt >= :from and o.orderedAt <= :to GROUP BY d.cuisine", Result.class)
                .setParameter("from", from)
                .setParameter("to", to);

        return theQuery.list();
    }
}
