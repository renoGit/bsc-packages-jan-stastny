package com.prorocketeers.bscpackagesjanstastny.service;

import com.prorocketeers.bscpackagesjanstastny.exception.UploadFileException;
import com.prorocketeers.bscpackagesjanstastny.repository.IFeeRepo;
import com.prorocketeers.bscpackagesjanstastny.repository.IPackagePostRepo;
import com.prorocketeers.bscpackagesjanstastny.utils.IUploadFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImportService implements IImportService {

    final IFeeRepo feeRepo;
    final IPackagePostRepo packagePostRepo;
    final IUploadFileUtil uploadFileUtil;

    @Override
    public void importPackagePostFile(String filePath) throws IOException, UploadFileException {
        val packagePosts = uploadFileUtil.loadFilePostCodeContent(filePath);
        packagePosts.forEach(packagePostRepo::createPackagePost);

    }

    @Override
    public void importFeeFile(String filePath) throws IOException, UploadFileException {
        val fees = uploadFileUtil.loadFileFeeContent(filePath);
        fees.forEach(feeRepo::createFee);
    }
}
