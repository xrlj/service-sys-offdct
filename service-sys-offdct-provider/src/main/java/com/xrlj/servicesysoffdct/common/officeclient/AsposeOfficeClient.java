package com.xrlj.servicesysoffdct.common.officeclient;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import com.xrlj.framework.spring.Base;
import com.xrlj.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

@Slf4j
@Component
class AsposeOfficeClient extends Base implements OfficeClient {

    @Override
    public OfficeClientType getOfficeClientType() {
        return OfficeClientType.ASPOSE;
    }

    @Override
    public File docToPdf(String docPath) {
        try {
            Document document;
            if (docPath.startsWith("http")) {
                InputStream i = getNetFileAsStream(docPath);
                document = new Document(i);
            } else {
                document = new Document(docPath);
            }
            //保存结果文件到本地
            String pdfFilePath = getAppTmpFileDir().concat(StringUtil.getUUID()).concat(".pdf");
            File pdfFile = new File(pdfFilePath);
            document.save(pdfFilePath, SaveFormat.PDF);
            return pdfFile;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public File genDocByTemplate(String docTemplatePath, Map data) {
        return null;
    }
}
