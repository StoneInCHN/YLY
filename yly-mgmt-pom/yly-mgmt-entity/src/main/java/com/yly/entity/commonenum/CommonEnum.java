package com.yly.entity.commonenum;

/**
 * 公共枚举数据
 * 
 * @author shijun
 *
 */
public class CommonEnum {

  /**
   * 配置元属性基本数据类型
   * 
   * @author shijun
   *
   */
  public enum MetaDataType {
    SHORT, INT, LONG, DOUBLE, DECIMAL, STRING
  }

  /**
   * 配置元之间关系
   */
  public enum MetaRelation {
    /**
     * 依赖
     */
    DEPEND,
    /**
     * 包含
     */
    INCLUDE,
    /**
     * 关联
     */
    ASSOCIATION
  }

  /**
   * 任务审批状态
   * 
   * @author sujinxuan
   *
   */
  public enum NodeApprovalStatus {
    /**
     * 同意
     */
    AGREE,
    /**
     * 不同意
     */
    REJECT

  }

  /**
   * 节点任务状态
   * 
   * @author sujinxuan
   *
   */
  public enum NodeStatus {
    /**
     * 未开始
     */
    NOTSTARTED,
    /**
     * 待处理
     */
    PENDING,
    /**
     * 已处理
     */
    PROCESSED
  }


  /**
   * 流程整体状态
   * 
   * @author sujinxuan
   *
   */
  public enum ProcessStatus {
    /**
     * 处理中
     */
    PROCESSING,
    /**
     * 处理结束
     */
    FINISHED
  }

  /**
   * 表单控件类型
   * 
   * @author sujinxuan
   *
   */
  public enum ComponentType {

    /**
     * 文本框
     */
    TEXTBOX,
    /**
     * 文本区域
     */
    TEXTAREA,
    /**
     * 单选框
     */
    RADIOBOX,
    /**
     * 复选框
     */
    CHECKBOX,
    /**
     * 下拉列表
     */
    DROPDOWNLIST,
    /**
     * 日期框
     */
    DATE,
    /**
     * 图片
     */
    IMAGE,
    /**
     * 附件
     */
    ATTACHMENT

  }

  /**
   * 评估原因
   * 
   * @author shijun
   *
   */
  public enum EvaluatingReason {
    /**
     * 入院评估
     */
    CHECKIN_EVALUATING,
    /**
     * 入院后常规评估
     */
    ROUTINE_EVALUATING,
    /**
     * 状况发生变化后的即时评估
     */
    IMMEDIATE_EVALUATING,
    /**
     * 有疑问的再次评估
     */
    QUESTION_EVALUATING
  }

  /**
   * 评估表格状态
   * 
   * @author shijun
   *
   */
  public enum EvaluatingFormStatus {
    /**
     * 启用
     */
    ENABLE,
    /**
     * 禁用
     */
    DISABLE
  }

  /**
   * 支付方式
   * 
   * @author sujinxuan
   *
   */
  public enum PaymentType {
    /**
     * 预存款
     */
    ADVANCE,
    /**
     * 现金
     */
    CASH,
    /**
     * 银行卡及信用卡
     */
    CARD,
    /**
     * 混合支付(现金+卡)
     */
    MIXTURE
  }


  /**
   * 数据字典
   * 
   * @author sujinxuan
   *
   */
  public enum ConfigKey {
    /**
     * 房间类型,0
     */
    ROOMTYPE("房间类型"),
    /**
     * 护理级别,1
     */
    NURSELEVEL("护理级别"),
    /**
     * 伙食类别,2
     */
    MEALTYPE("伙食类别"),
    /**
     * 支付方式,3
     */
    PAYMENTTYPE("支付方式"),
    /**
     * 表单类型,4
     */
    FORMTYPE("表单类型"),
    /**
     * 计量单位,5
     */
    UNITS("计量单位"),
    /**
     * 药品分类,6
     */
    DRUGSCATEGORY("药品分类"),
    /**
     * 药品用法,7
     */
    DRUGSMETHOD("药品用法"),
    /**
     * 人员类别,8
     */
    PERSONNELCATEGORY("人员类别"),
    /**
     * 评估结果,9
     */
    EVALUATINGRESULT("评估结果"),
    /**
     * 结算日期,10
     */
    BILLDAY("结算日期"),
    /**
     * 调账原因（调钱）,11
     */
    ADJUSTMENTCAUSE("调账原因"),
    /**
     * 体检配置项,12
     */
    PHYSICALEXAMITEM("体检配置项"),
    /**
     * 评估等级,13
     */
    EVALUATINGLEVEL("评估等级");
    
