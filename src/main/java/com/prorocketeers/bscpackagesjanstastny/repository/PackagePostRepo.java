package com.prorocketeers.bscpackagesjanstastny.repository;

import com.prorocketeers.bscpackagesjanstastny.domain.PackagePost;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PackagePostRepo implements IPackagePostRepo {

    List<PackagePost> packagePosts = new ArrayList<>();

    @Override
    public List<PackagePost> getPackagePosts() {
        return packagePosts;
    }

    @Override
    public Map<String, Double> getPackagePostsGrouped() {

        final Map<String, Double> collect = packagePosts.stream()
                .collect(
                        Collectors.groupingBy(PackagePost::getPostCode,
                                Collectors.summingDouble(PackagePost::getWeight)
                        )
                );

        return collect.entrySet()
                        .stream()
                        .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                        .collect(Collectors.toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (a, b) -> a, //or throw an exception
                                LinkedHashMap::new));
    }

    @Override
    public void createPackagePost(PackagePost packagePost) {
        packagePosts.add(packagePost);
    }

}
