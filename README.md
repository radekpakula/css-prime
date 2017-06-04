# CssPrime Add-on for Vaadin 8

${ComponentClassName} is a UI component add-on for Vaadin 8.

UI Extension for adding and managing css rules.

Extension create map for css rule in shared state.
Adds a style branch to the head section of the HTML source code
Allows you to update selected css rules

## Download release

Official releases of this add-on are available at Vaadin Directory. For Maven instructions, download and reviews, go to http://vaadin.com/addon/cssprime

## Building and running demo

git clone https://github.com/radekpakula/css-prime.git
mvn clean install
cd demo
mvn jetty:run

To see the demo, navigate to http://localhost:8080/

### Version 1.0-SNAPSHOT


## Roadmap

This component is developed as a hobby with no public roadmap or any guarantees of upcoming releases. That said, the following features are planned for upcoming releases:

## Issue tracking

The issues for this add-on are tracked on its github.com page. All bug reports and feature requests are appreciated. 

## Contributions

Contributions are welcome, but there are no guarantees that they are accepted as such. Process for contributing is the following:
- Fork this project
- Create an issue to this project about the contribution (bug or feature) if there is no such issue about it already. Try to keep the scope minimal.
- Develop and test the fix or functionality carefully. Only include minimum amount of code needed to fix the issue.
- Refer to the fixed issue in commit
- Send a pull request for the original project
- Comment on the original issue that you have implemented a fix for it

## License & Author

Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.

CssPrime is written by Radek Pakuła

# Developer Guide

## Getting started

Here is a simple example on how to try out the add-on component:

CssPrime css = new CssPrime();
UI.getCurrent().addExtension(css);
css.setStyle(<your css rule>);
css.setStyle(<your key rule>,<your css rule>);

For a more comprehensive example, see src/test/java/org/vaadin/template/demo/DemoUI.java