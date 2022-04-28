# Publish

This guide will describe how to publish the JDesk framework to Maven Central.

The project is written in Java using Maven as the build tool.

## Get Ready for Publishing Packages

Publishing a Java artifact is not that trivial like Python or JavaScript
packages. You have to comply with several rules.

I will show the general steps to publish the artifact.

### Basic Requirements

You need to own a domain name where the package is going to live or be hosted.
It is not necessary to have a domain name for each package, you can use the
github.io page that is generated from your project, but for real packages is
required to have a professional domain. In this case, the domain is
jdesk.mathsoftware.engineer.

This is semantic, such that you follow the subsets of: engineers (all engineers)
-> mathsoftware (me, a particular engineer) -> jdesk (a project of mine).

Later, the reverse DNS has to be used for the standard Java packaging
convention. That is, engineer.mathsoftware.jdesk.

You will also need a private key to sign the artifact. Generate
a [GPG key](https://www.gnupg.org/download) to use. Then run `gpg --gen-key`
and set it up like when you generate a GitHub GPG key.

You can use Gradle or Maven as the build tool. Because of the nature of this
project, I chose to use Maven in the beginning. So this article will use Maven
to configure the `pom.xml` file.

### Create a JIRA Account and Request a Ticket

First, you will need to have a
[Sonatype JIRA account](https://issues.sonatype.org/secure/Signup!default.jspa)
to open a new ticket to request your access to publish your artifact.

This sounds really weird, at least for the first time I had to do it. Just keep
carrying on.

Then, create a
[JIRA ticket](https://issues.sonatype.org/secure/CreateIssue.jspa?issuetype=21&pid=10134)
to fill your information. I will add screenshots to get you an idea of how to
proceed. Then you will be assisted by a bot to complete your request.

![JIRA Ticket 1](screenshots/jira-ticket-1.png)

![JIRA Ticket 2](screenshots/jira-ticket-2.png)

![JIRA Ticket 3](screenshots/jira-ticket-3.png)

As shown above, you have to fill your domain name, the git repository. Then
prove ownership by adding a text record to your domain.

### Next Steps

That was the overall process. You can read
[Publish Artifacts to Maven Central \| JetBrains Space](https://www.jetbrains.com/help/space/publish-artifacts-to-maven-central.html)
and [OSSRH Guide - The Central Repository Documentation](https://central.sonatype.org/publish/publish-guide)
for more details.

Two kind of releases are used for Java packages. The normal versions, like 0.
1.0 and SNAPSHOT versions like 0.1.0-SNAPSHOT. This is also confusing, but it
only means that the SNAPSHOT version that goes to a basic repository can still
have minimum changes by the developer, so the consumer knows that version might
change a little yet. The normal version on the other hand, is deployed to the
main repository, and it takes about 4 hours to reflect the changes.

