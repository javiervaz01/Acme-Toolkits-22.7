<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="authenticated.announcement.form.label.title" path="title"/>	
	<acme:input-select code="authenticated.announcement.form.label.status" path="status">
		<acme:input-option code="INFO" value="INFO" selected="${status == 'INFO'}"/>
		<acme:input-option code="WARNING" value="WARNING" selected="${status == 'WARNING'}"/>
		<acme:input-option code="IMPORTANT" value="IMPORTANT" selected="${status == 'IMPORTANT'}"/>
	</acme:input-select>
	<acme:input-textarea code="authenticated.announcement.form.label.text" path="text"/>
	<acme:input-url code="authenticated.announcement.form.label.info" path="info"/>
</acme:form>
