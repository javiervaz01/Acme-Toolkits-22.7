<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.quantity.list.label.code" path="code" width="10%"/>
	<acme:list-column code="inventor.quantity.list.label.name" path="name" width="30%"/>
	<acme:list-column code="inventor.quantity.list.label.technology" path="technology" width="20%"/>
	<acme:list-column code="inventor.quantity.list.label.retail-price" path="retailPrice" width="10%"/>
	<acme:list-column code="inventor.quantity.list.label.exchange" path="exchange" width="10%"/>
	<acme:list-column code="inventor.quantity.list.label.type" path="type" width="10%"/>
	<acme:list-column code="inventor.quantity.list.label.quantity" path="number" width="10%"/>
</acme:list>

<acme:button test="${showAddItem}" code="inventor.quantity.list.button.add-item" action="/inventor/quantity/create?toolkitId=${toolkitId}"/>

