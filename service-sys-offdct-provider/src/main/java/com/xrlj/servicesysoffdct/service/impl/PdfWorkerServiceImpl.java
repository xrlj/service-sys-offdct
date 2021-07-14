// 自动生成。手动添加泛型实体。
package com.xrlj.servicesysoffdct.service.impl;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.parser.PdfDocumentContentParser;
import com.itextpdf.kernel.pdf.canvas.parser.listener.IPdfTextLocation;
import com.itextpdf.kernel.pdf.canvas.parser.listener.RegexBasedLocationExtractionStrategy;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Image;
import com.xrlj.framework.base.BaseServiceImpl;
import com.xrlj.framework.feign.SysFilesClient;
import com.xrlj.framework.feign.vo.VSysFileResp;
import com.xrlj.framework.spring.mvc.api.APIs;
import com.xrlj.infrastructure.APIsAssert;
import com.xrlj.servicesysoffdct.api.vo.VPdfKeyCoordinateResp;
import com.xrlj.servicesysoffdct.api.vo.req.VPdfKeyCoordinateReq;
import com.xrlj.servicesysoffdct.common.ImgType;
import com.xrlj.servicesysoffdct.common.UploadFileUtils;
import com.xrlj.servicesysoffdct.common.officeclient.OfficeClientFactory;
import com.xrlj.servicesysoffdct.common.officeclient.OfficeClientType;
import com.xrlj.servicesysoffdct.service.PdfWorkerService;
import com.xrlj.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 接口实现
 */
@Slf4j
@Service
@RefreshScope
public class PdfWorkerServiceImpl extends BaseServiceImpl implements PdfWorkerService {

