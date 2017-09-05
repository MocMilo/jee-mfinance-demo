package com.infoshare.web.utils;

public class ConstantsProvider {

    private ConstantsProvider(){}

    public static final String CRITERIA_MODERATION_MESSAGE = "Note! Your input data does not correspond to current investment history of quotations. \n" +
            "    For analysis system used nearest possible quoutations acording to dates from submitted form.\n" +
            "    User criteria moderated by system are listed in User input moderation report.";
    public static final String NO_DATA_FOR_CRITERIA_MESSAGE = "Error! No data for current criteria!";

    public static final String AUTH_USER = "authenticatedUser";

    public static final String CONTENT_WRAPPER = "contentWrapper";
    public static final String CONTENT_WRAPPER_COLLECTION = "contentWrappers";

    public static final String USER_FAVOURITE_CUSTOM_NAME = "userCustomName";
    public static final String CRITERIA_ID = "criteriaId";
    public static final String DELETE_ACTION = "deleteAction";
    public static final String UPDATE_ACTION = "updateAction";

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String INVESTMENT_NAME = "investmentName";
    public static final String CAPITAL = "capital";
    public static final String BUY_DATE = "buyDate";
    public static final String SELL_DATE = "sellDate";
    public static final String IS_FAVOURITE = "isFavourite";

    public static final String APP_MESSAGE = "message";
    public static final String ALL_USERS = "allUsers";
    public static final String ALL_INV_REV_CRIT ="allInvRevCrit";
    public static final String FUND_COUNT = "fundCount";
    public static final String CURRENCY_COUNT = "currencyCount";
    public static final String ALL_CURRENCIES ="allCurrencies";
    public static final String ALL_FUNDS ="allFunds";
    public static final String ALL_INVESTMENTS ="allInvestments";

    public static final String INVESTMENT_NAME_A="investmentNameA";
    public static final String INVESTMENT_NAME_B="investmentNameB";
    public static final String INVESTMENT_STAT_RESULT_A="investmentStatResultA";
    public static final String INVESTMENT_STAT_RESULT_B="investmentStatResultB";

    public static final String MAIL_SMTP_HOST = "mail.smtp.host";
    public static final String MAIL_SMTP_SOCKETFACTORY_PORT = "mail.smtp.socketFactory.port";
    public static final String MAIL_SMTP_PORT = "mail.smtp.port";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String TARGET_EMAIL = "targetEmail";
    public static final String SMTP_CONFIG_FILE_NAME = "smtpconfig.json";

    public static final String TMP_FILE_NAME = "tmp/reportComponents.csv";

    public static final String TASK_NAME="taskName";
    public static final String START_DATE ="startDate";
    public static final String END_DATE ="endDate";
    public static final String TASK_START_DELAY="startDelay";
    public static final String TASK_TIMESPAN="timeSpan";
    public static final String TASK_ISACTIVE="isActive";

    public static final String LINE_SEPARATOR ="line.separator";
}
