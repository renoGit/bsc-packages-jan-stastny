package com.prorocketeers.bscpackagesjanstastny.utils;

import com.prorocketeers.bscpackagesjanstastny.domain.Fee;
import com.prorocketeers.bscpackagesjanstastny.domain.PackagePost;
import com.prorocketeers.bscpackagesjanstastny.exception.UploadFileException;

import java.io.IOException;
import java.util.List;

public interface IUploadFileUtil {

    List<PackagePost> loadFilePostCodeContent(String path) throws IOException, UploadFileException;

    List<Fee> loadFileFeeContent(String path) throws IOException, UploadFileException;

}
