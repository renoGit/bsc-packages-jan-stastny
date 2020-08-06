package com.prorocketeers.bscpackagesjanstastny.service;

import com.prorocketeers.bscpackagesjanstastny.domain.PackagePost;
import com.prorocketeers.bscpackagesjanstastny.repository.IPackagePostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PackagePostService implements IPackagePostService {

    final IPackagePostRepo packagePostRepo;

    @Override
    public void createPackagePost(PackagePost postCode) {
        packagePostRepo.createPackagePost(postCode);
    }

    @Override
    public Map<String, Double> getPackagePostsGrouped() {
        return packagePostRepo.getPackagePostsGrouped();
    }
}
