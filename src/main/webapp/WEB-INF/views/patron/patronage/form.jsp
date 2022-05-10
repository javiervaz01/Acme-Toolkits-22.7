<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<h2>
		<acme:message code="patron.patronage.form.info"/>
	</h2>
	<acme:input-select code="patron.patronage.form.label.status" path="status">
		<acme:input-option code="patron.patronage.form.label.proposed" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="patron.patronage.form.label.accepted" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="patron.patronage.form.label.denied" value="DENIED" selected="${status == 'DENIED'}"/>
	</acme:input-select>
	<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>
	<acme:input-textarea code="patron.patronage.form.label.legal-stuff" path="legalStuff"/>
	<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
	<acme:input-money code="patron.patronage.form.label.exchange" path="exchange"/>
	<acme:input-moment code="patron.patronage.form.label.creation-date" path="creationDate"/>
	<acme:input-moment code="patron.patronage.form.label.start-date" path="startDate"/>
	<acme:input-moment code="patron.patronage.form.label.end-date" path="endDate"/>
	<acme:input-url code="patron.patronage.form.label.info" path="info"/>
	<h2>
		<acme:message code="patron.patronage.form.inventor-data"/>
	</h2>
	<acme:input-textbox code="patron.patronage.form.label.inventor.identity.name" path="inventorName"/>
	<acme:input-textbox code="patron.patronage.form.label.inventor.identity.surname" path="inventorSurname"/>
	<acme:input-textbox code="patron.patronage.form.label.inventor.identity.email" path="inventorEmail"/>
	<acme:input-textbox code="patron.patronage.form.label.inventor.company" path="inventorCompany"/>
	<acme:input-textbox code="patron.patronage.form.label.inventor.statement" path="inventorStatement"/>
	<acme:input-url code="patron.patronage.form.label.inventor.info" path="inventorInfo"/>
	<acme:button code="patron.patronage.form.reports" action="/patron/patronage-report/list-by-patronage?masterId=${masterId}"/>
</acme:form>
