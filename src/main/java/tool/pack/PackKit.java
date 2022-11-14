package tool.pack;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * 描述: 打包工具类
 * 作者: yangzhao
 * 日期: 2021年04月27日 14:05
 */
public class PackKit {

    public static String PACKAGE_NAME;

    // hrp物资
    public static String CLASS_HOME_PATH;
    public static String PROJECT_HOME_PATH;
    public static String OUTPUT_HOME_PATH;
    public static String CUSTOM_REPLACE_TEXT;


    /**
     * 描述: 查找java文件所对应的类文件目录
     * 作者: yangzhao
     * 日期: 2021年05月08日 17:16
     */
    public static void findClass(String bitBucketRespText){
        List<Map<String, Object>> paths = parseBitBucketText(bitBucketRespText);

        for(Map<String, Object> path: paths){
            List<Map<String,String>> filePathList =  getClassFilePath(getFilePath(path));
            for(Map<String,String> filePaths:filePathList){
                String realFilePath = filePaths.get("realFilePath");
                String copyFilePath = filePaths.get("copyFilePath");

                System.out.println("realFilePath:"+realFilePath);
                System.out.println("copyFilePath:"+copyFilePath);
            }
        }
    }

    private static List<Map<String, String>> getClassFilePath(List<String> filePath) {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        String realFilePath = filePath.get(0);
        String copyFilePath = filePath.get(1);
        String relativeFilePath = filePath.get(2);
        //pom文件不处理
        if(realFilePath.endsWith("pom.xml")){
            return result;
        }
        if (realFilePath.endsWith(".class")){
            File realFile = new File(realFilePath);
            File parentDir = realFile.getParentFile();
            if(!realFile.exists()){
                throw new RuntimeException(realFilePath+"文件不存在");
            }

            if(!parentDir.exists()){
                throw new RuntimeException(realFile.getParentFile().getAbsolutePath()+"目录不存在");
            }

            FilenameFilter fileNameFilter = new FilenameFilter() {

                @Override
                public boolean accept(File dir, String name) {
                    String realFileName = realFile.getName();
                    if(name.equals(realFileName)){
                        return true;
                    }
                    if(name.lastIndexOf(".")>0 && name.startsWith(realFileName.split("\\.")[0]+"$"))
                    {
                        return true;
                    }
                    return false;
                }
            };

            File copyFile = new File(copyFilePath);
            String copyFilePathNew = copyFile.getParentFile().getAbsolutePath();
            File[] files = parentDir.listFiles(fileNameFilter);
            for(File file: files){
                //计算相对路径
                String relativeFilePathNew = relativeFilePath.substring(0,relativeFilePath.lastIndexOf("/"));
                relativeFilePathNew += (("/"+file.getName()).replaceAll("\\.class","\\.java"));

                Map<String,String> classFilePaths = new HashMap<>();
                classFilePaths.put("realFilePath",file.getAbsolutePath());
                classFilePaths.put("copyFilePath",copyFilePathNew+"\\"+file.getName());
                classFilePaths.put("relativeFilePath",relativeFilePathNew);
                result.add(classFilePaths);
            }

        }else{
            Map<String,String> classFilePaths = new HashMap<>();
            classFilePaths.put("realFilePath",realFilePath);
            classFilePaths.put("copyFilePath",copyFilePath);
            classFilePaths.put("relativeFilePath",relativeFilePath);
            result.add(classFilePaths);
        }
        return result;
    }

    /**
     * 描述: 解析打包文件列表
     * 作者: yangzhao
     * 日期: 2021年05月08日 17:17
     */
    public static void printFiles(String bitBucketRespText){
        List<Map<String, Object>> paths = parseBitBucketText(bitBucketRespText);
        int index = 0;
        for(Map<String, Object> path:paths){
            String filePath = (String) path.get("toString");
            if(index++==(paths.size()-1)){
                System.out.println(filePath );
            }else {
                System.out.println(filePath + ",");
            }
        }
    }

