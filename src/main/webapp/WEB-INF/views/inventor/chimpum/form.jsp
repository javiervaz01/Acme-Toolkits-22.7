<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.chimpum.form.label.code" path="code"  placeholder="ABC-123-D:yyyy/mm/dd"/>
	<jstl:choose>
	<jstl:when test="${command == 'create'}">
		<acme:input-textbox code="inventor.chimpum.form.label.creationDate" path="creationDate" readonly="true"/>
	</jstl:when>
	</jstl:choose>
	
	<acme:input-textbox code="inventor.chimpum.form.label.title" path="title" />
	<acme:input-textarea code="inventor.chimpum.form.label.description" path="description" />
	<acme:input-moment code="inventor.chimpum.form.label.startDate" path="startDate" />
	<acme:input-moment code="inventor.chimpum.form.label.endDate" path="endDate"/>
	<acme:input-textbox code="inventor.chimpum.form.label.budget" path="budget" />

	<jstl:choose>
			<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
	<acme:input-money code="inventor.chimpum.form.label.exchange" path="exchange" readonly="true"/>
			</jstl:when>		
	</jstl:choose>		
	
	<acme:input-url code="inventor.chimpum.form.label.info" path="info"/>
	<acme:input-textbox code="inventor.chimpum.form.label.item" path="item" readonly="true"/>


	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish')}">
			<acme:submit code="inventor.chimpum.form.button.update" action="/inventor/chimpum/update"/>
			<acme:submit code="inventor.chimpum.form.button.delete" action="/inventor/chimpum/delete"/>
			
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.chimpum.form.button.create" action="/inventor/chimpum/create"/>
		</jstl:when>
	</jstl:choose>		
</acme:form>
