<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
	<h2>
		<acme:message code="inventor.patronage.form.info"/>
	</h2>
	<acme:input-select code="inventor.patronage.form.label.status" path="status">
		<acme:input-option code="inventor.patronage.form.label.proposed" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="inventor.patronage.form.label.accepted" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="inventor.patronage.form.label.denied" value="DENIED" selected="${status == 'DENIED'}"/>
	</acme:input-select>

	<acme:input-textbox code="inventor.patronage.form.label.code" path="code" placeholder="ABC-123-D"/>
	<acme:input-textarea code="inventor.patronage.form.label.legal-stuff" path="legalStuff"/>
	<acme:input-money code="inventor.patronage.form.label.budget" path="budget"/>
	<acme:input-money code="inventor.patronage.form.label.exchange" path="exchange"/>
	<acme:input-moment code="inventor.patronage.form.label.creation-date" path="creationDate"/>
	<acme:input-moment code="inventor.patronage.form.label.start-date" path="startDate"/>
	<acme:input-moment code="inventor.patronage.form.label.end-date" path="endDate"/>
	<acme:input-url code="inventor.patronage.form.label.info" path="info"/>
     <h2>
	    <acme:message code="inventor.patronage.form.patron-data"/>
    </h2>
	<acme:input-textbox code="inventor.patronage.form.label.patron.identity.name" path="patronName"/>
	<acme:input-textbox code="inventor.patronage.form.label.patron.identity.surname" path="patronSurname"/>
	<acme:input-textbox code="inventor.patronage.form.label.patron.identity.email" path="patronEmail"/>
	<acme:input-textbox code="inventor.patronage.form.label.patron.company" path="patronCompany"/>
	<acme:input-textbox code="inventor.patronage.form.label.patron.statement" path="patronStatement"/>
	<acme:input-textbox code="inventor.patronage.form.label.patron.info" path="patronInfo"/>

	<jstl:if test="${status == 'PROPOSED'}">
		<acme:submit code="inventor.patronage.form.button.accept" action="/inventor/patronage/accept"/>
		<acme:submit code="inventor.patronage.form.button.deny" action="/inventor/patronage/deny"/>
	</jstl:if>
	<jstl:if test="${status == 'ACCEPTED'}">
		<acme:button code="inventor.patronage.form.reports" action="/inventor/patronage-report/list-by-patronage?masterId=${id}"/>
	</jstl:if>
</acme:form>




