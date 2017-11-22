package com.veers.storage.service;

import com.veers.storage.model.AppFile;

import java.util.List;

public interface FileService {

    AppFile findById(int id);

    List<AppFile> getAllFiles();

    void saveFile(AppFile appFile);
}
