<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${titel}</title>
</head>
<body>

<center>
    <table border="1" cellspacing="0" width="1000">

        <tr>
            <th colspan="4" align="center"><h2>简历</h2></th>
        </tr>

        <#list userList as user>
        <tr>
            <td>姓名:</td>
            <td>${user.name}</td>
            <td>性别:</td>
            <td>
                <#if (user.sex)==1> <#--if后不用加$-->男<#else>女</#if>
            </td>
        </tr>

        <tr>
            <td>出生年月:</td>
            <td>${user.birthday}</td>
            <td>籍贯:</td>
            <td>${user.address}</td>
        </tr>

        <tr>
            <td>年龄:</td>
            <td>${user.age}</td>
            <td>工作年限:</td>
            <td>${user.workAge}</td>
        </tr>

        <tr>
            <td>学历:</td>
            <td>${user.education}</td>
            <td>专业:</td>
            <td>${user.major}</td>
        </tr>

        <tr>
            <td>移动电话:</td>
            <td>${user.phone}</td>
            <td>电子邮箱:</td>
            <td>${user.email}</td>
        </tr>

        <tr>
            <th colspan="4" align="left">求职意向</th>
        </tr>

        <tr>
            <td>工作性质:</td>
            <td>${user.jobCategory}</td>
            <td>目标职位:</td>
            <td>${user.targetPosition}</td>
        </tr>

        <tr>
            <td>期望薪资:</td>
            <td>${user.expectedSalary}</td>
            <td>工作地:</td>
            <td>${user.workSpace}</td>
        </tr>
        </#list>

        <tr>
            <th colspan="4" align="left">技能专长</th>
        </tr>

         <#assign x = 0 />
            <#list skillList as skill>
             <#assign x = x +1 />
                <tr>
                    <td>${skill_index+1}</td>
                    <td colspan="3" align="left">${skill.skill}</td>
                </tr>
            </#list>

        <tr>
            <th colspan="4" align="left">工作经历</th>
        </tr>

        <#assign a = 0 />
            <#list workexperienceList as workexperience>
             <#assign a = a +1 />
                   <tr>
                        <td>${workexperience.time}</td>
                      <td>${workexperience.company}</td>
                       <td colspan="2" align="center">${workexperience.particulars}</td>
                  </tr>
              </#list>

        <tr>
            <th colspan="4" align="left">教育经历</th>
        </tr>

        <#assign b = 0 />
            <#list educationexperienceList as education>
                <#assign b = b +1 />
                    <tr>
                        <td>${education.time}</td>
                        <td>${education.school}</td>
                        <td colspan="2" align="center">${education.educationBackground}</td>
                    </tr>
            </#list>

    <#assign c = 0 />
        <#list projectList as projectList>
            <#assign c = c +1 />
                <tr>
                    <th>【项目${projectList_index+1}】</th>
                    <th colspan="3" align="center">${projectList.title}</th>
                </tr>

                <tr>
                    <td>开发环境:  </td>
                    <td colspan="3" align="center">${projectList.developmentEnvironment}</td>
                </tr>

                <tr>
                    <td>项目框架:  </td>
                    <td colspan="3" align="center">${projectList.projectFramework}</td>
                </tr>

                <tr>
                    <td>项目描述:  </td>
                    <td colspan="3" align="center">${projectList.itemDescription}</td>
                </tr>

                <tr>
                    <td>责任描述:  </td>
                    <td colspan="3" align="center">${projectList.jobDuty}</td>
                </tr>

                <tr>
                    <td>功能模块:  </td>
                    <td colspan="3" align="center">${projectList.functionModule}</td>
                </tr>
         </#list>

        <tr>
            <th colspan="4" align="left">自我评价</th>
        </tr>

    <#list userList as user>
        <tr>
            <td colspan="4" align="left">${user.selfAssessment}</td>
        </tr>
        </#list>
    </table>

</center>
</body>
</html>