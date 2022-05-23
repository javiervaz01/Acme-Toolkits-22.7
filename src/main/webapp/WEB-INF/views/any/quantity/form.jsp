<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="any.quantity.form.label.toolkit" path="toolkitTitle"/>

	<acme:input-select code="any.quantity.form.label.items" path="items">
		<jstl:forEach items="${items}" var="item">
			<acme:input-option code="${item.code} | ${item.name} (${item.retailPrice.currency})" value="${item.code}"/>
		</jstl:forEach>
	</acme:input-select>

	<acme:input-integer code="any.quantity.form.label.quantity" path="number"/>
</acme:form>
