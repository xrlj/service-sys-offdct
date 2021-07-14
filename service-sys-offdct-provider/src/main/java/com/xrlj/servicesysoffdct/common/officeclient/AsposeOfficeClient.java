package com.xrlj.servicesysoffdct.common.officeclient;

import com.aspose.pdf.devices.JpegDevice;
import com.aspose.pdf.devices.PngDevice;
import com.aspose.pdf.devices.Resolution;
import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import com.xrlj.framework.spring.Base;
import com.xrlj.servicesysoffdct.common.ImgType;
import com.xrlj.utils.FileUtil;
import com.xrlj.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URI;
import java.nio.file.Paths;
import java.util.*;

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

    @Override
    public List<File> pdfToImg(String pdfPath, Integer page, Integer resolution, ImgType imgType) throws Exception {
        Objects.requireNonNull(pdfPath);
        Objects.requireNonNull(imgType);
        List<File> imgFileList;
        if (page == null) { //转所有页码
            imgFileList = convertPDFtoImgAllPages(pdfPath, resolution, imgType);
        } else {
            imgFileList = convertPDFtoImgSinglePage(pdfPath, page, resolution, imgType);
        }
        return imgFileList;
    }


    private List<File> convertPDFtoImgSinglePage(String pdfPath, Integer page, Integer rlt, ImgType imgType) {
        String imgPath = getTmpDirPath().concat(StringUtil.getUUID());
        if (imgType.equals(ImgType.PNG)) {
            imgPath.concat(".png");
        }
        if (imgType.equals(ImgType.JPEG)) {
            imgPath.concat(".jpg");
        }
        File imgFile = new File(imgPath);

        page = (page == null || page.intValue() == 0) ? 1 : page.intValue();

        try(java.io.OutputStream imageStream = new java.io.FileOutputStream(imgPath)) {
            byte[] pdfFileBytes = IOUtils.toByteArray(new URI(pdfPath));
            InputStream i = new ByteArrayInputStream(pdfFileBytes);
            com.aspose.pdf.Document pdfDocument = new com.aspose.pdf.Document(i);

            // 设置图片清晰度
            Resolution resolution = new Resolution(rlt == null ? 300 : rlt.intValue());

            if (imgType.equals(ImgType.PNG)) {
                // Create PngDevice object with particular resolution
                PngDevice PngDevice = new PngDevice(resolution);

                // Convert a particular page and save the image to stream
                PngDevice.process(pdfDocument.getPages().get_Item(page), imageStream);
            }
            if (imgType.equals(ImgType.JPEG)) {
                // Create JpegDevice object with particular resolution
                JpegDevice JpegDevice = new JpegDevice(resolution);

                // Convert a particular page and save the image to stream
                JpegDevice.process(pdfDocument.getPages().get_Item(page), imageStream);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        List<File> fileList = Collections.emptyList();
        if (imgFile.isFile() && imgFile.length() > 0) {
            fileList.add(imgFile);
        }

        return fileList;
    }


    private List<File> convertPDFtoImgAllPages(String pdfPath, Integer rlt, ImgType imgType) throws Exception {
        byte[] pdfFileBytes = IOUtils.toByteArray(new URI(pdfPath));
        InputStream i = new ByteArrayInputStream(pdfFileBytes);
        com.aspose.pdf.Document pdfDocument = new com.aspose.pdf.Document(i);

        // Loop through all the pages of PDF file
        List<File> fileList = new ArrayList<>();
        for (int pageCount = 1; pageCount <= pdfDocument.getPages().size(); pageCount++) {
            // Create stream object to save the output image
            String imgPath = getTmpDirPath().concat(StringUtil.getUUID());
            if (imgType.equals(ImgType.JPEG)) {
                imgPath.concat(".jpg");
            }
            if (imgType.equals(ImgType.PNG)) {
                imgPath.concat(".png");
            }
            File imgFile = new File(imgPath);

            java.io.OutputStream imageStream = new java.io.FileOutputStream(imgFile);

            // Create Resolution object
            Resolution resolution = new Resolution(rlt == null ? 300 : rlt.intValue());

            if (imgType.equals(ImgType.JPEG)) {
                // Create JpegDevice object with particular resolution
                JpegDevice JpegDevice = new JpegDevice(resolution);

                // Convert a particular page and save the image to stream
                JpegDevice.process(pdfDocument.getPages().get_Item(pageCount), imageStream);
            }
            if (imgType.equals(ImgType.PNG)) {
                // Create PngDevice object with particular resolution
                PngDevice PngDevice = new PngDevice(resolution);

                // Convert a particular page and save the image to stream
                PngDevice.process(pdfDocument.getPages().get_Item(pageCount), imageStream);
            }

            // Close the stream
            imageStream.close();

            if (imgFile.isFile() && imgFile.length() > 0) {
                fileList.add(imgFile);
            }
        }

        return fileList;
    }

}
