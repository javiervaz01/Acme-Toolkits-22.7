<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="patron.inventor.form.label.name" path="name"/>
	<acme:input-textbox code="patron.inventor.form.label.surname" path="surname"/>
	<acme:input-email code="patron.inventor.form.label.email" path="email"/>
</acme:form>

<acme:button code="patron.inventor.list.button.create" action="/patron/patronage/create?inventorId=${id}"/>
