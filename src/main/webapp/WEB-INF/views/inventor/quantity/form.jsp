<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="inventor.quantity.form.label.toolkit" path="toolkit.title" readonly="true"/>
	
	<acme:input-select code="inventor.quantity.form.label.items" path="items">

		<jstl:forEach items="${items}" var="item">
			<acme:input-option code="${item.code} | ${item.name} (${item.type})" value="${item.code}" selected="${item.code.equals(selected.code)}"/>
		</jstl:forEach>
	</acme:input-select>
	
	<acme:input-integer code="inventor.quantity.form.label.quantity" path="number"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:submit code="inventor.quantity.form.button.update" action="/inventor/quantity/update"/>
			<acme:submit code="inventor.quantity.form.button.delete" action="/inventor/quantity/delete"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.quantity.form.button.create" action="/inventor/quantity/create?toolkitId=${toolkitId}"/>
		</jstl:when>		
	</jstl:choose>

</acme:form>
