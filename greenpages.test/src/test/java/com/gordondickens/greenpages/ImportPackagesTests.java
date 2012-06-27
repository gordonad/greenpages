package com.gordondickens.greenpages;

import org.eclipse.virgo.util.osgi.manifest.parse.HeaderDeclaration;
import org.eclipse.virgo.util.osgi.manifest.parse.ParserLogger;
import org.eclipse.virgo.util.osgi.manifest.parse.standard.StandardHeaderParser;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class ImportPackagesTests {
    private static final Logger logger = LoggerFactory.getLogger(ImportPackagesTests.class);

    private StandardHeaderParser parser = new StandardHeaderParser(new SimpleParserLogger());

    @Test
    public void defaults() {

        List<HeaderDeclaration> list = parser
                .parsePackageHeader("a.b.c.d;e.f.g.h;version=1.2.3;uses:=a.b.c", "a");
        logger.debug("LIST '{}'", list);

    }


    class SimpleParserLogger implements ParserLogger {
        private final Logger logger = LoggerFactory.getLogger(SimpleParserLogger.class);

        public String[] errorReports() {
            return null;
        }

        public void outputErrorMsg(Exception re, String item) {
            logger.debug("Error: {}", item);
        }
    }
}
