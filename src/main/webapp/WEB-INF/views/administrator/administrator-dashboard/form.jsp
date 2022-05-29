<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<h3>
	<acme:message code="administrator.dashboard.form.title.proposed"/>
</h3>
<acme:message code="administrator.dashboard.form.proposed"/>
<acme:print value="${numberOfProposedPatronages}"/>

<table class="table table-sm">
	<tr>
		<th><acme:message code="administrator.dashboard.form.label.currency"/></th>
		<th><acme:message code="administrator.dashboard.form.label.average"/></th>
		<th><acme:message code="administrator.dashboard.form.label.average-exchange"/></th>
		<th><acme:message code="administrator.dashboard.form.label.deviation"/></th>
		<th><acme:message code="administrator.dashboard.form.label.min"/></th>
		<th><acme:message code="administrator.dashboard.form.label.min-exchange"/></th>
		<th><acme:message code="administrator.dashboard.form.label.max"/></th>
		<th><acme:message code="administrator.dashboard.form.label.max-exchange"/></th>
	</tr>
	<jstl:forEach items="${statsBudgetofProposedPatronages}" var="var">
    	<tr>
     		<td><jstl:out value="${var.currency}" /></td>
			<td><acme:format value="${var.average}" format="{0,number,0.00}"/></td> 
			<td><jstl:out value="${var.exchangeAverage}"/></td> 
     		<td><acme:format value="${var.deviation}" format="{0,number,0.00}"/></td>
     		<td><acme:format value="${var.min}" format="{0,number,0.00}"/></td>
     		<td><jstl:out value="${var.exchangeMin}"/></td>
     		<td><acme:format value="${var.max}" format="{0,number,0.00}"/></td>
     		<td><jstl:out value="${var.exchangeMax}" /></td>
    	</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message code="administrator.dashboard.form.title.accepted"/>
</h3>
<acme:message code="administrator.dashboard.form.accepted"/>
<acme:print value="${numberOfAcceptedPatronages}"/>

<table class="table table-sm">
	<tr>
		<th><acme:message code="administrator.dashboard.form.label.currency"/></th>
		<th><acme:message code="administrator.dashboard.form.label.average"/></th>
		<th><acme:message code="administrator.dashboard.form.label.average-exchange"/></th>
		<th><acme:message code="administrator.dashboard.form.label.deviation"/></th>
		<th><acme:message code="administrator.dashboard.form.label.min"/></th>
		<th><acme:message code="administrator.dashboard.form.label.min-exchange"/></th>
		<th><acme:message code="administrator.dashboard.form.label.max"/></th>
		<th><acme:message code="administrator.dashboard.form.label.max-exchange"/></th>
	</tr>
	<jstl:forEach items="${statsBudgetofAcceptedPatronages}" var="var">
    	<tr>
     		<td><jstl:out value="${var.currency}" /></td>
			<td><acme:format value="${var.average}" format="{0,number,0.00}"/></td> 
			<td><jstl:out value="${var.exchangeAverage}"/></td> 
     		<td><acme:format value="${var.deviation}" format="{0,number,0.00}"/></td>
     		<td><acme:format value="${var.min}" format="{0,number,0.00}"/></td>
     		<td><jstl:out value="${var.exchangeMin}"/></td>
     		<td><acme:format value="${var.max}" format="{0,number,0.00}"/></td>
     		<td><jstl:out value="${var.exchangeMax}" /></td>
    	</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message code="administrator.dashboard.form.title.denied"/>
</h3>
<acme:message code="administrator.dashboard.form.denied"/>
<acme:print value="${numberOfDeniedPatronages}"/>

<table class="table table-sm">
	<tr>
		<th><acme:message code="administrator.dashboard.form.label.currency"/></th>
		<th><acme:message code="administrator.dashboard.form.label.average"/></th>
		<th><acme:message code="administrator.dashboard.form.label.average-exchange"/></th>
		<th><acme:message code="administrator.dashboard.form.label.deviation"/></th>
		<th><acme:message code="administrator.dashboard.form.label.min"/></th>
		<th><acme:message code="administrator.dashboard.form.label.min-exchange"/></th>
		<th><acme:message code="administrator.dashboard.form.label.max"/></th>
		<th><acme:message code="administrator.dashboard.form.label.max-exchange"/></th>
	</tr>
	<jstl:forEach items="${statsBudgetofDeniedPatronages}" var="var">
    	<tr>
     		<td><jstl:out value="${var.currency}" /></td>
			<td><acme:format value="${var.average}" format="{0,number,0.00}"/></td> 
			<td><jstl:out value="${var.exchangeAverage}"/></td>  
     		<td><acme:format value="${var.deviation}" format="{0,number,0.00}"/></td>
     		<td><acme:format value="${var.min}" format="{0,number,0.00}"/></td>
     		<td><jstl:out value="${var.exchangeMin}"/></td>
     		<td><acme:format value="${var.max}" format="{0,number,0.00}"/></td>
     		<td><jstl:out value="${var.exchangeMax}" /></td>
    	</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message code="administrator.dashboard.form.title.tools"/>
