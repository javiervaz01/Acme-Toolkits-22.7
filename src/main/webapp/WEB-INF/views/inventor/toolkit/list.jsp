<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.toolkit.list.label.code" path="code"/>
	<acme:list-column code="inventor.toolkit.list.label.title" path="title"/>
	<acme:list-column code="inventor.toolkit.list.label.description" path="description"/>
	<acme:list-column code="inventor.toolkit.list.label.assembly-notes" path="assemblyNotes"/>
	<acme:list-column code="inventor.toolkit.list.label.link" path="link"/>
</acme:list>