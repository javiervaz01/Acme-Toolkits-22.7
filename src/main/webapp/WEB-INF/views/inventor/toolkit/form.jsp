<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.toolkit.form.label.code" path="code" placeholder="ABC-123-D"/>
	<acme:input-textbox code="inventor.toolkit.form.label.title" path="title"/>
	<acme:input-textarea code="inventor.toolkit.form.label.description" path="description"/>
	<acme:input-textarea code="inventor.toolkit.form.label.assembly-notes" path="assemblyNotes"/>
	<acme:input-url code="inventor.toolkit.form.label.link" path="link"/>
		
	<jstl:if test="${!(command == 'create')}">
		<acme:input-money code="inventor.toolkit.form.label.retail-price" path="retailPrice" readonly="true"/>
		<acme:input-money code="inventor.toolkit.form.label.exchange" path="exchange" readonly="true"/>
	</jstl:if>	
	
	<jstl:choose>
		<jstl:when test="${command == 'show' && draftMode == false}">
			<acme:button code="inventor.toolkit.form.button.items" action="/inventor/quantity/list?toolkitId=${id}"/>
		</jstl:when>	 
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && draftMode == true}">
			<acme:button code="inventor.toolkit.form.button.items" action="/inventor/quantity/list?toolkitId=${id}"/>
			<acme:submit code="inventor.toolkit.form.button.update" action="/inventor/toolkit/update"/>
			<acme:submit code="inventor.toolkit.form.button.delete" action="/inventor/toolkit/delete"/>
			<acme:submit code="inventor.toolkit.form.button.publish" action="/inventor/toolkit/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.toolkit.form.button.create" action="/inventor/toolkit/create"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>