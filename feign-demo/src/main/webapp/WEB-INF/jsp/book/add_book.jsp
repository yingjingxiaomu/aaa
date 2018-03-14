<%--
  Created by IntelliJ IDEA.
  User: wjj
  Date: 2018/3/1
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body{padding: 50px 100px;}
        .layui-upload-img{width: 92px; height: 92px; margin: 0 10px 10px 0;}
        hr{margin: 30px 0;}
    </style>
</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/layui/css/layui.css"/>
<script src="<%=request.getContextPath()%>/js/layui/layui.js"></script>

<body>
<div id="addDiv" style="display: none">
<form class="layui-form" id="addFrom">
    <div class="layui-form-item">
        <label class="layui-form-label">书籍名称</label>
        <div class="layui-input-block">
            <input type="text" name="name" required  lay-verify="required" placeholder="请输入书籍名称" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">类型</label>
        <div class="layui-input-block">
            <select name="type" lay-verify="required">
                <option value=""></option>
                <option value="武侠">武侠</option>
                <option value="爱情">爱情</option>
                <option value="都市">都市</option>
                <option value="玄幻">玄幻</option>
            </select>
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
            <label><input type="radio" name="putaway" value="1" title="上架"></label>
            <label><input type="radio" name="putaway" value="2" title="下架" checked></label>
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
            <textarea name="info" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>

   <div class="layui-form-item layui-form-text">
       <label class="layui-form-label">封面</label>
       <div class="layui-upload">
           <button type="button" class="layui-btn" id="test2">图片上传</button>
           <div class="layui-upload-list" id="demo2"></div>
       </div>
       </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo" onclick="addBookButton()">提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</div>
<script>
    var $;
//时间
   layui.use('laydate', function(){
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#test1',//指定元素
            type: 'datetime'
        });
    });

    layui.use('upload', function() {
    var $ = layui.jquery
        , upload = layui.upload;

    upload.render({
        elem: '#test2'
        , url: '../book/fileupload'
        , multiple: true
        , number: 3
        , size: 1024
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                alert(result.length)
                $('#demo2').append('<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img">')
            });
        }
        , done: function (res) {
            alert(res+"-----");
            //上传完毕
        }
        , allDone: function (obj) {
            console.log(obj)
        }
    });
})
    //上传图片
   layui.use('upload', function(){
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#test2' //绑定元素
            ,url: '../book/fileupload' //上传接口
            ,done: function(res){
               alert() //上传完毕回调
            }
            ,error: function(){
                //请求异常回调
            }
        });
    });

//富文本编辑器
    layui.use('layedit', function() {
        var layedit = layui.layedit;

        var index = layedit.build('demo', {
            //hideTool: ['image']
            uploadImage: {
                url: '../book/fileupload'
                , type: 'post'
            }
            //,tool: []
            //,height: 100
        });
    })

    //添加
    function addBookButton() {
        $ = layui.jquery;
                $.ajax({
                    url:"../book/addBook",
                    type:"post",
                    data:$("#addFrom").serialize(),
                    dateType:"text",
                    success:function () {
                        alert("新增成功");
                        layer.close('<%=request.getContextPath()%>/book/toAddBookList')
                        location.reload();
                    }
                })

    }

</script>
</body>
</html>
