package com.yly.beans;

public interface ExportDataAttribute {
  //sheet名称
  public static final String ELDERLY_INFO = "老人信息";
  public static final String CONSIGNER = "委托人";
  public static final String FAMILY_MEMBERS = "家庭成员";
  public static final String CONFIG = "config";
  public static final String DYNAMIC_CONFIG = "dynamic_config";
  //elderlyInfo表的字段
  public static final String ID = "id"; 
  public static final String NAME = "name"; 
  public static final String IDCARD = "idcard"; 
  public static final String ELDERLY_PHONE_NUMBER = "elderly_phone_number"; 
  public static final String AGE = "age"; 
  public static final String GENDER = "gender"; 
  public static final String BE_HOSPITALIZED_DATE = "be_hospitalized_date"; 
  public static final String OUT_HOSPITALIZED_DATE = "out_hospitalized_date"; 
  public static final String NATION = "nation"; 
  public static final String PLACE_OF_ORIGIN = "place_of_origin"; 
  public static final String BIRTHDAY = "birthday"; 
  public static final String DELETE_STATUS = "delete_status"; 
  public static final String ELDERLY_STATUS = "elderly_status"; 
  public static final String EDUCATION_LEVEL = "education_level"; 
  public static final String POLITICAL_OUTLOOK = "political_outlook"; 
  public static final String RELIGION = "religion"; 
  public static final String HOBBIES = "hobbies"; 
  public static final String PERSONAL_HABITS = "personal_habits"; 
  public static final String HONORS = "honors"; 
  public static final String HOUSING_INFO = "housing_info"; 
  public static final String LIVING_STATE = "living_state"; 
  public static final String MARRIAGE_STATE = "marriage_state"; 
  public static final String MEDICAL_EXP_PAYMENT_WAY = "medical_exp_payment_way"; 
  public static final String MONTHLY_INCOME = "monthly_income"; 
  public static final String SOURCE_OF_INCOME = "source_of_income"; 
  public static final String PAYMENT_WAY = "payment_way"; 
  public static final String SOCIAL_INSURANCE_NUMBER = "social_insurance_number"; 
  public static final String ORIGINAL_COMPANY = "original_company"; 
  public static final String POSITION = "position"; 
  public static final String REGISTERED_RESIDENCE = "registered_residence"; 
  public static final String RESIDENTIAL_ADDRESS = "residential_address"; 
  public static final String MEAL_TYPE = "meal_type"; 
  public static final String NURSING_LEVEL = "nursing_level"; 
  public static final String PERSONNEL_CATEGORY = "personnel_category";
  //ElderlyConsigner表的字段
  public static final String  COMPANY_ADDRESS = "company_address";
  public static final String  CONSIGNER_NAME = "consigner_name";
  public static final String  CONSIGNER_PHONE_NUMBER = "consigner_phone_number";
  public static final String  CONSIGNER_RELATION = "consigner_relation";
  public static final String  CONSIGNER_RESIDENTIAL_ADDRESS = "consigner_residential_address";
  
  //ElderlyFamilyMembers表的字段
  public static final String MEMBER_NAME = "member_name";
  public static final String MEMBER_PHONE_NUMBER = "member_phone_number";
  public static final String MEMBER_RELATION = "member_relation";
  public static final String MEMBER_RESIDENTIAL_ADDRESS = "member_residential_address";
  
  public static final String  IS_SAME_CITY = "is_same_city";
  
  //公共
  public static final String YES_NO = "yes_no";
  public static final String YES = "YES";
  public static final String NO = "NO";
  public static final String ELDERLYINFO = "elderlyinfo";
  public static final String RELATION = "relation";
  public static final String IMPORT_STATUS = "import_status";
  public static final String IMPORT_FAILED = "导入失败";
  public static final String IMPORT_SUCCESS = "导入成功";
  public static final String IMPORT_BEFORE = "未导入";
  public static final String PLACEHOLDER = "%s";
  public static final String IMPORT_ERROR1 = "Data too long for column '%s' at row";
  public static final String IMPORT_ERROR_DATA_TOO_LONG = "<%s>的单元格内容过长，请修改！";
  public static final String IMPORT_ERROR_INPUT_INVALID = "<%s>的单元格输入有误，请修改！";
  
  
  
}
