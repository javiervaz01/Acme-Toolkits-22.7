<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	
	<acme:input-textbox code="administrator.systemconfiguration.form.label.currency" path="currency"/>
	<acme:input-textbox code="administrator.systemconfiguration.form.label.acceptedcurrencies" path="acceptedCurrencies"/>
	<acme:input-textbox code="administrator.systemconfiguration.form.label.strongSpamTerms" path="strongSpamTerms"/>
	<acme:input-textbox code="administrator.systemconfiguration.form.label.strongSpamThreshold" path="strongSpamThreshold"/>
	<acme:input-textbox code="administrator.systemconfiguration.form.label.weakSpamTerms" path="weakSpamTerms"/>
	<acme:input-textbox code="administrator.systemconfiguration.form.label.weakSpamThreshold" path="weakSpamThreshold"/>

</acme:form>