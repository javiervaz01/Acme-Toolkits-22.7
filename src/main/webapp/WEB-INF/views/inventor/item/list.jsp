<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.item.list.label.name" path="name"/>
	<acme:list-column code="inventor.item.list.label.code" path="code" width="10%"/>
	<acme:list-column code="inventor.item.list.label.technology" path="technology"/>
	<acme:list-column code="inventor.item.list.label.price" path="retailPrice"/>
	<acme:list-column code="inventor.item.list.label.type" path="type"/>
</acme:list>
