<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.chimpum.list.label.code" path="code" width="30%"/>
	<acme:list-column code="inventor.chimpum.list.label.creationDate" path="creationDate" width="20%"/>
	<acme:list-column code="inventor.chimpum.list.label.title" path="title" width="20%"/>
	<acme:list-column code="inventor.chimpum.list.label.startDate" path="startDate" width="10%"/>
	<acme:list-column code="inventor.chimpum.list.label.endDate" path="endDate" width="10%"/>
	<acme:list-column code="inventor.chimpum.list.label.budget" path="budget" width="10%"/>
</acme:list>
