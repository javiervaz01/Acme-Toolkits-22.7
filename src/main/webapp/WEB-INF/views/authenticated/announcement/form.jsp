<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<acme:form>
	<acme:input-moment code="authenticated.announcement.form.label.moment" path="moment"/>
	<acme:input-textbox code="authenticated.announcement.form.label.title" path="title"/>
	<acme:input-textarea code="authenticated.announcement.form.label.body" path="body"/>
	<acme:input-select code="authenticated.announcement.form.label.is-critical" path="isCritical">
			<acme:input-option code="authenticated.announcement.form.label.is-critical.true" value="true" selected="${isCritical}"/>
			<acme:input-option code="authenticated.announcement.form.label.is-critical.false" value="false" selected="${!isCritical}"/>
	</acme:input-select>
	<acme:input-url code="authenticated.announcement.form.label.info" path="info"/>
</acme:form>
