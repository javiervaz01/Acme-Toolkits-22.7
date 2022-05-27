<%--
- menu.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
	
		<acme:menu-option code="master.menu.any">
			<acme:menu-suboption code="master.menu.any.user-account.list" action="/any/user-account/list"/>			
			<acme:menu-suboption code="master.menu.any.item.list" action="/any/item/list"/>
			<acme:menu-suboption code="master.menu.any.toolkit.list" action="/any/toolkit/list"/>
			<acme:menu-suboption code="master.menu.any.chirp.list" action="/any/chirp/list-recent"/>

		</acme:menu-option>
		
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link" action="http://www.example.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.andres-duran.favourite-link" action="https://distill.pub/"/>
			<acme:menu-suboption code="master.menu.anonymous.javier-vazquez.favourite-link" action="https://www.chollometro.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.pablo-delfin-lopez.favourite-link" action="https://www.discogs.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.alejandro-carrasco.favourite-link" action="https://www.visitarsevilla.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.pablo-robledo.favourite-link" action="https://www.learnweb3.io/"/>
			<acme:menu-suboption code="master.menu.anonymous.pablo-nunez.favourite-link" action="https://www.windy.com/"/>
		</acme:menu-option>
	
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">			
			<acme:menu-suboption code="master.menu.user-account.system-configuration.show" action="/authenticated/system-configuration/show"/>
			<acme:menu-suboption code="master.menu.user-account.announcement.list-recent" action="/authenticated/announcement/list-recent"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-suboption code="master.menu.administrator.system-configuration.show" action="/administrator/system-configuration/show"/>
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/administrator-dashboard/show"/>
			<acme:menu-suboption code="master.menu.administrator.announcement" action="/administrator/announcement/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.inventor" access="hasRole('Inventor')">			
			<acme:menu-suboption code="master.menu.inventor.patronage.list" action="/inventor/patronage/list"/>
			<acme:menu-suboption code="master.menu.inventor.patronage.list-proposed" action="/inventor/patronage/list-proposed"/>
			<acme:menu-suboption code="master.menu.inventor.toolkit.list" action="/inventor/toolkit/list-mine"/>
			<acme:menu-suboption code="master.menu.inventor.item.list" action="/inventor/item/list-mine"/>
			<acme:menu-suboption code="master.menu.inventor.patronage-report.list" action="/inventor/patronage-report/list"/>
			<acme:menu-suboption code="master.menu.inventor.chimpum.list" action="/inventor/chimpum/list"/>
			
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">			
			<acme:menu-suboption code="master.menu.patron.dashboard" action="/patron/patron-dashboard/show"/>
			<acme:menu-suboption code="master.menu.patron.patronage.list" action="/patron/patronage/list-mine"/>
			<acme:menu-suboption code="master.menu.patron.patronage-report.list" action="/patron/patronage-report/list"/>
			<acme:menu-suboption code="master.menu.patron.inventor.list" action="/patron/inventor/list"/>
		</acme:menu-option>
		
	</acme:menu-left>
	
	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.user-account" access="hasRole('Authenticated')">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-patron" action="/authenticated/patron/create" access="!hasRole('Patron')"/>
			<acme:menu-suboption code="master.menu.user-account.patron" action="/authenticated/patron/update" access="hasRole('Patron')"/>
			<acme:menu-suboption code="master.menu.user-account.become-inventor" action="/authenticated/inventor/create" access="!hasRole('Inventor')"/>
			<acme:menu-suboption code="master.menu.user-account.inventor" action="/authenticated/inventor/update" access="hasRole('Inventor')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>

</acme:menu-bar>

