package com.yly.service;


/**
 * 换房
 * 
 * @author shijun
 *
 */
public interface ChangeRoomService {

  /**
   * 换房
   * 
   * @param elderlyInfoId
   * @param originalBedId
   * @param newBedId
   */
  public Boolean changeRoom(Long elderlyInfoId, Long originalBedId, Long newBedId);

}
