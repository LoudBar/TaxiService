<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css">
</head>
<body>
<table>
    <tr>
        <th>Id</th>
        <th>Departure place</th>
        <th>Arrival Place</th>
        <th>Driver</th>
    </tr>
    <#list shifts as item>
        <tr>
            <td>${item.id}</td>
            <td>${item.departurePlace}</td>
            <td>${item.arrivalPlace}</td>
            <td>${item.driver.firstName} <img class="driver-avatar" alt="IMAGE" src="/files/${item.driver.avatarId}"/> </td>
        </tr>
    </#list>
</table>


</body>