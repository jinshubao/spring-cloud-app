**项目文档生成工具**
    
* 修改gradle.build文件

        ext {
            //修改以下配置
            apiLocatoin = "http://localhost:8080"           //接口地址
            //tags = []                                     //API类(暂时不支持)               生成指定类名下的API文档, 如：tags = ['user-api', 'product-api'], 对应类为：UserApi,ProductApi
            //paths = []                                    //API路径(暂时不支持)             生成指定路径的API文档, 如： paths = ['/api/user/list', '/api/user/account'] 
            apiGroup = "接口分组"                            //API接口分组名        该值为空，生成所有分组的文档
        }


* 执行**gradle clean asciidoctor**命令生成文档

* 文件说明：

        build
            asciidoc
                generated
                    definitions.adoc    //数据结构定义
                    overview.adoc       //概述
                    paths.adoc          //API文档
                    security.adoc       
                html5                   //生成HTML文档位置
                    index.html
                pdf                     //生成PDF文档位置
                    index.pdf
            swagger
                swagger.json            //临时文件
        src
            docs
                asciidoc
                    extensions          //扩展点
                    appendices.adoc     //文档附加数据
                    demo.adoc           //Demo
                    index.adoc          //主文档
                    manual.adoc         //操作手册
                    records.adoc        //文档修改记录
                    records-demo.adoc   //文档修改记录Demo
                    
             
* 文档修改记录说明
    
    按照以下格式编写文档变更记录
    
        == 变更记录
        
        === 接口变更记录
        
        [options="header", cols=".^2,.^5,.^9"]
        |===
        |序号|接口|描述|变更日期
        |1 |<<_account_infous_get,个人信息>>|增加bindBankCardStatus字段，增加payPasswordStatus字段，增加realNameAuthStatus字段
        |2 |<<friend_list_get,好友列表>>|增加friendInvestAwardCashDesc字段，增加friendInvestAwardCash字段
        |===
    > 链接地址的拼写方法为：(url.replaceAll("/", "_") + requestMethod).toLowerCase()，如：接口地址为'/user/list'，请求方法为'get'，链接地址为：'_user_list_get'
                
    以上代码生成如下街表格：
    
    序号 | 接口 |描述
    ---|--- |---
    |1 |[个人信息](_account_infous_get)|增加bindBankCardStatus字段，增加payPasswordStatus字段，增加realNameAuthStatus字段
    |2 |[好友列表](_friend_list_get)|增加friendInvestAwardCashDesc字段，增加friendInvestAwardCash字段