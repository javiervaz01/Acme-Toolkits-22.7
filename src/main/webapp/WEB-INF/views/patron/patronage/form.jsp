<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	
	<jstl:if test="${command != 'create' && !draftMode}">
		<acme:input-textbox code="patron.patronage.form.label.status" path="status" readonly="true"/>		
	</jstl:if>
	
	<acme:input-textbox code="patron.patronage.form.label.code" path="code" placeholder="ABC-123-D"/>
	<acme:input-textarea code="patron.patronage.form.label.legal-stuff" path="legalStuff"/>
	<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
	
	<jstl:if test="${command != 'create'}">
		<acme:input-money code="patron.patronage.form.label.exchange" path="exchange" readonly="true"/>
		<acme:input-moment code="patron.patronage.form.label.creation-date" path="creationDate" readonly="true"/>	
	</jstl:if>

	<acme:input-moment code="patron.patronage.form.label.start-date" path="startDate"/>
	<acme:input-moment code="patron.patronage.form.label.end-date" path="endDate"/>
	<acme:input-url code="patron.patronage.form.label.info" path="info"/>
	
	<jstl:if test="${command != 'create'}">
	<h2>
		<acme:message code="patron.patronage.form.inventor-data"/>
	</h2>
	<acme:input-textbox code="patron.patronage.form.label.inventor.identity.name" path="inventorName" readonly="true"/>
	<acme:input-textbox code="patron.patronage.form.label.inventor.identity.surname" path="inventorSurname" readonly="true"/>
	<acme:input-textbox code="patron.patronage.form.label.inventor.identity.email" path="inventorEmail" readonly="true"/>
	<acme:input-textbox code="patron.patronage.form.label.inventor.company" path="inventorCompany" readonly="true"/>
	<acme:input-textbox code="patron.patronage.form.label.inventor.statement" path="inventorStatement" readonly="true"/>
	<acme:input-url code="patron.patronage.form.label.inventor.info" path="inventorInfo" readonly="true"/>
	</jstl:if>
	
	<jstl:choose>	 
		<jstl:when test="${command == 'show' && !draftMode}">
			<acme:button code="patron.patronage.form.reports" action="/patron/patronage-report/list-by-patronage?masterId=${masterId}"/>			
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && draftMode}">
			<acme:submit code="patron.patronage.form.button.update" action="/patron/patronage/update"/>
			<acme:submit code="patron.patronage.form.button.delete" action="/patron/patronage/delete"/>
			<acme:submit code="patron.patronage.form.button.publish" action="/patron/patronage/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="patron.patronage.form.button.create" action="/patron/patronage/create?inventorId=${inventorId}"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>
