package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleSearchDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 文章业务逻辑层
 *
 * @author Guoty
 * @create 2019/01/23  16:27
 */
@Service
public class ArticleSearchService {
    @Autowired
    private ArticleSearchDao articleSearchDao;

    /**
     * 增加文章
     *
     * @param article
     */
    public void add(Article article) {
        articleSearchDao.save(article);
    }
}
