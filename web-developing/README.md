* Tomcat-8.5.9
* Maven-3.3.9

In the Command line write: mvn tomcat7:run 
- add clean to the Maven targets list before launching (optional)

1. JavaScript:
- Jquery (Ajax, Cookie) 
2. Json (org.json, com.google.code.gson)
3. Java EE (Servlet, JSP, JSTL)

Task #1:

* (+) 1. (15 points) Write simple Java Web App with one servlet and one JSP page. 
JSP should has AJAX call buttons (or forms if you feel better with forms) with names GET, POST, PUT, DELETE. 
Servlet should handle these requests and change its own state (inner state can be presented as a large collection of String objects). 
* (+) 2. (5 points) JSP should display inner state of servlet (optional). 
* (+) 3. (3 points) Don’t forget README (with deploy instructions for Tomcat 8) and web.xml
* (+) 4. (2 points) Use Maven to build that project
* (+) 5. (5 points) Use cookies to track amount of JSP views
* (+) 6. (5 points) Add JSTL support and use fmt functions to present data
* (+-) 7. (5 points) Use Rest-Assured framework to write a few tests for servlet
