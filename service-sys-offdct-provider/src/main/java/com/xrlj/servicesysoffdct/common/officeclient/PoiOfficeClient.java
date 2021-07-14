package com.xrlj.servicesysoffdct.common.officeclient;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import com.xrlj.framework.spring.Base;
import com.xrlj.servicesysoffdct.common.ImgType;
import com.xrlj.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
class PoiOfficeClient extends Base implements OfficeClient {

    @Override
    public OfficeClientType getOfficeClientType() {
        return OfficeClientType.POI;
    }

    @Override
    public File docToPdf(String docPath) {
        return null;
    }

    @Override
    public File genDocByTemplate(String docTemplatePath, Map data) {
        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();
        Configure config = Configure.builder()
                .bind("list1", policy)
                .bind("list2", policy)
                .bind("list3", policy)
                .build();
        XWPFTemplate template;
        if (docTemplatePath.startsWith("http")) {
            template = XWPFTemplate.compile(getNetFileAsStream(docTemplatePath), config).render(data);
        } else {
            template = XWPFTemplate.compile(docTemplatePath, config).render(data);
        }

        File docFile = null;
        try {
            String docPath = getAppTmpFileDir().concat(StringUtil.getUUID()).concat(".docx"); //生成后docx路径
            docFile = new File(docPath);
            template.writeAndClose(new FileOutputStream(docPath));
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return docFile;
    }

    @Override
    public List<File> pdfToImg(String pdfPath, Integer page, Integer resolution, ImgType imgType) {
        return null;
    }
}
