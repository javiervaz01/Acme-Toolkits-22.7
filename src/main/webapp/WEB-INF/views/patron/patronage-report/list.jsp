<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="patron.patronage-report.list.label.patronage.identify.code" path="patronage.code"/>
	<acme:list-column code="patron.patronage-report.list.label.sequence-number" path="sequenceNumber" width="10%"/>
	<acme:list-column code="patron.patronage-report.list.label.creation-time" path="creationTime"/>
	<acme:list-column code="patron.patronage-report.list.label.info" path="info"/>
</acme:list>

<jstl:if test="${masterId != null}">
	<acme:button code="patron.patronage-report.list.button.create" action="/patron/patronage-report/create?masterId=${masterId}"/>
</jstl:if>
