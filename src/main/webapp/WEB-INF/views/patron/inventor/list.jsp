<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h2>
	<acme:message code="patron.inventor.list.inventor-data"/>
</h2>
<acme:list>
	<acme:list-column code="patron.inventor.list.label.username" path="username" width="10%"/>
	<acme:list-column code="patron.inventor.list.label.name" path="name" width="35%"/>
	<acme:list-column code="patron.inventor.list.label.surname" path="surname" width="35%"/>		
</acme:list>
