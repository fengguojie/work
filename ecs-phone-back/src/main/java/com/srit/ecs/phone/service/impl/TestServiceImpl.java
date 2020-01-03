package com.srit.ecs.phone.service.impl;

//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.interceptor.TransactionAspectSupport;
//
//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.baomidou.mybatisplus.plugins.Page;
//import com.srit.ecs.infoopen.entity.ArticleEntity;
//import com.srit.ecs.infoopen.entity.DictionaryEntity;
//import com.srit.ecs.infoopen.mapper.ArticleMapper;
//import com.srit.ecs.infoopen.mapper.DictionaryMapper;
//import com.srit.ecs.infoopen.service.ArticleService;
//import com.srit.ecs.infoopen.util.BeanUtils;
//import com.srit.ecs.infoopen.util.Constants;
//import com.srit.ecs.infoopen.util.DateUtils;
//import com.srit.ecs.infoopen.util.HumpToUnderline;
//import com.srit.ecs.infoopen.util.PageDetails;

//@Service
public class TestServiceImpl{// implements ArticleService {

	/**
	
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private DictionaryMapper dictionaryMapper;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public Map<String, Object> queryAllByPage(ArticleEntity template, PageDetails page) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
        EntityWrapper<ArticleEntity> queryWrapper = new EntityWrapper<ArticleEntity>();
        String sortName = new HumpToUnderline().humpToUnderline(new  StringBuffer(page.getSortName())).toString();
        queryWrapper.where(" state = true ")
        			.orderBy(sortName, (page.getSortOrder().equals(Sort.Direction.ASC) ? true : false ));
        if(template.getTitle() != null ) {
        	queryWrapper.like("title", template.getTitle());
        }
        if(template.getCategoryId() != null &&  template.getCategoryId() != 0) {
        	queryWrapper.eq("category_id", template.getCategoryId());
        }
        if(template.getId() != null &&  template.getId() != 0) {
        	queryWrapper.eq("id", template.getId());
        }
        List<ArticleEntity> articleList = articleMapper.selectPage(new Page<>(page.getPageNumber()+1, page.getPageSize()), queryWrapper);
        Integer count = articleMapper.selectCount(queryWrapper);
        loadDatas(articleList);
        result.put(Constants.BOOTSTRAP_FORMAT_TOTAL, count);
        result.put(Constants.BOOTSTRAP_FORMAT_ROWS, articleList);
		return result;
	}
	
	@Override
    @Transactional
    public String saveOneByEntity(ArticleEntity template) {
        String status;
        try {
            if (template.getId() != null) {
                BeanUtils.setUpdateProperties(template);
                BeanUtils.copyNullProperties(articleMapper.selectById(template.getId()), template);
                articleMapper.updateById(template);
            } else {
                BeanUtils.setCreateProperties(template);
                template.setCode(DateUtils.generateNumberKey().toString());
                articleMapper.insert(template);
            }
            status = Constants.HTTP_STATUS_SUCCESS;
        } catch (Exception e) {
            status = Constants.HTTP_STATUS_ERROR;
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return status;
    }
	
    @Override
    @Transactional
    public String deleteAllByIds(String[] ids) {
        String status;
        try {
            for (String id : ids) {
            	//articleMapper.deleteById(new Long(id));
            	ArticleEntity ae = new ArticleEntity();
            	ae.setId(new Long(id));
            	ae.setState(false);
            	articleMapper.updateById(ae);
            }
            status = Constants.HTTP_STATUS_SUCCESS;
        } catch (Exception e) {
            status = Constants.HTTP_STATUS_ERROR;
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return status;
    }
	
    @Override
    @Transactional
    public String releaseByIds(String[] ids) {
        String status;
        try {
            for (String id : ids) {
            	ArticleEntity ae = articleMapper.selectById(new Long(id));
            	Integer articleStatus = 0;
            	if(ae.getStatus() == 0 ) {
            		articleStatus = 1;
            	}else if(ae.getStatus() == 1 ) {
            		articleStatus = 0;
            	}else {
            		continue;
            	}
        		jdbcTemplate.update("UPDATE article SET STATUS= ? WHERE id= ?", new Object[]{articleStatus,ae.getId()});
            }
            status = Constants.HTTP_STATUS_SUCCESS;
        } catch (Exception e) {
            status = Constants.HTTP_STATUS_ERROR;
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return status;
    }
    
    public void loadDatas(List<ArticleEntity> list) {
    	try {
        	for(ArticleEntity ae  : list) {
        		if(ae.getCategoryId() != null && ae.getCategoryId() != 0) {
            		ae.setCategoryName(loadCategory(ae.getCategoryId()).getName());
        		}
        	}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    public DictionaryEntity loadCategory(Long id) {
    	return dictionaryMapper.selectById(id);
    }
    */
}
