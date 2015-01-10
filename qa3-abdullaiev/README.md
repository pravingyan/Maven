rozetka
=======

This is the project I'd had developed at Bionic University on QA Selenium course. 
Some refactoring had been made after finishing the course.

This is the simple test automation project that consists of three main packages: 

	1. Pages - PageObject classes that represent functionality of site pages 
	2. Tests - this is where the testing happens 
	3. Utils - helper classes

Here goes the sort description of them.

All the page classes are inherited from the BasePage class which represents 
functionality that every page on site has (like search, log in, etc.). 
Fields of such classes are mostly locators of elements stored in Selenium By objects. 
Methods in such classes do the actions on site like a typical user does.

All the test classes are inherited from the BaseTest class, in which set up and
tear down of browser, test data and other things like that are being performed.

Utils package contains the following helper classes:
- Browser class that wraps all the actions with Selenium WebDriver
- Checker class for wrapping different kinds of asserts (though some of them may seem 
  redundant, it's better to keep all assertions in one place because with further 
  project development the need in different asserts will grow)
- Logger class that helps to send different kinds of messages to console
- StringHelper class that may generate random strings and emails
- TestData class that stores data for test execution
- TimeHelper class just to do Thread.sleep() in more elegant way
