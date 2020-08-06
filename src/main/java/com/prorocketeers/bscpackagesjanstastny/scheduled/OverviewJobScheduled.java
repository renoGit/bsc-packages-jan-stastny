package com.prorocketeers.bscpackagesjanstastny.scheduled;

import com.prorocketeers.bscpackagesjanstastny.domain.PackagePost;
import com.prorocketeers.bscpackagesjanstastny.service.IOverviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class OverviewJobScheduled {

    final IOverviewService overviewService;

    @Scheduled(cron = "${shared-folders-cron}")
    private void runImport() {
        final List<PackagePost> overview = overviewService.getOverview();

        DecimalFormat df = (DecimalFormat) DecimalFormat.getNumberInstance(Locale.US);
        df.applyPattern("#0.000");
        DecimalFormat df2 = (DecimalFormat) DecimalFormat.getNumberInstance(Locale.US);
        df2.applyPattern("#0.00");

//        DecimalFormat df = new DecimalFormat("#0.000");
//        DecimalFormat df2 = new DecimalFormat("#0.00");

        System.out.println();
        System.out.println("---Package delivery overview---");
        for (PackagePost packagePostFee : overview) {
            System.out.println(packagePostFee.getPostCode() + " " +
                    df.format(packagePostFee.getWeight()) + " " +
                    df2.format(packagePostFee.getFee()));
        }
    }

}
