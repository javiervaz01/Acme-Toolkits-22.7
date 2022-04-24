<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<h2>
		<acme:message code="patron.patronageReport.title.report"/>
	</h2>
	<acme:input-textbox code="patron.patronageReport.list.label.sequenceNumber" path="sequenceNumber"/>
	<acme:input-moment code="patron.patronageReport.list.label.creationTime" path="creationTime"/>
	<acme:input-textarea code="patron.patronageReport.list.label.memorandum" path="memorandum"/>
	<acme:input-textbox code="patron.patronageReport.list.label.info" path="info"/>
	
	<h2>
		<acme:message code="patron.patronageReport.list.label.patronage"/>
	</h2>
	<acme:input-textbox code="patron.patronage.list.label.patronage.identify.code" path="patronage.code"/>
	<acme:input-textbox code="patron.patronage.list.label.patronage.identify.status" path="patronage.status"/>
	<acme:input-textbox code="patron.patronage.list.label.patronage.identify.legalStuff" path="patronage.legalStuff"/>
	<acme:input-textbox code="patron.patronage.list.label.patronage.identify.budget" path="patronage.budget"/>
	<acme:input-textbox code="patron.patronage.list.label.patronage.identify.creationDate" path="patronage.creationDate"/>
	<acme:input-textbox code="patron.patronage.list.label.patronage.identify.startDate" path="patronage.startDate"/>
	<acme:input-textbox code="patron.patronage.list.label.patronage.identify.endDate" path="patronage.endDate"/>
	<acme:input-textbox code="patron.patronage.list.label.patronage.identify.info" path="patronage.info"/>
</acme:form>
