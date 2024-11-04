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

<h2>Form 3 - post to @Body</h2>
<form method="post" action="/post3" enctype="multipart/form-data">
    <@showFormFields />
</form>

<h2>Form 4 - post to @Body</h2>
<form method="post" action="/post4" enctype="multipart/form-data">
    <@showFormFields />
</form>

<h2>Form 5 - post to @Body and Fix</h2>
<form method="post" action="/post5" enctype="multipart/form-data">
    <@showFormFields />
</form>

</body>
</html>

<#macro showFormFields>
<label>Text 1 (input type=text): <input type="text" name="text1" value="Sample plain text"></label><br />
<label>Text 2 (textarea): <textarea name="text2" rows="1">Another simple ascii example, in textarea form.</textarea></label><br />
<label> Text 3 (textarea): <textarea name="text3" rows="1">An example using “smart quotes” and fancy dashes – basically just non-ascii.</textarea></label><br />
<input type="submit" value="Submit" />
</#macro>