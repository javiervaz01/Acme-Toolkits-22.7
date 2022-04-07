<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="any.user-account.form.label.name" path="identity.name" readonly="true"/>
	<acme:input-textbox code="any.user-account.form.label.surname" path="identity.surname" readonly="true"/>
	<acme:input-email code="any.user-account.form.label.email" path="identity.email" readonly="true"/>
</acme:form>