    private String keyName; 
    
    private ConfigKey(String keyName) { 
        this.keyName = keyName; 
    } 
        
    public String getKeyName() { 
        return this.keyName; 
    } 
  }
  
  /**
   * 缴费类型
   * @author sujinxuan
   *
   */
  public enum BillingType{
    /**
     * 入院缴费
     */
    CHECK_IN,
    /**
     * 退住结算
     */
    CHECK_OUT,
    /**
     * 日常缴费
     */
    DAILY
    
  }

  /**
   * 缴费状态
   * 
   * @author sujinxuan
   *
   */
  public enum PaymentStatus {
    /**
     * 已缴费
     */
    PAID,
    /**
     * 未缴费
     */
    UNPAID,
    /**
     * 未缴费(调账)
     */
    UNPAID_ADJUSTMENT,
    /**
     * 已退款
     */
    REFUNDED
  }

  /**
   * 收支类型
   * 
   * @author sujinxuan
   *
   */
  public enum BudgetType {
    /**
     * 缴费
     */
    INCOME,
    /**
     * 扣费
     */
    COST
  }

  /**
   * 帐号状态
   */
  public enum AccountStatus {

    /** 帐号正常 */
    ACTIVED,

    /** 帐号锁定 */
    LOCKED
  }

  /**
   * 性别
   */
  public enum Gender {

    /** 男 */
    MALE,

    /** 女 */
    FEMALE
  }

  /**
   * 员工状态
   */
  public enum StaffStatus {

    /** 在职 */
    INSERVICE,

    /** 离职 */
    OUTSERVICE
  }

  /**
   * 寄存物品状态
   * 
   * @author shijun
   *
   */
  public enum StuffDepositStatus {
    /**
     * 存入
     */
    PUT_IN,
    /**
     * 取走
     */
    TAKE_ALWAY

  }

  /**
   * 剂量单位
   */
  public enum DosageUnit {
    /**
     * 克
     */
    G,
    /**
     * 千克
     */
    KG,
    /**
     * 毫克
     */
    MG,
    /**
     * 毫升
     */
    ML,
    /**
     * 升
     */
    L
  }

  /**
   * 用药频度
   */
  public enum DoseFrequency {
    /**
     * 一天一次
     */
    Qd,
    /**
     * 一天两次
     */
    Bid,
    /**
     * 一天三次
     */
    Tid,
    /**
     * 一天四次
     */
    Qid,
    /**
     * 两小时一次
     */
    Q2h,
    /**
     * 四小时一次
     */
    Q4h
  }

  /**
   * 药品状态
   * 
   * @author shijun
   *
   */
  public enum DrugStatus {
    /**
     * 启用
     */
    ENABLE,
    /**
     * 禁用
     */
    DISABLE
  }

  /**
   * 处方类型
   * 
   * @author shijun
   *
   */
  public enum PrescriptionType {
    /**
     * 中药
     */
    CHINESE_MEDICINE,
    /**
     * 西药
     */
    WESTEN_MEDICINE
  }

  /**
   * 处方状态
   * 
   * @author shijun
   *
   */
  public enum PrescriptionStatus {
    /**
     * 已缴费
     */
    PAID,
    /**
     * 未交费
     */
    UNPAID

  }

  /**
   * 资产类型
   * 
   * @author sujinxuan
   *
   */
  public enum AssetsType {
    /**
     * 房屋建筑物
     */
    BUILDING,
    /**
     * 生产经营
     */
    PRODUCTION,
    /**
     * 交通工具
     */
    VEHICLE,
    /**
     * 电子设备
     */
    ELECTRONIC,
    /**
     * 其他
     */
    OTHERS

  }

  /**
   * 固定资产使用状况
   * 
   * @author sujinxuan
   *
   */
  public enum AssetsUsage {
    /**
     * 使用中
     */
    INSERVICE,
    /**
     * 未使用
     */
    OUTSERVICE,
    /**
     * 不需用
     */
    NONEED

  }

