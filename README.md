# Jobby
This application was built using the Spring framework with Hibernate for database connectivity and Thymeleaf for template generating. The app was built as a senior capstone project in the spring quarter ad DePaul University by the following people:

	- Craig Bruenger: craigmbruenger@gmail.com
	- Carissa Ward: carissanward@gmail.com
	- Josh Hawk: joshhawk00Wgmail.com
	- Jonathan Huerta: huertajonathan132@gmail.com
	- Jake Dremann:
	- Qingyuan Guo:
 

What this app does:

- Provides a service for job seeking candidates and companies to create accounts and take surveys by providing their unique input and then being matched with their ideal counter parts.
- Serves dynamic web content according to URI, including index and detail pages for job seeking candidates, their resumes, their profile pictures, and their survey results as well as for companies and their posted jobs.
- Includes database connectivity, where all of the associated data for job seeking candidates' and companies' data is stored
- Maintains persistent data, using an H2 database
- Manages data with Hibernate
- Allows a user to perform CRUD (create, read, update, delete) operations on their associated data
- Performs server-side form validation for adding/updating job seeking candidates, companies, jobs, profile pictures, and resumes
- Uses a database
- Serves static assets, such as images, fonts, CSS, and JS
- Implements user authentication with Spring security
- Stores users' encrypted passwords
- Allows admin access with a set of predefined queries for finding candidates/companies by ids and email addresses and for finding jobs by id or company id. Allows activating/deactivating user accounts and deleting accounts/jobs.
- Utilizes an algorithm to match candidates with jobs and these matches are presenting, in a ranked order, to both the candidates and the companies who posted the jobs.