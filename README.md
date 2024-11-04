## Micronaut Multipart Form Decoding Demo

This project explores a problem in Micronaut 4.4.2 using Servlet and Multipart Form Decoding.

In this example, we'll post a multipart form with "smart quotes" and long dashes to Micronaut using Tomcat.

We will then extract that data 5 different ways.

It seems that the `@Part` annotation works correctly, but the `@Body` annotation converts strings using the wrong charset. This should be fixed so that string data is interpreted using `UTF8` not `latin1`.

It's important to fix this, because forms whose fields are dynamically appended do not have the option of using the `@Part` annotation.

## Running the project
- Start mincronaut per usual
- Visit http://localhost:8080/ in your browser
- Submit each of the 5 different forms in turn to see the behaviour.  You'll see that #3 fails. #4 errors (demonstrating a guess that perhaps raw bytes are available in `@Body`, which turned out to not be the case), and #5 shows a hack that fixes the bytes and hopefully helps pinpoint the problem in the codebase.


