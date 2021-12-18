<!DOCTYPE html>
<html lang="en">
<#setting date_format="dd MMMM yyyy 'г.,' HH:mm">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="../js/accordion.js"></script>
</head>
<body>
<table>
    <tr>
        <th>Id</th>
        <th>Departure place</th>
        <th>Arrival Place</th>
        <th>Driver</th>
        <th></th>
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