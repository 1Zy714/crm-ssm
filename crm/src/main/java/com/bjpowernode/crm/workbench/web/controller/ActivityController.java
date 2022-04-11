package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.commons.constants.Constants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.commons.utils.HSSFUtils;
import com.bjpowernode.crm.commons.utils.UUIDUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.service.ActivityService;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.rtf.RTFEditorKit;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/workbench/activity")
public class ActivityController {
    @Resource
    private UserService userService;
    @Resource
    private ActivityService activityService;

    @RequestMapping("index.do")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("users", userService.queryAllUsers());
        mv.setViewName("/workbench/activity/index");
        return mv;
    }

    @RequestMapping("save.do")
    @ResponseBody
    public Object save(Activity a, HttpSession session) {
        //添加createBy createTime，id
        User createUser = (User) session.getAttribute(Constants.SESSION_USER);
        a.setId(UUIDUtils.getUUID());
        a.setCreateTime(DateUtils.formatDateTime(new Date()));
        a.setCreateBy(createUser.getId());
        ReturnObject ro = new ReturnObject();
        try {
            int count = activityService.saveCreateActivity(a);
            if (count > 0) {
                ro.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                ro.setCode(Constants.RETURN_OBJECT_CODE_FAILED);
                ro.setMsg("插入失败");
            }
        } catch (Exception e) {
            ro.setCode(Constants.RETURN_OBJECT_CODE_FAILED);
            ro.setMsg("插入失败");
        }
        return ro;

    }

    @RequestMapping("pageList.do")
    @ResponseBody
    public Object queryActivityByConditionForPage(String name, String owner, String startDate,
                                                  String endDate, int pageNo, int pageSize) {
        //将传递过来的数据封装map
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("owner", owner);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("beginNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);
        //调用service方法
        List<Activity> activityList = activityService.queryActivityList(map);
        System.out.println(activityList);

        int total = activityService.selectTotalOfActivityByCondition(map);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalRows", total);
        resultMap.put("activityList", activityList);
        return resultMap;

    }
    @RequestMapping("delete.do")
    @ResponseBody
    public Object deleteActivityByIds(String[] id) {
        ReturnObject ro = new ReturnObject();
        try {
            int count = activityService.deleteActivityByIds(id);
            if (count == id.length)
                ro.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            else {
                ro.setCode(Constants.RETURN_OBJECT_CODE_FAILED);
                ro.setCode("删除失败");
            }
        } catch (Exception e) {
            ro.setCode(Constants.RETURN_OBJECT_CODE_FAILED);
            ro.setCode("删除失败");
        }
        return ro;
    }
    @RequestMapping("edit.do")
    @ResponseBody
    public Object selectActivityById(String id){
        Activity activity = activityService.selectActivityById(id);
        return activity;
    }
    @RequestMapping("update.do")
    public @ResponseBody Object updateActivity(HttpSession session,Activity activity){
        String editTime = DateUtils.formatDateTime(new Date());
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        String editBy = user.getId();
        activity.setEditTime(editTime);
        activity.setEditBy(editBy);
        //System.out.println(activity);
        ReturnObject ro = new ReturnObject();
        try {
            int count = activityService.updateActivity(activity);
            System.out.println(count);
            if(count == 1)
                ro.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            else {
                ro.setCode(Constants.RETURN_OBJECT_CODE_FAILED);
                ro.setMsg("更新数据库失败");
            }
        }catch (Exception e){
            ro.setCode(Constants.RETURN_OBJECT_CODE_FAILED);
            ro.setMsg(e.getMessage());
        }
        return ro;

    }
    @RequestMapping("download.do")
    //框架不适合返回文件，所以通过response返回
    public void download(HttpServletResponse response) throws IOException {
        //设置相应类型为excel文件
        response.setContentType("application/octet-stream");
        //浏览器接收到响应信息后，默认在浏览器直接打开，还会调用应用程序，是在打不开才会下载
        //所以我们需要设置响应头信息，使浏览器接收到响应后直接进行下载
        response.addHeader("Content-Disposition","attachment;filename=user.xls");
        //通过输出流将文件写出
        OutputStream out = response.getOutputStream();
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
        wb.write(out);
        wb.close();
        //out由tomcat自动关闭
        out.flush();

    }
    @RequestMapping("export.do")
    public void exportAllActivities(HttpServletResponse response) throws IOException {

        //通过业务层获取列表
        List<Activity> list = activityService.selectActivities();
        //床架excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //在对应的文件创建页
        HSSFSheet sheet = wb.createSheet("activities");
        //对应页创建行
        HSSFRow row = sheet.createRow(0);//行号，从0开始，依次递增
        //行中创建列
        /*
        * 创建表头
        * */
        HSSFCell cell = row.createCell(0);//列号，从0开始
            cell.setCellValue(" id");
        cell=row.createCell(1);
            cell.setCellValue(" owner");
        cell=row.createCell(2);
            cell.setCellValue(" name");
        cell=row.createCell(3);
            cell.setCellValue(" start_date");
        cell=row.createCell(4);
            cell.setCellValue(" end_date");
        cell=row.createCell(5);
            cell.setCellValue("  cost");
        cell=row.createCell(6);
            cell.setCellValue(" description ");
        cell=row.createCell(7);
            cell.setCellValue(" create_time");
        cell=row.createCell(8);
            cell.setCellValue(" create_by");
        cell=row.createCell(9);
            cell.setCellValue(" edit_time");
        cell=row.createCell(10);
            cell.setCellValue(" edit_by");
        //判断list是否为空
        if(list!=null&&list.size()>0){
        //遍历list集合，将数据写入
        Activity activity = null;
        /*
        * 输入数据
        * */
        for(int i=0;i<list.size();i++) {
            activity = list.get(i);
            //对应页创建行
            row = sheet.createRow(i+1);//行号，从0开始，依次递增
            //行中创建列
            cell = row.createCell(0);//列号，从0开始
            cell.setCellValue(activity.getId());
            cell = row.createCell(1);
            cell.setCellValue(activity.getOwner());
            cell = row.createCell(2);
            cell.setCellValue(activity.getName());
            cell = row.createCell(3);
            cell.setCellValue(activity.getStartDate());
            cell = row.createCell(4);
            cell.setCellValue(activity.getEndDate());
            cell = row.createCell(5);
            cell.setCellValue(activity.getCost());
            cell = row.createCell(6);
            cell.setCellValue(activity.getDescription());
            cell = row.createCell(7);
            cell.setCellValue(activity.getCreateTime());
            cell = row.createCell(8);
            cell.setCellValue(activity.getCreateBy());
            cell = row.createCell(9);
            cell.setCellValue(activity.getCreateTime());
            cell = row.createCell(10);
            cell.setCellValue(activity.getCreateBy());
        }
        }
      /*  //将生成的xls文件,导出
        OutputStream os = new FileOutputStream("C:\\Users\\player\\Desktop\\感恩有你\\SSM框架\\SSM\\src\\activity.xls");
        wb.write(os);
        wb.close();
        os.close();

        //读excel文件
        /*
        * 通过输入流读取文件再写道输出流
        *
        InputStream is = new FileInputStream("C:\\Users\\player\\Desktop\\感恩有你\\SSM框架\\SSM\\src\\activity.xls");
        byte[] buff = new byte[256];
        int len=0;
        while((len=is.read(buff))!=-1){
            out.write(buff,0,len);
        }*/
        Date date = new Date();
        long start = date.getTime();
        response.setContentType("application/octet-stream");
        //浏览器接收到响应信息后，默认在浏览器直接打开，还会调用应用程序，是在打不开才会下载
        //所以我们需要设置响应头信息，使浏览器接收到响应后直接进行下载
        response.addHeader("Content-Disposition","attachment;filename=user.xls");
        //通过输出流将文件写出
        OutputStream out = response.getOutputStream();
        wb.write(out);
        long end = new Date().getTime();
        System.out.println("开始时间:"+start);
        System.out.println("结束时间:"+end);
        System.out.println("花费的时间："+(end-start)+"ms");


        wb.close();
        out.flush();


    }

    /**
     * 文件上传测试
     * 需要配置springmvc文件上传解析器
     * */
    @RequestMapping("upload.do")
    public void upload(String userName, MultipartFile myFile) throws IOException {
        //文本数据打印
       /* System.out.println("username:"+userName);
        //将文件内容读出
        File file = new File("C:\\Users\\player\\Desktop\\感恩有你\\SSM框架\\SSM\\src\\",myFile.getName()+myFile.getOriginalFilename());
       myFile.transferTo(file);
       byte[] bytes = myFile.getBytes();
       for(byte b:bytes){
           System.out.print(b+" ");*/
        HSSFWorkbook wb = new HSSFWorkbook(myFile.getInputStream());
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = null;
        HSSFCell cell = null;
        for(int i = 0;i<sheet.getLastRowNum()+1;i++){
            row = sheet.getRow(i);
            for(int j=0;j<row.getLastCellNum();j++){
                cell = row.getCell(j);
               System.out.println(HSSFUtils.getCellValueForStr(cell)+" ");
            }
            //每一行所有列都打完了，执行换行
            System.out.println();
        }
    }
    /**
     * 上传excel文件并处理
     * */
    @RequestMapping("importActivity.do")
    @ResponseBody
    public Object importActivity(MultipartFile activityFile,HttpSession session){
        HSSFWorkbook wb = null;
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        String owner = user.getId();
        ReturnObject ro = new ReturnObject();
        try {
            wb = new HSSFWorkbook(activityFile.getInputStream());
            //上传文件只有1页，如果多页则需要多加一个循环
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row = null;
            HSSFCell cell = null;
            //第一行是属性名，无需解析
            Activity activity  = null;
            List<Activity> activityList = new ArrayList<>();
            for(int i = 1;i<sheet.getLastRowNum()+1;i++){
                //每一行对应一个市场活动记录
                activity = new Activity();
                activity.setId(UUIDUtils.getUUID());
                activity.setOwner(owner);
                activity.setCreateTime(DateUtils.formatDateTime(new Date()));
                activity.setCreateBy(owner);
                row = sheet.getRow(i);
                for(int j=0;j<row.getLastCellNum();j++){
                    cell = row.getCell(j);
                    String value = HSSFUtils.getCellValueForStr(cell);
                    switch (j){
                        case 0:
                            activity.setName(value);
                            break;
                        case 1:
                            activity.setStartDate(value);
                            break;
                        case 2:
                            activity.setEndDate(value);
                            break;
                        case 3:
                            activity.setCost(value);
                            break;
                        case 4:
                            activity.setDescription(value);
                            break;
                        default:
                            break;
                    }
                }
                activityList.add(activity);

            }
                //向业务层发起请求
            int flag = activityService.saveActivityByList(activityList);
            //判断是否插入成功
            ro.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            ro.setRetData(flag);
        } catch (IOException e) {
            ro.setCode(Constants.RETURN_OBJECT_CODE_FAILED);
            ro.setMsg("导入失败");
        }
        return ro;


    }

}
