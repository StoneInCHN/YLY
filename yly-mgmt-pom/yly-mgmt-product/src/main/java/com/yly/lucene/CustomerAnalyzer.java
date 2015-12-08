package com.yly.lucene;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.WhitespaceTokenizer;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKTokenizer;

import com.ibm.icu.text.Transliterator;

public class CustomerAnalyzer extends Analyzer
{

  @Override
  public TokenStream tokenStream (String fieldName, Reader reader)
  {
    return icuTokenStream (reader);
  }

  public  TokenStream  icuTokenStream (Reader reader) 
  {
//    Transliterator NAME_PINYIN_TRANSLITERATOR = Transliterator
//      .getInstance("Han-Latin;NFD;[[:NonspacingMark:][:Space:]] Remove");
    
    Transliterator NAME_PINYIN_TRANSLITERATOR = Transliterator.createFromRules(
        "Han-Latin;",
        ":: Han-Latin;[[:any:]-[[:space:][\uFFFF]]] { [[:any:]-[:white_space:]] >;::Null;[[:NonspacingMark:][:Space:]]>;",
        Transliterator.FORWARD);
    
//    Transliterator NAME_PINYIN_TRANSLITERATOR = 
//        Transliterator.createFromRules(null, ":: Han-Latin/Names;[[:space:]][bpmfdtnlgkhjqxzcsryw] { [[:any:]-[:white_space:]] >;::NFD;[[:NonspacingMark:][:Space:]]>;",Transliterator.FORWARD);
   
    TokenStream ts=new WhitespaceTokenizer(Version.LUCENE_35,reader);
    
    TokenStream result = new LowerCaseFilter(Version.LUCENE_35,ts);
//    TokenStream result=new IKTokenizer (reader, true);
       
    result = new PimEdgeNGramTokenFilter(result,
        PimEdgeNGramTokenFilter.Side.BACK,
            2,
            20);
    result = new PimEdgeNGramTokenFilter(result,
        PimEdgeNGramTokenFilter.Side.FRONT,
            2,
            20);
//    result = new org.apache.lucene.analysis.icu.ICUTransformFilter(result, NAME_PINYIN_TRANSLITERATOR);
    return result ;
  }
  
  public  TokenStream  icuTokenStream3 (Reader reader) 
  {
//    Transliterator NAME_PINYIN_TRANSLITERATOR = Transliterator
//      .getInstance("Han-Latin;NFD;[[:NonspacingMark:][:Space:]] Remove");
    
    Transliterator NAME_PINYIN_TRANSLITERATOR = Transliterator.createFromRules(
        "Han-Latin;",
        ":: Han-Latin;[[:any:]-[[:space:][\uFFFF]]] { [[:any:]-[:white_space:]] >;::Null;[[:NonspacingMark:][:Space:]]>;",
        Transliterator.FORWARD);
    
    Transliterator NAME_PINYIN_TRANSLITERATOR2 = 
        Transliterator.createFromRules(null, ":: Han-Latin/Names;[[:space:]][bpmfdtnlgkhjqxzcsryw] { [[:any:]-[:white_space:]] >;::NFD;[[:NonspacingMark:][:Space:]]>;",Transliterator.FORWARD);
   
//    TokenStream ts=new WhitespaceTokenizer(Version.LUCENE_35,reader);
    
//    TokenStream result = new LowerCaseFilter(Version.LUCENE_35,ts);
    TokenStream result=new IKTokenizer (reader, true);
       
    result = new PimEdgeNGramTokenFilter(result,
        PimEdgeNGramTokenFilter.Side.BACK,
            2,
            20);
    result = new PimEdgeNGramTokenFilter(result,
        PimEdgeNGramTokenFilter.Side.FRONT,
            2,
            20);
//    result = new org.apache.lucene.analysis.icu.ICUTransformFilter(result, NAME_PINYIN_TRANSLITERATOR);
    return result ;
  }
}
