package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 文章数据访问层接口
 *
 * @author Guoty
 * @create 2019/01/23/16:26
 */
public interface ArticleSearchDao extends ElasticsearchRepository<Article, String> {
}
