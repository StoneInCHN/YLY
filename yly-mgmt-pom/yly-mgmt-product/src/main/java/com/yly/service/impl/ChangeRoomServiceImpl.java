package com.yly.service.impl;

import java.util.Date;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.yly.entity.Bed;
import com.yly.entity.BedChangeRecord;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.commonenum.CommonEnum.UsageState;
import com.yly.service.BedChangeRecordService;
import com.yly.service.BedService;
import com.yly.service.ChangeRoomService;
import com.yly.service.ElderlyInfoService;

/**
 * 换房
 * 
 * @author shijun
 *
 */
public class ChangeRoomServiceImpl implements ChangeRoomService {

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;

  @Resource(name = "bedServiceImpl")
  private BedService bedService;

  @Resource(name = "bedChangeRecordServiceImpl")
  private BedChangeRecordService bedChangeRecordService;

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void changeRoom(Long elderlyInfoId, Long originalBedId, Long newBedId) {

    /**
     * 查询老人信息,现有床位和更换的床位
     */
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoId);

    Bed originalBed = bedService.find(originalBedId);

    Bed newBed = bedService.find(newBedId);

    if (elderlyInfo != null && originalBed != null && newBed != null) {

      /**
       * 退出原来的床位
       */
      originalBed.setElderlyInfo(null);
      originalBed.setUsageState(UsageState.UNAPPROPRIATED);

      bedService.update(originalBed);

      /**
       * 设置新的床位
       */
      elderlyInfo.setBed(newBed);
      elderlyInfoService.update(elderlyInfo);

      /**
       * 添加换房记录
       */

      BedChangeRecord bedChangeRecord = new BedChangeRecord();

      bedChangeRecord.setChangeDate(new Date());
      bedChangeRecord.setElderlyInfo(elderlyInfo);
      bedChangeRecord.setNewBed(newBed);
      bedChangeRecord.setOldBed(originalBed);

      bedChangeRecordService.save(bedChangeRecord);
    }

  }
}
