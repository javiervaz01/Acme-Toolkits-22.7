<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<acme:form>
	<acme:input-textbox code="any.item.form.label.name" path="name"/>
	<acme:input-textbox code="any.item.form.label.code" path="code" placeholder="ABC-123-D"/>
	<acme:input-textbox code="any.item.form.label.technology" path="technology"/>
	<acme:input-textarea code="any.item.form.label.description" path="description"/>
	<acme:input-money code="any.item.form.label.price" path="retailPrice"/>
	<acme:input-money code="any.item.form.label.exchange" path="exchange"/>
	<acme:input-url code="any.item.form.label.info" path="info"/>
	<acme:input-select code="any.item.form.label.type" path="type">
		<acme:input-option code="any.item.form.label.type.component" value="COMPONENT" selected="${type == 'COMPONENT'}"/>
		<acme:input-option code="any.item.form.label.type.tool" value="TOOL" selected="${type == 'TOOL'}"/>
	</acme:input-select>
</acme:form>
