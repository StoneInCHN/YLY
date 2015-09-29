package com.yly.json.request;

public class DrugsInfoSearchRequest
{

  private String keyword;
  private String startDate;
  private String endDate;
  public String getKeyword ()
  {
    return keyword;
  }
  public void setKeyword (String keyword)
  {
    this.keyword = keyword;
  }
  public String getStartDate ()
  {
    return startDate;
  }
  public void setStartDate (String startDate)
  {
    this.startDate = startDate;
  }
  public String getEndDate ()
  {
    return endDate;
  }
  public void setEndDate (String endDate)
  {
    this.endDate = endDate;
  }
  
  
}
