<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="inventor.patronageReport.list.label.sequenceNumber" path="sequenceNumber"/>
	<acme:input-moment code="inventor.patronageReport.list.label.creationTime" path="creationTime"/>
	<acme:input-textarea code="inventor.patronageReport.list.label.memorandum" path="memorandum"/>
	<acme:input-textbox code="inventor.patronageReport.list.label.info" path="info"/>
</acme:form>
