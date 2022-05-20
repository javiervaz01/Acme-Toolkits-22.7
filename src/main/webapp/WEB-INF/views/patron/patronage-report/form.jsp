<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<h2>
		<acme:message code="patron.patronage-report.title.report"/>
	</h2>
	<acme:input-textbox code="patron.patronage-report.form.label.sequence-number" path="sequenceNumber" readonly="true"/>
	
	<jstl:if test="${command != 'create'}">
		<acme:input-moment code="patron.patronage-report.form.label.creation-time" path="creationTime" readonly="true"/>
	</jstl:if>
	
	<acme:input-textarea code="patron.patronage-report.form.label.memorandum" path="memorandum"/>
	<acme:input-url code="patron.patronage-report.form.label.info" path="info"/>
	
	<jstl:choose>
		<jstl:when test="${!command == 'create'}">
			<h2>
				<acme:message code="patron.patronage-report.form.label.patronage"/>
			</h2>
			<acme:input-textbox code="patron.patronage.form.label.patronage.identify.code" path="patronageCode" readonly="true"/>
			<acme:input-textbox code="patron.patronage.form.label.patronage.identify.status" path="patronageStatus" readonly="true"/>
			<acme:input-textbox code="patron.patronage.form.label.patronage.identify.legal-stuff" path="patronagelegalStuff" readonly="true"/>
			<acme:input-money code="patron.patronage.form.label.patronage.identify.budget" path="patronageBudget" readonly="true"/>
			<acme:input-money code="patron.patronage.form.label.exchange" path="exchange" readonly="true"/>
			<acme:input-moment code="patron.patronage.form.label.patronage.identify.creation-date" path="patronageCreationDate" readonly="true"/>
			<acme:input-moment code="patron.patronage.form.label.patronage.identify.start-date" path="patronageStartDate" readonly="true"/>
			<acme:input-moment code="patron.patronage.form.label.patronage.identify.end-date" path="patronageEndDate" readonly="true"/>
			<acme:input-url code="patron.patronage.form.label.patronage.identify.info" path="patronageInfo" readonly="true"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:input-checkbox code="patron.patronage-report.form.label.confirmation" path="confirmation"/>
			<acme:submit code="patron.patronage-report.form.button.create" action="/patron/patronage-report/create?masterId=${masterId}"/>
		</jstl:when>
	</jstl:choose>
</acme:form>
