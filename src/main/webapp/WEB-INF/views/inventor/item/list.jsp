<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.item.list.label.code" path="code"/>
	<acme:list-column code="inventor.item.list.label.name" path="name" width="10%"/>
	<acme:list-column code="inventor.item.list.label.technology" path="technology"/>
	<acme:list-column code="inventor.item.list.label.retail-price" path="retailPrice"/>
	<acme:list-column code="inventor.item.list.label.type" path="type"/>
</acme:list>

<acme:button test="${showCreate}" code="inventor.item.list.button.create" action="/inventor/item/create?masterId=${masterId}"/>
