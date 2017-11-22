package com.veers.storage.dao;

import com.veers.storage.model.AppFile;
import com.veers.storage.model.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("fileDao")
public class FileDaoImpl extends AbstractDao<Integer, AppFile> implements FileDao{

    static final Logger logger = LoggerFactory.getLogger(FileDaoImpl.class);

    public AppFile findById(int id) {
        AppFile appFile = getByKey(id);
        if (appFile !=null) {
            Hibernate.initialize(appFile.getPath());
        }
        return appFile;
    }

    public void save(AppFile appFile){
        persist(appFile);
    }

    @SuppressWarnings("unchecked")
    public List<AppFile> getAllFiles() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<AppFile> appFiles = (List<AppFile>) criteria.list();

        return appFiles;
    }

    public AppFile getAllFilesByUser(User user) {
        return null;
    }

}
