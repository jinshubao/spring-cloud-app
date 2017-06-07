package com.jean.cloud.server.api;

import io.github.swagger2markup.Swagger2MarkupConverter;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by jinshubao on 2017/6/7.
 */
public class ApiControllerTest {


    @Test
    public void userDetail() throws Exception {
        URL remoteSwaggerFile = new URL("http://petstore.swagger.io/v2/swagger.json");
        Path outputDirectory = Paths.get("doc/gen");

        Swagger2MarkupConverter.from(remoteSwaggerFile)
                .build()
                .toFolder(outputDirectory);

    }

}