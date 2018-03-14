<%--
  Created by IntelliJ IDEA.
  User: xzl
  Date: 2018/3/1
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询用户</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <style type="text/css">.layui-table-fixed-r td{height:30px!important;}
    .layui-table-fixed-r th{height:30px!important;}
    .layui-table img {
        max-width: 30px;min-height: 30px;
    }
    </style>
</head>
<body>

<%--查询--%>
<div id="pTable" style="width: 99.9%;height:99.9%">
    <table class="layui-table" id="layui_table_id" lay-filter="test" style="width: 99.9%;height:99.9%">
    </table>
    <div id="laypage"></div>
</div>

<%--详情点击弹出 Div弹框 --%>
<div id="qrCodeDiv" style="display: none">
    <br>
    <h2 align="center">请扫描下方二维码</h2>
    <div>
        <div style="width: 400px">
            <div style="padding-left:240px" id="imgDiv"></div>
        </div>
    </div>
</div>

<script src="../js/layui/layui.js"></script>
<%--操作--%>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">查看详情</a>
</script>

<script>
    var $;
    var form;
    var limitcount = 10;//每页条数
    var curnum = 1;//显示第几页
    //列表查询方法
    function productsearch(start,limitsize) {
        layui.use(['table','laypage','laydate'], function(){
                table = layui.table,
                laydate=layui.laydate,
                laypage = layui.laypage;
            table.render({
                elem: '#layui_table_id'
                , url: '../user/getUserList?page='+start+'&rows=' + limitsize
                , cols: [[ //表头
                     {checkbox: true}
                    ,{field:'id', title: 'ID',width:80, sort: true}
                    ,{field:'name', title: '用户名称', width:80, sort: true}
                    ,{field:'sex', title: '性别', width:80, sort: true,edit:true}
                    ,{field:'birthday', title: '出生年月', width:80}
                    ,{field:'address', title: '籍贯', width:80}
                    ,{field:'age', title: '年龄', width:80}
                    ,{field:'workAge', title: '工作年限', width:80}
                    ,{field:'education', title: '学历', width:80}
                    ,{field:'major', title: '专业', width:80}
                    ,{field:'phone', title: '电话', width:80}
                    ,{field:'email', title: '邮箱', width:80}
                    ,{field:'jobCategory', title: '工作性质', width:80}
                    ,{field:'targetPosition', title: '目标职位', width:80}
                    ,{field:'expectedSalary', title: '期望薪资', width:80}
                    ,{field:'workSpace', title: '工作地', width:80}
                    ,{field:'selfAssessment', title: '自我评价', width:80}
                    ,{field: 'heading', title: '头像', width: 80,templet:'<div><img src="{{d.heading}}" alt="图片不存在"></div>',style:'height:50px;width:50px;line-height:50px!important;'}
                    ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150 ,hidden:true}
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
                    var id = data.id;
                    getQrCode(id)
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
    productsearch( curnum, limitcount);

    function getQrCode(id){
        $ = layui.jquery
        $.ajax({
            url:"../user/getQrCode",
            data:{"content":"<%=request.getContextPath()%>http://192.168.0.114:8763/user/toUserADDList?id="+id,"id":id},
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.success){
                    $('#imgDiv').html('<img src="' + data.path + '"  width="260" height="260" alt="图片不存在">')
                    getCode();
                }else{
                    alert("系统错误");
                }

            }
        })
    }
    //弹框展示
    function getCode() {
        layer.open({
            type: 1,
            title: "查看个人简历信息", //不显示标题栏
            skin: 'layui-layer-demo', //样式类名
            closeBtn: 1, //不显示关闭按钮
            shift: 2,
            area: ['800px', '650px'], //宽高
            shadeClose: true, //开启遮罩关闭
            content:$("#qrCodeDiv"),
            scrollbar: false, // 父页面 滚动条 禁止
            btnAlign: 'c',
            end: function () {
                location.reload();
            },

        });
    }
</script>
</body>
</html>
