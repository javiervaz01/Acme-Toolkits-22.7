<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.patronage.list.label.status" path="status" width="10%"/>
	<acme:list-column code="inventor.patronage.list.label.code" path="code"/>
	<acme:list-column code="inventor.patronage.list.label.legal-stuff" path="legalStuff"/>
	<acme:list-column code="inventor.patronage.list.label.budget" path="budget"/>
	<acme:list-column code="inventor.patronage.list.label.creation-date" path="creationDate"/>
	<acme:list-column code="inventor.patronage.list.label.start-date" path="startDate"/>
	<acme:list-column code="inventor.patronage.list.label.end-date" path="endDate"/>
	<acme:list-column code="inventor.patronage.list.label.info" path="info"/>
</acme:list>
