<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.patronageReport.list.label.sequenceNumber" path="sequenceNumber" width="10%"/>
	<acme:list-column code="inventor.patronageReport.list.label.creationTime" path="creationTime"/>
	<acme:list-column code="inventor.patronageReport.list.label.memorandum" path="memorandum"/>
	<acme:list-column code="inventor.patronageReport.list.label.info" path="info"/>
	<acme:list-column code="inventor.patronage.list.label.patronage.identify.status" path="patronage.status"/>
	<acme:list-column code="inventor.patronage.list.label.patronage.identify.legalStuff" path="patronage.legalStuff"/>
	<acme:list-column code="inventor.patronage.list.label.patronage.identify.budget" path="patronage.budget"/>
	<acme:list-column code="inventor.patronage.list.label.patronage.identify.creationDate" path="patronage.creationDate"/>
	<acme:list-column code="inventor.patronage.list.label.patronage.identify.startDate" path="patronage.startDate"/>
	<acme:list-column code="inventor.patronage.list.label.patronage.identify.endDate" path="patronage.endDate"/>
	<acme:list-column code="inventor.patronage.list.label.patronage.identify.info" path="patronage.identify.info"/>	
</acme:list>