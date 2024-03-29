<?php  																														require_once($_SERVER['DOCUMENT_ROOT'] . "/eclipse.org-common/system/app.class.php");	require_once($_SERVER['DOCUMENT_ROOT'] . "/eclipse.org-common/system/nav.class.php"); 	require_once($_SERVER['DOCUMENT_ROOT'] . "/eclipse.org-common/system/menu.class.php"); 	$App 	= new App();	$Nav	= new Nav();	$Menu 	= new Menu();		include($App->getProjectCommon());    # All on the same line to unclutter the user's desktop'

$pageTitle 		= "";
$pageKeywords	= "";
$pageAuthor		= "";

ob_start();
?>
    <div id="maincontent">
	<div id="midcolumn">

<h1>The Eclipse Integration for Apache Maven (IAM)</h1>
</p>
<?php
include_once($_SERVER['DOCUMENT_ROOT'] . "/projects/fragments/proposal-page-header.php");
generate_header("YOUR PROPOSAL UNIQUE KEY HERE");
?>

<H2>Introduction</H2>
<P>The <B>Eclipse Integration for Apache Maven (IAM)</B>
Project is a new project proposal under the <A HREF="http://www.eclipse.org/technology/">Eclipse
Technology Project</A>.</P>
<P>This proposal is in the Project Proposal Phase (as
defined in the <A HREF="http://www.eclipse.org/projects/dev_process/">Eclipse
Development Process document</A>) and is written to
declare its intent and scope. This proposal is written to solicit
additional participation and input from the Eclipse community. You
are invited to comment on and/or join the project.</P>
<H2>Background</H2>
<P>Apache Maven is a very popular project
comprehension tool, and rivals Apache Ant as the most widely used
build system. While Eclipse has very good support for Apache Ant it
does not provide any integration for Apache Maven out of the box or
under the umbrella of the Eclipse Foundation, and  users are not
necessarily aware of third party integrations.</P>
<P>Apache Maven offers
significant improvements in the overall speed and quality of software
development. But because Apache Maven is designed to deliver
real-time transparency regarding the state of any project, it helps
to not only eliminate build process failures, it also enables
organizations to control and protect against the inclusion of
incompatible components during the earliest testing, integration and
packaging phases of a typical development life cycle.</P>
<P>Through standardized
and automated build processes, Apache Maven gives not only developers
and build masters visibility into the which components are being
used, but it also benefits anyone that needs information on the
quality and characteristics of the applications being built.</P>
<P>The IAM project has
already gone through several months of development under the codename
<A HREF="http://code.google.com/p/q4e/">Q4E</A>
, right now at version 0.6.1, creating a community of users and
contributors that are really interested in seeing the project become
a Eclipse Foundation project as that seems to be the natural place
for it.</P>
<H2>Scope</H2>
<P>The objectives of the IAM project are
to: 
</P>
<UL>
        <LI>Make Eclipse the IDE of choice for
        Apache Maven users</LI>
        <LI>Allow Eclipse users to explore the
        benefits of Apache Maven</LI>
        <LI>Tightly integrate with JDT</LI>
        <LI>Allow the user to create new
        Eclipse projects from Apache Maven Archetypes</LI>
        <LI>Launch Apache Maven builds from
        the standard Eclipse run configurations</LI>
        <LI>Provide the underlying libraries
        for Buckminster integration</LI>
        <LI>Explore the integration points
        with Equinox Provisioning repositories</LI>
</UL>
<H2>Description</H2>
<P>The current implementation of IAM is
documented in <A HREF="http://code.google.com/p/q4e/wiki/DevelopmentGuide">
http://code.google.com/p/q4e/wiki/DevelopmentGuide</A>.
The core library interacts with Apache Maven libraries and exposes an
Eclipse style API, used by the Apache Maven specific user interface,
like event or goal execution views, the JDT integration that will
provide the user a consistent view between Apache Maven projects and
any other project, and the Buckminster integration that will live at
the Buckminster project.</P>
<P>Wizards for new project creation and
import of existing Apache Maven projects are provided for a rich user
experience as well as integration with other Eclipse projects like WTP
or AspectJ. Supporting a wide range of eclipse plug-ins will serve as an
exemplary solution for tool builders and prove the validity of the design.</P>

