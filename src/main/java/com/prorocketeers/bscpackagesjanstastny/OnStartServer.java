package com.prorocketeers.bscpackagesjanstastny;

import com.prorocketeers.bscpackagesjanstastny.domain.PackagePost;
import com.prorocketeers.bscpackagesjanstastny.exception.UploadFileException;
import com.prorocketeers.bscpackagesjanstastny.parser.ILineParser;
import com.prorocketeers.bscpackagesjanstastny.service.IImportService;
import com.prorocketeers.bscpackagesjanstastny.service.IPackagePostService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class OnStartServer implements ApplicationListener<ContextRefreshedEvent> {

    final ApplicationArguments appArgs;
    final IImportService importService;
    final IPackagePostService postCodeService;
    final ILineParser lineParser;
    final ApplicationContext context;

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {

        uploadFiles();
        runLineReader();
    }


    private void uploadFiles() throws IOException, UploadFileException {

        String[] myArgs = null;
        if (!appArgs.getNonOptionArgs().isEmpty()) {
            myArgs = appArgs.getNonOptionArgs().get(0).split(",");
        }

        // arguments handling
        if (myArgs != null) {
            if (myArgs.length > 0) {
                importService.importPackagePostFile(myArgs[0]);
                System.out.println("The package delivery file was imported.");
            }

            if (myArgs.length > 1) {
                importService.importFeeFile(myArgs[1]);
                System.out.println("The fees file was imported.");
            }
        }
    }

    private void runLineReader() {
        System.out.println("\n You can add package and then press 'Enter': ");

        Scanner in = new Scanner(System.in);
        String s;
        do {
            s = in.nextLine();

            if (s.length() > 0) {
                final PackagePost postCode = lineParser.parsePostCodeLine(s);

                if (postCode != null) {
                    postCodeService.createPackagePost(postCode);
                    System.out.println("The package was saved.");
                }
            }
        }
        while (!"quit".equals(s));

        shutDownApp();
    }

    private void shutDownApp() {
        int exitCode = SpringApplication.exit(context, () -> 0);
        System.exit(exitCode);
    }

}