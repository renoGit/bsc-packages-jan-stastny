package com.prorocketeers.bscpackagesjanstastny.utils;

import com.prorocketeers.bscpackagesjanstastny.domain.Fee;
import com.prorocketeers.bscpackagesjanstastny.domain.PackagePost;
import com.prorocketeers.bscpackagesjanstastny.exception.UploadFileException;
import com.prorocketeers.bscpackagesjanstastny.parser.ILineParser;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UploadFileUtil implements IUploadFileUtil {

    final ILineParser lineParser;

    /**
     * Reading of a file
     * @param path a file path
     * @return the list of {@link PackagePost}
     * @throws IOException
     * @throws UploadFileException
     */
    public List<PackagePost> loadFilePostCodeContent(String path) throws IOException, UploadFileException {
        try (val linesStream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            val lines = linesStream.collect(Collectors.toUnmodifiableList());

            List<PackagePost> packagePostDeliveries = new ArrayList<>();
            for (val line : lines) {
                final PackagePost postCode = lineParser.parsePostCodeLine(line);
                if (postCode != null) {
                    packagePostDeliveries.add(postCode);
                } else {
                    throw new UploadFileException(String.format("The file '%s' was not uploaded due to some parse issue.", path));
                }
            }
            return packagePostDeliveries;
        }
    }

    public List<Fee> loadFileFeeContent(String path) throws IOException, UploadFileException {
        try (val linesStream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            val lines = linesStream.collect(Collectors.toUnmodifiableList());

            List<Fee> fees = new ArrayList<>();
            for (val line : lines) {
                final Fee fee = lineParser.parseFeeLine(line);
                if (fee != null) {
                    fees.add(fee);
                } else {
                    throw new UploadFileException(String.format("The file '%s' was not uploaded due to some parse issue.", path));
                }
            }
            return fees;
        }
    }


}
