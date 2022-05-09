<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.item.list.label.code" path="code"/>
	<acme:list-column code="inventor.item.list.label.name" path="name" width="10%"/>
	<acme:list-column code="inventor.item.list.label.technology" path="technology"/>
	<acme:list-column code="inventor.item.list.label.retail-price" path="retailPrice"/>
	<acme:list-column code="inventor.item.list.label.exchange" path="exchange"/>
	<acme:list-column code="inventor.item.list.label.type" path="type"/>
	
	<jstl:choose>
		<jstl:when test="${command == 'list-by-toolkit'}">
			<acme:list-column code="inventor.item.list.label.quantity" path="quantity"/>
		</jstl:when>
	</jstl:choose>
	
</acme:list>

<acme:button test="${showAddItem}" code="inventor.item.list.button.add-item" action="/inventor/quantity/create?toolkitId=${toolkitId}"/>
