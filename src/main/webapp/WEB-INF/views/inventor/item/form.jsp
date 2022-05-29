<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.item.form.label.name" path="name"/>
	<acme:input-textbox code="inventor.item.form.label.code" path="code" placeholder="ABC-123-D"/>
	<acme:input-textbox code="inventor.item.form.label.technology" path="technology"/>
	<acme:input-textarea code="inventor.item.form.label.description" path="description"/>
	<acme:input-money code="inventor.item.form.label.retail-price" path="retailPrice"/>

	<jstl:choose>
			<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
	<acme:input-money code="inventor.item.form.label.exchange" path="exchange" readonly="true"/>
			</jstl:when>		
	</jstl:choose>		
	
	<acme:input-url code="inventor.item.form.label.info" path="info"/>
	<acme:input-select code="inventor.item.form.label.type" path="type">
		<acme:input-option code="inventor.item.form.label.type.component" value="COMPONENT" selected="${type == 'COMPONENT'}"/>
		<acme:input-option code="inventor.item.form.label.type.tool" value="TOOL" selected="${type == 'TOOL'}"/>
	</acme:input-select>

	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && draftMode == true}">
			<acme:submit code="inventor.item.form.button.update" action="/inventor/item/update"/>
			<acme:submit code="inventor.item.form.button.delete" action="/inventor/item/delete"/>
			<acme:submit code="inventor.item.form.button.publish" action="/inventor/item/publish"/>
			
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.item.form.button.create" action="/inventor/item/create"/>
		</jstl:when>
		
		<jstl:when test="${type == 'TOOL' && chimpum != null }">
			<acme:button code="inventor.item.form.button.see-chimpum" action="/inventor/chimpum/show?id=${chimpum.id}"/>
		</jstl:when>
		<jstl:when test="${type == 'TOOL' && chimpum == null }">
			<acme:button code="inventor.item.form.button.create-chimpum" action="/inventor/chimpum/create?masterId=${id}"/>
		</jstl:when>
	</jstl:choose>		
</acme:form>
