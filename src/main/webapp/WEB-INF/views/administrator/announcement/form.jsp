<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<acme:form>
	<acme:input-textbox code="authenticated.announcement.form.label.title" path="title"/>
	<acme:input-textarea code="authenticated.announcement.form.label.body" path="body"/>
	<acme:input-checkbox code="authenticated.announcement.form.label.is-critical" path="isCritical"/>
	<acme:input-url code="authenticated.announcement.form.label.info" path="info"/>
	<acme:input-checkbox code="administrator.announcement.form.label.confirmation" path="confirmation"/>
	<acme:submit code="administrator.announcement.form.button.create" action="/administrator/announcement/create"/>
</acme:form>