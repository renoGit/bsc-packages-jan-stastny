package com.prorocketeers.bscpackagesjanstastny.service;

import com.prorocketeers.bscpackagesjanstastny.exception.UploadFileException;

import java.io.IOException;

public interface IImportService {
    void importPackagePostFile(String filePath) throws IOException, UploadFileException;

    void importFeeFile(String filePath) throws IOException, UploadFileException;
}