  /**
   * 民族枚举数据
   * 
   * @author shijun
   *
   */
  public enum Nation {
    /**
     * 汉族
     */
    HANZU,
    /**
     * 藏族
     */
    ZANGZU,
    /**
     * 回族
     */
    HUIZU,
    /**
     * 白族
     */
    BAIZU,
    /**
     * 傣族
     */
    DAIZU,
    /**
     * 满族
     */
    MANZU,
    /**
     * 苗族
     */
    MIAOZU,
    /**
     * 怒族
     */
    NUZU,
    /**
     * 黎族
     */
    LIZU,
    /**
     * 京族
     */
    JINGZU,
    /**
     * 羌族
     */
    QIANGZU,
    /**
     * 水族
     */
    SHUIZU,
    /**
     * 侗族
     */
    DONGZU,
    /**
     * 土族
     */
    TUZU,
    /**
     * 佤族
     */
    WAZU,
    /**
     * 瑶族
     */
    YAOZU,
    /**
     * 畲族
     */
    SHEZU,
    /**
     * 彝族
     */
    YIZU,
    /**
     * 壮族
     */
    ZHUANGZU,
    /**
     * 阿昌族
     */
    ACHANGZU,
    /**
     * 保安族
     */
    BAOANZU,
    /**
     * 朝鲜族
     */
    CHAOXIANZU,
    /**
     * 布朗族
     */
    BULANGZU,
    /**
     * 布依族
     */
    BUYIZU,
    /**
     * 德昂族
     */
    DEANGZU,
    /**
     * 东乡族
     */
    DONGXIANGZU,
    /**
     * 独龙族
     */
    DULONGZU,
    /**
     * 高山族
     */
    GAOSHANZU,
    /**
     * 哈尼族
     */
    HANIZU,
    /**
     * 珞巴族
     */
    LUOBAZU,
    /**
     * 拉祜族
     */
    LAHUZU,
    /**
     * 僳僳族
     */
    SUSUZU,
    /**
     * 仡佬族
     */
    GELAOZU,
    /**
     * 赫哲族
     */
    HEZHEZU,
    /**
     * 景颇族
     */
    JINGPOZU,
    /**
     * 基诺族
     */
    JINUOZU,
    /**
     * 蒙古族
     */
    MENGGUZU,
    /**
     * 纳西族
     */
    NAXIZU,
    /**
     * 撒拉族
     */
    SALAZU,
    /**
     * 毛南族
     */
    MAONANZU,
    /**
     * 锡伯族
     */
    XIBOZU,
    /**
     * 土家族
     */
    TUJIAZU,
    /**
     * 仫佬族
     */
    MULAOZU,
    /**
     * 普米族
     */
    PUMIZU,
    /**
     * 门巴族
     */
    MENBAZU,
    /**
     * 裕固族
     */
    YUGUZU,
    /**
     * 达斡尔族
     */
    DAWOERZU,
    /**
     * 塔吉克族
     */
    TAJIKEZU,
    /**
     * 俄罗斯族
     */
    ELUOSIZU,
    /**
     * 鄂伦春族
     */
    ELUNCHUNZU,
    /**
     * 鄂温克族
     */
    EWENKEZU,
    /**
     * 哈萨克族
     */
    HASAKEZU,
    /**
     * 塔塔尔族
     */
    TATAERZU,
    /**
     * 维吾尔族
     */
    WEIWUERZU,
    /**
     * 柯尔克孜族
     */
    KEERKEZIZU,
    /**
     * 乌孜别克族
     */
    WUZIBIEKEZU
  }

  /**
   * 教育程度
   * 
   * @author shijun
   *
   */
  public enum EducationLevel {
    /**
     * 本科
     */
    BACHELOR,
    /**
     * 硕士
     */
    MASTER,
    /**
     * 博士
     */
    DOCTOR,
    /**
     * 高中
     */
    HIGHSCHOOL,
    /**
     * 中专
     */
    ZHONGZHUAN,
    /**
     * 初中
     */
    JUNIORHIGHSCHOOL,
    /**
     * 小学
     */
    PRIMARYSCHOOL,
    /**
     * 其它
     */
    OTHER
  }

