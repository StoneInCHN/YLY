package com.yly.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.dao.ElderlyPhotoAlbumDao;
import com.yly.entity.ElderlyPhotoAlbum;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ElderlyPhotoAlbumService;
import com.yly.utils.DateTimeUtils;

/**
 * 老人相册 Service Implement
 * 
 * @author luzhang
 *
 */
@Service("elderlyPhotoAlbumServiceImpl")
public class ElderlyPhotoAlbumServiceImpl extends BaseServiceImpl<ElderlyPhotoAlbum, Long>
    implements ElderlyPhotoAlbumService {

  @Resource(name = "elderlyPhotoAlbumDaoImpl")
  private ElderlyPhotoAlbumDao elderlyPhotoAlbumDao;

  @Resource
  public void setBaseDao(ElderlyPhotoAlbumDao elderlyPhotoAlbumDao) {
    super.setBaseDao(elderlyPhotoAlbumDao);
  }

  @Override
  public Page<ElderlyPhotoAlbum> SearchPageByFilter(String keysOfElderlyName, String keysOfPhotoAlbumName, Pageable pageable) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);

    try {
      BooleanQuery query = new BooleanQuery();
      if (keysOfElderlyName != null) {
        String text = QueryParser.escape(keysOfElderlyName);
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "elderlyInfo.name", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery, Occur.MUST);
      }
      if (keysOfPhotoAlbumName != null) {
        String text = QueryParser.escape(keysOfPhotoAlbumName);
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "name", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery, Occur.MUST);
      }      

      return elderlyPhotoAlbumDao.search(query, pageable, analyzer, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
