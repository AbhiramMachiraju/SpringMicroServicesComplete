package com.abhiram.inventoryservice.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abhiram.inventoryservice.model.InventoryTO;
import com.abhiram.inventoryservice.repository.CustomInventoryRepo;

@Repository
@SuppressWarnings("deprecation")
public class InventoryRepoImpl implements CustomInventoryRepo {

	@Autowired
	private Session session;

	public List<InventoryTO> findBySkuCodeIn(List<String> skuCode) {

		List<InventoryTO> list = new ArrayList<InventoryTO>();

		for (String s : skuCode) {
			InventoryTO obj = null;
			System.out.println(s);
			// obj = (InventoryTO)
			// session.createCriteria(InventoryTO.class).add(Restrictions.eq("skuCode",
			// s)).setMaxResults(1).uniqueResult();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<InventoryTO> cq = cb.createQuery(InventoryTO.class);
			Root<InventoryTO> root = cq.from(InventoryTO.class);
			cq.select(root);
			cq.where(cb.equal(root.get("skuCode"),s.toString()));
			Query<InventoryTO> query = session.createQuery(cq).setMaxResults(1);
			obj = query.uniqueResult();

			if (obj != null)
				list.add(obj);
		}

		return list;
	}

}
