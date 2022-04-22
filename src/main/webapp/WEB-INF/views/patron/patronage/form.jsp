<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<h2>
		<acme:message code="patron.patronage.form.info"/>
	</h2>

	<acme:input-select code="patron.patronage.form.label.status" path="status">
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED'}"/>
	</acme:input-select>
	<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>
	<acme:input-textarea code="patron.patronage.form.label.legal-stuff" path="legalStuff"/>
	<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
	<acme:input-moment code="patron.patronage.form.label.creation-date" path="creationDate"/>
	<acme:input-moment code="patron.patronage.form.label.start-date" path="startDate"/>
	<acme:input-moment code="patron.patronage.form.label.end-date" path="endDate"/>
	<acme:input-url code="patron.patronage.form.label.info" path="info"/>
	<acme:input-textbox code="patron.patronage.form.label.patron.identity.name" path="inventor.identity.name"/>
	<acme:input-textbox code="patron.patronage.form.label.patron.identity.surname" path="inventor.identity.surname"/>
	<acme:input-textbox code="patron.patronage.form.label.patron.identity.email" path="inventor.identity.email"/>
	<acme:input-textbox code="patron.patronage.form.label.patron.company" path="inventor.company"/>
	<acme:input-textbox code="patron.patronage.form.label.patron.statement" path="inventor.statement"/>
	<acme:input-textbox code="patron.patronage.form.label.patron.info" path="inventor.info"/>
	
	
	<h2>
		<acme:message code="patron.patronage.form.reports"/>
	</h2>
	<table class="table table-sm">
		<tr>
			<th><acme:message code="patron.patronage.list.label.sequenceNumber"/></th>
			<th><acme:message code="patron.patronage.list.label.creationTime"/></th>
			<th><acme:message code="patron.patronage.list.label.memorandum"/></th>
			<th><acme:message code="patron.patronage.list.label.info"/></th>
		</tr>
		<jstl:forEach items="${reports}" var="var">
	    	<tr>
	     		<td><acme:print value="${var.getSequenceNumber()}" /></td>
	     		<td><acme:print value="${var.getCreationTime()}" /></td>
	     		<td><acme:print value= "${var.getMemorandum()}"/></td>
	     		<td><acme:print value="${var.getInfo()}" /></td>
	    	</tr>
		</jstl:forEach>
	</table>
		
	
	
	
</acme:form>



