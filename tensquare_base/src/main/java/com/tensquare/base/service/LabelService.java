package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 标签业务逻辑
 *
 * @author Zero
 */
@Service
public class LabelService {
    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部标签
     *
     * @return
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    public Label findById(String id) {
        Label label = labelDao.findById(id).get();
        if (label == null) {
            System.out.println("没有该条数据！");
        }
        return label;
    }

    /**
     * 增加标签
     *
     * @param label
     */
    public void add(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }


    /**
     * 更新标签
     *
     * @param label
     */
    public void update(Label label) {
        labelDao.save(label);
    }


    /**
     * 删除标签
     *
     * @param id
     */
    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //创建一个list集合，来存放所有的查询条件
                List<Predicate> predicateList = new ArrayList<>();
                if (!StringUtils.isEmpty(label.getLabelname())) {
                    Predicate labelname = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    predicateList.add(labelname);
                }

                if (!StringUtils.isEmpty(label.getState())) {
                    Predicate labelState = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                    predicateList.add(labelState);
                }

                Predicate[] predicates = new Predicate[predicateList.size()];

                predicates = predicateList.toArray(predicates);
                return criteriaBuilder.and(predicates);
            }
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //创建一个list集合，来存放所有的查询条件
                List<Predicate> predicateList = new ArrayList<>();
                if (!StringUtils.isEmpty(label.getLabelname())) {
                    Predicate labelname = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    predicateList.add(labelname);
                }

                if (!StringUtils.isEmpty(label.getState())) {
                    Predicate labelState = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                    predicateList.add(labelState);
                }

                Predicate[] predicates = new Predicate[predicateList.size()];

                predicates = predicateList.toArray(predicates);
                return criteriaBuilder.and(predicates);
            }
        }, pageable);
    }
}
