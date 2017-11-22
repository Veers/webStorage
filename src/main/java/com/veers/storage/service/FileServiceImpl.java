package com.veers.storage.service;

import com.veers.storage.dao.FileDao;
import com.veers.storage.model.AppFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("fileService")
@Transactional
public class FileServiceImpl implements FileService{

    @Autowired
    private FileDao dao;

    public AppFile findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<AppFile> getAllFiles() {
        return dao.getAllFiles();
    }

    public void saveFile(AppFile appFile) {
        dao.save(appFile);
    }
}
