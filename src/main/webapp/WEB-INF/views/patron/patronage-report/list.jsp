<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="patron.patronage.list.label.patronage.identify.code" path="patronage.code"/>
	<acme:list-column code="patron.patronageReport.list.label.sequenceNumber" path="sequenceNumber" width="10%"/>
	<acme:list-column code="patron.patronageReport.list.label.creationTime" path="creationTime"/>
	<acme:list-column code="patron.patronageReport.list.label.memorandum" path="memorandum"/>
	<acme:list-column code="patron.patronageReport.list.label.info" path="info"/>
</acme:list>