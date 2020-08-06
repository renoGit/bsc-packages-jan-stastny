package com.prorocketeers.bscpackagesjanstastny.parser;

import com.prorocketeers.bscpackagesjanstastny.domain.Fee;
import com.prorocketeers.bscpackagesjanstastny.domain.PackagePost;

public interface ILineParser {

    PackagePost parsePostCodeLine(String line);

    Fee parseFeeLine(String line);

}