  /**
   * 政治面貌
   * 
   * @author shijun
   *
   */
  public enum PoliticalOutlook {
    /**
     * 中共党员
     */
    ZHONGDANGDANGYUAN,
    /**
     * 共青团员
     */
    GONGQINGTUANYUAN,
    /**
     * 民主党派成员
     */
    MINZUDANGPAICHENGYUAN,
    /**
     * 群众
     */
    QUNZHONG,
    /**
     * 其它
     */
    OTHER
  }

  /**
   * 信仰
   * 
   * @author shijun
   *
   */
  public enum Religion {
    /**
     * 佛教
     */
    BUDDHISM,
    /**
     * 道教
     */
    TAOISM,
    /**
     * 基督教
     */
    CHRISTIANITY,
    /**
     * 伊斯兰教
     */
    ISLAM,
    /**
     * 天主教
     */
    CATHOLICISM,
    /**
     * 其它
     */
    OTHER
  }

  /**
   * 婚姻状况
   * 
   * @author shijun
   *
   */
  public enum MarriageState {
    /**
     * 已婚
     */
    MARRIED,
    /**
     * 未婚
     */
    UNMARRIED,
    /**
     * 丧偶
     */
    WIDOWED,
    /**
     * 其它
     */
    OTHER
  }

  /**
   * 居住情况
   * 
   * @author shijun
   *
   */
  public enum LivingState {
    /**
     * 独居
     */
    LIVINGALONE,
    /**
     * 和家人朋友居住
     */
    LIVINGWITHFAMILY,
    /**
     * 其它
     */
    OTHER
  }

  /**
   * 医疗支付方式
   * 
   * @author shijun
   *
   */
  public enum MedicalExpPaymentWay {
    /**
     * 基本社保
     */
    SOCIALINSURANCE,
    /**
     * 新农合
     */
    XINNONGHE,
    /**
     * 商业保险
     */
    COMMERCIALINSURANCE,
    /**
     * 自费
     */
    SELFFINANCED,
    /**
     * 公费
     */
    PUBLICFINANCED

  }

  /**
   * 收入来源
   * 
   * @author shijun
   *
   */
  public enum SourceOfIncome {
    /**
     * 退休金
     */
    PENSION,
    /**
     * 子女补助
     */
    CHILDSUPPORT,
    /**
     * 社会政府资助
     */
    SOCIALSUPPORT,
    /**
     * 其它
     */
    OTHER
  }

  /**
   * 捐赠类型
   * 
   * @author chenyoujun
   *
   */
  public enum DonateType {
    /**
     * 钱
     */
    MONEY,
    /**
     * 物
     */
    ITEM

  }

  /**
   * 住房信息
   * 
   * @author shijun
   *
   */
  public enum HousingInfo {
    /**
     * 产权房
     */
    PROPERTYRIGHTHOUSE,
    /**
     * 租房
     */
    RENTALHOUSE,
    /**
     * 其它
     */
    OTHER
  }

  /**
   * 志愿者类型
   * 
   * @author sujinxuan
   *
   */
  public enum VolunteerType {
    /**
     * 个人
     */
    PERSONAL,
    /**
     * 组织机构
     */
    ORGANIZATION
  }

  /**
   * 房间使用状态
   * 
   * @author tanbiao
   *
   */
  public enum RoomStatus {

    /**
     * 启用
     */
    ENABLE,
    /**
     * 禁用
     */
    DISABLE

  }
  
  /**
   * 房间使用状态
   * 
   * @author tanbiao
   *
   */
  public enum BedStatus {

    /**
     * 启用
     */
    ENABLE,
    /**
     * 禁用
     */
    DISABLE

  }
  
  /**
   * 床位使用状态
   * @author tanbiao
   *  
   */
  public enum UsageState{
    
    /**
     * 未被占用
     */
    UNAPPROPRIATED,
    /**
     *已占用 
     */
    OCCUPIED
  }
  

  /**
   * 信息渠道
   * 
   * @author shijun
   *
   */
  public enum InfoChannel {
    /**
     * 网络
     */
    NETWORK,
    /**
     * 社区
     */
    COMMUNITY,
    /**
     * 他人介绍
     */
    OHTER_INTRODUCT,
    /**
     * 广告
     */
    ADVERTISEMENT,
    /**
     * 其它
     */
    OTHER
  }

