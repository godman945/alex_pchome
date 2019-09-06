<!DOCTYPE html>
<html>
    <head>
        <title>Cities</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css">
        </head>
    <body>
        <h2>List of cities</h2>
        <table>
            <tr>
                <th>Id</th>
            </tr>
					<#if cities?exists>
        				<#list cities as city>
							<tr>
                    			<td>${city.id}</td>
                			</tr>
						</#list>
					<#else>
						DDDDDD
					</#if>
        </table>

    </body>
</html>