    @Value("${office.client.platform.pdf-to-img}")
    private String officeClientPlatformPdfToImg;

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
    public List<VPdfKeyCoordinateResp> getKeyCoordinate(VPdfKeyCoordinateReq vo) {
        APIsAssert.fieldNotNull(vo.getPdfPath(), "pdfPath");
        APIsAssert.fieldNotNull(vo.getKeys(), "keys");

        List<VPdfKeyCoordinateResp> respList = new ArrayList<>();

        PdfReader reader = null;
        PdfDocument pdfDocument = null;
        String pdfLocalPath = null;
        try {
            if (vo.getPdfPath().startsWith("http")) {
                pdfLocalPath = getFileFromFileSystem(vo.getPdfPath());
                reader = new PdfReader(pdfLocalPath);
            } else {
                reader = new PdfReader(vo.getPdfPath());
            }
            pdfDocument = new PdfDocument(reader);
            PdfDocumentContentParser pdcp = new PdfDocumentContentParser(pdfDocument);
            int pages = pdfDocument.getNumberOfPages();

            List<String> keysList = StringUtil.splitStrToList(vo.getKeys(), ",");
            for (String key : keysList) {
                VPdfKeyCoordinateResp vPdfKeyCoordinateResp = new VPdfKeyCoordinateResp();
                vPdfKeyCoordinateResp.setKeyword(key);
                List<VPdfKeyCoordinateResp.PositionListDTO> positionListDTOList = new ArrayList<>();
                vPdfKeyCoordinateResp.setPositionList(positionListDTOList);

                for (int i = 1; i <= pages; i++) {
                    RegexBasedLocationExtractionStrategy strategy = new RegexBasedLocationExtractionStrategy(key);
                    RegexBasedLocationExtractionStrategy regexStrategy = pdcp.processContent(i, strategy);
                    Collection<IPdfTextLocation> resultantLocations = regexStrategy.getResultantLocations();

                    VPdfKeyCoordinateResp.PositionListDTO positionListDTO = new VPdfKeyCoordinateResp.PositionListDTO(); //每页信息
                    positionListDTO.setPageIndex(i);
                    List<VPdfKeyCoordinateResp.PositionListDTO.CoordinateListDTO> coordinateListDTOList = new ArrayList<>();
                    positionListDTO.setCoordinateList(coordinateListDTOList);

                    for (IPdfTextLocation location : resultantLocations) {
                        Rectangle boundRectangle = location.getRectangle();

                        VPdfKeyCoordinateResp.PositionListDTO.CoordinateListDTO coordinateListDTO = new VPdfKeyCoordinateResp.PositionListDTO.CoordinateListDTO(); //x,y坐标信息
                        coordinateListDTO.setPosx(boundRectangle.getX());
                        coordinateListDTO.setPosy(boundRectangle.getY());

                        coordinateListDTOList.add(coordinateListDTO);
                    }

                    if (!coordinateListDTOList.isEmpty()) {
                        positionListDTOList.add(positionListDTO);
                    }
                }

                respList.add(vPdfKeyCoordinateResp);
            }
        } catch (IOException e) {
            log.error("pdf查询关键字坐标异常", e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (pdfDocument != null) {
                pdfDocument.close();
            }
            if (pdfLocalPath != null) {
                File f = new File(pdfLocalPath);
                f.delete();
            }
        }

        return respList;
    }

    @Override
    public VSysFileResp imgToPdf(String imgPath, String fileOriName) {
        File pdfFile = null;
        try {
            APIsAssert.fieldNotNull(imgPath, "imgPath");
            APIsAssert.fieldNotNull(fileOriName, "fileOriName");

            String ets = FilenameUtils.getExtension(imgPath);
            if (!ets.startsWith("jpg") || !ets.startsWith("png")) {
                throw APIs.error(1001, "图片格式为jpg或png", null);
            }

            // file:///C:/Users/Administrator/Pictures/aa.png
            byte[] imgBytes = IOUtils.toByteArray(new URI(imgPath));
            byte[] pdfBytes = convertImageBytesToPdfBytes(imgBytes);
            pdfFile = new File(getAppTmpFileDir().concat(StringUtil.getUUID()).concat(".pdf"));
            FileUtils.writeByteArrayToFile(pdfFile, pdfBytes);

            return UploadFileUtils.uploadFile(pdfFile.getPath(), fileOriName, filesystemClient);
        } catch (Exception e) {
            throw APIs.error(500, "图片转pdf异常", null);
        } finally {
            if (pdfFile != null) {
                pdfFile.delete();
            }
        }
    }

    @Override
    public List<VSysFileResp> pdfToImg(String pdfPath, Integer page, ImgType imgType, String fileOriName) {
        List<VSysFileResp> respList = new ArrayList<>();
        List<File> fileList = null;
        try {
            APIsAssert.fieldNotNull(pdfPath, "pdfPath");
            String extName = FilenameUtils.getExtension(pdfPath);
            if (!extName.startsWith("pdf")) {
                throw APIs.error(1001, "文件后缀错误，必须pdf文件哦", null);
            }
            fileList = officeClientFactory.getOfficeClient(OfficeClientType.valueOf(officeClientPlatformPdfToImg)).pdfToImg(pdfPath, page, 300, imgType);
            if (fileList == null || fileList.isEmpty()) {
                throw APIs.error(1002, "转换错误，没有数据", null);
            }
            if (fileList.size() == 1) {
                File file = fileList.get(0);
                respList.add(UploadFileUtils.uploadFile(file.getPath(), fileOriName, filesystemClient));
            } else {
                for (int i = 0; i < fileList.size(); i++) {
                    File f = fileList.get(i);
                    respList.add(UploadFileUtils.uploadFile(f.getPath(), FilenameUtils.getBaseName(fileOriName).concat("-" + i), filesystemClient));
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw APIs.error(1003, "转换异常", null);
        } finally {
            if (fileList != null) {
                for (File file: fileList) {
                    if (file != null && file.isFile()) {
                        file.delete();
                    }
                }
            }
        }
        return respList;
    }

    /**
     * 图片文件转成pdf。
     *
     * @param imgBytes 图片字节
     * @return pdf字节
     */
    private static byte[] convertImageBytesToPdfBytes(byte[] imgBytes) {
        ByteArrayOutputStream returnSM = new ByteArrayOutputStream();
        Document document = null;
        PdfWriter writer = null;
        try {
            writer = new PdfWriter(returnSM);
            // Creating a PdfDocument
            PdfDocument pdfDoc = new PdfDocument(writer);
            //PdfDocument doc = new PdfDocument(new PdfReader(src), new PdfWriter(returnSM));
            // Creating an ImageData object
            ImageData data = ImageDataFactory.create(imgBytes);
            // Creating an Image object
            Image img = new Image(data);
            img.setBorder(Border.NO_BORDER);
            float imgWidth = img.getImageWidth();
            float imgHeight = img.getImageHeight();
            img.setFixedPosition(0, 0);
            Rectangle rect = new Rectangle(imgWidth, imgHeight);
            PageSize pagesize = new PageSize(rect);
            pdfDoc.setDefaultPageSize(pagesize);
            // Creating a Document Document
            document = new Document(pdfDoc, pagesize);
            document.setMargins(0, 0, 0, 0);
            // Adding image to the document
            document.add(img);
            // Closing the document
            document.close();
            writer.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (document != null) {
                document.close();
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return returnSM.toByteArray();
    }
}
