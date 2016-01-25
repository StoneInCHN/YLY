package com.yly.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yly.common.log.LogUtil;
import com.yly.controller.BillingController;
import com.yly.dao.BillingDao;
import com.yly.entity.Bed;
import com.yly.entity.BedChangeRecord;
import com.yly.entity.BedChargeConfig;
import com.yly.entity.BedNurseCharge;
import com.yly.entity.Billing;
import com.yly.entity.BillingAdjustment;
import com.yly.entity.BillingSupplyment;
import com.yly.entity.Deposit;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.MealCharge;
import com.yly.entity.MealChargeConfig;
import com.yly.entity.NurseChargeConfig;
import com.yly.entity.NurseLevelChangeRecord;
import com.yly.entity.PersonalizedCharge;
import com.yly.entity.PersonalizedRecord;
import com.yly.entity.Room;
import com.yly.entity.SystemConfig;
import com.yly.entity.commonenum.CommonEnum.BillingType;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;
import com.yly.entity.commonenum.CommonEnum.ElderlyStatus;
import com.yly.entity.commonenum.CommonEnum.PaymentStatus;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.service.BedChangeRecordService;
import com.yly.service.BedChargeConfigService;
import com.yly.service.BedNurseChargeService;
import com.yly.service.BillingService;
import com.yly.service.DepositService;
import com.yly.service.ElderlyInfoService;
import com.yly.service.MealChargeConfigService;
import com.yly.service.MealChargeService;
import com.yly.service.NurseChargeConfigService;
import com.yly.service.NurseLevelChangeRecordService;
import com.yly.service.PersonalizedRecordService;
import com.yly.service.SystemConfigService;
import com.yly.service.TenantAccountService;
import com.yly.utils.DateTimeUtils;
import com.yly.utils.FieldFilterUtils;
import com.yly.utils.GenTenantBill;
import com.yly.utils.SpringUtils;
import com.yly.utils.ToolsUtils;

/**
 * 日常缴费账单
 * 
 * @author sujinxuan
 *
 */
