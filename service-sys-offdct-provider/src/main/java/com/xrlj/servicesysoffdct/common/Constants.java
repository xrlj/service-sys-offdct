package com.xrlj.servicesysoffdct.common;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;

public final class Constants {

    public interface RedisKayGen {

        String MY_JWT = "my:jwt:";
    }

    public static String getMuPDFToolsPath() {
        String exePath = FileUtils.getUserDirectoryPath().concat(File.separator).concat(".xrlj").concat(File.separator).concat(SystemUtils.IS_OS_LINUX ? "mutool" : "mutool.exe");
        return exePath;
    }
}
