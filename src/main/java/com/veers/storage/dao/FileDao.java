package com.veers.storage.dao;

import com.veers.storage.model.AppFile;
import com.veers.storage.model.User;

import java.util.List;

public interface FileDao {
    AppFile findById(int id);

    AppFile getAllFilesByUser(User user);

    void save(AppFile appFile);

    List<AppFile> getAllFiles();
}
