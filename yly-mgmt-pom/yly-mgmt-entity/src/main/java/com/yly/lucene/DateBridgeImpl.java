package com.yly.lucene;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.lucene.document.Document;
import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.StringBridge;

public class DateBridgeImpl implements FieldBridge
{

  @Override
  public void set (String name, Object value, Document document,
      LuceneOptions luceneOptions)
  {
    if (value instanceof Date)
    {
      SimpleDateFormat sdf = new SimpleDateFormat ("yyyyMMdd");
      luceneOptions.addFieldToDocument (name, sdf.format ((Date)value), document);
    }
    
  }


}
