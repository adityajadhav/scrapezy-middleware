# scrapezy-middleware
**Web scraping as a service with auto API generation _[Winner, Amazon Web Services - Best Use of AWS, Wildhacks 2017, Northwestern University]_**

# Inspiration
Every business can benefit from web scraping and a customized way to monitor any site that you are interested in which can often be a challenging task. Also, if there is an API available on top of that for every website that will be really helpful for the developers to extract useful information.

The need for all those requirements inspired us to build scrapezy which provides “scrapping as a service” along with the API for any website.

# What it does
Scrapezy provides web scrapping as a service and to build an API on top of any website. This is not only for web developers but also for users without any programming knowledge. To facilitate that we provide two interfaces in the form of web application and chrome extension. It takes input as the target url, data elements and schema and returns the api url which contains the extracted information in the form of user specified schema.

# How we built it
Web application was built using AngularJS, HTML and Bootstrap. The backend is MongoDB. We used Java to build the REST APIs and connect the whole thing. The chrome extension was built using javascript.

# Challenges we ran into
Generating dynamic objects to match the user specified schema.
Its was a daunting task to parse the information from the websites offering different content such as single page applications which generate different class and id every time and some requires session information to get the page contents.
Keeping the application simple and easy to use.
Accomplishments that we're proud of
Its works!!

# What's next for Scrapezy
We will integrate more features such as adding session information and to support multiple types of output extractors
