<html>
<head>
    <title>${title}</title>
    <#include "inline-css.ftlh" />
</head>
<body>
<h1>${title}</h1>

<h2>Form 1 - post to pull out strings</h2>
<form method="post" action="/post1" enctype="multipart/form-data">
    <@showFormFields />
</form>

<h2>Form 2 - post to pull out bytes</h2>
<form method="post" action="/post2" enctype="multipart/form-data">
    <@showFormFields />
</form>

<p>Thanks. Have a nice day.</p>
</body>
</html>

<#macro showFormFields>
    Text 1 (input type=text): <input type="text" name="text1" value="Sample plain text"><br />
    Text 2 (textarea): <textarea name="text2" rows="1">Another simple ascii example, in textarea form.</textarea><br />
    Text 3 (textarea): <textarea name="text3" rows="10">
An example using “smart quotes” and fancy dashes – basically just non-ascii. These will be posted by the browser as UTF-8 per standard, but then interpreted as strings by Micronaut using latin-1. The only way to retrieve the data successfully is to grab the raw bytes and convert them to a string manually, which is not great.

As such it is not possible to handle a multipart form with arbitrary form fields. The @Body annotation seems to force items into a string incorrectly. Especially when the form that is being posted is dynamically created and the fields contained within it are not known in advance. This issue with servlet/multipart is a bit of a problem.
    </textarea><br />
    <input type="submit" value="submit" />
</#macro>