// 自动生成。手动添加泛型实体。
package com.xrlj.servicesysoffdct.service.impl;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.IPdfTextLocation;
import com.itextpdf.kernel.pdf.canvas.parser.listener.RegexBasedLocationExtractionStrategy;
import com.xrlj.framework.base.BaseServiceImpl;
import com.xrlj.framework.feign.SysFilesClient;
import com.xrlj.framework.feign.vo.VFileUploadReq;
import com.xrlj.framework.feign.vo.VSysFileResp;
import com.xrlj.framework.spring.mvc.api.APIs;
import com.xrlj.servicesysoffdct.common.officeclient.OfficeClientFactory;
import com.xrlj.servicesysoffdct.common.officeclient.OfficeClientType;
import com.xrlj.servicesysoffdct.service.PdfWorkerService;
import com.xrlj.utils.security.Base64Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * 接口实现
 */
@Slf4j
@Service
@RefreshScope
public class PdfWorkerServiceImpl extends BaseServiceImpl implements PdfWorkerService {

    private final SysFilesClient filesystemClient;
    private final OfficeClientFactory officeClientFactory;

    public PdfWorkerServiceImpl(SysFilesClient filesystemClient, OfficeClientFactory officeClientFactory) {
        this.filesystemClient = filesystemClient;
        this.officeClientFactory = officeClientFactory;
    }

    @Override
    public VSysFileResp pdfToWork(String pdfPath, String fileOriName) {
        return null;
    }

    @Override
    public VSysFileResp htmlTemplateToPdf(String htmlTemplateUrl, Map data, String fileOriName) {
        return null;
    }

    @Override
    public VSysFileResp ftlTemplateToPdf(String ftlTemplateUrl, Map data, String fileOriName) {
        return null;
    }

    @Override
    public Serializable getKeyCoordinate(String key) {
        PdfReader reader = null;
        PdfDocument pdfDocument = null;
        try {
            reader = new PdfReader("");
            pdfDocument = new PdfDocument(reader);
            int pages = pdfDocument.getNumberOfPages();
            for (int i = 1; i <= pages; i++) {
                PdfPage page = pdfDocument.getPage(i);

                RegexBasedLocationExtractionStrategy strategy = new RegexBasedLocationExtractionStrategy(key);
                PdfCanvasProcessor canvasProcessor = new PdfCanvasProcessor(strategy);
                canvasProcessor.processPageContent(page);
                Collection<IPdfTextLocation> resultantLocations = strategy.getResultantLocations();

                if (resultantLocations.isEmpty()) {
                    break;
                }

                for (IPdfTextLocation location : resultantLocations) {
                    Rectangle boundRectangle = location.getRectangle();
                    System.out.println(location.getText());
                    System.out.println("["+key + "] location of x: " + boundRectangle.getX() + "  ,y: " + boundRectangle.getY());
                }
            }
            reader.close();
            pdfDocument.close();
        } catch (IOException e) {
            log.error("pdf查询关键字坐标异常", e.getMessage());
        }

        return null;
    }
}