    /**
     * 描述: 打包
     * 作者: yangzhao
     * 日期: 2021年05月08日 17:17
     */
    public static void packByPaths(List<Map<String, Object>> paths) throws IOException {
        File outputDir = new File(OUTPUT_HOME_PATH);
        if(outputDir.exists()){
            System.out.println("输出目录已存在，删除目录："+OUTPUT_HOME_PATH);
            FileUtils.deleteDirectory(outputDir);
        }

        Set<String> fileList = new HashSet<>();
        int count = 0;
        for(Map<String, Object> path: paths){
            List<Map<String,String>> filePathList =  getClassFilePath(getFilePath(path));
            for(Map<String,String> filePaths:filePathList){
                String realFilePath = filePaths.get("realFilePath");
                String copyFilePath = filePaths.get("copyFilePath");
                String relativeFilePath = filePaths.get("relativeFilePath");
                int n = copyFile(realFilePath,copyFilePath);
                fileList.add(relativeFilePath);
                count+=n;
            }
        }
        String fileListPath = OUTPUT_HOME_PATH+"\\fileList.txt";
        System.out.println("输出文件列表 >> "+fileListPath);
        Files.write(Paths.get(fileListPath), String.join(",",fileList).getBytes());
        System.out.println("打包成功，共计"+count+"个文件");
    }

    /**
     * 描述: 打包
     * 作者: yangzhao
     * 日期: 2021年05月08日 17:17
     */
    public static void pack(String bitBucketRespText) throws IOException {
        List<Map<String, Object>> paths = parseBitBucketText(bitBucketRespText);
        packByPaths(paths);
    }

    /**
     * 描述: 打包
     * 作者: yangzhao
     * 日期: 2021年08月04日 16:49
     */
    public static void pack(List<String> bitBucketRespTexts) throws IOException {
        List<Map<String, Object>> paths = new ArrayList<>();
        for(String bitBucketRespText:bitBucketRespTexts){
            paths.addAll(parseBitBucketText(bitBucketRespText));
        }
        packByPaths(paths);
    }

    enum Config{
        //飞医物资
        FEIYI_HRP("D:\\workspace\\A-project-wuzi\\fywuzi\\out\\artifacts\\fywuzi_Web_exploded",
                "D:\\workspace\\A-project-wuzi\\fywuzi",
                "D:\\打包目录"+PACKAGE_NAME,
               ""),
        //hrp物资
        YXT_HRP("D:\\workspace\\A-project-wuzi-hrp\\yxt\\out\\artifacts\\yxt_war_exploded",
              "D:\\workspace\\A-project-wuzi-hrp\\yxt",
              "D:\\打包目录"+PACKAGE_NAME,
             ""),
        //柳州妇幼物资
        LIUZHOUFUYOU_HRP( "D:\\workspace\\A-project-wuzi-hrp\\yxt_LiuZhouFuYou\\out\\artifacts\\yxt_LiuZhouFuYou_Web_exploded",
                        "D:\\workspace\\A-project-wuzi-hrp\\yxt_LiuZhouFuYou",
                        "D:\\打包目录"+PACKAGE_NAME,
                      ""),
        //供应链
        NETPLAT( "D:\\workspace\\A-project-netplat\\netplat\\classes\\artifacts\\NetPlat_Web_exploded",
                "D:\\workspace\\A-project-netplat\\netplat",
                "D:\\打包目录"+PACKAGE_NAME,
              ""),
        //物资接口项目
        FEIYI_INTERFACE( "D:\\workspace\\A-project-wuzi\\feiyi_passages\\target\\feiyi_interface-0.0.1-SNAPSHOT",
                       "D:\\workspace\\A-project-wuzi\\feiyi_passages",
                       "D:\\打包目录"+PACKAGE_NAME,
                     "main/java/ -->  "),
        //HRP接口项目
        HRP_INTERFACE( "D:\\workspace\\A-project-wuzi-hrp\\feiyi_passages\\target\\feiyi_interface-0.0.1-SNAPSHOT",
                       "D:\\workspace\\A-project-wuzi-hrp\\feiyi_passages",
                       "D:\\打包目录"+PACKAGE_NAME,
                     "main/java/ -->  "),

        //hrp物资2
        YXT_HRP2("D:\\workspace\\A-project-wuzi-hrp\\yxt2\\out\\artifacts\\yxt2_Web_exploded",
                        "D:\\workspace\\A-project-wuzi-hrp\\yxt2",
                        "D:\\打包目录"+PACKAGE_NAME,
             "");

        public final  String CLASS_HOME_PATH;
        public final  String PROJECT_HOME_PATH;
        public final  String OUTPUT_HOME_PATH;
        public final  String CUSTOM_REPLACE_TEXT;

        Config(String CLASS_HOME_PATH, String PROJECT_HOME_PATH, String OUTPUT_HOME_PATH, String CUSTOM_REPLACE_TEXT) {
            this.CLASS_HOME_PATH = CLASS_HOME_PATH;
            this.PROJECT_HOME_PATH = PROJECT_HOME_PATH;
            this.OUTPUT_HOME_PATH = OUTPUT_HOME_PATH;
            this.CUSTOM_REPLACE_TEXT = CUSTOM_REPLACE_TEXT;
        }

    }