@Service("billingServiceImpl")
public class BillingServiceImpl extends ChargeRecordServiceImpl<Billing, Long> implements
    BillingService {

  @Resource(name = "threadPoolExecutor")
  private Executor threadPoolExecutor;

  @Resource(name = "billingDaoImpl")
  private BillingDao billingDao;

  @Resource(name = "bedChargeConfigServiceImpl")
  private BedChargeConfigService bedChargeConfigService;

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;

  @Resource(name = "nurseChargeConfigServiceImpl")
  private NurseChargeConfigService nurseChargeConfigService;

  @Resource(name = "mealChargeConfigServiceImpl")
  private MealChargeConfigService mealChargeConfigService;

  @Resource(name = "depositServiceImpl")
  private DepositService depositService;

  @Resource(name = "bedNurseChargeServiceImpl")
  private BedNurseChargeService bedNurseChargeService;

  @Resource(name = "mealChargeServiceImpl")
  private MealChargeService mealChargeService;

  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @Resource(name = "personalizedRecordServiceImpl")
  private PersonalizedRecordService personalizedRecordService;

  @Resource(name = "nurseLevelChangeRecordServiceImpl")
  private NurseLevelChangeRecordService nurseLevelChangeRecordService;

  @Resource(name = "bedChangeRecordServiceImpl")
  private BedChangeRecordService bedChangeRecordService;


  @Resource
  public void setBaseDao(BillingDao billingDao) {
    super.setBaseDao(billingDao);
  }

  public Map<String, Object> getMealConfigByElderly(String[] properties, ElderlyInfo elderlyInfo) {
    List<Filter> mealfilters = new ArrayList<Filter>();
    Filter filter = new Filter("chargeItem", Operator.eq, elderlyInfo.getMealType());
    mealfilters.add(filter);
    List<MealChargeConfig> mealChargeConfigs =
        mealChargeConfigService.findList(null, mealfilters, null, true, null);
    if (mealChargeConfigs != null && mealChargeConfigs.size() == 1) {
      Map<String, Object> mealChargeMap =
          FieldFilterUtils.filterEntityMap(properties, mealChargeConfigs.get(0));
      return mealChargeMap;
    }
    return null;
  }

  @Override
  public List<Map<String, Object>> getBedNurseConfigByElderly(String[] properties,
      ElderlyInfo elderlyInfo) {
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    if (elderlyInfo.getBed() != null) {
      List<Filter> filters = new ArrayList<Filter>();
      Filter filter =
          new Filter("chargeItem", Operator.eq, elderlyInfo.getBed().getRoom().getRoomType());
      filters.add(filter);
      List<BedChargeConfig> bedChargeConfigs =
          bedChargeConfigService.findList(null, filters, null, true, null);
      if (bedChargeConfigs != null && bedChargeConfigs.size() == 1) {
        Map<String, Object> bedChargeMap =
            FieldFilterUtils.filterEntityMap(properties, bedChargeConfigs.get(0));
        list.add(bedChargeMap);
      }

    }

    if (elderlyInfo.getNursingLevel() != null) {
      List<Filter> filters = new ArrayList<Filter>();
      Filter filter = new Filter("chargeItem", Operator.eq, elderlyInfo.getNursingLevel());
      filters.add(filter);
      List<NurseChargeConfig> nurseChargeConfigs =
          nurseChargeConfigService.findList(null, filters, null, true, null);
      if (nurseChargeConfigs != null && nurseChargeConfigs.size() == 1) {
        Map<String, Object> nurseChargeConfigMap =
            FieldFilterUtils.filterEntityMap(properties, nurseChargeConfigs.get(0));
        list.add(nurseChargeConfigMap);
      }

    }
    return list;
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void updateUnpaidCheckInBill(Billing originBill, Billing editBill) {
    depositService.delete(originBill.getDeposit());
    editBill.getDeposit().setBilling(originBill);
    originBill.setDeposit(editBill.getDeposit());

    bedNurseChargeService.delete(originBill.getBedNurseCharge());
    editBill.getBedNurseCharge().setBilling(originBill);
    originBill.setBedNurseCharge(editBill.getBedNurseCharge());

    mealChargeService.delete(originBill.getMealCharge());
    if (editBill.getMealCharge() != null) {
      editBill.getMealCharge().setBilling(originBill);
      originBill.setMealCharge(editBill.getMealCharge());
    }

    if (LogUtil.isDebugEnabled(BillingServiceImpl.class)) {
      LogUtil.debug(BillingServiceImpl.class, "Check In Charge Update unpaid bill",
          "Bill Entity=%s", ToolsUtils.entityToString(originBill));
    }
    billingDao.merge(originBill);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public Billing updatePaidCheckInBill(Billing originBill, Billing editBill, Long mealTypeId,
      Boolean isMonthlyMeal) {
    Boolean depositeChanged = false;
    Boolean bedNuresChanged = false;
    Boolean mealChanged = false;
    /**
     * 判定押金是否修改
     */
    if (!originBill.getDeposit().getDepositAmount()
        .equals(editBill.getDeposit().getDepositAmount())) {
      depositeChanged = true;
    }
    /**
     * 判断床位护理费是否修改
     */
    if (originBill.getBedNurseCharge().getPeriodStartDate().getTime() != editBill
        .getBedNurseCharge().getPeriodStartDate().getTime()) {
      bedNuresChanged = true;
    }
    /**
     * 判断伙食费是否修改
     */
    if ((originBill.getMealCharge() == null && editBill.getMealCharge() != null)
        || (originBill.getMealCharge() != null && editBill.getMealCharge() == null)
        || (originBill.getMealCharge().getPeriodStartDate().getTime() != editBill.getMealCharge()
            .getPeriodStartDate().getTime())
        || (originBill.getMealCharge().getPeriodStartDate().getTime() == editBill.getMealCharge()
            .getPeriodStartDate().getTime() && !originBill.getElderlyInfo().getMealType().getId()
            .equals(mealTypeId))) {

      mealChanged = true;
    }

    if (depositeChanged || bedNuresChanged || mealChanged) {
      BillingSupplyment billingSupplyment = new BillingSupplyment();
      billingSupplyment.setBilling(originBill);
      billingSupplyment.setBillingNo(ToolsUtils.generateBillNo(tenantAccountService
          .getCurrentTenantOrgCode()));
      billingSupplyment.setOperator(tenantAccountService.getCurrentUsername());
      billingSupplyment.setTenantID(tenantAccountService.getCurrentTenantID());

      if (depositeChanged) {
        // 押金
        Deposit deposit = editBill.getDeposit();
        deposit.setBillingSupply(billingSupplyment);
        deposit.setBillingNo(billingSupplyment.getBillingNo());
        deposit.setOperator(billingSupplyment.getOperator());
        deposit.setTenantID(billingSupplyment.getTenantID());
        billingSupplyment.setDeposit(deposit);
        billingSupplyment.setDepositAmount(deposit.getDepositAmount());
      }

      if (bedNuresChanged) {
        BedNurseCharge bedNurseCharge = editBill.getBedNurseCharge();
        bedNurseCharge.setBillingSupply(billingSupplyment);
        bedNurseCharge.setBillingNo(billingSupplyment.getBillingNo());
        bedNurseCharge.setOperator(billingSupplyment.getOperator());
        bedNurseCharge.setTenantID(billingSupplyment.getTenantID());
        billingSupplyment.setBedNurseCharge(bedNurseCharge);
        billingSupplyment.setBedAmount(bedNurseCharge.getBedAmount());
        billingSupplyment.setNurseAmount(bedNurseCharge.getNurseAmount());
      }

      if (mealChanged) {
        if (isMonthlyMeal != null) {
          MealCharge mealCharge = editBill.getMealCharge();
          mealCharge.setBillingSupply(billingSupplyment);
          mealCharge.setBillingNo(billingSupplyment.getBillingNo());
          mealCharge.setOperator(billingSupplyment.getOperator());
          mealCharge.setTenantID(billingSupplyment.getTenantID());
          billingSupplyment.setMealCharge(mealCharge);
          billingSupplyment.setMealAmount(mealCharge.getMealAmount());
        }
      }

      billingSupplyment.setTotalAmount(editBill.getTotalAmount());
      originBill.setBillingSupply(billingSupplyment);
      if (LogUtil.isDebugEnabled(BillingServiceImpl.class)) {
        LogUtil.debug(BillingController.class, "Check In Charge Update paid Bill",
            "Bill Entity=%s", ToolsUtils.entityToString(billingSupplyment));
      }

      return billingDao.merge(originBill);
    }
    return null;
  }

  @Override
  public void genMonthlyBill() {
    List<Filter> filters = new ArrayList<Filter>();
    Filter keyFilter = new Filter("configKey", Operator.eq, ConfigKey.BILLDAY);
    Filter enableFilter = new Filter("isEnabled", Operator.eq, true);
    filters.add(enableFilter);
    filters.add(keyFilter);
    List<SystemConfig> systemConfigs = systemConfigService.findList(null, filters, null);
    Date now = DateTimeUtils.getDayStart(new Date());
    Calendar c = Calendar.getInstance();
    c.setTime(now);
    for (SystemConfig config : systemConfigs) {
      Integer billDay = Integer.parseInt(config.getConfigValue());
      if (billDay == c.get(Calendar.DAY_OF_MONTH)) {
        GenTenantBill ex = new GenTenantBill(c.getTime(), config.getTenantID());
        threadPoolExecutor.execute(ex);
      }
    }

  }


  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void genBillByTenantBillDate(Date billDate, Long tenantId) {
    Date startDate = DateTimeUtils.getSpecifyTimeForDate(billDate, null, -1, 1, null, null, null);
    Date nextBillDate =
        DateTimeUtils.getSpecifyTimeForDate(billDate, null, 1, null, null, null, null);
    Date nextStartDate =
        DateTimeUtils.getSpecifyTimeForDate(billDate, null, null, 1, null, null, null);
    List<Filter> filters = new ArrayList<Filter>();
    Filter statusFilter = new Filter("elderlyStatus", Operator.eq, ElderlyStatus.IN_NURSING_HOME);
    filters.add(statusFilter);
    List<ElderlyInfo> elderlyInfos = elderlyInfoService.findList(null, filters, null, true, null);
    for (ElderlyInfo elderlyInfo : elderlyInfos) {
      Billing billing = new Billing();
      billing.setChargeStatus(PaymentStatus.UNPAID);
      billing.setBillType(BillingType.DAILY);
      billing.setElderlyInfo(elderlyInfo);
      billing
          .setBillingNo(ToolsUtils.generateBillNo(tenantAccountService.getCurrentTenantOrgCode()));
      billing.setTenantID(tenantId);
      billing.setPeriodStartDate(startDate);
      billing.setPeriodEndDate(billDate);

      String[] properties = {"chargeItem.configValue", "amountPerDay", "amountPerMonth"};
      /**
       * ==========================================================================================
       * =========================== 床位护理费
       */
      BedNurseCharge bedNurseCharge = new BedNurseCharge();
      bedNurseCharge.setBilling(billing);
      bedNurseCharge.setBillingNo(billing.getBillingNo());
      bedNurseCharge.setElderlyInfo(elderlyInfo);
      bedNurseCharge.setChargeStatus(billing.getChargeStatus());
      bedNurseCharge.setTenantID(billing.getTenantID());
      bedNurseCharge.setPeriodStartDate(nextStartDate);
      bedNurseCharge.setPeriodEndDate(nextBillDate);

      List<Map<String, Object>> chargeMap = getBedNurseConfigByElderly(properties, elderlyInfo);
      bedNurseCharge
          .setBedAmount(new BigDecimal(chargeMap.get(0).get("amountPerMonth").toString()));
      bedNurseCharge.setNurseAmount(new BigDecimal(chargeMap.get(1).get("amountPerMonth")
          .toString()));

      // 如该结算周期内老人的床位护理费已在入院账单中缴纳,则生成状态为已缴费的床位护理费日常账单
      List<Filter> dateFilters = new ArrayList<Filter>();
      Filter elderFilter = new Filter("elderlyInfo", Operator.eq, elderlyInfo);
      Filter sTimeFilter = new Filter("periodStartDate", Operator.ge, nextStartDate);
      Filter eTimeFilter = new Filter("periodEndDate", Operator.le, nextBillDate);
      dateFilters.add(sTimeFilter);
      dateFilters.add(eTimeFilter);
      dateFilters.add(elderFilter);
      List<BedNurseCharge> existBedNurseCharges =
          bedNurseChargeService.findList(null, dateFilters, null, true, null);
      if (existBedNurseCharges != null) {
        if (existBedNurseCharges.size() != 1) {
          LogUtil.error(BillingServiceImpl.class,
              "ERROR:run monthly bill job,existBedNurseCharges size is not 1",
              "Tenant ID=%s,ElderlyInfo ID=%s,BillDate=%s", tenantId, elderlyInfo.getId(),
              billDate.toString());
        } else {
          bedNurseCharge.setChargeStatus(PaymentStatus.PAID);
        }
      }
      billing.setBedAmount(bedNurseCharge.getBedAmount());
      billing.setNurseAmount(bedNurseCharge.getNurseAmount());
      billing.setBedNurseCharge(bedNurseCharge);

      // 计算老人换房后差价
      List<Ordering> orderings = new ArrayList<Ordering>();
      Ordering ordering = new Ordering();
      ordering.setDirection(Direction.desc);
      ordering.setProperty("createDate");

      List<Filter> elderlyFilters = new ArrayList<Filter>();
      elderlyFilters.add(elderFilter);
      List<BedChangeRecord> bedChangeRecords =
          bedChangeRecordService.findList(null, elderlyFilters, orderings);
      if (bedChangeRecords != null && bedChangeRecords.size() > 0) {
        BedChangeRecord bedChangeRecord = bedChangeRecords.get(0);
        if (!elderlyInfo.getBed().getId().equals(bedChangeRecord.getNewBed().getId())) {// 上个结算周期内存在更换房间情况
          Long diffDays =
              (billDate.getTime() - bedChangeRecord.getChangeDate().getTime())
                  / (1000 * 60 * 60 * 24) + 1;
          BigDecimal diffPrice =
              calDiffPriceConfig(ConfigKey.ROOMTYPE, diffDays, bedChangeRecord.getOldBed()
                  .getRoom().getRoomType(), bedChangeRecord.getNewBed().getRoom().getRoomType());

          BillingAdjustment billAdjust = new BillingAdjustment();
          billAdjust.setBilling(billing);
          billAdjust.setChargeStatus(billing.getChargeStatus());
          billAdjust.setAdjustmentAmount(diffPrice);
          billAdjust.setAdjustmentCause(SpringUtils.getMessage("yly.bedNurse.change",
              getBedLocation(bedChangeRecord.getOldBed()),
              getBedLocation(bedChangeRecord.getNewBed()), bedChangeRecord.getChangeDate()));
          billing.getBillingAdjustment().add(billAdjust);
        }

      }

      List<NurseLevelChangeRecord> nurseChangeRecords =
          nurseLevelChangeRecordService.findList(null, elderlyFilters, orderings);
      if (nurseChangeRecords != null && nurseChangeRecords.size() > 0) {
        NurseLevelChangeRecord nurseChangeRecord = nurseChangeRecords.get(0);
        if (!elderlyInfo.getNursingLevel().getId()
            .equals(nurseChangeRecord.getNewNurseLevel().getId())) {// 上个结算周期内存在更换护理级别情况
          Long diffDays =
              (billDate.getTime() - nurseChangeRecord.getChangeDate().getTime())
                  / (1000 * 60 * 60 * 24) + 1;
          BigDecimal diffPrice =
              calDiffPriceConfig(ConfigKey.ROOMTYPE, diffDays,
                  nurseChangeRecord.getOldNurseLevel(), nurseChangeRecord.getNewNurseLevel());

          BillingAdjustment billAdjust = new BillingAdjustment();
          billAdjust.setBilling(billing);
          billAdjust.setChargeStatus(billing.getChargeStatus());
          billAdjust.setAdjustmentAmount(diffPrice);
          billAdjust.setAdjustmentCause(SpringUtils.getMessage("yly.bedNurse.change",
              nurseChangeRecord.getOldNurseLevel().getConfigValue(), nurseChangeRecord
                  .getNewNurseLevel().getConfigValue(), nurseChangeRecord.getChangeDate()));
          billing.getBillingAdjustment().add(billAdjust);


        }
      }

      /**
       * ==========================================================================================
       * ========================== 伙食费
       */
      if (elderlyInfo.getMealFeeMonthlyPayment()) {
        MealCharge mealCharge = new MealCharge();
        mealCharge.setBilling(billing);
        mealCharge.setBillingNo(billing.getBillingNo());
        mealCharge.setElderlyInfo(elderlyInfo);
        mealCharge.setChargeStatus(billing.getChargeStatus());
        mealCharge.setTenantID(tenantId);
        mealCharge.setPeriodStartDate(nextStartDate);
        mealCharge.setPeriodEndDate(nextBillDate);

        Map<String, Object> mealChargeConfigMap = getMealConfigByElderly(properties, elderlyInfo);
        mealCharge.setMealAmount(new BigDecimal(mealChargeConfigMap.get("amountPerMonth")
            .toString()));

        // 如该结算周期内老人的伙食费已在入院账单中缴纳,则生成状态为已缴费的伙食费日常账单
        List<MealCharge> existMealCharges =
            mealChargeService.findList(null, dateFilters, null, true, null);
        if (existMealCharges != null) {
          if (existBedNurseCharges.size() != 1) {
            LogUtil.error(BillingServiceImpl.class,
                "ERROR:run monthly bill job,existBedNurseCharges size is not 1",
                "Tenant ID=%s,ElderlyInfo ID=%s,BillDate=%s", tenantId, elderlyInfo.getId(),
                billDate.toString());
          } else {
            mealCharge.setChargeStatus(PaymentStatus.PAID);
          }
        }
        billing.setMealAmount(mealCharge.getMealAmount());
        billing.setMealCharge(mealCharge);
      }

      /**
       * ==========================================================================================
       * ========================== 个性化服务费
       */
      List<Filter> serviceFilters = new ArrayList<Filter>();
      Filter sFilter = new Filter("serviceTime", Operator.le, startDate);
      Filter eFilter = new Filter("serviceTime", Operator.ge, billDate);
      dateFilters.add(sFilter);
      dateFilters.add(eFilter);
      dateFilters.add(elderFilter);
      List<PersonalizedRecord> personalizedRecords =
          personalizedRecordService.findList(null, serviceFilters, null, true, null);
      if (personalizedRecords != null && personalizedRecords.size() > 0) {
        PersonalizedCharge personalizedCharge = new PersonalizedCharge();
        personalizedCharge.setBilling(billing);
        personalizedCharge.setBillingNo(billing.getBillingNo());
        personalizedCharge.setElderlyInfo(elderlyInfo);
        personalizedCharge.setChargeStatus(billing.getChargeStatus());
        personalizedCharge.setTenantID(tenantId);
        personalizedCharge.setPeriodStartDate(startDate);
        personalizedCharge.setPeriodEndDate(billDate);
        BigDecimal serviceCharge = new BigDecimal(0);
        for (PersonalizedRecord personalizedRecord : personalizedRecords) {
          personalizedRecord.setPersonalizedCharge(personalizedCharge);
          serviceCharge =
              serviceCharge.add(personalizedRecord.getPersonalizedNurse().getServicePrice());
        }
        Set<PersonalizedRecord> records = new HashSet<PersonalizedRecord>(personalizedRecords);
        personalizedCharge.setPersonalizedRecords(records);
        personalizedCharge.setPersonalizedAmount(serviceCharge);

        billing.setPersonalizedAmount(personalizedCharge.getPersonalizedAmount());
        billing.setPersonalizedCharge(personalizedCharge);
      }


      billingDao.merge(billing);
    }

  }

  public BigDecimal calDiffPriceBed(Long days, Bed oldBed, Bed newBed) {
    List<Filter> oldFilters = new ArrayList<Filter>();
    Filter oldFilter = new Filter("chargeItem", Operator.eq, oldBed.getRoom().getRoomType());
    oldFilters.add(oldFilter);
    List<BedChargeConfig> oldBedChargeConfigs =
        bedChargeConfigService.findList(null, oldFilters, null, true, null);

    List<Filter> newFilters = new ArrayList<Filter>();
    Filter newFilter = new Filter("chargeItem", Operator.eq, newBed.getRoom().getRoomType());
    newFilters.add(newFilter);
    List<BedChargeConfig> newBedChargeConfigs =
        bedChargeConfigService.findList(null, newFilters, null, true, null);
    if (oldBedChargeConfigs != null && oldBedChargeConfigs.size() == 1
        && newBedChargeConfigs != null && newBedChargeConfigs.size() == 1) {

    }
    return null;

  }

  public BigDecimal calDiffPriceConfig(ConfigKey key, Long days, SystemConfig oldConfig,
      SystemConfig newConfig) {
    List<Filter> oldFilters = new ArrayList<Filter>();
    Filter oldFilter = new Filter("chargeItem", Operator.eq, oldConfig);
    oldFilters.add(oldFilter);

    List<Filter> newFilters = new ArrayList<Filter>();
    Filter newFilter = new Filter("chargeItem", Operator.eq, newConfig);
    newFilters.add(newFilter);

    if (key.equals(ConfigKey.ROOMTYPE)) {
      List<BedChargeConfig> oldChargeConfigs =
          bedChargeConfigService.findList(null, oldFilters, null, true, null);


      List<BedChargeConfig> newChargeConfigs =
          bedChargeConfigService.findList(null, newFilters, null, true, null);
      if (oldChargeConfigs != null && oldChargeConfigs.size() == 1 && newChargeConfigs != null
          && newChargeConfigs.size() == 1) {
        BedChargeConfig oldCharge = oldChargeConfigs.get(0);
        BedChargeConfig newCharge = newChargeConfigs.get(0);
        BigDecimal diffPrice = newCharge.getAmountPerDay().subtract(oldCharge.getAmountPerDay());
        BigDecimal diffAmount = diffPrice.multiply(new BigDecimal(days));
        BigDecimal amount = newCharge.getAmountPerMonth().subtract(oldCharge.getAmountPerMonth());
        if (diffAmount.abs().compareTo(amount.abs()) == 1) {
          return amount;
        }
        return diffAmount;
      }
    } else if (key.equals(ConfigKey.NURSELEVEL)) {
      List<NurseChargeConfig> oldChargeConfigs =
          nurseChargeConfigService.findList(null, oldFilters, null, true, null);


      List<NurseChargeConfig> newChargeConfigs =
          nurseChargeConfigService.findList(null, newFilters, null, true, null);
      if (oldChargeConfigs != null && oldChargeConfigs.size() == 1 && newChargeConfigs != null
          && newChargeConfigs.size() == 1) {
        NurseChargeConfig oldCharge = oldChargeConfigs.get(0);
        NurseChargeConfig newCharge = newChargeConfigs.get(0);
        BigDecimal diffPrice = newCharge.getAmountPerDay().subtract(oldCharge.getAmountPerDay());
        BigDecimal diffAmount = diffPrice.multiply(new BigDecimal(days));
        BigDecimal amount = newCharge.getAmountPerMonth().subtract(oldCharge.getAmountPerMonth());
        if (diffAmount.abs().compareTo(amount.abs()) == 1) {
          return amount;
        }
        return diffAmount;
      }
    }

    return null;

  }

  public String getBedLocation(Bed bed) {
    String bedLocation = "";
    StringBuffer str = new StringBuffer();
    if (bed != null) {
      Room room = bed.getRoom();
      str.append(room.getBuilding().getBuildingName());
      str.append(bed.getBedNumber());
      str.append(room.getRoomType().getConfigValue());
      bedLocation = str.toString();
    }
    return bedLocation;
  }
}
