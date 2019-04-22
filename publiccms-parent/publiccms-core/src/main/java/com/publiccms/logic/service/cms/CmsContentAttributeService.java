package com.publiccms.logic.service.cms;

import java.io.Serializable;
import java.util.List;

import com.publiccms.entities.cms.CmsContentAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.publiccms.common.base.BaseService;
import com.publiccms.logic.dao.cms.CmsContentAttributeDao;

/**
 *
 * CmsContentAttributeService
 * 
 */
@Service
@Transactional
public class CmsContentAttributeService extends BaseService<CmsContentAttribute> {
    
    private String[] ignoreProperties = new String[] { "contentId" };

    /**
     * @param ids
     * @return results page
     */
    public List<CmsContentAttribute> getEntitysWithoutText(Serializable[] ids) {
        return dao.getEntitys(ids);
    }

    @Autowired
    private CmsContentAttributeDao cmsContentAttributeDao;

    public CmsContentAttribute findByEntity(CmsContentAttribute cmsContentAttribute){
        return cmsContentAttributeDao.findByEntity(cmsContentAttribute);
    }

    /**
     * @param contentId
     * @param entity
     */
    public void updateAttribute(Long contentId, CmsContentAttribute entity) {
        CmsContentAttribute attribute = getEntity(contentId);
        if (null != attribute) {
            if (null != entity) {
                update(attribute.getContentId(), entity, ignoreProperties);
            } else {
                delete(attribute.getContentId());
            }
        } else {
            if (null != entity) {
                entity.setContentId(contentId);
                save(entity);
            }
        }
    }
    
}