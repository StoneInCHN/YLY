package com.yly.dao.impl;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.springframework.stereotype.Repository;

import com.yly.dao.BedDao;
import com.yly.entity.Bed;
import com.yly.entity.Building;
import com.yly.entity.Room;
import com.yly.framework.dao.impl.BaseDaoImpl;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;

@Repository("bedDaoImpl")
public class BedDaoImpl extends BaseDaoImpl<Bed, Long> implements BedDao {

  @Override
  public Page<Bed> findPage(Building building, Room room, Pageable page) {
    
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Bed> criteriaQuery = criteriaBuilder.createQuery(Bed.class);
    Root<Bed> root = criteriaQuery.from(Bed.class);
    criteriaQuery.select(root);
    Predicate restrictions = criteriaBuilder.conjunction();
    if (room != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("room"), room.getId()));
    }
    if (building != null) {
      Set<Room> rooms = building.getRooms();
      if (rooms.size() > 0) {
        int i = 1;
        for (Room roomTemp : rooms) {
          if (1 == i) {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("room"), roomTemp.getId()));
          }else{
            restrictions = criteriaBuilder.or(restrictions, criteriaBuilder.equal(root.get("room"), roomTemp.getId()));
          }
          i++;
        }
      }else{
        return null;
      }
    }
    criteriaQuery.where(restrictions);
    return super.findPage(criteriaQuery, page);
  }

}
