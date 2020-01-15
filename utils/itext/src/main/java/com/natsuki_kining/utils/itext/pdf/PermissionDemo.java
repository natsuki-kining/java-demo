package com.natsuki_kining.utils.itext.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * pdf 设置权限
 *
 * @Author natsuki_kining
 * @Date 2020/1/13 10:12
 **/
public class PermissionDemo {

    public static void readOnly() throws DocumentException, IOException {
        // 创建文件
        Document document = new Document();
        // 建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E:\\temp\\readOnly.pdf"));

        // 只读权限
        writer.setEncryption("".getBytes(), "".getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);

        // 打开文件
        document.open();

        // 添加内容
        document.add(new Paragraph("read only."));

        // 关闭文档
        document.close();
        // 关闭书写器
        writer.close();
    }

    public static void reader() throws DocumentException, IOException {

        //读取pdf文件
        PdfReader pdfReader = new PdfReader("E:\\temp\\image.pdf");

        //修改器
        PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("E:\\temp\\reader.pdf"));

        Image image = Image.getInstance("E:\\temp\\image.jpg");
        image.scaleAbsolute(50, 50);
        image.setAbsolutePosition(0, 700);

        for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
            PdfContentByte content = pdfStamper.getUnderContent(i);
            content.addImage(image);
        }

        pdfStamper.close();
    }

    public static void main(String[] args) throws IOException, DocumentException {
        readOnly();
        reader();
    }
}
