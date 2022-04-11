import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.junit.Test;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyTest {
    /*
    * 使用apache-poi生成excel文件
    * */
    @Test
    public void textPoi() throws IOException {
        //创建HSSFWorkbook文件对应一个excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //在对应的文件创建页
        HSSFSheet sheet = wb.createSheet("user");
        //对应页创建行
        HSSFRow row = sheet.createRow(0);//行号，从0开始，依次递增
        HSSFRow row1 = sheet.createRow(1);
        //行中创建列
        HSSFCell cell = row.createCell(0);//列号，从0开始
        //设置样式,创建HSSFCellStyle
        HSSFCellStyle style = wb.createCellStyle();
        //居中
        style.setAlignment(HorizontalAlignment.CENTER);
        cell.setCellStyle(style);
        cell.setCellValue("学号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("班别");

        row1.createCell(0).setCellValue("3119005194");
        row1.createCell(1).setCellValue("廖振宇");
        row1.createCell(2).setCellValue("19软工5");
        //调用工具函数生成excel文件
        FileOutputStream os = new FileOutputStream("C:\\Users\\player\\Desktop\\感恩有你\\SSM框架\\SSM\\src\\user.xls");
        wb.write(os);
        //关闭资源
        os.close();
        wb.close();

    }
}
