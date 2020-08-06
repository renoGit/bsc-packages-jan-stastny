package com.prorocketeers.bscpackagesjanstastny.repository;

import com.prorocketeers.bscpackagesjanstastny.domain.PackagePost;

import java.util.List;
import java.util.Map;

public interface IPackagePostRepo {
    List<PackagePost> getPackagePosts();

    Map<String, Double> getPackagePostsGrouped();

    void createPackagePost(PackagePost packagePost);
}
