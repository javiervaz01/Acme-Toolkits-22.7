<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<h2>
		<acme:message code="inventor.patronage.form.info"/>
	</h2>

	<acme:input-select code="inventor.patronage.form.label.status" path="status">
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED'}"/>
	</acme:input-select>
	<acme:input-textbox code="inventor.patronage.form.label.code" path="code"/>
	<acme:input-textarea code="inventor.patronage.form.label.legal-stuff" path="legalStuff"/>
	<acme:input-money code="inventor.patronage.form.label.budget" path="budget"/>
	<acme:input-moment code="inventor.patronage.form.label.creation-date" path="creationDate"/>
	<acme:input-moment code="inventor.patronage.form.label.start-date" path="startDate"/>
	<acme:input-moment code="inventor.patronage.form.label.end-date" path="endDate"/>
	<acme:input-url code="inventor.patronage.form.label.info" path="info"/>
	<acme:input-textbox code="inventor.patronage.form.label.patron.identity.name" path="patron.identity.name"/>
	<acme:input-textbox code="inventor.patronage.form.label.patron.identity.surname" path="patron.identity.surname"/>
	<acme:input-textbox code="inventor.patronage.form.label.patron.identity.email" path="patron.identity.email"/>
	<acme:input-textbox code="inventor.patronage.form.label.patron.company" path="patron.company"/>
	<acme:input-textbox code="inventor.patronage.form.label.patron.statement" path="patron.statement"/>
	<acme:input-textbox code="inventor.patronage.form.label.patron.info" path="patron.info"/>
	
	<acme:button code="inventor.patronage.form.reports" action="/inventor/patronage-report/list"/>
	
		
	
	
	
</acme:form>