</h3>
<acme:message code="administrator.dashboard.form.tools"/>
<acme:print value="${numberOfTools}"/>

<table class="table table-sm">
	<tr>
		<th><acme:message code="administrator.dashboard.form.label.currency"/></th>
		<th><acme:message code="administrator.dashboard.form.label.average"/></th>
		<th><acme:message code="administrator.dashboard.form.label.average-exchange"/></th>
		<th><acme:message code="administrator.dashboard.form.label.deviation"/></th>
		<th><acme:message code="administrator.dashboard.form.label.min"/></th>
		<th><acme:message code="administrator.dashboard.form.label.min-exchange"/></th>
		<th><acme:message code="administrator.dashboard.form.label.max"/></th>
		<th><acme:message code="administrator.dashboard.form.label.max-exchange"/></th>
	</tr>
	<jstl:forEach items="${statsRetailPriceOfTools}" var="var">
    	<tr>
     		<td><jstl:out value="${var.currency}" /></td>
			<td><acme:format value="${var.average}" format="{0,number,0.00}"/></td> 
			<td><jstl:out value="${var.exchangeAverage}"/></td>  
     		<td><acme:format value="${var.deviation}" format="{0,number,0.00}"/></td>
     		<td><acme:format value="${var.min}" format="{0,number,0.00}"/></td>
     		<td><jstl:out value="${var.exchangeMin}"/></td>
     		<td><acme:format value="${var.max}" format="{0,number,0.00}"/></td>
     		<td><jstl:out value="${var.exchangeMax}" /></td>
    	</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message code="administrator.dashboard.form.title.components"/>
</h3>
<acme:message code="administrator.dashboard.form.components"/>
<acme:print value="${numberOfComponents}"/>

<table class="table table-sm">
	<tr>
		<th><acme:message code="administrator.dashboard.form.label.technology"/></th>
		<th><acme:message code="administrator.dashboard.form.label.currency"/></th>
		<th><acme:message code="administrator.dashboard.form.label.average"/></th>
		<th><acme:message code="administrator.dashboard.form.label.average-exchange"/></th>
		<th><acme:message code="administrator.dashboard.form.label.deviation"/></th>
		<th><acme:message code="administrator.dashboard.form.label.min"/></th>
		<th><acme:message code="administrator.dashboard.form.label.min-exchange"/></th>
		<th><acme:message code="administrator.dashboard.form.label.max"/></th>
		<th><acme:message code="administrator.dashboard.form.label.max-exchange"/></th>
	</tr>
	<jstl:forEach items="${statsRetailPriceOfComponents}" var="var">
    	<tr>
    		<td><jstl:out value="${var.technology}" /></td>
     		<td><jstl:out value="${var.currency}" /></td>
     		<td><acme:format value="${var.average}" format="{0,number,0.00}"/></td> 
     		<td><jstl:out value="${var.exchangeAverage}"/></td>  
     		<td><acme:format value="${var.deviation}" format="{0,number,0.00}"/></td>
     		<td><acme:format value="${var.min}" format="{0,number,0.00}"/></td>
     		<td><jstl:out value="${var.exchangeMin}"/></td>
     		<td><acme:format value="${var.max}" format="{0,number,0.00}"/></td>
     		<td><jstl:out value="${var.exchangeMax}" /></td>
    	</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message code="administrator.dashboard.form.title.chimpum"/>
</h3>
<acme:message code="administrator.dashboard.form.chimpum"/>
<acme:print value="${ratioOfChimpums}%"/>

<table class="table table-sm">
	<tr>
		<th><acme:message code="administrator.dashboard.form.label.currency"/></th>
		<th><acme:message code="administrator.dashboard.form.label.average"/></th>
		<th><acme:message code="administrator.dashboard.form.label.deviation"/></th>
		<th><acme:message code="administrator.dashboard.form.label.min"/></th>
		<th><acme:message code="administrator.dashboard.form.label.max"/></th>
	</tr>
	<jstl:forEach items="${statsChimpums}" var="var">
    	<tr>
     		<td><jstl:out value="${var.currency}" /></td>
			<td><acme:format value="${var.average}" format="{0,number,0.00}"/></td> 
     		<td><acme:format value="${var.deviation}" format="{0,number,0.00}"/></td>
     		<td><acme:format value="${var.min}" format="{0,number,0.00}"/></td>
     		<td><acme:format value="${var.max}" format="{0,number,0.00}"/></td>
    	</tr>
	</jstl:forEach>
</table>