<%@ include file="/common/taglibs.jsp"%>

<title><fmt:message key="userList.title"/></title>
<h2><fmt:message key="userList.title"/></h2>

<button class="btn btn-primary" onclick="location.href='departmentList'" style="float: right; margin-top: -30px">
    <i class="icon-plus icon-white"></i> Add User</button>

<display:table name="departmentList" class="table table-condensed table-striped table-hover" requestURI="departments" id="departmentList" export="true" pagesize="10" excludedParams="ajax">
    <display:setProperty name="export.pdf.filename" value="users.pdf"/>
    <display:column property="departmentId" sortable="true" href="userform" media="html"
                    paramId="departmentId" paramProperty="departmentId" titleKey="user.id"/>
    <display:column property="departmentName" sortable="true" titleKey="user.firstName" escapeXml="true"/>
    <display:column property="departmentDescription" sortable="true" titleKey="user.lastName" escapeXml="true"/>
</display:table>