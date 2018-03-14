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
    <title>查询书籍</title>
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
    <a class="layui-btn layui-btn-mini" onclick="addBook()" lay-event="edit">添加</a>
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del" onclick="deleteAllBook()">批量删除</a>
    <table class="layui-table" id="layui_table_id" lay-filter="test" style="width: 99.9%;height:99.9%">
    </table>
    <div id="laypage"></div>
</div>

<%--添加的Div弹框 与from表单--%>
<div id="addDiv" style="display: none">
    <form class="layui-form" id="addBookForm">
        <input type="hidden" name="id" id="id">
        <div class="layui-form-item">
            <label class="layui-form-label">书籍名称</label>
            <div class="layui-input-block">
                <input type="text" name="name" required  lay-verify="required" placeholder="请输入书籍名称" autocomplete="off" class="layui-input" id="name">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">类型</label>
            <div class="layui-input-block">
                <select name="type" lay-verify="required" id="type">
                    <option value=""></option>
                    <option value="武侠">武侠</option>
                    <option value="爱情">爱情</option>
                    <option value="都市">都市</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <label><input type="radio" name="putaway" value="上架" title="上架"></label>
                <label><input type="radio" name="putaway" value="下架" title="下架"></label>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">回头客</label>
            <div class="layui-input-block">
                <input type="checkbox" name="people" value="老年" title="老年">
                <input type="checkbox" name="people" value="少年" title="少年">
                <input type="checkbox" name="people" value="青少年" title="青少年">
                <input type="checkbox" name="people" value="中年" title="中年">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">出版时间</label>
            <div class="layui-inline"> <!-- 注意：这一层元素并不是必须的 -->
                <input type="text"  class="layui-input" id="test1" name="ctime">
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">书籍详情</label>
            <div class="layui-input-block">
                <textarea  id="info" placeholder="请输入内容" name="info" style="border: 0; width: 99%;height:30px"></textarea>
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">封面</label>
            <div style="width: 400px">
                <div id="demo2"></div>
                <div style="padding-left:105px">
                    <input type="hidden" id="image" name="heading">
                    <button type="button" class="layui-btn" id="test2">
                        <i class="layui-icon">&#xe67c;</i>上传图片
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>
<script src="../js/layui/layui.js"></script>
<%--操作--%>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
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
                , url: '../book/getBookList?tPage='+start+'&tNumber=' + limitsize
                , cols: [[ //表头
                     {checkbox: true}
                    ,{field:'id', title: 'ID',width:150, sort: true}
                    ,{field:'name', title: '书籍名称', width:150, sort: true}
                    ,{field:'type', title: '类型', width:150, sort: true,edit:true}
                    ,{field:'putaway', title: '是否上架', width:150}
                    ,{field:'info', title: '简介', width:200}
                    ,{field:'people', title: '适用人群', width:150}
                    ,{field:'ctime', title: '时间', width:150}
                    ,{field: 'heading', title: '封面', width: 150,templet:'<div><img src="{{d.heading}}" alt="图片不存在"></div>',style:'height:50px;width:50px;line-height:50px!important;'}
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
                    layer.confirm("确认要删除吗，删除后不能恢复", { title: "删除确认" }, function (index) {
                        layer.close(index);
                        $.post("../book/deleteBook?id="+data.id, function (data) {
                            location.reload();
                        });
                    });
                } else if(layEvent === 'edit'){
                    if(data.id !=null){
                        updateBook();
                        layui.use('form', function(){
                            form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
                            $("#id").val(data.id);
                            $("#name").val(data.name);
                            $("#info").val(data.info);
                            $("#test1").val(data.ctime);
                            $("#image").val(data.heading);
                            $("#type").val(data.type);
                            $('#demo2').html('<img src="' + data.heading + '" width="100" height="100" alt="图片不存在">')
                            $("input[name='putaway'][value=" +data.putaway+ "]").attr("checked", true);
                            var arr = new Array([]);
                            arr  = data.people.split(",");
                            $.each(arr,function(i,item){
                                $("input[name='people'][value="+item+"]").attr("checked","checked");
                            });
                            form.render();
                        });
                    }
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

    //时间
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#test1',//指定元素
            type: 'datetime'
        });
    });

    //上传图片
    layui.use('upload', function(){
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#test2' //绑定元素
            ,url: '../book/fileupload' //上传接口
            ,done: function(res){
                //上传完毕回调
                $ = layui.jquery;
                $("#image").val(res.path);
                $('#demo2').html('<img src="' + res.path + '" width="100" height="100" alt="图片不存在">')
            }
            ,error: function(res){

            }
        });
    });

    //Demo
    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });

    //表单修改
    function updateBook(){
        addtab("修改");
    }

    //表单新增
    function addBook(){
        addtab("新增");
    }

    //表单ajax提交
    function addtab(msg) {
         $ = layui.jquery
         layui.use('layedit', function(){
            layedit = layui.layedit;
            indexs = layedit.build('info'); //建立编辑器
        });
            layer.open({
                type: 1,
                title: msg+"用户信息", //不显示标题栏
                skin: 'layui-layer-demo', //样式类名
                closeBtn: 1, //不显示关闭按钮
                shift: 2,
                area: ['800px', '650px'], //宽高
                shadeClose: true, //开启遮罩关闭
                content:$("#addDiv"),
                scrollbar: false, // 父页面 滚动条 禁止
                btnAlign: 'c',
                btn:['提交'],
                yes: function(index, layero){
                    layedit.sync(indexs);
                    $.ajax({
                        url:"../book/addBook",
                        type:"post",
                        data:$("#addBookForm").serialize(),
                        dataType:"json",
                        async:true,
                        success:function (data) {
                            layer.close(index);
                            location.reload();
                        }
                    })
                },
                end: function () {
                    location.reload();
                },

            });
        }

    //批量删除
    function deleteAllBook(){
        $ = layui.jquery
        var ids = "";
        var checkStatus = table.checkStatus('layui_table_id')
            ,data = checkStatus.data;
        for (var i in data){
            ids+=","+data[i].id;
        }
        if(ids== ""){
            layer.msg("请选择需要删除的专题");
        }else{
            ids=ids.substr(1);
            layer.confirm("确认删除勾选的专题？", {icon: 3, title:"确认"}, function(){
                $.ajax({
                    url:"../book/deleteAllBook",
                    type:"post",
                    data:{"ids":ids},
                    dataType:"json",
                    success:function(data){
                        if(data.success){
                            layer.msg("删除成功!");
                            window.location.href = location;
                        }else{
                            layer.msg("删除失败!");
                        }
                    }
                })
            }, function(){
            });
        }
    }
</script>
</body>
</html>
