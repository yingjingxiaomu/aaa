<%--
  Created by IntelliJ IDEA.
  User: xzl
  Date: 2018/3/6
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询登录日志</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
</head>
<body>
<%--查询--%>
<div id="logTable" style="width: 99.9%;height:99.9%">
    <table class="layui-table" id="layui_table_log" lay-filter="test" style="width: 99.9%;height:99.9%">
    </table>
    <div id="laypage"></div>
</div>

<script src="../js/layui/layui.js"></script>
<script>
    var $;
    var limitcount = 10;//每页条数
    var curnum = 1;//显示第几页
    //列表查询方法
    function productsearch(start,limitsize) {
        layui.use(['table','laypage','laydate'], function(){
            table = layui.table,
                laydate=layui.laydate,
                laypage = layui.laypage;
            table.render({
                elem: '#layui_table_log'
                , url: '../log/getLogList?page='+start+'&rows=' + limitsize
                , cols: [[ //表头
                    {checkbox: true},
                    {field:"loginNumber",title:"登录账号",width:100},
                    {field:"loginTime",title:"登陆时间",width:180},
                    {field:"loginStatus",title:"登录状态",width:130},
                    {field:"loginIp",title:"IP地址",width:130},
                    {field:"loginIpLocation",title:"IP地址所在地",width:130},
                    {field:"requestMsg",title:"入参",width:130},
                    {field:"responseMsg",title:"返回参数",width:180},
                ]]
                , page: false
                , height: 430
                ,done: function(res, curr, count){
                    //如果是异步请求数据方式，res即为你接口返回的信息。
                    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                    laypage.render({
                        elem:'laypage'
                        ,count:count
                        ,curr:curnum
                        ,limit:limitcount
                        ,scrollbar: false// 父页面 滚动条 禁止
                        ,layout: ['prev', 'page', 'next', 'skip','count','limit']
                        ,jump:function (obj,first) {
                            if(!first){
                                curnum = obj.curr;
                                limitcount = obj.limit;
                                productsearch(curnum,limitcount);
                            }
                        }
                    })
                }
            })

            //监听工具条
            table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                $ = layui.jquery
                var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
                if(layEvent === 'detail'){
                    viewLableInfo(data.attrId);
                    layer.msg(data.attrId);
                } else if(layEvent === 'del'){

                } else if(layEvent === 'edit'){

                }
            });
            //常规用法
            laydate.render({
                elem: '#createDate'
            });
            //常规用法
            laydate.render({
                elem: '#processingTime'
            });

        });
    }
    productsearch(curnum, limitcount);

</script>
</body>
</html>