  /**
   * 入住意向
   * 
   * @author shijun
   *
   */
  public enum CheckinIntention {
    /**
     * 确认入住
     */
    CONFIRMED,
    /**
     * 入住意愿强
     */
    WILL_TO_CHECKIN_STRONGLY,
    /**
     * 入住意愿不强
     */
    WILL_TO_CHECKIN_NOT_STRONGLY,
    /**
     * 不入住
     */
    WILL_NOT_CHECKIN

  }
  /**
   * 关系
   * 
   * @author shijun
   *
   */
  public enum Relation {
    /**
     * 本人
     */
    SELF,
    /**
     * 子女
     */
    CHILDREN,
    /**
     * 夫妻
     */
    MARRIAGE_RELATIONSHIP,
    /**
     * 祖孙关系
     */
    GRANDPARENTS_AND_GRANDCHILDREN,
    /**
     * 兄弟或姐妹
     */
    BROTHERS_OR_SISTERS,

    /**
     * 儿媳或女婿
     */
    DAUGHTERINLAW_OR_SONINLAW,
    /**
     * 朋友
     */
    FRIEND,

    /**
     * 其它
     */
    OTHER

  }

  /**
   * 费用支付方式
   * 
   * @author shijun
   *
   */
  public enum PaymentWay {
    /**
     * 子女支付
     */
    CHILDREN_PAY,
    /**
     * 老人自费
     */
    OWN_PAYMENT,
    /**
     * 政府支付
     */
    GOV_PAY,
    /**
     * 单位支付
     */
    COMPANY_PAY,
    /**
     * 其它
     */
    OTHER

  }

  /**
   * 人员编号
   */
  public enum IdentifierType {
    /**
     * 老人编号
     */
    ELDERLYINFO_IDENTIFIER
  }

  /**
   * 树节点展开状态
   * 
   * @author tanbiao
   *
   */
  public enum TreeNodeState {
    /**
     * 展开
     */
    open,
    /**
     * 关闭
     */
    closed
  }

  /**
   * 逻辑删除标记
   * 
   * @author shijun
   *
   */
  public enum DeleteStatus {
    /**
     * 没有删除
     */
    NOT_DELETED,
    /**
     * 删除
     */
    DELETED
  }

  /**
   * 老人状态
   * 
   * @author shijun
   *
   */
  public enum ElderlyStatus {
    /**
     * 在院
     */
    IN_NURSING_HOME,
    /**
     * 出院
     */
    OUT_NURSING_HOME,
    /**
     * 入院办理
     */
    IN_PROGRESS_CHECKIN,
    /**
     * 出院办理
     */
    IN_PROGRESS_CHECKOUT,
    /**
     * 过世
     */
    DEAD,
    /**
     * 入院办理(已出账单未交费)
     */
    IN_PROGRESS_CHECKIN_BILL,
    /**
     * 通过入院评估
     */
    IN_PROGRESS_EVALUATING,
  }
  /**
   * 版本状态
   * @author huyong
   *
   */
  public enum VersionStatus{
     /**
      * 可用
      */
      ENABLE,
      /**
       * 禁用
       */
      DISABLE
  }
  /**
   * 老人事件类型
   * @author luzhang
   *
   */
  public enum ElderlyEventType{
    /**
     * 恶劣事件
     */
    NEGATIVE,
    /**
     * 普通事件
     */
    NORMAL,
    /**
     * 表扬事件
     */
    ACTIVE
  }
  /**
   * 全国评估表评估结果
   * @author luzhang
   *
   */
  public enum EvaluatingResult{
    /**
     * 0 能力完好
     */
    ABILITY_GOOD,
    /**
     * 1 轻度失能
     */
    ABILITY_MILD,
    /**
     * 2 中度失能
     */
    ABILITY_MODERATE,
    /**
     * 3 重度失能
     */
    ABILITY_SEVERE
  }
  /**
   * 评估表类型
   * @author luzhang
   *
   */
  public enum EvaluatingFormType{
    /**
     * 系统默认评估表
     */
    SYSTEM_FORM,
    /**
     * 用户自定义评估表
     */
    CUSTOM_FORM
  }
}
