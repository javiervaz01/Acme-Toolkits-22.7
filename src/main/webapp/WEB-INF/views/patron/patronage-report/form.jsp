<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<h2>
		<acme:message code="patron.patronageReport.title.report"/>
	</h2>
	<acme:input-textbox code="patron.patronageReport.list.label.sequenceNumber" path="sequenceNumber"/>
	<acme:input-moment code="patron.patronageReport.list.label.creationTime" path="creationTime"/>
	<acme:input-textarea code="patron.patronageReport.list.label.memorandum" path="memorandum"/>
	<acme:input-url code="patron.patronageReport.list.label.info" path="info"/>
	<h2>
		<acme:message code="patron.patronageReport.list.label.patronage"/>
	</h2>
	<acme:input-textbox code="patron.patronage.list.label.patronage.identify.code" path="patronage.code"/>
	<acme:input-textbox code="patron.patronage.list.label.patronage.identify.status" path="patronage.status"/>
	<acme:input-textbox code="patron.patronage.list.label.patronage.identify.legal-stuff" path="patronage.legalStuff"/>
	<acme:input-money code="patron.patronage.list.label.patronage.identify.budget" path="patronage.budget"/>
		<acme:input-money code="patron.patronage.list.label.exchange" path="exchange"/>
	<acme:input-moment code="patron.patronage.list.label.patronage.identify.creation-date" path="patronage.creationDate"/>
	<acme:input-moment code="patron.patronage.list.label.patronage.identify.start-date" path="patronage.startDate"/>
	<acme:input-moment code="patron.patronage.list.label.patronage.identify.end-date" path="patronage.endDate"/>
	<acme:input-url code="patron.patronage.list.label.patronage.identify.info" path="patronage.info"/>
</acme:form>
