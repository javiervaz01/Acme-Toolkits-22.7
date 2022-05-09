<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<h2>
		<acme:message code="inventor.patronage.form.info"/>
	</h2>
	<acme:input-select code="inventor.patronage.form.label.status" path="status">
		<acme:input-option code="inventor.patronage.form.label.proposed" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="inventor.patronage.form.label.accepted" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="inventor.patronage.form.label.denied" value="DENIED" selected="${status == 'DENIED'}"/>
	</acme:input-select>

	<jstl:choose>
		<jstl:when test="${command=='show'}">
		<acme:input-textbox code="inventor.patronage.form.label.code" path="code"/>
			<acme:input-textarea code="inventor.patronage.form.label.legal-stuff" path="legalStuff"/>
			<acme:input-money code="inventor.patronage.form.label.budget" path="budget"/>
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
		</jstl:when>
	</jstl:choose>
	
	<acme:button code="inventor.patronage.form.reports" action="/inventor/patronage-report/list"/>
	<acme:button test="${command == 'show' && status == 'PROPOSED'}" code="inventor.patronage.form.button.update" action="/inventor/patronage/update?patronageId=${id}"/>
	<acme:submit test="${command == 'update' && status == 'PROPOSED'}" code="inventor.patronage.form.button.update" action="/inventor/patronage/update"/>
	<acme:button code="inventor.patronage-report.form.button.create" action="/inventor/patronage-report/create?id=${id}"/>
	
</acme:form>




