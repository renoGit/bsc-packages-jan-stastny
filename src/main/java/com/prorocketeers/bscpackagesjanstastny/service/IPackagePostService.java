package com.prorocketeers.bscpackagesjanstastny.service;

import com.prorocketeers.bscpackagesjanstastny.domain.PackagePost;

import java.util.Map;

public interface IPackagePostService {
    void createPackagePost(PackagePost postCode);

    Map<String, Double> getPackagePostsGrouped();
}