    private static void loadConfig(Config cf){
        CLASS_HOME_PATH = cf.CLASS_HOME_PATH;
        PROJECT_HOME_PATH = cf.PROJECT_HOME_PATH;
        OUTPUT_HOME_PATH = cf.OUTPUT_HOME_PATH;
        CUSTOM_REPLACE_TEXT = cf.CUSTOM_REPLACE_TEXT;
    }

    /**
     * 描述: 添加UI菜单
     * 作者: yangzhao
     * 日期: 2021年07月13日 16:01
     */
    public static void menu() throws IOException {
        // 1.打包名称
        System.out.println("请输入打包名称:");
        Scanner sc2 = new Scanner(System.in);
        String name = sc2.nextLine();  //读取字符串型输入
        PACKAGE_NAME = "\\" + name;
        if(StringUtils.isBlank(PACKAGE_NAME)){
            throw new RuntimeException("打包名称为空");
        }
        System.out.println("");

        // 2.打包项目路径
        System.out.println("请选择需要打包的项目:");
        System.out.println("   1.飞医物资");
        System.out.println("   2.HRP物资");
        System.out.println("   3.柳州妇幼物资");
        System.out.println("   4.供应链");
        System.out.println("   5.飞医接口项目");
        System.out.println("   6.HRP接口项目");
        System.out.println("   7.HRP物资2");
        Scanner sc1 = new Scanner(System.in);
        int project = sc1.nextInt();    //读取整型输入

        switch (project){
            case 1:
                loadConfig(Config.FEIYI_HRP);
                break;
            case 2:
                loadConfig(Config.YXT_HRP);
                break;
            case 3:
                loadConfig(Config.LIUZHOUFUYOU_HRP);
                break;
            case 4:
                loadConfig(Config.NETPLAT);
                break;
            case 5:
                loadConfig(Config.FEIYI_INTERFACE);
                break;
            case 6:
                loadConfig(Config.HRP_INTERFACE);
                break;
            case 7:
                loadConfig(Config.YXT_HRP2);
                break;
            default:
                loadConfig(Config.YXT_HRP);
        }
        System.out.println("");

        // 3.打包内容
        System.out.println("请输入待解析文本(输入P开始打包):");
        List<String> bitBucketRespTexts = new ArrayList<>();
        while(true){
            Scanner sc3 = new Scanner(System.in);
            String bitBucketRespText = sc3.nextLine();  //读取字符串型输入
            if("P".equals(bitBucketRespText)){
                break;
            }else {
                bitBucketRespTexts.add(bitBucketRespText);
            }

        };

        System.out.println("");
        System.out.println("");
        System.out.println("--------------------------------------------------------");

        pack(bitBucketRespTexts);
    }


    private static int copyFile(String srcPath,String desPath) throws IOException {
        File source = new File(srcPath);
        File dest = new File(desPath);

        if(!dest.getParentFile().exists()) {
            if(!dest.getParentFile().mkdirs()) {
                System.out.println("创建目标文件所在目录失败！");
                throw new RuntimeException("创建目标文件所在目录失败");
            }
        }



        long start = System.nanoTime();

        System.out.println("Copy File Start: "+ srcPath + " -> " + desPath);

        if(dest.exists()){
            System.out.println(desPath+"文件已存在，跳过此文件！");
            return 0;
        }else{
            Files.copy(source.toPath(), dest.toPath());
        }

        System.out.println("Copy File Complete,Use Time : " + (System.nanoTime() - start)/1000 + "ms" );
        return 1;
    }

    private static List<Map<String, Object>> parseBitBucketText(String bitBucketRespText){
        List<Map<String, Object>> list = new ArrayList<>();
        Gson gson = new Gson();
        Map<String,Object> bitBucketRespMap = gson.fromJson(bitBucketRespText, Map.class);
        ArrayList<Object> values = (ArrayList<Object>) bitBucketRespMap.get("values");
        for (Object value:values){
            Map<String, Object> path = (Map<String, Object>)((Map<String, Object>) value).get("path");
            list.add(path);
        }
        return list;
    }

