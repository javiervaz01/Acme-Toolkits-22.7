<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list readonly="false">
	<acme:list-column code="any.chirp.list.label.title" path="title" width="60%"/>
	<acme:list-column code="any.chirp.list.label.author" path="author" width="20%"/>
	<acme:list-column code="any.chirp.list.label.moment" path="moment" width="20%"/>
	
	
</acme:list>

<acme:button code="any.chirp.form.button.create" action="/any/chirp/create"/>
