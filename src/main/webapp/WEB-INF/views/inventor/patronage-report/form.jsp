<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<acme:form>
	<h2>
		<acme:message code="inventor.patronage-report.form.title.report"/>
	</h2>
	<acme:input-textbox code="inventor.patronage-report.form.label.sequence-number" path="sequenceNumber" readonly="true"/>
	
	<jstl:if test="${command != 'create'}">
		<acme:input-moment code="inventor.patronage-report.form.label.creation-time" path="creationTime" readonly="true"/>
	</jstl:if>
	
	<acme:input-textarea code="inventor.patronage-report.form.label.memorandum" path="memorandum"/>
	<acme:input-url code="inventor.patronage-report.form.label.info" path="info"/>
	
	<jstl:choose>
		<jstl:when test="${!command == 'create'}">
			<h2>
				<acme:message code="inventor.patronage-report.form.label.patronage"/>
			</h2>
			<acme:input-textbox code="inventor.patronage-report.form.label.patronage.identify.code" path="patronageCode"/>
			<acme:input-textbox code="inventor.patronage-report.form.label.patronage.identify.status" path="patronageStatus"/>
			<acme:input-textbox code="inventor.patronage-report.form.label.patronage.identify.legal-stuff" path="patronageLegalStuff"/>
			<acme:input-money code="inventor.patronage-report.form.label.patronage.identify.budget" path="patronageBudget"/>
			<acme:input-money code="patron.patronage.form.label.exchange" path="exchange" readonly="true"/>
			<acme:input-moment code="inventor.patronage-report.form.label.patronage.identify.creation-date" path="patronageCreationDate"/>
			<acme:input-moment code="inventor.patronage-report.form.label.patronage.identify.start-date" path="patronageStartDate"/>
			<acme:input-moment code="inventor.patronage-report.form.label.patronage.identify.end-date" path="patronageEndDate"/>
			<acme:input-url code="inventor.patronage-report.form.label.patronage.identify.info" path="patronageInfo"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:input-checkbox code="inventor.patronage-report.form.label.confirmation" path="confirmation"/>
			<acme:submit code="inventor.patronage-report.form.button.create" action="/inventor/patronage-report/create?masterId=${masterId}"/>
		</jstl:when>
	</jstl:choose>
</acme:form>
