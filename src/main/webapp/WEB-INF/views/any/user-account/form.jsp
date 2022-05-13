<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="any.user-account.form.label.name" path="identity.name"/>
	<acme:input-textbox code="any.user-account.form.label.surname" path="identity.surname"/>
	<acme:input-email code="any.user-account.form.label.email" path="identity.email"/>
	<acme:input-textbox code="any.user-account.form.label.role-list" path="roleList"/>
</acme:form>
