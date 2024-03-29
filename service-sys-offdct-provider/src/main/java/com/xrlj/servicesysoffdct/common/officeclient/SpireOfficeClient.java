package com.xrlj.servicesysoffdct.common.officeclient;

import com.xrlj.framework.spring.Base;
import com.xrlj.servicesysoffdct.common.ImgType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
class SpireOfficeClient extends Base implements OfficeClient {

    @Override
    public OfficeClientType getOfficeClientType() {
        return OfficeClientType.SPIRE;
    }

    @Override
    public File docToPdf(String docPath) {
        try {
            /*Document document = null;
            if (docPath.startsWith("http")) {
                InputStream i = getNetFileAsStream(docPath);
                document.loadFromStream(i, FileFormat.Doc);
            } else {
                document.loadFromFile(docPath);
            }
            //保存结果文件到本地
            String pdfFilePath = getAppTmpFileDir().concat(StringUtil.getUUID()).concat(".pdf");
            File pdfFile = new File(pdfFilePath);
            document.saveToFile(pdfFilePath, FileFormat.PDF);
            return pdfFile;*/

            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public File genDocByTemplate(String docTemplatePath, Map data) {
        return null;
    }

    @Override
    public List<File> pdfToImg(String pdfPath, Integer page, Integer resolution, ImgType imgType) {
        return null;
    }
}
