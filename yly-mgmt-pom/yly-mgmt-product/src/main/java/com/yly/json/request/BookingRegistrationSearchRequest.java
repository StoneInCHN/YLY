package com.yly.json.request;

import java.util.Date;

public class BookingRegistrationSearchRequest {
  /**
   * 预约人（隐藏域）
   */
  private String peopleWhoBookedHidden;
  /**
   * 老人姓名（隐藏域）
   */
  private String elderlyNameHidden;
  /**
   * 意向房型的值（隐藏域）
   */
  private String searchRoomTypeValueHidden;
  /**
   * 预约入住时间, 开始（隐藏域）
   */
  private Date bookingCheckInBeginDateHidden;
  /**
   * 预约入住时间, 结束（隐藏域）
   */
  private Date bookingCheckInEndDateHidden;
  public String getPeopleWhoBookedHidden() {
    return peopleWhoBookedHidden;
  }
  public void setPeopleWhoBookedHidden(String peopleWhoBookedHidden) {
    this.peopleWhoBookedHidden = peopleWhoBookedHidden;
  }
  public String getElderlyNameHidden() {
    return elderlyNameHidden;
  }
  public void setElderlyNameHidden(String elderlyNameHidden) {
    this.elderlyNameHidden = elderlyNameHidden;
  }
  public String getSearchRoomTypeValueHidden() {
    return searchRoomTypeValueHidden;
  }
  public void setSearchRoomTypeValueHidden(String searchRoomTypeValueHidden) {
    this.searchRoomTypeValueHidden = searchRoomTypeValueHidden;
  }
  public Date getBookingCheckInBeginDateHidden() {
    return bookingCheckInBeginDateHidden;
  }
  public void setBookingCheckInBeginDateHidden(Date bookingCheckInBeginDateHidden) {
    this.bookingCheckInBeginDateHidden = bookingCheckInBeginDateHidden;
  }
  public Date getBookingCheckInEndDateHidden() {
    return bookingCheckInEndDateHidden;
  }
  public void setBookingCheckInEndDateHidden(Date bookingCheckInEndDateHidden) {
    this.bookingCheckInEndDateHidden = bookingCheckInEndDateHidden;
  }
  
  
}
