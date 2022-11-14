# Toolbox
Some little tool
* PackKit.java： java WEB项目打包更新工具

````
    请输入打包名称:
    TEST
    请选择需要打包的项目:
        1.飞医物资
        2.HRP物资
        3.柳州妇幼物资
        4.供应链
        5.飞医接口项目
        6.HRP接口项目
        7.HRP物资
    1
    请输入待解析文本(输入P开始打包):
    {"fromHash":"8072d8c6176f6cb47ae80b3e91d0a8513daee3bb","toHash":"ac20ad399e2760a16d74526d1c58567fe6dceba4","properties":{"changeScope":"ALL"},"values":[{"contentId":"9452e745bf8647af7994ef832b61598b8158e7b4","fromContentId":"0000000000000000000000000000000000000000","path":{"components":["SQL","11.MTRL","0DDL","0TABLE","202211081015_ALTER_MTRL_ACCT_SUPPLIER_DETAIL.SQL"],"parent":"SQL/11.MTRL/0DDL/0TABLE","name":"202211081015_ALTER_MTRL_ACCT_SUPPLIER_DETAIL.SQL","extension":"SQL","toString":"SQL/11.MTRL/0DDL/0TABLE/202211081015_ALTER_MTRL_ACCT_SUPPLIER_DETAIL.SQL"},"executable":false,"percentUnchanged":-1,"type":"ADD","nodeType":"FILE","links":{"self":[null]},"properties":{"gitChangeType":"ADD"}},{"contentId":"ff0e639ec5381b6d7a632c89a115fe24769f1905","fromContentId":"0000000000000000000000000000000000000000","path":{"components":["WebContent","WEB-INF","ETL","MTRL","SunPlat","province","jiangsu","spd","sunPlatImpExp.ktr"],"parent":"WebContent/WEB-INF/ETL/MTRL/SunPlat/province/jiangsu/spd","name":"sunPlatImpExp.ktr","extension":"ktr","toString":"WebContent/WEB-INF/ETL/MTRL/SunPlat/province/jiangsu/spd/sunPlatImpExp.ktr"},"executable":false,"percentUnchanged":-1,"type":"ADD","nodeType":"FILE","links":{"self":[null]},"properties":{"gitChangeType":"ADD"}},{"contentId":"71cf41120f710f6286e64dbd2c4bf5380882fa8c","fromContentId":"0000000000000000000000000000000000000000","path":{"components":["WebContent","WEB-INF","ETL","MTRL","SunPlat","province","jiangsu","spd","sunPlatSyncImpExp.kjb"],"parent":"WebContent/WEB-INF/ETL/MTRL/SunPlat/province/jiangsu/spd","name":"sunPlatSyncImpExp.kjb","extension":"kjb","toString":"WebContent/WEB-INF/ETL/MTRL/SunPlat/province/jiangsu/spd/sunPlatSyncImpExp.kjb"},"executable":false,"percentUnchanged":-1,"type":"ADD","nodeType":"FILE","links":{"self":[null]},"properties":{"gitChangeType":"ADD"}},{"contentId":"9335bd223472516db071fe4e9ebceb5c3a6105c1","fromContentId":"0000000000000000000000000000000000000000","path":{"components":["WebContent","WEB-INF","ETL","MTRL","SunPlat","province","jiangsu","spd","sunPlatUtils.js"],"parent":"WebContent/WEB-INF/ETL/MTRL/SunPlat/province/jiangsu/spd","name":"sunPlatUtils.js","extension":"js","toString":"WebContent/WEB-INF/ETL/MTRL/SunPlat/province/jiangsu/spd/sunPlatUtils.js"},"executable":false,"percentUnchanged":-1,"type":"ADD","nodeType":"FILE","links":{"self":[null]},"properties":{"gitChangeType":"ADD"}},{"contentId":"78eb3af4fa9172798bae76859f4937f4fd83eceb","fromContentId":"0000000000000000000000000000000000000000","path":{"components":["src","HRP","MTRL","BLL","MtrlComm","MtrlSunPlat","MtrlSunPlatBll.java"],"parent":"src/HRP/MTRL/BLL/MtrlComm/MtrlSunPlat","name":"MtrlSunPlatBll.java","extension":"java","toString":"src/HRP/MTRL/BLL/MtrlComm/MtrlSunPlat/MtrlSunPlatBll.java"},"executable":false,"percentUnchanged":-1,"type":"ADD","nodeType":"FILE","links":{"self":[null]},"properties":{"gitChangeType":"ADD"}},{"contentId":"e750a17666b7353caf596f9db135f68c661efe28","fromContentId":"bb90349e72a3ce6c1dcef59b140f665614473f29","path":{"components":["src","HRP","MTRL","BLL","MtrlFinanceManage","MtrlAccountSettlement","MtrlAccountSettlementBll.java"],"parent":"src/HRP/MTRL/BLL/MtrlFinanceManage/MtrlAccountSettlement","name":"MtrlAccountSettlementBll.java","extension":"java","toString":"src/HRP/MTRL/BLL/MtrlFinanceManage/MtrlAccountSettlement/MtrlAccountSettlementBll.java"},"executable":false,"percentUnchanged":-1,"type":"MODIFY","nodeType":"FILE","srcExecutable":false,"links":{"self":[null]},"properties":{"gitChangeType":"MODIFY"}}],"size":6,"isLastPage":true,"start":0,"limit":1000,"nextPageStart":null}
    P
    
    --------------------------------------------------------
    输出目录已存在，删除目录：D:\打包目录\TEST
    Copy File Start: D:\workspace\A-project-wuzi\fywuzi\SQL/11.MTRL/0DDL/0TABLE/202211081015_ALTER_MTRL_ACCT_SUPPLIER_DETAIL.SQL -> D:\打包目录\TEST\SQL/11.MTRL/0DDL/0TABLE/202211081015_ALTER_MTRL_ACCT_SUPPLIER_DETAIL.SQL
    Copy File Complete,Use Time : 2157ms
    Copy File Start: D:\workspace\A-project-wuzi\fywuzi\out\artifacts\fywuzi_Web_exploded\WEB-INF/ETL/MTRL/SunPlat/province/jiangsu/spd/sunPlatImpExp.ktr -> D:\打包目录\TEST\WEB-INF/ETL/MTRL/SunPlat/province/jiangsu/spd/sunPlatImpExp.ktr
    Copy File Complete,Use Time : 1365ms
    Copy File Start: D:\workspace\A-project-wuzi\fywuzi\out\artifacts\fywuzi_Web_exploded\WEB-INF/ETL/MTRL/SunPlat/province/jiangsu/spd/sunPlatSyncImpExp.kjb -> D:\打包目录\TEST\WEB-INF/ETL/MTRL/SunPlat/province/jiangsu/spd/sunPlatSyncImpExp.kjb
    Copy File Complete,Use Time : 940ms
    Copy File Start: D:\workspace\A-project-wuzi\fywuzi\out\artifacts\fywuzi_Web_exploded\WEB-INF/ETL/MTRL/SunPlat/province/jiangsu/spd/sunPlatUtils.js -> D:\打包目录\TEST\WEB-INF/ETL/MTRL/SunPlat/province/jiangsu/spd/sunPlatUtils.js
    Copy File Complete,Use Time : 1271ms
    Copy File Start: D:\workspace\A-project-wuzi\fywuzi\out\artifacts\fywuzi_Web_exploded\WEB-INF\classes\HRP\MTRL\BLL\MtrlComm\MtrlSunPlat\MtrlSunPlatBll.class -> D:\打包目录\TEST\WEB-INF\classes\HRP\MTRL\BLL\MtrlComm\MtrlSunPlat\MtrlSunPlatBll.class
    Copy File Complete,Use Time : 854ms
    Copy File Start: D:\workspace\A-project-wuzi\fywuzi\out\artifacts\fywuzi_Web_exploded\WEB-INF\classes\HRP\MTRL\BLL\MtrlFinanceManage\MtrlAccountSettlement\MtrlAccountSettlementBll$1.class -> D:\打包目录\TEST\WEB-INF\classes\HRP\MTRL\BLL\MtrlFinanceManage\MtrlAccountSettlement\MtrlAccountSettlementBll$1.class
    Copy File Complete,Use Time : 812ms
    Copy File Start: D:\workspace\A-project-wuzi\fywuzi\out\artifacts\fywuzi_Web_exploded\WEB-INF\classes\HRP\MTRL\BLL\MtrlFinanceManage\MtrlAccountSettlement\MtrlAccountSettlementBll$2.class -> D:\打包目录\TEST\WEB-INF\classes\HRP\MTRL\BLL\MtrlFinanceManage\MtrlAccountSettlement\MtrlAccountSettlementBll$2.class
    Copy File Complete,Use Time : 805ms
    Copy File Start: D:\workspace\A-project-wuzi\fywuzi\out\artifacts\fywuzi_Web_exploded\WEB-INF\classes\HRP\MTRL\BLL\MtrlFinanceManage\MtrlAccountSettlement\MtrlAccountSettlementBll$3.class -> D:\打包目录\TEST\WEB-INF\classes\HRP\MTRL\BLL\MtrlFinanceManage\MtrlAccountSettlement\MtrlAccountSettlementBll$3.class
    Copy File Complete,Use Time : 807ms
    Copy File Start: D:\workspace\A-project-wuzi\fywuzi\out\artifacts\fywuzi_Web_exploded\WEB-INF\classes\HRP\MTRL\BLL\MtrlFinanceManage\MtrlAccountSettlement\MtrlAccountSettlementBll.class -> D:\打包目录\TEST\WEB-INF\classes\HRP\MTRL\BLL\MtrlFinanceManage\MtrlAccountSettlement\MtrlAccountSettlementBll.class
    Copy File Complete,Use Time : 1147ms
    输出文件列表 >> D:\打包目录\TEST\fileList.txt
    打包成功，共计9个文件 
````
 ###### 解析文本格式为BitBucket的changes请求返回响应
