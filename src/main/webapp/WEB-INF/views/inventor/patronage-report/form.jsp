<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<h2>
		<acme:message code="inventor.patronage-report.form.title.report"/>
	</h2>
	<acme:input-textbox code="inventor.patronage-report.form.label.sequence-number" path="sequenceNumber"/>
	<acme:input-moment code="inventor.patronage-report.form.label.creation-time" path="creationTime"/>
	<acme:input-textarea code="inventor.patronage-report.form.label.memorandum" path="memorandum"/>
	<acme:input-url code="inventor.patronage-report.form.label.info" path="info"/>
	<h2>
		<acme:message code="inventor.patronage-report.form.label.patronage"/>
	</h2>
	<acme:input-textbox code="inventor.patronage-report.form.label.patronage.identify.code" path="patronage.code"/>
	<acme:input-textbox code="inventor.patronage-report.form.label.patronage.identify.status" path="patronage.status"/>
	<acme:input-textbox code="inventor.patronage-report.form.label.patronage.identify.legal-stuff" path="patronage.legalStuff"/>
	<acme:input-money code="inventor.patronage-report.form.label.patronage.identify.budget" path="patronage.budget"/>
	<acme:input-money code="inventor.patronage-report.form.label.exchange" path="exchange"/>
	<acme:input-moment code="inventor.patronage-report.form.label.patronage.identify.creation-date" path="patronage.creationDate"/>
	<acme:input-moment code="inventor.patronage-report.form.label.patronage.identify.start-date" path="patronage.startDate"/>
	<acme:input-moment code="inventor.patronage-report.form.label.patronage.identify.end-date" path="patronage.endDate"/>
	<acme:input-url code="inventor.patronage-report.form.label.patronage.identify.info" path="patronage.info"/>
</acme:form>
