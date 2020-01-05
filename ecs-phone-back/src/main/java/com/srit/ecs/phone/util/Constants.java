package com.srit.ecs.phone.util;

public interface Constants {
    String HTTP_STATUS = "status";
    String HTTP_STATUS_SUCCESS = "success";
    String HTTP_STATUS_ERROR = "error";

    String HTTP_RESULT_COUNT = "count";

    String PRINCIPAL = "principal";
    String PRINCIPAL_IS_ADMIN = "isAdmin";
    String PRINCIPAL_USER_ID = "userId";
    //修改标记，增加loginName登录
    String PRINCIPAL_LOGIN_NAME = "loginName";
    String PRINCIPAL_USER_FULL_NAME = "fullName";
    String PRINCIPAL_DEPT_ID = "deptId";

    String PAGE_DETAILS_PAGE_NUMBER = "pageNum";
    String PAGE_DETAILS_PAGE_SIZE = "pageSize";
    String PAGE_DETAILS_SORT_NAME = "sortName";
    String PAGE_DETAILS_SORT_ORDER = "sortOrder";
    String PAGE_DETAILS_SORT_ORDER_ASC = "asc";
    String PAGE_DETAILS_SORT_ORDER_DESC = "desc";

    String BEAN_PROPERTY_CREATE_BY = "createBy";
    String BEAN_PROPERTY_CREATE_TIME = "id";
    String BEAN_PROPERTY_UPDATE_BY = "updateBy";
    String BEAN_PROPERTY_UPDATE_TIME = "updateTime";
    String BEAN_PROPERTY_STATE = "state";
    String BEAN_PROPERTY_STATE_TRUE = "true";

    String BOOTSTRAP_FORMAT_DATA = "data";
    String BOOTSTRAP_FORMAT_ROWS = "rows";
    String BOOTSTRAP_FORMAT_TOTAL = "total";
    
    //各个模块根据code码区分，数据库中必须有一条code码是定义的这个code码，所有的子菜单都是这个记录的子菜单
    String PARTYBUILD_CODE = "4914158";
    String DATAANALYZE_CODE = "4914160";
    String BUSSMENU_CODE = "4914161";
    String CONVENIENCE2_CODE = "4914162";
    String DATAMANAGE_CODE = "888888";
    String GRIDDING_CODE = "4914159";
    
}
