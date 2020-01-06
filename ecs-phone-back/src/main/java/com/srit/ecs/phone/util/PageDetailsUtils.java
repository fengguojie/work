package com.srit.ecs.phone.util;

import com.srit.ecs.phone.util.Constants;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class PageDetailsUtils {
    public static PageDetails getpPageDetails(HttpServletRequest request) {
        PageDetails pageDetails = new PageDetails();
        String pageNumber = request.getParameter(Constants.PAGE_DETAILS_PAGE_NUMBER);
        String pageSize = request.getParameter(Constants.PAGE_DETAILS_PAGE_SIZE);
        String sortName = request.getParameter(Constants.PAGE_DETAILS_SORT_NAME);
        String sortOrder = request.getParameter(Constants.PAGE_DETAILS_SORT_ORDER);
        pageDetails.setPageNumber(StringUtils.hasText(pageNumber) ? Integer.valueOf(pageNumber) : 1);
        pageDetails.setPageSize(StringUtils.hasText(pageSize) ? Integer.valueOf(pageSize) : 10);
        pageDetails.setSortName(StringUtils.hasText(sortName) ? sortName : Constants.BEAN_PROPERTY_CREATE_TIME);
        pageDetails.setSortOrder(StringUtils.hasText(sortOrder)
                ? sortOrder.equals(Constants.PAGE_DETAILS_SORT_ORDER_DESC) ? Sort.Direction.DESC : Sort.Direction.ASC
                : Sort.Direction.ASC);
        return pageDetails;
    }
}