<p>As Apache Maven, IAM will be cross platform and vendor neutral providing extension points for other projects to reuse and extend it.</p>

<H2>Organization</H2>
<h3>Mentors</h3>
<p>The following people have agreed to mentor the project</p>
<ul>
        <LI>Ed Merks, IBM</LI>
        <LI>Thomas Watson, IBM</LI>
        <LI>Brett Porter, Exist Global</LI>
</ul>

<h3>Initial committers</h3>
<P>The initial committers will focus on
evolving the current IAM implementation by delivering support for
Java development. Our agile development process will follow
eclipse.org's standards for openness and transparency Our goal
is to provide the infrastructure and APIs needed to integrate task
and degree-of-interest centric UIs to any Eclipse plug-ins whose
users experience information overload. As such we will actively
encourage contributions to Eclipse IAM. We also plan to help
improve the Eclipse platform by submitting patches and extension
point suggestions. The initial committers are:</P>
<UL>
        <LI>Carlos Sanchez, Exist Global: Project Lead</LI>
        <LI>Abel Mui&ntilde;o, Berggi</LI>
        <LI>Erle Mantos, Exist Global</LI>
        <LI>Jake Pezaro</LI>
        <LI>Michael Poindexter, Qumu</LI>
        <LI>Philip Dodds, Exist Global</LI>
        <LI>Brett Porter, Exist Global</LI>
        <LI>Allan Ramirez, Exist Global</LI>
</UL>
<h3>Interested parties</h3>
<P>The following projects have expressed
interest using the tools, contributing ideas, guidance and
discussion. Key contacts listed.</LI>
<UL>
        <LI>Equinox (IBM): Jeff McAffer, Pascal Rapicault</LI>
        <LI>IONA: Oisin Hurley</LI>
        <LI>Apache Cocoon: Grzegorz Kossakowski</LI>
        <LI>Apache Archiva: Joakim Erdfelt</LI>
        <LI>University of A Coruña, Spain: Fernando Bellas</LI>
        <LI>CALGB: Robert Dale</LI>
        <LI>Scala: Josh Suereth, David Bernard</LI>
        <LI>Qumu, Inc.</LI>
</UL>
<H3>Developer community</h3>
<P>We expect to extend the initial set of
committers by actively supporting a developer community. The initial
community outside of Exist Global includes Abel Mui&ntilde;o, which is
planning to extend IAM and use it as a foundation for its Appfuse
Eclipse plugin (Candy for Appfuse).</P>
<P>We are also contributing the Apache
Maven integration for Buckminster which will use the same underlying
libraries jointly developed by IAM and Buckminster committers.</P>
<P>As its already happening with
Buckminster we want to make the different pieces reusable for other
projects that want to interact with Apache Maven by providing an API
that will bridge the Eclipse and Apache Maven worlds, and
facilitation other parties contributions by making our planning and
processes accessible and transparent. 
</P>
<h3>User community</h3>
<P>IAM will improve the user experience
for all previous Apache Maven users, which represent a big community.
We need to integrate their feedback as it is critical to build an
usable user interface. It will be achieved by leveraging the standard
eclipse.org mechanisms of supporting an open project and the
community. 
</P>
<H2>Tentative Plan</H2>
<P>
2008-06 M1: Finalize WTP integration<BR/>
2008-08 M2: PDE integration<BR/>
2008-10 M3: SVN/CVS integration<BR/>
2008-12 M4: P2 integration<BR/>
2009-02 M5: Mylyn integration<BR/>
2009-04 M6: Buckminster integration<BR/>
</P>


      </div>
  </div>
<?php
	$html = ob_get_contents();
	ob_end_clean();

	# Generate the web page
	$App->generatePage($theme, $Menu, $Nav, $pageAuthor, $pageKeywords, $pageTitle, $html);
?>
