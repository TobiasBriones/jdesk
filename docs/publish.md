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

