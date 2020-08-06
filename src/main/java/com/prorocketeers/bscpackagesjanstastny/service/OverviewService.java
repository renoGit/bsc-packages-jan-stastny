package com.prorocketeers.bscpackagesjanstastny.service;

import com.prorocketeers.bscpackagesjanstastny.domain.Fee;
import com.prorocketeers.bscpackagesjanstastny.domain.PackagePost;
import com.prorocketeers.bscpackagesjanstastny.repository.IFeeRepo;
import com.prorocketeers.bscpackagesjanstastny.repository.IPackagePostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OverviewService implements IOverviewService {

    final IFeeRepo feeRepo;
    final IPackagePostRepo packagePostRepo;

    @Override
    public List<PackagePost> getOverview() {

        final List<PackagePost> packagePosts = packagePostRepo.getPackagePosts();
        final List<Fee> fees = feeRepo.getFees();

        if (packagePosts.isEmpty()) {
            return new ArrayList<>();
        }

        final List<PackagePost> packagePostFees = packagePosts.stream().
                map(packagePost -> PackagePost.builder()
                        .postCode(packagePost.getPostCode())
                        .weight(packagePost.getWeight())
                        .fee(!fees.isEmpty() ? assignFee(packagePost.getWeight(), fees) : BigDecimal.ZERO)
                        .build()
                ).collect(Collectors.toList());


        final Set<String> postCodes = packagePostFees.stream().map(PackagePost::getPostCode)
                .collect(Collectors.toSet());
        final Map<String, Double> weightsGrouped = packagePostFees.stream().collect(Collectors.groupingBy(PackagePost::getPostCode, Collectors.summingDouble(PackagePost::getWeight)));
        final Map<String, BigDecimal> feesGrouped = packagePostFees.stream().collect(Collectors.groupingBy(PackagePost::getPostCode,
                Collectors.reducing(BigDecimal.ZERO, PackagePost::getFee, BigDecimal::add)));

        return postCodes.stream().map(s -> new PackagePost(weightsGrouped.get(s), s, feesGrouped.get(s)))
                .sorted(Comparator.comparing(PackagePost::getWeight).reversed())
                .collect(Collectors.toList());
    }

    private BigDecimal assignFee(double weight, List<Fee> fees) {
        final Fee feeFound = fees.stream()
                .filter(fee -> weight >= fee.getWeight())
                .findFirst()
                .orElse(fees.get(fees.size() - 1));
        return feeFound.getFee();
    }

}
