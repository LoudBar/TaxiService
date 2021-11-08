<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
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
            <td>
                <div class="shift-info shift-info-active">
                    <div class="arrow">
                        <div class="arrow-top"></div>
                        <div class="arrow-bottom"></div>
                    </div>
                </div>
            </td>
        </tr>
    </#list>
</table>


</body>