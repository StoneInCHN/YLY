package com.yly.json.response;

import java.util.List;

public class ReportDataResopnse
{
  private String name;
  private List<String> data;
  
  public String getName ()
  {
    return name;
  }
  public void setName (String name)
  {
    this.name = name;
  }
  public List<String> getData ()
  {
    return data;
  }
  public void setData (List<String> data)
  {
    this.data = data;
  }
  
  
}
