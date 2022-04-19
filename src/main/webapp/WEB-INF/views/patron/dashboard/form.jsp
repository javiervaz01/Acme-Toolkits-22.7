<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h2>
	<acme:message code="patron.dashboard.form.title.general-indicators"/>
</h2>

<h3>
	<acme:message code="patron.dashboard.form.title.proposed"/>
</h3>
<acme:message code="patron.dashboard.form.proposed"/>
<acme:print value="${numberOfProposedPatronages}"/>

<table class="table table-sm">
	<tr>
		<th><acme:message code="patron.dashboard.form.label.currency"/></th>
		<th><acme:message code="patron.dashboard.form.label.average"/></th>
		<th><acme:message code="patron.dashboard.form.label.deviation"/></th>
		<th><acme:message code="patron.dashboard.form.label.min"/></th>
		<th><acme:message code="patron.dashboard.form.label.max"/></th>
	</tr>
	<jstl:forEach items="${statsBudgetofProposedPatronages}" var="var">
    	<tr>
     		<td><jstl:out value="${var.currency}" /></td>
     		<td><fmt:formatNumber value="${var.average}" maxFractionDigits="1"/></td>
     		<td><fmt:formatNumber value="${var.deviation}" maxFractionDigits="1"/></td>
     		<td><fmt:formatNumber value="${var.min}" maxFractionDigits="1"/></td>
     		<td><fmt:formatNumber value="${var.max}" maxFractionDigits="1"/></td>
    	</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message code="patron.dashboard.form.title.accepted"/>
</h3>
<acme:message code="patron.dashboard.form.accepted"/>
<acme:print value="${numberOfAcceptedPatronages}"/>

<table class="table table-sm">
	<tr>
		<th><acme:message code="patron.dashboard.form.label.currency"/></th>
		<th><acme:message code="patron.dashboard.form.label.average"/></th>
		<th><acme:message code="patron.dashboard.form.label.deviation"/></th>
		<th><acme:message code="patron.dashboard.form.label.min"/></th>
		<th><acme:message code="patron.dashboard.form.label.max"/></th>
	</tr>
	<jstl:forEach items="${statsBudgetofAcceptedPatronages}" var="var">
    	<tr>
     		<td><jstl:out value="${var.currency}" /></td>
     		<td><jstl:out value="${var.average}" /></td>
     		<td><jstl:out value="${var.deviation}" /></td>
     		<td><jstl:out value="${var.min}" /></td>
     		<td><jstl:out value="${var.max}" /></td>
    	</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message code="patron.dashboard.form.title.denied"/>
</h3>
<acme:message code="patron.dashboard.form.denied"/>
<acme:print value="${numberOfDeniedPatronages}"/>

<table class="table table-sm">
	<tr>
		<th><acme:message code="patron.dashboard.form.label.currency"/></th>
		<th><acme:message code="patron.dashboard.form.label.average"/></th>
		<th><acme:message code="patron.dashboard.form.label.deviation"/></th>
		<th><acme:message code="patron.dashboard.form.label.min"/></th>
		<th><acme:message code="patron.dashboard.form.label.max"/></th>
	</tr>
	<jstl:forEach items="${statsBudgetofDeniedPatronages}" var="var">
    	<tr>
     		<td><jstl:out value="${var.currency}" /></td>
     		<td><jstl:out value="${var.average}" /></td>
     		<td><jstl:out value="${var.deviation}" /></td>
     		<td><jstl:out value="${var.min}" /></td>
     		<td><jstl:out value="${var.max}" /></td>
    	</tr>
	</jstl:forEach>
</table>