    private static List<String> getFilePath(Map<String,Object> path){
        List<String> filePaths =  new ArrayList<>();
        String filePath = (String) path.get("toString");
        String extension = ((String) path.get("extension")).toUpperCase();

        if(filePath.indexOf("WebContent/")!=-1){
            filePath = filePath.substring(filePath.indexOf("WebContent/") + 11);
        }

        if(!("SQL".equals(extension)||"sql".equals(extension)) && filePath.indexOf("src/")!=-1){
            filePath = filePath.substring(filePath.indexOf("src/") + 4);
            filePath = "WEB-INF/classes/"+ filePath;
        }

        if(StringUtils.isNotBlank(CUSTOM_REPLACE_TEXT)){
            String[] replaceRegs = CUSTOM_REPLACE_TEXT.split(",");
            for(String replaceReg:replaceRegs){
                String matchText = replaceReg.split("-->")[0].trim();
                String replaceText = replaceReg.split("-->")[1].trim();
                if(filePath.indexOf(matchText)!=-1){
                    filePath = filePath.replaceAll(matchText,replaceText);
                }
            }
        }

        if("SQL".equals(extension)||"sql".equals(extension)){
            filePaths.add(PROJECT_HOME_PATH +"\\"+ filePath);
            filePaths.add(OUTPUT_HOME_PATH +"\\"+ filePath);
            filePaths.add((String) path.get("toString"));
            return filePaths;
        }

        if("JS".equals(extension)){
            filePaths.add(CLASS_HOME_PATH +"\\"+ filePath);
            filePaths.add(OUTPUT_HOME_PATH +"\\"+ filePath);
            filePaths.add((String) path.get("toString"));
            return filePaths;
        }

        if("JSP".equals(extension)){
            filePaths.add(CLASS_HOME_PATH +"\\"+ filePath);
            filePaths.add(OUTPUT_HOME_PATH +"\\"+ filePath);
            filePaths.add((String) path.get("toString"));
            return filePaths;
        }

        if("JAVA".equals(extension)){
            filePath = filePath.replaceAll("\\.java","\\.class");
            filePaths.add(CLASS_HOME_PATH +"\\"+ filePath);
            filePaths.add(OUTPUT_HOME_PATH +"\\"+ filePath);
            filePaths.add((String) path.get("toString"));
            return filePaths;
        }

        if("PROPERTIES".equals(extension)){
            filePath = filePath.replaceAll("main/resources/","");
            filePaths.add(CLASS_HOME_PATH +"\\"+ filePath);
            filePaths.add(OUTPUT_HOME_PATH +"\\"+ filePath);
            filePaths.add((String) path.get("toString"));
            return filePaths;
        }

        if("JAR".equals(extension)){
            filePath = filePath.replaceAll("classes/main/webapp/WEB-INF/","");
            filePaths.add(CLASS_HOME_PATH +"\\"+ filePath);
            filePaths.add(OUTPUT_HOME_PATH +"\\"+ filePath);
            filePaths.add((String) path.get("toString"));
            return filePaths;
        }


        filePaths.add(CLASS_HOME_PATH +"\\"+ filePath);
        filePaths.add(OUTPUT_HOME_PATH +"\\"+ filePath);
        filePaths.add((String) path.get("toString"));
        return filePaths;
    }



    public static void main(String[] args) throws IOException {
        menu();
//        String text = "{\"fromHash\":\"fd24e35d8457e3cc65d8bafae58a90a24f5ef101\",\"toHash\":\"bacecd107c1ca573d46dd567b2e7e4066231a716\",\"properties\":{\"changeScope\":\"ALL\"},\"values\":[{\"contentId\":\"8a154dca7a2795a8de2a121803a2523c7c31e737\",\"fromContentId\":\"a01c320c70d2397230cd87192101bf5a1b82bc59\",\"path\":{\"components\":[\"src\",\"HRP\",\"MTRL\",\"DAL\",\"MtrlPurchaseManage\",\"MtrlDeptApplicationPlan\",\"MtrlDeptApplicationDal.java\"],\"parent\":\"src/HRP/MTRL/DAL/MtrlPurchaseManage/MtrlDeptApplicationPlan\",\"name\":\"MtrlDeptApplicationDal.java\",\"extension\":\"java\",\"toString\":\"src/HRP/MTRL/DAL/MtrlPurchaseManage/MtrlDeptApplicationPlan/MtrlDeptApplicationDal.java\"},\"executable\":false,\"percentUnchanged\":-1,\"type\":\"MODIFY\",\"nodeType\":\"FILE\",\"srcExecutable\":false,\"links\":{\"self\":[null]},\"properties\":{\"gitChangeType\":\"MODIFY\"}}],\"size\":1,\"isLastPage\":true,\"start\":0,\"limit\":1000,\"nextPageStart\":null}";
//        pack(text);
//        printFiles(text);
//        findClass(text);
    }
}
